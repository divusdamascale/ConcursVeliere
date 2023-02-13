import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.GridLayout;

public class Cereri extends JFrame {

    private ArrayList<Cerere> cereri;
    private JComboBox<Cerere> comboBox;
    private JLabel l1, l2, l3, l4, l5, l6;
    private JButton accept, decline;

    public Cereri() {
        super("Cereri Inscriere");
        setSize(400, 200);
        setLocationRelativeTo(null);
        cereri = new ArrayList<Cerere>();
        try {
            BufferedReader buf = new BufferedReader(new FileReader("cereri.txt"));
            String cred;
            String[] creds;
            while (true) {
                cred = buf.readLine();
                if (cred == null) {
                    break;
                }
                creds = cred.split(" ");
                cereri.add(new Cerere(creds[0], creds[1], Integer.parseInt(creds[2])));
            }

            comboBox = new JComboBox<Cerere>(cereri.toArray(new Cerere[0]));
            comboBox.setRenderer(new ListCellRenderer<Cerere>() {
                @Override
                public Component getListCellRendererComponent(JList<? extends Cerere> list, Cerere value, int index,
                        boolean isSelected, boolean cellHasFound) {
                    JLabel label = new JLabel("Cerere de inscriere:" + value.getUsername());
                    return label;

                }

            });

            this.add(comboBox, BorderLayout.NORTH);
            Cerere text = (Cerere) comboBox.getSelectedItem();
            l1 = new JLabel("Username: ");
            l2 = new JLabel(text.getUsername());
            l3 = new JLabel("Nume: ");
            l4 = new JLabel(text.getNume());
            l5 = new JLabel("Varsta: ");
            l6 = new JLabel(Integer.toString(text.getVarsta()));
            JPanel p1 = new JPanel(new GridLayout(3, 2));
            p1.add(l1);
            p1.add(l2);
            p1.add(l3);
            p1.add(l4);
            p1.add(l5);
            p1.add(l6);
            add(p1);

            comboBox.addActionListener(e -> {
                Cerere text1 = (Cerere) comboBox.getSelectedItem();
                p1.removeAll();
                l1 = new JLabel("Username: ");
                l2 = new JLabel(text1.getUsername());
                l3 = new JLabel("Nume: ");
                l4 = new JLabel(text1.getNume());
                l5 = new JLabel("Varsta: ");
                l6 = new JLabel(Integer.toString(text1.getVarsta()));
                p1.add(l1);
                p1.add(l2);
                p1.add(l3);
                p1.add(l4);
                p1.add(l5);
                p1.add(l6);
                revalidate();
            });

            accept = new JButton("Accept");
            decline = new JButton("Decline");

            JPanel p2 = new JPanel(new FlowLayout());
            p2.add(accept);
            p2.add(decline);
            add(p2, BorderLayout.SOUTH);

            accept.addActionListener(e -> {

                try {
                    BufferedReader logareCitire = new BufferedReader(new FileReader("logare.txt"));
                    BufferedWriter logareScriere = new BufferedWriter(new FileWriter("tempLogare.txt"));
                    BufferedReader cereriCitire = new BufferedReader(new FileReader("cereri.txt"));
                    BufferedWriter cereriScriere = new BufferedWriter(new FileWriter("tempCereri.txt"));

                    Cerere c = (Cerere) comboBox.getSelectedItem();
                    int index = comboBox.getSelectedIndex();
                    String cred1;
                    String[] creds1;
                    int nrConcurs = 0;

                    if (index == 0) {
                        comboBox.setSelectedIndex(index + 1);
                    } else {
                        comboBox.setSelectedIndex(index - 1);
                    }

                    comboBox.removeItemAt(index);

                    while (true) {
                        cred1 = logareCitire.readLine();
                        if (cred1 == null) {
                            break;
                        }

                        creds1 = cred1.split(" ");
                        boolean flag = false;
                        if (creds1[2].equals("s")) {
                            nrConcurs = Integer.parseInt(creds1[5]);
                        }

                        if (creds1[0].equals(c.getUsername())) {
                            creds1[2] = "s";
                            for (String str : creds1) {
                                logareScriere.write(str + " ");
                            }
                            nrConcurs++;
                            logareScriere.write(Integer.toString(nrConcurs));
                            flag = true;
                        } else {
                            if (flag == true) {
                                int f = Integer.parseInt(creds1[5]);
                                f += 2;
                                creds1[5] = Integer.toString(f);
                            }

                            for (String str : creds1) {

                                logareScriere.write(str + " ");
                            }
                        }
                        logareScriere.newLine();
                    }

                    while (true) {
                        cred1 = cereriCitire.readLine();
                        if (cred1 == null) {
                            break;
                        }

                        creds1 = cred1.split(" ");
                        if (creds1[0].equals(c.getUsername())) {
                            continue;
                        } else {
                            for (String str : creds1) {
                                cereriScriere.write(str + " ");
                            }
                        }
                        cereriScriere.newLine();
                    }

                    logareCitire.close();
                    logareScriere.close();
                    cereriCitire.close();
                    cereriScriere.close();

                    File originalLogare = new File("logare.txt");
                    originalLogare.delete();

                    File originalCereri = new File("cereri.txt");
                    originalCereri.delete();

                    File tempLogare = new File("tempLogare.txt");
                    tempLogare.renameTo(originalLogare);

                    File tempCereri = new File("tempCereri.txt");
                    tempCereri.renameTo(originalCereri);

                } catch (Exception l) {
                    System.out.print(l);
                }

            });

            decline.addActionListener(e -> {
                try {
                    BufferedReader cereriCitire = new BufferedReader(new FileReader("cereri.txt"));
                    BufferedWriter cereriScriere = new BufferedWriter(new FileWriter("tempCereri.txt"));

                    Cerere c = (Cerere) comboBox.getSelectedItem();
                    int index = comboBox.getSelectedIndex();
                    String cred1;
                    String[] creds1;

                    if (index == 0) {
                        comboBox.setSelectedIndex(index + 1);
                    } else {
                        comboBox.setSelectedIndex(index - 1);
                    }

                    comboBox.removeItemAt(index);

                    while (true) {
                        cred1 = cereriCitire.readLine();
                        if (cred1 == null) {
                            break;
                        }

                        creds1 = cred1.split(" ");
                        if (creds1[0].equals(c.getUsername())) {
                            continue;
                        } else {
                            for (String str : creds1) {
                                cereriScriere.write(str + " ");
                            }
                        }
                        cereriScriere.newLine();
                    }
                    cereriCitire.close();
                    cereriScriere.close();

                    File originalCereri = new File("cereri.txt");
                    originalCereri.delete();
                    File tempCereri = new File("tempCereri.txt");
                    tempCereri.renameTo(originalCereri);

                } catch (Exception l) {
                    System.out.print(l);
                }

            });

            buf.close();

        } catch (Exception e) {
            return;
        }

    }

    public static void main(String[] args) {
        new Cereri().setVisible(true);
    }

}
