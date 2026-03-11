package Models;

import java.math.BigDecimal;

public class PointLogs {
    private int ID;
    private int UserID;
    private BigDecimal Amount;
    private String Description;
    public String CreateAt;
    public PointLogs(int ID, int UserID, BigDecimal Amount, String Description, String CreateAt){
        this.ID=ID;
        this.UserID=UserID;
        this.Amount=Amount;
        this.Description=Description;
        this.CreateAt=CreateAt;
    }

    public int getID() {
        return ID;
    }

    public int getUserID() {
        return UserID;
    }

    public BigDecimal getAmount() {
        return Amount;
    }

    public String getDescription() {
        return Description;
    }

    public String getCreateAt() {
        return CreateAt;
    }
}
