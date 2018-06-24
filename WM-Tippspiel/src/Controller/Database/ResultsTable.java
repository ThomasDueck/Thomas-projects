package Controller.Database;

import Model.Match;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Controller.Database.SQLDriverConnection.connect;

public class ResultsTable {

    /**
     * Deletes a result from the table results
     * @param id - The id of a given match (unique)
     * @return true if success, false otherwise
     */
    public static boolean deleteResult(int id){
        SQLDriverConnection.connect();
        String sql = "DELETE FROM results WHERE match_id = ?";
        try {
            PreparedStatement stmt = SQLDriverConnection.conn.prepareStatement(sql);
            stmt.setInt(1,id);
            int result = stmt.executeUpdate();
            SQLDriverConnection.conn.close();
            if(result > 0) return true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Returns the whole results table with all results that are available.
     * @return ArrayList<Match> - all matchresults that are known
     */
    public static ArrayList<Match> getResults() {

        connect();
        String sql = "SELECT * FROM RESULTS;";
        ArrayList<Match> matches = new ArrayList<>();
        try {
            PreparedStatement stmt = SQLDriverConnection.conn.prepareStatement(sql);
            ResultSet matchList = stmt.executeQuery();
            while (matchList.next()) {
                matches.add(new Match(matchList.getString(1), matchList.getString(2), matchList.getInt(3), matchList.getInt(4), matchList.getInt(5)));
            }
            SQLDriverConnection.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return matches;
        }

    }

    /**
     * Returns an ObservableList of all Matches to update the table
     * @return ObservableList<Match> - Needed to update the tableView
     */
    public static ObservableList<Match> getObservableMatches(){
        ArrayList<Match> matches = getResults();
        ObservableList<Match> temp2 = FXCollections.observableArrayList();
        for(Match m : matches){
            temp2.add(m);
        }
        return temp2;
    }

    /**
     * Inserts a result of a match into the table
     * @param home - String/Name of hometeam
     * @param goalH - Goals of hometeam scored
     * @param goalA - Goals of awayteam scored
     * @param away - String/Name of awayteam
     * @return a boolean, true if success, false if not
     */
    public static boolean addResults(String home, int goalH, int goalA, String away) {
        connect();
        String sql = "INSERT INTO results (home, away, goals_home, goals_away) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = SQLDriverConnection.conn.prepareStatement(sql);
            stmt.setString(1, home);
            stmt.setInt(3, goalH);
            stmt.setInt(4, goalA);
            stmt.setString(2, away);

            int success = stmt.executeUpdate();
            if (success > 0) return true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
