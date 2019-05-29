package project.model;

public class Secretaria extends Pessoa {


    public void setSenha(String senha) {
        this.senha = senha;
    }

    private String login;
    private String senha;
    private String pergunta;
    private String resposta;


    public Secretaria(String login, String senha, String pergunta, String answer, String name){
        super();
        this.setLogin(login);
        this.nome =name;
        this.setSenha(senha);
        this.setPergunta(pergunta);
        this.setResposta(answer);

    }
    public Secretaria(){
        super();
    }


    public String getSenha() {
        return senha;
    }


    public String getLogin() {
        return login;
    }

    public String getName() {
        return nome;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }





}
