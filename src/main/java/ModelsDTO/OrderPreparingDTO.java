package ModelsDTO;

import java.util.List;

public class OrderPreparingDTO {
    private int orderID;
    private String orderNo;
    private String pickupCode;
    private String CustomerName;
    private String orderTime;
    private String status;
    private List<OrderPreparingItemDTO> items;
    public OrderPreparingDTO(int orderID, String orderNo, String pickupCode, String CustomerName, String orderTime, String status,  List<OrderPreparingItemDTO> items){
        this.orderID = orderID;
        this.orderNo = orderNo;
        this.pickupCode = pickupCode;
        this.CustomerName = CustomerName;
        this.orderTime  = orderTime;
        this.status = status;
        this.items = items;
    }
    public int getOrderID() { return orderID; }
    public String getOrderNo() { return orderNo; }
    public String getPickupCode() { return pickupCode; }
    public String getCustomerName() { return CustomerName; }
    public String getOrderTime() { return orderTime; }
    public String getStatus() { return status; }
    public List<OrderPreparingItemDTO> getItems() { return items; }
}
