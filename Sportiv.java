import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;

public class Sportiv extends JFrame {

    private JButton bContestatie, bLogout, bInformatii, buttonConcurs;

    public Sportiv(Persoana x) {
        super("Sportiv");
        setLocationRelativeTo(null);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(7, 1, 10, 10));
        JLabel l1 = new JLabel("Buna ziua!");
        JLabel l2 = new JLabel("Numarul dumneavoastra de concurs este: " + x.getNrConcurs());// +numarul de concurs

        bContestatie = new JButton("Contestatie");
        JButton rezultate = new JButton("Rezultate Concursuri");
        buttonConcurs = new JButton("Concurs");

        p1.add(l1);
        p1.add(l2);
        p1.add(buttonConcurs);
        p1.add(rezultate);
        p1.add(bContestatie);

        rezultate.addActionListener(e -> {
            new VizualizareRez().setVisible(true);
        });

        buttonConcurs.addActionListener(e -> {
            new Concursuri(x).setVisible(true);
        });

        bContestatie.addActionListener(e -> {

            new Contestatie().setVisible(true);
            ;
        });

        bContestatie.setBounds(15, 15, 15, 15);

        bLogout = new JButton("Iesire din cont");

        bLogout.addActionListener(e2 -> {
            dispose();
            new LogIn().setVisible(true);
        });

        bInformatii = new JButton("Informatii");

        bInformatii.addActionListener(e3 -> {

            new Informatii(x).setVisible(true);

        });

        p1.add(bInformatii);
        p1.add(bLogout);
        add(p1);

    }

    public static void main(String[] args) {
        new Sportiv(new Persoana("Gica", 20, "Gicaxxx", "asd", "spoertiv")).setVisible(true);
    }

}
