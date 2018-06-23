package View;

import Controller.Listener.ButtonListener;
import Model.Bet;
import Model.Match;
import Model.User;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AddUserGUI {

    public static HBox addUser(TableView<Match> tableMatch, TableView<Bet> tableBet, ComboBox<User> userBox){





        TextField user = new TextField();
        Button addBtn = new Button("Add User");
        addBtn = ButtonListener.generateListenerAddUser(addBtn ,user);
        Button delBtn = new Button("Delete User");
        delBtn = ButtonListener.generateListenerDelUser(delBtn, user, tableBet);
        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(user, addBtn, delBtn);
        return hbox;
    }
}
