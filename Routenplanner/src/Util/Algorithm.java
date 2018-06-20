package Util;

import Data.Pair;
import Network.Edge;
import Network.Graph;

import java.util.*;

public class Algorithm {


    public static ArrayList<Integer> Djikstra(Graph graph, String from, String to) {

        int fromID = graph.checkID(from);
        int toID = graph.checkID(to);
        if (fromID == -1 || toID == -1) return null;

        PriorityQueue<Data.Pair> queue = new PriorityQueue<>();
        queue.add(new Data.Pair(0, fromID));
        Pair distances[] = new Pair[graph.nodes];
        Arrays.fill(distances, new Pair(Integer.MAX_VALUE, -1));
        distances[fromID] = new Pair(0,-1);

        while (!queue.isEmpty()) {

            Data.Pair tempNode = queue.poll();
            int currFrom = tempNode.getNode();
            for (int i = 0; i < graph.adjacency[currFrom].size(); i++) {
                String neighbourNode = graph.adjacency[currFrom].get(i).getTo();
                int neighbourNodeID = graph.checkID(neighbourNode);
                int dist = graph.adjacency[currFrom].get(i).getDist();

                if (distances[currFrom].getWeight() + dist < distances[neighbourNodeID].getWeight()) {
                    distances[neighbourNodeID] = new Pair(distances[currFrom].getWeight() + dist, currFrom);
                    queue.add(new Data.Pair(distances[neighbourNodeID].getWeight(), neighbourNodeID));

                }

            }

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
        return null;
    }
}
