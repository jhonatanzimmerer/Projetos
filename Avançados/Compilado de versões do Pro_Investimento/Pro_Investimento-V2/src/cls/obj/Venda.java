package cls.obj;

public class Venda {

    String data;
    String ativo;
    int quantidade;
    double precoV;
    double precoM;
    String classe;
    double taxa;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoV() {
        return precoV;
    }

    public void setPrecoV(double precoV) {
        this.precoV = precoV;
    }

    public double getPrecoM() {
        return precoM;
    }

    public void setPrecoM(double precoM) {
        this.precoM = precoM;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public double getTaxa() {
        return taxa;
    }

    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }
}
