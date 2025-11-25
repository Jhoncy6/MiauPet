package Services;

import model.Animal;
import model.Cliente;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO extends ConnectionDAO {

    public boolean inserirAnimal(Animal animal) {
        connectToDb();
        String sql = "INSERT INTO Animal (nome, especie, raca, idCliente) VALUES (?,?,?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, animal.getNome());
            pst.setString(2, animal.getEspecie());
            pst.setString(3, animal.getRaca());
            pst.setInt(4, animal.getDono().getId());
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

    public List<Animal> buscarAnimalDono(Cliente dono) {
        List<Animal> lista = new ArrayList<>();
        connectToDb();
        String sql = "SELECT * FROM Animal WHERE idCliente = ?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1, dono.getId());
            rs = pst.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                String especie = rs.getString("especie");
                String raca = rs.getString("raca");

                Animal animalEncontrado = new Animal(nome, especie, raca, dono);
                lista.add(animalEncontrado);
            }
        }catch(SQLException e){
            System.out.println("Erro ao inserir Animal: " + e.getMessage());
        }finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return lista;
    }


}
