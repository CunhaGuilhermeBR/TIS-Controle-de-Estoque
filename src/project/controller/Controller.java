package project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.FileNotFoundException;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import project.Main;
import project.MainCadastro;
import project.MainMenu;
import project.MainRecuperarSenha;
import project.model.Secretaria;


import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller {
    @FXML
    public StackPane mainStackPane;
    @FXML
    public BorderPane mainBorderPane;
     @FXML
    private Label signUpComplete, bemVindo, loginFail, campoShow;

    @FXML
    private TextField campoLogin, loginSingUp, questionSignUp, answerSignUp, nomeSignUp;

    @FXML
    private TextField loginSignup;

    @FXML
    private Hyperlink lembrarSenha;

    @FXML
    AnchorPane cadastro, mainMenu, screenLogin;

    @FXML
    PasswordField passwordSignUp, campoSenha;

    @FXML
    private TextField questionRetrieve,answerRetrieve;

    private static MainMenu mm = new MainMenu();
    private static MainCadastro mc = new MainCadastro();
    private static MainRecuperarSenha mr = new MainRecuperarSenha();

    @FXML
    private void handleButtonAction(ActionEvent actionEvent) {
        loginIn();
        signUpComplete.setVisible(false);
    }

    @FXML private javafx.scene.control.Button butLogin;
private void loginIn(){
    boolean token = login();
    String user = campoLogin.getText();
    if (token == true) {
        Main.getStage().close();
        try {
            mm.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    } else {
        loginFail.setVisible(true);
        campoLogin.clear();
        campoSenha.clear();
    }
}

    public boolean login(){
        String nomeArquivo = "Usuários";
        String line,passwordLog;
        String  userLog = campoLogin.getText();
        passwordLog = campoSenha.getText();
        try {
            FileReader fi = new FileReader(nomeArquivo);
            BufferedReader reader = new BufferedReader(fi);
            do {
                line = reader.readLine();
                if (userLog.equals(line)){
                    line = reader.readLine();
                    if(passwordLog.equals(line))
                        return true;
                    else{
                        loginFail.setVisible(true);
                        return false;
                }}
            } while(line != null);
        } catch(Exception e) {
            e.printStackTrace();
        }
    return false;}

    @FXML
    public void handleButtonActionSignUp(ActionEvent actionEvent) throws Exception {
        mc.start(new Stage());
        loginFail.setVisible(false);
    }
    @FXML
    public void handleFinalizeCadastro(ActionEvent actionEvent)throws FileNotFoundException {
        loginFail.setVisible(false);
        String login=loginSignup.getText();
        String question= questionSignUp.getText();
        String answer= answerSignUp.getText();
        String password = new String(passwordSignUp.getText());
        String name = nomeSignUp.getText();
        Secretaria s = new Secretaria(login,password,question,answer, name);
        register(s);
        signUpComplete.setVisible(true);
        cadastro.setVisible(false);
        MainCadastro.getStage().close();
    }
     public void register(Secretaria s){
         try {
             String nomeArquivo = "Usuários";
             FileWriter fileWriter = new FileWriter(nomeArquivo, true);
             BufferedWriter writer = new BufferedWriter(fileWriter);

             String line=s.getPergunta();
             writer.write(line);
             writer.newLine();

             line=s.getResposta();
             writer.write(line);
             writer.newLine();

             line = s.getLogin();
             writer.write(line);
             writer.newLine();

             line=s.getSenha();
             writer.write(line);
             writer.newLine();

             line=s.getName();
             writer.write(line);
             writer.newLine();

             writer.close();
         }
         catch(Exception e) { //Tratamento genérico da excessão ocorrida
             System.out.println("O seguinte erro ocorreu: " + e.toString());
         }
     }

    public void handleButtonActionEnter(KeyEvent e) {
        if(e.getCode() == KeyCode.ENTER)
            loginIn();
    }

    public void handleRecuperarSenha(ActionEvent e) throws Exception {
    mr.start(new Stage());
    }

    public void retrieve(ActionEvent e){
        campoShow.setVisible(false);
        String nomeArquivo = "Usuários";
        String line,resp;
        String  perg = questionRetrieve.getText();
        resp = answerRetrieve.getText();
        try {
            FileReader fi = new FileReader(nomeArquivo);
            BufferedReader reader = new BufferedReader(fi);
            do {
                line = reader.readLine();
                if (perg.equals(line)){
                    line = reader.readLine();
                    if(resp.equals(line)){
                      line=reader.readLine();
                      String user = line;
                      line = reader.readLine();
                      String senha= line;
                        campoShow.setVisible(true);
                        campoShow.setText("Usuário:" + user + " Senha:" + senha);
                    }
                    else{
                        campoShow.setVisible(true);
                        campoShow.setText("Pergunta e/ou resposta incorreta");
                        return;
                    }}
            } while(line != null);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
