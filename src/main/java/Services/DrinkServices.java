package Services;

import Models.IceLevels;
import Models.Sizes;
import Models.SugarLevels;
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

public class DrinkServices {
    private static final String BASE_URL = "http://localhost:5018/api/ManageDrinkOption/";
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
        System.out.println("Status Code: " + responseCode);
        System.out.println("Server Message: " + responseMsg);

        // 3. 决定结果
        if (responseCode >= 200 && responseCode < 300) {
            return responseMsg; // 成功，返回消息
        } else {
            throw new Exception(responseMsg); // 失败，抛出异常
        }
    }
    public List<Sizes> getAllSize() throws Exception{
        URL url = new URL(BASE_URL + "GetSizes");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null){
            result.append(line);
        }
        reader.close();
        JSONArray array = new JSONArray(result.toString());
        List<Sizes> list = new ArrayList<>();

        for(int i = 0;i < array.length();i++){
            JSONObject obj = array.getJSONObject(i);
            int id = obj.getInt("id");
            String bSize = obj.getString("beverageSize");
            double price = obj.getDouble("priceModifier");
            list.add(new Sizes(
                    id,
                    bSize,
                    BigDecimal.valueOf(price)
                    ));
        }
        return list;
    }
    public List<IceLevels> getAllIceLevel() throws  Exception{
        URL url = new URL(BASE_URL + "GetIceLevels");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null){
            result.append(line);
        }
        reader.close();

        JSONArray array = new JSONArray(result.toString());
        List<IceLevels> list = new ArrayList<>();

        for (int i =0; i< array.length(); i++){
            JSONObject obj = array.getJSONObject(i);
            int id = obj.getInt("id");
            String iceopt = obj.getString("iceOption");
            list.add(new IceLevels(id, iceopt));
        }
        return list;
    }
    public List<SugarLevels> getAllSugarLevel() throws Exception{
        URL url = new URL(BASE_URL + "GetSugarLevels");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder result = new StringBuilder();
        String line;
         while ((line = reader.readLine()) !=null){
             result.append(line);
         }
         reader.close();
         JSONArray array = new JSONArray(result.toString());
         List<SugarLevels> list = new ArrayList<>();

         for (int i=0;i<array.length();i++){
             JSONObject obj = array.getJSONObject(i);

             int id = obj.getInt("id");
             String sOpt = obj.getString("sugarOption");

             list.add(new SugarLevels(id, sOpt));
         }
         return list;
    }
}
