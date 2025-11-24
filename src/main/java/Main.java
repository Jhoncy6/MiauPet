
import Services.ServicoDAO;
import model.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Cliente cliente1 = new Cliente("João", 1, "99999-9999", 1212123, "joao@x.com");
        Veterinario vet1 = new Veterinario("Dr. Carlos", 1, "123123", "Clinico geral", "CRMV-1234");
        Veterinario vet2 = new Veterinario("Dra. Carla", 2, "123548", "sim", "CRMV-56342");

        Animal rex = new Animal("Rex", "Cachorro", "Buldog", cliente1);
        cliente1.adicionarAnimal(rex);

        Consulta consulta1 = new Consulta(
                1,
                "Doente",
                LocalDateTime.of(2025, 11, 30, 15, 0),
                "Filhote, foi pego na rua recentemente, pode ter alguma alergia",
                cliente1,
                vet1
        );

        Servico s1  = new Servico(5, "Raio-X geral", 50.0);
        Servico s2  = new Servico(4, "Tosa completa", 30.0);
        Servico s3  = new Servico(2, "Vacinação", 60.0);
        Servico s4  = new Servico(3, "Banho", 40.0);
        Servico s5  = new Servico(6, "Exame de sangue", 120.0);
        Servico s6  = new Servico(8, "Limpeza de ouvido", 25.0);
        Servico s7  = new Servico(7, "Ultrassom abdominal", 150.0);
        Servico s8  = new Servico(9, "Corte de unhas", 18.0);
        Servico s9 = new Servico(10, "Aplicação de antipulgas", 35.0);
        Servico s10  = new Servico(1, "Consulta geral", 80.0);

        ServicoDAO dao = new ServicoDAO();
        dao.inserirServico(s1);
        dao.inserirServico(s2);
        dao.inserirServico(s3);
        dao.inserirServico(s4);
        dao.inserirServico(s5);
        dao.inserirServico(s6);
        dao.inserirServico(s7);
        dao.inserirServico(s8);
        dao.inserirServico(s9);
        dao.inserirServico(s10);

        CatalogoServicos catalogo = new CatalogoServicos();

        catalogo.adicionarServico(s1);
        catalogo.adicionarServico(s2);
        catalogo.adicionarServico(s3);
        catalogo.adicionarServico(s4);
        catalogo.adicionarServico(s5);
        catalogo.adicionarServico(s6);
        catalogo.adicionarServico(s7);
        catalogo.adicionarServico(s8);
        catalogo.adicionarServico(s9);
        catalogo.adicionarServico(s10);

        int opcao;

        do {
            System.out.println("\nOlá, " + cliente1.getNome() + "!");
            System.out.println("O que você deseja fazer?");
            System.out.println("1 - Ver serviços disponíveis");
            System.out.println("2 - Marcar uma consulta");
            System.out.println("3 - Ver médicos disponíveis");
            System.out.println("4 - Sair");
            System.out.print("Opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:
                    catalogo.listarServicos();
                    break;

                case 2:
                    System.out.println("\n=== Marcar consulta ===");

                    System.out.print("Digite o nome do seu animal: ");
                    String nomeAnimal = sc.nextLine();

                    System.out.print("Espécie: ");
                    String especie = sc.nextLine();

                    System.out.print("Raça: ");
                    String raca = sc.nextLine();

                    Animal animal = new Animal(nomeAnimal, especie, raca, cliente1);
                    cliente1.adicionarAnimal(animal);

                    System.out.print("Motivo da consulta: ");
                    String motivo = sc.nextLine();

                    System.out.print("Comentários adicionais: ");
                    String comentarios = sc.nextLine();

                    LocalDateTime dataConsulta = LocalDateTime.now();

                    Consulta consulta = new Consulta(
                            2,
                            motivo,
                            dataConsulta,
                            comentarios,
                            cliente1,
                            vet1
                    );

                    consulta.adicionarServico(s10);
                    consulta.adicionarServico(s3);

                    System.out.println("\nConsulta marcada com sucesso!");
                    consulta.exibirResumo();
                    break;

                case 3:
                    System.out.println("\nMédicos disponíveis:");
                    System.out.println("- " + vet1.getNome() + " | Clínico geral");
                    System.out.println("- " + vet2.getNome() + " | Dermatologia");
                    break;

                case 4:
                    System.out.println("\nObrigado por usar o MiAu Pet! Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 4);

        sc.close();
    }
}
