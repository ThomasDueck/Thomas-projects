package view;

import model.Bet;
import model.Match;
import staticData.LayoutData;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DrawScene {


    private static TableView<Match> resultTable = new TableView<>();
    private static TableView<Bet> betTable = new TableView<>();
    private static Label status = new Label();


    /**
     * Creates the MainMenu of the Bet Game.
     * @param primaryStage
     */
    public static void createScene(Stage primaryStage){

        //Create the scene with Width, Height and Title
        Scene scene = new Scene(new Group());
        primaryStage.setWidth(LayoutData.WIDTH);
        primaryStage.setHeight(LayoutData.HEIGHT);
        primaryStage.setTitle(LayoutData.TITLE);
        primaryStage.setResizable(false);

        //Create the Tables beneath each other
        final HBox tablesHbox = new HBox(LayoutData.TABLESHBOXSPACING);
        tablesHbox.setSpacing(LayoutData.TABLESHBOXSPACING);
        tablesHbox.getChildren().addAll(view.DrawResults.showResults(resultTable, betTable), view.DrawBet.showBets(resultTable, betTable));

        //Creates an Anchorpane to insert the Boxes on to.
        AnchorPane pane = new AnchorPane();
        pane.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(LayoutData.IMAGEPATH)), CornerRadii.EMPTY, Insets.EMPTY)));

        //Get the Box designed for containing all users so it can be updated later on.
        VBox table = (VBox) tablesHbox.getChildren().get(1);
        HBox userInterface = (HBox) table.getChildren().get(2);
        ComboBox<String> userCombobox = (ComboBox<String>) userInterface.getChildren().get(5);

        //Create for layoutpurpose label and separator
        Label lblUser = new Label(LayoutData.EDITUSERTITLE);
        lblUser.setFont(new Font(LayoutData.FONT, LayoutData.FONTSIZE));
        lblUser.setTextFill(Color.web(LayoutData.COLOR));

        status.setFont(new Font(LayoutData.FONT, LayoutData.FONTSIZE));
        status.setTextFill(Color.web(LayoutData.COLOR));
        Separator separator = new Separator();
        separator.setOrientation(Orientation.HORIZONTAL);


        //Create the mainVbox, with the order Tables and Usermenu. Set Margin to WIDTH/3 so it is roughly in the center
        final VBox mainVbox = new VBox(LayoutData.MAINVBOXSPACING);
        HBox userMenu = view.AddUserGUI.editUserInterface(betTable, userCombobox);
        mainVbox.getChildren().addAll(tablesHbox, status, separator,  lblUser, userMenu);
        mainVbox.setMargin(userMenu, new Insets(0, 0, LayoutData.USERMENUPADDINGBOTTOM, LayoutData.CENTERUSERPANEL));
        mainVbox.setMargin(lblUser, new Insets(0, 0, 0, LayoutData.CENTERUSERPANEL));
        mainVbox.setMargin(status, new Insets(0,0,0,LayoutData.CENTERUSERPANEL));

        //Add mainVbox to the AnchorPane to the scene and finally show.
        pane.getChildren().addAll(mainVbox);
        ((Group) scene.getRoot()).getChildren().addAll(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void setStatus(String message){
        status.setText(message);
    }
}
