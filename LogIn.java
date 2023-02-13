import java.awt.BorderLayout;
import java.awt.Color;

import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;
import javax.swing.*;

public class LogIn extends JFrame{
    private JTextField t1;
    private JPasswordField p1;
    private JButton b1,b2,b3;

    public LogIn()
    {   
        this.setTitle("Log In");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(450,125);
        JPanel panel1 = new JPanel();
        JLabel l1 = new JLabel("User:");
        t1 = new JTextField(10);
        panel1.add(l1);
        panel1.add(t1);
        panel1.setForeground(Color.BLUE);

        JPanel panel2 = new JPanel();
        JLabel l3 = new JLabel("Password:");
        b1 = new JButton("Log In");
        b2 = new JButton("Sign In");
        b3 = new JButton("Forgot my password");
        p1 = new JPasswordField(10);
        panel2.add(l3);
        panel2.add(p1);
        JPanel panel3 = new JPanel();

        panel3.add(b1,BorderLayout.SOUTH);
        panel3.add(b2,BorderLayout.SOUTH);
        panel3.add(b3,BorderLayout.SOUTH);
        panel1.add(panel2,BorderLayout.SOUTH);
        // this.add(panel2,BorderLayout.SOUTH);
        this.add(panel1,BorderLayout.NORTH);
        this.add(panel3,BorderLayout.SOUTH);

        b1.addActionListener(e -> 
        {
            
            // JOptionPane.showMessageDialog(null,t1.getText()+" "+p1.getText());
            logare(t1.getText(), p1.getText());
        });

        b2.addActionListener(e -> 
        {
            dispose();
            new SignIn().setVisible(true);
        });
        
    }

    public void logare (String username, String Password)
    {
        try {
            if(username.equals("")||Password.equals(""))
        {
            System.out.println("eroare");
            return;
        }
        BufferedReader buf  = new BufferedReader(new FileReader("logare.txt"));
        String cred;
        String [] creds;
        boolean x =false;
        while(true)
        {

            cred = buf.readLine();
            if(cred == null)
            {
                break;
            }
            creds = cred.split(" ");
            // System.out.println(creds[0]+" "+ creds[1]);
            if (creds[0].equals(username) && creds[1].equals(Password)) {
                if(creds[2].equals("v"))
                {
                    dispose();
                    
                    new VizFrame(new Persoana(creds[3], Integer.parseInt(creds[4]), creds[0], creds[1], "v")).setVisible(true);
                    // JOptionPane.showMessageDialog(null,"vizitator");
                }else if(creds[2].equals("s"))
                {
                    dispose();
                    new Sportiv(new Persoana(creds[3], Integer.parseInt(creds[4]), creds[0], creds[1], "s",Integer.parseInt(creds[5]))).setVisible(true);
                    // JOptionPane.showMessageDialog(null,"sportiv");
                }else if(creds[2].equals("a"))
                {
                    dispose();
                    new AntrenorFrame(new Persoana(creds[3], Integer.parseInt(creds[4]), creds[0], creds[1], "a")).setVisible(true);
                    // new AntFrame(creds[3], "Antrenor").setVisible(true);
                    // JOptionPane.showMessageDialog(null,"antrenor");
                }
                else if(creds[2].equals("admin"))
                {   
                    dispose();
                    new AdminFrame(new Persoana(creds[3], Integer.parseInt(creds[4]), creds[0], creds[1], "admin")).setVisible(true);
                }else if(creds[2].equals("sc"))
                {
                    dispose();
                    new Secretar(new Persoana(creds[3], Integer.parseInt(creds[4]), creds[0], creds[1], "sc")).setVisible(true);

                }
                x = true;
                break;
            }
        }
        buf.close();
        if(x == false)
        {
            JOptionPane.showMessageDialog(null,"Parola sau username gresit");
        }

        } catch (Exception e) {
            return;
        }
        
        
    }

    public static void main(String[] args) {
        new LogIn().setVisible(true);
    }

}