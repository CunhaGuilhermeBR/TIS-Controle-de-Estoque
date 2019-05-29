package project.model;

import project.database.Entidade;
import java.io.*;


public class Material implements Entidade {
    private String nome;
    private String unMedida;
    private int quantTotal;

    public int getQuantDisp() { return quantDisp; }

    public void setQuantDisp(int quantDisp) { this.quantDisp = quantDisp; }

    private int quantDisp;
    private int id;
    private boolean disponivel;

    public String getUnMedida() {
        return unMedida;
    }

    public void setUnMedida(String unMedida) {
        this.unMedida = unMedida;
    }

    public Material(int i, String nome) { }

    public Material(int id, String nome, int quantTotal, String unM) {
        this.id = id;
        this.nome = nome;
        this.quantTotal = quantTotal;
        this.quantDisp = quantTotal;
        if (quantTotal >0)
            this.disponivel = true;
        else
            this.disponivel = false;
        this.unMedida = unM;
    }

    public Material() { }

    public String toString() {
        return "Nome: " + this.nome + " Quantidade: " + this.quantTotal + " Quantidade disponível "+ quantDisp +  unMedida + "   ID: " + this.id + "\n"; }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantTotal() {
        return quantTotal;
    }

    public void setQuantTotal(int quantTotal) {
        this.quantTotal = quantTotal;
    }

    public byte[] getByteArray() throws IOException {
        ByteArrayOutputStream dados = new ByteArrayOutputStream();
        DataOutputStream saida = new DataOutputStream( dados );
        saida.writeInt(this.id);
        saida.writeUTF(this.nome);
        saida.writeBoolean(this.disponivel);
        saida.writeInt(this.quantTotal);
        saida.writeInt(this.quantDisp);
        saida.writeUTF(unMedida);
        return dados.toByteArray();
    }

    public void setByteArray(byte[] b) throws IOException, EOFException {
        ByteArrayInputStream dados = new ByteArrayInputStream(b);
        DataInputStream entrada = new DataInputStream(dados);

        this.id = entrada.readInt();
        this.nome = entrada.readUTF();
        this.disponivel = entrada.readBoolean();
        this.quantTotal = entrada.readInt();
        this.quantDisp = entrada.readInt();
        this.unMedida = entrada.readUTF();
        entrada.close();


    }
    public void setId(int c) {
        this.id = c;
    }

    public int getId() {
        return this.id;
    }


}
