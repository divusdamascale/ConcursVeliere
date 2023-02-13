import javax.swing.*;
import java.awt.*;
public class Secretar extends JFrame{

    public Secretar(Persoana x){
        super("Secretar de cursa");
        setSize(400,500);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

       
        JPanel p1=new JPanel();
        p1.setLayout(new GridLayout(7,1,10,10));
        JLabel l1=new JLabel("Salut, "+x.getNume()+"!");
        JButton b1=new JButton("Vizualizare concursuri");
        JButton rezultate = new JButton("Adaugare Rezultat");
        JButton vizRezultate = new JButton("Vizualizare Rezultate");
        JButton bc=new JButton("Cereri");
        JButton b3=new JButton("Informatii");
        JButton b4=new JButton("Iesire din cont");
        p1.add(l1);
        p1.add(b1);
        p1.add(rezultate);
        p1.add(vizRezultate);
        p1.add(bc);
        p1.add(b3);
        p1.add(b4);
        this.add(p1);

        rezultate.addActionListener(e -> 
        {
            new AdaugareRez().setVisible(true);
        });

        vizRezultate.addActionListener(e -> 
        {
            new VizualizareRez().setVisible(true);
        });

        b1.addActionListener(e -> {
            new Concursuri(x).setVisible(true);
        } );

    
        bc.addActionListener(e-> 
        {
            new Cereri().setVisible(true);
        });

        b3.addActionListener(e -> {
            new Informatii(x).setVisible(true);
        });

        b4.addActionListener(e -> 
        {
            dispose();
            new LogIn().setVisible(true);
        });

    }

    // public static void main(String[] args){
    //     new Secretar(new Persoana("Gica",20,"Gicaxxx","asd","sc")).setVisible(true);
    // }
    
}
