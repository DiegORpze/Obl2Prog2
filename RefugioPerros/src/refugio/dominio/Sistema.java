package refugio.dominio;

//PESCE Diego; ROUET--COIRIER Raphaël
import java.util.ArrayList;
import java.io.Serializable;

public class Sistema implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String DISPONIBLE = "Disponible para adoptar";
    public static final String NO_DISPONIBLE = "No disponible para adoptar";
    public static final String ENTREGADO = "Entregado a familia";

    private ArrayList<Animal> animales;
    private ArrayList<Familia> familias;
    private ArrayList<Empresa> empresas;
    private ArrayList<Adopcion> adopciones;
    private ArrayList<Donacion> donaciones;
    private transient ArrayList<ObservadorSistema> observadores;

    private int proximoNumeroAdopcion;
    private int proximoNumeroDonacion;

    public Sistema() {
        animales = new ArrayList<>();
        familias = new ArrayList<>();
        empresas = new ArrayList<>();
        adopciones = new ArrayList<>();
        donaciones = new ArrayList<>();
        observadores = new ArrayList<>();

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

        if (!DISPONIBLE.equals(estado) && !NO_DISPONIBLE.equals(estado)) {
            return false;
        }

        if (buscarAnimal(animal.getCodigo()) != null) {
            return false;
        }
        if (!animal.getCodigo().matches("[a-zA-Z0-9]+")) {
            return false;
        }
        animales.add(animal);
        notificarObservadores();
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
        notificarObservadores();
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
        notificarObservadores();
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
            if (familia.getCedula().trim().equalsIgnoreCase(cedula.trim())) {
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
            if (empresa.getRut().trim().equalsIgnoreCase(rut.trim())) {
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

    public int getProximoNumeroAdopcion() {
        return proximoNumeroAdopcion;
    }

    // validacion aux
    private boolean textoValido(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    //métodos de adopciones
    public Adopcion realizarAdopcion(String codigoAnimal, String cedulaFamilia, String comentarios) {

        if (codigoAnimal == null || cedulaFamilia == null || comentarios == null) {
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
        String comentariosLimpios = comentarios.trim();

        Adopcion adopcion = new Adopcion(proximoNumeroAdopcion, animalIngresado, familiaIngresada, comentariosLimpios);
        proximoNumeroAdopcion++;

        animalIngresado.setEstado(ENTREGADO);
        adopciones.add(adopcion);

        notificarObservadores();

        return adopcion;
    }

    public boolean anularAdopcion(int numero) {
        Adopcion adopcionIngresada = buscarAdopcion(numero);
        if (adopcionIngresada == null) {
            return false;
        }

        Animal animal = adopcionIngresada.getAnimal();
        animal.setEstado(NO_DISPONIBLE);
        adopciones.remove(adopcionIngresada);

        notificarObservadores();

        return true;
    }

    public boolean cambiarEstadoAnimal(String codigo) {

        Animal animalIngresado = buscarAnimal(codigo);
        if (animalIngresado == null
                || ENTREGADO.equals(animalIngresado.getEstado())) {
            return false;
        }
        if (animalIngresado.getEstado().equals(DISPONIBLE)) {
            animalIngresado.setEstado(NO_DISPONIBLE);
        } else {
            animalIngresado.setEstado(DISPONIBLE);
        }

        notificarObservadores();

        return true;
    }

    public Donacion registrarDonacion(String rutEmpresa, int tipo,
            double cantidadKilos) {

        Empresa empresa = buscarEmpresa(rutEmpresa);

        if (empresa == null) {
            return null;
        }

        if (tipo < 1 || tipo > 4) {
            return null;
        }

        if (!Double.isFinite(cantidadKilos) || cantidadKilos <= 0) {
            return null;
        }

        Donacion donacion = new Donacion(
                proximoNumeroDonacion, empresa, tipo, cantidadKilos);

        donaciones.add(donacion);
        proximoNumeroDonacion++;

        notificarObservadores();

        return donacion;
    }

    public double getStockPorTipo(int tipo) {

        double total = 0;

        for (Donacion donacion : donaciones) {
            if (donacion.getTipo() == tipo) {
                total += donacion.getCantidadKilos();
            }
        }

        return total;
    }

    public int cantidadAdopcionesFamilia(String cedulaFamilia) {

        Familia familiaIngresada = buscarFamilia(cedulaFamilia);

        if (familiaIngresada == null) {
            return 0;
        }

        int cant = 0;
        for (Adopcion adopcion : adopciones) {
            if (adopcion.getFamilia().equals(familiaIngresada)) {
                cant++;
            }
        }

        return cant;
    }

    public ArrayList<Familia> getFamiliaOrdenadasPorCantAdopciones() {
        ArrayList<Familia> ordenadas = getFamilias();

        ordenadas.sort((f1, f2) -> {
            int cant1 = cantidadAdopcionesFamilia(f1.getCedula());
            int cant2 = cantidadAdopcionesFamilia(f2.getCedula());
            int comp = Integer.compare(cant2, cant1);
            if (comp != 0) {
                return comp;
            }
            return f1.getApellido().compareToIgnoreCase(f2.getApellido());
        });

        return ordenadas;
    }

    public ArrayList<Familia> getFamiliaOrdenadasPorApellido() {
        ArrayList<Familia> ordenadas = getFamilias();

        ordenadas.sort((f1, f2) -> {
            int comp = f1.getApellido().compareToIgnoreCase(f2.getApellido());
            if (comp != 0) {
                return comp;
            }
            return f1.getCedula().compareToIgnoreCase(f2.getCedula());
        });

        return ordenadas;
    }

    public ArrayList<Donacion> getDonacionesOrdenadas() {
        ArrayList<Donacion> ordenadas = getDonaciones();

        ordenadas.sort((f1, f2) -> {
            return Integer.compare(f1.getNumero(), f2.getNumero());
        });

        return ordenadas;
    }

    //Contadores
    public int getCantidadAnimalesTotales() {
        return animales.size();
    }

    public int getCantidadAdopciones() {
        return adopciones.size();
    }

    public int getCantidadEmpresas() {
        return empresas.size();
    }

    public int getCantidadAnimalesDisponibles() {

        int cant = 0;
        for (Animal animal : animales) {
            if (DISPONIBLE.equals(animal.getEstado())) {
                cant++;
            }
        }
        return cant;
    }

    public int getCantidadAnimalesEntregados() {

        int cant = 0;
        for (Animal animal : animales) {
            if (ENTREGADO.equals(animal.getEstado())) {
                cant++;
            }
        }
        return cant;
    }

    public int getCantidadAnimalesNoDisponibles() {

        int cant = 0;
        for (Animal animal : animales) {
            if (NO_DISPONIBLE.equals(animal.getEstado())) {
                cant++;
            }
        }
        return cant;
    }

// observer
    public void agregarObservador(ObservadorSistema observador) {

        if (observadores == null) {
            observadores = new ArrayList<>();
        }

        if (observador != null && !observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    public void eliminarObservador(ObservadorSistema observador) {

        if (observadores != null) {
            observadores.remove(observador);
        }
    }

    private void notificarObservadores() {

        if (observadores != null) {

            ArrayList<ObservadorSistema> copia
                    = new ArrayList<>(observadores);

            for (ObservadorSistema observador : copia) {
                observador.actualizar(this);
            }
        }
    }

    public void recuperarDatos(Sistema datos) {

        animales = datos.getAnimales();
        familias = datos.getFamilias();
        empresas = datos.getEmpresas();
        adopciones = datos.getAdopciones();
        donaciones = datos.getDonaciones();

        proximoNumeroAdopcion = datos.proximoNumeroAdopcion;
        proximoNumeroDonacion = datos.proximoNumeroDonacion;

        notificarObservadores();
    }

}
