package View;

import Controller.Database.BetTable;
import Controller.Database.ResultsTable;
import Model.Bet;
import Model.Match;
import Model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;


public class AddMatchGUI {

    public static HBox addButtonMatch(TableView<Match> table){

        final HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 0, 0, 0));
        TextField homeTeam = new TextField();
        homeTeam.setMaxWidth(40);
        TextField awayTeam = new TextField();
        awayTeam.setMaxWidth(40);
        Label lbl = new Label();
        lbl.setText(":");

        //Obtain competing teams and create choiceboxes for both teams.
        ComboBox<String> home = new ComboBox<>();
        ComboBox<String> away = new ComboBox<>();
        away.setItems(Controller.Database.TeamsTable.getObservableMatchesName());
        home.setItems(Controller.Database.TeamsTable.getObservableMatchesName());
        home.setPromptText("Home");
        away.setPromptText("Away");

        Button add = new Button("Add result");

        final TextField awayTeam2 = Controller.Listener.TextfieldListener.onlyNumbers(awayTeam);
        final TextField homeTeam2 = Controller.Listener.TextfieldListener.onlyNumbers(homeTeam);


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

    public static HBox addButtonBet(TableView<Bet> table){

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 0, 0, 0));
        TextField homeTeam = new TextField();
        homeTeam.setMaxWidth(40);
        TextField awayTeam = new TextField();
        awayTeam.setMaxWidth(40);
        Label lbl = new Label();
        lbl.setText(":");

        //Obtain competing teams and create choiceboxes for both teams.
        ComboBox<String> home = new ComboBox<>();
        ComboBox<String> away = new ComboBox<>();
        ComboBox<String> user = new ComboBox<>();
        home.setPromptText("Home");
        away.setPromptText("Away");
        user.setPromptText("User");

        away.setItems(Controller.Database.TeamsTable.getObservableMatchesName());
        home.setItems(Controller.Database.TeamsTable.getObservableMatchesName());
        user.setItems(Controller.Database.UserTable.getObservableUser());


        Button add = new Button("Add result");

        final TextField awayTeam2 = Controller.Listener.TextfieldListener.onlyNumbers(awayTeam);
        final TextField homeTeam2 = Controller.Listener.TextfieldListener.onlyNumbers(homeTeam);


        add.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                BetTable.addBet(home.getValue(),
                        Integer.parseInt(homeTeam2.getText()), Integer.parseInt(awayTeam2.getText()), away.getValue(), user.getValue());
                table.setItems(BetTable.getObservableBets());
                //TODO
            }
        });

        hbox.getChildren().addAll(home, homeTeam, lbl, awayTeam, away, user, add);
        hbox.setSpacing(10);
        return hbox;
    }


}
