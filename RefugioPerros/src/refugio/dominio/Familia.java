package refugio.dominio;

//PESCE Diego; ROUET--COIRIER Raphaël
import java.io.Serializable;

public class Familia implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cedula;
    private String apellido;
    private String celular;

    public Familia(String cedula, String apellido, String celular) {
        this.cedula = cedula;
        this.apellido = apellido;
        this.celular = celular;
    }

    public String getCedula() {
        return cedula;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCelular() {
        return celular;
    }
}
