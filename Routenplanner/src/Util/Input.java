package Util;

import Network.Edge;
import Network.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Input {


    public static boolean parseInput(Graph graph) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String input = reader.readLine();
            String commands[] = input.split(" ");
            switch (commands[0]) {
                case "add":
                    if (commands.length == 4 && commands[3].matches("-?\\d+")) {
                        String from = commands[1];
                        String to = commands[2];
                        int dist = Integer.parseInt(commands[3]);
                        if (graph.addEdge(new Edge(from, to, dist)))
                            System.out.println("Kante von " + from + " zu " + to + " mit Laenge " + dist + " erfolgreich hinzugefügt");
                        JSON.toFile(graph, "./graph.txt");
                    } else {
                        if (commands.length != 4) System.out.println("Falsche Anzahl der Argumente");
                        if (commands.length == 4 && !commands[3].matches("-?\\d+"))
                            System.out.println("Die Distance ist keine Zahl");
                    }
                    break;
                case "del":
                    if (commands.length == 3) {
                        String from = commands[1];
                        String to = commands[2];
                        System.out.println(graph.delEdge(from, to));
                        //TODO
                        JSON.toFile(graph, "./graph.txt");
                    } else {
                        System.out.println("Falsche Anzahl der Argumente für del");
                    }
                    break;
                case "route":
                    if (commands.length == 3) {
                        String from = commands[1];
                        String to = commands[2];
                        ArrayList<Integer> result = Util.Algorithm.Djikstra(graph, from, to);
                        if (result != null) {
                            System.out.println("Die kürzeste Route von " + from + " zu " + to + " ist " + result.get(0) + " lang");
                            for (int j = result.size() - 1; j > 0; j--) {
                                if (j != 1) System.out.print(graph.idToName.get(result.get(j)) + " > ");
                                else System.out.println(graph.idToName.get(result.get(1)));
                            }

                        } else
                            System.out.println(to + " ist von " + from + " leider nicht erreichbar");
                    } else {
                        System.out.println("Falsche Anzahl der Argumente");
                    }
                    break;
                case "end":
                    return false;

            }


        } catch (IOException e) {
            System.out.println("Irgendetwas lief beim Einlesen schief");
            return false;
        }

        return true;
    }
}
