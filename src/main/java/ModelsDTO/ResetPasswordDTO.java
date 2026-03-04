package ModelsDTO;

public class ResetPasswordDTO {
    private String Email;
    private String NewPassword;
    private String OTP;
    public ResetPasswordDTO(String Email, String NewPassword, String OTP){
        this.Email = Email;
        this.NewPassword = NewPassword;
        this.OTP = OTP;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getNewPassword() {
        return NewPassword;
    }

    public void setNewPassword(String newPassword) {
        NewPassword = newPassword;
    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }
}
