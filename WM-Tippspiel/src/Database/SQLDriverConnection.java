package Database;

import Data.Team;

import java.io.SequenceInputStream;
import java.sql.*;
import java.util.ArrayList;

public class SQLDriverConnection {
    static Connection conn = null;
    static String url = "jdbc:sqlite:database/soccer.db";

    public static void connect() {

        try {
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to database successful");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Team> getTeams(){
        connect();
        String sql = "SELECT * FROM Teams";
        ArrayList<Team> allTeams = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet teams = stmt.executeQuery();
            while(teams.next()){
                allTeams.add(new Team(teams.getString(1), teams.getInt(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();

            } finally{
                return allTeams;
            }
        }
    }



}
