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

public class DrinkOptionServices  {
    private static final String BASE_URL = "http://localhost:5018/api/ManageDrinkOption/";
    public List<Sizes> GetDrinkSize() throws Exception {
        URL url = new URL(BASE_URL + "GetSizes");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        reader.close();

        JSONArray array = new JSONArray(result.toString());
        List<Sizes> list = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            int id = obj.optInt("id", obj.optInt("ID", 0));
            String size = obj.optString("beverageSize", obj.optString("BeverageSize", ""));
            Double price = obj.optDouble("priceModifier", obj.optDouble("PriceModifier", 0.0));
            list.add(new Sizes(id, size, BigDecimal.valueOf(price)));
        }
        return list;
    }

    public List<IceLevels> GetDrinkIce() throws Exception {
        URL url = new URL(BASE_URL + "GetIceLevels");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        reader.close();

        JSONArray array = new JSONArray(result.toString());
        List<IceLevels> list = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            int id = obj.optInt("id", obj.optInt("ID", 0));
            String iceOption = obj.optString("iceOption", obj.optString("IceOption", ""));
            list.add(new IceLevels(id, iceOption));
        }
        return list;
    }

    public List<SugarLevels> GetDrinkSugar() throws Exception {
        URL url = new URL(BASE_URL + "GetSugarLevels");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        reader.close();

        JSONArray array = new JSONArray(result.toString());
        List<SugarLevels> list = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            int id = obj.optInt("id", obj.optInt("ID", 0));
            String sugarOption = obj.optString("sugarOption", obj.optString("SugarOption", ""));
            list.add(new SugarLevels(id, sugarOption));
        }
        return list;
    }
}
