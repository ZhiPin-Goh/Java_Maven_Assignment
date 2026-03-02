package ModelsDTO;

public class OrderPreparingItemDTO {
    private String beverageName;
    private int quantity;
    private String description;
    public OrderPreparingItemDTO(String beverageName, int quantity, String description){
        this.beverageName = beverageName;
        this.quantity = quantity;
        this.description = description;
    }
    public String getBeverageName(){return beverageName;}
    public int getQuantity(){return quantity;}
    public String getDescription(){return description;}
}
