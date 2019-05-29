package project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import project.MainMenu;
import project.model.*;

import java.util.Optional;

public class NavBarController {
    public static boolean confirmar(Material obj) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText(obj.toString());
        alert.setContentText("Você confirma que quer excluir esse material?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean confirmar(Pessoa obj) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText(obj.toString());
        alert.setContentText("Você confirma que quer excluir esse trabalhador?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean confirmar(Ferramenta obj) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText(obj.toString());
        alert.setContentText("Você confirma que quer excluir essa ferramenta?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean confirmar(Obra obj) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText(obj.toString());
        alert.setContentText("Você confirma que quer excluir essa obra?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean confirmar(Emprestimo obj) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText(obj.toString());
        alert.setContentText("Você confirma que quer excluir esse Empréstimo?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    public static boolean retorno(String ver) {
        switch (ver){
            case "true":
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmação");

                alert.setContentText("Operação realizada com sucesso");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    return true;
                } else
                    return false;
            case "false":
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmação");

                alert.setContentText("Operação cancelada");

                result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    return true;
                } else {
                    return false;
                }
            case "nada":
                break;
        }

     return false;
    }

    @FXML
    public static boolean retornoMensagem(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Mensagem");
        alert.setContentText(mensagem);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    void goToCadMat(ActionEvent event) {
        MainMenu.changeScreen("cadMat");
    }

    @FXML
    void goToCadObra(ActionEvent event) {
        MainMenu.changeScreen("cadObra"); }

    @FXML
    void goToCadFer(ActionEvent event) throws Exception { MainMenu.changeScreen("cadFer");  }


    @FXML
    void goToCadTrab(ActionEvent event) {
        MainMenu.changeScreen("cadTrab");
    }

    @FXML
    void goToListFer(ActionEvent event) {
        MainMenu.changeScreen("listarFer");
    }

    @FXML
    public void goToGer(ActionEvent actionEvent) throws Exception { MainMenu.changeScreen("ger"); }

    @FXML
    void goToListMat(ActionEvent event) { MainMenu.changeScreen("listarMat"); }

    @FXML
    void goToListObra(ActionEvent event) {
        MainMenu.changeScreen("ListarObra");
    }

    @FXML
    void goToListTrab(ActionEvent event) { MainMenu.changeScreen("listarTrab"); }

    @FXML
    void goToUtili(ActionEvent e) {
        MainMenu.changeScreen("utili");
    }

    @FXML
    void goToEmprest(ActionEvent e) {
        MainMenu.changeScreen("emprestimo");
    }

    @FXML
    void fechar(ActionEvent e) {
        MainMenu.getStage().close();
    }
}
