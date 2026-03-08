package Services;

import Models.Beverage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.util.*;

public class BeverageServices {
    private static final String BASE_URL = "http://localhost:5018/api/ManageBeverages/";

    public List<Beverage> getAllBeverage() throws Exception {
        URL url = new URL(BASE_URL + "GetAllBeverages");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            System.out.println("Error: Server returned status code " + responseCode);
            return new ArrayList<>();
        }
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        reader.close();

        JSONArray array = new JSONArray(result.toString());
        List<Beverage> list = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            int id = obj.getInt("id");
            String beveragename = obj.getString("beverageName");
            String beveragedescription = obj.optString("beverageDescription", "N/A");
            String beveragecategory = obj.optString("beverageCategory", "N/A");
            String beverageimagepath = obj.optString("beverageImagePath", "N/A");
            String beveragecode = obj.optString("beverageCode", "N/A");
            double price = obj.optDouble("price", 0);
            boolean isavailable = obj.optBoolean("isAvailable", false);
            boolean hashotoption = obj.optBoolean("hasHotOption", false);
            boolean hasiceoption = obj.optBoolean("hasIceOption", false);
            list.add(new Beverage(id, beveragename, beveragedescription, beveragecategory, beverageimagepath,
                    beveragecode, price, isavailable, hashotoption, hasiceoption));
        }
        return list;
    }

    public Beverage GetBeverageByID(int Id) throws Exception {
        URL url = new URL(BASE_URL + "GetBeverageByID/" + Id);
        // URL url = new URL(BASE_URL + Id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();
        if (responseCode == 404) {
            return null;
        }
        if (responseCode != 200) {
            throw new Exception("Server Error: " + responseCode);
        }
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        reader.close();

        JSONObject obj = new JSONObject(result.toString());
        int id = obj.getInt("id");
        String beveragename = obj.getString("beverageName");
        String beveragedescription = obj.optString("beverageDescription", "N/A");
        String beveragecategory = obj.optString("beverageCategory", "N/A");
        String beverageimage = obj.optString("beverageImagePath", "N/A");
        String beveragecode = obj.optString("beverageCode", "N/A");
        double price = obj.optDouble("price", 0);
        boolean isavailable = obj.optBoolean("isAvailable", false);
        boolean hashotoption = obj.optBoolean("hasHotOption", false);
        boolean hasiceoption = obj.optBoolean("hasIceOption", false);
        return new Beverage(id, beveragename, beveragedescription, beveragecategory, beverageimage, beveragecode, price,
                isavailable, hashotoption, hasiceoption);
    }

    public Beverage GetBeverageByCode(String code) throws Exception {
        URL url = new URL(BASE_URL + "GetBeverageByCode/%23" + code);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();

        if (responseCode == 404) {
            return null;
        }
        if (responseCode != 200) {
            throw new Exception("Server Error: " + responseCode);
        }

        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        reader.close();

        JSONObject obj = new JSONObject(result.toString());
        int id = obj.getInt("id");
        String beveragename = obj.getString("beverageName");
        String beveragedescription = obj.optString("beverageDescription", "N/A");
        String beveragecategory = obj.optString("beverageCategory", "N/A");
        String beverageimage = obj.optString("beverageImagePath", "N/A");
        String beveragecode = obj.optString("beverageCode", "N/A");
        double price = obj.optDouble("price", 0);
        boolean isavailable = obj.optBoolean("isAvailable", false);
        boolean hashotoption = obj.optBoolean("hasHotOption", false);
        boolean hasiceoption = obj.optBoolean("hasIceOption", false);
        return new Beverage(id, beveragename, beveragedescription, beveragecategory, beverageimage, beveragecode, price,
                isavailable, hashotoption, hasiceoption);
    }

    public String CreateBeverage(Beverage beverage) throws Exception {
        URL url = new URL(BASE_URL + "CreateBeverage");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        JSONObject obj = new JSONObject();
        obj.put("ID", 0);
        obj.put("beveragename", beverage.getBeverageName());
        obj.put("beveragedescription", beverage.getBeverageDescription());
        obj.put("beveragecategory", beverage.getBeverageCategory());
        obj.put("price", beverage.getPrice());
        obj.put("hashotoption", beverage.isHasHotOption());
        obj.put("hasiceoption", beverage.isHasIceOption());
        obj.put("beverageimagepath", beverage.getBeverageImagePath());

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(obj.toString().getBytes("UTF-8"));
        outputStream.close();

        int responseCode = connection.getResponseCode();
        InputStream inputStream;
        if (responseCode >= 200 && responseCode < 300) {
            inputStream = connection.getInputStream();
        } else {
            inputStream = connection.getErrorStream();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        String responseMsg = response.toString();
        System.out.println("Status Code: " + responseCode);
        System.out.println("Server Message: " + responseMsg);
        if (responseCode == 200) {
            return responseMsg;
        } else {
            throw new Exception(responseMsg);
        }
    }

    public String UpdateBeverage(Beverage beverage) throws Exception {
        URL url = new URL(BASE_URL + "UpdateBeverage");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        JSONObject obj = new JSONObject();
        obj.put("ID", beverage.getID());
        obj.put("beveragename", beverage.getBeverageName());
        obj.put("beveragedescription", beverage.getBeverageDescription());
        obj.put("beveragecategory", beverage.getBeverageCategory());
        obj.put("price", beverage.getPrice());
        obj.put("hashotoption", beverage.isHasHotOption());
        obj.put("hasiceoption", beverage.isHasIceOption());

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(obj.toString().getBytes("UTF-8"));
        outputStream.close();

        int responseCode = connection.getResponseCode();
        InputStream inputStream;
        if (responseCode >= 200 && responseCode < 300) {
            inputStream = connection.getInputStream();
        } else {
            inputStream = connection.getErrorStream();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        String responseMsg = response.toString();
        System.out.println("Status Code: " + responseCode);
        System.out.println("Server Message: " + responseMsg);
        if (responseCode == 200) {
            return responseMsg;
        } else {
            throw new Exception(responseMsg);
        }
    }

    public String DeleteBeverage(int id) throws Exception {
        URL url = new URL(BASE_URL + "DeleteBeverage/" + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        int responseCode = connection.getResponseCode();
        InputStream inputStream;
        if (responseCode >= 200 && responseCode < 300) {
            inputStream = connection.getInputStream();
        } else {
            inputStream = connection.getErrorStream();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        String responseMsg = response.toString();
        System.out.println("Status Code: " + responseCode);
        System.out.println("Server Message: " + responseMsg);
        if (responseCode == 200) {
            return responseMsg;
        } else {
            throw new Exception(responseMsg);
        }
    }

    public String BeverageUnAvailable(int id) throws Exception {
        URL url = new URL(BASE_URL + "BeverageUnAvailable/" + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        int responseCode = connection.getResponseCode();
        InputStream inputStream;
        if (responseCode >= 200 && responseCode < 300) {
            inputStream = connection.getInputStream();
        } else {
            inputStream = connection.getErrorStream();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        String responseMsg = response.toString();
        System.out.println("Status Code: " + responseCode);
        System.out.println("Server Message: " + responseMsg);
        if (responseCode == 200) {
            return responseMsg;
        } else {
            throw new Exception(responseMsg);
        }
    }

    public String BeverageAvailable(int id) throws Exception {
        URL url = new URL(BASE_URL + "BeverageAvailable/" + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        int responseCode = connection.getResponseCode();
        InputStream inputStream;
        if (responseCode >= 200 && responseCode < 300) {
            inputStream = connection.getInputStream();
        } else {
            inputStream = connection.getErrorStream();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        String responseMsg = response.toString();
        System.out.println("Status Code: " + responseCode);
        System.out.println("Server Message: " + responseMsg);
        if (responseCode == 200) {
            return responseMsg;
        } else {
            throw new Exception(responseMsg);
        }
    }

    public List<Beverage> SearchBeverage(String keyword) throws Exception {
        String encodedKeyword = java.net.URLEncoder.encode(keyword, "UTF-8");
        URL url = new URL(BASE_URL + "SearchBeverage?keyword=" + encodedKeyword);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            return new ArrayList<>();
        }
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        reader.close();

        JSONArray array = new JSONArray(result.toString());
        List<Beverage> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            int id = obj.getInt("id");
            String beveragename = obj.getString("beverageName");
            String beveragedescription = obj.optString("beverageDescription", "N/A");
            String beveragecategory = obj.optString("beverageCategory", "N/A");
            String beverageimagepath = obj.optString("beverageImagePath", "N/A");
            String beveragecode = obj.optString("beverageCode", "N/A");
            double price = obj.optDouble("price", 0);
            boolean isavailable = obj.optBoolean("isAvailable", true); // Default to true because the .NET API SearchBeverage omits it but only returns available items
            boolean hashotoption = obj.optBoolean("hasHotOption", false);
            boolean hasiceoption = obj.optBoolean("hasIceOption", false);
            list.add(new Beverage(id, beveragename, beveragedescription, beveragecategory, beverageimagepath,
                    beveragecode, price, isavailable, hashotoption, hasiceoption));
        }
        return list;
    }
}
