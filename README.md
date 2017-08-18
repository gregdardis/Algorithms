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
This program uses the recursive merge sort algorithm to sort an array of ints, while also counting the number of inversions of the original array. An inversion in an array is defined as such: If A[i], A[j] are two entries in an array A,  i and j are inverted if j > i but A[j] < A[i].
Basically, if a larger number precedes a smaller number in the array, that is an inversion.
For example: {2, 1} has 1 inversion, and {3, 2, 1} has 3 inversions. 

The program doesn't prompt the user to enter any values, it just shows
a short demonstration of sorting four different int arrays and prints how many inversions each original array had.

## Command Line Instructions for Merge Sort and Count

`cd src`

Compile:  
`javac MergesortAndCount.java`  

Run:  
`java MergesortAndCount`



