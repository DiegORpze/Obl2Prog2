
package refugio.servicios;

//PESCE Diego; ROUET--COIRIER Raphaël

import refugio.dominio.Sistema;
import refugio.dominio.Adopcion;
import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class GeneradorHTML {

    public static void generar(File archivo, Sistema sistema)
            throws IOException {

        ArrayList<Adopcion> adopciones = sistema.getAdopciones();

        try (BufferedWriter escritor = Files.newBufferedWriter(
                archivo.toPath(), StandardCharsets.UTF_8)) {

            escritor.write("<!DOCTYPE html>\n");
            escritor.write("<html lang=\"es\">\n");
            escritor.write("<head>\n");
            escritor.write("<meta charset=\"UTF-8\">\n");
            escritor.write("<title>Reporte de adopciones</title>\n");
            escritor.write("</head>\n");
            escritor.write("<body>\n");

            escritor.write("<h1>Reporte de adopciones</h1>\n");

            escritor.write("<p>Total de adopciones: "
                    + adopciones.size() + "</p>\n");

            if (adopciones.isEmpty()) {

                escritor.write("<p>No existen adopciones registradas.</p>\n");

            } else {

                escritor.write("<table border=\"1\">\n");

                escritor.write("<tr>"
                        + "<th>Número</th>"
                        + "<th>Código</th>"
                        + "<th>Animal</th>"
                        + "<th>Familia</th>"
                        + "<th>Comentarios</th>"
                        + "</tr>\n");

                for (Adopcion adopcion : adopciones) {

                    escritor.write("<tr>");

                    escritor.write("<td>"
                            + adopcion.getNumero() + "</td>");

                    escritor.write("<td>"
                            + escaparHTML(adopcion.getAnimal().getCodigo())
                            + "</td>");

                    escritor.write("<td>"
                            + escaparHTML(adopcion.getAnimal().getNombre())
                            + "</td>");

                    escritor.write("<td>"
                            + escaparHTML(adopcion.getFamilia().getApellido())
                            + "</td>");

                    escritor.write("<td>"
                            + escaparHTML(adopcion.getComentarios())
                            + "</td>");

                    escritor.write("</tr>\n");
                }

                escritor.write("</table>\n");
            }

            escritor.write("</body>\n");
            escritor.write("</html>\n");
        }
    }

    private static String escaparHTML(String texto) {

        if (texto == null) {
            return "";
        }

        return texto.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
}
