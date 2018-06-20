package Main;

import Util.JSON;

public class main {

    public static void main(String[] args) {
        Network.Graph graph = new Network.Graph(0, 0, 0);
        if (args.length != 0) {
            graph = JSON.fromFile(args[0]);
        }

        while (true) {
            boolean status = Util.Input.parseInput(graph);
            if (status == false) break;

        }


    }
}
