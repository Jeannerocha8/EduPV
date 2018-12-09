package tcc.studio.com.edupv;

public class Questao {

    private int id;
    private String enunciado;
    private String opc1;
    private String opc2;
    private String opc3;
    private String opc4;
    private String resposta;


    public Questao() {
        id = 0;
        enunciado = "";
        opc1 ="";
        opc2 ="";
        opc3 ="";
        opc4 ="";
        resposta="";
    }

    public Questao(String enunciado, String opc1, String opc2, String opc3, String opc4, String resposta) {
        this.enunciado = enunciado;
        this.opc1 = opc1;
        this.opc2 = opc2;
        this.opc3 = opc3;
        this.opc4 = opc4;
        this.resposta = resposta;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getOpc1() {
        return opc1;
    }

    public void setOpc1(String opc1) {
        this.opc1 = opc1;
    }

    public String getOpc2() {
        return opc2;
    }

    public void setOpc2(String opc2) {
        this.opc2 = opc2;
    }

    public String getOpc3() {
        return opc3;
    }

    public void setOpc3(String opc3) {
        this.opc3 = opc3;
    }

    public String getOpc4() {
        return opc4;
    }

    public void setOpc4(String opc4) {
        this.opc4 = opc4;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
}
