package project.model;

import project.database.Entidade;
import java.io.*;

public class Ferramenta  extends Material implements Entidade  {
    private String marca;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }


    public String toString() {
        return "Nome: " + getNome()  + " Quantidade disponível: "+ Ferramenta.super.getQuantDisp() +"\n"; }


    public Ferramenta(int id,String nome, int quant, String marca){
        super();
        setId(id);
        setNome(nome);
        setQuantTotal(quant);
        setQuantDisp(quant);
        this.marca = marca;
        if (quant > 0)
            setDisponivel(true);
        else if (quant < 0 ){
            setDisponivel(false);
            setQuantTotal(0);
            setQuantDisp(0);
        }
        else
            setDisponivel(false);

    }

    public Ferramenta(){}

    @Override
    public byte[] getByteArray() throws IOException {
        ByteArrayOutputStream dados = new ByteArrayOutputStream();
        DataOutputStream saida = new DataOutputStream( dados );
        saida.writeInt(super.getId());
        saida.writeUTF(super.getNome());
        saida.writeBoolean(super.isDisponivel());
        saida.writeInt(super.getQuantTotal());
        saida.writeInt(super.getQuantDisp());
        saida.writeUTF(this.getMarca());
        return dados.toByteArray();
    }

    @Override
    public void setByteArray(byte[] b) throws IOException, EOFException {
        ByteArrayInputStream dados = new ByteArrayInputStream(b);
        DataInputStream entrada = new DataInputStream(dados);

        super.setId(entrada.readInt());
        super.setNome(entrada.readUTF());
        super.setDisponivel(entrada.readBoolean());
        super.setQuantTotal(entrada.readInt());
        super.setQuantDisp(entrada.readInt());
        this.marca = entrada.readUTF();
        entrada.close();
    }

    public void setId(int c) {
        super.setId(c);
    }

    public int getId() {
        return super.getId();
    }
}
