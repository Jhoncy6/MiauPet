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
            System.out.println("1 - Administrar Serviços");
            System.out.println("2 - Administrar Clientes e Animais");
            System.out.println("0 - Sair do Sistema");

            opcaoPrincipal = lerInteiro("Escolha uma opção: ");

            switch (opcaoPrincipal) {
                case 1:
                    administrarServicos();
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

    private static void administrarServicos() {
        int opcao;
        int idServico;

        do {
            System.out.println("------- Admin de Serviços ------");
            System.out.println("1 - Listar todos os serviços");
            System.out.println("2 - Adicionar um novo serviço");
            System.out.println("0 - Voltar");
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    System.out.println("--------- Lista de Serviços ---------");
                    servicoDAO.listarServicos();
                    System.out.println("Escolha o ID do serviço que deseja editar: ");
                    idServico = lerInteiro("Escolha uma opção: ");
                    if (idServico != 0) {
                        administrarServicoEscolhido(idServico);
                    }
                    break;
                case 2:
                    adicionarNovoServico();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void administrarServicoEscolhido(int idServico) {
        Servico servico = servicoDAO.buscarServicoPorId(idServico);

        int opcao;
        do {
            System.out.println("--------- Administrar Serviço ---------");
            System.out.println("ID: " + servico.getId());
            System.out.println("Nome atual: " + servico.getNomeServico());
            System.out.println("Preço atual: R$ " + servico.getPreco());
            System.out.println("1 - Editar apenas o nome");
            System.out.println("2 - Editar apenas o preço");
            System.out.println("3 - Remover serviço");
            System.out.println("0 - Voltar");
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();
                    servico.setNomeServico(novoNome);
                    if (servicoDAO.atualizarServico(servico)) {
                        System.out.println("Nome atualizado com sucesso!");
                    } else {
                        System.out.println("Erro ao atualizar nome.");
                    }
                    break;

                case 2:
                    double novoPreco = lerDouble("Novo preço: ");
                    servico.setPreco(novoPreco);
                    if (servicoDAO.atualizarServico(servico)) {
                        System.out.println("Preço atualizado com sucesso!");
                    } else {
                        System.out.println("Erro ao atualizar preço.");
                    }
                    break;
                case 3:
                    System.out.println("!!! PERIGO !!!");
                    System.out.println("Você está prestes a remover: " + servico.getNomeServico());
                    System.out.println("Tem certeza? (Digite 1 para CONFIRMAR ou 0 para CANCELAR)");
                    int confirmacao = lerInteiro("Sua escolha: ");
                    if (confirmacao == 1) {
                        boolean sucesso = servicoDAO.removerServicoPorId(idServico);
                        if (sucesso) {
                            System.out.println("Serviço removido com sucesso");
                            administrarServicos();
                        } else {
                            System.out.println(" Erro ao remover serviço.");
                        }
                    } else {
                        System.out.println("Operação cancelada.");
                    }
                    break;
                case 0:
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private static void adicionarNovoServico() {
        System.out.println("--------- Adicionar novo serviço ---------");

        System.out.print("Nome do serviço: ");
        String nome = scanner.nextLine();

        double preco = lerDouble("Preço do serviço: ");

        Servico novo = new Servico(nome, preco);

        if (servicoDAO.inserirServico(novo)) {
            System.out.println("Serviço \"" + nome + "\" cadastrado com sucesso por R$ " + preco + "!");
        } else {
            System.out.println("Erro ao cadastrar o serviço.");
        }
    }

    private static void exibirMenuCliente(int idClienteAtual) {
        System.out.println("------- Cliente ID " + idClienteAtual + " ------");
        System.out.println("1 - Listar e Administrar Animais");
        System.out.println("2 - Editar Cliente");
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
                    menuEditarCliente(idClienteAtual);
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private static void menuEditarCliente(int idCliente) {
        Cliente cliente = clienteDAO.buscarClientePorId(idCliente);

        if (cliente == null) {
            System.out.println("Erro: Cliente não encontrado.");
            return;
        }

        int opcao = -1;
        do {
            System.out.println("--- Editando Cliente: " + cliente.getNome() + " ---");
            System.out.println("1 - Alterar Nome (" + cliente.getNome() + ")");
            System.out.println("2 - Alterar CPF (" + cliente.getCpf() + ")");
            System.out.println("3 - Alterar Telefone (" + cliente.getTelefone() + ")");
            System.out.println("4 - Alterar Email (" + cliente.getEmail() + ")");
            System.out.println("5 - Remover Usuario e suas dependencias");
            System.out.println("9 - SALVAR ALTERAÇÕES");
            System.out.println("0 - Cancelar/Voltar");

            opcao = lerInteiro("Escolha o dado para editar: ");

            switch (opcao) {
                case 1:
                    System.out.print("Novo Nome: ");
                    String novoNome = scanner.nextLine();
                    cliente.setNome(novoNome);
                    System.out.println("Nome alterado temporariamente.");
                    break;
                case 2:
                    System.out.print("Novo CPF: ");
                    cliente.setCpf(scanner.nextLine());
                    break;
                case 3:
                    int novoTel = lerInteiro("Novo Telefone: ");
                    cliente.setTelefone(novoTel);
                    break;
                case 4:
                    System.out.print("Novo Email: ");
                    cliente.setEmail(scanner.nextLine());
                    break;
                case 5:
                    System.out.println("!!! PERIGO !!!");
                    System.out.println("Você está prestes a remover: " + cliente.getNome());
                    System.out.println("Ao fazer isso, TODOS os animais e consultas deste cliente serão apagadas.");
                    System.out.println("Tem certeza? (Digite 1 para CONFIRMAR ou 0 para CANCELAR)");
                    int confirmacao = lerInteiro("Sua escolha: ");
                    if (confirmacao == 1) {
                        boolean sucesso = clienteDAO.removerClientePorId(idCliente);
                        if (sucesso) {
                            System.out.println("Cliente removido com sucesso");
                            administrarClientes();
                        } else {
                            System.out.println(" Erro ao remover cliente.");
                        }
                    } else {
                        System.out.println("Operação cancelada.");
                    }
                    break;
                case 9:
                    boolean sucesso = clienteDAO.atualizarCliente(cliente);
                    if (sucesso) {
                        System.out.println("Cliente atualizado com sucesso");
                        opcao = 0;
                    } else {
                        System.out.println("Erro ao atualizar cliente.");
                    }
                    break;
                case 0:
                    System.out.println("Alterações descartadas.");
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
            System.out.println("2 - Editar animal");
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

    private static double lerDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                String linha = scanner.nextLine();
                return Double.parseDouble(linha);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido, digite um número (use ponto, ex: 100.50).");
            }
        }
    }

}