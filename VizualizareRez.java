import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.JPanel;
import java.awt.*;

public class VizualizareRez extends JFrame {
    ArrayList<RezultatConcurs> rezultate = new ArrayList<RezultatConcurs>();
    JComboBox<RezultatConcurs> comboBox;
    JLabel l1, l2, l3, n1, n2, n3;

    public VizualizareRez() {
        try {
            setTitle("Rezultate Concurs");
            setSize(400, 200);
            setLocationRelativeTo(null);
            BufferedReader buf = new BufferedReader(new FileReader("rezultateConcurs.txt"));
            String conc;
            String[] concs;
            while (true) {
                conc = buf.readLine();
                if (conc == null) {
                    break;
                }
                concs = conc.split(" ");
                String[] castigatori = new String[6];
                int index = 0;
                for (int i = 3; i < concs.length; i++) {
                    castigatori[index] = concs[i];
                    index++;
                }

                RezultatConcurs x = new RezultatConcurs(concs[0], concs[1], concs[2], castigatori);
                rezultate.add(x);

            }

            buf.close();

            comboBox = new JComboBox<RezultatConcurs>(rezultate.toArray(new RezultatConcurs[0]));
            comboBox.setRenderer(new ListCellRenderer<RezultatConcurs>() {
                @Override
                public Component getListCellRendererComponent(JList<? extends RezultatConcurs> list,
                        RezultatConcurs value, int index, boolean isSelected, boolean cellHasFound) {
                    JLabel label = new JLabel(value.getNumeConcurs());
                    return label;
                }
            });

            JPanel p1 = new JPanel();
            p1.add(comboBox);
            add(p1, BorderLayout.NORTH);
            RezultatConcurs y = (RezultatConcurs) comboBox.getSelectedItem();
            JPanel p2 = new JPanel(new GridLayout(4, 2));
            l1 = new JLabel("Locul 1:");
            l2 = new JLabel("Locul 2:");
            l3 = new JLabel("Locul 3:");
            n1 = new JLabel(y.getLocuri()[0]);
            n2 = new JLabel(y.getLocuri()[1]);
            n3 = new JLabel(y.getLocuri()[2]);
            p2.add(l1);
            p2.add(n1);
            p2.add(l2);
            p2.add(n2);
            p2.add(l3);
            p2.add(n3);
            JPanel p3 = new JPanel(new FlowLayout());
            JLabel l4 = new JLabel(Arrays.toString(y.getLocuri()));

            add(p2);
            p3.add(l4);
            add(p3, BorderLayout.SOUTH);

            comboBox.addActionListener(e -> {
                RezultatConcurs zd = (RezultatConcurs) comboBox.getSelectedItem();
                p2.removeAll();
                p3.removeAll();
                l1 = new JLabel("Locul 1:");
                l2 = new JLabel("Locul 2:");
                l3 = new JLabel("Locul 3:");
                n1 = new JLabel(zd.getLocuri()[0]);
                n2 = new JLabel(zd.getLocuri()[1]);
                n3 = new JLabel(zd.getLocuri()[2]);
                p2.add(l1);
                p2.add(n1);
                p2.add(l2);
                p2.add(n2);
                p2.add(l3);
                p2.add(n3);

                JLabel s3 = new JLabel(Arrays.toString(zd.getLocuri()));
                p3.add(s3);
                revalidate();

            });

        } catch (Exception e) {
            System.out.println(e);

        }

    }

    public static void main(String[] args) {
        new VizualizareRez().setVisible(true);
    }

}
