package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainRecuperarSenha extends Application {

    public static Stage getStage() {
        return stage;
    }
    public static void setStage(Stage loginStage) {
        MainRecuperarSenha.stage = loginStage;
    }

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception{
        Parent cadastroTela = FXMLLoader.load(getClass().getResource("view/TelaRecuperarSenha.fxml"));
        Scene scene = new Scene(cadastroTela,800,600);

        stage.getIcons().add(new Image("file:src/image/logo.png"));
        stage.setTitle("DHD Engenharia");
        stage.setScene(scene);
        stage.show();
        setStage(stage);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
