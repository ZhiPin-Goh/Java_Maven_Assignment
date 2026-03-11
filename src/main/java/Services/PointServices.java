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
    private static final String BASE_URL = "http://localhost:5018/api/ManagePoints/";
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
        StringBuilder response = new StringBuilder();
        if (inputStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
        } else {
            response.append("Error " + responseCode + ": No response from server");
        }

        String responseMsg = response.toString();

        // 3. 决定结果
        if (responseCode >= 200 && responseCode < 300) {
            return responseMsg; // 成功，返回消息
        } else {
            throw new Exception(responseMsg); // 失败，抛出异常
        }
    }
    public String DailyCheckIn(int id) throws Exception{
        URL url = new URL(BASE_URL + "DailyCheckIn?id="+id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");

        return getResponseFromConnection(connection);
    }

    public List<PointLogs> GetPointLogs(int userID) throws Exception{
        URL url = new URL(BASE_URL + "GetPointLogs/"+userID);
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

            // Fetch from exact JSON key names
            BigDecimal amount = BigDecimal.valueOf(obj.optDouble("points", 0.0));
            if (!obj.has("points") && obj.has("Points")) {
                amount = BigDecimal.valueOf(obj.optDouble("Points", 0.0));
            }
            
            String description = obj.optString("description", "N/A");
            if (!obj.has("description") && obj.has("Description")) {
                 description = obj.optString("Description", "N/A");
            }
            
            String createat = obj.optString("date", "N/A");
            if (!obj.has("date") && obj.has("Date")) {
                 createat = obj.optString("Date", "N/A");
            }

            pointLogsList.add(new PointLogs(0, userID, amount, description, createat));
        }
        return pointLogsList;
    }
    public BigDecimal GetPoints(int id) throws Exception{
        URL url = new URL(BASE_URL + "GetPoints/"+id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();
        if (responseCode !=200){
            throw new Exception("Server Error: "+ responseCode);
        }

        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        JSONObject obj = new JSONObject(result.toString());

        BigDecimal pointBalance = BigDecimal.valueOf(obj.getDouble("pointBalance"));
        return pointBalance;
    }

}
