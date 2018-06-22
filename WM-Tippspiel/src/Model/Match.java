package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Match {

    public SimpleStringProperty home;
    public SimpleStringProperty away;
    public SimpleIntegerProperty goalH;
    public SimpleIntegerProperty goalA;
    public SimpleIntegerProperty id;

    public Match(String home, String away, int goalH, int goalA) {
        this.home = new SimpleStringProperty(home);
        this.away = new SimpleStringProperty(away);
        this.goalH = new SimpleIntegerProperty(goalH);
        this.goalA = new SimpleIntegerProperty(goalA);
    }

    public Match(String home, String away, int goalH, int goalA, int id) {
        this.home = new SimpleStringProperty(home);
        this.away = new SimpleStringProperty(away);
        this.goalH = new SimpleIntegerProperty(goalH);
        this.goalA = new SimpleIntegerProperty(goalA);
        this.id = new SimpleIntegerProperty(id);
    }

    public String getHome() {
        return home.get();
    }

    public String getAway() {
        return away.get();
    }

    public int getGoalH() {
        return goalH.get();
    }

    public int getGoalA() {
        return goalA.get();
    }

    public int getId() {
        return id.get();
    }

    public void setHome(String tHome) {
        home.set(tHome);
    }

    public void setAway(String tAway) {
        away.set(tAway);
    }

    public void setGoalH(int tGoalH) {
        goalH.set(tGoalH);
    }

    public void setGoalA(int tGoalA) {
        goalA.set(tGoalA);
    }

    public void setId(int id) {
        this.id.set(id);
    }
}
