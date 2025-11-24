package model;

public class Veterinario extends Pessoa {
    private String especialidade;
    private String crmv;

    public Veterinario(String nome, String cpf , String especialidade, String crmv) {
        super(nome, cpf);
        this.especialidade = especialidade;
        this.crmv = crmv;
    }
    public String getEspecialidade() {
        return especialidade;
    }
    public String getCrmv() {
        return crmv;
    }

    public void Funcional() {
        System.out.println("model.Veterinario esta funcionando");
    }
}
