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


}
