import javax.swing.JFrame;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SchimbaParola extends JFrame {

    public SchimbaParola(Persoana x) {
        setLocationRelativeTo(null);
        JPanel form = new JPanel();
        JLabel ok1, ok2, ok3;
        ok1 = new JLabel("");
        ok2 = new JLabel("");
        ok3 = new JLabel("");
        JPasswordField text1, text2, text3;
        text1 = new JPasswordField();
        text2 = new JPasswordField();
        text3 = new JPasswordField();
        form.setLayout(new GridLayout(3, 3));
        form.add(new JLabel("Parola actuala:"));
        form.add(text1);
        form.add(ok1);
        form.add(new JLabel("Parola noua:"));
        form.add(text2);
        form.add(ok2);
        form.add(new JLabel("Confrima parola noua:"));
        form.add(text3);
        form.add(ok3);

        JPanel formButton = new JPanel();
        JButton buttonReset = new JButton("Reseteaza parola");
        formButton.add((buttonReset));

        add(form);
        add(formButton, BorderLayout.SOUTH);

        setSize(400, 150);
        setLocationRelativeTo(null);

        buttonReset.addActionListener(l -> {
            if (ok1.getText().equals("ok") && ok2.getText().equals("ok") && ok3.getText().equals("ok")) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("logare.txt"));
                    BufferedWriter writer = new BufferedWriter(new FileWriter("temp.txt"));
                    String cred;
                    String[] creds;
                    StringBuilder sb = new StringBuilder();
                    while ((cred = reader.readLine()) != null) {
                        creds = cred.split(" ");
                        if (creds[0].equals(x.getUsername())) {
                            creds[1] = text2.getText();
                            for (String string : creds) {
                                sb.append(string).append(" ");
                            }
                            writer.write(sb.toString());
                            writer.newLine();
                        } else {
                            writer.write(cred);
                            writer.newLine();
                        }
                    }
                    writer.close();
                    reader.close();

                    File orignalFile = new File("logare.txt");
                    orignalFile.delete();

                    File tempFile = new File("temp.txt");
                    tempFile.renameTo(orignalFile);
                    dispose();
                    JOptionPane.showMessageDialog(null, "Parola dumneavoastra a fost schimbata cu succes!");

                } catch (Exception l1) {
                    return;
                }

            } else {
                System.out.println("not ok");
            }
        });

        text1.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e2) {

                try {

                    BufferedReader buf = new BufferedReader(new FileReader("logare.txt"));
                    String cred;
                    String[] creds;
                    boolean succes = false;
                    while (true) {

                        cred = buf.readLine();
                        if (cred == null) {
                            break;
                        }
                        creds = cred.split(" ");
                        // System.out.println(creds[0]+" "+ creds[1]);
                        if (x.getUsername().equals(creds[0])) {
                            if (text1.getText().equals(creds[1])) {
                                ok1.setText("ok");
                                ok1.setForeground(Color.green);
                                succes = true;
                            }
                        }
                    }
                    buf.close();
                    if (succes == false) {
                        ok1.setText("Parola gresita");
                        ok1.setForeground(Color.red);
                    }

                    if (text1.getText().length() == 0) {
                        ok1.setText("");
                    }

                } catch (Exception e) {
                    return;
                }
            }
        });

        text2.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e2) {
                if (!text2.getText()
                        .matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")) {
                    if (text2.getText().length() == 0) {
                        ok2.setText("");
                    } else {
                        ok2.setText("weak");
                        ok2.setForeground(Color.red);
                    }
                } else {
                    ok2.setText("ok");
                    ok2.setForeground(Color.green);
                }
            }
        });

        text3.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e2) {
                if (text3.getText().equals(text2.getText())) {
                    if (text3.getText().length() == 0) {
                        ok3.setText("");
                    } else {
                        if (!ok2.getText().equals("ok")) {
                            ok3.setForeground(Color.red);
                            ok3.setText("invalid");
                        } else {
                            ok3.setText("ok");
                            ok3.setForeground(Color.green);
                        }

                    }

                } else {
                    if (text3.getText().length() == 0) {
                        ok3.setText("");
                    } else {
                        ok3.setText("not the same");
                        ok3.setForeground(Color.red);
                    }
                }
            }
        });

        setVisible(true);

    };

}
