package project.database;

import project.controller.MenuController;
import project.model.Obra;
import project.model.Pessoa;

import java.util.ArrayList;
import java.util.Scanner;

public class Obra_CRUD{

    private static Scanner console = new Scanner(System.in);
    private static Arquivo<Obra> arqObra;



    public static ArrayList listarObras() throws Exception {
        try {
            arqObra = new Arquivo<>(Obra.class.getConstructor(), "obras.db");
        } catch(Exception e) {
            e.printStackTrace();
        }

        ArrayList<Obra> list = new ArrayList();
        Object[] obj = arqObra.listar();
        for(int i=0; i<obj.length; i++) {
            list.add((Obra) obj[i]);
        }
        return list;
    }

    public static ArrayList listarObrasEmAndamento() throws Exception {
        try {
            arqObra = new Arquivo<>(Obra.class.getConstructor(), "obras.db");
        } catch(Exception e) {
            e.printStackTrace();
        }

        ArrayList<Obra> list = new ArrayList();
        ArrayList<Obra> listD = new ArrayList();
        Object[] obj = arqObra.listar();
        for(int i=0; i<obj.length; i++) {
            list.add((Obra) obj[i]);
        }
        for(int i=0; i<obj.length; i++) {
            if (list.get(i).isEmAndamento())
                 listD.add(list.get(i));
        }
        return listD;
    }

    public static int incluirObra(String nome, String trab) throws Exception {
        try {
            arqObra = new Arquivo<>(Obra.class.getConstructor(), "obras.db");
        } catch(Exception e) {
            e.printStackTrace();
        }

        Obra obj = new Obra(-1, nome, trab);
        ArrayList<Pessoa> trabs = Pessoa_CRUD.listarPessoas();
        for (int i=0; i<trabs.size(); i++){
            if(trabs.get(i).getNome().equals(trab))
                Pessoa_CRUD.alterarTrab(trabs.get(i).getId(),trabs.get(i).getNome(), nome, trabs.get(i).getFerAtuais(), "nada");
        }
        int id = arqObra.incluir(obj);
        return id;
    }

    public static void excluirObra(int id) throws Exception {
        try {
            arqObra = new Arquivo<>(Obra.class.getConstructor(), "obras.db");
        } catch(Exception e) {
            e.printStackTrace();
        }
        if(id <=0)
            return;

        Obra obj;
        if( (obj = (Obra) arqObra.buscar(id))!=null ) {
            Boolean confirma = MenuController.confirmar(obj);
            ArrayList<Pessoa> trabs = new ArrayList<>();
            trabs = Pessoa_CRUD.listarPessoas();
            for (int i = 0; i<trabs.size(); i++){
                if(trabs.get(i).getObraAtual().equals(obj.getNome())) {
                    int idTrab = trabs.get(i).getId();
                    Pessoa_CRUD.alterarTrab(idTrab, trabs.get(i).getNome(),"Nenhuma",trabs.get(i).getFerAtuais(), "nada");
                }
            }
            if(confirma == true) {
                if( arqObra.excluir(id) ) {
                    MenuController.retorno("true");
                }
                else
                    MenuController.retorno("false");
            }
        }
        else
            MenuController.retorno("false");
    }


    public static Obra buscarObra(int codigo) throws Exception {
        try {
            arqObra = new Arquivo<>(Obra.class.getConstructor(), "obras.db");
        } catch(Exception e) {
            e.printStackTrace();
        }
        if(codigo <=0)
            return null;

        Obra obj;
        if( (obj = (Obra) arqObra.buscar(codigo))!=null )
            return obj;
        else {
            MenuController.retorno("false");
            return null;
        }
    }

    public static void alterarObra(int id, String nome, String trabs) throws Exception {
        try {
            arqObra = new Arquivo<>(Obra.class.getConstructor(), "obras.db");
        } catch(Exception e) {
            e.printStackTrace();
        }

        Obra obj;
        if( (obj = (Obra) arqObra.buscar(id))!=null ) {

            if(nome.length()>0 || nome.length()>0 ) {
                obj.setNome(nome.length()>0 ? nome : obj.getNome());
                obj.setTrabsAtuais(trabs.length()>0 ? trabs : obj.getTrabsAtuais());

                    if (arqObra.alterar(obj))
                        MenuController.retorno("true");
                    else
                        MenuController.retorno("false");

            }
        }
        else
            MenuController.retorno("false");

    }
}


