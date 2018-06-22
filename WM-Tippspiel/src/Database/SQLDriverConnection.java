package Database;

import java.sql.*;

public class SQLDriverConnection {
    protected static Connection conn = null;
    static String url = "jdbc:sqlite:database/soccer.db";

    public static void connect() {

        try {
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to database successful");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
