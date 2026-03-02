package Services;

import ModelsDTO.DashboardDTO;
import ModelsDTO.OrderPreparingDTO;
import ModelsDTO.OrderPreparingItemDTO;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
public class OrderingServices {
    private static final String BASE_URL = "http://localhost:5018/api/ManageOrdering/";
    private String getResponseFromConnection(HttpURLConnection connection) throws Exception {
        int responseCode = connection.getResponseCode();
        InputStream inputStream;

        // 1. 判断是读正常流还是错误流
        if (responseCode >= 200 && responseCode < 300) {
            inputStream = connection.getInputStream();
        } else {
            inputStream = connection.getErrorStream();
        }

        if (inputStream == null) {
            throw new Exception("Server returned error code " + responseCode + " but no error message.");
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        String responseMsg = response.toString();
        //System.out.println("Status: " + responseCode + " | Msg: " + responseMsg);

        if (responseCode >= 200 && responseCode < 300) {
            return responseMsg;
        } else {
            throw new Exception(responseMsg);
        }
    }
    public List<OrderPreparingDTO> GetOrderPreparing() throws Exception{
        URL url = new URL(BASE_URL + "GetOrderPreparing");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();
        if(responseCode != 200){
            return new ArrayList<>();
        }

        String jsonResponse = getResponseFromConnection(connection);

        JSONArray jsonArray =new JSONArray(jsonResponse);
        List<OrderPreparingDTO> resultList = new ArrayList<>();
        for(int i = 0 ; i < jsonArray.length() ; i++){
            JSONObject obj = jsonArray.getJSONObject(i);

            int orderID = obj.getInt("orderID");
            String orderNo = obj.getString("orderNo");
            String pickupCode = obj.optString("pickupCode", "N/A");
            String customerName = obj.getString("customerName");
            String orderTime = obj.getString("orderTime");
            String status = obj.getString("status");

            JSONArray itemsArray = obj.getJSONArray("items");
            List<OrderPreparingItemDTO> itemsList = new ArrayList<>();
            for (int j = 0; j < itemsArray.length(); j++) {
                JSONObject itemObj = itemsArray.getJSONObject(j);

                itemsList.add(new OrderPreparingItemDTO(
                        itemObj.getString("beverageName"),
                        itemObj.getInt("quantity"),
                        itemObj.getString("description")
                ));
            }
            resultList.add(new OrderPreparingDTO(orderID, orderNo, pickupCode, customerName, orderTime, status, itemsList));
        }
        return resultList;
    }
    public List<OrderPreparingDTO> GetOrderReady() throws Exception{
        URL url = new URL(BASE_URL + "GetOrderReadyForPickup");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();
        if (responseCode != 200){
            return new ArrayList<>();
        }
        String jsonResponse = getResponseFromConnection(connection);

        JSONArray jsonArray =new JSONArray(jsonResponse);
        List<OrderPreparingDTO> resultList = new ArrayList<>();
        for(int i = 0 ; i < jsonArray.length() ; i++){
            JSONObject obj = jsonArray.getJSONObject(i);

            int orderID = obj.getInt("orderID");
            String orderNo = obj.getString("orderNo");
            String pickupCode = obj.optString("pickupCode", "N/A");
            String customerName = obj.getString("customerName");
            String orderTime = obj.getString("orderTime");
            String status = obj.getString("status");

            JSONArray itemsArray = obj.getJSONArray("items");
            List<OrderPreparingItemDTO> itemsList = new ArrayList<>();
            for (int j = 0; j < itemsArray.length(); j++) {
                JSONObject itemObj = itemsArray.getJSONObject(j);

                itemsList.add(new OrderPreparingItemDTO(
                        itemObj.getString("beverageName"),
                        itemObj.getInt("quantity"),
                        itemObj.getString("description")
                ));
            }
            resultList.add(new OrderPreparingDTO(orderID, orderNo, pickupCode, customerName, orderTime, status, itemsList));
        }
        return resultList;
    }
    public String MarkAsReady(int id) throws Exception{
        URL url = new URL(BASE_URL + "MarkAsReady/" + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        return getResponseFromConnection(connection);
    }

    public String OrderingComplete(int id) throws Exception{
        URL url = new URL(BASE_URL + "OrderingComplete/" + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        return getResponseFromConnection(connection);
    }

    public DashboardDTO GetDashboardStatus() throws Exception{
        URL url = new URL(BASE_URL + "GetDashboardStats");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();
        if (responseCode != 200){

            throw new Exception("Failed to load dashboard stats. Code: " + responseCode);
        }
        String jsonResponse = getResponseFromConnection(connection);
        JSONObject obj = new JSONObject(jsonResponse);

        double revenue = obj.optDouble("totalRevenue", 0);

        int todayCount = obj.optInt("totalOrderToday", 0);

        List<OrderPreparingDTO> pendingList = new ArrayList<>();

        if (obj.has("pending") && !obj.isNull("pending")) {
            JSONArray pendingArray = obj.getJSONArray("pending");

            for (int i = 0; i < pendingArray.length(); i++) {
                JSONObject pObj = pendingArray.getJSONObject(i);

                // 根据 image_1e8160.png 截图，Key 全部是小写驼峰
                int orderID = pObj.getInt("orderID");
                String orderNo = pObj.getString("orderNo");
                String pickupCode = pObj.optString("pickupCode", "N/A");
                String customerName = pObj.getString("customerName");
                String orderTime = pObj.getString("orderTime");
                String status = pObj.getString("status");

                // ★注意★：你的 .NET 代码里没有返回 Items 列表
                // 所以这里创建一个空的列表传进去，防止报错
                List<OrderPreparingItemDTO> emptyItems = new ArrayList<>();

                // 使用 OrderPreparingDTO
                pendingList.add(new OrderPreparingDTO(
                        orderID,
                        orderNo,
                        pickupCode,
                        customerName,
                        orderTime,
                        status,
                        emptyItems
                ));
            }
        }

        return new DashboardDTO(BigDecimal.valueOf(revenue), todayCount, pendingList);
    }
}
