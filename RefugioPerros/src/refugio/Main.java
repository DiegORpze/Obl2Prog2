package refugio;

//PESCE Diego; ROUET--COIRIER Raphaël
import refugio.dominio.Sistema;
import refugio.interfaz.Principal;
import javax.swing.UIManager;
import refugio.dominio.*;

public class Main {

    public static void main(String[] args) {

        try {

            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());

        } catch (Exception e) {

            System.out.println("No se pudo cambiar la apariencia.");
        }

        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

                Sistema sistema = new Sistema();

                Principal ventana = new Principal(sistema);

                ventana.setVisible(true);


            }
        });
    }
}
