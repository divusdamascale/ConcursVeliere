import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.invoke.VarHandle;

public class Persoana {
    private String nume;
    private int varsta;
    private String username;
    private String password;
    private String rol;
    private int nrConcurs;
    
    public int getNrConcurs() {
        return nrConcurs;
    }

    public void setNrConcurs(int nrConcurs) {
        this.nrConcurs = nrConcurs;
    }

    public String getNume() {
        return nume;
    }

    public int getVarsta() {
        return varsta;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRol() {
        return rol;
    }

    public Persoana(String nume, int varsta, String username, String password, String rol) {
        this.nume = nume;
        this.varsta = varsta;
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public Persoana(String nume, int varsta, String username, String password, String rol,int nr) {
        this.nume = nume;
        this.varsta = varsta;
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.nrConcurs = nr;
    }

    public Persoana(String username)
    {
        try {
            BufferedReader bf = new BufferedReader(new FileReader("logare.txt"));
            String cred;
            String [] creds;
    
            while(true)
            {
                cred = bf.readLine();
                creds = cred.split(" ");
                if(creds[0].equals(username))
                {
                    this.nume = creds[3];
                    this.username = creds[0];
                    this.password = creds[1];
                    this.varsta = Integer.parseInt(creds[4]);
                    this.rol = creds[2];
                    break;
                }
            }
        
            bf.close();
            
        } catch (Exception e) {
            return;
        }
       

        }


        @Override

        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            sb.append(nume).append(" ").append(varsta);
            return sb.toString();
        }

        public void setNume(String nume) {
            this.nume = nume;
        }

        public void setVarsta(int varsta) {
            this.varsta = varsta;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setRol(String rol) {
            this.rol = rol;
        }

       

    }




