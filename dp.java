
public class dp{

    public static int maxValue(int wt[], int val[], int capacity){
        int row = wt.length+1;
        int wt_col = capacity+1;
        int dp[][] = new int[row][wt_col];

        for(int i=1; i<row; i++){
            for(int w=0; w<=capacity; w++){
                if(w >= wt[i-1]){
                    dp[i][w] = Math.max( (val[i-1]+dp[i-1][w-(wt[i-1])]), dp[i-1][w]);
                }
                else{
                    dp[i][w] = dp[i-1][w];
                }

            }
        }

        printMatrix(dp);
        return dp[wt.length][capacity];
    }

    public static void printMatrix(int mat[][]){
        for(int i=0; i<mat.length; i++){
            for(int j=0; j<mat[0].length; j++){
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

    }



    public static void main(String args[]){
        System.out.println("Knapsack(0/1) using Dynamic Programming");
        int wt[] = {3, 4, 5, 6};
        int val[] = {2, 3, 4, 1};
        int capacity = 8;

        int res = maxValue(wt, val, capacity);
        System.out.println("Max possible value of 0/1 Knapsack : " + res);



    }
}












