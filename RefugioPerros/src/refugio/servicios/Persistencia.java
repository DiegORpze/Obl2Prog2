
package refugio.servicios;

//PESCE Diego; ROUET--COIRIER Raphaël

import refugio.dominio.Sistema;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class Persistencia {

    private static final String ARCHIVO = "refugio.dat";

    public static void guardar(Sistema sistema)
            throws IOException {

        try (ObjectOutputStream salida = new ObjectOutputStream(
                new FileOutputStream(ARCHIVO))) {

            salida.writeObject(sistema);
        }
    }

    public static Sistema recuperar()
            throws IOException, ClassNotFoundException {

        try (ObjectInputStream entrada = new ObjectInputStream(
                new FileInputStream(ARCHIVO))) {

            Sistema sistema = (Sistema) entrada.readObject();

            return sistema;
        }
    }
}
