package refugio.dominio;

//PESCE Diego; ROUET--COIRIER Raphaël
import java.io.Serializable;

public class Donacion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int numero;
    private Empresa empresa;
    private int tipo;
    private double cantidadKilos;

    public Donacion(int numero, Empresa empresa,
            int tipo, double cantidadKilos) {
        this.numero = numero;
        this.empresa = empresa;
        this.tipo = tipo;
        this.cantidadKilos = cantidadKilos;
    }

    public int getNumero() {
        return numero;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public int getTipo() {
        return tipo;
    }

    public double getCantidadKilos() {
        return cantidadKilos;
    }
}
