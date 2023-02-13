import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.*;

public class AdminFrame extends JFrame {

    private JButton concursuri, delogare, conturi, info, cereri;

    public AdminFrame(Persoana x) {
        super("Admin");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLayout(new GridLayout(8, 1, 10, 10));

        concursuri = new JButton("Concursuri");
        JButton rezultat = new JButton("Rezultat Concurs");
        JButton vizRez = new JButton("Vizulizare rezultate");
        // inscriere = new JButton("Inscriere");
        info = new JButton("Informatii");
        delogare = new JButton("Log out");
        cereri = new JButton("Cereri");
        conturi = new JButton("Conturi");
        this.add(new JLabel("Salut, " + x.getNume() + " !"));
        this.add(concursuri);
        this.add(rezultat);
        this.add(vizRez);
        this.add(cereri);
        this.add(conturi);
        this.add(info);
        this.add(delogare);

        vizRez.addActionListener(e -> {
            new VizualizareRez().setVisible(true);
        });

        rezultat.addActionListener(e -> {
            new AdaugareRez().setVisible(true);
        });

        conturi.addActionListener(e -> {
            new Conturi(x).setVisible(true);
        });

        concursuri.addActionListener(e -> {
            new Concursuri(x).setVisible(true);
        });

        cereri.addActionListener(e -> {
            new Cereri().setVisible(true);
        });

        delogare.addActionListener(e -> {
            dispose();
            new LogIn().setVisible(true);
        });

        info.addActionListener(e -> {
            new Informatii(x).setVisible(true);
        });
    }

    public static void main(String[] args) {
        new AdminFrame(new Persoana("test", 30, "test", "test", "admin")).setVisible(true);
    }

}
