package data;

public class Strings {

    public static final String WRONGNUMBER = "Wrong number of arguments";
    public static final String DISTNAN = "Distance is not a number";
    public static final String ADDEDGE = "add";
    public static final String DELEDGE = "del";
    public static final String SHORTESTROUTE = "route";
    public static final String END = "end";
    public static final String SAVE = "save";
    public static final String LOAD = "load";
    public static final String INPUTERROR = "Something went wrong while parsing input";
    public static final String STARTNOTEXIST = "Start does not exist";
    public static final String GOALNOTEXIST = "Goal does not exist";
    public static final String GRAPHNOTEXIST = "Graph could not be created";
    public static final String GRAPHNOTSAVED = "Graph could not be saved";
    public static final String GRAPHSAVED = "Graph saved!";
    public static final String GRAPHLOADED = "Graph loaded!";
    public static final String PATHORGRAPHNOTVALID = "Path or Graph were not valid";
    public static final String LISTNODE = "listnodes";
    public static final String LISTEDGES = "listedges";
    public static final String NOEDGES = "There are no edges";
    public static final String NONODES = "There are no nodes";
    public static final String COMMANDNOTFOUND = "Command not found";



    public static final String HELP = "This is a command line route planer.\n" +

            "ADD <FROM> <TO> <DIST> - adds a new edge between FROM and TO with length DIST\n" +

            "DEL <FROM> <TO> - deletes all edges between FROM and TO\n" +

            "ROUTE <FROM> <TO> - calculates shortest route between FROM and TO\n" +

            "LISTEDGES - list all edges of current graph\n" +

            "LISTNODES - list all nodes of current graph\n" +

            "LOAD <PATH> - Loads a graph from a file\n" +

            "SAVE <PATH> - Saves the graph to a file\n" +

            "END - ends the program\n";




}
