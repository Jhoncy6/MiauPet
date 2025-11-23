import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("João",1,"99999-9999",1212123, "joao@x.com");
        Veterinario veterinario1 = new Veterinario("Dr. Carlos",1, "123123","Clinico geral", "CRMV-1234");

        Animal rex = new Animal("Rex", "Cachorro", "Buldog", cliente1);
        cliente1.adicionarAnimal(rex);

        Consulta consulta1 = new Consulta(1, "Doente", LocalDateTime.of(2025, 11, 30, 15, 0) ,"Filhote, foi pego na rua recentemente, pode ter alguma alergia", cliente1, veterinario1);

        Servico servico1 = new Servico("RaioX geral", 50.0);
        Servico servico2 = new Servico("Tosa", 30.0);

        CatalogoServicos catalogo = new CatalogoServicos();

        catalogo.adicionarServico(servico1);
        catalogo.adicionarServico(servico2);

        catalogo.listarServicos();

        consulta1.adicionarServico(servico1);
        consulta1.adicionarServico(servico2);
    }
}
