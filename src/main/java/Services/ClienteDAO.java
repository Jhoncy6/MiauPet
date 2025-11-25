package Services;

import model.Cliente;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends ConnectionDAO {

    public List<Cliente> mostrarTodosClientes() {
        connectToDb();
        String sql = "SELECT * FROM Cliente";
        List<Cliente> clientes = new ArrayList<>();
        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                int telefone = rs.getInt("telefone");
                String email = rs.getString("email");

                Cliente cliente = new Cliente(nome, cpf, telefone, email);
                cliente.setId(id);

                clientes.add(cliente);
            }

            return clientes;

        } catch (SQLException e) {
            System.out.println("Erro ao listar todos os clientes: " + e.getMessage());
            return new ArrayList<>(); // melhor que retornar null
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    public Cliente buscarClientePorCpf(String cpf) {
        connectToDb();
        String sql = "SELECT * FROM Cliente WHERE cpf = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, cpf);
            rs = pst.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int telefone = rs.getInt("telefone");
                String email = rs.getString("email");

                Cliente cliente = new Cliente(nome, cpf, telefone, email);
                cliente.setId(id);

                return cliente;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar Cliente por CPF: " + e.getMessage());
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    } // não precisa desse ponto e vírgula extra aqui

}
