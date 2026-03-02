package Models;

public class Cart {
    private int Id;
    private int UserID;
    private int BeverageID;
    private int Quantity;
    private int SugarLevelID;
    private int BeverageSizeID;
    private int IceLevelID;
    public Cart(int Id, int UserID, int BeverageID, int Quantity, int SugarLevelID, int BeverageSizeID, int IceLevelID){
        this.Id = Id;
        this.UserID = UserID;
        this.BeverageID = BeverageID;
        this.Quantity = Quantity;
        this.SugarLevelID = SugarLevelID;
        this.BeverageSizeID = BeverageSizeID;
        this.IceLevelID = IceLevelID;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
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

    public int getSugarLevelID() {
        return SugarLevelID;
    }

    public void setSugarLevelID(int sugarLevelID) {
        SugarLevelID = sugarLevelID;
    }

    public int getBeverageSizeID() {
        return BeverageSizeID;
    }

    public void setBeverageSizeID(int beverageSizeID) {
        BeverageSizeID = beverageSizeID;
    }

    public int getIceLevelID() {
        return IceLevelID;
    }

    public void setIceLevelID(int iceLevelID) {
        IceLevelID = iceLevelID;
    }
}
