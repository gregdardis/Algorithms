
public class Mergesort {

    /**
     * Sorts an array using the mergesort algorithm.
     * 
     * @param arr   Array to be sorted
     * @param a     Left index of the array
     * @param c     Right index of the array
     * @return      Sorted array
     */
    public static void sort(int[] arr, int a, int c) {
        if (a < c) {
            // mid = middle of array
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
    
    public static void main(String[] args) {
        int test[] = {12, 11, 13, 5, 7, 6, 0, 999, 32, 32, 32, 21, 13, 8};
        int test2[] = {12, 11, 13, 5};
        sort(test, 0, test.length - 1);
        printIntArray(test);
    }
}
