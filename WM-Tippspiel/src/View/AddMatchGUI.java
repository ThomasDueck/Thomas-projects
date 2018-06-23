package View;

import Controller.Listener.ButtonListener;
import Model.Bet;
import Model.Match;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;


public class AddMatchGUI {


    /**
     * Creates the addResult/addBet Bar, and returns a HBox with the complete layout with all buttons and structures needed to
     * create a new valid bet or result.
     *
     * @param mode       - 0 if it is a Match, 1 if it is a bet.
     * @param tableMatch - Table for Results
     * @param tableBet   - Table for Bets
     * @return HBox
     */
    public static HBox addButton(int mode, TableView<Match> tableMatch, TableView<Bet> tableBet) {

        //Create the TextFields to insert Goals.
        //Listener only allows for numbers in the box, so only valid input is allowed.
        TextField homeTeam = new TextField();
        TextField awayTeam = new TextField();
        homeTeam.setMaxWidth(40);
        awayTeam.setMaxWidth(40);
        awayTeam = Controller.Listener.TextfieldListener.onlyNumbers(awayTeam);
        homeTeam = Controller.Listener.TextfieldListener.onlyNumbers(homeTeam);

        //Label for Layout
        Label lbl = new Label();
        lbl.setText(":");

        //Creating the Comboboxes for Hometeam, AwayTeam and Users. User is only needed for Bets.
        ComboBox<String> home = new ComboBox<>();
        ComboBox<String> away = new ComboBox<>();
        ComboBox<String> user = new ComboBox<>();

        home.setPromptText("Home");
        away.setPromptText("Away");
        user.setPromptText("User");

        away.setItems(Controller.Database.TeamsTable.getObservableTeamNames());
        home.setItems(Controller.Database.TeamsTable.getObservableTeamNames());
        user.setItems(Controller.Database.UserTable.getObservableUser());

        //Create add Button. 0 corresponds to a Match, thus does not need user, and 1 corresponds Bet and needs
        //attribute user.
        Button add = new Button();
        if (mode == 0) {
            add = new Button("Add Result");
            add = ButtonListener.generateListenerAddResult(add, tableMatch, home, homeTeam, awayTeam, away);
        } else if (mode == 1) {
            add = new Button("Add Bet");
            add = ButtonListener.generateListenerAddBet(add, tableBet, home, homeTeam, awayTeam, away, user);
        }

        //Finally, create a new HBox and return it.
        //Bets have the additional user ComboBox.
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 0, 0, 0));
        if (mode == 0) {
            hbox.getChildren().addAll(home, homeTeam, lbl, awayTeam, away, add);
        } else if (mode == 1) {
            hbox.getChildren().addAll(home, homeTeam, lbl, awayTeam, away, user, add);
        }
        hbox.setSpacing(10);
        return hbox;
    }


}
