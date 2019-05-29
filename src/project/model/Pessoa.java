package project.model;

import project.database.Entidade;

import java.io.*;
import java.util.HashMap;

public class Pessoa implements Entidade {
    public String nome, obraAtual;
    private int id;

    public Pessoa() { }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObraAtual() {
        return obraAtual;
    }

    public void setObraAtual(String obraAtual) {
        this.obraAtual = obraAtual;
    }

    public String getFerAtuais() {
        return ferAtuais;
    }

    public void setFerAtuais(String ferAtuais) {
        this.ferAtuais = ferAtuais;
    }

    private String ferAtuais;
    private static HashMap<String, Integer> fers;

    public Pessoa(int id, String nome, String obraA, String ferAtuais) {
        this.id = id;this.nome = nome; this.obraAtual = obraA; this.ferAtuais= ferAtuais;
    }

    public Pessoa(int i, String nome, String obraA) {}

    public void addFer(String ferNova, int n){
        if ( fers.containsKey( ferNova ) ) {
            int x = fers.get(ferNova).intValue();
            fers.put(ferNova, n + x);
        }
        else{
            fers.put(ferNova, n);
        }
    }

    public String toString() {
        return "Nome: " + this.nome + "  ID: " + this.id ;
    }

    public byte[] getByteArray() throws IOException {
        ByteArrayOutputStream dados = new ByteArrayOutputStream();
        DataOutputStream saida = new DataOutputStream( dados );
        saida.writeInt(this.id);
        saida.writeUTF(this.nome);
        saida.writeUTF(this.obraAtual);
        saida.writeUTF(ferAtuais);

        return dados.toByteArray();
    }

    public void setByteArray(byte[] b) throws IOException, EOFException {
        ByteArrayInputStream dados = new ByteArrayInputStream(b);
        DataInputStream entrada = new DataInputStream(dados);

        this.id = entrada.readInt();
        this.nome = entrada.readUTF();
        this.obraAtual = entrada.readUTF();
        this.ferAtuais = entrada.readUTF();
        entrada.close();
    }
    public void setId(int c) {
        this.id = c;
    }

    public int getId() {
        return this.id;
    }
}
