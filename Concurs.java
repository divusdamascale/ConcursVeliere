import java.util.ArrayList;

public class Concurs {

    private String denumire;
    private String organizator;
    private String locatie;
    private ArrayList <Persoana> concurenti;
    private int vMin;
    private int vMax;

    public Concurs(String denumire, String organizator, String locatie, int vMin, int vMax) {
        this.denumire = denumire;
        this.organizator = organizator;
        this.locatie = locatie;
        this.vMin = vMin;
        this.vMax = vMax;
        concurenti = new ArrayList<Persoana>();
    }

    public Concurs(String denumire, String organizator, String locatie, int vMin, int vMax, ArrayList <Persoana> concurenti) {
        this.denumire = denumire;
        this.organizator = organizator;
        this.locatie = locatie;
        this.vMin = vMin;
        this.vMax = vMax;
        this.concurenti = concurenti;
    }



    
    public void addConcurenti(Persoana x){
        concurenti.add(x);
    }


    public int getvMin() {
        return vMin;
    }

    public void setvMin(int vMin) {
        this.vMin = vMin;
    }

    public int getvMax() {
        return vMax;
    }

    public void setvMax(int vMax) {
        this.vMax = vMax;
    }

    public Concurs(String denumire, String organizator, String locatie, ArrayList<Persoana> concurenti) {
        this.denumire = denumire;
        this.organizator = organizator;
        this.locatie = locatie;
        vMax = 100;
        vMin = 1;
        this.concurenti = concurenti;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public void setOrganizator(String organizator) {
        this.organizator = organizator;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public void setConcurenti(ArrayList<Persoana> concurenti) {
        this.concurenti = concurenti;
    }

    public ArrayList<Persoana> getConcurenti() {
        return concurenti;
    }

    public String getDenumire() {
        return denumire;
    }

    public String getOrganizator() {
        return organizator;
    }

    public String getLocatie() {
        return locatie;
    }

   

    @Override

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Denumire: ").append(denumire).append(" ");
        sb.append("Organizator: ").append(organizator).append(" ");
        sb.append("Locatie: ").append( locatie).append(" \n Concurenti:\n");
        sb.append(concurenti.toString());
        return sb.toString();

    }

    
    
}
