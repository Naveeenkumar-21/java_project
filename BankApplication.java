import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
public class Main extends Frame implements ActionListener {
    private static final long serialVersionUID = 1L;
    ArrayList<String[]> accountList = new ArrayList<>();
    Label titleLabel, label;
    TextField textField;
    Button submitButton, signUpButton;
    Image backgroundImage;

    public Main() {
        // Load the background image
        backgroundImage = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Downloads\\th.jpg");
        Font titleFont = new Font("Broadway", Font.BOLD, 30);
        Font labelFont = new Font("Andromeda", Font.PLAIN, 20);
        Font textFont = new Font("Arial", Font.PLAIN, 20);
        Font buttonFont = new Font("Broadway", Font.BOLD, 16);

        setTitle("ATM TRANSACTION");
        setSize(700, 500);
        setLayout(new GridBagLayout());

        titleLabel = new Label("Bank Application");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.RED); // Set title text color to red

        label = new Label("Enter Account Number or Phone Number:");
        label.setFont(labelFont);
        label.setForeground(Color.BLUE); // Set label text color to blue

        textField = new TextField(20);
        textField.setFont(textFont);

        submitButton = new Button("Submit");
        submitButton.setFont(buttonFont);
        submitButton.setForeground(Color.RED); // Set button text color to red
        submitButton.setBackground(Color.WHITE); // Set button background color to white

        signUpButton = new Button("Sign Up");
        signUpButton.setFont(buttonFont);
        signUpButton.setForeground(Color.RED); // Set button text color to red
        signUpButton.setBackground(Color.WHITE); // Set button background color to white

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.anchor = GridBagConstraints.CENTER;

        add(titleLabel, gbc);

        gbc.gridy = 1;
        add(label, gbc);

        gbc.gridy = 2;
        add(textField, gbc);

        gbc.gridy = 3;
        add(submitButton, gbc);

        gbc.gridy = 4;
        add(signUpButton, gbc);

        submitButton.addActionListener(this);
        signUpButton.addActionListener(this);

        // Add mouse hover effect
        MouseAdapter hoverEffect = new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
            }
        };

        textField.addMouseListener(hoverEffect);
        submitButton.addMouseListener(hoverEffect);
        signUpButton.addMouseListener(hoverEffect);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent closewindow) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(backgroundImage, 1, 1, getWidth(), getHeight(), this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String input = textField.getText();
            boolean isValid = false;
            String[] accountDetails = null;

            for (String[] account : accountList) {
                if (account[0].equals(input) || account[1].equals(input)) {
                    isValid = true;
                    accountDetails = account;
                    break;
                }
            }

            if (isValid) {
                new BankOperation(accountDetails);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Account Number or Phone Number!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == signUpButton) {
            new SignUpForm(this);
        }
    }

    public static void main(String[] args) {
        new Main();
    }

    public void addAccount(String[] accountDetails) {
        accountList.add(accountDetails);
    }
}

class SignUpForm extends Frame implements ActionListener {
    private static final long serialVersionUID = 1L;
    Main mainApp;
    Label nameLabel, dobLabel, phoneLabel, emailLabel, passwordLabel;
    TextField nameField, dobField, phoneField, emailField, passwordField;
    Button signUpButton;
    Image backgroundImage;

