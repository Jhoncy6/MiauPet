package model;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {
    private int telefone;
    private String email;

    private List<Animal> animais = new ArrayList<>();
    private List<Consulta> consultas = new ArrayList<>();

    public Cliente(String nome, String cpf, int telefone, String email) {
        super(nome, cpf);
        this.telefone = telefone;
        this.email = email;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void adicionarConsulta(Consulta consulta) {
        this.consultas.add(consulta);
    }

    public int getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }
}
