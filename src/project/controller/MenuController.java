package project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import project.FileSystem;
import project.database.*;
import project.model.*;

import java.util.ArrayList;
import java.util.Optional;

public class MenuController extends NavBarController {
    FileSystem fs = new FileSystem();
    private static ObservableList<Material> obsMaterials;
    private static ObservableList<Emprestimo> obsEmprestimos;
    private static ObservableList<Utilização> obsUtilizacoes;
    private static ObservableList<Ferramenta> obsFerramentas;
    private static ObservableList<Obra> obsObras, comboBoxObras;
    private static ObservableList<Pessoa> obsTrabs,comboBoxTrabs;
    private static ObservableList<Integer> diasUti,mesesUti,anosUti, diasEmp, mesesEmp, anosEmp;
    @FXML private TextField idObra;

    @FXML private Label labelDataUti;
    @FXML private Button butAlterarTrab,butBuscarTrab,butFinalizarUti2,butExcluirTrab,butVoltarNovaUti,butFinalizarEmp,butListarEmp,butBuscarEmp,butNovoEmp,butAlterarEmp,butFinalizarNovaUti;
    @FXML private ComboBox<Integer> AnoUti,DiaUti,MêsUti;
    @FXML private TextField textFieldQuantMat;


    @FXML private Label LabelMaterial;

    @FXML private ComboBox<Material> ComboBoxMat;

    @FXML private Label LabelQuantMat;

    @FXML private TextField idTrab,alterarNomeTrab,idFerramenta,alterarObraAtual;

    @FXML private Button butExcluirFerramenta,butAddTrabMesmo;

    @FXML private TextField idUti;

    @FXML private Button butVoltarFinalizarUti,butVoltarBuscaUti, butBuscarUti2, butVoltarFinalizarEmp;
    @FXML private TextField alterarNomeFerramenta;
    @FXML private TextField alterarQuantFerramenta;
    @FXML private TextField alterarMarca;
    @FXML private Button butFinalizarEmp2,butBuscarFerramenta,butExcluirObra, butAlterarFerramenta, butNovaUti, butAlterarUti, butBuscarUti, butListarUti, butFinalizarUti;
    @FXML private TextField alterarNomeObra;
    @FXML private TextField alterarTrabs,textFieldQuantMatUti;
    @FXML private Button butAlterarObra;
    @FXML private Button butBuscarObra;
    @FXML private TableView<Utilização> tabelaUti;
    @FXML private TableColumn<Utilização, String> campoDataInicioUti, campoTrabUti,campoMatUti,campoDataRetornoUti;
    @FXML private TableColumn<Utilização, Integer> campoQuantUti, campoIdUti;
    @FXML private TableColumn<Utilização, Boolean> campoEmAndamentoUti;
    @FXML private TableView<Obra> tabelaObra, tabelaObraEmAndamento;
    @FXML private TableColumn<Obra, String> campoNomeObra;
    @FXML private  TableColumn<Obra, String> campoTrabs, campoNomeObra2, campoTrabs2;
    @FXML private  TableColumn<Obra, Boolean> campoEmAndamento;
    @FXML private TableColumn<Obra, Integer> campoIDObra,campoIDObra2;
    @FXML private TableView<Emprestimo> tabelaEmp;
    @FXML private TableColumn<Emprestimo, String> campoDataInicio, campoTrabEmp,campoFerEmp,campoDataRetorno;
    @FXML private TableColumn<Emprestimo, Integer> campoQuantEmp, campoIdEmp;
    @FXML private TableColumn<Emprestimo, Boolean> campoEmAndamentoEmp;

    @FXML private Label labelDataEmp;
    @FXML private ComboBox<Integer> DiaEmp;
    @FXML private ComboBox<Integer> MêsEmp;
    @FXML private ComboBox<Integer> AnoEmp;
    @FXML private Label LabelEmpréstimo;
    @FXML private ComboBox<Ferramenta> ComboBoxFerEmp;
    @FXML private Button butAddTrab,butFinalizarNovoEmp, butVoltarAlterarEmp, butFinalizarAlterarEmp;
    @FXML private Button butVoltarNovoEmp;
    @FXML private Label LabelQuantFer;

    @FXML private TextField idFer, TextFielQuantFer;

    @FXML private Button butBuscarEmp2,butVoltarBuscaEmp, butFinalizarAlterarUti, butVoltarAlterarUti;

    @FXML private TableView<Pessoa> tabelaTrab, tabelaTrabComplete;

    @FXML private TableColumn<Pessoa, String> campoObraAtual;

    @FXML private TableColumn<Pessoa, String> campoFers;

    @FXML private TableColumn<Pessoa, String> campoNomeTrab,campoNomeTrab2;

    @FXML TableColumn<Pessoa, Integer> campoIDTrab, campoIDTrab2;

    @FXML private TableView<Material> tabelaMat, tabelaMatCompleta;

    @FXML private TableView<Ferramenta> tabelaFer, tabelaFerComplete;

    @FXML private TableColumn<Ferramenta, String> campoNomeFer, campoNomeFer2;

    @FXML private TableColumn<Ferramenta, Integer> campoQuantFer,campoQuantFer2,campoQuantTotalFer2;
    @FXML private TableColumn<Ferramenta, String> campoMarca, campoMarca2;

