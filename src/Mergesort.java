import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This program is an implementation of the recursive merge sort algorithm.
 * The sort method sorts an array of ints. 
 * There is a demonstration in the main method of arrays being sorted and printed to the console.
 */
public class Mergesort {

    /**
     * Sorts an array of ints using the merge sort algorithm.
     * 
     * @param arr   Array to be sorted
     * @param a     Left index of the array
     * @param c     Right index of the array
     * @return      Sorted array
     */
    public static void sort(int[] arr, int a, int c) {
        if (a < c) {
            int mid = a + (c - a)/2;
            sort(arr, a, mid);
            sort(arr, mid+1, c);
            merge(arr, a, mid, c);
        }
    }
    
    /**
     * Merges two subarrays of arr[].
     * First subarray is array[a..mid], second subarray is array[mid+1..c]
     */
    private static void merge(int[] arr, int a, int mid, int c) {
        /* Temp arrays */
        int ALength = mid - a + 1;
        int BLength = c - mid;
        int[] A = new int[ALength];
        int[] B = new int[BLength];
        
        /* Fill in temp arrays */
        for (int x = 0; x < ALength; x++) {
            A[x] = arr[a + x]; 
        }
        for (int y = 0; y < BLength; y++) {
            B[y] = arr[mid + y + 1];
        }
        
        /* three pointers, one to go through each array */
        int i = 0;
        int j = 0;
        int k = a;
        
        /* Actual merging */
        for (k = a; k < arr.length; k++) {
            if (A[i] <= B[j]) {
                arr[k] = A[i];
                i++;
                if (!(i < ALength)) break;
            } else if (B[j] < A[i]) {
                arr[k] = B[j];
                j++;
                if (!(j < BLength)) break;
            }
        }
        // make sure k pointer is in the right spot in the array after breaking
        k++;
        
        /* Copy remaining elements of whichever array has leftovers */
        while (i < ALength) {
            arr[k] = A[i];
            i++;
            k++;
        }
        
        while(j < BLength) {
            arr[k] = B[j];
            j++;
            k++;
        }
    }
    
    private static void printIntArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
    
    /**
     * Takes 100,000 numbers from a given file and puts them into an array.
     * 
     * @param fileName  Name of file 
     * @return          Array with 100,000 numbers in it
     */
    private static int[] getArrayFromFile(String fileName) {
        File file = new File(fileName);
        int[] arr = new int[100000];
        int i = 0;

        try {
            Scanner scanner = new Scanner(file);
            
            while (scanner.hasNextLine()) {
                arr[i] = scanner.nextInt();
                i++;
            }
            scanner.close();
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return arr;
    }
    
    /* Demonstration */
    public static void main(String[] args) {
        int[] test1 = {12, 11, 13, 5, 7, 6, 0, 5, 32, 7, 32, 21, 0, 8};
        int[] test2 = {3, 2, 1, 2, 3};
        int[] test3 = {0};
        System.out.println("This is a merge sort demonstration.");
        
        System.out.print("\nInitial array 1: ");
        printIntArray(test1);
        System.out.print("\nSorted array 1: ");
        sort(test1, 0, test1.length - 1);
        printIntArray(test1);
        
        System.out.print("\n\nInitial array 2: ");
        printIntArray(test2);
        System.out.print("\nSorted array 2: ");
        sort(test2, 0, test2.length - 1);
        printIntArray(test2);
        
        System.out.print("\n\nInitial array 3: ");
        printIntArray(test3);
        System.out.print("\nSorted array 3: ");
        sort(test3, 0, test3.length - 1);
        printIntArray(test3);
        
        System.out.println("\n\nGetting 100,000 ints array.");
        int[] hugeArray = getArrayFromFile("IntegerArray.txt");
        sort(hugeArray, 0, hugeArray.length - 1);
        System.out.println("Array has been sorted.");
    }
}
