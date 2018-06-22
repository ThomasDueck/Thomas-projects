package GUI;

import Data.Match;
import Data.Team;
import Database.ResultsTable;
import Database.TeamsTable;
import javafx.beans.property.ReadOnlyObjectProperty;
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

        //Create Columns for left Table and add them
        table.setEditable(true);
        TableColumn homeCol = new TableColumn("Hometeam");
        TableColumn homeGoalCol = new TableColumn("Goals Home");
        TableColumn awayGoalCol = new TableColumn("Goals Away");
        TableColumn awayCol = new TableColumn("Awayteam");
        table.getColumns().addAll(homeCol, homeGoalCol, awayGoalCol, awayCol);

        //Convert Class Attributes to Columns
        homeCol.setCellValueFactory(new PropertyValueFactory<Match, String>("home"));
        homeGoalCol.setCellValueFactory(new PropertyValueFactory<Match, String>("goalH"));
        awayGoalCol.setCellValueFactory(new PropertyValueFactory<Match, String>("goalA"));
        awayCol.setCellValueFactory(new PropertyValueFactory<Match, String>("away"));

        //Add all matches to the table
        ArrayList<Match> matches = ResultsTable.getResults();
        ObservableList<Match> data = FXCollections.observableArrayList();
        for(Match m : matches){
            data.add(m);
        }
        table.setItems(data);
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
        vbox.getChildren().addAll(label, table, addButton(table), del);

        return vbox;
    }

    public static HBox addButton(TableView<Match> table){

        final HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 0, 0, 0));
        TextField homeTeam = new TextField();
        homeTeam.setMaxWidth(40);
        TextField awayTeam = new TextField();
        awayTeam.setMaxWidth(40);
        Label lbl = new Label();
        lbl.setText(":");

        //Obtain competing teams and create choiceboxes for both teams.
        ObservableList<String> data = FXCollections.observableArrayList();
        ArrayList<Team> teams = TeamsTable.getTeams();
        for(Team t : teams){
            data.add(t.getTeamname());
        }
        ChoiceBox<String> home = new ChoiceBox<>();
        ChoiceBox<String> away = new ChoiceBox<>();
        away.setItems(data);
        home.setItems(data);

        Button add = new Button("Add result");

        final TextField awayTeam2 = Listener.TextfieldListener.onlyNumbers(awayTeam);
        final TextField homeTeam2 = Listener.TextfieldListener.onlyNumbers(homeTeam);


        add.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                ResultsTable.addResults(home.getValue(),
                        Integer.parseInt(homeTeam2.getText()), Integer.parseInt(awayTeam2.getText()), away.getValue());
                table.setItems(ResultsTable.getObservableMatches());

            }
        });

        hbox.getChildren().addAll(home, homeTeam, lbl, awayTeam, away, add);
        hbox.setSpacing(10);
        return hbox;
    }
}
