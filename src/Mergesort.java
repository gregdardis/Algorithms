
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
        /* three pointers, one to go through each array */
        int i = 0;
        int j = 0;
        int k = 0;
       
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
        
        /* Actual merging */
        for (k = a; k < arr.length; k++) {
            if (A[i] <= B[j]) {
                arr[k] = A[i];
                i++;
                if (i == (ALength - 1)) break;
            } else if (B[j] < A[i]) {
                arr[k] = B[j];
                j++;
                if (j == (BLength - 1)) break;
            }
        }
        
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
    
    public static void main(String[] args) {
        
    }
}
