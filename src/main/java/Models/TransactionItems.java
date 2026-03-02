package Models;

import java.math.BigDecimal;

public class TransactionItems {
    private int ID;
    private int TransactionID;
    private int BeverageID;
    private int Quantity;
    private int SizeID;
    private int SugarLevelID;
    private int IceLevelID;
    private BigDecimal PriceAtPurchase;
    public TransactionItems(int ID, int TransactionID, int BeverageID, int Quantity, int SizeID, int SugarLevelID, int IceLevelID, BigDecimal PriceAtPurchase){
        this.ID = ID;
        this.TransactionID = TransactionID;
        this.BeverageID = BeverageID;
        this.Quantity = Quantity;
        this.SizeID = SizeID;
        this.SugarLevelID = SugarLevelID;
        this.IceLevelID = IceLevelID;
        this.PriceAtPurchase = PriceAtPurchase;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getTransactionID() {
        return TransactionID;
    }

    public void setTransactionID(int transactionID) {
        TransactionID = transactionID;
    }

    public int getBeverageID() {
        return BeverageID;
    }

    public void setBeverageID(int beverageID) {
        BeverageID = beverageID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getSizeID() {
        return SizeID;
    }

    public void setSizeID(int sizeID) {
        SizeID = sizeID;
    }

    public int getSugarLevelID() {
        return SugarLevelID;
    }

    public void setSugarLevelID(int sugarLevelID) {
        SugarLevelID = sugarLevelID;
    }

    public int getIceLevelID() {
        return IceLevelID;
    }

    public void setIceLevelID(int iceLevelID) {
        IceLevelID = iceLevelID;
    }

    public BigDecimal getPriceAtPurchase() {
        return PriceAtPurchase;
    }

    public void setPriceAtPurchase(BigDecimal priceAtPurchase) {
        PriceAtPurchase = priceAtPurchase;
    }
}
