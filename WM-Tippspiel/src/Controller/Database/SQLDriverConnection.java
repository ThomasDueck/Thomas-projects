package Controller.Database;

import java.sql.*;

public class SQLDriverConnection {

    protected static Connection conn = null;
    static String URL = "jdbc:sqlite::resource:soccer.db";

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
