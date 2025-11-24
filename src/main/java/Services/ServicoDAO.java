package Services;
import model.Servico;

import java.sql.SQLException;

public class ServicoDAO extends ConnectionDAO {

    public boolean inserirServico(Servico servico) {
        connectToDb();
        String sql = "INSERT INTO Servico (preco, nomeServico) VALUES (?,?)";

        try {
            pst = connection.prepareStatement(sql);
            pst.setDouble(1, servico.getPreco());
            pst.setString(2, servico.getNomeServico());
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir Servico: " + e.getMessage());
            return false;
        } finally {
            try{
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
}
