package Services;

import model.Animal;
import java.sql.SQLException;

public class AnimalDAO extends ConnectionDAO {

    public boolean inserirAnimal(Animal animal, int idDono) {
        connectToDb();
        String sql = "INSERT INTO Animal (nome, especie, raca, idCliente) VALUES (?,?,?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, animal.getNome());
            pst.setString(2, animal.getEspecie());
            pst.setString(3, animal.getRaca());
            pst.setInt(4, idDono);
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir Animal: " + e.getMessage());
            return false;
        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    public boolean inserirAnimal(Animal animal) {
        int idDono = animal.getDono().getId();
        return inserirAnimal(animal, idDono);
    }
}
