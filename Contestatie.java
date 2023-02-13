import javax.swing.*;
import java.awt.*;
import javax.swing.JOptionPane;

public class Contestatie extends JFrame {

    private JLabel l1, l2, l3, l4, l5;// "Completati datele necesare pentru contestatie",nume,prenume,varsta,locul
                                      // obtinut
    private JTextField tf1, tf2, tf3, tf4;
    private JPanel p1, p2;

    public Contestatie() {

        super("Contestatie");
        setSize(400, 400);
        setLocationRelativeTo(null);
        p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1, 10, 10));
        l1 = new JLabel("Completati datele necesare contestatiei:");
        p1.add(l1);
        this.add(p1, BorderLayout.NORTH);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(5, 2, 10, 10));
        l2 = new JLabel("Nume:");
        tf1 = new JTextField(15);
        l3 = new JLabel("Prenume:");
        tf2 = new JTextField(15);
        l4 = new JLabel("Varsta");
        tf3 = new JTextField(15);
        l5 = new JLabel("Locul obtinut");
        tf4 = new JTextField(15);
        JButton b1 = new JButton("Trimitere");
        JButton b2 = new JButton("Anulare");

        b1.addActionListener(e -> {
            JOptionPane.showMessageDialog(b2, "Contestatie trimisa!");// +scriere in fisier
        });

        b2.addActionListener(e2 -> {
            this.dispose(); // System.exit(0);
        });
        p2.add(l2);
        p2.add(tf1);
        p2.add(l3);
        p2.add(tf2);
        p2.add(l4);
        p2.add(tf3);
        p2.add(l5);
        p2.add(tf4);
        p2.add(b1);
        p2.add(b2);
        this.add(p2);

    }

    public static void main(String[] args) {
        new Contestatie().setVisible(true);
    }
}