package Models;

import java.math.BigDecimal;

public class Sizes {
    private int ID;
    private String BeverageSize;
    private BigDecimal PriceModifier;
    public Sizes(int ID, String BeverageSize, BigDecimal PriceModifier){
        this.ID = ID;
        this.BeverageSize = BeverageSize;
        this.PriceModifier = PriceModifier;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getBeverageSize() {
        return BeverageSize;
    }

    public void setBeverageSize(String beverageSize) {
        BeverageSize = beverageSize;
    }

    public BigDecimal getPriceModifier() {
        return PriceModifier;
    }

    public void setPriceModifier(BigDecimal priceModifier) {
        PriceModifier = priceModifier;
    }
}
