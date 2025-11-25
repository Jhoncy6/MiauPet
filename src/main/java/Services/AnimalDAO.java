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

                // se o construtor do Animal espera Cliente, você pode passar null aqui ou ajustar
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

}
