package model;

public class Veterinario extends Pessoa {
    private String especialidade;
    private String crmv;

    public Veterinario(String nome, int id,  String cpf, String especialidade, String crmv) {
        super(nome, id);
        this.especialidade = especialidade;
        this.crmv = crmv;
    }

    public void Funcional() {
        System.out.println("model.Veterinario esta funcionando");
    }
}
