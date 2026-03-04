package Models;

public class User {
    private int ID;
    public String UserName;
    private String Email;
    private String PhoneNumber;
    private  String Password;
    private  int OTP;
    private String Status;
    private String UserCode;
    public User(int ID, String UserName, String Email, String PhoneNumber, String Password, int OTP, String Status, String UserCode){
        this.ID =ID;
        this.UserName = UserName;
        this.Email = Email;
        this.PhoneNumber = PhoneNumber;
        this.Password = Password;
        this.OTP = OTP;
        this.Status = Status;
        this.UserCode = UserCode;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserName(){return UserName;}

    public void setUserName(String userName){UserName = userName;}

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getOTP() {
        return OTP;
    }

    public void setOTP(int OTP) {
        this.OTP = OTP;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getUserCode() {
        return UserCode;
    }

    public void setUserCode(String userCode) {
        UserCode = userCode;
    }

}
