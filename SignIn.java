import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class SignIn extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField passwordConfirmField;
    private JTextField nameField;
    private JTextField ageField;
    private JLabel usernameStatusLabel;
    private JLabel passwordStatusLabel;
    private JLabel passwordConfirmStatusLabel;
    private JLabel nameStatusLabel;
    private JLabel ageStatusLabel;
    private JButton loginButton;

    public SignIn() {
        super("Sign In");
        setSize(400, 270);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 10, 10));

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        passwordConfirmField = new JPasswordField(20);
        nameField = new JTextField(20);
        ageField = new JTextField(20);
        usernameStatusLabel = new JLabel(" ");
        passwordStatusLabel = new JLabel(" ");
        passwordConfirmStatusLabel = new JLabel(" ");
        nameStatusLabel = new JLabel(" ");
        ageStatusLabel = new JLabel(" ");
        loginButton = new JButton("Sign In");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameStatusLabel.getText().equals("ok") && passwordStatusLabel.getText().equals("ok")
                        && passwordConfirmStatusLabel.getText().equals("ok") && nameStatusLabel.getText().equals("ok")
                        && ageStatusLabel.getText().equals("ok")) {
                    try {
                        BufferedWriter buf = new BufferedWriter(new FileWriter("logare.txt", true));
                        StringBuffer sb = new StringBuffer();
                        sb.append(usernameField.getText());
                        sb.append(" ");
                        sb.append(passwordField.getText());
                        sb.append(" ");
                        sb.append("v");
                        sb.append(" ");
                        sb.append(nameField.getText());
                        sb.append(" ");
                        sb.append(ageField.getText());
                        sb.append(" ");
                        buf.newLine();
                        buf.write(sb.toString());
                        buf.close();
                        dispose();
                        JOptionPane.showMessageDialog(null, "Bine ai venit," + nameField.getText() + "!");
                        new LogIn().setVisible(true);
                    } catch (Exception e1) {
                        return;
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Parola si username-ul trebuie sa contina minim o majuscula.");
                }
            }
        });

        usernameField.setPreferredSize(new Dimension(180, 10));
        passwordField.setPreferredSize(new Dimension(180, 10));
        passwordConfirmField.setPreferredSize(new Dimension(180, 10));
        nameField.setPreferredSize(new Dimension(180, 10));
        ageField.setPreferredSize(new Dimension(180, 10));

        formPanel.add(new JLabel("Username:"));
        formPanel.add(usernameField);
        formPanel.add(usernameStatusLabel);
        formPanel.add(new JLabel("Password:"));
        formPanel.add(passwordField);
        formPanel.add(passwordStatusLabel);
        formPanel.add(new JLabel("Confirm Password:"));
        formPanel.add(passwordConfirmField);
        formPanel.add(passwordConfirmStatusLabel);
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(nameStatusLabel);
        formPanel.add(new JLabel("Age:"));
        formPanel.add(ageField);
        formPanel.add(ageStatusLabel);

        usernameField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                try {

                    if (usernameField.getText().matches("[A-Z]+.*")) {
                        if (usernameField.getText().length() < 6) {

                            usernameStatusLabel.setForeground(Color.red);
                            usernameStatusLabel.setText("Min 6 char");
                        }
                        if (usernameField.getText().length() > 16) {
                            usernameStatusLabel.setForeground(Color.red);
                            usernameStatusLabel.setText("Max 15 char");
                        }
                        if (usernameField.getText().length() < 16 && usernameField.getText().length() >= 6) {
                            BufferedReader buf = new BufferedReader(
                                    new FileReader("D:\\Facultate\\POO\\eVeliere\\src\\eveliere\\logare.txt"));
                            String nume;
                            String[] nume1;
                            Boolean ok = false;
                            while (true) {
                                nume = buf.readLine();
                                if (nume == null) {
                                    break;
                                }
                                nume1 = nume.split(" ");
                                if (nume1[0].equals(usernameField.getText())) {
                                    usernameStatusLabel.setText("Username in use");
                                    usernameStatusLabel.setForeground(Color.red);
                                    ok = true;
                                }
                            }
                            if (ok == false) {
                                usernameStatusLabel.setText("ok");
                                usernameStatusLabel.setForeground(Color.green);
                            }
                            buf.close();

                        }

                    } else {
                        if (usernameField.getText().length() == 0) {
                            usernameStatusLabel.setText("");
                        } else {
                            usernameStatusLabel.setText("Invalid char");
                            usernameStatusLabel.setForeground(Color.red);
                        }

                    }

                } catch (Exception e1) {
                    return;
                }

            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (!passwordField.getText()
                        .matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")) {
                    if (passwordField.getText().length() == 0) {
                        passwordStatusLabel.setText("");
                    } else {
                        passwordStatusLabel.setText("weak");
                        passwordStatusLabel.setForeground(Color.red);
                    }
                } else {
                    passwordStatusLabel.setText("ok");
                    passwordStatusLabel.setForeground(Color.green);
                }
            }
        });

        passwordConfirmField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (passwordConfirmField.getText().equals(passwordField.getText())) {
                    if (passwordConfirmField.getText().length() == 0) {
                        passwordConfirmStatusLabel.setText("");
                    } else {
                        if (!passwordStatusLabel.getText().equals("ok")) {
                            passwordConfirmStatusLabel.setForeground(Color.red);
                            passwordConfirmStatusLabel.setText("invalid");
                        } else {
                            passwordConfirmStatusLabel.setText("ok");
                            passwordConfirmStatusLabel.setForeground(Color.green);
                        }

                    }

                } else {
                    if (passwordConfirmField.getText().length() == 0) {
                        passwordConfirmStatusLabel.setText("");
                    } else {
                        passwordConfirmStatusLabel.setText("not the same");
                        passwordConfirmStatusLabel.setForeground(Color.red);
                    }
                }
            }
        });

        nameField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (nameField.getText().matches("^[A-Z][a-z]*") && nameField.getText().length() > 2) {
                    nameStatusLabel.setText("ok");
                    nameStatusLabel.setForeground(Color.green);
                } else {
                    if (nameField.getText().length() == 0) {
                        nameStatusLabel.setText("");
                    } else {
                        nameStatusLabel.setText("invalid");
                        nameStatusLabel.setForeground(Color.red);
                    }
                }
            }
        });

        ageField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (ageField.getText().matches("[1-9][0-9]*") && ageField.getText().length() < 3) {
                    ageStatusLabel.setText("ok");
                    ageStatusLabel.setForeground(Color.green);
                } else {
                    if (ageField.getText().length() == 0) {
                        ageStatusLabel.setText("");
                    } else {
                        ageStatusLabel.setText("invalid");
                        ageStatusLabel.setForeground(Color.red);
                    }
                }
            }
        });

        add(formPanel, BorderLayout.CENTER);
        add(loginButton, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new SignIn().setVisible(true);
    }
}