    @FXML private TableColumn<Ferramenta, Integer> campoIDFer, campoIDFer2;

    @FXML private TableColumn<Material, String> campoNomeMat;
    @FXML private TableColumn<Material, String> campoNomeMat2;

    @FXML private TableColumn<Material, Integer> campoQuantMat, campoQuantTotalMat;
    @FXML private TableColumn<Material, Integer>  campoQuantMat2;
    @FXML private Pane paneMaterial, paneObra, paneFerramenta, paneTrab;
    @FXML private TableColumn<Material, Integer> campoIDMat;
    @FXML private TableColumn<Material, Integer> campoIDMat2;
    @FXML private TableColumn<Material, String>  campoUnMed2;
    @FXML private TableColumn<Material, String> campoUnMed;

    @FXML private AnchorPane sideMenu;

    @FXML private Button butVoltarListarUti,butCadObra, butCadMat, butCadFer, butCadTrab, butExcluirMat, butAlterarMat, butBuscarMat;

    @FXML private ComboBox<Pessoa>  ComboBoxTrabUti,ComboBoxTrabEmp, trabsObra;
    @FXML private ComboBox<Obra> obraAtual;

    @FXML private TextField fers,  nomeMat, quantMat,unMedida, nomeFer, quantFer, marcaFer, nomeObra, nomeTrab, idMaterial, alterarNomeMat, alterarQuantMat, alterarUnMed;

    @FXML private Label LabelTrabFerEmp,labelTrabGer,labelFerGer,labelMatGer, labelgerObra;
    @FXML private Button butExcluirFerGer,butBuscarTrabGer, butExcluirTrabGer, butAlterarTrabGer, butBuscarFerGer, butAlterarFerGer;;

    @FXML private Button butExcluirObraGer,butAlterarObraGer,butBuscarObraGer,butVoltarListarEmp;

    @FXML private Button butAlterarMatGer,butBuscarMatGer,butExcluirMatGer;

    @FXML
    private void carregaComboBox() throws Exception {
        ArrayList listTrabs = Pessoa_CRUD.listarPessoasAtoas();
        comboBoxTrabs = FXCollections.observableArrayList(listTrabs);
        trabsObra.setItems(comboBoxTrabs);
    }

    private void carregaComboBoxUti() throws Exception{
        ArrayList dias = new ArrayList();
        for (int i = 1; i <32; i++){
            dias.add(i);
        }
        ArrayList meses = new ArrayList();
        meses.add("Janeiro"); meses.add("Fevereiro"); meses.add("Março"); meses.add("Abril"); meses.add("Maio");meses.add("Junho"); meses.add("Julho"); meses.add("Agosto"); meses.add("Setembro"); meses.add("Outubro"); meses.add("Novembro"); meses.add("Dezembro");
        ArrayList anos = new ArrayList();
        for (int i = 2019; i < 2030; i ++){
            anos.add(i);
        }
        diasUti = FXCollections.observableArrayList(dias);mesesUti= FXCollections.observableArrayList(meses);anosUti=FXCollections.observableArrayList(anos);
        DiaUti.setItems(diasUti); MêsUti.setItems(mesesUti); AnoUti.setItems(anosUti);
        ArrayList mats = Material_CRUD.listarMaterias();
        obsMaterials = FXCollections.observableArrayList(mats);
        ComboBoxMat.setItems(obsMaterials);
        ArrayList listTrabs = Pessoa_CRUD.listarPessoas();
        comboBoxTrabs = FXCollections.observableArrayList(listTrabs);
        ComboBoxTrabUti.setItems(comboBoxTrabs);
    }

    private void carregaComboBoxEmp() throws Exception{
        ArrayList listTrabs = Pessoa_CRUD.listarPessoas();
        comboBoxTrabs = FXCollections.observableArrayList(listTrabs);
        ComboBoxTrabEmp.setItems(comboBoxTrabs);
        ArrayList fers = Ferramenta_CRUD.listarFerramentasDisp();
        obsFerramentas = FXCollections.observableArrayList(fers);
        ComboBoxFerEmp.setItems(obsFerramentas);
        ArrayList dias = new ArrayList();
        for (int i = 1; i <32; i++){
            dias.add(i);
        }
        ArrayList meses = new ArrayList();
        meses.add("Janeiro"); meses.add("Fevereiro"); meses.add("Março"); meses.add("Abril"); meses.add("Maio");meses.add("Junho"); meses.add("Julho"); meses.add("Agosto"); meses.add("Setembro"); meses.add("Outubro"); meses.add("Novembro"); meses.add("Dezembro");
        ArrayList anos = new ArrayList();
        for (int i = 2019; i < 2030; i ++){
            anos.add(i);
        }
        diasEmp = FXCollections.observableArrayList(dias);mesesEmp= FXCollections.observableArrayList(meses);anosEmp=FXCollections.observableArrayList(anos);
        DiaEmp.setItems(diasEmp); MêsEmp.setItems(mesesEmp); AnoEmp.setItems(anosEmp);
    }

    private void esconderButUti(){
        butNovaUti.setVisible(false);  butBuscarUti.setVisible(false); butListarUti.setVisible(false); butFinalizarUti.setVisible(false);
    }

