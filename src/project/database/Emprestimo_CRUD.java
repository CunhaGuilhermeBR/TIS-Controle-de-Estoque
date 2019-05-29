package project.database;

import project.controller.MenuController;
import project.model.Emprestimo;
import project.model.Ferramenta;
import project.model.Pessoa;


import java.util.ArrayList;
import java.util.Scanner;

public class Emprestimo_CRUD {

    private static Scanner console = new Scanner(System.in);
    private static Arquivo<Emprestimo> arqEmprestimos;

    public static ArrayList listarEmprestimos() throws Exception {
        try {
            arqEmprestimos = new Arquivo<>(Emprestimo.class.getConstructor(), "emprestimos.db");
        } catch(Exception e) {
            e.printStackTrace();
        }

        ArrayList<Emprestimo> list = new ArrayList();
        Object[] obj = arqEmprestimos.listar();
        for(int i=0; i<obj.length; i++) {
            list.add((Emprestimo) obj[i]);
        }
        return list;
    }

    public static int incluirEmprestimo(String data, String ferramenta, int quant, String trab) throws Exception {
        try {
            arqEmprestimos = new Arquivo<>(Emprestimo.class.getConstructor(), "emprestimos.db");
        } catch(Exception e) {
            e.printStackTrace();
        }
        ArrayList<Ferramenta> fers = Ferramenta_CRUD.listarFerramentasDisp();
        ArrayList<Pessoa> pessoas = Pessoa_CRUD.listarPessoas();
        Boolean nome = false;
        Pessoa trabAtual = new Pessoa();
        for (int i = 0; i < pessoas.size(); i++){
            if(pessoas.get(i).getNome().equalsIgnoreCase(trab)) {
                trabAtual = pessoas.get(i);
                nome = true;
            }
        }
        for (int i =0; i <= fers.size(); i++){
            if (fers.get(i).getNome().equals(ferramenta) && nome == true){
                if (fers.get(i).getQuantDisp() >= quant){
                    int q = fers.get(i).getQuantDisp();
                    Ferramenta_CRUD.alterarFerramenta(fers.get(i).getId(), fers.get(i).getNome(),fers.get(i).getQuantTotal(),q-quant,fers.get(i).getMarca(),"nada");
                    Emprestimo obj = new Emprestimo(-1, data, ferramenta, quant, trab);
                    int id = arqEmprestimos.incluir(obj);
                    if (trabAtual.getFerAtuais().equals("Nenhuma")){
                        Pessoa_CRUD.alterarTrab(trabAtual.getId(),trabAtual.getNome(), trabAtual.getObraAtual(),quant + " "+ ferramenta, "nada");
                    }
                    else{
                     String fers2 = trabAtual.getFerAtuais() + "," + quant + " "+ ferramenta;
                        Pessoa_CRUD.alterarTrab(trabAtual.getId(),trabAtual.getNome(), trabAtual.getObraAtual(),fers2, "nada");
                    }
                    MenuController.retorno("true");
                    return id;
                }
                MenuController.retorno("false");
            }
        }
        return 0;
    }

