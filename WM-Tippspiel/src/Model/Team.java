package model;

public class Team {

    public String teamname;
    public int id;

    public Team(String teamname, int id) {
        this.teamname = teamname;
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }


}
