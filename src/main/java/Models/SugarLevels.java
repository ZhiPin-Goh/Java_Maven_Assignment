package Models;

public class SugarLevels {
    private int ID;
    private String SugarOption;
    public SugarLevels(int ID, String SugarOption){
        this.ID = ID;
        this.SugarOption = SugarOption;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSugarOption() {
        return SugarOption;
    }

    public void setSugarOption(String sugarOption) {
        SugarOption = sugarOption;
    }
}
