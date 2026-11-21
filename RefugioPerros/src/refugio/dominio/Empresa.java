package refugio.dominio;

//PESCE Diego; ROUET--COIRIER Raphaël
import java.io.Serializable;

public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String rut;
    private String direccion;

    public Empresa(String nombre, String rut, String direccion) {
        this.nombre = nombre;
        this.rut = rut;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRut() {
        return rut;
    }

    public String getDireccion() {
        return direccion;
    }
}
