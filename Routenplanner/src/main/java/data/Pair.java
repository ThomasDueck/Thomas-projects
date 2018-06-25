package data;

public class Pair implements Comparable<Pair> {

    private int node;
    private int weight;

    public Pair(int weight, int node) {
        this.node = node;
        this.weight = weight;
    }


    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Pair p1) {
        return this.getWeight() - p1.getWeight();
    }
}
