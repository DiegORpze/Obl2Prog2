package refugio.servicios;

//PESCE Diego; ROUET--COIRIER Raphaël

import refugio.dominio.Sistema;
import refugio.dominio.Donacion;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ImportadorDonaciones {
    
    public static String importar(File archivo, Sistema sistema)
            throws IOException {

        if (archivo == null || !archivo.isFile()) {
            throw new IOException("El archivo no existe.");
        }

        ArrayList<String> errores = new ArrayList<>();
        int correctas = 0;
        int numeroLinea = 0;

        ArrayList<String> lineas = new ArrayList<>(
                Files.readAllLines(archivo.toPath(),
                        StandardCharsets.UTF_8));

        for (String linea : lineas) {
            numeroLinea++;

            String[] datos = linea.split("#", -1);

            if (datos.length != 3) {
                errores.add("Linea " + numeroLinea
                        + ": formato incorrecto");
                continue;
            }

            String rut = datos[0].trim();
            String textoTipo = datos[1].trim();
            String textoKilos = datos[2].trim();

            ArrayList<String> erroresLinea = new ArrayList<>();

            if (rut.isEmpty()) {
                erroresLinea.add("RUT vacio");
            } else if (sistema.buscarEmpresa(rut) == null) {
                erroresLinea.add("Empresa no registrada");
            }

            int tipo = 0;

            try {
                tipo = Integer.parseInt(textoTipo);

                if (tipo < 1 || tipo > 4) {
                    erroresLinea.add("Tipo incorrecto");
                }

            } catch (NumberFormatException e) {
                erroresLinea.add("Tipo incorrecto");
            }

            double kilos = 0;

            try {
                kilos = Double.parseDouble(
                        textoKilos.replace(",", "."));

                if (!Double.isFinite(kilos) || kilos <= 0) {
                    erroresLinea.add("Cantidad incorrecta");
                }

            } catch (NumberFormatException e) {
                erroresLinea.add("Cantidad incorrecta");
            }

            if (!erroresLinea.isEmpty()) {
                errores.add("Linea " + numeroLinea + ": "
                        + String.join(", ", erroresLinea));
            } else {
                Donacion donacion = sistema.registrarDonacion(
                        rut, tipo, kilos);

                if (donacion != null) {
                    correctas++;
                } else {
                    errores.add("Linea " + numeroLinea
                            + ": no se pudo registrar");
                }
            }
        }

        String resultado = "Lineas procesadas: " + numeroLinea
                + "\nDonaciones importadas: " + correctas
                + "\nLineas con errores: " + errores.size();

        if (!errores.isEmpty()) {

            String nombre = archivo.getName();
            int punto = nombre.lastIndexOf('.');

            if (punto > 0) {
                nombre = nombre.substring(0, punto);
            }

            File archivoErrores = new File(
                    archivo.getParentFile(),
                    nombre + "Error.txt");

            Files.write(archivoErrores.toPath(),
                    errores, StandardCharsets.UTF_8);

            resultado += "\nArchivo de errores: "
                    + archivoErrores.getAbsolutePath();
        }

        return resultado;
    }
}
