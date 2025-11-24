import Services.*;
import model.Cliente;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Cliente novo = new Cliente("joao", "1231", 2222, "24111");
            ClienteDAO cliente= new ClienteDAO();
                cliente.inserirCliente(novo);


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