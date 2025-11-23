import java.util.ArrayList;
import java.util.List;

public class CatalogoServicos {
    private List<Servico> servicos = new ArrayList<>();

    public void adicionarServico(Servico servico) {
        servicos.add(servico);
    }

    public void listarServicos() {
            System.out.println("Serviços disponíveis:");
        for (Servico s : servicos) {
            System.out.println("- " + s.getNomeServico() + " | R$ " + s.getPreco());
        }
    }

}
