package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Bet {

    public SimpleStringProperty home;
    public SimpleStringProperty away;
    public SimpleStringProperty goalH;
    public SimpleStringProperty goalA;
    public SimpleIntegerProperty id;
    public SimpleStringProperty user;

    public Bet() {

    }

    public Bet(String home, String away, String goalH, String goalA, String user) {
        this.home = new SimpleStringProperty(home);
        this.away = new SimpleStringProperty(away);
        this.goalH = new SimpleStringProperty(goalH);
        this.goalA = new SimpleStringProperty(goalA);
        this.user = new SimpleStringProperty(user);
    }

    public Bet(String home, String away, String goalH, String goalA, String user, int id) {
        this.home = new SimpleStringProperty(home);
        this.away = new SimpleStringProperty(away);
        this.goalH = new SimpleStringProperty(goalH);
        this.goalA = new SimpleStringProperty(goalA);
        this.id = new SimpleIntegerProperty(id);
        this.user = new SimpleStringProperty(user);
    }


    public String getHome() {
        return home.get();
    }

    public String getAway() {
        return away.get();
    }

    public String getGoalH() {
        return goalH.get();
    }

    public String getGoalA() {
        return goalA.get();
    }

    public int getId() {
        return id.get();
    }

    public String getUser() {
        return user.get();
    }

    public void setHome(String tHome) {
        home.set(tHome);
    }

    public void setAway(String tAway) {
        away.set(tAway);
    }

    public void setGoalH(String tGoalH) {
        goalH.set(tGoalH);
    }

    public void setGoalA(String tGoalA) {
        goalA.set(tGoalA);
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public void setUser(String name) {
        this.user.set(name);
    }


}
