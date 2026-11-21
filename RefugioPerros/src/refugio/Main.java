package refugio;

//PESCE Diego; ROUET--COIRIER Raphaël
import refugio.dominio.*;
import refugio.servicios.Persistencia;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Sistema sistema = new Sistema();

        sistema.agregarAnimal(new Animal(
                "A001", "Rocky", "Tranquilo",
                15.5, Sistema.DISPONIBLE));

        sistema.agregarFamilia(new Familia(
                "12345678", "Fernandez", "099123456"));

        sistema.agregarEmpresa(new Empresa(
                "Alimentos SA", "123456789012",
                "Av. Italia 1234"));

        sistema.realizarAdopcion(
                "A001", "12345678", "Buena adaptacion");

        sistema.registrarDonacion(
                "123456789012", 2, 20);

        try {

            Persistencia.guardar(sistema);
            System.out.println("Datos guardados correctamente.");

            sistema = new Sistema();

            System.out.println("Animales antes de recuperar: "
                    + sistema.getCantidadAnimalesTotales());

            sistema = Persistencia.recuperar();

            System.out.println("Datos recuperados correctamente.");

            System.out.println("Animales: "
                    + sistema.getCantidadAnimalesTotales());

            System.out.println("Familias: "
                    + sistema.getFamilias().size());

            System.out.println("Empresas: "
                    + sistema.getCantidadEmpresas());

            System.out.println("Adopciones: "
                    + sistema.getCantidadAdopciones());

            System.out.println("Donaciones: "
                    + sistema.getDonaciones().size());

            System.out.println("Stock tipo 2: "
                    + sistema.getStockPorTipo(2));

            System.out.println("Estado de Rocky: "
                    + sistema.buscarAnimal("A001").getEstado());

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}
