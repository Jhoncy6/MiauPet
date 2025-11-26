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

    public Cliente buscarClientePorId(int id) {
        connectToDb();
        String sql = "SELECT * FROM Cliente WHERE id = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                int telefone = rs.getInt("telefone");
                String email = rs.getString("email");

                Cliente cliente = new Cliente(nome, cpf, telefone, email);
                cliente.setId(id);
                return cliente;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente por ID: " + e.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return null;
    }

    public boolean atualizarCliente(Cliente cliente) {
        connectToDb();
        String sql = "UPDATE Cliente SET nome = ?, cpf = ?, telefone = ?, email = ? WHERE id = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getCpf());
            pst.setInt(3, cliente.getTelefone());
            pst.setString(4, cliente.getEmail());
            pst.setInt(5, cliente.getId());

            int linhasAfetadas = pst.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
            return false;
        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    public boolean removerClientePorId(int id) {
        connectToDb();
        String sql = "DELETE FROM Cliente WHERE id = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);

            int linhasAfetadas = pst.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao remover cliente: " + e.getMessage());
            return false;
        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
