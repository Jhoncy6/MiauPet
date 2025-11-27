package Services;
import model.Servico;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO extends ConnectionDAO {

    public Servico buscarServicoPorId(int idServico) {
        connectToDb();
        String sql = "SELECT id, preco, nomeServico FROM Servico WHERE id = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, idServico);
            rs = pst.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                double preco = rs.getDouble("preco");
                String nome = rs.getString("nomeServico");

                Servico servico = new Servico(nome, preco);
                servico.setId(id);
                return servico;
            }
            return null;

        } catch (SQLException e) {
            System.out.println("Erro ao buscar serviço por ID: " + e.getMessage());
            return null;
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

    public boolean listarServicos() {
        connectToDb();
        String sql = "SELECT * FROM Servico";
        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                double preco = rs.getDouble("preco");
                String nome = rs.getString("nomeServico");
                System.out.println("ID: " + id + " - " + nome + " | R$ " + preco);
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao Buscar servicos: " + e.getMessage());
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

    public List<Servico> listarTodosServicos() {
        List<Servico> lista = new ArrayList<>();
        connectToDb();
        String sql = "SELECT * FROM Servico";

        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                double preco = rs.getDouble("preco");
                String nome = rs.getString("nomeServico");

                Servico s = new Servico(nome, preco);
                s.setId(id);
                lista.add(s);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar serviços: " + e.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar: " + e.getMessage());
            }
        }
        return lista;
    }

    public boolean atualizarServico(Servico servico) {
        connectToDb();
        String sql = "UPDATE Servico SET nomeServico = ?, preco = ? WHERE id = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, servico.getNomeServico());
            pst.setDouble(2, servico.getPreco());
            pst.setInt(3, servico.getId());

            int linhas = pst.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar serviço: " + e.getMessage());
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

    public boolean removerServicoPorId(int id) {
        connectToDb();
        String sql = "DELETE FROM Servico WHERE id = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);

            int linhasAfetadas = pst.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao remover serviço: " + e.getMessage());
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

    public boolean inserirServico(Servico servico) {
        connectToDb();
        String sql = "INSERT INTO Servico (preco, nomeServico) VALUES (?, ?)";

        try {
            pst = connection.prepareStatement(sql);
            pst.setDouble(1, servico.getPreco());
            pst.setString(2, servico.getNomeServico());
            pst.executeUpdate(); // insere no banco

            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir Servico: " + e.getMessage());
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
