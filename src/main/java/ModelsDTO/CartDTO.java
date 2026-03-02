package ModelsDTO;

import java.math.BigDecimal;

public class CartDTO {
    private int CartID;
    private String BeverageName;
    private String ImagePath;
    private String Description;
    private int Quantity;
    private BigDecimal UnitPrice;
    private BigDecimal TotalPrice;
    public CartDTO(int CartID, String BeverageName, String ImagePath, String Description, int Quantity, BigDecimal UnitPrice, BigDecimal TotalPrice){
        this.CartID = CartID;
        this.BeverageName = BeverageName;
        this.ImagePath = ImagePath;
        this.Description = Description;
        this.Quantity = Quantity;
        this.UnitPrice = UnitPrice;
        this.TotalPrice = TotalPrice;
    }
    public int getCartID(){return CartID;}
    public String getBeverageName(){return BeverageName;}
    public String getImagePath(){return ImagePath;}
    public String getDescription(){return Description;}
    public int getQuantity(){return Quantity;}
    public BigDecimal getUnitPrice(){return UnitPrice;}
    public BigDecimal getTotalPrice(){return TotalPrice;}
}
