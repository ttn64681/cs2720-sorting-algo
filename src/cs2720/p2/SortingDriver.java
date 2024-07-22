package cs2720.p2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Runs and tests five different sorting algorithms given a randomly generated input sizes.
 */
public class SortingDriver {

    public static void main(String[] args) {
        // Checks if file is invalid
        if (args.length != 1) {
            System.out.println("Usage: java Sorting <input-file>");
            return;
        } // if
        String file = args[0];
        int[] array = null;
        // Read file input and convert to array
        try {
            array = buildArray(file);
        } catch (IOException io) {
            System.out.println(io.getMessage());
            return;
        } // try
        Scanner scanner = new Scanner(System.in);
        // Print User Interface
        System.out.println("\nselection-sort (s)");
        System.out.println("merge-sort (m)");
        System.out.println("heap-sort (h)");
        System.out.println("quick-sort-fp (q)");
        System.out.println("quick-sort-rp (r)");
        System.out.println("Enter the algorithm:");
        String input = scanner.next().toLowerCase();
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
     * Helper method to read file input of integers and build an array out of it.
     *
     * @param file the file input
     * @return array the array created
     */
    private static int[] buildArray(String file) throws IOException {
        ArrayList<Integer> arrayList = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            // adds line-by-line the integers from the file to an ArrayList
            while ((line = reader.readLine()) != null) {
                String[] numbers = line.split("\\s+");
                for (String number : numbers) {
                    if (!number.trim().isEmpty()) {
                        arrayList.add(Integer.parseInt(number.trim()));
                    } // if
                } // for
            } // while
        } finally {
            if (reader != null) {
                reader.close();
            } // if
        } // try
        // add contents to an array
        int[] array = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            array[i] = arrayList.get(i);
        } // for
        return array;
    } // buildArray

} // Sorting Driver
