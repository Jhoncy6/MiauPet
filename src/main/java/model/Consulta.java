package model;

import java.time.LocalDateTime;
import java.util.ArrayList; // Precisas deste import
import java.util.List;

public class Consulta {
    private int id;
    private LocalDateTime dia;
    private String motivo;
    private String comentarios;

    private Cliente cliente;
    private Animal animal;
    private Veterinario veterinario;
    private List<Servico> servicos = new ArrayList<>();

    public Consulta(String motivo, LocalDateTime dia, String comentarios, Cliente cliente, Veterinario veterinario, Animal animal) {
        this.motivo = motivo;
        this.dia = dia;
        this.comentarios = comentarios;
        this.cliente = cliente;
        this.veterinario = veterinario;
        this.animal = animal;
    }

    public Consulta(int id, String motivo, LocalDateTime dia, String comentarios, Cliente cliente, Veterinario veterinario, Animal animal) {
        this.id = id;
        this.motivo = motivo;
        this.dia = dia;
        this.comentarios = comentarios;
        this.cliente = cliente;
        this.veterinario = veterinario;
        this.animal = animal;
    }

    public void adicionarServico(Servico servico) {
        this.servicos.add(servico);
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

    public void setDia(LocalDateTime dia) {
        this.dia = dia;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}