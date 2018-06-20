package Util;

import Network.Edge;
import Network.Graph;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.LinkedList;

public class JSON {


    public static boolean toFile(Graph graph, String path) {
        try (Writer writer = new FileWriter("./graph.txt")) {
            Gson gson = new GsonBuilder().create();
            if (gson != null) gson.toJson(graph, writer);
        } catch (IOException e) {
            System.out.println("Die Json konnte nicht in die Datei geschrieben werden.");
            e.printStackTrace();
        }

        return true;
    }

    public static Graph fromFile(String path) {
        try {
            InputStreamReader reader = new FileReader(path);
            Gson gson = new GsonBuilder().create();
            Data.JsonEdge edges = gson.fromJson(reader, Data.JsonEdge.class);
            Graph graph = new Graph();
            for (Edge tempEdge : edges.edgeList) {
                graph.addEdge(tempEdge);
            }
            return graph;

        } catch (IOException e) {
            System.out.println("Der Graph konnte nicht erstellt werden.");
            return null;
        }
    }

}
