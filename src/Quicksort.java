import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Quicksort {

    public static int sort(int[] arr, int l, int r) {
        int comparisons = 0;
        if (r - l < 1) return 0;
        int pivotIndex = partition(arr, l, r);
        comparisons += sort(arr, l, pivotIndex - 1);
        comparisons += sort(arr, pivotIndex + 1, r);
        return comparisons + (r - l);
    }
    
    private static int partition(int[] arr, int l, int r) {
        // finds a pivot and swaps it with the first element in the array
        swap(arr, l, choosePivot(arr, l, r));
        int pivotIndex = l;
        
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
     * Chooses the pivot for each new partition. 
     * Utilizes the median-of-three pivot rule.
     * Median is found by putting the three choices into an array and sorting it,
     * then the index of the median of the first, middle, and last number in the array is returned.
     */
    private static int choosePivot(int[] arr, int l, int r) {
        int first = arr[l];
        int last = arr[r];
        int middle = arr[(l+r) / 2];
        int[] medianArr = {first, middle, last};
        MergesortAndCount.sortAndCount(medianArr, 0, medianArr.length - 1);
        if (medianArr[1] == first) return l;
        if (medianArr[1] == last) return r;
        return (l+r) / 2;
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
    
    /**
     * Takes 10,000 numbers from a given file and puts them into an array.
     * 
     * @param fileName  Name of file 
     * @return          Array with 10,000 numbers in it
     */
    private static int[] getArrayFromFile(String fileName) {
        File file = new File(fileName);
        int[] arr = new int[10000];
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
    
    public static void main(String[] args) {
        int[] test1 = {3, 2};
        int[] test2 = {3, 2, 1, 6, 3};
        int[] test3 = {0};
        int[] test4 = {12, 11, 13, 5, 7, 6, 0, 5, 32, 7, 32, 21, 0, 8};
        System.out.println("This is a quick sort demonstration, where the total number of comparisons is counted.");
        
        System.out.print("\nInitial array 1: ");
        printIntArray(test1);
        System.out.println("\nNumber of comparisons: " + Quicksort.sort(test1, 0, test1.length - 1));
        System.out.print("Sorted array 1: ");
        printIntArray(test1);
        
        System.out.print("\n\nInitial array 2: ");
        printIntArray(test2);
        System.out.println("\nNumber of comparisons: " + Quicksort.sort(test2, 0, test2.length - 1));
        System.out.print("Sorted array 2: ");
        printIntArray(test2);
        
        System.out.print("\n\nInitial array 3: ");
        printIntArray(test3);
        System.out.println("\nNumber of comparisons: " + Quicksort.sort(test3, 0, test3.length - 1));
        System.out.print("Sorted array 3: ");
        printIntArray(test3);
        
        System.out.print("\n\nInitial array 4: ");
        printIntArray(test4);
        System.out.println("\nNumber of comparisons: " + Quicksort.sort(test4, 0, test4.length - 1));
        System.out.print("Sorted array 4: ");
        printIntArray(test4);
        
        int numberToPrint = 100;
        String fileName = "QuickSort.txt";
        System.out.println("\n\nGetting 10,000 ints array from file: " + fileName);
        int[] hugeArray = getArrayFromFile(fileName);
        System.out.println("Sorting 10,000 ints.");
        System.out.println("Number of comparisons: " + Quicksort.sort(hugeArray, 0, hugeArray.length - 1));
        System.out.println("Array has been sorted. \nHere are the first " + numberToPrint + " numbers in the sorted array:");
        for (int i = 0; i < numberToPrint; i++) {
            System.out.print(hugeArray[i] + " ");
        }
        
    }
    
}
