package Models;

public class Beverage {
    private int ID;
    private String BeverageName;
    private String BeverageDescription;
    private String BeverageCategory;
    private String BeverageImagePath;
    private String BeverageCode;
    private double Price;
    private boolean IsAvailable;
    private boolean HasHotOption;
    private boolean HasIceOption;
    public Beverage(int ID, String BeverageName,String BeverageDescription, String BeverageCategory, String BeverageImagePath, String BeverageCode, double Price, boolean IsAvailable, boolean HasHotOption, boolean HasIceOption){
        this.ID = ID;
        this.BeverageName = BeverageName;
        this.BeverageCategory = BeverageCategory;
        this.BeverageImagePath = BeverageImagePath;
        this.BeverageCode = BeverageCode;
        this.Price = Price;
        this.IsAvailable = IsAvailable;
        this.HasHotOption = HasHotOption;
        this.HasIceOption = HasIceOption;
        this.BeverageDescription = BeverageDescription;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


    public String getBeverageName() {
        return BeverageName;
    }

    public void setBeverageName(String beverageName) {
        BeverageName = beverageName;
    }

    public String getBeverageDescription() {
        return BeverageDescription;
    }

    public void setBeverageDescription(String beverageDescription) {
        BeverageDescription = beverageDescription;
    }

    public String getBeverageCategory() {
        return BeverageCategory;
    }

    public void setBeverageCategory(String beverageCategory) {
        BeverageCategory = beverageCategory;
    }

    public String getBeverageImagePath() {
        return BeverageImagePath;
    }

    public void setBeverageImagePath(String beverageImagePath) {
        BeverageImagePath = beverageImagePath;
    }

    public String getBeverageCode() {
        return BeverageCode;
    }

    public void setBeverageCode(String beverageCode) {
        BeverageCode = beverageCode;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public boolean isAvailable() {
        return IsAvailable;
    }

    public void setAvailable(boolean available) {
        IsAvailable = available;
    }

    public boolean isHasHotOption() {
        return HasHotOption;
    }

    public void setHasHotOption(boolean hasHotOption) {
        HasHotOption = hasHotOption;
    }

    public boolean isHasIceOption() {
        return HasIceOption;
    }

    public void setHasIceOption(boolean hasIceOption) {
        HasIceOption = hasIceOption;
    }
}
