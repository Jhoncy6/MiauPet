package model;

import java.util.ArrayList;
import java.util.List;


public class Cliente extends Pessoa {

    private int telefone;
    private String email;

    private List<Animal> animais = new ArrayList<>();

    public Cliente(String nome, int id, String cpf, int telefone, String email) {
        super(nome, cpf);
        this.telefone = telefone;
        this.email = email;
    }

    public Animal adicionarAnimal(Animal animal) {
        animais.add(animal);
        return animal;
    }

    public String getCpf() {
        return super.getCpf(); // ou return this.cpf;
    }
}
