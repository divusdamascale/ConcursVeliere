import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class Informatii extends JFrame {
  private JButton b1;

  public Informatii(Persoana x) {
    super("Informatii personale");
    setSize(300, 200);
    setLocationRelativeTo(null);
    JLabel t1, t2, t3, t4;
    JPanel p1 = new JPanel();
    t1 = new JLabel("Username: " + x.getUsername());
    t2 = new JLabel("Nume: " + x.getNume());
    t3 = new JLabel("Varsta: " + x.getVarsta());
    // t4 = new JLabel("Rol: "+ x.getRol());

    if (x.getRol().equals("s")) {
      t4 = new JLabel("Rol: Sportiv");
      p1.add(t4);
    } else if (x.getRol().equals("admin")) {
      t4 = new JLabel("Rol: Administrator");
      p1.add(t4);
    } else if (x.getRol().equals("sc")) {
      t4 = new JLabel("Rol: Secretar de cursa");
      p1.add(t4);
    }

    p1.setLayout(new GridLayout(4, 1, 10, 10));
    p1.add(t1);
    p1.add(t2);
    p1.add(t3);
    // p1.add(t4);

    // if(x.getRol().equals("s")){
    // t4=new JLabel("Rol: Sportiv");
    // p1.add(t4);}
    // else if(x.getRol().equals("sc")){
    // t4=new JLabel("Rol: Secretar de cursa");
    // p1.add(t4);}
    // else if(x.getRol().equals("admin")){
    // t4=new JLabel("Rol: Administrator");
    // p1.add(t4);}

    b1 = new JButton("Schimba Parola");
    add(p1);
    add(b1, BorderLayout.SOUTH);
    setVisible(true);

    b1.addActionListener(e1 -> {
      new SchimbaParola(x).setVisible(true);

    });

  }

}