
import Services.AnimalDAO;
import Services.ClienteDAO;
import Services.ServicoDAO;
import Services.VeterinarioDAO;
import model.*;

public class Main {
    public static void main(String[] args) {

        ServicoDAO servicoDAO = new ServicoDAO();

        Servico s1  = new Servico("Raio-X geral", 50.0);
        Servico s2  = new Servico(4, "Tosa completa", 30.0);
        Servico s3  = new Servico(2, "Vacinação", 60.0);
        Servico s4  = new Servico(3, "Banho", 40.0);
        Servico s5  = new Servico(6, "Exame de sangue", 120.0);
        Servico s6  = new Servico(8, "Limpeza de ouvido", 25.0);
        Servico s7  = new Servico(7, "Ultrassom abdominal", 150.0);
        Servico s8  = new Servico(9, "Corte de unhas", 18.0);
        Servico s9 = new Servico(10, "Aplicação de antipulgas", 35.0);
        Servico s10  = new Servico(1, "Consulta geral", 80.0);
        servicoDAO.inserirServico(s1);
        servicoDAO.inserirServico(s2);
        servicoDAO.inserirServico(s3);
        servicoDAO.inserirServico(s4);
        servicoDAO.inserirServico(s5);
        servicoDAO.inserirServico(s6);
        servicoDAO.inserirServico(s7);
        servicoDAO.inserirServico(s8);
        servicoDAO.inserirServico(s9);
        servicoDAO.inserirServico(s10);
        servicoDAO.listarServicos();


        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente1 = new Cliente("João", "99999-9999", 1212123, "joao@x.com");  // CRIACAO DO CLIENTE
        clienteDAO.inserirCliente(cliente1); // ADD Cliente no BANCO


        AnimalDAO animalDAO = new AnimalDAO();
        Animal Rex = new Animal("Rex", "Cachorro", "buldog", cliente1);
        animalDAO.inserirAnimal(Rex);


        Veterinario vet1 = new Veterinario("Dr. Carlos",  "123123", "Clinico geral", "CRMV-1234"); // CRIACAO DO VET
        VeterinarioDAO vetDAO = new VeterinarioDAO();
        vetDAO.inserirVeterinario(vet1); // ADD VET no BANCO

       vetDAO.listarVeterinarios(); // JA ESTA PRINTANDO TODOS SO VET

        }
    }