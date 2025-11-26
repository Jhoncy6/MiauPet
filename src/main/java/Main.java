import Services.*;
import model.*;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static ClienteDAO clienteDAO = new ClienteDAO();
    private static AnimalDAO animalDAO = new AnimalDAO();
    private static ConsultaDAO consultaDAO = new ConsultaDAO();
    private static ServicoDAO servicoDAO = new ServicoDAO(); // reservado se quiser usar depois
    private static Veterinario vetPadrao = new Veterinario("Dr. Carlos", "123123", "Clinico", "CRMV-1234");

    public static void main(String[] args) {

        System.out.println("------- MiAu Pet - Painel ADM ------");

        // 1) escolher cliente com o qual vamos mexer
        int idClienteAtual = selecionarCliente();

        if (idClienteAtual == -1) {
            System.out.println("Encerrando, nenhum cliente selecionado.");
            return;
        }

        int opcao;
        do {
            exibirMenuCliente(idClienteAtual);
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    listarAnimaisDeUmCliente(idClienteAtual);
                    break;
                case 2:
                    listarServicosDeUmaConsulta();
                    break;
                case 3:
                    // trocar de cliente
                    idClienteAtual = selecionarCliente();
                    if (idClienteAtual == -1) {
                        System.out.println("Nenhum cliente selecionado. Encerrando.");
                        opcao = 0;
                    }
                    break;
                case 0:
                    System.out.println("Saindo do sistema... Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    // -------- ESCOLHER CLIENTE --------

    private static int selecionarCliente() {
        System.out.println("\n--------- Lista de Clientes ---------");
        List<Cliente> clientes = clienteDAO.mostrarTodosClientes();

        if (clientes == null || clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado no banco.");
            return -1;
        }

        for (Cliente c : clientes) {
            System.out.println("ID: " + c.getId() + " | Nome: " + c.getNome());
        }

        int id = lerInteiro("Digite o ID do cliente que deseja gerenciar (ou 0 para sair): ");
        if (id == 0) {
            return -1;
        }

        // aqui poderia validar se o ID realmente existe na lista, se quiser
        return id;
    }

    // -------- MENU DO CLIENTE ESCOLHIDO --------

    private static void exibirMenuCliente(int idClienteAtual) {
        System.out.println("\n------- MiAu Pet - Cliente atual: ID " + idClienteAtual + " ------");
        System.out.println("1 - Listar animais deste cliente");
        System.out.println("2 - Listar serviços de uma consulta");
        System.out.println("3 - Trocar de cliente");
        System.out.println("0 - Sair");
    }

    // 1) Listar animais de um cliente (buscarAnimaisPorIdCliente)
    private static void listarAnimaisDeUmCliente(int idCliente) {
        System.out.println("\n--------- Animais do Cliente ID " + idCliente + " ---------");

        List<Animal> animais = animalDAO.buscarAnimaisPorIdCliente(idCliente);

        if (animais == null || animais.isEmpty()) {
            System.out.println("Nenhum animal encontrado para o cliente com ID " + idCliente + ".");
            return;
        }

        for (Animal animal : animais) {
            System.out.println(" - " + animal.getNome() +
                    " (" + animal.getEspecie() + " - " + animal.getRaca() + ")");
        }
    }

    // 2) Perguntar um idConsulta e mostrar serviços + preço (buscarServicosDaConsulta)
    private static void listarServicosDeUmaConsulta() {
        System.out.println("\n--------- Serviços de uma Consulta ---------");

        int idConsulta = lerInteiro("Digite o ID da consulta: ");

        List<Servico> servicos = consultaDAO.buscarServicosDaConsulta(idConsulta);

        if (servicos == null || servicos.isEmpty()) {
            System.out.println("Nenhum serviço encontrado para a consulta ID " + idConsulta + ".");
            return;
        }

        double total = 0.0;
        System.out.println("Serviços da consulta ID " + idConsulta + ":");
        for (Servico s : servicos) {
            System.out.println(" - " + s.getNomeServico() + " | Preço: R$ " + s.getPreco());
            total += s.getPreco();
        }
        System.out.println("Total dos serviços: R$ " + total);
    }

    // Utilitário para ler int com mensagem
    private static int lerInteiro(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                String linha = scanner.nextLine();
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido, digite um número inteiro.");
            }
        }
    }
}
