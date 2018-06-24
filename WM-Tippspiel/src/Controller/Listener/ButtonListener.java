package Controller.Listener;

import Controller.Database.BetTable;
import Controller.Database.ResultsTable;
import Controller.Database.UserTable;
import Model.Bet;
import Model.Match;
import StaticData.StatusMessages;
import View.DrawScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ButtonListener {


    /**
     * Adds a Listener to the Delete Bet Button and updates the Bet Table. Also deletes the Bet from the database
     *
     * @param del      - Button
     * @param tableBet - Table to be updated
     * @return Button with proper listener
     */
    public static Button generateListenerDelBet(Button del, TableView<Bet> tableBet) {
        del.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                Bet bet = tableBet.getSelectionModel().getSelectedItem();
                if (bet != null){
                    boolean succ = BetTable.deleteBet(bet.getId());
                    if(succ) DrawScene.setStatus(StatusMessages.BETDELETED);
                    else DrawScene.setStatus(StatusMessages.BETNOTDELETED);
                    tableBet.setItems(BetTable.getObservableBets());
                }
                else{
                    DrawScene.setStatus(StatusMessages.NOBETSELECTED);
                }
            }
        });
        return del;
    }

    /**
     * Adds a Listener to the Delete Result Button and updates the Result Table. Also deletes the Result from the
     * database
     *
     * @param del        - Button
     * @param tableMatch - Table to be updated
     * @return Button with proper listener
     */
    public static Button generateListenerDelResult(Button del, TableView<Match> tableMatch) {
        del.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                Match a = tableMatch.getSelectionModel().getSelectedItem();
                if (a != null){
                    boolean succ = ResultsTable.deleteResult(a.getId());
                    if(succ) DrawScene.setStatus(StatusMessages.RESULTDELETED);
                    else DrawScene.setStatus(StatusMessages.RESULTNOTDELETED);
                    tableMatch.setItems(ResultsTable.getObservableMatches());
                }
                else{
                    DrawScene.setStatus(StatusMessages.NOMATCHSELECTED);
                }
            }
        });
        return del;
    }

    /**
     * Adds the Listener to enter a valid Bet into the database. Needs the data from ComboBoxes and TextFields
     * to also check if it is a valid action. Also adds the bet to the bet table.
     *
     * @param add       - Addbutton of Bet Table
     * @param tableBet  - Bet Table
     * @param home      - Chosen hometeam
     * @param homeTeam2 - Goals scored by hometeam
     * @param awayTeam2 - Goals scored by awayteam
     * @param away      - Chosen awayteam
     * @param user      - User who entered the bet
     * @return Button with added Listener
     */
    public static Button generateListenerAddBet(Button add, TableView<Bet> tableBet, ComboBox<String> home, TextField homeTeam2, TextField awayTeam2, ComboBox<String> away, ComboBox<String> user) {
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if(home.getValue().equals(away.getValue())){
                    DrawScene.setStatus(StatusMessages.AGAINSTITSELF);
                }
                else if (home.getValue() != null && !homeTeam2.getText().equals("") && !awayTeam2.getText().equals("") && away.getValue() != null && user.getValue() != null){

                    boolean succ = BetTable.addBet(home.getValue(),
                            Integer.parseInt(homeTeam2.getText()), Integer.parseInt(awayTeam2.getText()), away.getValue(), user.getValue());
                    if(succ) DrawScene.setStatus(StatusMessages.BETADDED);
                    else DrawScene.setStatus(StatusMessages.BETNOTADDED);
                    tableBet.setItems(BetTable.getObservableBets());
                    homeTeam2.setText("0");
                    awayTeam2.setText("0");
                }
                else{
                    DrawScene.setStatus(StatusMessages.VALUESMISSING);
                }
                //TODO
            }
        });
        return add;
    }

    /**
     * Adds the Listener to enter a valid Matchresult into the database. Needs the data from ComboBoxes and TextFields
     * to also check if it is a valid action. Also adds the match to the match table.
     *
     * @param add       - Addbutton of Bet Table
     * @param tableMatch  - Match Table
     * @param home      - Chosen hometeam
     * @param homeTeam2 - Goals scored by hometeam
     * @param awayTeam2 - Goals scored by awayteam
     * @param away      - Chosen awayteam
     * @return Button with added Listener
     */
    public static Button generateListenerAddResult(Button add, TableView<Match> tableMatch, ComboBox<String> home, TextField homeTeam2, TextField awayTeam2, ComboBox<String> away) {
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if(home.getValue().equals(away.getValue())){
                    DrawScene.setStatus(StatusMessages.AGAINSTITSELF);
                }
                else if (home.getValue() != null && !homeTeam2.getText().equals("") && !awayTeam2.getText().equals("") && away.getValue() != null){
                    boolean succ = ResultsTable.addResults(home.getValue(),
                            Integer.parseInt(homeTeam2.getText()), Integer.parseInt(awayTeam2.getText()), away.getValue());
                    if(succ) DrawScene.setStatus(StatusMessages.RESULTADDED);
                    else DrawScene.setStatus(StatusMessages.RESULTNOTADDED);
                    tableMatch.setItems(ResultsTable.getObservableMatches());
                    homeTeam2.setText("0");
                    awayTeam2.setText("0");

                }
                else{
                    DrawScene.setStatus(StatusMessages.VALUESMISSING);
                }
                //TODO
            }
        });
        return add;
    }

    /**
     * Generates a Listener to enter a user into the database. First checks if the user already exists
     * there cannot be two users with the same names. Updates the checkbox for inputing a bet to display a new user
     * @param add - Add User Button
     * @param user - Username
     * @param userBox - Combobox to be updated
     * @return Button with added Listener
     */
    public static Button generateListenerAddUser(Button add, TextField user, ComboBox<String> userBox) {
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if(!UserTable.checkUser(user.getText())){
                    boolean succ = UserTable.addUser(user.getText());
                    userBox.setItems(UserTable.getObservableUser());
                    if(succ) DrawScene.setStatus(StatusMessages.USERADDED);
                    else DrawScene.setStatus(StatusMessages.USERNOTADDED);
                    user.clear();

                }
                else{
                    DrawScene.setStatus(StatusMessages.USEREXISTS);
                }


            }
        });
        return add;
    }

    /**
     * Deletes a user with a certain name and also deletes the bets of the user as well as updates the ComboBox for Bets
     * @param del - Delete User Button
     * @param user - Username
     * @param tableBet - TableBet
     * @param userBox - Combobox with users to be updated
     * @return Button with added Listener
     */
    public static Button generateListenerDelUser(Button del, TextField user, TableView<Bet> tableBet, ComboBox<String> userBox) {
        del.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                boolean succUser = UserTable.delUser(user.getText());
                BetTable.deleteBetUser(user.getText());
                tableBet.setItems(BetTable.getObservableBets());
                userBox.setItems(UserTable.getObservableUser());
                user.clear();
                if(succUser) DrawScene.setStatus(StatusMessages.USERDELETED);
                else DrawScene.setStatus(StatusMessages.USERNOTDELETED);
            }
        });
        return del;
    }


}
