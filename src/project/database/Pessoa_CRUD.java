package project.database;

import project.controller.MenuController;
import project.model.Obra;
import project.model.Pessoa;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Pessoa_CRUD{

    private static Scanner console = new Scanner(System.in);
    private static Arquivo<Pessoa> arqPessoas;

    public static ArrayList listarPessoas() throws Exception {
        try {
            arqPessoas = new Arquivo<>(Pessoa.class.getConstructor(), "pessoas.db");
        } catch(Exception e) {
            e.printStackTrace();
        }

        ArrayList list = new ArrayList();
        Object[] obj = arqPessoas.listar();
        for(int i=0; i<obj.length; i++) {
            list.add(obj[i]);
        }
        return list;
    }

    public static ArrayList listarPessoasAtoas() throws Exception {
        try {
            arqPessoas = new Arquivo<>(Pessoa.class.getConstructor(), "pessoas.db");
        } catch(Exception e) {
            e.printStackTrace();
        }

        ArrayList<Pessoa> list = new ArrayList();
        ArrayList listAtoa = new ArrayList();
        Object[] obj = arqPessoas.listar();
        for(int i=0; i<obj.length; i++) {
            list.add((Pessoa) obj[i]);
        }
        for(int i=0; i<obj.length; i++) {
            if(list.get(i).getObraAtual().equals("Nenhuma"))
                listAtoa.add(list.get(i));
        }
        return listAtoa;
    }

    public static int incluirPessoa(String nome, String obraA, String fers) throws Exception {
        try {
            arqPessoas = new Arquivo<>(Pessoa.class.getConstructor(), "pessoas.db");
        } catch(Exception e) {
            e.printStackTrace();
        }
        Pessoa obj = new Pessoa(-1, nome, obraA, fers);
        ArrayList<Obra> obras = Obra_CRUD.listarObras();
        for (int i =0; i<obras.size();i++){
              if( obras.get(i).getNome().equals(obraA) == true){
                  String trabs = obras.get(i).getTrabsAtuais() + "," + nome;
                  Obra_CRUD.alterarObra(obras.get(i).getId(), obras.get(i).getNome(), trabs);
            }
        }
        int id = arqPessoas.incluir(obj);
        return id;
    }

    public static void excluirPessoa(int id) throws Exception {
        try {
            arqPessoas = new Arquivo<>(Pessoa.class.getConstructor(), "pessoas.db");
        } catch(Exception e) {
            e.printStackTrace();
        }
        if(id <=0)
            return;

        Pessoa obj;
        if( (obj = (Pessoa) arqPessoas.buscar(id))!=null ) {
            Boolean confirma = MenuController.confirmar(obj);
            ArrayList<Obra> obras = new ArrayList<>();
            obras = Obra_CRUD.listarObras();
            StringTokenizer aux = new StringTokenizer(",");
            StringBuilder builder = new StringBuilder();

            if( obj.getObraAtual().equals("Nenhuma")){ }
            else{
            for ( int i =0; i<obras.size(); i++){
                    if(obras.get(i).getNome().equals(obj.getObraAtual())){
                        String str = obras.get(i).getTrabsAtuais();
                        String[] strA = str.split(",");
                        for (int j =0; j<strA.length; j++){
                            if(strA[j].equals(obj.getNome())){ }else{
                               builder.append(strA[j]);
                               if(builder.toString().length()>0)
                                  Obra_CRUD.alterarObra(obras.get(i).getId(),obras.get(i).getNome(),builder.toString());
                               else
                                   Obra_CRUD.alterarObra(obras.get(i).getId(),obras.get(i).getNome(),"Ninguém");
                        }
                        }
                    }
                }
            }
            if(confirma == true) {
                if( arqPessoas.excluir(id) ) {
                    MenuController.retorno("true");
                }
                else
                    MenuController.retorno("false");
            }
        }
        else
            MenuController.retorno("false");
    }


    public static Pessoa buscarPessoa(int codigo) throws Exception {
        try {
            arqPessoas = new Arquivo<>(Pessoa.class.getConstructor(), "pessoas.db");
        } catch(Exception e) {
            e.printStackTrace();
        }
        if(codigo <=0)
            return null;

        Pessoa obj;
        if( (obj = (Pessoa) arqPessoas.buscar(codigo))!=null )
            return obj;
        else {
            MenuController.retorno("false");
            return null;
        }
    }

    public static void alterarTrab(int id, String nome, String obraA, String fer, String ver) throws Exception {
        try {
            arqPessoas = new Arquivo<>(Pessoa.class.getConstructor(), "pessoas.db");
        } catch(Exception e) {
            e.printStackTrace();
        }

        Pessoa obj;
        if( (obj = (Pessoa) arqPessoas.buscar(id))!=null ) {

            if(nome.length()>0 || nome.length()>0 ) {
                obj.setNome(nome.length()>0 ? nome : obj.getNome());
                obj.setObraAtual(obraA.length()>0 ? obraA : obj.getObraAtual());
                obj.setFerAtuais(fer.length()>0 ? fer : fer);
                    if (arqPessoas.alterar(obj))
                        MenuController.retorno(ver);
                    else
                        MenuController.retorno(ver);

            }
        }
        else
            MenuController.retorno(ver);

    }
}


