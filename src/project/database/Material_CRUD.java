package project.database;

import project.controller.MenuController;
import project.model.Material;
import java.util.ArrayList;
import java.util.Scanner;

public class Material_CRUD{

    private static Scanner console = new Scanner(System.in);
    private static Arquivo<Material> arqMateriais;



    public static ArrayList listarMaterias() throws Exception {
        try {
            arqMateriais = new Arquivo<>(Material.class.getConstructor(), "materiais.db");
        } catch(Exception e) {
            e.printStackTrace();
        }

        ArrayList<Material> list = new ArrayList();
        Object[] obj = arqMateriais.listar();
        for(int i=0; i<obj.length; i++) {
            list.add((Material) obj[i]);
        }
        return list;
    }

    public static ArrayList listarMateriasDisp() throws Exception {
        try {
            arqMateriais = new Arquivo<>(Material.class.getConstructor(), "materiais.db");
        } catch(Exception e) {
            e.printStackTrace();
        }

        ArrayList<Material> list = new ArrayList();
        ArrayList<Material> listD = new ArrayList<>();
        Object[] obj = arqMateriais.listar();
        for(int i=0; i<obj.length; i++) {
            list.add((Material) obj[i]);
            if (list.get(i).isDisponivel() == true)
                listD.add((Material) obj[i]);
        }
        return listD;
    }

    public static int incluirMaterial(String nome, int quant, String unM) throws Exception {
        try {
            arqMateriais = new Arquivo<>(Material.class.getConstructor(), "Materiais.db");
        } catch(Exception e) {
            e.printStackTrace();
        }

            Material obj = new Material(-1, nome, quant, unM);
            int id = arqMateriais.incluir(obj);
            return id;
    }

    public static void excluirMaterial(int id) throws Exception {
        try {
            arqMateriais = new Arquivo<>(Material.class.getConstructor(), "Materiais.db");
        } catch(Exception e) {
            e.printStackTrace();
        }
        if(id <=0)
            return;

        Material obj;
        if( (obj = (Material) arqMateriais.buscar(id))!=null ) {
            Boolean confirma = MenuController.confirmar(obj);
            if(confirma == true) {
                if( arqMateriais.excluir(id) ) {
                    MenuController.retorno("true");
                }
                else
                    MenuController.retorno("false");
            }
        }
        else
            MenuController.retorno("false");
    }


    public static Material buscarMaterial(int codigo) throws Exception {
        try {
            arqMateriais = new Arquivo<>(Material.class.getConstructor(), "Materiais.db");
        } catch(Exception e) {
            e.printStackTrace();
        }
        if(codigo <=0)
            return null;

        Material obj;
        if( (obj = (Material) arqMateriais.buscar(codigo))!=null )
            return obj;
        else {
            MenuController.retorno("false");
            return null;
        }
    }

    public static void alterarMaterial(int id, String nome, int quantTotal, int quantDisp, String unM, String ver) throws Exception {
        try {
            arqMateriais = new Arquivo<>(Material.class.getConstructor(), "Materiais.db");
        } catch(Exception e) {
            e.printStackTrace();
        }

        Material obj;
        if( (obj = (Material) arqMateriais.buscar(id))!=null ) {

            if(nome.length()>0 || nome.length()>0 || quantTotal>=0) {
                    obj.setNome(nome.length()>0 ? nome : obj.getNome());
                    obj.setQuantTotal(quantTotal>=0?quantTotal:obj.getQuantTotal());
                    obj.setUnMedida(unM.length()>0 ? unM : obj.getUnMedida());
                    obj.setQuantDisp(quantDisp>=0?quantDisp:obj.getQuantDisp());

                 if (arqMateriais.alterar(obj))
        MenuController.retorno(ver);
                 else
        MenuController.retorno(ver);

            }
        }
        else
            MenuController.retorno("false");

    }
}


