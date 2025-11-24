package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



public class Consulta {

    private int id;
    private LocalDateTime dia;
    private String motivo;
    private String comentarios;

    private Cliente cliente;
    private Veterinario veterinario;
    private List<Servico> servicos = new ArrayList<>();


    public Consulta(int id, String motivo, LocalDateTime dia, String comentarios, Cliente cliente, Veterinario veterinario) {
        this.id = id;
        this.dia = dia;
        this.motivo = motivo;
        this.comentarios = comentarios;
        this.cliente = cliente;
        this.veterinario = veterinario;
    }

    public void adicionarServico(Servico servico) {
        servicos.add(servico);
    }

    public void exibirResumo() {
        System.out.println("\n===== RESUMO DA CONSULTA =====");
        System.out.println("Código: " + id);
        System.out.println("Data e hora: " + dia);
        System.out.println("Motivo: " + motivo);
        System.out.println("Comentários: " + comentarios);

        if (cliente != null) {
            System.out.println("\nCliente: " + cliente.getNome());
        }

        if (veterinario != null) {
            System.out.println("Veterinário: " + veterinario.getNome());
        }

        System.out.println("\nServiços incluídos:");
        if (servicos.isEmpty()) {
            System.out.println("- Nenhum serviço adicionado");
        } else {
            for (Servico s : servicos) {
                System.out.println("- " + s.getNomeServico() + " | R$ " + s.getPreco());
            }
        }
        System.out.println("==============================\n");
    }


}
