package Services;
import model.Servico;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO extends ConnectionDAO {

    public List <Servico> listarTodosServicos() {
        List<Servico> servicos = new ArrayList<>();
        connectToDb();
        String sql = "SELECT id, preco, nomeServico FROM Servico";
        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                double preco = rs.getDouble("preco");
                String nome = rs.getString("nomeServico");
                Servico servico = new Servico(id, nome, preco);
                servicos.add(servico);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao Buscar servicos: " + e.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return servicos;
    }

}