    public static void finalizarEmp(int id, String data) throws Exception {
        try {
            arqEmprestimos = new Arquivo<>(Emprestimo.class.getConstructor(), "emprestimos.db");
        } catch(Exception e) {
            e.printStackTrace();
        }
        if (id <= 0)
            return;

        Emprestimo obj;
        ArrayList<Pessoa> trabs = new ArrayList<>();
        ArrayList<Ferramenta> ferramentas = new ArrayList<>();
        trabs = Pessoa_CRUD.listarPessoas();
        ferramentas = Ferramenta_CRUD.listarFerramentas();
        String[] fers ;
        String ferAtuais = "";
        if( (obj = (Emprestimo) arqEmprestimos.buscar(id)) != null ) {
            Emprestimo_CRUD.alterarEmprestimo(obj.getId(), obj.getDataEmprestimo(),data, obj.getFerramenta(), obj.getQuantFer(), false);
            for (int i = 0; i < trabs.size(); i++){
                String ferTrab = trabs.get(i).getFerAtuais();
                String ferEmprestimo = obj.getQuantFer()+ " " + obj.getFerramenta() ;

                fers = ferTrab.split(",");
                for (int j = 0; j < fers.length; j++){
                    if ( fers[j].equals(ferEmprestimo)){

                    }
                    else
                        ferAtuais += ferTrab;
                }
                if (ferAtuais.equals(""))
                    Pessoa_CRUD.alterarTrab(trabs.get(i).getId(),trabs.get(i).getNome(),trabs.get(i).getObraAtual(),"Nenhuma", "nada");
                else
                    Pessoa_CRUD.alterarTrab(trabs.get(i).getId(),trabs.get(i).getNome(),trabs.get(i).getObraAtual(),ferAtuais, "nada");

                ferAtuais = "";

            }
            int quant = obj.getQuantFer();
            for (int i = 0; i < ferramentas.size(); i++){
                if (ferramentas.get(i).getNome().equals(obj.getFerramenta()))
                    Ferramenta_CRUD.alterarFerramenta(ferramentas.get(i).getId(), ferramentas.get(i).getNome(), ferramentas.get(i).getQuantTotal(),ferramentas.get(i).getQuantDisp() + quant, ferramentas.get(i).getMarca(), "nada");
            }

            MenuController.retorno("true");
        }
        else
        MenuController.retorno("false");



    }

    public static void excluirEmpréstimo(int id) throws Exception {
        try {
            arqEmprestimos = new Arquivo<>(Emprestimo.class.getConstructor(), "emprestimos.db");
        } catch(Exception e) {
            e.printStackTrace();
        }

        if(id <=0)
            return;

        Emprestimo obj;
        if( (obj = (Emprestimo) arqEmprestimos.buscar(id))!=null ) {
            Boolean confirma = MenuController.confirmar(obj);

                if( arqEmprestimos.excluir(id) ) {
                    MenuController.retorno("true");
                }
                else
                    MenuController.retorno("false");
            }

        else
            MenuController.retorno("false");
    }


    public static Emprestimo buscarEmprestimo(int codigo) throws Exception {
        try {
            arqEmprestimos = new Arquivo<>(Emprestimo.class.getConstructor(), "emprestimos.db");
        } catch(Exception e) {
            e.printStackTrace();
        }

        if(codigo <=0)
            return null;

        Emprestimo obj;
        if( (obj = (Emprestimo) arqEmprestimos.buscar(codigo))!=null )
            return obj;
        else {
            MenuController.retorno("false");
            return null;
        }
    }

    public static void alterarEmprestimo(int id,String data,String dataR, String ferramenta, int quant, boolean emAnd ) throws Exception {
        try {
            arqEmprestimos = new Arquivo<>(Emprestimo.class.getConstructor(), "emprestimos.db");
        } catch(Exception e) {
            e.printStackTrace();
        }

        Emprestimo obj;
        if( (obj = (Emprestimo) arqEmprestimos.buscar(id))!=null ) {

            if(data.length()>0 || dataR.length()>0 || quant>=0) {
                obj.setDataEmprestimo(data.length()>0 ? data : obj.getDataEmprestimo());
                obj.setQuantFer(quant>=0?quant:obj.getQuantFer());
                obj.setFerramenta(ferramenta.length()>0 ? ferramenta : obj.getFerramenta());
                obj.setDataRetorno(dataR.length()>0 ? dataR : obj.getDataRetorno());
                obj.setEmAndamento(emAnd);

                    if (arqEmprestimos.alterar(obj))
                        MenuController.retorno("true");
                    else
                        MenuController.retorno("false");

            }
        }
        else
            MenuController.retorno("false");

    }
}

