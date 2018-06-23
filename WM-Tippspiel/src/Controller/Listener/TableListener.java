package Controller.Listener;

import Controller.Database.BetTable;
import Model.Bet;
import Model.Match;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableView;

public class TableListener {

    public static TableView<Match> addChangeListener(TableView<Match> tableMatch, TableView<Bet> tableBet) {
        tableMatch.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Model.Match selected = tableMatch.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    tableBet.setItems(BetTable.getGameBetsFirst(selected.getHome(), selected.getAway()));

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
