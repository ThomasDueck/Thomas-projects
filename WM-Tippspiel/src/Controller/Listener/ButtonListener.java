package Controller.Listener;

import Controller.Database.BetTable;
import Controller.Database.ResultsTable;
import Controller.Database.UserTable;
import Model.Bet;
import Model.Match;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ButtonListener {


    public static Button generateListenerDelBet(Button del, TableView<Bet> tableBet) {
        del.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                Bet bet = tableBet.getSelectionModel().getSelectedItem();
                if (bet != null) BetTable.deleteBet(bet.getId());
                tableBet.setItems(BetTable.getObservableBets());
            }
        });
    return del;
    }

    public static Button generateListenerDelResult(Button del, TableView<Match> tableMatch){
        del.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){

                Match a = tableMatch.getSelectionModel().getSelectedItem();
                if(a != null) ResultsTable.deleteResult(a.getId());
                tableMatch.setItems(ResultsTable.getObservableMatches());
            }
        });
        return del;
    }

    public static Button generateListenerAddBet(Button add, TableView<Bet> tableBet, ComboBox<String> home, TextField homeTeam2, TextField awayTeam2, ComboBox<String> away, ComboBox<String> user){
        add.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                BetTable.addBet(home.getValue(),
                        Integer.parseInt(homeTeam2.getText()), Integer.parseInt(awayTeam2.getText()), away.getValue(), user.getValue());
                tableBet.setItems(BetTable.getObservableBets());
                //TODO
            }
        });
        return add;
    }

    public static Button generateListenerAddResult(Button add, TableView<Match> tableMatch, ComboBox<String> home, TextField homeTeam2, TextField awayTeam2, ComboBox<String> away){
        add.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                ResultsTable.addResults(home.getValue(),
                        Integer.parseInt(homeTeam2.getText()), Integer.parseInt(awayTeam2.getText()), away.getValue());
                tableMatch.setItems(ResultsTable.getObservableMatches());
                //TODO
            }
        });
        return add;
    }

    public static Button generateListenerAddUser(Button add, TextField user){
        add.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                UserTable.addUser(user.getText());

            }
        });
        return add;
    }

    public static Button generateListenerDelUser(Button del, TextField user, TableView<Bet> tableBet){
        del.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                UserTable.delUser(user.getText());
                System.out.println(BetTable.deleteBetUser(user.getText()));

                tableBet.setItems(BetTable.getObservableBets());
            }
        });
        return del;
    }




}
