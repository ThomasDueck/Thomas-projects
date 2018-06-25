package util;

import data.Pair;
import network.Graph;

import java.util.*;

public class Algorithm {


    /**
     * Implementation of Djikstra, includes also tracking of which nodes need to be visited for the shortest path
     * from A to B.
     * @param graph - Graph
     * @param from - Startnode
     * @param to - Endnode
     * @return Returns an ArrayList, where arraylist.get(0) is the distance and all other entries are the order of cities reversed
     *          This means, arraylist.get(1) is the last city visited and arraylist.get(size-1) is the first city visited.
     */
    public static ArrayList<Integer> Djikstra(Graph graph, String from, String to) {

        //Check if Nodes exist
        int fromID = graph.checkID(from);
        int toID = graph.checkID(to);
        if (fromID == -1 || toID == -1) return null;

        //Initialize Priorityqueue for Djikstra, has current distance and Node-ID of the current node.
        PriorityQueue<data.Pair> queue = new PriorityQueue<>();
        queue.add(new data.Pair(0, fromID));
        Pair distances[] = new Pair[graph.nodes];
        Arrays.fill(distances, new Pair(Integer.MAX_VALUE, -1));

        //Distance-Pairs distances[X] are <Length to X from FROM, precursor-city-id on the route>
        distances[fromID] = new Pair(0,-1);

        while (!queue.isEmpty()) {

            //Get the node with highest priority (lowest distance) from the queue
            data.Pair tempNode = queue.poll();
            int currFrom = tempNode.getNode();

            //Traverse all neighbors of current node
            for (int i = 0; i < graph.adjacency[currFrom].size(); i++) {
                String neighbourNode = graph.adjacency[currFrom].get(i).getTo();
                int neighbourNodeID = graph.checkID(neighbourNode);
                int dist = graph.adjacency[currFrom].get(i).getDist();

                //Check if the distance to current node + edgelength is smaller than distance
                //to a neighbor node, if yes, insert into queue and save new smaller distance for neighbornode
                if (distances[currFrom].getWeight() + dist < distances[neighbourNodeID].getWeight()) {
                    distances[neighbourNodeID] = new Pair(distances[currFrom].getWeight() + dist, currFrom);
                    queue.add(new data.Pair(distances[neighbourNodeID].getWeight(), neighbourNodeID));

                }

            }

            //Reconstruct Path. A Path to TO is found if distances[toID] is smaller than Integer.MAX_VALUE
            if (distances[toID].getWeight() != Integer.MAX_VALUE){
                ArrayList<Integer> cities = new ArrayList<>();
                cities.add(distances[toID].getWeight());
                cities.add(toID);
                int tempCity = distances[toID].getNode();
                while(tempCity != -1){
                    cities.add(tempCity);
                    tempCity = distances[tempCity].getNode();
                }
                return cities;
            }

        }
        //No path found, return null
        return null;
    }
}
