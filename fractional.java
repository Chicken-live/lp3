import java.util.Arrays;
import java.util.Comparator;

// Class to represent an item with a value and weight
class Item {
    double value;  
    double weight;

    Item(double value, double weight) {
        this.value = value;
        this.weight = weight;
    }
}

public class fractional {

    public static double getMaxValue(Item[] items, double capacity) {
        

        Arrays.sort(items, (a, b) -> (int)((b.value / b.weight) - (a.value / a.weight)) );

        double totalValue = 0.0;

        for (int i = 0; i < items.length; i++) { // Loop through each item
            Item item = items[i]; // Get the current item
            // Check if the knapsack is already full
            if (capacity <= 0) {
                break; 
            }

            // Check if the entire item can fit into the knapsack
            if (item.weight <= capacity) {
                totalValue += item.value; 
                capacity -= item.weight; 
            } else {
                // If the item can't fit entirely, take the fraction that fits
                totalValue += (item.value/item.weight) * (capacity); 
                // Set capacity to zero since the knapsack is now full
                capacity = 0;
            }
        }


        return totalValue; 
    }

    public static void main(String[] args) {
        // Create an array of items (value, weight)
        Item[] items = {
            new Item(60, 10),  
            new Item(100, 20), 
            new Item(120, 30) 
        };

        double capacity = 50; // Maximum weight capacity of the knapsack
        double maxValue = getMaxValue(items, capacity); // Calculate max value

        System.out.println("Maximum value in the Knapsack = " + maxValue);
    }
}


