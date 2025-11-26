package Services;

import model.Cliente;
import model.Consulta;
import model.Servico;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO extends ConnectionDAO {

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
