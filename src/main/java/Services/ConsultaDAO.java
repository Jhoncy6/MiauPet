package Services;

import model.Cliente;
import model.Consulta;
import model.Servico;
import model.Veterinario;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO extends ConnectionDAO {

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
                        "",
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

    public boolean atualizarConsulta(Consulta consulta) {
        connectToDb();
        String sql = "UPDATE Consulta SET motivo = ?, comentarios = ? WHERE id = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, consulta.getMotivo());
            pst.setString(2, consulta.getComentarios());
            pst.setInt(3, consulta.getId());
            int linhasAfetadas = pst.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar consulta: " + e.getMessage());
            return false;
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

    public boolean removerConsulta(int idConsulta) {
        connectToDb();
        String sql = "DELETE FROM Consulta WHERE id = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, idConsulta);
            int linhas = pst.executeUpdate();
            return linhas > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao remover consulta: " + e.getMessage());
            return false;
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

    public boolean adicionarServicoNaConsulta(int idConsulta, int idServico) {
        connectToDb();
        String sql = "INSERT INTO Consulta_has_Servico (idConsulta, idServico) VALUES (?, ?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, idConsulta);
            pst.setInt(2, idServico);
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar serviço extra: " + e.getMessage());
            return false;
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

}
