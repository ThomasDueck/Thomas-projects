package controller.database;

import java.sql.*;

public class SQLDriverConnection {

    protected static Connection conn = null;
    //Path for JAR
    static String URL = "jdbc:sqlite::resource:soccer.db";
    //Path for IDE
    //static String URL = "jdbc:sqlite:database/soccer.db";

    /**
     * Creates a connection to the database for queries
     */
    public static void connect() {

        try {
            conn = DriverManager.getConnection(URL);

            System.out.println("Connection to database successful");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



}
