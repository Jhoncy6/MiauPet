package Services;
import model.Consulta;
import model.Servico;
import model.Veterinario;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO extends ConnectionDAO {

    public int inserirConsulta(Consulta consulta) {

        connectToDb();
        String sql = "INSERT INTO Consulta (dia, motivo, comentarios, idVeterinario, idAnimal) VALUES (?, ?, ?, ?, ?)";

        try {
            pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pst.setTimestamp(1, Timestamp.valueOf(consulta.getDia()));
            pst.setString(2, consulta.getMotivo());
            pst.setString(3, consulta.getComentarios());
            pst.setInt(4, consulta.getCliente().getId());
            pst.setInt(5, consulta.getAnimal().getId());

            pst.execute();

            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                int idGerado = rs.getInt(1);
                consulta.setId(idGerado);
                inserirServicosDaConsulta(consulta);
                return idGerado;
            } else {
                return -1;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir consulta: " + e.getMessage());
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

    public void inserirServicosDaConsulta(Consulta consulta) {

        connectToDb();
        String sql = "INSERT INTO Consulta_has_Servico (idConsulta, idServico) VALUES (?, ?)";

        try {
            pst = connection.prepareStatement(sql);

            for (Servico servico : consulta.getServicos()) {
                pst.setInt(1, consulta.getId());
                pst.setInt(2, servico.getId());
                pst.execute();
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir serviços da consulta: " + e.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    public List<Consulta> buscarConsultasPorIdAnimal(int idAnimal) {
        List<Consulta> listaConsultas = new ArrayList<>();
        connectToDb();

        String sql = "SELECT c.*, v.nome as nomeVet, v.crmv, v.especialidade " + "FROM Consulta c " + "INNER JOIN Veterinario v ON c.idVeterinario = v.id " + "WHERE c.idAnimal = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, idAnimal);
            rs = pst.executeQuery();

            while (rs.next()) {
                int idConsulta = rs.getInt("id");
                LocalDateTime dia = rs.getTimestamp("dia").toLocalDateTime();
                String motivo = rs.getString("motivo");
                String comentarios = rs.getString("comentarios");

                Veterinario vet = new Veterinario(
                        rs.getString("nomeVet"),
                        rs.getString( "cpf"),
                        rs.getString("especialidade"),
                        rs.getString("crmv")
                );
                vet.setId(rs.getInt("idVeterinario"));


                Consulta consulta = new Consulta(idConsulta, motivo, dia, comentarios, null, vet, null);

                buscarServicosDaConsulta(consulta);

                listaConsultas.add(consulta);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar consultas: " + e.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return listaConsultas;
    }

    private void buscarServicosDaConsulta(Consulta consulta) {

        ConnectionDAO daoAux = new ConnectionDAO() {};
        daoAux.connectToDb();

        String sql = "SELECT s.* FROM Servico s " +
                "INNER JOIN Consulta_has_Servico cs ON s.id = cs.idServico " +
                "WHERE cs.idConsulta = ?";

        try {
            PreparedStatement pstAux = daoAux.connection.prepareStatement(sql);
            pstAux.setInt(1, consulta.getId());
            ResultSet rsAux = pstAux.executeQuery();

            while(rsAux.next()) {
                Servico s = new Servico(
                        rsAux.getInt("id"),
                        rsAux.getString("nomeServico"),
                        rsAux.getDouble("preco")
                );
                consulta.adicionarServico(s);
            }
            pstAux.close();
            daoAux.connection.close();

        } catch (SQLException e) {
            System.out.println("Erro ao buscar serviços da consulta: " + e.getMessage());
        }
    }

}
