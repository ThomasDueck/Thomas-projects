package Util;

import Network.Edge;
import Network.Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Algorithm {


    public static int Djikstra(Graph graph, String from, String to) {

        int fromID = graph.checkID(from);
        int toID = graph.checkID(to);
        if (fromID == -1 || toID == -1) return Integer.MAX_VALUE;

        PriorityQueue<Data.Pair> queue = new PriorityQueue<>();
        queue.add(new Data.Pair(0, fromID));
        int distances[] = new int[graph.nodes];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[fromID] = 0;

        while (!queue.isEmpty()) {

            Data.Pair tempNode = queue.poll();
            int currFrom = tempNode.getNode();
            for (int i = 0; i < graph.adjacency[currFrom].size(); i++) {
                String neighbourNode = graph.adjacency[currFrom].get(i).getTo();
                int neighbourNodeID = graph.checkID(neighbourNode);
                int dist = graph.adjacency[currFrom].get(i).getDist();

                if (distances[currFrom] + dist < distances[neighbourNodeID]) {
                    distances[neighbourNodeID] = distances[currFrom] + dist;
                    queue.add(new Data.Pair(distances[neighbourNodeID], neighbourNodeID));

                }

            }

            if (distances[toID] != Integer.MAX_VALUE) return distances[toID];

        }
        return distances[toID];
    }
}
