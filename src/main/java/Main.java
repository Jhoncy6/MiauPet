
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

        Veterinario vet1 = new Veterinario("Dr. Carlos",  "123123", "Clinico geral", "CRMV-1234");

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

        int opcao = -1;
        int opcaoLogado = -1;
        int opcaoAnimalEscolhida = -1;

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
                        do {
                            System.out.println(" ------------------------------------------------- ");
                            System.out.println(clienteEncontrado.getNome() + " Entrou com sucesso");
                            System.out.println(" Abaixo mostra as opções disponiveis dentro da nossa Loja :");
                            System.out.println(" - Gostaria de Marcar uma consulta? ( Digite 1 )");
                            System.out.println(" - Ver consultas já criadas ( Digite 2 )");
                            System.out.println(" --------------------------------------------------- ");
                            System.out.print(" -> Opcao Escolhida : ");
                            opcaoLogado = scanner.nextInt();
                            scanner.nextLine();

                            switch (opcaoLogado) {
                                case 1:
                                    System.out.println(" ------------------------------------------------- ");
                                    System.out.println(" Para começar, gosariamos de saber : ");
                                    System.out.println(" Animal já cadastrado ( Digite 1 )");
                                    System.out.println(" Cadastrar um animal (Digite 2 ) ");
                                    System.out.println(" Para sair ( digite 0 ) ");
                                    System.out.println(" -> Opcao Escolhida : ");
                                    opcaoAnimalEscolhida = scanner.nextInt();
                                    scanner.nextLine();
                                    do {
                                        switch (opcaoAnimalEscolhida) {
                                            case 1:

                                            break;

                                            case 2:
                                                System.out.println(" ------------------------------------------------- ");
                                                System.out.println(" Cadastro do Pet");
                                                System.out.println(" Digite o nome dele(a):");
                                                String nomeAnimal = scanner.nextLine();
                                                System.out.println(" Digite a especie ( Cachorro, gato ... ):");
                                                String especieAnimal = scanner.nextLine();
                                                System.out.println(" Digite a raça dele(a):");
                                                String racaAnimal = scanner.nextLine();
                                                Animal animal = new Animal(nomeAnimal, especieAnimal, racaAnimal, clienteEncontrado);
                                                System.out.println(" Perfeito, registrado com sucesso");
                                                System.out.println(" ------------------------------------------------- ");
                                                System.out.println(" Vamos criar uma consulta para o(a) " + animal.getNome());
                                                System.out.println("Diga-nos o motivo da Consulta:");
                                                String motivoDaConsultaAnimal = scanner.nextLine();
                                                System.out.println("Algum comentario sobre o animal ( Alergia...)");
                                                   String comentarioAnimal = scanner.nextLine();
                                                Consulta consulta = new Consulta(motivoDaConsultaAnimal,LocalDateTime.of(2025, 11, 30, 15, 0), comentarioAnimal, clienteEncontrado, vet1 );
                                                System.out.println("Abaixo temos uma lista de serviço");
                                                servicoDAO.listarServicos();


                                            break;

                                            case 0:
                                                System.out.println(" ------------------------------------------------- ");
                                                System.out.println(" Até logo, tchau" + clienteEncontrado.getNome());
                                                System.out.println(" ------------------------------------------------- ");
                                            break;

                                            default:
                                                System.out.println("Opção invaldia, digite 1 ( Marcar Consulta ) , 2 ( ver consultas ) ou 0 ( sair )");

                                                break;
                                        }


                                    }while (opcaoAnimalEscolhida != 0);

                                case 2:


                                case 0:
                                    System.out.println(" ------------------------------------------------- ");
                                    System.out.println(" Até logo, tchau" + clienteEncontrado.getNome());
                                    System.out.println(" ------------------------------------------------- ");
                                default:
                                    System.out.println("Opção invaldia, digite 1 ( Marcar Consulta ) , 2 ( ver consultas ) ou 0 ( sair )");

                                    break;
                            }

                        } while (opcaoLogado != 0);

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

//        Cliente cliente1 = new Cliente();
//
//
//
//
//
//        vetDAO.inserirVeterinario(vet1);
//
//
//        ConsultaDAO consultaDAO = new ConsultaDAO();
//        consultaDAO.inserirConsulta(consulta1);
//        consultaDAO.inserirServicosDaConsulta(consulta1);

        }
    }
