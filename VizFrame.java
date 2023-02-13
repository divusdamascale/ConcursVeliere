
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;

import java.awt.*;

public class VizFrame extends JFrame {
    private Persoana x;

    private JButton concursuri, delogare, info, inscriere, rezultate;

    public VizFrame(Persoana x) {
        super("Vizitator");
        this.x = x;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(300, 400);
        setLayout(new GridLayout(6, 1, 10, 10));
        concursuri = new JButton("Concursuri");
        inscriere = new JButton("Inscriere");
        rezultate = new JButton("Rezultate Concursuri");
        info = new JButton("Informatii");
        delogare = new JButton("Log out");
        this.add(new JLabel("Salut, " + x.getNume() + " !" + "\n Vizitator"));
        this.add(concursuri);
        this.add(rezultate);
        this.add(inscriere);
        this.add(info);
        this.add(delogare);

        rezultate.addActionListener(e -> {
            new VizualizareRez().setVisible(true);
        });

        concursuri.addActionListener(e -> {
            new Concursuri(x).setVisible(true);
        });

        inscriere.addActionListener(e -> {
            try {
                BufferedReader buf = new BufferedReader(new FileReader("cereri.txt"));
                String string;
                String str[];
                boolean xy = false;
                while (true) {
                    string = buf.readLine();
                    System.out.println(string);
                    if (string == null) {
                        break;
                    }
                    str = string.split(" ");
                    if (str[0].equals(x.getUsername())) {
                        JOptionPane.showMessageDialog(null,
                                "Cererea dumneavoastra a fost deja inregistrata si urmeaza sa fie procesata.");
                        xy = true;
                        break;
                    }
                }

                if (xy == false) {
                    new Formular(x).setVisible(true);
                }
                buf.close();
            } catch (Exception e2) {
                return;
            }

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
        // new VizFrame("test",20,"Ad").setVisible(true);
    }

}
