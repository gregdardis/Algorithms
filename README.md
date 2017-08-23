***All programs made using Eclipse, version: Neon.2 Release (4.6.2)*.**

# __***Karatsuba Multiplication***__ 
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
  
  
   
# __***Merge Sort and Count***__
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




# __***Quick Sort***__
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



# __***Karger's Minimum Cut Algorithm***__

## Description

  This program is an implementation of Karger's algorithm, which is used to compute a 
  minimum cut of a connected graph. This algorithm is randomized, so the edges
  which are chosen to be "contracted" (next paragraph) are chosen randomly on every run of the algorithm.
 
 The algorithm is based being able to "contract" an edge, which merges the two endpoint 
  nodes of that edge into one node. For example, contracting node B into node A connects
 all nodes that were connected to node B to node A, and removes node A. After each contraction, all self loops (a node connected by an edge to itself) are removed.
 
Let n be the number of nodes in the graph.
Running this algorithm (n^2)ln(n) times and recording the remaining cuts when there are only two nodes remaining gives only a 1/n chance that the minimum cut is not computed on one of these trials.

There is a demonstration of the algorithm running on a 200 node graph from file kargerMinCut.txt in the main method, only completing 50 trials and still somewhat reliably coming up with the right answer (minimum cut == 17). The reason only 50 trials are performed is because (n^2)ln(n) with n = 200 is a large number and would take awhile with this slow algorithm.

Graphs are stored in a HashMap\<Integer, ArrayList\<Integer\>\>, where each vertex is numbered and mapped to an ArrayList of all vertices it shares an edge with.  

## Command Line Instructions for Karger's Min Cut Algorithm

`cd src/`

Compile:  
`javac KargerMinCut.java`  

Run:  
`java KargerMinCut`





