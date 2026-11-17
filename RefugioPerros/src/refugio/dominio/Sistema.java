
package refugio.dominio;

// autores

import java.util.ArrayList;

public class Sistema {

    public static final String DISPONIBLE = "Disponible para adoptar";
    public static final String NO_DISPONIBLE = "No disponible para adoptar";
    public static final String ENTREGADO = "Entregado a familia";

    private ArrayList<Animal> animales;
    private ArrayList<Familia> familias;
    private ArrayList<Empresa> empresas;
    private ArrayList<Adopcion> adopciones;
    private ArrayList<Donacion> donaciones;

    private int proximoNumeroAdopcion;
    private int proximoNumeroDonacion;

    public Sistema() {
        animales = new ArrayList<>();
        familias = new ArrayList<>();
        empresas = new ArrayList<>();
        adopciones = new ArrayList<>();
        donaciones = new ArrayList<>();

        proximoNumeroAdopcion = 1;
        proximoNumeroDonacion = 1;
    }

    // altas
    public boolean agregarAnimal(Animal animal) {
        if (animal == null) {
            return false;
        }

        if (!textoValido(animal.getCodigo())
                || !textoValido(animal.getNombre())
                || !textoValido(animal.getTemperamento())
                || !Double.isFinite(animal.getPeso())
                || animal.getPeso() <= 0) {
            return false;
        }

        String estado = animal.getEstado();

        if (!DISPONIBLE.equals(estado)
                && !NO_DISPONIBLE.equals(estado)
                && !ENTREGADO.equals(estado)) {
            return false;
        }

        if (buscarAnimal(animal.getCodigo()) != null) {
            return false;
        }

        animales.add(animal);
        return true;
    }

    public boolean agregarFamilia(Familia familia) {
        if (familia == null) {
            return false;
        }

        if (!textoValido(familia.getCedula())
                || !textoValido(familia.getApellido())
                || !textoValido(familia.getCelular())) {
            return false;
        }

        if (buscarFamilia(familia.getCedula()) != null) {
            return false;
        }

        familias.add(familia);
        return true;
    }

    public boolean agregarEmpresa(Empresa empresa) {
        if (empresa == null) {
            return false;
        }

        if (!textoValido(empresa.getNombre())
                || !textoValido(empresa.getRut())
                || !textoValido(empresa.getDireccion())) {
            return false;
        }

        if (buscarEmpresa(empresa.getRut()) != null) {
            return false;
        }

        empresas.add(empresa);
        return true;
    }

    // busquedas
    public Animal buscarAnimal(String codigo) {
        if (codigo == null) {
            return null;
        }

        for (Animal animal : animales) {
            if (animal.getCodigo().trim()
                    .equalsIgnoreCase(codigo.trim())) {
                return animal;
            }
        }

        return null;
    }

    public Familia buscarFamilia(String cedula) {
        if (cedula == null) {
            return null;
        }

        for (Familia familia : familias) {
            if (familia.getCedula().trim()
                    .equals(cedula.trim())) {
                return familia;
            }
        }

        return null;
    }

    public Empresa buscarEmpresa(String rut) {
        if (rut == null) {
            return null;
        }

        for (Empresa empresa : empresas) {
            if (empresa.getRut().trim()
                    .equals(rut.trim())) {
                return empresa;
            }
        }

        return null;
    }

    // consultas listas
    public ArrayList<Animal> getAnimales() {
        return new ArrayList<>(animales);
    }

    public ArrayList<Familia> getFamilias() {
        return new ArrayList<>(familias);
    }

    public ArrayList<Empresa> getEmpresas() {
        return new ArrayList<>(empresas);
    }

    public ArrayList<Adopcion> getAdopciones() {
        return new ArrayList<>(adopciones);
    }

    public ArrayList<Donacion> getDonaciones() {
        return new ArrayList<>(donaciones);
    }

    // validacion aux
    private boolean textoValido(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }
}
