import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This program is an implementation of Karger's algorithm, which is used to compute a 
 * minimum cut of a connected graph. This algorithm is randomized, so the edges
 * which are chosen to be "contracted" (next paragraph) are chosen randomly on every run of the algorithm.
 * 
 * The algorithm is based being able to "contract" an edge, which merges the two endpoint 
 * nodes of that edge into one node. For example, contracting node B into node A connects
 * all nodes that were connected to node B to node A, and removes node A. After each contraction,
 * all self loops (a node connected by an edge to itself) are removed.
 * 
 * Let n be the number of nodes in the graph.
 * Running this algorithm (n^2)ln(n) times and recording the remaining cuts when there are only
 * two nodes remaining gives only a 1/n chance that the minimum cut is not computed on one of these trials.
 * 
 * There is a demonstration of the algorithm running on a 200 node graph from file kargerMinCut.txt in the main method, 
 * only completing 50 trials and still somewhat reliably coming up with the right answer (minimum cut == 17).
 * The reason only 50 trials are performed is because (n^2)ln(n) with n = 200 is a large number and would take
 * awhile with this slow algorithm.
 * 
 * Graphs are stored in a HashMap<Integer, ArrayList<Integer>>, where each vertex is numbered and mapped to an ArrayList of all
 * vertices it shares an edge with.
 */
public class KargerMinCut {
    
    public static int findMinCut(HashMap<Integer, ArrayList<Integer>> originalGraph) {
        HashMap<Integer, ArrayList<Integer>> graph = copyGraph(originalGraph);
        int originalGraphSize = graph.size();
        while (graph.size() > 2) {
            // Get a vertex and another vertex that the first vertex shares an edge with.
            // Here, vertex1 and vertex2 share an edge
            Integer vertex1 = getRandomNumberInRange(1, originalGraphSize);
            ArrayList<Integer> vertex1List = graph.get(vertex1);
            while (vertex1List == null) {
                vertex1 = getRandomNumberInRange(1, originalGraphSize);
                vertex1List = graph.get(vertex1);
            }
            int randomIndex = getRandomNumberInRange(0, vertex1List.size() - 1);
            Integer vertex2 = vertex1List.get(randomIndex);
            
            contract(graph, vertex1, vertex2);
            
            // remove self loops
            vertex1List.removeAll(Collections.singleton(vertex1));
        }
        
        // return cut represented by the remaining 2 vertices
        int count = 0;
        for (Integer key : graph.keySet()) {
            ArrayList<Integer> listToPrint = graph.get(key);
            count = listToPrint.size();
        }
        return count;
    }
    
    /**
     * Contracts an edge in a graph. If vertex1 and vertex2 form an edge, and this edge is contracted,
     * all vertices connected to vertex2 are now connected to vertex1, and vertex2 is removed.
     * 
     * @param graph     Graph in which to contract an edge
     * @param vertex1   Vertex at one end of the edge to be contracted
     * @param vertex2   Vertex at the other end of the edge to be contracted
     */
    private static void contract(HashMap<Integer, ArrayList<Integer>> graph, Integer vertex1, Integer vertex2) {
        ArrayList<Integer> vertex1List = graph.get(vertex1);
        ArrayList<Integer> vertex2List = graph.get(vertex2);
        for (int i = 0; i < vertex2List.size(); i++) {
            vertex1List.add(vertex2List.get(i));
        }
        graph.remove(vertex2);
        replaceAllInstances(graph, vertex1, vertex2);
    }
    
    /**
     * Goes through a graph and replaces all instances of vertex2 with vertex1.
     */
    private static void replaceAllInstances(HashMap<Integer, ArrayList<Integer>> graph, Integer vertex1, Integer vertex2) {
        for (Map.Entry<Integer, ArrayList<Integer>> entry: graph.entrySet()) {
            ArrayList<Integer> list = entry.getValue();
            for (int i = 0; i < list.size(); i++) {
                while (list.contains(vertex2)) {
                    list.remove(vertex2);
                    list.add(vertex1);
                }
            }
        }
    }
    
    /**
     * Returns a random int between min and max, inclusive.
     */
    private static int getRandomNumberInRange(int min, int max) {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }
    
