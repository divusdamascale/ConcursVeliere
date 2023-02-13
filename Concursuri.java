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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;

import java.awt.BorderLayout;

public class Concursuri extends JFrame {

    JComboBox<Concurs> comboBox;
    ArrayList<Concurs> concursuri;
    String concurs2;
    String[] concurs;
    ArrayList<Persoana> concurenti;
    JLabel l11;
    JLabel l22;
    JLabel l33;
    JLabel l5;
    JPanel p1;
    JButton addButton, deleteButton, editButton;

    /**
     * @param x
     */
    public Concursuri(Persoana x) {
        try {
            setTitle("Concursuri disponibile");
            setSize(700, 600);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());
            concursuri = new ArrayList<Concurs>();
            concurenti = new ArrayList<Persoana>();
            BufferedReader bf = new BufferedReader(new FileReader("concursuri.txt"));
            System.out.println(concurs2);
            while (true) {

                concurenti.clear();
                concurs2 = bf.readLine();
                if (concurs2 == null) {
                    bf.close();
                    break;
                }
                concurs = concurs2.split(" ");
                boolean flag = false;
                for (int i = 5; i < concurs.length; i++) {
                    // System.out.println(i);
                    if (concurs[i].equals("null")) {
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

            this.add(comboBox, BorderLayout.NORTH);
            Concurs text = (Concurs) comboBox.getSelectedItem();
            p1 = new JPanel(new GridLayout(5, 2));
            JLabel l1 = new JLabel("Denumire Concurs: ");
            l11 = new JLabel(text.getDenumire());
            JLabel l2 = new JLabel("Organizator Concurs: ");
            l22 = new JLabel(text.getOrganizator());
            JLabel l3 = new JLabel("Locatie Concurs: ");
            l33 = new JLabel(text.getLocatie());
            JLabel l10 = new JLabel("Varste: ");
            JLabel l13 = new JLabel(text.getvMin() + " - " + text.getvMax());
            JLabel l4 = new JLabel("Concurenti:");
            l5 = new JLabel(text.getConcurenti().toString());

            p1.add(l1);
            p1.add(l11);
            p1.add(l2);
            p1.add(l22);
            p1.add(l3);
            p1.add(l33);
            p1.add(l10);
            p1.add(l13);
            p1.add(l4);
            p1.add(l5);
            this.add(p1);

            if (x.getRol().equals("admin") || x.getRol().equals("sc")) {
                JPanel p3 = new JPanel(new FlowLayout());
                addButton = new JButton("Adaugare");
                deleteButton = new JButton("Stergere");
                editButton = new JButton("Editare");
                p3.add(addButton);
                p3.add(editButton);
                p3.add(deleteButton);
                this.add(p3, BorderLayout.SOUTH);

                addButton.addActionListener(e -> {
                    dispose();
                    new AddConcursuri().setVisible(true);

                });
                // edit button
                editButton.addActionListener(e -> {
                    dispose();
                    new EditConcurs((Concurs) comboBox.getSelectedItem()).setVisible(true);
                });

                deleteButton.addActionListener(e -> {

                    try {
                        // Concurs conc = (Concurs) comboBox.getSelectedItem();

                        int index = comboBox.getSelectedIndex();
                        if (index == 0) {
                            comboBox.setSelectedIndex(index + 1);
                        } else {
                            comboBox.setSelectedIndex(index - 1);
                        }

                        comboBox.removeItemAt(index);
                        concursuri.remove(index);

                        // System.out.println(concursuri.remove(conc));
                        BufferedWriter buf = new BufferedWriter(new FileWriter("temp.txt"));

                        for (int i = 0; i < concursuri.size(); i++) {
                            buf.write(concursuri.get(i).getDenumire() + " " + concursuri.get(i).getOrganizator() + " "
                                    + concursuri.get(i).getLocatie() + " " + concursuri.get(i).getvMin() + " "
                                    + concursuri.get(i).getvMax() + " ");
                            System.out.println(concursuri.get(i).getDenumire() + " "
                                    + concursuri.get(i).getOrganizator() + " " + concursuri.get(i).getLocatie() + " "
                                    + concursuri.get(i).getvMin() + " " + concursuri.get(i).getvMax() + " ");

                            if (concursuri.get(i).getConcurenti().size() > 0) {
                                for (int j = 0; j < concursuri.get(i).getConcurenti().size(); j++) {
                                    buf.write(concursuri.get(i).getConcurenti().get(j).getUsername() + " ");

                                }
                            }

                            buf.newLine();

                        }
                        buf.close();
                        File originalFile = new File("concursuri.txt");
                        originalFile.delete();

                        File tempFile = new File("temp.txt");
                        tempFile.renameTo(originalFile);

                        JOptionPane.showMessageDialog(null, "Concursul a fost sters din baza de date");

                    } catch (Exception l) {
                        System.out.println(l);
                    }
                });
            }

            comboBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    p1.removeAll();
                    Concurs text = (Concurs) comboBox.getSelectedItem();
                    JLabel l1 = new JLabel("Denumire Concurs: ");
                    l11.setText(text.getDenumire());
                    JLabel l2 = new JLabel("Organizator Concurs: ");
                    l22.setText(text.getOrganizator());
                    JLabel l3 = new JLabel("Locatie Concurs: ");
                    l33.setText(text.getLocatie());
                    JLabel l10 = new JLabel("Varste: ");
                    JLabel l13 = new JLabel(text.getvMin() + " - " + text.getvMax());
                    JLabel l4 = new JLabel("Concurenti:");
                    l5.setText(text.getConcurenti().toString());
                    p1.add(l1);
                    p1.add(l11);
                    p1.add(l2);
                    p1.add(l22);
                    p1.add(l3);
                    p1.add(l33);
                    p1.add(l10);
                    p1.add(l13);
                    p1.add(l4);
                    p1.add(l5);
                    revalidate();

                }
            });

        } catch (Exception e) {
            return;
        }
    }

}
