package Q1;

import java.util.Map.Entry;
import java.util.*; 
import java.util.regex.*;

public class Graph {

    // Assume maximum path length to be less than INF
    private static Integer INF = 1000*1000*1000 ;
    private Map<String, Node> nodeMap = new HashMap<>() ;
    
    public void addNode(String name) {
        /*
         * TODO: Implement this method
         */
        Node n = new Node(name);
        nodeMap.put(name, n);
    }

    public void addDirectedEdge(String nameA, String nameB, Integer distance) {
        /*
         * TODO: Implement this method
         * Check if nodes with nameA and nameB exist.
         */
        if (nodeMap.containsKey(nameA) && nodeMap.containsKey(nameB))
            nodeMap.get(nameA).addNeighbour(nodeMap.get(nameB), distance);     
    }

    public Map<String, Integer> dijkstraAlgorithm(String source) {
        /*
         * TODO: Implement this method
         * Return a map of name of all the nodes
         * with the minimum distance from source node
         */
        Map<String, Integer> dist = new HashMap<>();
        Map<Node,Boolean> unvisit = new HashMap<>(); 
        PriorityQueue<String> uv = new PriorityQueue<>((a, b) -> dist.get(a) - dist.get(b));        

        dist.put(source, 0);
        unvisit.put(nodeMap.get(source), true);
        uv.add(source);

        for (String key : nodeMap.keySet()) {
            if (key.equals(source)) continue;
            dist.put(key, INF);
            unvisit.put(nodeMap.get(key), true);
            uv.add(key);
        }

        while (!uv.isEmpty() && dist.get(uv.peek()) != INF) {
            Node u = nodeMap.get(uv.peek()); // System.out.println("ok ");System.out.println(uv.size());
            String uName = u.getName();
            for (Node v : u.adjacentNodes.keySet()) {
                String vName = v.getName();

                if (!unvisit.get(v)) continue;

                if (u.adjacentNodes.get(v) + dist.get(uName) < dist.get(vName))  {
                    String elementToUpdate = v.getName();
                    dist.replace(vName, u.adjacentNodes.get(v) + dist.get(uName));

                }
            }
            unvisit.replace(u,false);
            uv.poll();
        }
        return dist;

    } 
}