package Util;

import Data.Strings;
import Network.Edge;
import Network.Graph;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class JSON {


    /**
     * Writes a JSON to a file with the help of GSON API
     * @param graph - Graph to be saved
     * @param path - Path of file
     * @return True on success
     */
    public static boolean toFile(Graph graph, String path) {
        try (Writer writer = new FileWriter(path)) {
            Gson gson = new GsonBuilder().create();
            if (gson != null) gson.toJson(graph, writer);
            System.out.println(Strings.GRAPHSAVED);
        } catch (IOException e) {
            System.out.println(Strings.GRAPHNOTSAVED);
            return false;
        }

        return true;
    }

    /**
     * Load a graph from a file with GSON API
     * @param path - Path to file
     * @return Returns Graph, or null if something went wrong.
     */
    public static Graph fromFile(String path) {
        try {
            InputStreamReader reader = new FileReader(path);
            Gson gson = new GsonBuilder().create();
            Graph tempGraph = gson.fromJson(reader, Graph.class);
            return tempGraph;
        } catch (IOException e) {
            System.out.println(Strings.GRAPHNOTEXIST);
            return null;
        }
    }

}
