package Lab6;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BankApp {
    private JFrame frame;
    private JLabel label;
    public BankApp() {
        BankExecutor executor = new BankExecutor();
        JFrame frame = new JFrame("Banking");
        frame.setSize(300, 100);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.label = new JLabel("Account ammount " + AccountManager.getInstance().getAmmount());
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton creationButton = new JButton("Open account");
        creationButton.addActionListener(e -> {
            executor.submitAction(new BankAction());
            AccountApp accountApp = new AccountApp(AccountManager.getInstance().getLastId(), this);
            this.label.setText("Account ammoount " + AccountManager.getInstance().getAmmount());
        });
        creationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(creationButton);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        frame.getContentPane().add(panel);

        frame.setVisible(true);
    }

    public void updateLabelText(String text){
        SwingUtilities.invokeLater(() -> {
            label.setText(text);
        });
    }

    public static void main(String[] args) {
        BankApp app = new BankApp();
    }
}
