package View;

import Controller.Listener.ButtonListener;
import Controller.Listener.TableListener;
import Model.Bet;
import Model.Match;
import Controller.Database.ResultsTable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class DrawResults {

    /**
     * Create the VBox for the left side, which corresponds to the Results.
     * This VBox consists of: A label, a table, a bar for adding results, and a delete button.
     * The bar for adding results is generated in {@link AddMatchGUI#addButton(int, TableView, TableView)}
     * @param tableMatch
     * @return
     */
    public static VBox showResults(TableView<Match> tableMatch, TableView<Bet> tableBet){

        //Create Table Results for left Table
        Label label = new Label("Results");
        label.setFont(new Font("Arial", 20));
        tableMatch.setPlaceholder(new Label("No Results filled in yet"));


        //Create Columns for left Table and add them
        tableMatch.setEditable(true);
        TableColumn homeCol = new TableColumn("Hometeam");
        TableColumn homeGoalCol = new TableColumn("Goals Home");
        TableColumn awayGoalCol = new TableColumn("Goals Away");
        TableColumn awayCol = new TableColumn("Awayteam");
        tableMatch.getColumns().addAll(homeCol, homeGoalCol, awayGoalCol, awayCol);
        tableMatch.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //Convert Class Attributes to Columns
        homeCol.setCellValueFactory(new PropertyValueFactory<Match, String>("home"));
        homeGoalCol.setCellValueFactory(new PropertyValueFactory<Match, String>("goalH"));
        awayGoalCol.setCellValueFactory(new PropertyValueFactory<Match, String>("goalA"));
        awayCol.setCellValueFactory(new PropertyValueFactory<Match, String>("away"));

        //Add all matches to the table
        tableMatch.setItems(ResultsTable.getObservableMatches());
        Button del = new Button("Delete highlighted result");
        del = ButtonListener.generateListenerDelResult(del, tableMatch);

        //Listener for more structure of Bets: All corresponding bets to a highlighted matches are grouped at the top.
        tableMatch = TableListener.addChangeListener(tableMatch,tableBet);

        //Finally add them to the Vbox which then gets added to the scene
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, tableMatch, AddMatchGUI.addButton(0,tableMatch, tableBet), del);

        return vbox;
    }



}
