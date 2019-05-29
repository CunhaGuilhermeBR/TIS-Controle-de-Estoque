package project.model;

import project.database.Entidade;

import java.io.*;

public class Utilização implements Entidade {
    private  int quantFer;
    private String dataRetorno, trab;

    public int getQuantFer() {
        return quantFer;
    }

    public void setQuantFer(int quantFer) {
        this.quantFer = quantFer;
    }

    public String getDataRetorno() {
        return dataRetorno;
    }

    public void setDataRetorno(String dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    public String getTrab() {
        return trab;
    }

    public void setTrab(String trab) {
        this.trab = trab;
    }

    public Boolean getEmAndamento() {
        return emAndamento;
    }

    public void setEmAndamento(Boolean emAndamento) {
        this.emAndamento = emAndamento;
    }

    private Boolean emAndamento;
    private String dataEmprestimo;
    private int id;

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(String dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    private String material;

    public Utilização(int id,String de,  String q, int quant, String trab){
        this.id = id;
        this.dataEmprestimo = de;
        this.dataRetorno = "Indisponível";
        this.material = q;
        this.quantFer = quant;
        this.trab = trab;
        this.emAndamento = true;
    }

    public Utilização(){}

    public void setId(int c) {
        this.id = c;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public byte[] getByteArray() throws IOException {
        ByteArrayOutputStream dados = new ByteArrayOutputStream();
        DataOutputStream saida = new DataOutputStream( dados );
        saida.writeInt(this.id);
        saida.writeUTF(this.dataEmprestimo);
        saida.writeUTF(this.dataRetorno);
        saida.writeUTF(this.material);
        saida.writeInt(this.quantFer);
        saida.writeUTF(this.trab);
        saida.writeBoolean(this.emAndamento);

        return dados.toByteArray();
    }

    @Override
    public void setByteArray(byte[] b) throws IOException {
        ByteArrayInputStream dados = new ByteArrayInputStream(b);
        DataInputStream entrada = new DataInputStream(dados);

        this.id = entrada.readInt();
        this.dataEmprestimo = entrada.readUTF();
        this.dataRetorno = entrada.readUTF();
        this.material = entrada.readUTF();
        this.quantFer = entrada.readInt();
        this.trab = entrada.readUTF();
        this.emAndamento = entrada.readBoolean();

        entrada.close();
    }
}
