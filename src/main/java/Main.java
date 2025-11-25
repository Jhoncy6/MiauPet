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

        // listar clientes
        int idx = 1;
        for (Cliente cliente : clienteDAO.mostrarTodosClientes()) {
            System.out.println(idx + "- Cliente: " + cliente.getNome());
            idx++;
        }

        // pegar animais do cliente de id 1, por exemplo
        int idCliente = 2;
        List<Animal> animais = animalDAO.buscarAnimaisPorIdCliente(idCliente);

        // AGORA: imprimir só o nome do animal
        for (Animal animal : animais) {
            System.out.println("Animal: " + animal.getNome());
        }

        int idConsulta = 1;
        for (Servico s : consultaDAO.buscarServicosDaConsulta(idConsulta)) {
            System.out.println("Serviço: " + s.getNomeServico() +
                    " | Preço: " + s.getPreco());
        }

    }
}
