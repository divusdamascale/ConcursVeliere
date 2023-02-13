
public class RezultatConcurs {

    String numeConcurs;
    String organizator;
    String locatie;
    String [] locuri;

    public RezultatConcurs(String numeConcurs, String organizator, String locatie, String[] locuri) {
        this.numeConcurs = numeConcurs;
        this.organizator = organizator;
        this.locatie = locatie;
        this.locuri = locuri;
    }

    public String getNumeConcurs() {
        return numeConcurs;
    }
    public String getOrganizator() {
        return organizator;
    }
    public String getLocatie() {
        return locatie;
    }
    public String[] getLocuri() {
        return locuri;
    }
    public void setNumeConcurs(String numeConcurs) {
        this.numeConcurs = numeConcurs;
    }
    public void setOrganizator(String organizator) {
        this.organizator = organizator;
    }
    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }
    public void setLocuri(String[] locuri) {
        this.locuri = locuri;
    }

    @Override
    public String toString()
    {
        int index=1;
        StringBuilder sb = new StringBuilder();
        sb.append(numeConcurs+" "+ organizator+" "+ locatie+"\n");
        sb.append("Rezultate: \n");
        for (String string : locuri) {
            if(string==null)
            {
                continue;
            }
            sb.append(index+"."+string+"\n");
            index++;
        }
        return sb.toString();
    }
    



    

    
    
}
