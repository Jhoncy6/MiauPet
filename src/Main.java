public class Main {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("Joao", 1, "12312313", 123433, "joao@gmail.com");
        Animal animal1 = new Animal(1, "Rodirgo", "Cachorro", "ViraLata", cliente1);
        Veterinario veterinario1 = new Veterinario("Carlos", 1, "123123123", "banho","123123");
        veterinario1.Funcional();
        cliente1.adicionarAnimal(animal1);
        cliente1.Funcional();
    }
}
