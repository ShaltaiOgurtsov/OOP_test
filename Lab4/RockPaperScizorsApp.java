package Lab4;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent; 
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RockPaperScizorsApp {
    static int turns;
    static List<String> usedMoves = new ArrayList<>();
    static int[] score = new int[] {0, 0};

    private static void TurnDecidorWindow(){
        JFrame frame = new JFrame("Turn Selector");
        frame.setSize(300, 150);

        var textField = new JTextField();
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(!Character.isDigit(c)){
                    e.consume();
                }
            }
        });
        frame.add(textField);

        var startButton = new JButton("Chose");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                GameWindow();
            }
        });

        frame.setVisible(true);
        frame.setResizable(false);
    }

    private static void GameWindow(){
        JFrame frame = new JFrame("Rock Paper Scizors!!!");
        frame.setLayout(null);
        frame.setSize(600, 400);

        var label1 = new JLabel("Your option");
        label1.setBounds(50, 200, 70, 30);
        frame.add(label1);

        var label2 = new JLabel("Opponent option");
        label2.setBounds(450, 200, 70, 30);
        frame.add(label2);


        String[] options = {"Rock", "Paper", "Scizors"};
        JComboBox<String> combox = new JComboBox<String>(options);
        combox.setBounds(50, 120, 70, 30);
        frame.add(combox);

        String[] modes = new String[] {"Random", "Momentary", "Global"};
        JComboBox<String> modeSelectBox = new JComboBox<>(modes);
        modeSelectBox.setBounds(50, 40, 70, 30);
        frame.add(modeSelectBox);

        var scoreLabel = new Label("Score");
        scoreLabel.setBounds(250, 40, 70, 30);
        frame.add(scoreLabel);


        var scoreCount = new Label("0 : 0");
        scoreCount.setBounds(250, 60, 70, 30);
        frame.add(scoreCount);

        var playButton = new JButton("Chose");
        playButton.setBounds(50, 300, 70, 30);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String playersChoise = combox.getSelectedItem().toString();
                String mode = modeSelectBox.getSelectedItem().toString();
                String AIChoise;

                System.out.println(mode);

                if (mode == "Random"){
                    AIChoise = AISimulation.RandomAI();
                } 
                else if (mode == "Momentary"){
                    AIChoise = AISimulation.GetTheMostFrequant(usedMoves);
                }
                else if (mode == "Global"){
                    ArrayList<String> IOmoves = IOStuff.GetArray();
                    AIChoise = AISimulation.GetTheMostFrequant(usedMoves);
                }
                else {
                    AIChoise = "Placeholder";
                }

                label1.setText(playersChoise);
                label2.setText(AIChoise);

                int[] results = GameSimulation.Simulate(playersChoise, AIChoise);

                usedMoves.add(playersChoise);
                IOStuff.AddToFile(playersChoise);

                score[0] += results[0];
                score[1] += results[1];

                String resScore = score[0] + " : " + score[1];

                scoreCount.setText(resScore);
            }
        });
        frame.add(playButton);

        frame.setVisible(true);
        frame.setResizable(false);
    }

    public static void main(String[] args) {
        GameWindow();
    }
}
