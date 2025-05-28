package Lab7;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TiTaToApp extends JFrame{
    private GameLogic logic = new GameLogic();
    private JButton[][] buttons = new JButton[5][5];
    private int[][] board = logic.getBoard();

    public TiTaToApp(){
        setTitle("Tic Tac Toe 5x5");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 5));
        setButtons(panel);

        add(panel);
        setVisible(true);
    }

    public void setButtons(JPanel panel){
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                JButton btn = new JButton();
                switch (board[i][j]){
                    case 0 -> {
                        btn.setText("");
                    }
                    case 1 -> {
                        btn.setText("X");
                    }
                    case 2 -> {
                        btn.setText("O");
                    }
                }

                buttons[i][j] = btn;
                btn.addActionListener(new CellClickListener(i, j));
                panel.add(btn);
            }
        }
    }

    private class CellClickListener implements ActionListener{
        private int row, col;

        public CellClickListener(int r, int c){
            this.row = r;
            this.col = c;
        }

        @Override
        public void actionPerformed(ActionEvent e){
            JButton btn = buttons[row][col];
            if (btn.getText() == "");
                btn.setText("X");
                board[row][col] = 1;

                int[] enemyTurn = EnemyAI.greedy_ai_search(board);

                JButton new_btn = buttons[enemyTurn[0]][enemyTurn[1]];
                new_btn.setText("O");
                board[enemyTurn[0]][enemyTurn[1]] = 2;
            
            int gameState = logic.checkWinstate();

            switch(gameState){
                case 1 -> {
                    JOptionPane.showInputDialog(null, "The player has won");
                }
                case 2 -> {
                    JOptionPane.showInputDialog(null, "The ai has won");
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TiTaToApp());   
    }
}