    private void esconderButEmp(){
        butNovoEmp.setVisible(false); butBuscarEmp.setVisible(false); butListarEmp.setVisible(false); butFinalizarEmp.setVisible(false);
    }

    @FXML
    public void handleNovoEmp(ActionEvent e) throws Exception {
        esconderButEmp();
        carregaComboBoxEmp();
        labelDataEmp.setVisible(true);
        ComboBoxTrabEmp.setVisible(true);
        DiaEmp.setVisible(true);
        LabelTrabFerEmp.setVisible(true);
        MêsEmp.setVisible(true);
        AnoEmp.setVisible(true);
        LabelEmpréstimo.setVisible(true);
        ComboBoxFerEmp.setVisible(true);
        butFinalizarNovoEmp.setVisible(true);butVoltarNovoEmp.setVisible(true);LabelQuantFer.setVisible(true);
        TextFielQuantFer.setVisible(true);
    }

    @FXML
    public void handleNovaUti(ActionEvent e) throws Exception {
        esconderButUti();
        carregaComboBoxUti();
        labelDataUti.setVisible(true);
        DiaUti.setVisible(true);
        MêsUti.setVisible(true);
        textFieldQuantMatUti.setVisible(true);
        AnoUti.setVisible(true);
        LabelMaterial.setVisible(true);
        ComboBoxMat.setVisible(true);
        ComboBoxTrabUti.setVisible(true);
        butFinalizarNovaUti.setVisible(true);butVoltarNovaUti.setVisible(true);LabelQuantMat.setVisible(true);
        textFieldQuantMatUti.setVisible(true);
    }

    @FXML
    public void cadastrarUti(ActionEvent e) throws Exception {
        int d = DiaUti.getSelectionModel().getSelectedItem();
        String m = String.valueOf(MêsUti.getSelectionModel().getSelectedItem());
        int a = AnoUti.getSelectionModel().getSelectedItem();
        String data = d +"/" +m +"/" + a;
        Material ma = ComboBoxMat.getSelectionModel().getSelectedItem();
        int q = Integer.parseInt(textFieldQuantMatUti.getText());
        Pessoa p = ComboBoxTrabUti.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar cadastro");
        alert.setHeaderText("Data: " + d +"/" +m +"/" + a + "\n"+   " Material; "+ q + ma.getNome());
        alert.setContentText("Você confirma o cadastro?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            int id= Uti_CRUD.incluirUtilizacao(data, ma.getNome(), q, p.getNome());
            Alert idShow = new Alert(Alert.AlertType.INFORMATION);
            idShow.setTitle("Cadastro confirmado");
            idShow.setHeaderText("A utilização foi cadastrado com sucesso");
            idShow.setContentText("A utilização foi cadastrado com id:" + id);
            idShow.showAndWait();
            textFieldQuantMatUti.setText("");

        } else {
            textFieldQuantMatUti.setText("");
        }
    }

    @FXML
    public void cadastrarEmp(ActionEvent e) throws Exception {
        int d = DiaEmp.getSelectionModel().getSelectedItem();
        String m = String.valueOf(MêsEmp.getSelectionModel().getSelectedItem());
        int a = AnoEmp.getSelectionModel().getSelectedItem();
        String data = d +"/" +m +"/" + a;
        Ferramenta fe = ComboBoxFerEmp.getSelectionModel().getSelectedItem();
        int q = Integer.parseInt(TextFielQuantFer.getText());
        Pessoa p = ComboBoxTrabEmp.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar cadastro");
        alert.setHeaderText("Data: " + d +"/" +m +"/" + a + "\n"+   " Ferramenta; "+ q + fe.getNome());
        alert.setContentText("Você confirma o cadastro?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            int id= Emprestimo_CRUD.incluirEmprestimo(data, fe.getNome(), q, p.getNome());
            Alert idShow = new Alert(Alert.AlertType.INFORMATION);
            idShow.setTitle("Cadastro confirmado");
            idShow.setHeaderText("O empréstimo foi cadastrado com sucesso");
            idShow.setContentText("O empréstimo foi cadastrado com id:" + id);
            idShow.showAndWait();
            TextFielQuantFer.setText("");

        } else {
            TextFielQuantFer.setText("");
        }

    }

    @FXML public void voltarNovaUti(ActionEvent e){
        butNovaUti.setVisible(true); butBuscarUti.setVisible(true); butListarUti.setVisible(true); butFinalizarUti.setVisible(true);
        labelDataUti.setVisible(false);
        DiaUti.setVisible(false);
        MêsUti.setVisible(false);
        AnoUti.setVisible(false);
        ComboBoxTrabUti.setVisible(false);
        LabelMaterial.setVisible(false);
        ComboBoxMat.setVisible(false);
        butFinalizarNovaUti.setVisible(false);butVoltarNovaUti.setVisible(false);LabelQuantMat.setVisible(false);
        textFieldQuantMatUti.setVisible(false);
    }

