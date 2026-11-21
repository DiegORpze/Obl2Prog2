
package refugio.dominio;

//PESCE Diego; ROUET--COIRIER Raphaël

public class Animal {

    private String codigo;
    private String nombre;
    private String temperamento;
    private double peso;
    private String estado;

    public Animal(String codigo, String nombre,
                  String temperamento, double peso,
                  String estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.temperamento = temperamento;
        this.peso = peso;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public String getTemperamento() {
        return temperamento;
    }
    public double getPeso() {
        return peso;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
