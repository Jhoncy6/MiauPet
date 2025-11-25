package model;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {
    private int telefone;
    private String email;

    private List<Animal> animais;

    public Cliente(String nome, String cpf, int telefone, String email) {
        super(nome, cpf);
        this.telefone = telefone;
        this.email = email;
    }

    public void adicionarAnimal(Animal animal) {
        this.animais.add(animal);
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public int getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }
}
