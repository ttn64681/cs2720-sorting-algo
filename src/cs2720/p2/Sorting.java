package cs2720.p2;

import java.util.Random;

/**
 * Class that contains multiple sorting algorithms.
 */
public class Sorting {

    private static long comparison;

    /**
     * Helper method to retrieve the comparison count.
     *
     * @return comparison the comparison count
     */
    public static long getCount() {
        return comparison;
    } // getCount

    /**
     * Helper method to reset the comparison count.
     */
    public static void resetCount() {
        comparison = 0;
    } // getCount

    /**
     * Helper method to print array.
     *
     * @param array the given array to print.
     */
    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(i + " ");
        } // for
    } // printArray

    /**
     * Selection sort algorithm: search through an array and keep track of the minimum value
     * during each iteration. At the end of each iteration, swap values.
     *
     * O(n^2)
     * small data set = okay
     * large data set = BAD
     *
     * @param array the array to sort
     */
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                comparison++;
                if (array[min] > array[j]) {
                    min = j;
                } // int
            } // for

            int temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        } // for
    } // selectionSort

    /**
     * Merge sort algorithm: recursively divide array in 2, sort, re-combine.
     *
     * O(nlogn)
     * space complexity O(n)
     * @param array the array to sort
     */
    public static void mergeSort(int[] array) {
        int length = array.length;
        if (length <= 1) {
            return;
        } //base case

        int middle = length / 2;
        int[] leftArray = new int[middle];
        int[] rightArray = new int[length - middle];

        int i = 0; //left array
        int j = 0; //right array

        for (; i < length; i++) {
            if (i < middle) {
                leftArray[i] = array[i];
            } else {
                rightArray[j] = array[i];
                j++;
            } // if
        } // for
        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(leftArray, rightArray, array);
    } // mergeSort

    /**
     * Helper method to merge the left and right half of a given array while ordering
     * them in the process.
     *
     * @param leftArray the left partition of the given array
     * @param rightArray the right partition of the given array
     * @param array the given array
     */
    private static void merge(int[] leftArray, int[] rightArray, int[] array) {
        int leftSize = array.length / 2;
        int rightSize = array.length - leftSize;
        int i = 0, l = 0, r = 0; //indices

        //check the conditions for merging
        while (l < leftSize && r < rightSize) {
            comparison++;
            if (leftArray[l] < rightArray[r]) {
                array[i] = leftArray[l];
                i++;
                l++;
            } else {
                array[i] = rightArray[r];
                i++;
                r++;
            } // if
        } // while
        while (l < leftSize) {
            array[i] = leftArray[l];
            i++;
            l++;
        }
        while (r < rightSize) {
            array[i] = rightArray[r];
            i++;
            r++;
        } // while
    } // merge

    /**
     * Heap sort algorithm: organizes elements in an array into a binary heap. then sorts
     * that heap by moving the largest element in the array.
     *
     * O(nlogn)
     * @param arr the given array
     */
    public static void heapSort(int[] arr) {
        int N = arr.length;

        // Build heap (rearrange array)
        for (int i = N / 2 - 1; i >= 0; i--) {
            heapify(arr, N, i);
        } // for
        // One by one extract an element from heap
        for (int i = N - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        } // for
    } // heapSort

    /**
     * Helper method to heapify a subtree rooted with node i which is an index in arr[].
     * n is size of heap.
     *
     * @param arr the given array
     * @param N the size of the heap
     * @param i the index of rooted node of subtree
     */
    private static void heapify(int[] arr, int N, int i) {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        comparison++;
        // If left child is larger than root
        if (l < N && arr[l] > arr[largest]) {
            largest = l;
        } // if

        comparison++;
        // If right child is larger than largest so far
        if (r < N && arr[r] > arr[largest]) {
            largest = r;
        } // if
        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, N, largest);
        } // if
    } // heapify

    /**
     * Quick sort algorithm: moves smaller elements to left of a pivot, recursively divide
     * array in 2 partitions. Pivot is the first element.
     *
     * best-case: O(nlogn)
     * worst-case: O(n^2) if already sorted
     * average-case: O(nlogn)
     *
     * @param arr the given array to sort
     * @param low the starting index
     * @param high the ending index
     */
    public static void quickSort(int[] arr, int low, int high) {
        // If low is lesser than high
        if (low < high) {
            // idx is index of pivot element which is at its
            // sorted position
            int idx = partition(arr, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSort(arr, low, idx - 1);
            quickSort(arr, idx + 1, high);
        } // if
    } // quickSort

    /**
     * Quick sort algorithm: moves smaller elements to left of a pivot, recursively divide
     * array in 2 partitions. Pivot is a random element.
     *
     * best-case: O(nlogn)
     * worst-case: O(n^2) if already sorted
     * average-case: O(nlogn)
     *
     * @param arr the given array to sort
     * @param low the starting index
     * @param high the ending index
     */
    public static void quickSortRandPiv(int[] arr, int low, int high) {
        if (low < high) {
            /* pi is partitioning index, arr[pi] is
            now at right place */
            int pi = partitionRandPiv(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            quickSortRandPiv(arr, low, pi-1);
            quickSortRandPiv(arr, pi+1, high);
        } // if
    } // quickSortRandPiv

    /**
     * Helper method for quickSort to swap two elements.
     *
     * @param arr the given array
     * @param i the first index of an element
     * @param j the other index of an element
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    } // swap

    /**
     * This function takes first element as pivot, places the pivot element at its correct
     * position in sorted array, and places all smaller to left of pivot and all greater
     * elements to right of pivot.
     *
     * @param arr the given array to partition
     * @param low the starting index
     * @param high the ending index
     * @return k the pivot element index (end)
     */
    private static int partition(int[] arr, int low, int high) {
        // First element as pivot
        int pivot = arr[low];
        int st = low; // st points to the starting of array
        int end = high; // end points to the ending of the array
        int k = high;
        for (int i = high; i > low; i--) {
            comparison++;
            if (arr[i] > pivot) {
                swap(arr, i, k--);
            } // if
        } // for
        swap(arr, low, k);
        // As we got pivot element index is end
        // now pivot element is at its sorted position
        // return pivot element index (end)
        return k;
    } // partition

    /**
     * This function takes a random element as pivot, places the pivot element at its correct
     * position in sorted array, and places all smaller to left of pivot and all greater
     * elements to right of pivot.
     *
     * @param arr the given array to partition
     * @param low the starting index
     * @param high the ending index
     * @return k the pivot element index (end)
     */
    private static int partitionRandPiv(int[] arr, int low, int high) {
        // pivot is chosen randomly
        random(arr,low,high);
        int pivot = arr[high];

        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            comparison++;
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] < pivot) {
                i++;

                // swap arr[i] and arr[j]
                swap(arr, i, j);
            } // if
        } // if

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    } // partitionRandPiv


    /**
     * This Function helps in calculating random numbers between low(inclusive)
     * and high(inclusive).
     *
     * @param arr the given array
     * @param low the starting index
     * @param high the ending index
     */
    private static void random(int arr[],int low,int high) {
        Random rand = new Random();
        int pivot = rand.nextInt(high - low) + low;

        int temp1 = arr[pivot];
        arr[pivot] = arr[high];
        arr[high] = temp1;
    } // random

} // Sorting
