package Network;

import Data.Strings;
import Util.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Graph {

    public int nodes;
    public int edges;
    public ArrayList<Edge> adjacency[];
    public HashMap<String, Integer> nameToID;
    public HashMap<Integer, String> idToName;
    public int id;


    /**
     * Constructor for Graph, initializes edges, nodes, and id.
     *
     * @param edges - number of edges
     * @param nodes - number of nodes
     * @param id    - number of biggest id thus far
     */
    public Graph(int edges, int nodes, int id) {
        this.edges = edges;
        this.nodes = nodes;
        this.id = id;

        adjacency = new ArrayList[nodes];
        for (int i = 0; i < nodes; i++) {
            adjacency[i] = new ArrayList<Edge>();
        }
        nameToID = new HashMap<>();
        idToName = new HashMap<>();

    }

    /**
     * Adds bidirectional Edges to the Graph
     *
     * @param edge - Edge to be added
     * @return True on success
     */
    public boolean addEdge(Edge edge) {

        //Check if <FROM> and <TO> already exist, otherwise create them
        int fromID = checkID(edge.getFrom());
        if (fromID == -1) {
            fromID = this.id;
            nameToID.put(edge.getFrom(), this.id);
            idToName.put(this.id++, edge.getFrom());
            nodes++;
        }
        int toID = checkID(edge.getTo());
        if (toID == -1) {
            toID = this.id;
            nameToID.put(edge.getTo(), this.id);
            idToName.put(this.id++, edge.getTo());
            nodes++;
        }

        //If the adjacency-list is too small, it needs to be made bigger and old entries have to be copied.
        if (nodes > adjacency.length) {
            ArrayList<Edge> tempAd[];
            tempAd = new ArrayList[nodes];
            for (int i = 0; i < adjacency.length; i++) {
                tempAd[i] = adjacency[i];
            }
            for (int i = adjacency.length; i < nodes; i++) {
                tempAd[i] = new ArrayList<Edge>();
            }

            adjacency = tempAd;
        }

        //Finally, add edges to the Graph.
        adjacency[fromID].add(edge);
        adjacency[toID].add(new Edge(edge.getTo(), edge.getFrom(), edge.getDist()));
        edges = edges + 2;
        return true;

    }

    /**
     * Deletes edges between FROM and TO in the Graph
     *
     * @param from - Node from
     * @param to   - Node to
     * @return String with the message of success or not
     */
    public String delEdge(String from, String to) {

        //Check if from and to are already known, if not nothing can be deleted
        int fromID = checkID(from);
        int toID = checkID(to);
        if (fromID == -1) return Strings.STARTNOTEXIST;
        if (toID == -1) return Strings.GOALNOTEXIST;

        //Delete bidirectional connection between two nodes in an adjacency list
        adjacency[fromID].removeIf(p -> p.getTo().equals(to));
        adjacency[toID].removeIf(p -> p.getTo().equals(from));

        edges = edges - 2;
        return "All connections between " + from + " and " + to + " are removed";
    }

    /**
     * Checks if a Nodename is known (saved in map)
     *
     * @param from - Nodename
     * @return -1 if not known, else id of node
     */
    public int checkID(String from) {
        if (nameToID.containsKey(from)) return nameToID.get(from);
        else return -1;
    }

    /**
     * Loads a graph from a file, and overrides the current graph.
     *
     * @param path - Path to graphfile
     * @return True on success
     */
    public boolean loadGraph(String path) {
        Graph object = JSON.fromFile(path);
        if (object == null) return false;
        this.id = object.id;
        this.edges = object.edges;
        this.nodes = object.nodes;
        nameToID = object.nameToID;
        idToName = object.idToName;

        adjacency = new ArrayList[nodes];
        for (int i = 0; i < nodes; i++) {
            adjacency[i] = new ArrayList<Edge>();
        }

        for (int i = 0; i < object.adjacency.length; i++) {
            for (Edge tempEdge : object.adjacency[i]) {
                adjacency[i].add(tempEdge);
            }
        }
        return true;
    }

    public void listEdges() {
        boolean empty = true;
        for (int i = 0; i < adjacency.length; i++) {
            for (Edge tempEdge : adjacency[i]) {
                empty = false;
                System.out.println(tempEdge.getFrom() + ", " + tempEdge.getTo() + " - " + tempEdge.getDist());
            }
        }
        if (empty) System.out.println(Strings.NOEDGES);
    }

    public void listNodes() {
        Set<String> keySet = nameToID.keySet();
        if (keySet.isEmpty()) System.out.println(Strings.NONODES);
        else {
            for (String tempName : keySet) {
                System.out.println(tempName);
            }

        }
    }
}
