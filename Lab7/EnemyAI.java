package Lab7;

public class EnemyAI {
    public static boolean is_winning_move(int[][] board, int x, int y, int symbol){
        int[][] directions = {
            {0, 1},
            {1, 0},
            {1, 1},
            {1, -1}
        };
        
        for (int[] dir : directions){
            int dx = dir[0];
            int dy = dir[1];
            int count = 1;


            int nx = x + dx;
            int ny = y + dy;
            while (nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && board[nx][ny] == symbol) { 
                count++;
                nx += dx;
                ny += dy;
            }

            nx = x - dx;
            ny = y - dy;
            while (nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && board[nx][ny] == symbol) { 
                count++;
                nx -= dx;
                ny -= dy;
            }

            if (count >= 5){
                return true;
            }
        }
        return  false;
    }

    public static int evaluate_score(int[][] board, int x, int y, int symbol){
        int score = 0;
        int[][] directions = {
            {0, 1},
            {1, 0},
            {1, 1},
            {1, -1}
        };
        
        for (int[] dir : directions){
            int dx = dir[0];
            int dy = dir[1];
            int count = 1;


            int nx = x + dx;
            int ny = y + dy;
            while (nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && board[nx][ny] == symbol) { 
                count++;
                nx += dx;
                ny += dy;
            }

            nx = x - dx;
            ny = y - dy;
            while (nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && board[nx][ny] == symbol) { 
                count++;
                nx -= dx;
                ny -= dy;
            }

            score = Math.max(score, count);
        }
        return score;
    }

    public static int[] greedy_ai_search(int[][] board){
        int best_score = -1;
        int[] best_move = null;

        for(int x = 0; x < 5; x++){
            for (int y = 0; y < 5; y++){
                if (board[x][y] != 0){
                    continue;
                }


                board[x][y] = 2;
                if (is_winning_move(board, x, y, 2)){
                    System.out.println("Chose to play for itself ");
                    board[x][y] = 0;
                    return new int[] {x, y};
                }
                board[x][y] = 0;

                
                board[x][y] = 1;
                if (is_winning_move(board, x, y, 1)){
                    System.err.println("Chose to preventplayer from winning");
                    board[x][y] = 0;
                    return new int[] {x, y};
                }
                board[x][y] = 0;


                int score = evaluate_score(board, x, y, 2);
                if (score > best_score){
                    System.out.println("Chose the best score");
                    best_score = score;
                    best_move = new int[] {x, y};
                }
            }
        }
        return best_move;
    }
}
