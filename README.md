***All programs made using Eclipse, version: Neon.2 Release (4.6.2)*.**

# Karatsuba Multiplication
## Description
This program uses the recursive Karatsuba multiplication algorithm to compute the product of two numbers. These numbers can be huge due to the use of the BigInteger class. 

The program repeatedly prompts the user to enter two numbers, then prints the product to the console. 
If the user enters -1 as one of the numbers, the program terminates.

## Command Line Instructions for Karatsuba Multiplication

`cd src/`

Compile:  
`javac Karatsuba.java`

Run:  
`java Karatsuba`  
  
  
   
# Merge Sort and Count
## Description
This program uses the recursive Merge Sort algorithm to sort an array of ints, while also counting the number of inversions of the original array.  

An inversion in an array is defined as such: If A[i], A[j] are two entries in an array A,  i and j are inverted if j > i but A[j] < A[i].  
Basically, if a larger number precedes a smaller number in the array, that is an inversion.
For example: {2, 1} has 1 inversion, and {3, 2, 1} has 3 inversions. 

The program doesn't prompt the user to enter any values, it just shows
a short demonstration of sorting four different int arrays and prints how many inversions each original array had.

## Command Line Instructions for Merge Sort and Count

`cd src/`

Compile:  
`javac MergesortAndCount.java`  

Run:  
`java MergesortAndCount`  




# Quick Sort
## Description
This program uses the recursive Quick Sort algorithm to sort an array of ints, while also counting the number of comparisons made while sorting. The pivot is chosen using the median-of-three pivot rule.  
Note: The comparisons made while choosing the pivot using the median-of-three rule are not counted.  

The median-of-three rule chooses the pivot by considering the first, middle and last element of the given array; the median of these three values is the pivot. The median element is swapped with the first element of the given array, and that median element at index 0 is now the pivot.  
Note: In the array [1, 2, 3, 4] the middle element is the 2.  

The program doesn't prompt the user to enter any values, it just shows
a short demonstration of sorting four different int arrays and prints how many comparisons the algorithm had to make in order to sort each array.  

## Command Line Instructions for Quick Sort

`cd src/`

Compile:  
`javac QuickSort.java`  

Run:  
`java QuickSort` 





