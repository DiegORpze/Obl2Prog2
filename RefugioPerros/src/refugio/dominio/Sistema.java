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

        if (!textoValido(animal.getCodigo()) || !textoValido(animal.getNombre()) || !textoValido(animal.getTemperamento())
                || !Double.isFinite(animal.getPeso()) || animal.getPeso() <= 0) {
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

        if (!textoValido(familia.getCedula()) || !textoValido(familia.getApellido()) || !textoValido(familia.getCelular())) {
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

        if (!textoValido(empresa.getNombre()) || !textoValido(empresa.getRut()) || !textoValido(empresa.getDireccion())) {
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
            if (animal.getCodigo().trim().equalsIgnoreCase(codigo.trim())) {
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
            if (familia.getCedula().trim().equals(cedula.trim())) {
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
            if (empresa.getRut().trim().equals(rut.trim())) {
                return empresa;
            }
        }

        return null;
    }
    
    public Adopcion buscarAdopcion(int numero) {
        for (Adopcion adopcion : adopciones) {
            if (adopcion.getNumero() == numero) {
                return adopcion;
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

    //métodos de adopciones
    public Adopcion realizarAdocpcion(String codigoAnimal, String cedulaFamilia, String comentarios) {

        if (codigoAnimal == null || cedulaFamilia == null) {
            return null;
        }

        Animal animalIngresado = buscarAnimal(codigoAnimal);
        if (animalIngresado == null || !DISPONIBLE.equals(animalIngresado.getEstado())) {
            return null;
        }
        
        Familia familiaIngresada = buscarFamilia(cedulaFamilia);
        if (familiaIngresada == null) {
            return null;
        }
        
        Adopcion adopcion = new Adopcion(proximoNumeroAdopcion, animalIngresado, familiaIngresada, comentarios);
        proximoNumeroAdopcion++;
        
        animalIngresado.setEstado(ENTREGADO);
        adopciones.add(adopcion);
        
        return adopcion;
    }
    
    public boolean anularAdopcion(int numero){
        Adopcion adopcionIngresada = buscarAdopcion(numero);
        if(adopcionIngresada == null){
            return false;
        }
        
        Animal animal = adopcionIngresada.getAnimal();
        animal.setEstado(NO_DISPONIBLE);
        adopciones.remove(adopcionIngresada);
        
        return true;
    }
    
    public boolean cambiarEstadoAnimal(string codigo){
        
        Animal animalIngresado = buscarAnimal(codigo);
        if(animalIngresado == null || !ENTREGADO.equals(animalIngresado.getEstado())){
            return false;
        }
        if(animalIngresado.getEstado().equals(DISPONIBLE)){
            animalIngresado.setEstado(NO_DISPONIBLE);
        }else{
            animalIngresado.setEstado(DISPONIBLE);
        }
        
        return true;
    }
}
