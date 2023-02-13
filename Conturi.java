import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.ListCellRenderer;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Conturi extends JFrame {

    JComboBox <Persoana> comboBox;
    ArrayList <Persoana> persoane;
    String concurs2;
    String [] concurs;
    JLabel l11;
    JLabel l22;
    JLabel l33;
    JLabel l5;
    JPanel p1;
    JButton addButton,deleteButton,editButton;

   


    public Conturi(Persoana x)
    {
        try {
        setTitle("Conturi existente");
        setSize(700,600);
        setLocationRelativeTo(null);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        persoane = new ArrayList<Persoana>();
        BufferedReader bf = new BufferedReader(new FileReader("logare.txt"));
           
        while(true)
        {
            concurs2 = bf.readLine();
            if(concurs2 == null)
            {
                bf.close();
                break;
            }
            concurs = concurs2.split(" ");

            
        
        persoane.add(new Persoana(concurs[3], Integer.parseInt(concurs[4]), concurs[0], concurs[1], concurs[2]));
        }

        
            
        
        // System.out.println(persoane.get(2).toString());
            comboBox = new JComboBox<Persoana>(persoane.toArray(new Persoana[0]));
            comboBox.setRenderer(new ListCellRenderer<Persoana>() {
                @Override
                public Component getListCellRendererComponent(JList <? extends Persoana> list,Persoana value, int index,boolean isSelected,boolean cellHasFound)
                {
                    JLabel label = new JLabel(value.getUsername());
                    return label;
                }
            });


            this.add(comboBox,BorderLayout.NORTH);
            Persoana text = (Persoana)comboBox.getSelectedItem();
             p1 = new JPanel(new GridLayout(4,2));
            JLabel l1 = new JLabel("Username: " );
             l11 = new JLabel(text.getUsername());
            JLabel l2 = new JLabel("Nume: "  );
             l22 = new JLabel(text.getNume());
            JLabel l3 = new JLabel("Varsta: "  );
             l33 = new JLabel( Integer.toString(text.getVarsta()));
            JLabel l4 = new JLabel("Rol:");
             l5 = new JLabel(text.getRol());
            
            
            p1.add(l1);
            p1.add(l11);
            p1.add(l2);
            p1.add(l22);
            p1.add(l3);
            p1.add(l33);
            p1.add(l4);
            p1.add(l5);
            this.add(p1);

            
                JPanel p3 = new JPanel(new FlowLayout());
                addButton = new JButton("Adaugare");
                deleteButton = new JButton("Stergere");
                editButton = new JButton("Editare");
                p3.add(addButton);
                p3.add(editButton);
                p3.add(deleteButton);
                this.add(p3,BorderLayout.SOUTH);
            

            comboBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    
                    p1.removeAll();
                    Persoana text = (Persoana) comboBox.getSelectedItem();
                     JLabel l1 = new JLabel("Username: " );
                     l11.setText(text.getUsername());
                     JLabel l2 = new JLabel("Nume: "  );
                     l22.setText(text.getNume());
                     JLabel l3 = new JLabel("Varsta: "  );
                     l33.setText(Integer.toString(text.getVarsta()));
                     JLabel l4 = new JLabel("Rol:");
                     l5.setText(text.getRol());
                     p1.add(l1);
                     p1.add(l11);
                     p1.add(l2);
                     p1.add(l22);
                     p1.add(l3);
                     p1.add(l33);
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
