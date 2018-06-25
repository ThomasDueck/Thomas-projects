package controller.database;

import model.Bet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static controller.database.SQLDriverConnection.connect;

public class BetTable {

    /**
     * Selects all Bets and saves them in a ArrayList<Bet>
     * @return ArrayList<Bet>
     */
    public static ArrayList<Bet> getResults() {
        connect();
        String sql = "SELECT * FROM BET;";
        ArrayList<Bet> bets = new ArrayList<>();
        try {
            PreparedStatement stmt = SQLDriverConnection.conn.prepareStatement(sql);
            ResultSet betList = stmt.executeQuery();
            while (betList.next()) {
                bets.add(new Bet(betList.getString(1), betList.getString(2), betList.getString(3), betList.getString(4), betList.getString(5), betList.getInt(6)));
            }
            SQLDriverConnection.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return bets;
        }

    }

    /**
     * Adds a new Bet to the Table Bet. Needs home, away, goals for both teams and a user who entered the bet.
     *
     * @param home  - Hometeamname
     * @param goalH - Goals home
     * @param goalA - Goals away
     * @param away  - Awayteamname
     * @param user  - User
     * @return Boolean - true if success, false if not
     */
    public static boolean addBet(String home, int goalH, int goalA, String away, String user) {
        connect();
        String sql = "INSERT INTO BET (home, away, estHome, estAway, user) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = SQLDriverConnection.conn.prepareStatement(sql);
            stmt.setString(1, home);
            stmt.setInt(3, goalH);
            stmt.setInt(4, goalA);
            stmt.setString(2, away);
            stmt.setString(5, user);

            int success = stmt.executeUpdate();
            if (success > 0) return true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Gets all Bets and adds them to an ObservableList<Bet>
     *
     * @return Observable<Bet>
     */
    public static ObservableList<Bet> getObservableBets() {
        ArrayList<Bet> bets = getResults();
        ObservableList<Bet> temp2 = FXCollections.observableArrayList();
        for (Bet b : bets) {
            temp2.add(b);
        }
        return temp2;
    }

    /**
     * Selects all Bets to a certain game home vs. away
     *
     * @param home - Hometeamname
     * @param away - Awayteamname
     * @return ArrayList<Bet>
     */
    public static ArrayList<Bet> getGameBets(String home, String away) {
        connect();
        System.out.println(home);
        System.out.println(away);
        String sql = "SELECT * FROM BET WHERE home = ? AND away = ?;";
        try {
            PreparedStatement stmt = SQLDriverConnection.conn.prepareStatement(sql);
            stmt.setString(1, home);
            stmt.setString(2, away);
            ArrayList<Bet> bets = new ArrayList<Bet>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                bets.add(new Bet(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
            }
            return bets;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Selects all bets except bets to a certain game home vs. away
     *
     * @param home - Hometeamname
     * @param away - Awayteamname
     * @return ArrayList<Bet>
     */
    public static ArrayList<Bet> exceptGame(String home, String away) {
        connect();
        String sql = "SELECT * FROM BET EXCEPT SELECT * FROM BET WHERE home = ? AND away = ?;";
        try {
            PreparedStatement stmt = SQLDriverConnection.conn.prepareStatement(sql);
            stmt.setString(1, home);
            stmt.setString(2, away);
            ArrayList<Bet> bets = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                bets.add(new Bet(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
            }
            return bets;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get Bets of all Games except the one highlighted in the Results Table
     *
     * @param home - String of highlighted hometeam
     * @param away - String of highlighted awayteam
     * @return ObservableList<Bet> containing all Bets except from pair home vs. away
     */
    public static ObservableList<Bet> getGameExceptFirst(String home, String away) {
        ArrayList<Bet> bets = exceptGame(home, away);
        ObservableList<Bet> obsBet = FXCollections.observableArrayList();
        for (Bet b : bets) {
            obsBet.add(b);
        }
        return obsBet;
    }

    /**
     * Get Bets to a certain pairing of a game.
     *
     * @param home - String of the hometeam
     * @param away - String of the awayteam
     * @return ObservableList<Bet> containing all Bets.
     */
    public static ObservableList<Bet> getGameBetsFirst(String home, String away) {
        ArrayList<Bet> bets = getGameBets(home, away);
        ObservableList<Bet> obsBet = FXCollections.observableArrayList();
        for (Bet b : bets) {
            obsBet.add(b);
        }
        return obsBet;
    }


    /**
     * Deletes a bet from table bet
     *
     * @param id - The id of a given bet (unique)
     * @return true if success, false otherwise
     */
    public static boolean deleteBet(int id) {
        SQLDriverConnection.connect();
        String sql = "DELETE FROM BET WHERE bet_id = ?";
        try {
            PreparedStatement stmt = SQLDriverConnection.conn.prepareStatement(sql);
            stmt.setInt(1, id);
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
     * Deletes bets from a certain user from table bet
     *
     * @param user
     * @return true if success, false otherwise
     */
    public static boolean deleteBetUser(String user) {
        SQLDriverConnection.connect();
        String sql = "DELETE FROM Bet WHERE user = ?";
        try {
            PreparedStatement stmt = SQLDriverConnection.conn.prepareStatement(sql);
            stmt.setString(1, user);
            int result = stmt.executeUpdate();
            SQLDriverConnection.conn.close();
            if(result > 0) return true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
