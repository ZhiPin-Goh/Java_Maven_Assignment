package ModelsDTO;

import java.util.List;

public class TransactionPreparingDTO {
    private String OrderTime;
    private String Status;
    private String PickupCode;
    private List<TransactionPreparingItemDTO> items;
    public TransactionPreparingDTO(String OrderTime, String Status, String PickupCode, List<TransactionPreparingItemDTO> items){
        this.OrderTime = OrderTime;
        this.Status = Status;
        this.PickupCode = PickupCode;
        this.items = items;
    }

    public String getOrderTime(){return OrderTime;}
    public String getStatus(){return Status;}
    public String getPickupCode(){return PickupCode;}
    public List<TransactionPreparingItemDTO> getItems(){return items;}
}
