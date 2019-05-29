package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainCadastro extends Application {

    public static Stage getStage() {
        return stage;
    }
    public static void setStage(Stage loginStage) {
        MainCadastro.stage = loginStage;
    }

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception{
        Parent cadastroTela = FXMLLoader.load(getClass().getResource("view/TelaCadastroUsuario.fxml"));
        Scene scene = new Scene(cadastroTela,800,600);

        stage.getIcons().add(new Image("file:src/image/logo.png"));
        stage.setTitle("DHD Engenharia");
        stage.setMaxWidth(800);
        stage.setMaxHeight(600);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        setStage(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
