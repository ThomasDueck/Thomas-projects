package Controller.Database;

import Model.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Controller.Database.SQLDriverConnection.connect;

public class UserTable {

    public static ArrayList<Team> getUser() {
        connect();
        String sql = "SELECT * FROM User";
        ArrayList<Team> allUser = new ArrayList<>();
        try {
            PreparedStatement stmt = SQLDriverConnection.conn.prepareStatement(sql);
            ResultSet user = stmt.executeQuery();
            while (user.next()) {
                allUser.add(new Team(user.getString(1), user.getInt(2)));
            }
            SQLDriverConnection.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return allUser;
        }
    }

    public static ObservableList<String> getObservableUser(){
        ArrayList<Team> teams = getUser();
        ObservableList<String> teamList = FXCollections.observableArrayList();
        for(Team t : teams){
            teamList.add(t.getTeamname());
        }
        return teamList;
    }
}
