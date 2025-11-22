public class Animal {
    private int id;
    private String nome;
    private String especie;
    private String raca;

    // Composicao de volta -> Cliente e Animal
    private Cliente dono;

    public Animal(String nome, String especie, String raca, Cliente dono) {
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.dono = dono;
    }

}
