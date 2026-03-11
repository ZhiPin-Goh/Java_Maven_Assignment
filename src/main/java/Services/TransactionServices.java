package Services;

import ModelsDTO.TransactionDTO;
import ModelsDTO.TransactionItemsDTO;
import ModelsDTO.TransactionPreparingDTO;
import ModelsDTO.TransactionPreparingItemDTO;
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
public class TransactionServices {
    private static final String BASE_URL = "http://localhost:5018/api/ManageTransaction/";

    private String getResponseFromConnection(HttpURLConnection connection) throws Exception {
        int responseCode = connection.getResponseCode();
        InputStream inputStream;

        // 1. 选频道
        if (responseCode >= 200 && responseCode < 300) {
            inputStream = connection.getInputStream();
        } else {
            inputStream = connection.getErrorStream();
        }

        // 2. 读数据
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        String responseMsg = response.toString();


        // 3. 决定结果
        if (responseCode >= 200 && responseCode < 300) {
            return responseMsg;
        } else {
            throw new Exception(responseMsg);
        }
    }
    public List<TransactionDTO> GetTransactionHistory(int id) throws Exception {
        URL url = new URL(BASE_URL + "GettransactionHistory/" + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        try{
            if (connection.getResponseCode() == 404){
                return new ArrayList<>();
            }
        }
        catch (Exception ex){
            return new ArrayList<>();
        }
        String jsonResponse = getResponseFromConnection(connection);

        JSONArray jsonArray = new JSONArray(jsonResponse);
        List<TransactionDTO> transactionList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject tObj = jsonArray.getJSONObject(i);

            int userId = tObj.getInt("userID");
            String transNo = tObj.getString("transactionNo");
            BigDecimal total = BigDecimal.valueOf(tObj.getDouble("totalAmount"));
            String time = tObj.getString("orderTime");
            String status = tObj.getString("status");
            String pickupCode = tObj.optString("pickupCode", "N/A");
            BigDecimal pointsused = BigDecimal.valueOf(tObj.getDouble("pointsUsed"));
            BigDecimal discount = BigDecimal.valueOf(tObj.getDouble("discountAmount"));
            BigDecimal pointsearned = BigDecimal.valueOf(tObj.getDouble("pointsEarned"));
            JSONArray itemsArray = tObj.getJSONArray("items");

            List<TransactionItemsDTO> itemsList = new ArrayList<>();

            for (int j = 0; j < itemsArray.length(); j++) {
                JSONObject itemObj = itemsArray.getJSONObject(j);

                itemsList.add(new TransactionItemsDTO(
                        itemObj.getString("beverageName"),
                        itemObj.getString("imagePath"),
                        itemObj.getString("description"),
                        itemObj.getInt("quantity"),
                        BigDecimal.valueOf(itemObj.getDouble("price"))
                ));
            }

            transactionList.add(new TransactionDTO(userId, transNo, total, time, status, pickupCode, pointsused, discount, pointsearned, itemsList));
        }

        return transactionList;
    }
    public List<TransactionPreparingDTO> GetTransactionPreparing(int id) throws Exception{
        URL url = new URL(BASE_URL + "GetPreparingOrder/" + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        try{
            if (connection.getResponseCode() == 404){
                return new ArrayList<>();
            }
        }catch (Exception ex){
            return  new ArrayList<>();
        }

        String jsonResponse = getResponseFromConnection(connection);
        JSONArray jsonArray = new JSONArray(jsonResponse);
        List<TransactionPreparingDTO> preparingList = new ArrayList<>();

        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject pObj = jsonArray.getJSONObject(i);
            String ordertime = pObj.getString("orderTime");
            String status = pObj.getString("status");
            String pickupcode = pObj.getString("pickupCode");
            JSONArray itemsArray = pObj.getJSONArray("items");
            List<TransactionPreparingItemDTO> itemList = new ArrayList<>();

            for (int j =0; j< itemsArray.length(); j++){
                JSONObject itemObj = itemsArray.getJSONObject(j);

                itemList.add(new TransactionPreparingItemDTO(
                        itemObj.getString("beverageName"),
                        itemObj.getString("imagePath"),
                        itemObj.getString("description"),
                        itemObj.getInt("quantity"),
                        BigDecimal.valueOf(itemObj.getDouble("price"))
                ));
            }
            preparingList.add(new TransactionPreparingDTO(
                    ordertime,
                    status,
                    pickupcode,
                    itemList
            ));
        }
        return preparingList;
    }
    public TransactionDTO GetOneTransactionDetails(String transactionNo) throws Exception {
        URL url = new URL(BASE_URL + "GetOneTransactionDetails/" + transactionNo);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new Exception("Server Error: " + responseCode);
        }

        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        reader.close();

        JSONObject obj = new JSONObject(result.toString());

        int userID = obj.getInt("userID");
        String transNo = obj.getString("transactionNo");
        BigDecimal totalAmount = BigDecimal.valueOf(obj.getDouble("totalAmount"));
        String orderTime = obj.getString("orderTime");
        String status = obj.getString("status");
        String pickupCode = obj.optString("pickupCode", "N/A"); // 使用 optString 更安全

        JSONArray itemsArray = obj.getJSONArray("items");
        List<TransactionItemsDTO> itemList = new ArrayList<>();

        for (int j = 0; j < itemsArray.length(); j++) {
            JSONObject itemObj = itemsArray.getJSONObject(j);

            itemList.add(new TransactionItemsDTO(
                    itemObj.getString("beverageName"),
                    itemObj.getString("imagePath"),
                    itemObj.getString("description"),
                    itemObj.getInt("quantity"),
                    BigDecimal.valueOf(itemObj.getDouble("price"))
            ));
        }
        return new TransactionDTO(userID, transNo, totalAmount, orderTime, status, pickupCode,null,null,null, itemList);
    }
}
