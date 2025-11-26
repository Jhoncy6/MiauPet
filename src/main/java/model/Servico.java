package model;

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

    public int getId() { return id; }

    public String getNomeServico() {
        return nomeServico;
    }

    public double getPreco() {
        return preco;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
