package Services;


import model.Consulta;
import model.Servico;
import model.Veterinario;
import model.Animal;
import java.sql.*;
import java.time.LocalDateTime;
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


}
