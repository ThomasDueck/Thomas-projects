package sample;

import Data.Team;
import Database.SQLDriverConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        SQLDriverConnection.connect();
        ArrayList<Team> teams = SQLDriverConnection.getTeams();
        for(Team t : teams){
            System.out.println(t.getTeamname());
        }
        launch(args);
    }
}