    public SignUpForm(Main mainApp) {
        this.mainApp = mainApp;

        backgroundImage = Toolkit.getDefaultToolkit().getImage("C:\\xampp\\htdocs\\23ITR111\\day 4\\cool-website-background-13.jpg");

        Font labelFont = new Font("Andromeda", Font.PLAIN, 20);
        Font textFont = new Font("Arial", Font.PLAIN, 20);
        Font buttonFont = new Font("Broadway", Font.BOLD, 16);

        setTitle("Sign Up");
        setSize(700, 500);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        nameLabel = new Label("Name:");
        nameLabel.setFont(labelFont);
        nameLabel.setForeground(Color.BLUE);

        dobLabel = new Label("Date of Birth (YYYY-MM-DD):");
        dobLabel.setFont(labelFont);
        dobLabel.setForeground(Color.BLUE);

        phoneLabel = new Label("Phone Number:");
        phoneLabel.setFont(labelFont);
        phoneLabel.setForeground(Color.BLUE);

        emailLabel = new Label("Email:");
        emailLabel.setFont(labelFont);
        emailLabel.setForeground(Color.BLUE);

        passwordLabel = new Label("Password:");
        passwordLabel.setFont(labelFont);
        passwordLabel.setForeground(Color.BLUE);

        nameField = new TextField(20);
        nameField.setFont(textFont);

        dobField = new TextField(20);
        dobField.setFont(textFont);

        phoneField = new TextField(20);
        phoneField.setFont(textFont);

        emailField = new TextField(20);
        emailField.setFont(textFont);

        passwordField = new TextField(20);
        passwordField.setFont(textFont);

        signUpButton = new Button("Sign Up");
        signUpButton.setFont(buttonFont);
        signUpButton.setForeground(Color.RED);
        signUpButton.setBackground(Color.WHITE);

        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        add(nameLabel, gbc);
        gbc.gridy++;
        add(dobLabel, gbc);
        gbc.gridy++;
        add(phoneLabel, gbc);
        gbc.gridy++;
        add(emailLabel, gbc);
        gbc.gridy++;
        add(passwordLabel, gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        add(nameField, gbc);
        gbc.gridy++;
        add(dobField, gbc);
        gbc.gridy++;
        add(phoneField, gbc);
        gbc.gridy++;
        add(emailField, gbc);
        gbc.gridy++;
        add(passwordField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(signUpButton, gbc);

        signUpButton.addActionListener(this);

        // Add mouse hover effect
        MouseAdapter hoverEffect = new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
            }
        };

        nameField.addMouseListener(hoverEffect);
        dobField.addMouseListener(hoverEffect);
        phoneField.addMouseListener(hoverEffect);
        emailField.addMouseListener(hoverEffect);
        passwordField.addMouseListener(hoverEffect);
        signUpButton.addMouseListener(hoverEffect);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signUpButton) {
            String name = nameField.getText();
            String dob = dobField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();

            // Check if any field is empty
            if (name.isEmpty() || dob.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled out!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!validateEmail(email)) {
                JOptionPane.showMessageDialog(this, "Invalid email address!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!validatePassword(password)) {
                JOptionPane.showMessageDialog(this, "Password must be 6-16 characters long, contain at least one uppercase letter, one lowercase letter, one digit, and one special character (@#$%)", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate Date of Birth
            if (!validateDOB(dob)) {
                JOptionPane.showMessageDialog(this, "Invalid Date of Birth! Please use the format YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String accountNumber = generateAccountNumber();
            String[] accountDetails = {accountNumber, phone, name, email, password};
            mainApp.addAccount(accountDetails);

            System.out.println("User Details:");
            System.out.println("Name: " + name);
            System.out.println("Date of Birth: " + dob);
            System.out.println("Phone Number: " + phone);
            System.out.println("Email: " + email);
            System.out.println("Password: " + password);
            System.out.println("Account Number: " + accountNumber); // Print account number to console

            JOptionPane.showMessageDialog(this, "Sign Up Successful! Your Account Number is: " + accountNumber, "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }

    private boolean validateDOB(String dob) {
        // Regular expression to validate Date of Birth in the format YYYY-MM-DD
        String dobRegex = "\\d{4}-\\d{2}-\\d{2}";
        return dob.matches(dobRegex);
    }

    private boolean validateEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean validatePassword(String password) {
        String passwordRegex = "((?=.*[a-z])(?=.*\\d)(?=.*[@#$%])(?=.*[A-Z]).{6,16})";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }


    private String generateAccountNumber() {
        return String.valueOf((int) (Math.random() * 1000000000));
    }
}

class BankOperation extends Frame implements ActionListener {
    private static final long serialVersionUID = 1L;
    String[] accountDetails;
    double balance = 0;

    Label titleLabel;
    Button depositButton, showBalanceButton, withdrawButton, interestButton, exitButton;
    Image backgroundImage;

    public BankOperation(String[] accountDetails) {
        this.accountDetails = accountDetails;
        backgroundImage = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Downloads\\pexels-liliana-drew-8554372.jpg");

        Font titleFont = new Font("Broadway", Font.BOLD, 30);
        Font buttonFont = new Font("Broadway", Font.BOLD, 16);

        setTitle("Bank Operations");
        setSize(700, 500);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        titleLabel = new Label("Select operation:-");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.RED); // Set title text color to red
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        depositButton = new Button("Deposit");
        depositButton.setFont(buttonFont);
        depositButton.setForeground(Color.WHITE); // Set button text color to white
        depositButton.setBackground(Color.BLUE); // Set button background color to blue
        gbc.gridy = 1;
        add(depositButton, gbc);

        showBalanceButton = new Button("Show Bank Balance");
        showBalanceButton.setFont(buttonFont);
        showBalanceButton.setForeground(Color.WHITE); // Set button text color to white
        showBalanceButton.setBackground(Color.BLUE); // Set button background color to blue
        gbc.gridy = 2;
        add(showBalanceButton, gbc);

        withdrawButton = new Button("Withdraw");
        withdrawButton.setFont(buttonFont);
        withdrawButton.setForeground(Color.WHITE); // Set button text color to white
        withdrawButton.setBackground(Color.BLUE); // Set button background color to blue
        gbc.gridy = 3;
        add(withdrawButton, gbc);

        interestButton = new Button("Interest Comparison");
        interestButton.setFont(buttonFont);
        interestButton.setForeground(Color.WHITE); // Set button text color to white
        interestButton.setBackground(Color.BLUE); // Set button background color to blue
        gbc.gridy = 4;
        add(interestButton, gbc);

        exitButton = new Button("Exit");
        exitButton.setFont(buttonFont);
        exitButton.setForeground(Color.WHITE); // Set button text color to white
        exitButton.setBackground(Color.RED); // Set button background color to red
        gbc.gridy = 5;
        add(exitButton, gbc);

        depositButton.addActionListener(this);
        showBalanceButton.addActionListener(this);
        withdrawButton.addActionListener(this);
        interestButton.addActionListener(this);
        exitButton.addActionListener(this);

        // Add mouse hover effect
        MouseAdapter hoverEffect = new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
            }
        };

        depositButton.addMouseListener(hoverEffect);
        showBalanceButton.addMouseListener(hoverEffect);
        withdrawButton.addMouseListener(hoverEffect);
        interestButton.addMouseListener(hoverEffect);
        exitButton.addMouseListener(hoverEffect);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == depositButton) {
            String amount = JOptionPane.showInputDialog(this, "Enter the amount to deposit:");
            try {
                double depositAmount = Double.parseDouble(amount);
                if (depositAmount > 0) {
                    balance += depositAmount;
                    JOptionPane.showMessageDialog(this, "Amount deposited successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid amount!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == showBalanceButton) {
            JOptionPane.showMessageDialog(this, "Your bank balance is: $" + balance);
        } else if (e.getSource() == withdrawButton) {
            if (balance > 0) {
                String amount1 = JOptionPane.showInputDialog(this, "Enter the amount to withdraw:");
                try {
                    double withdrawAmount = Double.parseDouble(amount1);
                    if (withdrawAmount > 0 && withdrawAmount <= balance) {
                        balance -= withdrawAmount;
                        JOptionPane.showMessageDialog(this, "Amount withdrawn successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid amount!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient balance!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == interestButton) {
            String interestAmount = JOptionPane.showInputDialog(this, "Enter the rate of interest:");
            String time = JOptionPane.showInputDialog(this, "Enter the time in years:");
            try {
                double interest = Double.parseDouble(interestAmount);
                double years = Double.parseDouble(time);
                if (interest > 0.0 && interest <= 100.0 && years != 0) {
                    JOptionPane.showMessageDialog(this, "The Interest Amount is " + ((balance * interest * years) / 100));
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter correct details.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException exp) {
                JOptionPane.showMessageDialog(this, "Invalid details", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == exitButton) {
            dispose();
        }
    }
}
