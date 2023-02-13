import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class AddConcursuri extends JFrame {
    JTextField t1, t2, t3, t4, t5;

    AddConcursuri() {
        super("Add Concurs");
        setSize(400, 300);
        setLocationRelativeTo(null);
        JPanel p1 = new JPanel(new GridLayout(3, 2));
        JLabel l1, l2, l3;
        l1 = new JLabel("Nume Concurs:");
        l2 = new JLabel("Organizator Concurs");
        l3 = new JLabel("Locatie Concurs:");
        t1 = new JTextField(50);
        t2 = new JTextField(50);
        t3 = new JTextField(50);

        p1.add(l1);
        p1.add(t1);
        p1.add(l2);
        p1.add(t2);
        p1.add(l3);
        p1.add(t3);

        JPanel p2 = new JPanel(new FlowLayout());
        JLabel l4 = new JLabel("Interval Varsta");
        t4 = new JTextField(2);
        t5 = new JTextField(2);
        p2.add(l4);
        p2.add(t4);
        p2.add(t5);

        JPanel p3 = new JPanel(new GridLayout(3, 1));
        p3.add(p1);
        p3.add(p2);

        JButton addButton = new JButton("Add");

        JPanel p4 = new JPanel(new FlowLayout());
        p4.add(addButton);

        p3.add(p4);
        add(p3);

        addButton.addActionListener(e -> {
            try {
                BufferedWriter buf = new BufferedWriter(new FileWriter("concursuri.txt", true));
                StringBuilder sb = new StringBuilder();
                sb.append(t1.getText()).append(" ");
                sb.append(t2.getText()).append(" ");
                sb.append(t3.getText()).append(" ");
                sb.append(t4.getText()).append(" ").append(t5.getText()).append(" null");
                buf.write(sb.toString());
                buf.newLine();
                buf.close();
                new Concursuri(new Persoana("void", 0, "void", "void", "admin")).setVisible(true);
                JOptionPane.showMessageDialog(null, "Concursul a fost creat!");
                dispose();

            } catch (Exception l) {
                System.out.println(l);
            }
        });

    }

    public static void main(String[] args) {
        new AddConcursuri().setVisible(true);
    }

}
