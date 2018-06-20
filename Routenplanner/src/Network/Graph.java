package Network;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {

    public int nodes;
    public int edges;
    public ArrayList<Edge> adjacency[];
    public HashMap<String, Integer> nameToID;
    public HashMap<Integer, String> idToName;
    public int id;

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

    public Graph() {
        adjacency = new ArrayList[nodes];
        for (int i = 0; i < edges; i++) {
            adjacency[i] = new ArrayList<Edge>();
        }
        nameToID = new HashMap<>();
        idToName = new HashMap<>();
    }

    public boolean addEdge(Edge edge) {

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
        adjacency[fromID].add(edge);
        adjacency[toID].add(new Edge(edge.getTo(), edge.getFrom(), edge.getDist()));
        edges = edges + 2;
        return true;

    }

    public String delEdge(String from, String to) {
        int fromID = checkID(from);
        int toID = checkID(to);

        if (fromID == -1) return "Start existiert nicht";
        if (toID == -1) return "Ziel existiert nicht";

        adjacency[fromID].removeIf(p -> p.getTo().equals(to));
        adjacency[toID].removeIf(p -> p.getTo().equals(from));

        edges = edges - 2;
        return "Alle Verbindungen zwischen " + from + " und " + to + " gekappt";
    }

    public int checkID(String from) {
        if (nameToID.containsKey(from)) return nameToID.get(from);
        else return -1;
    }
}
