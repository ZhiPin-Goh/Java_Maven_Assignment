package ModelsDTO;

import java.math.BigDecimal;

public class TransactionPreparingItemDTO {
    private String BeverageName;
    private String ImagePath;
    private String Description;
    private int Quantity;
    private BigDecimal Price;

    public TransactionPreparingItemDTO(String BeverageName, String ImagePath, String Description, int Quantity, BigDecimal Price){
        this.BeverageName = BeverageName;
        this.ImagePath = ImagePath;
        this.Description = Description;
        this.Quantity = Quantity;
        this.Price = Price;
    }
    public String getBeverageName(){return BeverageName;}
    public String getImagePath(){return ImagePath;}
    public String getDescription(){return Description;}
    public int getQuantity(){return Quantity;}
    public BigDecimal getPrice(){return Price;}
}
