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

    // 列表里装的是 DTO
    private List<TransactionItemsDTO> Items;

    // --- 修正构造函数参数类型 ---
    // 错误写法: List<TransactionItems> items
    // 正确写法: List<TransactionItemsDTO> items
    public TransactionDTO(int userID, String transactionNo, BigDecimal totalAmount, String orderTime, String status, String pickupCode, List<TransactionItemsDTO> items) {
        this.UserID = userID;
        this.TransactionNo = transactionNo;
        this.TotalAmount = totalAmount;
        this.OrderTime = orderTime;
        this.Status = status;
        this.PickupCode = pickupCode;
        this.Items = items;
    }

    public int getUserID() { return UserID; }
    public String getTransactionNo() { return TransactionNo; }
    public BigDecimal getTotalAmount() { return TotalAmount; }
    public String getOrderTime() { return OrderTime; }
    public String getStatus() { return Status; }
    public String getPickupCode() { return PickupCode; }
    public List<TransactionItemsDTO> getItems() { return Items; }
}
