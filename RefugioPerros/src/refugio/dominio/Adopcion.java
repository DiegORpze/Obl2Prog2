
package refugio.dominio;

//PESCE Diego; ROUET--COIRIER Raphaël

public class Adopcion {

    private int numero;
    private Animal animal;
    private Familia familia;
    private String comentarios;

    public Adopcion(int numero, Animal animal,
                    Familia familia, String comentarios) {
        this.numero = numero;
        this.animal = animal;
        this.familia = familia;
        this.comentarios = comentarios;
    }
    public int getNumero() {
        return numero;
    }
    public Animal getAnimal() {
        return animal;
    }
    public Familia getFamilia() {
        return familia;
    }
    public String getComentarios() {
        return comentarios;
    }
}
