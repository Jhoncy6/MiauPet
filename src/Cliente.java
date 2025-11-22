public class Cliente extends Pessoa {
    private int telefone;
    private String email;
    private String cpf;

    public Cliente(String nome, int id, String cpf, int telefone, String email) {
        super(nome,id);
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
    }

}
