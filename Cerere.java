public class Cerere {

    private String username;
    private String nume;
    private int varsta;

    public Cerere(String username, String nume, int varsta) {
        this.username = username;
        this.nume = nume;
        this.varsta = varsta;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    @Override

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(username).append(" ").append(nume).append(" ").append(varsta);
        return sb.toString();
    }

}
