import java.util.Arrays;
import java.util.Comparator;

public class OptimalSolutions {

    // Q1: Interval Scheduling - Greedy Algorithm
    static void intervalScheduling(int[] start, int[] end) {
        int n = start.length;
        int[][] jobs = new int[n][2];

        for (int i = 0; i < n; i++) {
            jobs[i][0] = start[i];
            jobs[i][1] = end[i];
        }

        Arrays.sort(jobs, Comparator.comparingInt(a -> a[1]));

        System.out.println("\nOptimal Job Schedule:");
        int lastEndTime = -1;
        for (int[] job : jobs) {
            if (job[0] >= lastEndTime) {
                System.out.println("Job: [" + job[0] + ", " + job[1] + "]");
                lastEndTime = job[1];
            }
        }
    }

    // Q2: Fractional Knapsack - Greedy Algorithm
    static void fractionalKnapsack(int[] weight, int[] value, int capacity) {
        int n = weight.length;
        Item[] items = new Item[n];

        for (int i = 0; i < n; i++) {
            items[i] = new Item(weight[i], value[i]);
        }

        Arrays.sort(items, (a, b) -> Double.compare(b.valuePerWeight(), a.valuePerWeight()));

        double maxCost = 0;
        for (Item item : items) {
            if (capacity >= item.weight) {
                maxCost += item.value;
                capacity -= item.weight;
            } else {
                maxCost += item.valuePerWeight() * capacity;
                break;
            }
        }
        System.out.println("\nMax Cost: " + maxCost);
    }

    static class Item {
        int weight, value;

        Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        double valuePerWeight() {
            return (double) value / weight;
        }
    }

    // Q3: Alternative Method for Interval Scheduling - Dynamic Programming
    static void intervalSchedulingDP(int[] start, int[] end) {
        int n = start.length;
        int[][] dp = new int[n + 1][2];

        for (int i = 1; i <= n; i++) {
            dp[i][0] = start[i - 1];
            dp[i][1] = end[i - 1];
        }

        Arrays.sort(dp, Comparator.comparingInt(a -> a[1]));

        System.out.println("\nOptimal Job Schedule (DP):");
        int lastEndTime = -1;
        for (int i = 1; i <= n; i++) {
            if (dp[i][0] >= lastEndTime) {
                System.out.println("Job: [" + dp[i][0] + ", " + dp[i][1] + "]");
                lastEndTime = dp[i][1];
            }
        }
    }

    public static void main(String[] args) {
        // Test Data
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] end = {2, 4, 6, 7, 9, 9};
        int[] weight = {10, 20, 30};
        int[] value = {60, 100, 120};
        int capacity = 50;

        // Call all methods
        intervalScheduling(start, end);
        fractionalKnapsack(weight, value, capacity);
        intervalSchedulingDP(start, end);
    }
}
