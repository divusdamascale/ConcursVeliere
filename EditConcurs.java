import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class EditConcurs extends JFrame{
    JTextField t1,t2,t3,t4,t5;
    JComboBox <Persoana> comboBox;
    ArrayList <Persoana> sportiviEligibili =  new ArrayList<Persoana>();
    EditConcurs(Concurs x)
    {
        super("Edit Concurs: " + x.getDenumire());
        setSize(230,500);
        setLocationRelativeTo(null);
        JPanel p1 = new JPanel(new FlowLayout());
        JLabel l1,l2,l3 ;
        l1 =  new JLabel("Denumire");
        l2 = new JLabel("Organizator");
        l3 = new JLabel("Locatie");
        t1 = new JTextField(x.getDenumire(),20);
        t2 = new JTextField(x.getOrganizator(),20);
        t3 = new JTextField(x.getLocatie(),20);
        // t1.setPreferredSize(new Dimension(50,10));
        p1.add(l1);
        p1.add(t1);
        p1.add(l2);
        p1.add(t2);
        p1.add(l3);
        p1.add(t3);
        JPanel p12 = new JPanel();
        JLabel l4 = new JLabel("Varsta Min-Max");
        p1.add(l4);
        t4= new JTextField(Integer.toString(x.getvMin()),3);
        t5= new JTextField(Integer.toString(x.getvMax()),3);
        p12.add(t4);
        p12.add(t5);
        p1.add(p12);
        add(p1);
        JLabel t5 = new JLabel("Concurenti");
        JLabel t6 = new JLabel(x.getConcurenti().toString());
        p1.add(t5);
        JPanel p6= new JPanel();
        p6.add(t6);
        p1.add(p6);

        creeazaSportivi(x, sportiviEligibili);

        // System.out.println(sportiviEligibili.toString());

        comboBox = new JComboBox<Persoana>(sportiviEligibili.toArray(new Persoana[0]));
        comboBox.setRenderer(new ListCellRenderer<Persoana>() {
            @Override
            public Component getListCellRendererComponent(JList <? extends Persoana> list,Persoana value, int index,boolean isSelected,boolean cellHasFound)
            {
                JLabel label = new JLabel(value.getUsername()+" "+value.getNume()+" "+value.getVarsta());
                return label;
            }
        });
        // comboBox.setPopupVisible(true);
        p1.add(comboBox);

        JButton addButton =  new JButton("Adaugare concurent");
        JButton editButton = new JButton("Editare concurs");
        JPanel p3 = new JPanel();
        p1.add(addButton);
        p3.add(editButton);

        p1.add(p3,BorderLayout.SOUTH);

        addButton.addActionListener(e -> 
        {
            try {
                int index = comboBox.getSelectedIndex();
               
                
                x.addConcurenti((Persoana)comboBox.getSelectedItem());

                if(index==0)
                {
                    comboBox.setSelectedIndex(index+1);
                }else
                {
                    comboBox.setSelectedIndex(index-1);
                }

                comboBox.removeItemAt(index);

                p6.removeAll();
                p6.add(new JLabel(x.getConcurenti().toString()));
                
            } catch (Exception l) {
                System.out.println(l);
            }
        });


        editButton.addActionListener(e -> 
        {
            try {
                String cred;
                String [] creds;
                BufferedReader buf = new BufferedReader(new FileReader("concursuri.txt"));
                BufferedWriter write = new BufferedWriter(new FileWriter("temp.txt"));
                Concurs c1=new Concurs(t1.getText(), t2.getText(), t3.getText(), Integer.parseInt(t4.getText()),Integer.parseInt(this.t5.getText()),x.getConcurenti());
                while(true)
                {
                    cred = buf.readLine();
                    if(cred ==  null)
                    {
                        break;
                    }

                    creds = cred.split(" ");

                    if(creds[0].equals(x.getDenumire()))
                    {
                        write.write(c1.getDenumire()+" "+c1.getOrganizator()+" "+c1.getLocatie()+" "+c1.getvMin()+" "+c1.getvMax()+" ");

                        if(c1.getConcurenti().size()>0)
                        {
                            for (Persoana pers : c1.getConcurenti()) {
                                write.write(pers.getUsername()+" ");   
                            }
                        }
                    }else
                    {
                        for (String string : creds) {
                            write.write(string+" ");
                        }
                    }

                    write.newLine();
                }

                buf.close();
                write.close();

                File originalFile = new File("concursuri.txt");
                originalFile.delete();

                File tempFile = new File("temp.txt");
                tempFile.renameTo(originalFile);
                dispose();
                new Concursuri(new Persoana("void", 12, "void", "void", "admin")).setVisible(true);;
                
                
            } catch (Exception l) {
                System.out.println(l);
            }

        });


    }


    private void creeazaSportivi(Concurs x,ArrayList <Persoana> sportiviEligibili)
    {

        try {
        BufferedReader buf = new BufferedReader(new FileReader("logare.txt"));
        String cred;
        String[] creds;

        while(true)
        {
            cred = buf.readLine();
            if(cred == null)
            {
                break;
            }
            creds = cred.split(" ");

            if(creds[2].equals("s")){
                if(Integer.parseInt(creds[4]) < x.getvMax() &&  Integer.parseInt(creds[4]) >x.getvMin())
                {
                    sportiviEligibili.add(new Persoana(creds[3],Integer.parseInt(creds[4]),creds[0],creds[1],creds[2]));
                }
            }

        }

        buf.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }


    public static void main(String[] args) {
        new EditConcurs(new Concurs("Test", "TestOrg", "Testloc", 20,60)).setVisible(true);
    }
    
}
