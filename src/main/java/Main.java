
import Services.ServicoDAO;
import Services.VeterinarioDAO;
import model.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Cliente cliente1 = new Cliente("João", 1, "99999-9999", 1212123, "joao@x.com");
        Veterinario vet1 = new Veterinario("Dr. Carlos",  "123123", "Clinico geral", "CRMV-1234");
        Veterinario vet2 = new Veterinario("Dra. Carla", "123548", "Clinico", "CRMV-56342");

        VeterinarioDAO vetDAO = new VeterinarioDAO();
        vetDAO.inserirVeterinario(vet1);

        Animal rex = new Animal("Rex", "Cachorro", "Buldog", cliente1);
        cliente1.adicionarAnimal(rex);

        Consulta consulta1 = new Consulta(
                1,
                "Doente",
                LocalDateTime.of(2025, 11, 30, 15, 0),
                "Filhote, foi pego na rua recentemente, pode ter alguma alergia",
                cliente1,
                vet1
        );

        Servico s1 = new Servico(5, "Raio-X geral", 50.0);
        Servico s2 = new Servico(4, "Tosa completa", 30.0);
        Servico s3 = new Servico(2, "Vacinação", 60.0);
        Servico s4 = new Servico(3, "Banho", 40.0);
        Servico s5 = new Servico(6, "Exame de sangue", 120.0);
        Servico s6 = new Servico(8, "Limpeza de ouvido", 25.0);
        Servico s7 = new Servico(7, "Ultrassom abdominal", 150.0);
        Servico s8 = new Servico(9, "Corte de unhas", 18.0);
        Servico s9 = new Servico(10, "Aplicação de antipulgas", 35.0);
        Servico s10 = new Servico(1, "Consulta geral", 80.0);

        ServicoDAO dao = new ServicoDAO();
        dao.inserirServico(s1);
        dao.inserirServico(s2);
        dao.inserirServico(s3);
        dao.inserirServico(s4);
        dao.inserirServico(s5);
        dao.inserirServico(s6);
        dao.inserirServico(s7);
        dao.inserirServico(s8);
        dao.inserirServico(s9);
        dao.inserirServico(s10);


        int opcao;

        do {
            System.out.println("\nOlá, " + cliente1.getNome() + "!");
            System.out.println("O que você deseja fazer?");
            System.out.println("1 - Ver serviços disponíveis");
            System.out.println("2 - Marcar uma consulta");
            System.out.println("3 - Ver médicos disponíveis");
            System.out.println("4 - Sair");
            System.out.print("Opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:
                    dao.listarServicos();
                    break;

                case 2:
                    System.out.println("\n=== Marcar consulta ===");

                    System.out.print("Digite o nome do seu animal: ");
                    String nomeAnimal = sc.nextLine();

                    System.out.print("Espécie: ");
                    String especie = sc.nextLine();

                    System.out.print("Raça: ");
                    String raca = sc.nextLine();

                    Animal animal = new Animal(nomeAnimal, especie, raca, cliente1);
                    cliente1.adicionarAnimal(animal);

                    System.out.print("Motivo da consulta: ");
                    String motivo = sc.nextLine();

                    System.out.print("Comentários adicionais: ");
                    String comentarios = sc.nextLine();

                    LocalDateTime dataConsulta = LocalDateTime.now();

                    Consulta consulta = new Consulta(
                            2,
                            motivo,
                            dataConsulta,
                            comentarios,
                            cliente1,
                            vet1
                    );

                    consulta.adicionarServico(s10);
                    consulta.adicionarServico(s3);

                    System.out.println("\nConsulta marcada com sucesso!");
                    consulta.exibirResumo();
                    break;

                case 3:
                    System.out.println("\nMédicos disponíveis:");
                    System.out.println("- " + vet1.getNome() + " | Clínico geral");
                    System.out.println("- " + vet2.getNome() + " | Dermatologia");
                    break;

                case 4:
                    System.out.println("\nObrigado por usar o MiAu Pet! Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }


    }
//        Scanner sc = new Scanner(System.in);
//        ClienteDAO clienteDAO = new ClienteDAO();
//        AnimalDAO animalDAO = new AnimalDAO();
//        ConsultaDAO consultaDAO = new ConsultaDAO();
//
//        Cliente clienteLogado = null;
//        boolean logado = false;
//
//        System.out.println("Bem-vindo ao PetShop!");
//
//        while (!logado) {
//            System.out.println("\n1 - Cadastro\n2 - Login");
//            System.out.print("Opção: ");
//            int op = sc.nextInt();
//            sc.nextLine();
//
//            if (op == 1) {
//                System.out.print("Nome: ");
//                String nome = sc.nextLine();
//                System.out.print("CPF: ");
//                String cpf = sc.nextLine();
//                System.out.print("Telefone: ");
//                String tel = sc.nextLine();
//                System.out.print("Email: ");
//                String email = sc.nextLine();
//
//                Cliente novo = new Cliente("joao", "1231", 2222, "24111");
//                ClienteDAO cliente= new ClienteDAO();
//                cliente.inserirCliente(novo);
//
//                if (idCli != -1) {
//                    System.out.println("Cadastro de animal obrigatório.");
//                    System.out.print("Nome Animal: ");
//                    String nomeAni = sc.nextLine();
//                    System.out.print("Espécie: ");
//                    String esp = sc.nextLine();
//                    System.out.print("Raça: ");
//                    String raca = sc.nextLine();
//
//                    animalDAO.inserirAnimal(nomeAni, esp, raca, idCli);
//                    System.out.println("Cadastro concluído! Faça login.");
//                } else {
//                    System.out.println("Erro ao cadastrar cliente.");
//                }
//
//            } else if (op == 2) {
//                System.out.print("Digite seu CPF: ");
//                String cpf = sc.nextLine();
//                clienteLogado = clienteDAO.buscarPorCpf(cpf);
//
//                if (clienteLogado != null) {
//                    logado = true;
//                    System.out.println("Login realizado com sucesso! Olá " + clienteLogado.getNome());
//                } else {
//                    System.out.println("CPF não encontrado. Tente novamente ou cadastre-se.");
//                }
//            }
//        }
//
//        int menu = -1;
//        while (menu != 4) {
//            System.out.println("\n--- MENU ---");
//            System.out.println("1 - Marcar consulta");
//            System.out.println("2 - Verificar consultas");
//            System.out.println("3 - Adicionar animal");
//            System.out.println("0 - Sair");
//            System.out.print("Escolha: ");
//            menu = sc.nextInt();
//            sc.nextLine();
//
//            switch (menu) {
//                case 1:
//                    System.out.print("Nome do animal para consulta: ");
//                    String nomeAni = sc.nextLine();
//                    int idAni = animalDAO.buscarIdPorNome(nomeAni, clienteLogado.getId());
//
//                    if (idAni != -1) {
//                        System.out.print("Motivo: ");
//                        String motivo = sc.nextLine();
//                        consultaDAO.agendarConsulta(LocalDateTime.now().plusDays(1), motivo, idAni, 1);
//                        System.out.println("Consulta agendada!");
//                    } else {
//                        System.out.println("Animal não encontrado.");
//                    }
//                    break;
//                case 2:
//                    consultaDAO.listarConsultasPorCliente(clienteLogado.getId());
//                    break;
//                case 3:
//                    System.out.print("Nome: ");
//                    String n = sc.nextLine();
//                    System.out.print("Espécie: ");
//                    String e = sc.nextLine();
//                    System.out.print("Raça: ");
//                    String r = sc.nextLine();
//                    animalDAO.inserirAnimal(n, e, r, clienteLogado.getId());
//                    System.out.println("Animal adicionado.");
//                    break;
//                case 0:
//                    System.out.println("Saindo...");
//                    break;
//                default:
//                    System.out.println("Opção inválida.");
//            }
//        }
//        sc.close();
//    }
}