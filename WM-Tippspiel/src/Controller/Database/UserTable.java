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

public class UserTable {

    /**
     * Gets all Users from the database table USER
     * @return ArrayList<Team> of all Users. Team
     */
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
            System.out.println("User couldnt get selected from table");
        } finally {
            return allUser;
        }
    }

    /**
     * Gets all Users and inserts them into an ObservableList<String>
     * @return ObservableList<String>
     */
    public static ObservableList<String> getObservableUser() { ;
        ArrayList<Team> teams = getUser();
        ObservableList<String> teamList = FXCollections.observableArrayList();
        for (Team t : teams) {
            teamList.add(t.getTeamname());
        }
        Collections.sort(teamList);
        return teamList;
    }

    /**
     * Adds a User into the database Table USER
     * @param user - username to be added
     * @return true if success, false otherwise
     */
    public static boolean addUser(String user) {
        connect();
        String sql = "INSERT INTO User (user) VALUES (?)";
        try {
            PreparedStatement stmt = SQLDriverConnection.conn.prepareStatement(sql);
            stmt.setString(1, user);
            int res = stmt.executeUpdate();
            SQLDriverConnection.conn.close();
            if(res > 0) return true;
            else return false;
        } catch (SQLException e) {
            System.out.println("User could not be inserted");
            return false;
        }
    }

    /**
     * Checks if a username is already taken by someone else
     * @param user - User to be checked
     * @return - true if user already exists, false if not.
     */
    public static boolean checkUser(String user) {
        connect();
        String sql = "SELECT * FROM USER where user = ?";
        try {
            PreparedStatement stmt = SQLDriverConnection.conn.prepareStatement(sql);
            stmt.setString(1, user);
            ResultSet rs = stmt.executeQuery();
            SQLDriverConnection.conn.close();
            if (rs.next()) {
                return true;
            } else return false;
        } catch (SQLException e) {
            System.out.println("User could not be checked");
            return true;
        }
    }

    /**
     * Deletes a User from the database
     * @param user - username
     * @return true with success, false if not
     */
    public static boolean delUser(String user) {
        connect();
        String sql = "DELETE FROM User WHERE User = ?";
        try {
            PreparedStatement stmt = SQLDriverConnection.conn.prepareStatement(sql);
            stmt.setString(1, user);
            int res = stmt.executeUpdate();
            SQLDriverConnection.conn.close();
            if(res > 0) return true;
            else return false;
        } catch (SQLException e) {
            System.out.println("User could not be deleted");
            return false;
        }
    }


}
