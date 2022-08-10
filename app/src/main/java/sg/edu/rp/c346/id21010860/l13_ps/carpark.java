package sg.edu.rp.c346.id21010860.l13_ps;

public class carpark {

    private int lots;
    private String type;
    private int available;
    private String carparknum;

    public String getCarparknum() {
        return carparknum;
    }

    public void setCarparknum(String carparknum) {
        this.carparknum = carparknum;
    }

    public String toString() {
        return "Carpark" +
                "\ncarpark number: " + carparknum +
                "\nlots: " + lots +
                "\ntype: " + type +
                "\navailable: " + available;
    }

    public int getLots() {
        return lots;
    }

    public void setLots(int lots) {
        this.lots = lots;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public carpark(int lots, String type, int available, String carparknum) {
        this.lots = lots;
        this.type = type;
        this.available = available;
        this.carparknum = carparknum;
    }
}
