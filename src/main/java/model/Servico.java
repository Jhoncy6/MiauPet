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
    }

    public int getId() { return id; }

    public String getNomeServico() {
        return nomeServico;
    }

    public double getPreco() {
        return preco;
    }

    public void setId(int id) {
    }
}
