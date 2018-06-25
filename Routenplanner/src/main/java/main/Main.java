package main;

import util.Input;

public class Main {


    /**
     * main-method. Parses input as long as there is something to parse.
     *
     * @param args
     */
    public static void main(String[] args) {
        network.Graph graph = new network.Graph(0, 0, 0);
        Input.displayHelp();
        while (util.Input.parseInput(graph)) {
        }


    }
}
