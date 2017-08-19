import java.util.Arrays;

public class Quicksort {

    public static void sort(int[] arr, int l, int r) {
        if (r - l < 1) return;
        // swap pivot to first element if it's not already first element
        int pivotIndex = partition(arr, l, r);
        sort(arr, l, pivotIndex - 1);
        sort(arr, pivotIndex + 1, r);
    }
    
    private static int partition(int[] arr, int l, int r) {
        int pivotIndex = choosePivot(l);
        
        // pointer to first element that is greater than the pivot
        int i = l + 1;
        
        for (int j = l + 1; j <= r; j++) {
            if (arr[j] < arr[pivotIndex]) {
                swap(arr, j, i);
                i++;
            }
        }
        swap(arr, pivotIndex, i - 1);
        return i - 1;
    }
    
    /**
     * Simply chooses the first element in the array for this implementation. 
     * Going to be upgraded to the median-of-three pivot rule soon.
     */
    private static int choosePivot(int l) {
        return l;
    }
    
    /**
     * Swaps element with index "a" with the element at index "b" in a given array.
     * 
     * @param arr   Array in which to swap elements
     */
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    
    private static void printIntArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
    
    public static void main(String[] args) {
        int[] test1 = {3, 2, 5, 1, 4};
//        int[] test1 = {3, 2, 1};
        int[] test2 = {3, 2, 1, 2, 3};
        int[] test3 = {0};
        int[] test4 = {12, 11, 13, 5, 7, 6, 0, 5, 32, 7, 32, 21, 0, 8};
        
        System.out.print("\nInitial array 1: ");
        printIntArray(test4);
        System.out.print("Sorted array 1: ");
        Quicksort.sort(test4, 0, test4.length - 1);
        printIntArray(test4);
        
    }
    
}
