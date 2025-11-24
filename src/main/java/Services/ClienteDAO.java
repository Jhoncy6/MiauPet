package Services;
import model.Cliente;
import java.sql.SQLException;
import java.sql.Statement;

public class ClienteDAO extends ConnectionDAO {

    public int inserirCliente(Cliente cliente) {
        connectToDb();
        String sql = "INSERT INTO Cliente (nome, cpf, telefone, email) VALUES (?,?,?,?)";

        try {
            pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getCpf());
            pst.setInt(3, cliente.getTelefone());
            pst.setString(4, cliente.getEmail());
            pst.execute();

            rs = pst.getGeneratedKeys();

            if (rs.next()) {
                int idGerado = rs.getInt(1);
                cliente.setId(idGerado);
                return idGerado;
            } else {
                return -1;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir Cliente: " + e.getMessage());
            return -1;
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



