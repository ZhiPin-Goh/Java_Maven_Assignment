package Models;

public class IceLevels {
    private int ID;
    private String IceOption;
    public IceLevels(int ID,String IceOption){
        this.ID = ID;
        this.IceOption = IceOption;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getIceOption() {
        return IceOption;
    }

    public void setIceOption(String iceOption) {
        IceOption = iceOption;
    }
}
