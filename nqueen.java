public class nqueen {
    private static final int N = 8; 
    private static int[][] board = new int[N][N];
    
    public static void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isSafe(int row, int col) {
        // Check the same column upwards
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }

        // Check the upper left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // Check the upper right diagonal
        for (int i = row, j = col; i >= 0 && j < N; i--, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    private static boolean solve8Queens(int row) {
        // Base case: If all queens are placed, return true
        if (row >= N) {
            return true;
        }

        // Try placing a queen in each column of the current row
        for (int col = 0; col < N; col++) {
            // Check if placing the queen at board[row][col] is safe
            if (isSafe(row, col)) {
                // Place the queen
                board[row][col] = 1;

                // Recur to place the rest of the queens
                if (solve8Queens(row + 1)) {
                    return true;
                }

                // If placing queen at board[row][col] doesn't lead to a solution, backtrack
                board[row][col] = 0;
            }
        }

        return false;
    }

    
    public static void main(String[] args) {
        board[0][0] = 1;

        if (solve8Queens(1)) {
            System.out.println("Solution to the 8-Queens problem:");
            printBoard();
        } else {
            System.out.println("No solution found.");
        }
    }
    
}