    @FXML public void voltarNovoEmp(ActionEvent e ){
        butNovoEmp.setVisible(true);  butBuscarEmp.setVisible(true); butListarEmp.setVisible(true); butFinalizarEmp.setVisible(true);
        labelDataEmp.setVisible(false);
        DiaEmp.setVisible(false);
        MêsEmp.setVisible(false);
        LabelTrabFerEmp.setVisible(false);
        ComboBoxTrabEmp.setVisible(false);
        AnoEmp.setVisible(false);
        LabelEmpréstimo.setVisible(false);
        ComboBoxFerEmp.setVisible(false);
        butFinalizarNovoEmp.setVisible(false);butVoltarNovoEmp.setVisible(false);LabelQuantFer.setVisible(false);
        TextFielQuantFer.setVisible(false);
    }

    @FXML public void buscarUti(ActionEvent e){
        esconderButUti();
        idUti.setVisible(true);butVoltarBuscaUti.setVisible(true); butBuscarUti2.setVisible(true);
    }

    @FXML public void buscarEmp (ActionEvent e){
        esconderButEmp();
        idFer.setVisible(true);butVoltarBuscaEmp.setVisible(true); butBuscarEmp2.setVisible(true);
    }

    @FXML public void voltarBuscarUti(ActionEvent e){
        butNovaUti.setVisible(true); butBuscarUti.setVisible(true); butListarUti.setVisible(true); butFinalizarUti.setVisible(true);
        idUti.setVisible(false);butVoltarBuscaUti.setVisible(false); butBuscarUti2.setVisible(false);
    }

    @FXML public void voltarBuscarEmp(ActionEvent e){
        butNovoEmp.setVisible(true); butBuscarEmp.setVisible(true); butListarEmp.setVisible(true); butFinalizarEmp.setVisible(true);
        idFer.setVisible(false);butVoltarBuscaEmp.setVisible(false); butBuscarEmp2.setVisible(false);
    }

    @FXML public void finalizarUtiMesmo(ActionEvent e) throws Exception {
        int d = DiaUti.getSelectionModel().getSelectedItem();
        String m = String.valueOf(MêsUti.getSelectionModel().getSelectedItem());
        int a = AnoUti.getSelectionModel().getSelectedItem();
        String data = d +"/" +m +"/" + a;
        int id = Integer.parseInt(idUti.getText());
        Uti_CRUD.finalizarUti(id, data);
    }


    @FXML public void finalizarEmp(ActionEvent e){
        esconderButEmp();
        DiaEmp.setVisible(true);
        MêsEmp.setVisible(true);
        AnoEmp.setVisible(true);
        idFer.setVisible(true);butVoltarFinalizarEmp.setVisible(true); butFinalizarEmp2.setVisible(true);butFinalizarNovoEmp.setVisible(false);

    }
    @FXML public void finalizeEmpMesmo(ActionEvent e) throws Exception {
        int d = DiaEmp.getSelectionModel().getSelectedItem();
        String m = String.valueOf(MêsEmp.getSelectionModel().getSelectedItem());
        int a = AnoEmp.getSelectionModel().getSelectedItem();
        String data = d +"/" +m +"/" + a;
        int id = Integer.parseInt(idFer.getText());
        Emprestimo_CRUD.finalizarEmp(id, data);
    }

    @FXML public void voltarFinalizarEmp(ActionEvent e){
        butNovoEmp.setVisible(true);  butBuscarEmp.setVisible(true); butListarEmp.setVisible(true); butFinalizarEmp.setVisible(true);
        idFer.setVisible(false);butVoltarFinalizarEmp.setVisible(false); butFinalizarEmp2.setVisible(false); DiaEmp.setVisible(false);
        MêsEmp.setVisible(false);
        AnoEmp.setVisible(false);
    }

    @FXML public void finalizarUti(ActionEvent e){
        esconderButUti();
        DiaUti.setVisible(true);
        MêsUti.setVisible(true);
        AnoUti.setVisible(true);
        butFinalizarUti2.setVisible(true);
        butVoltarFinalizarUti.setVisible(true);
        idUti.setVisible(true);
        butFinalizarUti2.setVisible(true);
    }

    @FXML public void voltarFinalizarUti(ActionEvent e){
        butNovaUti.setVisible(true); butBuscarUti.setVisible(true); butListarUti.setVisible(true); butFinalizarUti.setVisible(true);
        DiaUti.setVisible(false);
        MêsUti.setVisible(false);
        AnoUti.setVisible(false);
        butVoltarFinalizarUti.setVisible(false);
        idUti.setVisible(false);
        butFinalizarUti2.setVisible(false);

    }

    @FXML
    private void carregaComboBoxObras() throws Exception{
        ArrayList listObras = Obra_CRUD.listarObrasEmAndamento();
        comboBoxObras = FXCollections.observableArrayList(listObras);
        obraAtual.setItems(comboBoxObras);
    }

