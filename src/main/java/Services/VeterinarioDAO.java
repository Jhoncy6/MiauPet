package Services;

import model.Veterinario;
import java.sql.SQLException;
import java.sql.Statement;

public class VeterinarioDAO extends ConnectionDAO {

    public int inserirVeterinario(Veterinario vet) {
        connectToDb();
        String sql = "INSERT INTO Veterinario (nome, cpf, especialidade, crmv) VALUES (?, ?, ?, ?)";

        try {
            pst = connection.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, vet.getNome());
            pst.setString(2, vet.getCpf());
            pst.setString(3, vet.getEspecialidade());
            pst.setString(4, vet.getCrmv());
            pst.execute();

            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                int idGerado = rs.getInt(1);
                vet.setId(idGerado);
                return idGerado;
            } else {
                System.out.println("Erro, id veterinario nao gerado");
                return -1;
            }


        } catch (SQLException e) {
            System.out.println("Erro ao inserir Veterinário: " + e.getMessage());
            return -1;

        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    public boolean listarVeterinarios() {
        connectToDb();
        String sql = "SELECT id, nome, cpf, especialidade, crmv FROM Veterinario";

        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();

            System.out.println("\n=== Veterinários cadastrados ===");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String esp = rs.getString("especialidade");
                String crmv = rs.getString("crmv");

                System.out.println(id + " | " + nome + " | " + esp + " | CRMV: " + crmv);
            }
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao buscar veterinários: " + e.getMessage());
            return false;

        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}