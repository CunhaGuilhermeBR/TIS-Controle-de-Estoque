package project.database;

import project.controller.MenuController;
import project.model.*;


import java.util.ArrayList;
import java.util.Scanner;

public class Uti_CRUD {

    private static Scanner console = new Scanner(System.in);
    private static Arquivo<Utilização> arqUtilizações;

    public static ArrayList listarUtilizacoes() throws Exception {
        try {
            arqUtilizações = new Arquivo<Utilização>(Utilização.class.getConstructor(), "utilizações.db");
        } catch(Exception e) {
            e.printStackTrace();
        }

        ArrayList<Utilização> list = new ArrayList();
        Object[] obj = arqUtilizações.listar();
        for(int i=0; i<obj.length; i++) {
            list.add((Utilização) obj[i]);
        }
        return list;
    }

    public static int incluirUtilizacao(String data, String material, int quant, String trab) throws Exception {
        try {
            arqUtilizações = new Arquivo<Utilização>(Utilização.class.getConstructor(), "utilizações.db");
        } catch(Exception e) {
            e.printStackTrace();
        }
        ArrayList<Material> materiais = Material_CRUD.listarMaterias();
        for (int i =0; i <= materiais.size(); i++){
            if (materiais.get(i).getNome().equals(material) ){
                if (materiais.get(i).getQuantDisp() >= quant){
                    int q = materiais.get(i).getQuantDisp();
                    Material_CRUD.alterarMaterial(materiais.get(i).getId(), materiais.get(i).getNome(), materiais.get(i).getQuantTotal(),q-quant,materiais.get(i).getUnMedida(),"nada");
                    Utilização obj = new Utilização(-1, data, material, quant, trab);
                    int id = arqUtilizações.incluir(obj);
                    MenuController.retorno("true");
                    return id;
                }
            }
        }
        return 0;
    }

    public static void finalizarUti(int id, String data) throws Exception {
        try {
            arqUtilizações = new Arquivo<Utilização>(Utilização.class.getConstructor(), "utilizações.db");
        } catch(Exception e) {
            e.printStackTrace();
        }
        if (id <= 0)
            return;

        Utilização obj;
        if( (obj = (Utilização) arqUtilizações.buscar(id)) != null ) {
            Uti_CRUD.alterarUti(obj.getId(), obj.getDataEmprestimo(),data, obj.getMaterial(), obj.getQuantFer(), false, "nada");
            MenuController.retorno("true");
        }
        else
            MenuController.retorno("false");
    }

//    public static void excluirUtilizacao(int id) throws Exception {
//        try {
//            arqUtilizações = new Arquivo<Utilização>(Utilização.class.getConstructor(), "utilizações.db");
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//
//        if(id <=0)
//            return;
//
//        Utilização obj;
//        if( (obj = (Utilização) arqUtilizações.buscar(id))!=null ) {
//            Boolean confirma = MenuController.confirmar(obj);
//
//            if( arqUtilizações.excluir(id) ) {
//                MenuController.retorno("true");
//            }
//            else
//                MenuController.retorno("false");
//        }
//
//        else
//            MenuController.retorno("false");
//    }


    public static Utilização buscarUtilizacao(int codigo) throws Exception {
        try {
            arqUtilizações = new Arquivo<Utilização>(Utilização.class.getConstructor(), "utilizações.db");
        } catch(Exception e) {
            e.printStackTrace();
        }

        if(codigo <=0)
            return null;

        Utilização obj;
        if( (obj = (Utilização) arqUtilizações.buscar(codigo))!=null )
            return obj;
        else {
            MenuController.retorno("false");
            return null;
        }
    }

    public static void alterarUti(int id, String data, String dataR, String material, int quant, boolean emAnd, String ver ) throws Exception {
        try {
            arqUtilizações = new Arquivo<Utilização>(Utilização.class.getConstructor(), "utilizações.db");
        } catch(Exception e) {
            e.printStackTrace();
        }

        Utilização obj;
        if( (obj = (Utilização) arqUtilizações.buscar(id))!=null ) {
            if(data.length()>0 || dataR.length()>0 || quant>=0) {
                obj.setDataEmprestimo(data.length()>0 ? data : obj.getDataEmprestimo());
                obj.setQuantFer(quant>=0?quant:obj.getQuantFer());
                obj.setMaterial(material.length()>0 ? material : obj.getMaterial());
                obj.setDataRetorno(dataR.length()>0 ? dataR : obj.getDataRetorno());
                obj.setEmAndamento(emAnd);
                if (arqUtilizações.alterar(obj))
                    MenuController.retorno(ver);
                else
                    MenuController.retorno(ver);

            }
        }
        else
            MenuController.retorno("false");
    }
}


