package Controller.Database;

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

    public static void CREATETABLE(){
        String sql = "CREATE TABLE IF NOT EXISTS BET ( home VARCHAR(50) NOT NULL, away VARCHAR(50) NOT NULL, estHome int NOT NULL, estAway int NOT NULL, user VARCHAR(50), bet_id integer NOT NULL, PRIMARY KEY(bet_id), FOREIGN KEY(home) REFERENCES teams(teamname), FOREIGN KEY (away) REFERENCES teams(teamname), FOREIGN KEY (user) REFERENCES User(user) );";
        connect();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void CREATETABLE2(){
        String sql = "INSERT INTO USER (user) VALUES ('Möhrkeboy');";
        connect();
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
