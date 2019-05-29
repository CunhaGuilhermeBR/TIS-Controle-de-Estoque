package project.database;

import java.io.*;
import java.util.*;
import java.lang.reflect.Constructor;

public class Arquivo<T extends Entidade> {

    RandomAccessFile arquivo;
    String nomeArquivo;
    Constructor<T> construtor;

    public Arquivo(Constructor<T> c, String n) throws Exception {
        nomeArquivo = n;
        construtor = c;
        arquivo = new RandomAccessFile(nomeArquivo, "rw");
        if(arquivo.length()<4)
            arquivo.writeInt(0);
    }

    public int incluir(T obj) throws Exception {
        arquivo.seek(0);
        int id = arquivo.readInt();
        id++;
        obj.setId(id);
        arquivo.seek(0);
        arquivo.writeInt(id);

        arquivo.seek(arquivo.length());
        arquivo.writeByte(' ');
        byte[] b = obj.getByteArray();
        arquivo.writeInt(b.length);
        arquivo.write(b);

        return id;
    }

    public Object[] listar() throws Exception {
        ArrayList<T> lista = new ArrayList<>();
        arquivo.seek(4);
        byte lapide;
        byte[] b;
        int s;
        T obj;
        while(arquivo.getFilePointer() < arquivo.length()) {
            obj = construtor.newInstance();
            lapide = arquivo.readByte();
            s = arquivo.readInt();
            b = new byte[s];
            arquivo.read(b);
            obj.setByteArray(b);
            if(lapide==' ')
                lista.add(obj);
        }

        Object[] ls = lista.toArray();
        return ls;
    }

    public Entidade buscar(int id) throws Exception {

        T obj = construtor.newInstance();
        byte lapide;
        byte[] b;
        int s;

        arquivo.seek(4);
        while(arquivo.getFilePointer() < arquivo.length()) {
            lapide = arquivo.readByte();
            s = arquivo.readInt();
            b = new byte[s];
            arquivo.read(b);
            obj.setByteArray(b);
            if(lapide == ' ' && obj.getId()==id)
                return obj;
        }
        return null;
    }


    public boolean excluir(int id) throws Exception {

        T obj = construtor.newInstance();
        byte lapide;
        byte[] b;
        int s;
        long endereco;

        arquivo.seek(4);
        while(arquivo.getFilePointer() < arquivo.length()) {
            endereco = arquivo.getFilePointer();
            lapide = arquivo.readByte();
            s = arquivo.readInt();
            b = new byte[s];
            arquivo.read(b);
            obj.setByteArray(b);
            if(lapide == ' ' && obj.getId()==id) {
                arquivo.seek(endereco);
                arquivo.write('*');
                return true;
            }
        }
        return false;
    }

    public boolean alterar(T novoObj) throws Exception {

        T obj = construtor.newInstance();
        byte lapide;
        byte[] b;
        int s;
        long endereco;
        int id = novoObj.getId();

        arquivo.seek(4);
        while(arquivo.getFilePointer() < arquivo.length()) {
            endereco = arquivo.getFilePointer();
            lapide = arquivo.readByte();
            s = arquivo.readInt();
            b = new byte[s];
            arquivo.read(b);
            obj.setByteArray(b);
            if(lapide == ' ' && obj.getId()==id) {
                arquivo.seek(endereco);
                arquivo.write('*');

                arquivo.seek(arquivo.length());
                arquivo.writeByte(' ');
                b = novoObj.getByteArray();
                arquivo.writeInt(b.length);
                arquivo.write(b);

                return true;
            }
        }
        return false;

    }

}


