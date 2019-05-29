package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage loginStage) {
        Main.stage = loginStage;
    }


    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception{
        Parent loginTela = FXMLLoader.load(getClass().getResource("view/Login.fxml"));
        Scene scene = new Scene(loginTela);

        stage.getIcons().add(new Image("file:src/image/logo.png"));
        stage.setTitle("DHD Engenharia");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
        setStage(stage);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
