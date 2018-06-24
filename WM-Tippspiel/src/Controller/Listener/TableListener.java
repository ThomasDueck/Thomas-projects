package Controller.Listener;

import Controller.Database.BetTable;
import Model.Bet;
import Model.Match;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableView;

public class TableListener {

    /**
     * Adds a Listener to a MatchTable. If the selected row changes, the Bet Table is rearranged to display
     * the Bets corresponding to the Result first with a empty row between the match specific bets and bets for other matches
     *
     * @param tableMatch - TableView for Results/Matches
     * @param tableBet   - TableView for Bets
     * @return TableView<Match> - with correct Listener if changed
     */
    public static TableView<Match> addChangeListener(TableView<Match> tableMatch, TableView<Bet> tableBet) {
        tableMatch.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Model.Match selected = tableMatch.getSelectionModel().getSelectedItem();
                if (selected != null) {

                    //First get the corresponding matches.
                    tableBet.setItems(BetTable.getGameBetsFirst(selected.getHome(), selected.getAway()));

                    //If there were any, insert an empty row, and then add all other bets.
                    if (tableBet.getItems().size() > 0) {
                        tableBet.getItems().add(new Bet("", "", "", "", ""));
                    }
                    tableBet.getItems().addAll(BetTable.getGameExceptFirst(selected.getHome(), selected.getAway()));
                }
            }

        });

        return tableMatch;
    }
}
