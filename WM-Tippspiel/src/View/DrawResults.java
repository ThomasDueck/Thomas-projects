package View;

import Controller.Listener.ButtonListener;
import Controller.Listener.TableListener;
import Model.Bet;
import Model.Match;
import Controller.Database.ResultsTable;
import StaticData.LayoutData;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class DrawResults {

    /**
     * Create the VBox for the left side, which corresponds to the Results.
     * This VBox consists of: A label, a table, a bar for adding results, and a delete button.
     * The bar for adding results is generated in {@link AddTables#addButton(int, TableView, TableView)}
     * @param tableMatch
     * @return
     */
    public static VBox showResults(TableView<Match> tableMatch, TableView<Bet> tableBet){

        //Create Table Results for left Table
        Label label = new Label(LayoutData.RESULTTABLEHEADLINE);
        label.setFont(new Font(LayoutData.FONT, LayoutData.FONTSIZE));
        label.setTextFill(Color.web(LayoutData.COLOR));
        tableMatch.setPlaceholder(new Label(LayoutData.RESULTTABLEPLACEHOLDER));


        //Create Columns for left Table and add them
        tableMatch.setEditable(true);
        TableColumn homeCol = new TableColumn(LayoutData.COLHOME);
        TableColumn homeGoalCol = new TableColumn(LayoutData.COLGOALHOME);
        TableColumn awayGoalCol = new TableColumn(LayoutData.COLGOALAWAY);
        TableColumn awayCol = new TableColumn(LayoutData.COLAWAY);
        tableMatch.getColumns().addAll(homeCol, homeGoalCol, awayGoalCol, awayCol);
        tableMatch.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //Convert Class Attributes to Columns
        homeCol.setCellValueFactory(new PropertyValueFactory<Match, String>("home"));
        homeGoalCol.setCellValueFactory(new PropertyValueFactory<Match, String>("goalH"));
        awayGoalCol.setCellValueFactory(new PropertyValueFactory<Match, String>("goalA"));
        awayCol.setCellValueFactory(new PropertyValueFactory<Match, String>("away"));

        //Add all matches to the table
        tableMatch.setItems(ResultsTable.getObservableMatches());
        Button del = new Button(LayoutData.DELRESULT);
        del = ButtonListener.generateListenerDelResult(del, tableMatch);
        del.setStyle(LayoutData.CANCEL);

        //Listener for more structure of Bets: All corresponding bets to a highlighted matches are grouped at the top.
        tableMatch = TableListener.addChangeListener(tableMatch,tableBet);

        //Finally add them to the Vbox which then gets added to the scene
        final VBox vbox = new VBox();
        vbox.setSpacing(LayoutData.SPACING);
        vbox.setPadding(new Insets(LayoutData.PADDING, 0, 0, LayoutData.PADDINGLEFT));
        vbox.getChildren().addAll(label, tableMatch, AddTables.addButton(0,tableMatch, tableBet), del);

        return vbox;
    }



}