    public void handleFinalizeCadMat(ActionEvent actionEvent) throws Exception {
        String nome = nomeMat.getText();
        int quant = Integer.parseInt(quantMat.getText());
        String unM = unMedida.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar cadastro");
        alert.setHeaderText("Nome: " + nome + "\nQuantidade; " + quant +" " + unM);
        alert.setContentText("Você confirma o cadastro?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
           int id= Material_CRUD.incluirMaterial(nome, quant, unM);
            Alert idShow = new Alert(Alert.AlertType.INFORMATION);
            idShow.setTitle("Cadastro confirmado");
            idShow.setHeaderText("O material " + nome + " foi cadastrado com sucesso");
            idShow.setContentText("O material foi cadastrado com id:" + id);
            idShow.showAndWait();
            nomeMat.setText("");
            quantMat.setText("");
            unMedida.setText("");
        } else {
            unMedida.setText(""); nomeMat.setText(""); quantMat.setText("");
        }
    }
         public void handleListarMat(ActionEvent e) throws Exception {
        campoNomeMat.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
        campoQuantMat.setCellValueFactory(
                new PropertyValueFactory<>("quantDisp"));
        campoUnMed.setCellValueFactory(
                new PropertyValueFactory<>("unMedida"));
        campoIDMat.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        ArrayList lista = new ArrayList();
        lista = Material_CRUD.listarMateriasDisp();
        obsMaterials = FXCollections.observableArrayList(lista);
        tabelaMat.setItems(obsMaterials);
        tabelaMat.setVisible(true);
        tabelaMatCompleta.setVisible(false);
    }
    public void handleListarMatComplete(ActionEvent e) throws Exception {
        campoNomeMat2.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
        campoQuantTotalMat.setCellValueFactory(
                new PropertyValueFactory<>("quantTotal"));
        campoQuantMat2.setCellValueFactory(
                new PropertyValueFactory<>("quantDisp"));
        campoUnMed2.setCellValueFactory(
                new PropertyValueFactory<>("unMedida"));
        campoIDMat2.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        ArrayList lista = new ArrayList();
        lista = Material_CRUD.listarMaterias();
        obsMaterials = FXCollections.observableArrayList(lista);
        tabelaMatCompleta.setItems(obsMaterials);
        tabelaMat.setVisible(false);
        tabelaMatCompleta.setVisible(true);
    }

    public void handleListarEmprestimos(ActionEvent e) throws Exception {
        campoDataInicio.setCellValueFactory(
                new PropertyValueFactory<>("dataEmprestimo"));
        campoDataRetorno.setCellValueFactory(
                new PropertyValueFactory<>("dataRetorno"));
        campoQuantEmp.setCellValueFactory(
                new PropertyValueFactory<>("quantFer"));
        campoFerEmp.setCellValueFactory(
                new PropertyValueFactory<>("ferramenta"));
        campoTrabEmp.setCellValueFactory(
                new PropertyValueFactory<>("trab"));
        campoIdEmp.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        campoEmAndamentoEmp.setCellValueFactory(
                new PropertyValueFactory<>("emAndamento"));

        ArrayList lista = new ArrayList();
        lista = Emprestimo_CRUD.listarEmprestimos();
        obsEmprestimos = FXCollections.observableArrayList(lista);
        tabelaEmp.setItems(obsEmprestimos);
        tabelaEmp.setVisible(true);
        butVoltarListarEmp.setVisible(true);
    }
    public void handleListarUtilizacoes(ActionEvent e) throws Exception {
        campoDataInicioUti.setCellValueFactory(
                new PropertyValueFactory<>("dataEmprestimo"));
        campoDataRetornoUti.setCellValueFactory(
                new PropertyValueFactory<>("dataRetorno"));
        campoQuantUti.setCellValueFactory(
                new PropertyValueFactory<>("quantFer"));
        campoMatUti.setCellValueFactory(
                new PropertyValueFactory<>("material"));
        campoTrabUti.setCellValueFactory(
                new PropertyValueFactory<>("trab"));
        campoIdUti.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        campoEmAndamentoUti.setCellValueFactory(
                new PropertyValueFactory<>("emAndamento"));

        ArrayList lista = new ArrayList();
        lista = Uti_CRUD.listarUtilizacoes();
        obsUtilizacoes = FXCollections.observableArrayList(lista);
        tabelaUti.setItems(obsUtilizacoes);
        tabelaUti.setVisible(true);
        butVoltarListarUti.setVisible(true);
    }

public void setButVoltarListarUti(ActionEvent e){
        tabelaUti.setVisible(false);
        butVoltarListarUti.setVisible(false);
}

    public void voltarListarEmp(ActionEvent e){
        tabelaEmp.setVisible(false);
        butVoltarListarEmp.setVisible(false);
    }

    public void handleListarFer(ActionEvent e) throws Exception {
        campoNomeFer.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
        campoQuantFer.setCellValueFactory(
                new PropertyValueFactory<>("quantTotal"));
        campoMarca.setCellValueFactory(
                new PropertyValueFactory<>("marca"));
        campoIDFer.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        ArrayList lista = new ArrayList();
        lista = Ferramenta_CRUD.listarFerramentasDisp();
        obsFerramentas = FXCollections.observableArrayList(lista);
        tabelaFer.setItems(obsFerramentas);
        tabelaFerComplete.setVisible(false);
        tabelaFer.setVisible(true);
    }

    public void handleListarFerComplete(ActionEvent e) throws Exception {
        campoNomeFer2.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
        campoQuantTotalFer2.setCellValueFactory(
                new PropertyValueFactory<>("quantTotal"));
        campoQuantFer2.setCellValueFactory(
                new PropertyValueFactory<>("quantDisp"));
        campoMarca2.setCellValueFactory(
                new PropertyValueFactory<>("marca"));
        campoIDFer2.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        ArrayList lista = new ArrayList();
        lista = Ferramenta_CRUD.listarFerramentas();
        obsFerramentas = FXCollections.observableArrayList(lista);
        tabelaFerComplete.setItems(obsFerramentas);
        tabelaFerComplete.setVisible(true);
        tabelaFer.setVisible(false);
    }

