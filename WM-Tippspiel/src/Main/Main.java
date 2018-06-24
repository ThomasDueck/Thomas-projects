package Main;

import View.DrawScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

       DrawScene.createScene(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
