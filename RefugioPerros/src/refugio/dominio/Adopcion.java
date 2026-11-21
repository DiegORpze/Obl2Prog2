package refugio.dominio;

//PESCE Diego; ROUET--COIRIER Raphaël
import java.io.Serializable;

public class Adopcion implements Serializable {

    private static final long serialVersionUID = 1L;

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
