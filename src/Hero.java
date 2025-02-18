public class Hero {

    private int id;
    private String held;
    private String antagonist;
    private String konfrontationstyp;
    private String ort;
    private String date;
    private double globalerEinfluss;


    public Hero(int id, String held, String antagonist, String konfrontationstyp, String ort, String date, double globalerEinfluss) {
        this.id = id;
        this.held = held;
        this.antagonist = antagonist;
        this.konfrontationstyp = konfrontationstyp;
        this.ort = ort;
        this.date = date;
        this.globalerEinfluss = globalerEinfluss;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeld() {
        return held;
    }

    public void setHeld(String held) {
        this.held = held;
    }

    public String getAntagonist() {
        return antagonist;
    }

    public void setAntagonist(String antagonist) {
        this.antagonist = antagonist;
    }

    public String getKonfrontationstyp() {
        return konfrontationstyp;
    }

    public void setKonfrontationstyp(String konfrontationstyp) {
        this.konfrontationstyp = konfrontationstyp;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getGlobalerEinfluss() {
        return globalerEinfluss;
    }

    public void setGlobalerEinfluss(double globalerEinfluss) {
        this.globalerEinfluss = globalerEinfluss;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", held='" + held + '\'' +
                ", antagonist='" + antagonist + '\'' +
                ", konfrontationstyp='" + konfrontationstyp + '\'' +
                ", ort='" + ort + '\'' +
                ", date='" + date + '\'' +
                ", globalerEinfluss=" + globalerEinfluss +
                '}';
    }
}
