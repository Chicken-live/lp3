public class fibonacci {
    
    public static int fibonacciNum(int n, int[] stepCount) {
        // base cases
        if (n == 0) {
            stepCount[0]++; 
            return 0;
        }
        if (n == 1) {
            stepCount[0]++; 
            return 1;
        }
        
        // Variables to store the previous two Fibonacci numbers
        int a = 0, b = 1;
        int fib = 1;
        
        // Iterative loop to calculate the nth Fibonacci number
        for (int i = 2; i <= n; i++) {
            fib = a + b;
            a = b;
            b = fib;
            stepCount[0]++;
        }
        
        return fib;
    }

    public static int fibonacciRecurs(int n){
        if(n==0 || n==1){
            return n;
        }

        int fnm1 = fibonacciRecurs(n-1);
        int fnm2 = fibonacciRecurs(n-2);

        int fn = fnm1+fnm2;

        return fn; 


    }
    
    public static void main(String[] args) {
        int n = 4; // nth Fibonacci number (0th indexed count)
        int[] stepCount = {0}; // To store the number of steps taken
        
        int result = fibonacciNum(n, stepCount);
        
        // Print the Fibonacci number and the step count
        System.out.println("Fibonacci number at position " + n + " is: " + result);
        System.out.println("Steps taken to calculate the Fibonacci number: " + stepCount[0]);


        System.out.println("fibonacci num : " + fibonacciRecurs(7));
    
    }
}