    /**
     * Reads a graph from a file into a HashMap. Graph must be in a specific form.
     * The first column in the file represents the vertex label, and all entries in that
     * vertexes row (aside from the first column) tells all vertices that the vertex
     * shares an edge with.
     * 
     * @param fileName  File to read the graph from
     */
    private static HashMap<Integer, ArrayList<Integer>> readGraphFromFile(String fileName, HashMap<Integer, ArrayList<Integer>> graph) {
        
        BufferedReader bufferedReader = null;
        DataInputStream dataInputStream = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            dataInputStream = new DataInputStream(fileInputStream);
            bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
            
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(line);
                ArrayList<Integer> edgeList = new ArrayList<Integer>();
                
                Integer nodeNumber = new Integer(stringTokenizer.nextToken());
                
                while(stringTokenizer.hasMoreTokens()) {
                    edgeList.add(new Integer(stringTokenizer.nextToken()));
                }
                
                graph.put(nodeNumber, edgeList);
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                dataInputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        return graph;
    }
    
    /**
     * Prints the graph to the console. 
     * Note: copy the graph you want to print and pass it to this method.
     */
    private static void printGraphContents(HashMap<Integer, ArrayList<Integer>> graph) {
        HashMap<Integer, ArrayList<Integer>> graphPrintCopy = copyGraph(graph);
        Iterator iterator = graphPrintCopy.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            System.out.print("Node " + pair.getKey() + " is connected to the following edges: ");
            for (int i = 0; i < ((ArrayList<Integer>) pair.getValue()).size(); i++) {
                System.out.print(((ArrayList<Integer>) pair.getValue()).get(i) + " ");
            }
            System.out.println("\n");
            
        }
        iterator.remove();
    }
    
    /**
     * Makes a duplicate of the given graph. 
     * To be used before finding the minimum cut which will involve changing the original graph.
     */
    private static HashMap<Integer, ArrayList<Integer>> copyGraph(HashMap<Integer, ArrayList<Integer>> graph) {
        HashMap<Integer, ArrayList<Integer>> copiedGraph = new HashMap<>();
        
        Iterator iterator = graph.keySet().iterator();
        
        while (iterator.hasNext()) {
            Integer currentKey = (Integer) iterator.next();
            ArrayList<Integer> currentItemList = graph.get(currentKey);
            
            copiedGraph.put(currentKey, new ArrayList<Integer>(currentItemList));
        }
        return copiedGraph;
    }
    
    /**
     * A small test graph that is easy to follow along with while the algorithm 
     * finds it's min cut.
     */
    private static HashMap<Integer, ArrayList<Integer>> createSmallTestGraph() {
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        ArrayList<Integer> list3 = new ArrayList<Integer>();
        ArrayList<Integer> list4 = new ArrayList<Integer>();
        ArrayList<Integer> list5 = new ArrayList<Integer>();
        list1.addAll(Arrays.asList(2, 3, 3));
        list2.addAll(Arrays.asList(1, 5, 4));
        list3.addAll(Arrays.asList(1, 1, 4));
        list4.addAll(Arrays.asList(5, 2, 3));
        list5.addAll(Arrays.asList(2, 4));
        graph.put(1, list1);
        graph.put(2, list2);
        graph.put(3, list3);
        graph.put(4, list4);
        graph.put(5, list5);
        return graph;
    }
    
    /**
     * Demonstration on a 200 node graph.
     */
    public static void main(String[] args) {
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        graph = readGraphFromFile("kargerMinCut.txt", graph);
        int numTrials = 50;
        int count = 0;
        int minCut = 500;
        System.out.println("Number of trials to be completed: " + numTrials);
        for (int i = 1; i <= numTrials; i++) {
            count = findMinCut(graph);
            System.out.println("Trial: " + i + " - Cuts found: " + count);
            if (count < minCut)  {
                minCut = count;
            }
        }
        System.out.println("Min cut found: " + minCut);
        System.out.println("Number of trials completed: " + numTrials);
        System.out.println("Number of nodes in the graph: 200");
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Would you like to see the graph the algorithm was run on? (y/n) ");
        String userAnswer = scanner.nextLine();
        if (userAnswer.equals("y")) {
            printGraphContents(graph);
        }
        System.out.println("Goodbye.");
        scanner.close();
    }
        
}
