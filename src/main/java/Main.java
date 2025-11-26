import Services.*;
import model.*;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static ClienteDAO clienteDAO = new ClienteDAO();
    private static AnimalDAO animalDAO = new AnimalDAO();
    private static ConsultaDAO consultaDAO = new ConsultaDAO();
    private static ServicoDAO servicoDAO = new ServicoDAO();
    private static Veterinario vetPadrao = new Veterinario("Dr. Carlos", "123123", "Clinico", "CRMV-1234");

    public static void main(String[] args) {
        int opcaoPrincipal = -1;

        do {
            System.out.println("======= MiAu Pet - PAINEL ADM =======");
            System.out.println("1 - Administrar Serviços"); // Lista os servicos, consegue adicionar mais e editar os que existem ( valor e preco )
            System.out.println("2 - Administrar Clientes e Animais");
            System.out.println("0 - Sair do Sistema");

            opcaoPrincipal = lerInteiro("Escolha uma opção: ");

            switch (opcaoPrincipal) {
                case 1:
//                    administrarServicos();
                    break;
                case 2:
                    administrarClientes();
                    break;
                case 0:
                    System.out.println("A sair do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcaoPrincipal != 0);

        scanner.close();
    }

    private static void exibirMenuCliente(int idClienteAtual) {
        System.out.println("------- Cliente ID " + idClienteAtual + " ------");
        System.out.println("1 - Listar e Administrar Animais");
        System.out.println("2 - Editar Cliente"); // Selecionar o CLIENTE PELO ID -> Editar ( nome, telefone, email, cpf ) ou deletar
        System.out.println("0 - Voltar");
    }

    private static void administrarClientes() {
        int idClienteAtual = selecionarCliente();

        if (idClienteAtual == -1) {
            return;
        }

        int opcao;
        do {
            exibirMenuCliente(idClienteAtual);
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    listarAnimais_SelecionarAnimal(idClienteAtual);
                    break;
                case 2:
                    System.out.println("(Editar o cliente)");
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private static int selecionarCliente() {
        System.out.println("--------- Lista de Clientes ---------");
        List<Cliente> clientes = clienteDAO.mostrarTodosClientes();

        if (clientes == null || clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado no banco.");
            return -1;
        }

        for (Cliente c : clientes) {
            System.out.println("ID: " + c.getId() + " | Nome: " + c.getNome());
        }

        int id = lerInteiro("Digite o ID do cliente (ou 0 para cancelar): ");
        if (id == 0) {
            return -1;
        }

        return id;
    }

    private static void listarAnimais_SelecionarAnimal(int idCliente) {
        System.out.println("--------- Animais do Cliente ID " + idCliente + " ---------");

        List<Animal> animais = animalDAO.buscarAnimaisPorIdCliente(idCliente);

        if (animais == null || animais.isEmpty()) {
            System.out.println("Nenhum animal encontrado.");
            return;
        }

        for (Animal animal : animais) {
            System.out.println("ID: " + animal.getId() + " - " + animal.getNome() + " (" + animal.getEspecie() + ")");
        }

        int idAnimalEscolhido = lerInteiro("Digite o ID do animal para administrar (ou 0 para voltar): ");

        if (idAnimalEscolhido == 0) return;

        Animal animalSelecionado = null;
        for (Animal a : animais) {
            if (a.getId() == idAnimalEscolhido) {
                animalSelecionado = a;
                break;
            }
        }

        if (animalSelecionado != null) {
            menuAnimalEscolhido(animalSelecionado);
        } else {
            System.out.println("Animal não encontrado.");
        }
    }

    private static void menuAnimalEscolhido(Animal animalSelecionado) {
        int opcaoMenuAnimal;

        do {
            System.out.println("------- Animal: " + animalSelecionado.getNome() + " -------");
            System.out.println("1 - Adminstrar consultas "); // Mostrar as consultas pelo id do Animal -> editar consulta ( de acorodo como  id escolhido. Add mais servicos, editar motivo e comentarios)
            System.out.println("2 - Editar animal"); // Editar animal pelo ID  ( editar nome, especie, raca )
            System.out.println("0 - Voltar");

            opcaoMenuAnimal = lerInteiro("Escolha uma opção: ");

            switch (opcaoMenuAnimal) {
                case 1:
                    // listarConsultas(animalSelecionado);
                    System.out.println("Listando consultas... (Implementar)");
                    break;
                case 2:
                    System.out.println("Editando animal... (Implementar)");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcaoMenuAnimal != 0);
    }

    private static void listarServicosDeUmaConsulta() {
        System.out.println("--------- Serviços de uma Consulta ---------");
        int idConsulta = lerInteiro("Digite o ID da consulta: ");

        List<Servico> servicos = consultaDAO.buscarServicosDaConsulta(idConsulta);

        if (servicos == null || servicos.isEmpty()) {
            System.out.println("Nenhum serviço encontrado para a consulta ID " + idConsulta);
            return;
        }

        double total = 0.0;
        System.out.println("Serviços realizados:");
        for (Servico s : servicos) {
            System.out.println(" - " + s.getNomeServico() + " | R$ " + s.getPreco());
            total += s.getPreco();
        }
        System.out.println("Total: R$ " + total);
    }

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