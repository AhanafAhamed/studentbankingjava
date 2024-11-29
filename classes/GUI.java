package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI {

    public static Account login(ArrayList<Account> accounts,String name, String pass) throws NullPointerException{
        for (Account account: accounts){
            if((account.getName()).equals(name) && account.checkPass(pass)){ //Replace with hashing
                return account;
            }
        }
        throw new NullPointerException();

    }
    public static void displayLogin(ArrayList<Account> accounts){
        JFrame frame = new JFrame("Login Screen");
        JPanel panel = new JPanel(new FlowLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton loginButton = new JButton("Login");
        frame.setLayout(new BorderLayout());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setSize(350,400);


        JLabel userLabel = new JLabel("Username: ");
        JTextField userText = new JTextField(20);
        panel.add(userLabel);
        panel.add(userText);

        JLabel passLabel = new JLabel("Password: ");
        JPasswordField passField = new JPasswordField(20);
        panel.add(passLabel);
        panel.add(passField);

        buttonPanel.add(loginButton);

        frame.setVisible(true);


        loginButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Account account = GUI.login(accounts, userText.getText(), passField.getText());
                    GUI.displayAccount(account);
                    frame.dispose(); //Logic to be added later
                }
                catch(Exception e1){
                    Dialog warning = new Dialog(frame,"Invalid Credentials!", false);
                    warning.setSize(300,150);
                    Button closeButton = new Button("Close");
                    closeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            warning.setVisible(false);  // Hides the dialog
                        }
                    });
                    warning.add(closeButton);
                    warning.setVisible(true);
                }
            }
        });
    }

    public static void displayAccount(Account account){
        JFrame frame = new JFrame(account.getName() + "'s Account");
        frame.setLayout(new BorderLayout());
        JPanel infoPanel = new JPanel(new FlowLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(3,1));
        JLabel balanceLabel = new JLabel("Balance: "+String.valueOf(account.getBalance()));

        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton transferButton = new JButton("Transfer");
        JButton tranHisButton = new JButton("View Transaction History");
        JButton logoutButton = new JButton("Log Out");

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayDeposit(frame,balanceLabel,account);
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayWithdraw(frame,balanceLabel,account);

            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(infoPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        infoPanel.add(balanceLabel);
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(transferButton);
        buttonPanel.add(tranHisButton);
        buttonPanel.add(logoutButton);
        frame.setSize(350,400);

        frame.setVisible(true);
    }

    public static void displayWithdraw(JFrame frame, JLabel balanceLabel, Account account) {
        JDialog withdraw = new JDialog(frame, "Enter withdraw amount");
        withdraw.setSize(300, 150);
        JButton confirmButton = new JButton("Confirm");
        JLabel actionLabel = new JLabel("Enter Amount:");
        JTextField amountField = new JTextField(20);

        withdraw.add(actionLabel, BorderLayout.NORTH);
        withdraw.add(amountField, BorderLayout.CENTER);
        withdraw.add(confirmButton, BorderLayout.SOUTH);

        withdraw.setVisible(true);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                account.withdraw(amount);
                balanceLabel.setText("Balance: " + String.valueOf(account.getBalance()));
                withdraw.setVisible(false);
                withdraw.dispose();
            }
        });
    }

    public static void displayDeposit(JFrame frame, JLabel balanceLabel, Account account){
        JDialog deposit = new JDialog(frame,"Enter deposit amount");
        deposit.setSize(300,150);
        JButton confirmButton = new JButton("Confirm");
        JLabel actionLabel = new JLabel("Enter Amount:");
        JTextField amountField = new JTextField(20);

        deposit.add(actionLabel, BorderLayout.NORTH);
        deposit.add(amountField, BorderLayout.CENTER);
        deposit.add(confirmButton, BorderLayout.SOUTH);

        deposit.setVisible(true);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                account.deposit(amount);
                balanceLabel.setText("Balance: "+String.valueOf(account.getBalance()));
                deposit.setVisible(false);
                deposit.dispose();
            }
        });

    }

    /*
    void static CashApp displayTransferWindow(Account account){
        String mobileNo;
        String name;
    }
     */

}
