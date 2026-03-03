package Services;

import Models.Cart;
import ModelsDTO.CartDTO;
import ModelsDTO.CartQuantityDTO;
import ModelsDTO.GetCartDTO;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
public class CartServices {
    private static final String BASE_URL = "http://localhost:5018/api/ManageCart/";

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

        // 调试打印
        //System.out.println("Status Code: " + responseCode);
        //System.out.println("Server Message: " + responseMsg);

        // 3. 决定结果
        if (responseCode >= 200 && responseCode < 300) {
            return responseMsg; // 成功，返回消息
        } else {
            throw new Exception(responseMsg); // 失败，抛出异常
        }
    }
    public String CreateCart(Cart cart) throws Exception{
        URL url = new URL(BASE_URL + "CreateCart");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        JSONObject obj = new JSONObject();
        obj.put("ID", 0);
        obj.put("UserID", cart.getUserID());
        obj.put("BeverageID", cart.getBeverageID());
        obj.put("Quantity", cart.getQuantity());
        obj.put("SugarLevelID", cart.getSugarLevelID());
        obj.put("BeverageSizeID", cart.getBeverageSizeID());
        obj.put("IceLevelID", cart.getIceLevelID());

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(obj.toString().getBytes("UTF-8"));
        outputStream.close();

        return getResponseFromConnection(connection);
    }

    public String RemoveCart(int id) throws Exception{
        URL url = new URL(BASE_URL + "RemoveCart/" + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        return getResponseFromConnection(connection);
    }

    public GetCartDTO GetUserCartList(int Id) throws Exception{
        URL url = new URL(BASE_URL + "GetCartList/" + Id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();
        if (responseCode != 200){
            System.out.println("Error: Server returned status code " + responseCode);
        }

        String jsonResponse = getResponseFromConnection(connection);
        JSONObject obj = new JSONObject(jsonResponse);

        double totalAmount = obj.optDouble("totalAmount", 0);
        List<CartDTO> cartList = new ArrayList<>();

        JSONArray array = obj.getJSONArray("cartItem");

        for(int i = 0; i < array.length(); i++){
            JSONObject cObj = array.getJSONObject(i);
            int id = cObj.getInt("cartID");
            String name = cObj.getString("beverageName");
            String image = cObj.optString("imagePath", "");
            String des = cObj.getString("description");
            int quantity = cObj.getInt("quantity");
            double unitprice = cObj.getDouble("unitPrice");
            double totalprice = cObj.getDouble("totalPrice");

            cartList.add(new CartDTO(id, name, image, des, quantity, BigDecimal.valueOf(unitprice), BigDecimal.valueOf(totalprice)));
        }
        return new GetCartDTO(BigDecimal.valueOf(totalAmount), cartList);
    }
    public String AddQuantity(CartQuantityDTO cartQuantity) throws Exception{
        URL url = new URL(BASE_URL + "AddQuantity");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        JSONObject obj = new JSONObject();
        obj.put("cartId", cartQuantity.getCartID());
        obj.put("userid", cartQuantity.getUserID());
        obj.put("quantity", cartQuantity.getReqQuantity());

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(obj.toString().getBytes("UTF-8"));
        outputStream.close();

        return getResponseFromConnection(connection);
    }

    public String MinusQuantity(CartQuantityDTO cartQuantity) throws Exception{
        URL url = new URL(BASE_URL + "MinusQuantity");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        JSONObject obj = new JSONObject();
        obj.put("cartId", cartQuantity.getCartID());
        obj.put("userId", cartQuantity.getUserID());
        obj.put("quantity", cartQuantity.getReqQuantity());

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(obj.toString().getBytes("UTF-8"));
        outputStream.close();

        return getResponseFromConnection(connection);
    }

}