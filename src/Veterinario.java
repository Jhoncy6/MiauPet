public class Veterinario extends Pessoa {

    private String especialidade;
    private String crmv;

    public Veterinario() {
    }

    public Veterinario(int id, String nome, String especialidade, String crmv) {
        super(id, nome);
        this.especialidade = especialidade;
        this.crmv = crmv;
    }
}
