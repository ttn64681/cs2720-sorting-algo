package cs2720.p2;

import java.util.Scanner;
import java.util.Random;

/**
 * Runs and tests five different sorting algorithms given a randomly generated input sizes.
 */
public class SortingDriver {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        // Prompt size and print User Interface
        System.out.print("Enter size of array: ");
        int size = scanner.nextInt();
        System.out.println("\nselection-sort (s)");
        System.out.println("merge-sort (m)");
        System.out.println("heap-sort (h)");
        System.out.println("quick-sort-fp (q)");
        System.out.println("quick-sort-rp (r)");
        System.out.println("Enter the algorithm:");
        String input = scanner.next().toLowerCase();
        // build array given a size
        int[] array = buildArray(size);
        int length = array.length;
        // initialize comparison count
        Sorting.resetCount();

        switch (input) {
        case "s":
            Sorting.selectionSort(array);
            Sorting.printArray(array);
            System.out.println("\n#Selection-sort Comparisons: " + Sorting.getCount());
            break;
        case "m":
            Sorting.mergeSort(array);
            Sorting.printArray(array);
            System.out.println("\n#Merge-sort Comparisons: " + Sorting.getCount());
            break;
        case "h":
            Sorting.heapSort(array);
            Sorting.printArray(array);
            System.out.println("\n#Heap-sort Comparisons: " + Sorting.getCount());
            break;
        case "q":
            Sorting.quickSort(array, 0, length - 1);
            Sorting.printArray(array);
            System.out.println("\n#Quick-sort-fp Comparisons: " + Sorting.getCount());
            break;
        case "r":
            Sorting.quickSortRandPiv(array, 0, length - 1);
            Sorting.printArray(array);
            System.out.println("\n#Quick-sort-rp Comparisons: " + Sorting.getCount());
            break;
        default:
            System.out.println("Invalid choice");
            return;
        } // switch

    } // main

    /**
     * Helper method to build array given an inputted size.
     *
     * @param size the inputted size of array
     * @return array the array created
     */
    private static int[] buildArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        // fill array with random values
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size);
        } // for
        return array;
    } // buildArray

} // Sorting Driver
