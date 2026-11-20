
package refugio;

import refugio.dominio.*;

public class Main {

    public static void main(String[] args) {

        Sistema sistema = new Sistema();

        Empresa empresa = new Empresa(
                "Alimentos SA",
                "123456789012",
                "Av. Italia 1234"
        );

        sistema.agregarEmpresa(empresa);

        Donacion donacion1 = sistema.registrarDonacion(
                "123456789012", 2, 15.5);

        Donacion donacion2 = sistema.registrarDonacion(
                "123456789012", 2, 4.5);

        System.out.println("Donacion 1: "
                + donacion1.getNumero());

        System.out.println("Donacion 2: "
                + donacion2.getNumero());

        System.out.println("Total donaciones: "
                + sistema.getDonaciones().size());

        System.out.println("Stock tipo 2: "
                + sistema.getStockPorTipo(2));
    }
}
