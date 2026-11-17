
package refugio.dominio;

// autores

public class Empresa {

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
