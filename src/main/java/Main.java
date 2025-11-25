import Services.*;
import model.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ClienteDAO clienteDAO = new ClienteDAO();
    private static AnimalDAO animalDAO = new AnimalDAO();
    private static ServicoDAO servicoDAO = new ServicoDAO();
    private static Veterinario vetPadrao = new Veterinario("Dr. Carlos", "123123", "Clinico", "CRMV-1234");

    public static void main(String[] args) {
        int opcao = -1;
        do {
            exibirMenuPrincipal();
            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    realizarLogin();
                    break;
                case 2:
                    realizarCadastro();
                    break;
                case 0:
                    System.out.println("Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("--------- Bem vindo ao Miau Pet ---------");
        System.out.println("1 - Já sou cliente (Login)");
        System.out.println("2 - Quero me cadastrar");
        System.out.println("0 - Sair");
        System.out.print("Escolha -> ");
    }

    private static void realizarCadastro() {
        System.out.println("--------- Cadastro de Cliente ---------");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone (apenas números): ");
        int telefone = lerInteiro();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Cliente novoCliente = new Cliente(nome, cpf, telefone, email);
        int idConfirmado = clienteDAO.inserirCliente(novoCliente);

        if( idConfirmado != -1) {
            System.out.println( novoCliente.getNome() + " cadastrado com sucesso!" );
        } else {
            System.out.println("Erro ao cadastrar.");
        }
    }

    private static void realizarLogin() {
        System.out.println("--------- Entrar ---------");
        System.out.print("Insira seu CPF: ");
        String cpf = scanner.nextLine();

        Cliente cliente = clienteDAO.buscarClientePorCpf(cpf);

        if (cliente != null) {
            System.out.println("Bem-vindo(a), " + cliente.getNome() + "!");
            menuLogado(cliente);
        } else {
            System.out.println("Cliente, com o CPF: " + cpf + " não encontrado.");
        }
    }

    private static void menuLogado(Cliente cliente) {
        int opcao = -1;
        do {
            System.out.println("--------- Menu do Cliente ---------");
            System.out.println("1 - Marcar Nova Consulta");
            System.out.println("2 - Mostrar Consultas");
            System.out.println("0 - Deslogar");
            System.out.print("Escolha: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    marcarConsulta(cliente);
                    break;
                case 2:
                    mostrarConsulta(cliente);
                    break;
                case 0:
                    System.out.println("Deslogando...");
                    break;
                default:
                    System.out.println("Voltar para a tela de Cadastro/Login");
            }
        } while (opcao != 0);
    }

    private static void marcarConsulta(Cliente cliente) {
        int opcao = -1;
        do {
            System.out.println("--------- Marcar Consulta ---------");
            System.out.println("1 - Cadastrar um animal + consulta ");
            System.out.println("2 - Criar uma consulta para um animal ja cadastrado");
            System.out.println("0 - Deslogar");
            System.out.print("Escolha: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    criarAnimal(cliente);
                    break;
                case 2:
                    mostrarAnimal(cliente);
                    break;
                case 0:
                    System.out.println("Voltar para o menu!");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void criarAnimal( Cliente cliente ) {
        System.out.println("--------- Criacao de um Animal ---------");
        System.out.print("Nome do animal: ");
        String nomeAnimal = scanner.nextLine();
        System.out.print("Espécie (Cachorro,Gato...) ");
        String especie = scanner.nextLine();
        System.out.print("Raça: ");
        String raca = scanner.nextLine();

        Animal animal = new Animal(nomeAnimal, especie, raca, cliente);
        cliente.adicionarAnimal(animal);
        animalDAO.inserirAnimal(animal);
        System.out.println(animal.getNome() + " cadastrado com sucesso!");
        criarConsulta(cliente, animal);
    }

    private static void mostrarAnimal ( Cliente cliente ) {

        List<Animal> animaisEncontradosBanco = animalDAO.buscarAnimalDono(cliente);

        for (Animal animal : animaisEncontradosBanco) {
            cliente.adicionarAnimal(animal);
        }

        if (cliente.getAnimais().isEmpty()) {
            System.out.println( cliente.getNome() + " voce nao poussui nenhum animal cadastrado!" );
        } else {
            System.out.println("--- Seus Animais ---");
            for (int i = 0; i < cliente.getAnimais().size(); i++) {
                Animal a = cliente.getAnimais().get(i);
                System.out.println((i + 1) + ". " + a.getNome() + " (" + a.getEspecie() + ")");
            }
        }
    }

    public static void criarConsulta( Cliente cliente, Animal animal ) {
        System.out.print("--------- Criar uma consulta ---------");
        System.out.print("Motivo da consulta: ");
        String motivo = scanner.nextLine();
        System.out.print("Alguma observacao? ( ex: alergia a alguma medicamento");
        String comentario = scanner.nextLine();
        Consulta consulta = new Consulta(motivo, LocalDateTime.now(), comentario, cliente, vetPadrao);
    }

    private static void mostrarConsulta(Cliente cliente) {

    }

    private static int lerInteiro() {
        try {
            int num = scanner.nextInt();
            scanner.nextLine();
            return num;
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }

}