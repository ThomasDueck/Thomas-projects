package view;

import controller.listener.ButtonListener;
import model.Bet;
import staticData.LayoutData;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AddUserGUI {


    /**
     * Create the Interface for Editing User with Adding and Deleting.
     * @param tableBet - The Table for Bets, needed so the delete Button deletes all Bets from a deleted user
     * @param userBox - A added user has to be included in the combobox that is part of making a Bet.
     * @return HBox - Containing the Controls.
     */
    public static HBox editUserInterface(TableView<Bet> tableBet, ComboBox<String> userBox){

        //Adding the components editUserInterface, delUser and Textfield for entering a username to add or delete.
        TextField user = new TextField();
        Button addBtn = new Button(LayoutData.ADDUSER);
        Button delBtn = new Button(LayoutData.DELETEUSER);
        addBtn = ButtonListener.generateListenerAddUser(addBtn ,user, userBox);
        delBtn = ButtonListener.generateListenerDelUser(delBtn, user, tableBet, userBox);
        addBtn.setStyle(LayoutData.OK);
        delBtn.setStyle(LayoutData.CANCEL);
        //Eventually add it to a Box and return said Box.
        HBox userInterface = new HBox(LayoutData.USERINTERFACESPACING);
        userInterface.getChildren().addAll(user, addBtn, delBtn);
        return userInterface;
    }
}
