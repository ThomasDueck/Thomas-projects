package Model;

public class User {

    public String user;
    public int id;

    public User(String user, int id) {
        this.user = user;
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String teamname) {
        this.user = teamname;
    }


}
