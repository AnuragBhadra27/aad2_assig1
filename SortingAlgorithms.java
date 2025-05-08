import java.util.Arrays;
import java.util.Scanner;

public class SortingAlgorithms {

    // Merge Sort
    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    public static void merge(int[] array, int left, int mid, int right) {
        int[] leftArr = Arrays.copyOfRange(array, left, mid + 1);
        int[] rightArr = Arrays.copyOfRange(array, mid + 1, right + 1);

        int i = 0, j = 0, k = left;

        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                array[k++] = leftArr[i++];
            } else {
                array[k++] = rightArr[j++];
            }
        }

        while (i < leftArr.length) {
            array[k++] = leftArr[i++];
        }

        while (j < rightArr.length) {
            array[k++] = rightArr[j++];
        }
    }

    // Quick Sort
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);

            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    public static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                // Swap
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // Swap pivot
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    // Main method with switch-case
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = scanner.nextInt();

        int[] arr = new int[n];
        System.out.println("Enter elements:");

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println("Choose sorting algorithm:");
        System.out.println("1. Merge Sort");
        System.out.println("2. Quick Sort");
        int choice = scanner.nextInt();

        long startTime, endTime;

        switch (choice) {
            case 1:
                startTime = System.nanoTime();
                mergeSort(arr, 0, arr.length - 1);
                endTime = System.nanoTime();
                System.out.println("Sorted using Merge Sort: " + Arrays.toString(arr));
                System.out.println("Time taken: " + (endTime - startTime) / 1_000_000.0 + " ms");
                System.out.println("Time Complexity: O(n log n)");
                break;

            case 2:
                startTime = System.nanoTime();
                quickSort(arr, 0, arr.length - 1);
                endTime = System.nanoTime();
                System.out.println("Sorted using Quick Sort: " + Arrays.toString(arr));
                System.out.println("Time taken: " + (endTime - startTime) / 1_000_000.0 + " ms");
                System.out.println("Average Time Complexity: O(n log n)");
                break;

            default:
                System.out.println("Invalid choice.");
        }

        scanner.close();
    }
}
