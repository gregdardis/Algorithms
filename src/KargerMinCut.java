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
import java.util.StringTokenizer;

public class KargerMinCut {
    
    public static void findMinCut(HashMap<Integer, ArrayList<Integer>> graph) {
        int originalGraphSize = graph.size();
        while (graph.size() > 2) {
            System.out.println("Graph size: " + graph.size());
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
            
            // contract those vertices into the first vertex
            System.out.println("Contracting " + vertex2 + " into " + vertex1);
            contract(graph, vertex1, vertex2);
            
            // remove self loops
            vertex1List.removeAll(Collections.singleton(vertex1));
        }
        
        // after while loop, return cut represented by the remaining 2 vertices
        for (Integer key : graph.keySet()) {
            ArrayList<Integer> listToPrint = graph.get(key); 
            System.out.print("\nRemaining vertices: ");
            for (int i = 0; i < listToPrint.size(); i++) {
                System.out.print(listToPrint.get(i) + " ");
            }
        }
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
    
    private static void printGraphContents(HashMap<Integer, ArrayList<Integer>> graph) {
        Iterator iterator = graph.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            System.out.print(pair.getKey() + " node is connected to the following edges: ");
            for (int i = 0; i < ((ArrayList<Integer>) pair.getValue()).size(); i++) {
                System.out.print(((ArrayList<Integer>) pair.getValue()).get(i) + " ");
            }
            System.out.println("\n");
            
        }
        iterator.remove();
    }
    
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
    
    public static void main(String[] args) {
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        graph = readGraphFromFile("kargerMinCut.txt", graph);
//        graph = createSmallTestGraph();
        findMinCut(graph);
//        printGraphContents(graph);
    }
}
