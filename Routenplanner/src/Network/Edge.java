package Network;

public class Edge {

    private String from;
    private String to;
    private int distance;

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getDist() {
        return distance;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setDist(int dist) {
        this.distance = dist;
    }

    public Edge(String from, String to, int distance) {
        setFrom(from);
        setTo(to);
        setDist(distance);
    }

}
