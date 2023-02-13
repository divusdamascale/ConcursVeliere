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
import javax.swing.ListCellRenderer;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;

public class AdaugareRez extends JFrame {

    ArrayList<Concurs> concursuri = new ArrayList<Concurs>();
    JComboBox<Concurs> comboBox;
    String concurs2;
    String[] concurs;
    ArrayList<Persoana> concurenti;
    BufferedReader bf;

    public AdaugareRez() {
        try {
            concursuri = new ArrayList<Concurs>();
            concurenti = new ArrayList<Persoana>();
            setTitle("Rezultat");
            setSize(400, 250);
            setLocationRelativeTo(null);
            bf = new BufferedReader(new FileReader("concursuri.txt"));

            while (true) {

                concurenti.clear();
                concurs2 = bf.readLine();
                if (concurs2 == null) {
                    bf.close();
                    break;
                }
                // System.out.println(concurs2);
                concurs = concurs2.split(" ");
                boolean flag = false;
                for (int i = 5; i < concurs.length; i++) {
                    // System.out.println(i);

                    if (concurs[5].equals("null")) {
                        concursuri.add(new Concurs(concurs[0], concurs[1], concurs[2], Integer.parseInt(concurs[3]),
                                Integer.parseInt(concurs[4])));
                        flag = true;
                        break;

                    }
                    concurenti.add(new Persoana(concurs[i]));

                }

                // System.out.println(concurenti);
                // System.out.println(new Concurs(concurs[0],concurs[1],concurs[2],concurenti));
                if (flag == false) {
                    concursuri.add(new Concurs(concurs[0], concurs[1], concurs[2], Integer.parseInt(concurs[3]),
                            Integer.parseInt(concurs[4]), (ArrayList<Persoana>) concurenti.clone()));
                }

            }

            // System.out.println(concursuri.get(2).getConcurenti().toString());
            // System.out.println(concursuri);

            // System.out.println(concursuri.get(2).toString());
            comboBox = new JComboBox<Concurs>(concursuri.toArray(new Concurs[0]));
            comboBox.setRenderer(new ListCellRenderer<Concurs>() {
                @Override
                public Component getListCellRendererComponent(JList<? extends Concurs> list, Concurs value, int index,
                        boolean isSelected, boolean cellHasFound) {
                    JLabel label = new JLabel(value.getDenumire());
                    return label;
                }
            });

            JPanel p1 = new JPanel(new FlowLayout());

            p1.add(comboBox);
            add(p1, BorderLayout.NORTH);

            Concurs text = (Concurs) comboBox.getSelectedItem();

            JLabel[] rezultat = new JLabel[text.getConcurenti().size()];
            JTextField[] rezultatText = new JTextField[text.getConcurenti().size()];

            for (int i = 0; i < rezultat.length; i++) {
                rezultat[i] = new JLabel(text.getConcurenti().get(i).getNume());
                rezultatText[i] = new JTextField();
            }

            JPanel p2 = new JPanel(new GridLayout(rezultat.length, 2));
            for (int i = 0; i < rezultatText.length; i++) {
                p2.add(rezultat[i]);
                p2.add(rezultatText[i]);
            }

            add(p2);

            JButton addButton = new JButton("Adaugare Rezultat");

            add(addButton, BorderLayout.SOUTH);

            comboBox.addActionListener(e -> {
                Concurs text1 = (Concurs) comboBox.getSelectedItem();
                p2.removeAll();
                p2.setLayout(new GridLayout(text1.getConcurenti().size(), 2));
                JLabel[] rezultat1 = new JLabel[text1.getConcurenti().size()];
                JTextField[] rezultatText1 = new JTextField[text1.getConcurenti().size()];

                for (int i = 0; i < rezultat1.length; i++) {
                    rezultat1[i] = new JLabel(text1.getConcurenti().get(i).getNume());
                    rezultatText1[i] = new JTextField();
                }

                for (int i = 0; i < rezultatText1.length; i++) {
                    p2.add(rezultat1[i]);
                    p2.add(rezultatText1[i]);
                }

                revalidate();

            });

            addButton.addActionListener(e -> {
                try {
                    RezultatConcurs urs;

                    String[] locuri = new String[rezultat.length];

                    for (int i = 0; i < locuri.length; i++) {
                        locuri[Integer.parseInt(rezultatText[i].getText()) - 1] = rezultat[i].getText();
                    }
                    Concurs text1 = (Concurs) comboBox.getSelectedItem();

                    urs = new RezultatConcurs(text1.getDenumire(), text1.getOrganizator(), text1.getLocatie(), locuri);
                    BufferedWriter write = new BufferedWriter(new FileWriter("rezultateConcurs.txt", true));

                    StringBuilder sb = new StringBuilder();

                    sb.append(urs.getNumeConcurs()).append(" ").append(urs.getLocatie()).append(" ")
                            .append(urs.getOrganizator()).append(" ");
                    for (String str : locuri) {
                        sb.append(str).append(" ");
                    }

                    write.write(sb.toString());
                    write.newLine();
                    JOptionPane.showMessageDialog(null, "Rezultatul a fost adaugat");

                    write.close();

                    BufferedReader citireConcursuri = new BufferedReader(new FileReader("concursuri.txt"));
                    BufferedWriter scriereConcursuri = new BufferedWriter(new FileWriter("temp.txt"));
                    String concurs;
                    String[] concursuri;
                    Concurs text2 = (Concurs) comboBox.getSelectedItem();
                    int index1 = comboBox.getSelectedIndex();
                    if (index1 == 0) {
                        comboBox.setSelectedIndex(index1 + 1);
                    } else {
                        comboBox.setSelectedIndex(index1 - 1);
                    }

                    comboBox.removeItemAt(index1);

                    while (true) {
                        concurs = citireConcursuri.readLine();
                        if (concurs == null) {
                            break;
                        }

                        concursuri = concurs.split(" ");
                        if (concursuri[0].equals(text2.getDenumire())) {
                            continue;
                        } else {
                            for (int i = 0; i < concursuri.length; i++) {
                                scriereConcursuri.write(concursuri[i] + " ");
                            }
                            scriereConcursuri.newLine();
                        }

                    }

                    scriereConcursuri.close();
                    citireConcursuri.close();

                    File originalFile = new File("concursuri.txt");
                    originalFile.delete();

                    File tempFile = new File("temp.txt");
                    tempFile.renameTo(originalFile);

                } catch (Exception l) {
                    System.out.println(l);
                }

            });

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new AdaugareRez().setVisible(true);
    }

}
