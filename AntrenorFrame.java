
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.*;

public class AntrenorFrame extends JFrame {

    private JButton concursuri, delogare, info;

    public AntrenorFrame(Persoana x) {
        super("Antrenor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(300, 400);
        setLayout(new GridLayout(5, 1, 10, 10));
        concursuri = new JButton("Concursuri");
        JButton rezultate = new JButton("Vizualizare Rezultate");
        // inscriere = new JButton("Inscriere");
        info = new JButton("Informatii");
        delogare = new JButton("Log out");
        this.add(new JLabel("Salut, " + x.getNume() + " !"));
        this.add(concursuri);
        this.add(rezultate);
        this.add(info);
        this.add(delogare);

        rezultate.addActionListener(e -> {
            new VizualizareRez().setVisible(true);
        });

        concursuri.addActionListener(e -> {
            new Concursuri(x).setVisible(true);
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
        new AntrenorFrame(new Persoana("test", 30, "test", "test", "a")).setVisible(true);
    }

}
