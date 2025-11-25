package model;

public class Animal {
    private int id;
    private String nome;
    private String especie;
    private String raca;

    private Cliente dono;

    public Animal(String nome, String especie, String raca,Cliente dono) {
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.dono = dono;
    }

    public Animal(String nome, String especie, String raca,Cliente dono, int id) {
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.dono = dono;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public int getId() { return id; }

    public String getEspecie() {
        return especie;
    }

    public String getRaca() {
        return raca;
    }

    public Cliente getDono() {
        return dono;
    }

    public void setId(int id) {
        this.id = id;
    }

}