    public void handleListarTrab(ActionEvent e) throws Exception {
        campoNomeTrab.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
        campoFers.setCellValueFactory(
                new PropertyValueFactory<>("ferAtuais"));
        campoObraAtual.setCellValueFactory(
                new PropertyValueFactory<>("obraAtual"));
        campoIDTrab.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        ArrayList lista = new ArrayList();
        lista = Pessoa_CRUD.listarPessoas();
        obsTrabs = FXCollections.observableArrayList(lista);
        tabelaTrab.setItems(obsTrabs);
        tabelaTrab.setVisible(true);
        tabelaTrabComplete.setVisible(false);
    }

    public void handleListarTrabDisp(ActionEvent e) throws Exception {
        campoNomeTrab2.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
        campoIDTrab2.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        ArrayList lista = new ArrayList();
        lista = Pessoa_CRUD.listarPessoasAtoas();
        obsTrabs = FXCollections.observableArrayList(lista);
        tabelaTrabComplete.setItems(obsTrabs);
        tabelaTrab.setVisible(false);
        tabelaTrabComplete.setVisible(true);
    }

    public void handleFinalizeCadFer(ActionEvent e) throws Exception {
        String nome = nomeFer.getText();
        int quant = Integer.parseInt(quantFer.getText());
        String marca = marcaFer.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar cadastro");
        alert.setHeaderText("Nome: " + nome + "\nQuantidade; " + quant + "\nMarca: " + marca);
        alert.setContentText("Você confirma o cadastro?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            int id = Ferramenta_CRUD.incluirFerramenta(nome, quant, marca);
            Alert idShow = new Alert(Alert.AlertType.INFORMATION);
            idShow.setTitle("Cadastro confirmado");
            idShow.setHeaderText("A obra " + nome + " foi cadastrada com sucesso");
            idShow.setContentText("A Obra foi cadastrada com id:" + id);
            idShow.showAndWait();
            nomeFer.setText("");
            quantFer.setText("");
            marcaFer.setText("");
        } else {
            nomeFer.setText("");
            quantFer.setText("");
            marcaFer.setText("");
        }
    }

    public void handleFinalizeCadObra(ActionEvent e) throws Exception {
        String nome = nomeObra.getText();
        Pessoa trab = trabsObra.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar cadastro");
        alert.setHeaderText("Nome obra: " + nome + " Trabalhador:" + trab);
        alert.setContentText("Você confirma o cadastro?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            int id = Obra_CRUD.incluirObra(nome, trab.getNome());
            Alert idShow = new Alert(Alert.AlertType.INFORMATION);
            idShow.setTitle("Cadastro confirmado");
            idShow.setHeaderText("A obra " + nome + " foi cadastrada com sucesso");
            idShow.setContentText("A Obra foi cadastrada com id:" + id);
            idShow.showAndWait();
            nomeObra.setText("");
        } else {
            nomeObra.setText("");
        }
    }

    public void handleFinalizeCadTrab(ActionEvent e) {
        String nome = nomeTrab.getText();
        Obra obraA = obraAtual.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar cadastro");
        alert.setHeaderText("Nome trabalhador: " + nome);
        alert.setContentText("Você confirma o cadastro?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                int id;
                if(obraA == null){
                     id = Pessoa_CRUD.incluirPessoa(nome, "Nenhuma", "Nenhuma");
                }else {
                     id = Pessoa_CRUD.incluirPessoa(nome, obraA.getNome(), "Nenhuma");
                }Alert idShow = new Alert(Alert.AlertType.INFORMATION);
                idShow.setTitle("Cadastro confirmado");
                idShow.setHeaderText("O trabalhador " + nome + " foi cadastrado com sucesso");

                idShow.setContentText("O trabalhador foi cadastrado com id:" + id);
                idShow.showAndWait();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            nomeTrab.setText("");
        } else {
            nomeTrab.setText("");
        }
    }

    public void handleListarObra(ActionEvent actionEvent) throws Exception {
        campoNomeObra.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
        campoTrabs.setCellValueFactory(
                new PropertyValueFactory<>("trabsAtuais"));
        campoEmAndamento.setCellValueFactory(
                new PropertyValueFactory<>("emAndamento"));
        campoIDObra.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        ArrayList lista = new ArrayList();
        lista = Obra_CRUD.listarObras();
        obsObras = FXCollections.observableArrayList(lista);
        tabelaObra.setItems(obsObras);
        tabelaObraEmAndamento.setVisible(false);
        tabelaObra.setVisible(true);
    }

    public void handleListarObraEmAndamento(ActionEvent actionEvent) throws Exception {
        campoNomeObra2.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
        campoTrabs2.setCellValueFactory(
                new PropertyValueFactory<>("trabsAtuais"));
        campoIDObra2.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        ArrayList lista = new ArrayList();
        lista = Obra_CRUD.listarObrasEmAndamento();
        obsObras = FXCollections.observableArrayList(lista);
        tabelaObraEmAndamento.setItems(obsObras);
        tabelaObraEmAndamento.setVisible(true);
        tabelaObra.setVisible(false);
    }

