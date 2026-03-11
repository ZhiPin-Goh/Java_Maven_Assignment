package ModelsDTO;

import java.math.BigDecimal;
import java.util.List;
public class TransactionDTO {
    private int UserID;
    private String TransactionNo;
    private BigDecimal TotalAmount;
    private String OrderTime;
    private String Status;
    private String PickupCode;
    private BigDecimal PointsUsed;
    private String DiscountAmount;
    private BigDecimal PointsEarned;
    private List<TransactionItemsDTO> Items;

    public TransactionDTO(int userID, String transactionNo, BigDecimal totalAmount, String orderTime, String status, String pickupCode,
                          BigDecimal PointsUsed, String DiscountAmount, BigDecimal PointsEarned ,List<TransactionItemsDTO> items) {
        this.UserID = userID;
        this.TransactionNo = transactionNo;
        this.TotalAmount = totalAmount;
        this.OrderTime = orderTime;
        this.Status = status;
        this.PickupCode = pickupCode;
        this.PointsUsed = PointsUsed;
        this.DiscountAmount = DiscountAmount;
        this.PointsEarned = PointsEarned;
        this.Items = items;
    }

    public int getUserID() { return UserID; }
    public String getTransactionNo() { return TransactionNo; }
    public BigDecimal getTotalAmount() { return TotalAmount; }
    public String getOrderTime() { return OrderTime; }
    public String getStatus() { return Status; }
    public String getPickupCode() { return PickupCode; }
    public List<TransactionItemsDTO> getItems() { return Items; }

    public BigDecimal getPointsUsed() {
        return PointsUsed;
    }

    public String getDiscountAmount() {
        return DiscountAmount;
    }

    public BigDecimal getPointsEarned() {
        return PointsEarned;
    }
}
