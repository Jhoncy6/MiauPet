public class Animal {
    private int id;
    private String nome;
    private String especie;
    private String raca;
    private Cliente dono; // Composicao de volta

    public Animal(int id, String nome, String especie, String raca, Cliente dono) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.dono = dono;
    }

}
