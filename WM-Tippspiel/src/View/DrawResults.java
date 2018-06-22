package View;

import Controller.Database.BetTable;
import Model.Bet;
import Model.Match;
import Model.Team;
import Controller.Database.ResultsTable;
import Controller.Database.TeamsTable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class DrawResults {

    public static VBox showResults(TableView<Match> table){

        //Create Table Results for left Table
        Label label = new Label("Results");
        label.setFont(new Font("Arial", 20));
        table.setPlaceholder(new Label("No Results filled in yet"));


        //Create Columns for left Table and add them
        table.setEditable(true);
        TableColumn homeCol = new TableColumn("Hometeam");
        TableColumn homeGoalCol = new TableColumn("Goals Home");
        TableColumn awayGoalCol = new TableColumn("Goals Away");
        TableColumn awayCol = new TableColumn("Awayteam");
        table.getColumns().addAll(homeCol, homeGoalCol, awayGoalCol, awayCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //Convert Class Attributes to Columns
        homeCol.setCellValueFactory(new PropertyValueFactory<Match, String>("home"));
        homeGoalCol.setCellValueFactory(new PropertyValueFactory<Match, String>("goalH"));
        awayGoalCol.setCellValueFactory(new PropertyValueFactory<Match, String>("goalA"));
        awayCol.setCellValueFactory(new PropertyValueFactory<Match, String>("away"));

        //Add all matches to the table
        table.setItems(ResultsTable.getObservableMatches());
        Button del = new Button("Delete highlighted result");
        del.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){

                Match a = table.getSelectionModel().getSelectedItem();
                if(a != null) ResultsTable.deleteResult(a.getId());
                table.setItems(ResultsTable.getObservableMatches());
            }
        });
        //Finally add them to the Vbox which then gets added to the scene
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, AddMatchGUI.addButtonMatch(table), del);

        return vbox;
    }



}
