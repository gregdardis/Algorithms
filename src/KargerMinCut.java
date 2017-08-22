import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

public class KargerMinCut {
    
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
            iterator.remove();
        }
    }
    
    private static HashMap<Integer, ArrayList<Integer>> createSmallTestGraph() {
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        ArrayList<Integer> list3 = new ArrayList<Integer>();
        ArrayList<Integer> list4 = new ArrayList<Integer>();
        ArrayList<Integer> list5 = new ArrayList<Integer>();
        list1.addAll(Arrays.asList(2, 3));
        list2.addAll(Arrays.asList(1, 5, 4));
        list3.addAll(Arrays.asList(1, 4));
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
        printGraphContents(graph);
    }
}
