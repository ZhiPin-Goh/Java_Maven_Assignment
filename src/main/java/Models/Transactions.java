package Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transactions {
    private int ID;
    private String TransactionNo;
    private int UserID;
    private BigDecimal TotalAmount;
    private LocalDateTime OrderTime;
    private LocalDateTime PickupTime;
    private String PickupCode;
    private String Status;
    public Transactions(int ID, String TransactionNo, int UserID, BigDecimal TotalAmount,LocalDateTime OrderTime, LocalDateTime PickupTime, String PickupCode, String Status){
        this.ID = ID;
        this.TransactionNo = TransactionNo;
        this.UserID = UserID;
        this.TotalAmount = TotalAmount;
        this.OrderTime = OrderTime;
        this.PickupTime = PickupTime;
        this.PickupCode = PickupCode;
        this.Status = Status;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTransactionNo() {
        return TransactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        TransactionNo = transactionNo;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public BigDecimal getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        TotalAmount = totalAmount;
    }

    public LocalDateTime getOrderTime() {
        return OrderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        OrderTime = orderTime;
    }

    public LocalDateTime getPickupTime() {
        return PickupTime;
    }

    public void setPickupTime(LocalDateTime pickupTime) {
        PickupTime = pickupTime;
    }

    public String getPickupCode() {
        return PickupCode;
    }

    public void setPickupCode(String pickupCode) {
        PickupCode = pickupCode;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
