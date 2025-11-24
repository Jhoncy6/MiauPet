
import Services.*;
import model.*;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServicoDAO servicoDAO = new ServicoDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        AnimalDAO animalDAO = new AnimalDAO();
        VeterinarioDAO vetDAO = new VeterinarioDAO();

        Servico s1  = new Servico("Raio-X geral", 50.0);
        Servico s2  = new Servico("Tosa completa", 30.0);
        Servico s3  = new Servico("Vacinação", 60.0);
        Servico s4  = new Servico("Banho", 40.0);
        Servico s5  = new Servico("Exame de sangue", 120.0);
        Servico s6  = new Servico("Limpeza de ouvido", 25.0);
        Servico s7  = new Servico("Ultrassom abdominal", 150.0);
        Servico s8  = new Servico("Corte de unhas", 18.0);
        Servico s9 = new Servico("Aplicação de antipulgas", 35.0);
        Servico s10  = new Servico("Consulta geral", 80.0);
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

//       servicoDAO.listarServicos();

        int opcao = -1;

        do {
            System.out.println(" --------------------------------------------------- ");
            System.out.println(" ----------- Bem vindo ao Miau Pet ----------- ");
            System.out.println(" - Para prosseguir, vamos fazer algumas perguntas :");
            System.out.println(" - Já é um usuario da nossa Loja? ( Digite 1 )");
            System.out.println(" - Não? Deseja se cadastrar ( Digite 2 )");
            System.out.println(" - Gostaria de Sair? ( Digite 0 )");
            System.out.println(" --------------------------------------------------- ");
            System.out.print(" -> Opcao Escolhida : ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    System.out.println(" ------------------------------------------------- ");
                    System.out.println(" ----------- Vamos começar o Login ----------- ");
                    System.out.println(" ----------- Para entrar, insira seu CPF : ----------- ");
                    String cpfBusca = scanner.nextLine();
                    Cliente clienteEncontrado = clienteDAO.buscarClientePorCpf(cpfBusca);
                    if (clienteEncontrado != null) {
                        System.out.println(clienteEncontrado.getNome() + " Entrou com sucesso");
                    }else {
                        System.out.println("Erro ao fazer login, CPF de usuario não encontrado");
                    }
                break;

                case 2:
                    System.out.println(" --------------------------------------------------- ");
                    System.out.println(" ----------- Vamos começar o cadastro ----------- ");
                    System.out.println(" - Digite seu nome: ");
                    String nome = scanner.nextLine();

                    System.out.println(" - Digite seu telefone: ");
                    int telefone = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println(" - Digite seu email: ");
                    String email = scanner.nextLine();

                    System.out.println(" - Digite seu cpf: ");
                    String cpf = scanner.nextLine();

                    System.out.println(" --------------------------------------------------- ");
                    Cliente cliente = new Cliente(nome, cpf, telefone, email);
                    clienteDAO.inserirCliente(cliente);
                break;

                case 0:
                    System.out.println(" ------------------------------------------------- ");
                    System.out.println(" ----------- Até logo, tchau ----------- ");
                    System.out.println(" ------------------------------------------------- ");
                    break;
                default:
                    System.out.println("Opção invaldia, digite 1 ( cadastrar ) , 2 ( entrar ) ou 0 ( sair )");
            }

        } while (opcao != 0);
        scanner.close();

//        Cliente cliente1 = new Cliente("João", "123", 123, "joao@x.com");  // CRIACAO DO CLIENTE
//        Cliente cliente2 = new Cliente("João", "2", 123, "joao@x.com");  // CRIACAO DO CLIENTE
//
//        clienteDAO.inserirCliente(cliente1); // ADD Cliente no BANCO
//        clienteDAO.inserirCliente(cliente2);
//
//
//        System.out.print("➡️ Digite o CPF para podermos continuar:");
//        String cpfBusca = scanner.nextLine();
//        Cliente clienteEncontrado = clienteDAO.buscarClientePorCpf(cpfBusca);
//
//        if (clienteEncontrado != null) {
//            System.out.println("\n✅ SUCESSO: Cliente encontrado!");
//            System.out.println("   Nome: " + clienteEncontrado.getNome());
//            System.out.println("   Telefone: " + clienteEncontrado.getTelefone());
//            System.out.println("   ID: " + clienteEncontrado.getId());
//            System.out.println("   Email: " + clienteEncontrado.getEmail());
//        } else {
//            System.out.println("\n❌ ERRO: Cliente não encontrado para o CPF digitado.");
//            System.out.println("   A função retornou 'null', conforme esperado para um CPF inexistente ou incorreto.");
//        }
//
//
//
//        Animal Rex = new Animal("Rex", "Cachorro", "buldog", cliente1);
//        animalDAO.inserirAnimal(Rex);
//
//
//        Veterinario vet1 = new Veterinario("Dr. Carlos",  "123123", "Clinico geral", "CRMV-1234");
//
//        vetDAO.inserirVeterinario(vet1);
//
//        Consulta consulta1 = new Consulta("Dor de vabeça", LocalDateTime.of(2025, 11, 30, 15, 0), "MUITO", cliente1, vet1 );
//        ConsultaDAO consultaDAO = new ConsultaDAO();
//        consultaDAO.inserirConsulta(consulta1);
//        consultaDAO.inserirServicosDaConsulta(consulta1);

        }
    }
