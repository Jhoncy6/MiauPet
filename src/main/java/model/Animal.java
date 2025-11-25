package model;

public class Animal {
    private int id;
    private String nome;
    private String especie;
    private String raca;

    // Agregação de volta -> Cliente e Animal
    private Cliente dono;

    public Animal(String nome, String especie, String raca,Cliente dono) {
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.dono = dono;
    }

    public String getNome() {
        return nome;
    }

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
