
package refugio;

// Autores: completar nombres y números de estudiante

import refugio.dominio.Animal;
import refugio.dominio.Sistema;

public class Main {

    public static void main(String[] args) {

        Sistema sistema = new Sistema();

        Animal perro1 = new Animal(
                "A001", "Rocky", "Tranquilo",
                15.5, Sistema.DISPONIBLE);

        Animal perro2 = new Animal(
                "A002", "Luna", "Juguetona",
                8.0, Sistema.NO_DISPONIBLE);

        Animal perro3 = new Animal(
                "A001", "Max", "Activo",
                12.0, Sistema.DISPONIBLE);

        System.out.println(sistema.agregarAnimal(perro1));
        System.out.println(sistema.agregarAnimal(perro2));
        System.out.println(sistema.agregarAnimal(perro3));

        System.out.println("Total de animales: "
                + sistema.getAnimales().size());
    }
}
