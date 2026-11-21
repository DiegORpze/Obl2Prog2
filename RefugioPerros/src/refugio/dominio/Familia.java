
package refugio.dominio;

//PESCE Diego; ROUET--COIRIER Raphaël

public class Familia {

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
