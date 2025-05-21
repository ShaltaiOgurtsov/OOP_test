package Lab3;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class GameOfLife extends JPanel {
    private static final int CELL_SIZE = 6;
    private int rows;
    private int cols;
    private int[][] grid;

    public GameOfLife(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new int[rows][cols];
        setPreferredSize(new Dimension(cols * CELL_SIZE, rows * CELL_SIZE));
    }

    public void initializeGrid() {
        Random rand = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = rand.nextInt(2);
            }
        }
    }

    private int countNeighbours(int row, int col) {
        int livingNeighbours = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int newRow = row + i;
                int newCol = col + j;
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                    livingNeighbours += grid[newRow][newCol];
                }
            }
        }
        return livingNeighbours;
    }

    public void updateGrid() {
        int[][] newGrid = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int aliveNeighbours = countNeighbours(i, j);
                if (grid[i][j] == 1) {
                    if (aliveNeighbours < 2 || aliveNeighbours > 3) {
                        newGrid[i][j] = 0;
                    } else {
                        newGrid[i][j] = 1;
                    }
                } else {
                    if (aliveNeighbours == 3) {
                        newGrid[i][j] = 1;
                    }
                }
            }
        }
        grid = newGrid;
        repaint(); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    g.setColor(Color.GREEN); 
                } else {
                    g.setColor(Color.BLUE); 
                }
                g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.GRAY);
                g.drawRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    public static void main(String[] args) {
        int setCols = 200;
        int setRows = 150;
        GameOfLife game = new GameOfLife(setRows, setCols);
        JFrame frame = new JFrame("Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.pack(); 
        frame.setVisible(true);

        game.initializeGrid(); 

        Timer timer = new Timer(30, e -> game.updateGrid()); 
        timer.start();
    }
}