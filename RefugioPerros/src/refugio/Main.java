package refugio;

//PESCE Diego; ROUET--COIRIER Raphaël
import refugio.dominio.*;

public class Main implements ObservadorSistema {

    public static void main(String[] args) {

        Sistema sistema = new Sistema();

        Main observador = new Main();

        sistema.agregarObservador(observador);

        sistema.agregarAnimal(new Animal(
                "A001", "Rocky", "Tranquilo",
                15.5, Sistema.DISPONIBLE));

        sistema.agregarAnimal(new Animal(
                "A002", "Luna", "Juguetona",
                8.0, Sistema.DISPONIBLE));

        sistema.cambiarEstadoAnimal("A001");

        sistema.eliminarObservador(observador);

        sistema.agregarAnimal(new Animal(
                "A003", "Max", "Activo",
                12.0, Sistema.DISPONIBLE));
    }

    @Override
    public void actualizar(Sistema sistema) {

        System.out.println("Sistema actualizado");

        System.out.println("Total: "
                + sistema.getCantidadAnimalesTotales());

        System.out.println("Disponibles: "
                + sistema.getCantidadAnimalesDisponibles());

        System.out.println("No disponibles: "
                + sistema.getCantidadAnimalesNoDisponibles());

        System.out.println("------------------");
    }
}
