package Main;

import Model.Bet;
import Model.Match;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.css.PseudoClass;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private static TableView<Match> resultTable = new TableView<>();
    private static TableView<Bet> betTable = new TableView<>();

    private static int WIDTH = 1200;
    private static int HEIGHT = 700;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(new Group());
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        primaryStage.setTitle("Soccer World Cup Bet Game");

        final VBox vbox = new VBox(10);
        final HBox hbox = new HBox(100);
        HBox userMenu = View.AddUserGUI.addUser(resultTable, betTable, new ComboBox<>());
        Label lbl = new Label("Edit Users");

        hbox.getChildren().addAll(View.DrawResults.showResults(resultTable, betTable), View.DrawBet.showBets(resultTable, betTable));
        vbox.getChildren().addAll(hbox, lbl, userMenu);
        vbox.setMargin(userMenu, new Insets(0, 0, 0, WIDTH / 3));
        vbox.setMargin(lbl, new Insets(0, 0, 0, WIDTH / 3));

        hbox.setSpacing(100);
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        primaryStage.setScene(scene);
        primaryStage.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
