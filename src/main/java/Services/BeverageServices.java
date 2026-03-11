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
    public List<Beverage> BestSellerBeverage() throws Exception {
        URL url = new URL(BASE_URL + "BeverageBestSeller");
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
            int id = obj.optInt("beverageID", obj.optInt("id"));
            
            try {
                Beverage fullBev = GetBeverageByID(id);
                if (fullBev != null) {
                    list.add(fullBev);
                } else {
                    String beveragename = obj.optString("beverageName", "Unknown");
                    String beverageimagepath = obj.optString("beverageImagePath", "N/A");
                    list.add(new Beverage(id, beveragename, "N/A", "N/A", beverageimagepath, "N/A", 0.0, true, false, false));
                }
            } catch (Exception e) {
                String beveragename = obj.optString("beverageName", "Unknown");
                String beverageimagepath = obj.optString("beverageImagePath", "N/A");
                list.add(new Beverage(id, beveragename, "N/A", "N/A", beverageimagepath, "N/A", 0.0, true, false, false));
            }
        }
        return list;
    }
}
