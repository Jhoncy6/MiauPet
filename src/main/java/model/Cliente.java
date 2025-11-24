package model;

import java.util.ArrayList;
import java.util.List;


public class Cliente extends Pessoa {
    private int telefone;
    private String email;
    private String cpf;

    // Composicao entre -> model.Animal e model.Cliente
    private List<Animal> animais = new ArrayList<>();

    public Cliente(String nome, int id, String cpf, int telefone, String email) {
        super(nome,id);
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
    }

    public Animal adicionarAnimal(Animal animal) {
        animais.add(animal);
        return animal;
    }
}
