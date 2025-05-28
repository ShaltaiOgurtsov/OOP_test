package Lab5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class CoinGameApp {
    static Random random = new Random();
    private static int coinNumber = random.nextInt(30);
    public static void main(String[] args) {
        StartUpGame();

        JFrame frame = new JFrame("Coin Game");
        frame.setSize(600, 400);
        frame.setLayout(null);

        var remainingCoins = new JLabel(Integer.toString(coinNumber));
        remainingCoins.setBounds(300, 200, 80, 40);
        frame.add(remainingCoins);

        var takeOne = new JButton("Take one");
        takeOne.setBounds(150, 150, 90, 40);
        takeOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                coinNumber -= 1;
                if (coinNumber == 0){
                    remainingCoins.setText("The player has won");
                    return;
                }

                int aiTake = AISimulation.CoinGame(coinNumber);

                coinNumber -= aiTake;

                if (coinNumber == 0){
                    remainingCoins.setText("AI has won");
                    return;
                }


                remainingCoins.setText(String.valueOf(coinNumber));

            }
        });
        frame.add(takeOne);

        var takeTwo = new JButton("Take two");
        takeTwo.setBounds(450, 150, 90, 40);
        takeTwo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                coinNumber -= 2;
                if (coinNumber == 0){
                    remainingCoins.setText("The player has won");
                    return;
                }

                int aiTake = AISimulation.CoinGame(coinNumber);

                coinNumber -= aiTake;

                if (coinNumber == 0){
                    remainingCoins.setText("AI has won");
                    return;
                }


                remainingCoins.setText(String.valueOf(coinNumber));
            }
        });
        frame.add(takeTwo);

        frame.setVisible(true);
        frame.setResizable(false);
    }

    static void StartUpGame(){
        Random random = new Random();
        coinNumber = random.nextInt(20);
    }
}