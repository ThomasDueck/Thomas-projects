package sample;

import Data.Match;
import Data.Team;
import Database.SQLDriverConnection;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    private static TableView<Match> table = new TableView<>();
    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(new Group());
        primaryStage.setWidth(800);
        primaryStage.setHeight(700);
        primaryStage.setTitle("Hello World");


        final HBox hbox = new HBox();

        hbox.getChildren().addAll(GUI.DrawResults.showResults(table));
        ((Group) scene.getRoot()).getChildren().addAll(hbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }






    public static void main(String[] args) {
        launch(args);
    }
}
