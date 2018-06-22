package Main;

import Controller.Database.SQLDriverConnection;
import Model.Bet;
import Model.Match;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    private static TableView<Match> resultTable = new TableView<>();
    private static TableView<Bet> betTable = new TableView<>();
    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(new Group());
        primaryStage.setWidth(1200);
        primaryStage.setHeight(700);
        primaryStage.setTitle("Hello World");


        final HBox hbox = new HBox();

        hbox.getChildren().addAll(View.DrawResults.showResults(resultTable), View.DrawBet.showBets(betTable, resultTable));
        hbox.setSpacing(100);
        ((Group) scene.getRoot()).getChildren().addAll(hbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }






    public static void main(String[] args) {
        launch(args);
    }
}
