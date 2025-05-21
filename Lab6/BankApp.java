package Lab6;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.concurrent.ExecutionException;
import jdk.jshell.spi.ExecutionControl;


public class BankApp {
    public static void LoginScreen(){
        JFrame frame = new JFrame("Login screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));

        JLabel nameLabel = new JLabel("Name: ");
        panel.add(nameLabel);

        JTextField nameTextField = new JTextField();
        panel.add(nameTextField);

        JLabel moneyLabel = new JLabel("Money: ");
        panel.add(moneyLabel);

        JTextField monTextField = new JTextField();
        panel.add(monTextField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("CHEEEEEEECKEEEEEEEED");
                String accountName = nameTextField.getName();
                int accountMoney = Integer.parseInt(monTextField.getText());

                AccountManager(new Account(accountMoney, accountName));
            }
        });
        panel.add(submitButton);

        frame.getContentPane().add(panel);

        frame.setVisible(true);
        frame.setResizable(false);
    }

    public static  void AccountManager(Account account){
        JFrame frame = new JFrame("Account info");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));

        JLabel accountNamLabel = new JLabel(account.getName());
        panel.add(accountNamLabel);

        JLabel accountMoneyLabel = new JLabel("Ballance: " + account.getMoney());
        panel.add(accountMoneyLabel);

        JLabel accountIsOpenLabel = new JLabel(account.isOpen() ? "Open" : "Close");
        panel.add(accountIsOpenLabel);

        JButton despitButton = new JButton("Despit (+500)");
        panel.add(despitButton);

        JButton withdrawButton = new JButton("Withdraw (-300)");
        panel.add(withdrawButton);

        JButton toggleStatusButton = new JButton("Toggle status");
        panel.add(toggleStatusButton);

        JButton refreshButton = new JButton("Refresh");
        panel.add(refreshButton); 

        frame.getContentPane().add(panel);

        frame.setVisible(true);
        frame.setResizable(false);
    }



    public static void performAsyncAction(Runnable action){
        setButtonsEnables(false);

        SwingWorker <Void, Void> worker = new SwingWorker<Void,Void>() {
            @Override
            protected Void doInBackground() throws InterruptionException {
                Thread.sleep(1000);
                action.run();
                return null;
            }

            @Override
            protected void done(){
                try {
                    get();
                    setButtonsEnabled(true);
                } catch (InternalException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };

        worker.execute();
    }

    
    public static void main(String[] args) {
        LoginScreen();
    }
}
