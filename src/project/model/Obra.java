package project.model;

import project.database.Entidade;
import java.io.*;
import java.util.ArrayList;

public class Obra implements Entidade {
    public Obra(String nenhuma, int id) {
        this.nome= "Nenhuma";
        this.id = id;
        this.trabsAtuais = "Ninguém";
        this.emAndamento= false;
    }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getTrabsAtuais() {
        return trabsAtuais;
    }

    public void setTrabsAtuais(String trabsAtuais) {
        this.trabsAtuais = trabsAtuais;
    }

    private String nome, trabsAtuais;
    private ArrayList trabs;
    private int id;

    public boolean isEmAndamento() { return emAndamento; }

    public void setEmAndamento(boolean emAndamento) { this.emAndamento = emAndamento; }

    private boolean emAndamento;

    public Obra(int id, String nome, String trab){
        this.id = id;
        this.nome = nome;
        this.trabsAtuais = trab;
        this.emAndamento= true;
    }
    public Obra(){}

    public String toString() {
        return "Nome: " + this.nome + "\n";
    }

    private void addTrab(Pessoa p){
        trabs.add(p.nome);
    }

    private void addTrab(String p){
        trabs.add(p);
    }

    public byte[] getByteArray() throws IOException {
        ByteArrayOutputStream dados = new ByteArrayOutputStream();
        DataOutputStream saida = new DataOutputStream( dados );
        saida.writeInt(this.id);
        saida.writeUTF(this.nome);
        saida.writeUTF(this.trabsAtuais);
        saida.writeBoolean(this.emAndamento);

        return dados.toByteArray();
    }

    public void setByteArray(byte[] b) throws IOException, EOFException {
        ByteArrayInputStream dados = new ByteArrayInputStream(b);
        DataInputStream entrada = new DataInputStream(dados);
        this.id = entrada.readInt();
        this.nome = entrada.readUTF();
        this.trabsAtuais = entrada.readUTF();
        this.emAndamento = entrada.readBoolean();
        entrada.close();
    }

    public void setId(int c) {
        this.id = c;
    }

    public int getId() {
        return this.id;
    }
}
