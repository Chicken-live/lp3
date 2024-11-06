import java.util.Arrays;
// Jobs sequence for maximum profit:
// Job 3 Job 1 Job 5 
// Total profit: 142

class Job {
    int id;      
    int deadline; 
    int profit;  

    public Job(int id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class job {

    public static void jobSequencingWithDeadlines(Job[] jobs, int n) {
        // Sort jobs in descending order of profit
        Arrays.sort(jobs, (a, b) -> (b.profit - a.profit));
        // Arrays.sort(jobs, (a, b) -> {return Double.compare(b.profit, a.profit); }); // also ok

        // Find the maximum deadline to create the schedule array
        int maxDeadline = 0;
        for (int i = 0; i < jobs.length; i++) {
            if (jobs[i].deadline > maxDeadline) {
                maxDeadline = jobs[i].deadline;
            }
        }


        // Array to store result of jobs at particular slots
        int[] result = new int[maxDeadline + 1]; // Index represents time slots
        Arrays.fill(result, -1);  

        // Array to check if a time slot is occupied
        boolean[] slotOccupied = new boolean[maxDeadline + 1];

        // Variables to store the total profit and number of jobs done
        int totalProfit = 0;
        int countJobs = 0;

        // Iterate through all the jobs
        for (int i = 0; i < n; i++) {
            int deadline = jobs[i].deadline;  
            for (int j = deadline; j > 0; j--) {
                if (!slotOccupied[j]) {  
                    result[j] = jobs[i].id;  
                    slotOccupied[j] = true;  
                    totalProfit += jobs[i].profit;  
                    countJobs++;  
                    break;  
                }
            }
        }


        // Output the result
        System.out.println("Jobs sequence for maximum profit:");
        for (int i = 1; i <= maxDeadline; i++) {
            if (result[i] != -1) {
                System.out.print("Job " + result[i] + " ");
            }
        }
        System.out.println("\nTotal profit: " + totalProfit);
    }

    public static void main(String[] args) {
        Job[] jobs = {
            new Job(1, 2, 100),
            new Job(2, 1, 19),
            new Job(3, 2, 27),
            new Job(4, 1, 25),
            new Job(5, 3, 15)
        };

        int n = jobs.length;
        jobSequencingWithDeadlines(jobs, n);
    }
}
