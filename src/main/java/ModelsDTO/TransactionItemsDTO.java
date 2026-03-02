package ModelsDTO;

import java.math.BigDecimal;

public class TransactionItemsDTO {
    private String BeverageName;
    private String ImagePath;
    private String Description;
    private int Quantity;
    private BigDecimal Price;

    public TransactionItemsDTO(String beverageName, String imagePath, String description, int quantity, BigDecimal price) {
        this.BeverageName = beverageName;
        this.ImagePath = imagePath;
        this.Description = description;
        this.Quantity = quantity;
        this.Price = price;
    }

    public String getBeverageName() {
        return BeverageName;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public String getDescription() {
        return Description;
    }

    public int getQuantity() {
        return Quantity;
    }

    public BigDecimal getPrice() {
        return Price;
    }
}

