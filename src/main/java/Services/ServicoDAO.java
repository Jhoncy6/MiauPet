package Services;

import model.Servico;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO extends ConnectionDAO {

    public int inserirServico(Servico servico) {
        connectToDb();

        String sql = "INSERT INTO Servico (nomeServico, preco) VALUES (?, ?)";

        try {
            pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, servico.getNomeServico());
            pst.setDouble(2, servico.getPreco());
            pst.execute();

            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                int idGerado = rs.getInt(1);
                servico.setId(idGerado);
                return idGerado;
            } else {
                return -1;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir serviço: " + e.getMessage());
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

    public List<Servico> listarTodosServicos() {
        List<Servico> servicos = new ArrayList<>();
        connectToDb();
        String sql = "SELECT id, preco, nomeServico FROM Servico ORDER BY id";
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
