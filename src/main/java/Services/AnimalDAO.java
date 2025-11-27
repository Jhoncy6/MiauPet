package Services;

import model.Animal;
import model.Cliente;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO extends ConnectionDAO {

    public List<Animal> buscarAnimaisPorIdCliente(int idCliente) {
        List<Animal> lista = new ArrayList<>();
        connectToDb();
        String sql = "SELECT * FROM Animal WHERE idCliente = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, idCliente);
            rs = pst.executeQuery();

            while (rs.next()) {
                int id  = rs.getInt("id");
                String nome = rs.getString("nome");
                String especie = rs.getString("especie");
                String raca = rs.getString("raca");

                Animal animal = new Animal(nome, especie, raca, null);
                animal.setId(id);
                lista.add(animal);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar animais por idCliente: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }

        return lista;
    }

    public boolean atualizarAnimal(Animal animal) {
        connectToDb();
        String sql = "UPDATE Animal SET nome = ?, especie = ?, raca = ? WHERE id = ?";
        boolean sucesso = false;

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, animal.getNome());
            pst.setString(2, animal.getEspecie());
            pst.setString(3, animal.getRaca());
            pst.setInt(4, animal.getId());

            int rowsAffected = pst.executeUpdate();
            sucesso = rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar animal: " + e.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return sucesso;
    }

    public boolean removerAnimalPorId(int idAnimal) {
        connectToDb();
        String sql = "DELETE FROM Animal WHERE id = ?";
        boolean sucesso = false;

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, idAnimal);

            int rowsAffected = pst.executeUpdate();
            sucesso = rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao remover animal: " + e.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return sucesso;
    }

}
