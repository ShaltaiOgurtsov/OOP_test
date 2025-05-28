package Lab7;

public class GameLogic {
    private int[][] board;

    public GameLogic(){
        this.board = new int[5][5];
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                this.board[i][j] = 0;
            }
        }
    }

    public int[][] getBoard(){
        return this.board;
    }

    public GameLogic(boolean x){
        int[][] board = {
            {1, 0, 0, 0, 0},
            {2, 2, 2, 0, 2},
            {0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 0, 0, 0, 1}
        };

        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        int[] pixels = EnemyAI.greedy_ai_search(board);

        board[pixels[0]][pixels[1]] = 2;

        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println(pixels[0] + " " + pixels[1]);
        this.board = board;
    }

    public int checkWinstate(){
        int size = this.board.length;
        int[][] directions = {
            {0, 1},
            {1, 0},
            {1, 1},
            {1, -1}
        };

        for (int x = 0; x < size; x++){
            for (int y = 0; y < size; y++){
                for (int[] dir : directions){
                    int dx = dir[0], dy = dir[1];
                    int player_count = 1;
                    int ai_count = 1;
                    int nx = x + dx;
                    int ny = y + dy;
                    
                    while (nx >= 0 && nx < size && ny >= 0 && ny < size && this.board[nx][ny] == 1){
                        player_count++;
                        if (player_count == 5){
                            return 1;
                        }
                        nx += dx;
                        ny += dy;
                    }

                    while(nx >= 0 && nx < size && ny >= 0 && ny < size && this.board[nx][ny] == 2){
                        ai_count++;
                        if (ai_count == 5){
                            return 2;
                        }
                        nx += dx;
                        ny += dy;
                    }
                }
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        GameLogic logic = new GameLogic(true);
    }
}

