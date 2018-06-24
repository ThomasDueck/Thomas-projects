package Controller.Database;

import Model.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import static Controller.Database.SQLDriverConnection.connect;

public class TeamsTable {

    /**
     * Select all Teams that exist and add them to an ArrayList<Team>
     * @return ArrayList<Team>
     */
    public static ArrayList<Team> getTeams() {
        connect();
        String sql = "SELECT * FROM Teams";
        ArrayList<Team> allTeams = new ArrayList<>();
        try {
            PreparedStatement stmt = SQLDriverConnection.conn.prepareStatement(sql);
            ResultSet teams = stmt.executeQuery();
            while (teams.next()) {
                allTeams.add(new Team(teams.getString(1), teams.getInt(2)));
            }
            SQLDriverConnection.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return allTeams;
        }
    }

    /**
     * Get all TeamNames and add them to an ObservableList<String>
     * @return ObservableList<String>
     */
    public static ObservableList<String> getObservableTeamNames(){
        ArrayList<Team> teams = getTeams();
        ObservableList<String> teamList = FXCollections.observableArrayList();
        for(Team t : teams){
            teamList.add(t.getTeamname());
        }
        Collections.sort(teamList);
        return teamList;
    }

}
