package model;

public abstract class Pessoa {
    protected String nome;
    protected String cpf;
    protected int id;

    public Pessoa(String nome, String cpf ) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
