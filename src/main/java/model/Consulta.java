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
    private Animal animal;
    private List<Servico> servicos = new ArrayList<>();

    public Consulta( String motivo, LocalDateTime dia, String comentarios, Cliente cliente, Veterinario veterinario, Animal animal) {
        this.dia = dia;
        this.motivo = motivo;
        this.comentarios = comentarios;
        this.cliente = cliente;
        this.veterinario = veterinario;
        this.animal = animal;
    }

    public Consulta(int id, String motivo, LocalDateTime dia, String comentarios, Cliente cliente, Veterinario veterinario, Animal animal) {
        this.id = id;
        this.dia = dia;
        this.motivo = motivo;
        this.comentarios = comentarios;
        this.cliente = cliente;
        this.veterinario = veterinario;
        this.animal = animal;
    }

    public void adicionarServico(Servico servico) {
        this.servicos.add(servico);
    }

    public Animal getAnimal() {
        return animal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDia() {
        return dia;
    }

    public String getComentarios() {
        return comentarios;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public String getMotivo() {
        return motivo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

}
