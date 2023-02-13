import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class Formular extends JFrame {
    
    JButton accept,decline;
    public Formular(Persoana x)
    {
        JLabel l1,l2,l3,l4;
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        l1= new JLabel("Salut, " + x.getNume() + "!");
        l2= new JLabel("Acesta este formularul cu datele tale de inscriere la urmatorul concurs:");
        l3= new JLabel("Nume: "+ x.getNume());
        l4= new JLabel("Varsta: "+ x.getVarsta());
        JPanel p1= new JPanel(new GridLayout(4,1,5,5));
        p1.add(l1);
        p1.add(l2);
        p1.add(l3);
        p1.add(l4);
        this.add(p1);
        accept = new JButton("Doresc sa ma inscriu!");
        decline = new JButton("Renunta!");
        JPanel p2 = new JPanel(new FlowLayout());
        p2.add(accept);
        p2.add(decline);
        this.add(p2,BorderLayout.SOUTH);
        
        accept.addActionListener(e -> {
            dispose();
            try {
                BufferedWriter buf = new BufferedWriter(new FileWriter("cereri.txt",true));
                StringBuilder sb = new StringBuilder();
                sb.append(x.getUsername()).append(" ").append(x.getNume()).append(" ").append(x.getVarsta()).append(" ");
                buf.write(sb.toString());
                buf.newLine();
                buf.close();
            } catch (Exception e1) {
                return;
            }
            
            JOptionPane.showMessageDialog(null, "Cererea dumneavoastra a fostr trimisa!\n In scurt timp veti primi raspunsul cererii in aplicatie\n Multumim!");
        });

        decline.addActionListener(e -> 
        {
            dispose();
        });
    }
    
}