    public void handleExcluirMat(ActionEvent e) {
        idMaterial.setVisible(true);
        butExcluirMat.setVisible(true);
        butAlterarMat.setVisible(false);
        butBuscarMat.setVisible(false);
        alterarNomeMat.setVisible(false);
        alterarQuantMat.setVisible(false);
        alterarUnMed.setVisible(false);
        butAlterarMat.setVisible(false);
    }

    public void handleExcluirObra(ActionEvent e) {
        idObra.setVisible(true);
        butExcluirObra.setVisible(true);
        butAlterarObra.setVisible(false);
        butBuscarObra.setVisible(false);
        alterarNomeObra.setVisible(false);
        alterarTrabs.setVisible(false);
    }

    public void handleExcluirFerramenta(ActionEvent e) {
        idFerramenta.setVisible(true);
        butExcluirFerramenta.setVisible(true);
        butAlterarFerramenta.setVisible(false);
        butBuscarFerramenta.setVisible(false);

        alterarNomeFerramenta.setVisible(false);
        alterarQuantFerramenta.setVisible(false);
        alterarMarca.setVisible(false);
    }

    public void handleExcluirTrab(ActionEvent e) {
        idTrab.setVisible(true);
        butExcluirTrab.setVisible(true);
        butAlterarTrab.setVisible(false);
        butBuscarTrab.setVisible(false);
        alterarNomeTrab.setVisible(false);
        alterarObraAtual.setVisible(false);
    }

