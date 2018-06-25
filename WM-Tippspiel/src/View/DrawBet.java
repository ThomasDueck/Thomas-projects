package view;

import controller.database.BetTable;
import controller.listener.ButtonListener;
import model.Bet;
import model.Match;
import staticData.LayoutData;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DrawBet {


    /**
     * Create the VBox for the right side, which corresponds to the Bet.
     * This VBox consists of: A label, a table, a bar for adding bets, and a delete button.
     * The bar for adding bets is generated in {@link AddTables#addButton(int, TableView, TableView)}
     * @param - TableView<Bet>, TableView<Match>
     * @return VBox
     */
    public static VBox showBets(TableView<Match> tableMatch, TableView<Bet> tableBet) {

        //Set title of table
        Label label = new Label(LayoutData.BETTABLEHEADLINE);
        label.setFont(new Font(LayoutData.FONT, LayoutData.FONTSIZE));
        label.setTextFill(Color.web(LayoutData.COLOR));


        //Tableskeleton, add columns and properly resize table.
        tableBet.setPlaceholder(new Label(LayoutData.BETTABLEPLACEHOLDER));
        tableBet.setEditable(true);

        TableColumn homeCol = new TableColumn(LayoutData.COLHOME);
        TableColumn homeGoalCol = new TableColumn(LayoutData.COLGOALHOME);
        TableColumn awayGoalCol = new TableColumn(LayoutData.COLGOALAWAY);
        TableColumn awayCol = new TableColumn(LayoutData.COLAWAY);
        TableColumn user = new TableColumn(LayoutData.COLUSER);
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
        Button del = new Button(LayoutData.DELBET);
        del = ButtonListener.generateListenerDelBet(del, tableBet);
        del.setStyle(LayoutData.CANCEL);


        //Create a VBox which corresponds Bets, the right side of the table.
        final VBox vbox = new VBox();
        vbox.setSpacing(LayoutData.SPACING);
        vbox.setPadding(new Insets(LayoutData.PADDING, LayoutData.PADDINGRIGHT, 0, LayoutData.PADDING));
        vbox.getChildren().addAll(label, tableBet, AddTables.addButton(1,tableMatch, tableBet), del);
        return vbox;
    }
}
