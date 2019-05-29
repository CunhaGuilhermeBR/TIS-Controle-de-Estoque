package project.model;

import project.database.Entidade;

import java.io.*;

public class Emprestimo implements Entidade {
    private String dataEmprestimo, dataRetorno;
    private int id;

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(String dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public String getDataRetorno() {
        return dataRetorno;
    }

    public void setDataRetorno(String dataRetorno) {
        this.dataRetorno = dataRetorno;
    }


    public String getFerramenta() {
        return ferramenta;
    }

    public void setFerramenta(String ferramenta) {
        this.ferramenta = ferramenta;
    }

    private String ferramenta;

    public String getTrab() {
        return trab;
    }

    public void setTrab(String trab) {
        this.trab = trab;
    }

    private String trab;

    public int getQuantFer() {
        return quantFer;
    }

    public void setQuantFer(int quantFer) {
        this.quantFer = quantFer;
    }

    private int quantFer;

    public boolean isEmAndamento() {
        return emAndamento;
    }

    public void setEmAndamento(boolean emAndamento) {
        this.emAndamento = emAndamento;
    }

    private boolean emAndamento;

    public Emprestimo(int id,String de,  String q, int quant, String trab){
        this.id = id;
        this.dataEmprestimo = de;
        this.dataRetorno = "Indisponível";
        this.ferramenta = q;
        this.quantFer = quant;
        this.trab = trab;
        this.emAndamento = true;
    }

    public Emprestimo(){}

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
        saida.writeUTF(this.ferramenta);
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
        this.ferramenta = entrada.readUTF();
        this.quantFer = entrada.readInt();
        this.trab = entrada.readUTF();
        this.emAndamento = entrada.readBoolean();

        entrada.close();
    }
}
