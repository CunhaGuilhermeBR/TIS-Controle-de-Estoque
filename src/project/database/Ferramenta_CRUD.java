package project.database;

import project.controller.MenuController;
import project.model.Ferramenta;

import java.util.ArrayList;
import java.util.Scanner;

public class Ferramenta_CRUD{

    private static Scanner console = new Scanner(System.in);
    private static Arquivo<Ferramenta> arqFerramentas;

    public static ArrayList listarFerramentas() throws Exception {
        try {
            arqFerramentas = new Arquivo<>(Ferramenta.class.getConstructor(), "ferramentas.db");
        } catch(Exception e) {
            e.printStackTrace();
        }

        ArrayList<Ferramenta> list = new ArrayList();
        Object[] obj = arqFerramentas.listar();
        for(int i=0; i<obj.length; i++) {
            list.add((Ferramenta) obj[i]);
        }
        return list;
    }

    public static ArrayList listarFerramentasDisp() throws Exception {
        try {
            arqFerramentas = new Arquivo<>(Ferramenta.class.getConstructor(), "ferramentas.db");
        } catch(Exception e) {
            e.printStackTrace();
        }

        ArrayList<Ferramenta> list = new ArrayList();
        ArrayList<Ferramenta> listD = new ArrayList<>();
        Object[] obj = arqFerramentas.listar();
        for(int i=0; i<obj.length; i++) {
            list.add((Ferramenta) obj[i]);
        }
        for(int i=0; i<obj.length; i++) {
            if (list.get(i).isDisponivel())
                listD.add(list.get(i));
        }
        return list;
    }

    public static int incluirFerramenta(String nome, int quant, String marca) throws Exception {
        try {
            arqFerramentas = new Arquivo<>(Ferramenta.class.getConstructor(), "ferramentas.db");
        } catch(Exception e) {
            e.printStackTrace();
        }

        Ferramenta obj = new Ferramenta(-1, nome, quant, marca);
        int id = arqFerramentas.incluir(obj);
        return id;
    }

    public static void excluirFerramenta(int id) throws Exception {
        try {
            arqFerramentas = new Arquivo<>(Ferramenta.class.getConstructor(), "ferramentas.db");
        } catch(Exception e) {
            e.printStackTrace();
        }
        if(id <=0)
            return;

        Ferramenta obj;
        if( (obj = (Ferramenta) arqFerramentas.buscar(id))!=null ) {
            Boolean confirma = MenuController.confirmar(obj);
            if(confirma = true) {
                if( arqFerramentas.excluir(id) ) {
                    MenuController.retorno("true");
                }
                else
                    MenuController.retorno("false");
            }
        }
        else
            System.out.println("Material não encontrado");
    }


    public static Ferramenta buscarFerramenta(int codigo) throws Exception {
        try {
            arqFerramentas = new Arquivo<>(Ferramenta.class.getConstructor(), "ferramentas.db");
        } catch(Exception e) {
            e.printStackTrace();
        }
        if(codigo <=0)
            return null;

        Ferramenta obj;
        if( (obj = (Ferramenta) arqFerramentas.buscar(codigo))!=null )
            return obj;
        else {
            MenuController.retorno("false");
            return null;
        }
    }
    public static void alterarFerramenta(int id, String nome, int quantTotal, int quantDisp, String marca, String ver) throws Exception {
        try {
            arqFerramentas = new Arquivo<>(Ferramenta.class.getConstructor(), "ferramentas.db");
        } catch(Exception e) {
            e.printStackTrace();
        }

        Ferramenta obj;
        if( (obj = (Ferramenta) arqFerramentas.buscar(id))!=null ) {

            if(nome.length()>0 || nome.length()>0 || quantTotal>=0) {
                obj.setNome(nome.length()>0 ? nome : obj.getNome());
                obj.setQuantTotal(quantTotal>=0?quantTotal:obj.getQuantTotal());
                obj.setMarca(marca.length()>0 ? marca : obj.getMarca());
                obj.setQuantDisp(quantDisp>=0?quantDisp:obj.getQuantDisp());

                        if( arqFerramentas.alterar(obj) )
                        MenuController.retorno(ver);
                else
                            MenuController.retorno(ver);


            }
        }
        else
            MenuController.retorno("false");

    }
}


