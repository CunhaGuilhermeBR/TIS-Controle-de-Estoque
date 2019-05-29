package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainMenu extends Application {
    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        MainMenu.stage = stage;
    }



    private static Stage stage;

    private static Scene cadMat;
    private static Scene menuScene;
    private static Scene cadObra;
    private static Scene cadFerramenta;
    private static Scene cadTrabalhador;
    private static Scene listarMat;
    private static Scene listarObra;
    private static Scene listarFerramenta;
    private static Scene listarTrabalhador;
    private static Scene utilizacao;
    private static Scene emprestimo;
    private static Scene ger;

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("DHD Engenharia");
        Parent fxmlMenu = FXMLLoader.load(getClass().getResource("view/Menu.fxml"));
        menuScene = new Scene(fxmlMenu);

        Parent fxmlCadMat = FXMLLoader.load(getClass().getResource("view/CadastrarMaterial.fxml"));
        cadMat = new Scene(fxmlCadMat);

        Parent fxmlCadObra = FXMLLoader.load(getClass().getResource("view/CadastrarObra.fxml"));
        cadObra = new Scene(fxmlCadObra);

        Parent fxmlCadTrab = FXMLLoader.load(getClass().getResource("view/CadastrarTrabalhador.fxml"));
        cadTrabalhador = new Scene(fxmlCadTrab);

        Parent fxmlCadFer = FXMLLoader.load(getClass().getResource("view/CadastrarFerramenta.fxml"));
        cadFerramenta = new Scene(fxmlCadFer);

        Parent fxmlListMat = FXMLLoader.load(getClass().getResource("view/ListarMateriais.fxml"));
        listarMat = new Scene(fxmlListMat);

        Parent fxmlListFer = FXMLLoader.load(getClass().getResource("view/ListarFerramenta.fxml"));
        listarFerramenta = new Scene(fxmlListFer);

        Parent fxmlListObra = FXMLLoader.load(getClass().getResource("view/ListarObra.fxml"));
        listarObra = new Scene(fxmlListObra);

        Parent fxmlListTrab = FXMLLoader.load(getClass().getResource("view/ListarTrabalhador.fxml"));
        listarTrabalhador = new Scene(fxmlListTrab);

        Parent fxmlUtili = FXMLLoader.load(getClass().getResource("view/Utilizacao.fxml"));
        utilizacao = new Scene(fxmlUtili);

        Parent fxmlEmpres  = FXMLLoader.load(getClass().getResource("view/Emprestimo.fxml"));
        emprestimo = new Scene(fxmlEmpres);

        Parent fxmlGer  = FXMLLoader.load(getClass().getResource("view/gerenciamento.fxml"));
        ger = new Scene(fxmlGer);

        stage.setScene(menuScene);
        stage.getIcons().add(new Image("file:src/image/logo.png"));
        stage.setMaximized(true);
        stage.show();
        stage.setMinWidth(1350);
        stage.setMinHeight(1200);
        setStage(stage);

    }

   public static void changeScreen(String cena){
        switch (cena){
            case "cadMat" :
            stage.setScene(cadMat);
                stage.setMaximized(false);
                stage.setMaximized(true);
            break;

            case "cadObra" :
                stage.setScene(cadObra);
                stage.setMaximized(false);
                stage.setMaximized(true);
                break;

            case "cadTrab" :
                stage.setScene(cadTrabalhador);
                stage.setMaximized(false);
                stage.setMaximized(true);
                break;

            case "cadFer" :
                stage.setScene(cadFerramenta);
                stage.setMaximized(false);
                stage.setMaximized(true);
                break;
            case "listarMat":
                stage.setScene(listarMat);
                stage.setMaximized(false);
                stage.setMaximized(true);
               break;
            case "listarFer":
                stage.setScene(listarFerramenta);
                stage.setMaximized(false);
                stage.setMaximized(true);
                break;
            case "ListarObra":
                stage.setScene(listarObra);
                stage.setMaximized(false);
                stage.setMaximized(true);
                break;
            case "listarTrab":
                stage.setScene(listarTrabalhador);
                stage.setMaximized(false);
                stage.setMaximized(true);
                break;
            case "utili":
                stage.setScene(utilizacao);
                stage.setMaximized(false);
                stage.setMaximized(true);
                break;
            case "emprestimo":
                stage.setScene(emprestimo);
                stage.setMaximized(false);
                stage.setMaximized(true);
                break;
            case "ger":
                stage.setScene(ger);
                stage.setMaximized(false);
                stage.setMaximized(true);
                break;
        }
   }


    public static void main(String[] args) {
        launch(args);
    }
}
