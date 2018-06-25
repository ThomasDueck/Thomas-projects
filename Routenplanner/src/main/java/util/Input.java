package util;

import data.Strings;
import network.Edge;
import network.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Input {


    /**
     * Takes the graph and keeps parsing input. Returns a boolean, true if everything went okay and user didnt call
     * end, and false if user typed end or something went wrong
     *
     * @param graph
     * @return True - Continue Program, false - end program/error
     */
    public static boolean parseInput(Graph graph) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {

            //Takes inputline, splits it and traverses cases.
            String input = reader.readLine();
            String commands[] = input.split(" ");
            switch (commands[0].toLowerCase()) {

                case Strings.ADDEDGE:
                    addEdge(graph, commands);
                    break;

                case Strings.DELEDGE:
                    delEdge(graph, commands);
                    break;

                case Strings.SHORTESTROUTE:
                    route(graph, commands);
                    break;
                case Strings.LISTNODE:
                    graph.listNodes();
                    break;
                case Strings.LISTEDGES:
                    graph.listEdges();
                    break;

                case Strings.SAVE:
                    saveGraph(graph, commands);
                    break;

                case Strings.LOAD:
                    loadGraph(graph, commands);
                    break;

                //END - ends the program
                case Strings.END:
                    return false;
                default:
                    System.out.println(Strings.COMMANDNOTFOUND);
                        break;


            }


        } catch (IOException e) {
            System.out.println(Strings.INPUTERROR);
            return false;
        }

        return true;
    }

    public static void displayHelp() {
        System.out.println(Strings.HELP);
    }

    /**
     * ADD FROM TO DIST- adds an edge between FROM and TO, with weight dist.
     *
     * @param graph    - Modify Graph
     * @param commands - Command Arguments
     */
    public static void addEdge(Graph graph, String[] commands) {
        if (commands.length == 4 && commands[3].matches("\\d+")) {
            String from = commands[1];
            String to = commands[2];
            int dist = Integer.parseInt(commands[3]);
            if (graph.addEdge(new Edge(from, to, dist)))
                System.out.println("Route from " + from + " to " + to + " with length " + dist + " added");
        } else {
            //Not enough arguments
            if (commands.length != 4) System.out.println(Strings.WRONGNUMBER);
                //The last argument is not a positive number
            else if (commands.length == 4 && !commands[3].matches("\\d+"))
                System.out.println(Strings.DISTNAN);
        }
    }

    /**
     * DEL FROM TO - deletes all edges between FROM and TO
     *
     * @param graph    - Modify Graph
     * @param commands - Command Arguments
     */
    public static void delEdge(Graph graph, String[] commands) {
        if (commands.length == 3) {
            String from = commands[1];
            String to = commands[2];

            //Deletes Edges from the Graph
            System.out.println(graph.delEdge(from, to));
        } else {
            System.out.println(Strings.WRONGNUMBER);
        }
    }

    /**
     * ROUTE FROM TO - Calculates and outputs the shortest route between FROM and TO, and whether there is a route.
     *
     * @param graph    - Modify Graph
     * @param commands - Command Arguments
     */
    public static void route(Graph graph, String[] commands) {
        if (commands.length == 3) {
            String from = commands[1];
            String to = commands[2];

            //Apply Djikstra and calculate shortestRoute
            ArrayList<Integer> result = util.Algorithm.Djikstra(graph, from, to);

            //If result = null, no solution is found. Otherwise output the route and the shortest distance
            if (result != null) {
                System.out.println("Shortest path from " + from + " to " + to + " is " + result.get(0) + " long");
                for (int j = result.size() - 1; j > 0; j--) {
                    if (j != 1) System.out.print(graph.idToName.get(result.get(j)) + " > ");
                    else System.out.println(graph.idToName.get(result.get(1)));
                }

            } else
                System.out.println(to + " to " + from + " is not possible");
        } else {
            System.out.println(Strings.WRONGNUMBER);
        }
    }

    /**
     * Saves the graph to a file and checks for fitting number of arguments
     *
     * @param graph
     * @param commands
     */
    public static void saveGraph(Graph graph, String[] commands) {
        if (commands.length != 2) System.out.println(Strings.WRONGNUMBER);
        else{
            Path path = Paths.get(commands[1]);
            JSON.toFile(graph, path.toString());
        }


    }

    /**
     * Loads a graph from a file.
     *
     * @param graph    - Modify Graph
     * @param commands - Command Arguments
     */
    public static void loadGraph(Graph graph, String[] commands) {
        if (commands.length != 2) System.out.println(Strings.WRONGNUMBER);
        else{
            boolean succ = graph.loadGraph(Paths.get(commands[1]).toString());
            if (succ) System.out.println(Strings.GRAPHLOADED);
            else System.out.println(Strings.PATHORGRAPHNOTVALID);
        }
    }

}
