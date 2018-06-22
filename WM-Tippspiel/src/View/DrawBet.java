package View;

import Controller.Database.BetTable;
import Controller.Database.ResultsTable;
import Model.Bet;
import Model.Match;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class DrawBet {

    public static VBox showBets(TableView<Bet> table, TableView<Match> tableMatch){
        Label label = new Label("Bets");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);
        TableColumn homeCol = new TableColumn("Hometeam");
        TableColumn homeGoalCol = new TableColumn("Goals Home");
        TableColumn awayGoalCol = new TableColumn("Goals Away");
        TableColumn awayCol = new TableColumn("Awayteam");
        TableColumn user = new TableColumn("User");
        table.getColumns().addAll(homeCol, homeGoalCol, awayGoalCol, awayCol, user);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        homeCol.setCellValueFactory(new PropertyValueFactory<Match, String>("home"));
        homeGoalCol.setCellValueFactory(new PropertyValueFactory<Match, String>("goalH"));
        awayGoalCol.setCellValueFactory(new PropertyValueFactory<Match, String>("goalA"));
        awayCol.setCellValueFactory(new PropertyValueFactory<Match, String>("away"));
        user.setCellValueFactory(new PropertyValueFactory<Match, String>("user"));

        table.setItems(BetTable.getObservableBets());

        tableMatch.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Match selected = tableMatch.getSelectionModel().getSelectedItem();
                table.setItems(BetTable.getGameBetsFirst(selected.getHome(), selected.getAway()));
                if(table.getItems().size() > 0){
                    table.getItems().add(new Bet("", "", "", "", ""));
                }
                table.getItems().addAll(BetTable.getGameExceptFirst(selected.getHome(), selected.getAway()));

            }

        });


        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10,0,0,10));
        vbox.getChildren().addAll(label, table, addUser(table));
        return vbox;
    }

    public static HBox addUser(TableView<Bet> table){
        HBox hbox = AddMatchGUI.addButtonBet(table);
        return hbox;

    }
}
