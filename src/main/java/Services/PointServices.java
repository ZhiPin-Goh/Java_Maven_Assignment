package Services;

import Models.PointLogs;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PointServices {
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

        // 3. 决定结果
        if (responseCode >= 200 && responseCode < 300) {
            return responseMsg; // 成功，返回消息
        } else {
            throw new Exception(responseMsg); // 失败，抛出异常
        }
    }
    public String DailyCheckIn(int id) throws Exception{
        URL url = new URL(BASE_URL + "DailyCheckIn/"+id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");

        return getResponseFromConnection(connection);
    }

    public List<PointLogs> GetPointLogs(int userID) throws Exception{
        URL url = new URL(BASE_URL + "DailyCheckIn/"+userID);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);

        try{
            if (connection.getResponseCode()==404){
                return new ArrayList<>();
            }
        } catch (IOException ex) {
            return new ArrayList<>();
        }

        String jsonResponse = getResponseFromConnection(connection);
        JSONArray jsonArray = new JSONArray(jsonResponse);
        List<PointLogs>pointLogsList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            int id = obj.getInt("id");
            int userid = obj.getInt("userID");
            BigDecimal amount = obj.getBigDecimal("amount");
            String description = obj.getString("description");
            String createat = obj.getString("createAt");
            pointLogsList.add(new PointLogs(id,userid,amount,description,createat));
        }
        return pointLogsList;
    }

}
