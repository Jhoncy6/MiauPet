package model;

import java.time.LocalDateTime;
import java.util.List;

public class Consulta {
    private int id;
    private LocalDateTime dia;
    private String motivo;
    private String comentarios;

    private Animal animal;         // AGORA TEM UM ANIMAL
    private Veterinario veterinario;
    private List<Servico> servicos;

    public Consulta(LocalDateTime dia, String motivo, String comentarios,
                    Animal animal, Veterinario veterinario, List<Servico> servicos) {
        this.dia = dia;
        this.motivo = motivo;
        this.comentarios = comentarios;
        this.animal = animal;
        this.veterinario = veterinario;
        this.servicos = servicos;
    }

    public Consulta() {
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

    public Animal getAnimal() {        // <- ESSE CARA AQUI
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

    public Consulta getCliente() {
        return this;
    }
}
