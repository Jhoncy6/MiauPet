package Services;

import model.Cliente;
import model.Consulta;
import model.Servico;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO extends ConnectionDAO {

    public int inserirConsulta(Consulta consulta) {

        connectToDb();

        String sql = "INSERT INTO Consulta (dia, motivo, comentarios, idCliente, idVeterinario) " +
                "VALUES (?, ?, ?, ?, ?)";

        try {
            pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pst.setTimestamp(1, Timestamp.valueOf(consulta.getDia()));
            pst.setString(2, consulta.getMotivo());
            pst.setString(3, consulta.getComentarios());
            pst.setInt(4, consulta.getCliente().getId());
            pst.setInt(5, consulta.getVeterinario().getId());

            pst.execute();

            // PEGANDO O ID GERADO
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                int idGerado = rs.getInt(1);
                consulta.setId(idGerado);
                return idGerado;
            } else {
                return -1;
            }

            //inserirServicosDaConsulta(consulta);

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
        }
    }
    public List<Servico> buscarServicosDaConsulta(int idConsulta) {
        connectToDb();
        String sql =
                "SELECT s.id, s.nomeServico, s.preco " +
                        "FROM Servico s " +
                        "JOIN Consulta_has_Servico cs ON cs.idServico = s.id " +
                        "WHERE cs.idConsulta = ?";

        List<Servico> servicos = new ArrayList<>();

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, idConsulta);
            rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nomeServico = rs.getString("nomeServico");
                double preco = rs.getDouble("preco"); // ajusta pro tipo do seu model

                // ajusta pro seu construtor real:
                Servico servico = new Servico(nomeServico, preco);
                servico.setId(id);

                servicos.add(servico);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar serviços da consulta: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return servicos;
    }


}