    public void deleteMat(ActionEvent e){
        int id = Integer.parseInt(idMaterial.getText());
        try {
            Material_CRUD.excluirMaterial(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        idMaterial.setVisible(false);
        butExcluirMat.setVisible(false);
    }

    public void deleteObra(ActionEvent e){
        int id = Integer.parseInt(idObra.getText());
        try {
            Obra_CRUD.excluirObra(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        idObra.setVisible(false);
        butExcluirObra.setVisible(false);
    }

    public void deleteFerramenta(ActionEvent e){
        int id = Integer.parseInt(idFerramenta.getText());
        try {
            Ferramenta_CRUD.excluirFerramenta(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        idFerramenta.setVisible(false);
        butExcluirFerramenta.setVisible(false);
    }

    public void deleteTrab(ActionEvent e){
        int id = Integer.parseInt(idTrab.getText());
        try {
            Pessoa_CRUD.excluirPessoa(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        idTrab.setVisible(false);
        butExcluirTrab.setVisible(false);
    }

    public void handleAlterarMat(ActionEvent e){
        idMaterial.setVisible(true);
        alterarNomeMat.setVisible(true);
        alterarQuantMat.setVisible(true);
        alterarUnMed.setVisible(true);
        butAlterarMat.setVisible(true);
        butExcluirMat.setVisible(false);
        butBuscarMat.setVisible(false);
    }

    public void handleAlterarObra(ActionEvent e){
        idObra.setVisible(true);
        butExcluirObra.setVisible(false);
        butAlterarObra.setVisible(true);
        butBuscarObra.setVisible(false);
        alterarNomeObra.setVisible(true);
        alterarTrabs.setVisible(true);
    }

    public void handleAlterarFerramenta(ActionEvent e){
        idFerramenta.setVisible(true);
        alterarNomeFerramenta.setVisible(true);
        alterarQuantFerramenta.setVisible(true);
        alterarMarca.setVisible(true);
        butAlterarFerramenta.setVisible(true);
        butExcluirFerramenta.setVisible(false);
        butBuscarFerramenta.setVisible(false);
    }

    public void handleAlterarTrab(ActionEvent e){
        idTrab.setVisible(true);
        alterarNomeTrab.setVisible(true);
        alterarObraAtual.setVisible(true);
        butAlterarTrab.setVisible(true);
        butExcluirTrab.setVisible(false);
        butBuscarTrab.setVisible(false);
    }

    public void updateObra(ActionEvent e){
        int id = Integer.parseInt(idObra.getText());
        String nome = alterarNomeObra.getText();
        String trabs = alterarTrabs.getText();
        try {
            Obra_CRUD.alterarObra(id, nome, trabs);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateMat(ActionEvent e) throws Exception {
        int id = Integer.parseInt(idMaterial.getText());
        String nome = alterarNomeMat.getText();
        int quant = Integer.parseInt(alterarQuantMat.getText());
        String unMed = alterarUnMed.getText();
        Material obj = Material_CRUD.buscarMaterial(id);
        if (obj.getQuantTotal() != obj.getQuantDisp()){
            try {
                int dif = quant - obj.getQuantTotal() ;
                Material_CRUD.alterarMaterial(id, nome,quant , obj.getQuantDisp() + dif, unMed,"true");
            } catch (Exception ex) {
                ex.printStackTrace();
                MenuController.retorno("false");
            }
        }else{

        try {
            Material_CRUD.alterarMaterial(id, nome,quant , quant, unMed,"true");
        } catch (Exception ex) {
            ex.printStackTrace();
            MenuController.retorno("false");
        }
        }
    }

    public void updateFerramenta(ActionEvent e) throws Exception {
        int id = Integer.parseInt(idFerramenta.getText());
        String nome = alterarNomeMat.getText();
        int quant = Integer.parseInt(alterarQuantMat.getText());
        String marca = alterarMarca.getText();
        Material obj = Ferramenta_CRUD.buscarFerramenta(id);
        if (obj.getQuantDisp() != obj.getQuantTotal()){
            try {
                int dif = quant - obj.getQuantTotal() ;
                Ferramenta_CRUD.alterarFerramenta(id, nome, quant, obj.getQuantDisp() + dif , marca, "true");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else{
        try {
            Ferramenta_CRUD.alterarFerramenta(id, nome, quant, quant, marca, "true");
        } catch (Exception ex) {
            ex.printStackTrace();
        }}
    }

    public void updateTrab(ActionEvent e){
        int id = Integer.parseInt(idTrab.getText());
        String nome = alterarNomeTrab.getText();
        String obraAtual = alterarObraAtual.getText();
        try {
            Pessoa_CRUD.alterarTrab(id, nome, obraAtual, "Teste", "true");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void handleBuscarMat(ActionEvent e){
        butAlterarMat.setVisible(false);
        butExcluirMat.setVisible(false);
        butBuscarMat.setVisible(true);
        idMaterial.setVisible(true);
        alterarNomeMat.setVisible(false);
        alterarQuantMat.setVisible(false);
        alterarUnMed.setVisible(false);
        butAlterarMat.setVisible(false);
    }

    public void handleBuscarObra(ActionEvent e){
        butAlterarObra.setVisible(false);
        butExcluirObra.setVisible(false);
        butBuscarObra.setVisible(true);
        idObra.setVisible(true);
        alterarNomeObra.setVisible(false);
        alterarTrabs.setVisible(false);
    }

    public void handleBuscarFerramenta(ActionEvent e){
        butAlterarFerramenta.setVisible(false);
        butExcluirFerramenta.setVisible(false);
        butBuscarFerramenta.setVisible(true);
        idFerramenta.setVisible(true);
        alterarNomeFerramenta.setVisible(false);
        alterarQuantFerramenta.setVisible(false);
        alterarMarca.setVisible(false);
    }

    public void handleBuscarTrab(ActionEvent e){
        butAlterarTrab.setVisible(false);
        butExcluirTrab.setVisible(false);
        butBuscarTrab.setVisible(true);
        idTrab.setVisible(true);
        butBuscarTrab.setVisible(true);
        alterarNomeTrab.setVisible(false);
        alterarObraAtual.setVisible(false);
    }

    @FXML public void voltarTrab(ActionEvent e){
        paneTrab.setVisible(false);
    }
    @FXML public void voltarFer(ActionEvent e){
        paneFerramenta.setVisible(false);
    }
    @FXML public void voltarObra(ActionEvent e){
        paneObra.setVisible(false);
    }
    @FXML public void voltarMat(ActionEvent e){
        paneMaterial.setVisible(false);
    }

    public boolean buscarMat(ActionEvent e){
        int id = Integer.parseInt(idMaterial.getText());
        try {
           Material obj = Material_CRUD.buscarMaterial(id);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText(obj.toString());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        idMaterial.setVisible(false);
        butBuscarMat.setVisible(false);
        return false;
    }

    public boolean buscarObra(ActionEvent e){
        int id = Integer.parseInt(idObra.getText());
        try {
            Obra obj = Obra_CRUD.buscarObra(id);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText(obj.toString());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        idObra.setVisible(false);
        butBuscarObra.setVisible(false);
        return false;
    }

    public boolean buscarFerramenta(ActionEvent e){
        int id = Integer.parseInt(idFerramenta.getText());
        try {
            Ferramenta obj = Ferramenta_CRUD.buscarFerramenta(id);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText(obj.toString());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        idFerramenta.setVisible(false);
        butBuscarFerramenta.setVisible(false);
        return false;
    }

    public boolean buscarTrab(ActionEvent e){
        int id = Integer.parseInt(idTrab.getText());
        try {
            Pessoa obj = Pessoa_CRUD.buscarPessoa(id);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText(obj.toString());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        idTrab.setVisible(false);
        butBuscarTrab.setVisible(false);
        return false;
    }

    public void gerenciarObra(ActionEvent e){
        paneObra.setVisible(true);
    }

    public void gerenciarFer(ActionEvent e){
        paneFerramenta.setVisible(true);
    }
    public void gerenciarMat(ActionEvent e){
        paneMaterial.setVisible(true);
    }
    public void gerenciarTrab(ActionEvent e){
        paneTrab.setVisible(true);
    }

    public void handleAddTrab(ActionEvent actionEvent) {
        trabsObra.setVisible(true);
        idObra.setVisible(true);
        butAddTrabMesmo.setVisible(true);
    }

    public void addTrab(ActionEvent e) throws Exception {
       Obra obj = Obra_CRUD.buscarObra(Integer.parseInt(idObra.getText()));
       String trabsAtuais = obj.getTrabsAtuais();
       Pessoa p = trabsObra.getSelectionModel().getSelectedItem();
       trabsAtuais += "," + p.getNome();
       Obra_CRUD.alterarObra(obj.getId(),obj.getNome(),trabsAtuais);
       Pessoa_CRUD.alterarTrab(p.getId(),p.getNome(),obj.getNome(),p.getFerAtuais(),"nada");
    }
}