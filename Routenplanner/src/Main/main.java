package Main;

import Util.Input;
import Util.JSON;

public class Main {


    /**
     * Main-method. Parses input as long as there is something to parse.
     *
     * @param args
     */
    public static void main(String[] args) {
        Network.Graph graph = new Network.Graph(0, 0, 0);
        Input.displayHelp();
        while (Util.Input.parseInput(graph)) {
        }


    }
}
