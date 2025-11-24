package model;

public abstract class Pessoa {
    private String nome;
    private int id;

    public Pessoa(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
}
