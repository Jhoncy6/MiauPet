package Services;
import model.Cliente;
import model.Servico;
import java.sql.SQLException;

public class ClienteDAO extends ConnectionDAO {

    public boolean inserirCliente(Cliente cliente) {
        connectToDb();
        String sql = "INSERT INTO Cliente (nome, cpf, telefone, email) VALUES (?,?,?,?)";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getCpf());
            pst.setInt(3, cliente.getTelefone());
            pst.setString(4, cliente.getEmail());
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir Cliente: " + e.getMessage());
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
}



