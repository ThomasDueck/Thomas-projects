package View;

import Controller.Database.BetTable;
import Controller.Listener.ButtonListener;
import Model.Bet;
import Model.Match;
import javafx.css.PseudoClass;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class DrawBet {

    /**
     * Create the VBox for the right side, which corresponds to the Bet.
     * This VBox consists of: A label, a table, a bar for adding bets, and a delete button.
     * The bar for adding bets is generated in {@link AddMatchGUI#addButton(int, TableView, TableView)}
     * @param TableView<Bet>, TableView<Match>
     * @return VBox
     */
    public static VBox showBets(TableView<Match> tableMatch, TableView<Bet> tableBet) {

        //Set title of table
        Label label = new Label("Bets");
        label.setFont(new Font("Arial", 20));

        //Tableskeleton, add columns and properly resize table.
        tableBet.setPlaceholder(new Label("No Bets made yet"));
        tableBet.setEditable(true);

        TableColumn homeCol = new TableColumn("Hometeam");
        TableColumn homeGoalCol = new TableColumn("Goals Home");
        TableColumn awayGoalCol = new TableColumn("Goals Away");
        TableColumn awayCol = new TableColumn("Awayteam");
        TableColumn user = new TableColumn("User");
        tableBet.getColumns().addAll(homeCol, homeGoalCol, awayGoalCol, awayCol, user);
        tableBet.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //Set CellValueFactories, so the Columns correspond to Attributes of Bet
        homeCol.setCellValueFactory(new PropertyValueFactory<Bet, String>("home"));
        homeGoalCol.setCellValueFactory(new PropertyValueFactory<Bet, String>("goalH"));
        awayGoalCol.setCellValueFactory(new PropertyValueFactory<Bet, String>("goalA"));
        awayCol.setCellValueFactory(new PropertyValueFactory<Bet, String>("away"));
        user.setCellValueFactory(new PropertyValueFactory<Bet, String>("user"));

        //Finally fill the table with all Bets that are available and saved.
        tableBet.setItems(BetTable.getObservableBets());

        //Generate Delete Bet Button with functionality
        Button del = new Button("Delete highlighted bet");
        del = ButtonListener.generateListenerDelBet(del, tableBet);


        //Create a VBox which corresponds Bets, the right side of the table.
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, tableBet, AddMatchGUI.addButton(1,tableMatch, tableBet), del);
        return vbox;
    }
}
