import java.time.LocalDateTime;

public class Consulta {

    private int id;
    private LocalDateTime dia;
    private String motivo;
    private String comentarios;

    private Servico servico;

    public Consulta() {
    }

    public Consulta(int id, LocalDateTime dia, String motivo, String comentarios) {
        this.id = id;
        this.dia = dia;
        this.motivo = motivo;
        this.comentarios = comentarios;
    }


}
