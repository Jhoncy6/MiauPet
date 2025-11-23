public class Servico {

    private int id;
    private String nomeServico;
    private double preco;

    public Servico(String nomeServico, double preco) {
        this.nomeServico = nomeServico;
        this.preco = preco;
    }

    public Servico(int id, String nomeServico, double preco) {
        this.id = id;
        this.nomeServico = nomeServico;
        this.preco = preco;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
