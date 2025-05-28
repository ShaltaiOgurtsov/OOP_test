package Lab6;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class AccountApp {
    private int id;
    private BankApp app;



    public AccountApp(int id, BankApp app){
        this.id = id;
        this.app = app;
        this.startSession();
    }
    public void startSession(){
        BankExecutor executor = new BankExecutor();


        JFrame frame = new JFrame("Account " + AccountManager.getInstance().getAmmount());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel();
        label.setText("Account Balance: " + AccountManager.getInstance().getAccount(id).getBallance());
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton despiButton = new JButton("Despit");
        despiButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(null, "How much do you want to despit", "Despit dialogue", JOptionPane.QUESTION_MESSAGE);
            
            if (input != null) { 
                try {
                    int amount = Integer.parseInt(input);
                    System.out.println("User wants to despit: " + amount);
                    executor.submitAction(new BankAction(ActionType.DEPOSIT, id, Double.parseDouble(input)));
                    javax.swing.SwingUtilities.invokeLater(() -> {
                        label.setText("Account Balance: " + AccountManager.getInstance().getAccount(id).getBallance());
                    });
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please, enter a number", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        despiButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(despiButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));


        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(null, "How much do you want to withdraw", "Withdraw dialogue", JOptionPane.QUESTION_MESSAGE);
            
            if (input != null) { 
                try {
                    int amount = Integer.parseInt(input);
                    System.out.println("User wants to withdraw: " + amount);
                    executor.submitAction(new BankAction(ActionType.WITHDRAW, id, Double.parseDouble(input)));
                    javax.swing.SwingUtilities.invokeLater(() -> {
                        label.setText("Account Balance: " + AccountManager.getInstance().getAccount(id).getBallance());
                    });
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please, enter a number", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        withdrawButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(withdrawButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                executor.submitAction(new BankAction(id));
                SwingUtilities.invokeLater(() -> {
                    app.updateLabelText("Current accounts: " + AccountManager.getInstance().getAmmount());
                });
                frame.dispose();
            }
        }); 

        frame.getContentPane().add(panel);

        frame.setVisible(true);
    }
}
