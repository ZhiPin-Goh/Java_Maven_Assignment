package Services;

import Models.User;
import ModelsDTO.ChangePasswordDTO;
import ModelsDTO.EditUserDTO;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UserServices {
    private static final String BASE_URL = "http://localhost:5018/api/ManageUser/";


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
    public List<User> getAllUser() throws Exception {
        URL url = new URL(BASE_URL + "GetAllUsers");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        reader.close();

        JSONArray array = new JSONArray(result.toString());
        List<User> list = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);

            int id = obj.getInt("id");
            String username = obj.optString("userName", "N/A");
            String email = obj.optString("email", "N/A");
            String phonenumber = obj.optString("phoneNumber", "N/A");
            String password = obj.optString("password", "");
            int otp = obj.optInt("otp", 0);
            boolean status = obj.optBoolean("status", false);
            String usercode = obj.optString("userCode", "N/A");

            list.add(new User(id, email, username, phonenumber, password, otp, status, usercode));
        }
        return list;
    }

    public User SearchUserByID(int id) throws  Exception{
        URL url = new URL(BASE_URL + "GetUserByID/" + id);
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
        int userID = obj.getInt("id");
        String name = obj.getString("userName");
        String email = obj.getString("email");
        String phoneNumber = obj.optString("phoneNumber", "N/A");
        String password = obj.optString("password", "N/A");
        String userCode = obj.getString("userCode");
        boolean status = obj.getBoolean("status");
        return new User(userID,  name, email, phoneNumber, password, 12, status, userCode);
    }
    public String CreateUser(User user)throws Exception{
        URL url = new URL(BASE_URL + "CreateUser");
        HttpURLConnection connection =(HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        JSONObject obj = new JSONObject();
        obj.put("ID", 0);
        obj.put("UserName", user.getUserName());
        obj.put("Email", user.getEmail());
        obj.put("PhoneNumber", user.getPhoneNumber());
        obj.put("Password", user.getPassword());
        obj.put("Otp", user.getOTP());
        obj.put("Status", user.isStatus());
        obj.put("UserCode", user.getUserCode());


        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(obj.toString().getBytes("UTF-8"));
        outputStream.close();

        return getResponseFromConnection(connection);
    }

    public String EditUser(EditUserDTO user) throws Exception {
        URL url = new URL(BASE_URL + "UpdateUser");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        JSONObject obj = new JSONObject();
        obj.put("id", user.getUserID());
        obj.put("userName", user.getUserName());
        obj.put("email", user.getEmail());
        obj.put("phoneNumber", user.getPhoneNumber());

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(obj.toString().getBytes("UTF-8"));
        outputStream.close();

        return getResponseFromConnection(connection);
    }

    public int LoginUser(String email, String password) throws Exception {
        URL url = new URL(BASE_URL + "LoginUser");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        // 1. 组装发给后端的 JSON (对应 .NET 的 LoginDTO)
        JSONObject obj = new JSONObject();
        obj.put("Email", email);
        obj.put("Password", password);

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(obj.toString().getBytes("UTF-8"));
        outputStream.close();

        // 2. 调用通用的响应读取方法
        // 注意：如果 .NET 返回 401 Unauthorized，这里会直接抛出包含 "Invalid email or password." 的 Exception
        String jsonString = getResponseFromConnection(connection);

        // 3. 解析成功的 JSON
        JSONObject responseJson = new JSONObject(jsonString);

        // 4. 返回 userID 供 Web Session 使用
        if (responseJson.has("userID")) {
            return responseJson.getInt("userID");
        } else {
            throw new Exception("Login failed: User ID not found in response.");
        }
    }
    public String UnActiveUser(int id) throws Exception{
        URL url = new URL(BASE_URL + "UnActiveUser/" + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        return getResponseFromConnection(connection);
    }

    public String ActiveUser(int id) throws Exception {
        URL url = new URL(BASE_URL + "ActiveUser/" + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        return getResponseFromConnection(connection);
    }

    public String ChangePassword(ChangePasswordDTO password) throws Exception{
        URL url = new URL(BASE_URL + "ChangePassword");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        JSONObject obj = new JSONObject();
        obj.put("ID", password.getUserID());
        obj.put("CurrentPassword", password.getCurrentPassword());
        obj.put("NewPassword", password.getNewPassword());

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(obj.toString().getBytes("UTF-8"));
        outputStream.close();

        return getResponseFromConnection(connection);
    }
}