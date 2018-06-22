package Controller.Database;

import Model.Bet;
import Model.Match;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Controller.Database.SQLDriverConnection.connect;

public class BetTable {

    public static ArrayList<Bet> getResults() {

        connect();
        String sql = "SELECT * FROM BET;";
        ArrayList<Bet> bets = new ArrayList<>();
        try {
            PreparedStatement stmt = SQLDriverConnection.conn.prepareStatement(sql);
            ResultSet betList = stmt.executeQuery();
            while (betList.next()) {
                bets.add(new Bet(betList.getString(1), betList.getString(2), betList.getString(3), betList.getString(4), betList.getString(5)));
            }
            SQLDriverConnection.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return bets;
        }

    }

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

    public static ObservableList<Bet> getObservableBets(){
        ArrayList<Bet> bets = getResults();
        ObservableList<Bet> temp2 = FXCollections.observableArrayList();
        for(Bet b : bets){
            temp2.add(b);
        }
        return temp2;
    }

    public static ArrayList<Bet> getGameBets(String home, String away){
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
            while(rs.next()){
                bets.add(new Bet(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
            }
            return bets;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;

    }

    public static ArrayList<Bet> exceptGame(String home, String away){
        connect();
        String sql = "SELECT * FROM BET EXCEPT SELECT * FROM BET WHERE home = ? AND away = ?;";
        try {
            PreparedStatement stmt = SQLDriverConnection.conn.prepareStatement(sql);
            stmt.setString(1, home);
            stmt.setString(2, away);
            ArrayList<Bet> bets = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                bets.add(new Bet(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
            }
            return bets;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<Bet> getGameExceptFirst(String home, String away){
        ArrayList<Bet> bets = exceptGame(home, away);
        ObservableList<Bet> obsBet = FXCollections.observableArrayList();
        for(Bet b : bets){
            obsBet.add(b);
        }
        return obsBet;
    }

    public static ObservableList<Bet> getGameBetsFirst(String home, String away){
        ArrayList<Bet> bets = getGameBets(home, away);
        ObservableList<Bet> obsBet = FXCollections.observableArrayList();
        for(Bet b : bets){
            obsBet.add(b);
        }
        return obsBet;
    }
}
