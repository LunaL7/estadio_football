import java.util.*;

/**
 * La clase Reserva administra las reservas de asientos del estadio. 
 * Incluye funcionalidades para inicializar asientos, gestionar disponibilidad, 
 * realizar reservas, cancelar reservas y manejar listas de espera.
 */
public class Reserva {
    private Set<Asiento> fieldLevel;
    private Set<Asiento> mainLevel;
    private Set<Asiento> grandstandLevel;

    private Queue<Cliente> listaEsperaField;
    private Queue<Cliente> listaEsperaMain;
    private Queue<Cliente> listaEsperaGrandstand;

    private HashMap<Cliente, Asiento> reservaciones;
    private LinkedList<String> historial;

    /**
     * Constructor de la clase de reserva.
     */
    public Reserva(){
        this.listaEsperaField = new LinkedList<>();
        this.listaEsperaMain = new LinkedList<>();
        this.listaEsperaGrandstand = new LinkedList<>();
        this.historial = new LinkedList<>();

        this.reservaciones = new HashMap<>();
        this.fieldLevel = new HashSet<>();
        this.mainLevel = new HashSet<>();
        this.grandstandLevel = new HashSet<>();
        
        inicializarAsientos(this.fieldLevel, Asiento.TipoAsiento.FIELD_LEVEL);
        inicializarAsientos(this.mainLevel, Asiento.TipoAsiento.MAIN_LEVEL);
        inicializarAsientos(this.grandstandLevel, Asiento.TipoAsiento.GRANDSTAND_LEVEL);
    }

    /**
     * Inicia los Asientos de una seccion.
     * @param seccion seccion de sillas
     * @param tipo tipo de asiento de la seccion
     */
    private void inicializarAsientos(Set<Asiento> seccion, Asiento.TipoAsiento tipo) {
        for (int fila = 1; fila <= tipo.getFilas(); fila++) {
            for (int numero = 1; numero <= tipo.getSillasPorFila(); numero++) {
                seccion.add(new Asiento(tipo.getNombre(), fila, numero, tipo));
            }
        }
    }

    /**
     * Muestra la disponibilidad de asientos.
     */
    public void disponibilidad(){
        System.out.println("Disponibilidad de Asientos por Secciones: ");
        System.out.println("Field -> " + disponibilidadHelper(fieldLevel));
        System.out.println("Main -> " + disponibilidadHelper(mainLevel));
        System.out.println("Grandstand -> " + disponibilidadHelper(grandstandLevel));
    }

    /**
     * Cuenta la cantidad de sillas disponibles.
     * @param asientos asientos de la seccion
     * @return  devuelve el numero de asientos disponibles
     */
    private int disponibilidadHelper(Set<Asiento> asientos){
        int count = 0;

        for(Asiento asiento : asientos){
            if(!asiento.isreservado()){ count++; }
        }

        return count;
    }

/**
 * Realiza una reservacion para un cliende de su seccion deseada.
 * @param cliente cliente reservando una silla
 * @param seccion seccion de la silla a reservar
 */
    public void reservar(Cliente cliente, String seccion){
        Asiento asiento = reservarHelper(seccion);

        if(asiento != null){
            asiento.reservar();
            reservaciones.put(cliente, asiento);
            historial.add("Reservacion: " + cliente.getNombre() + " --> " + asiento.getSeccion() + " asiento " + asiento.getNumero());// mal hecho creo tengo que volver a leer las instrucciones
            System.out.println("Reservado exitosamente");
        }
        else{
            //preguntar si lista espera 
            listaEspera(cliente, seccion);
        }
    }

    /**
     * Encuentra un asiento en una seccion para reservar.
     * @param seccion seccion donde se va a reservar una silla
     * @return devuelve un asiento disponible, null si no hay espacio
     */
    private Asiento reservarHelper(String seccion){
        Set<Asiento> seccionSet;

        if(seccion == "1"){ seccionSet = fieldLevel; }
        else if(seccion == "2"){ seccionSet = mainLevel; }
        else if(seccion == "3"){ seccionSet = grandstandLevel; }
        else { return null;}

        for (Asiento asiento : seccionSet) {
            if (!asiento.isreservado()) { return asiento; }
        }

        return null;
    }

    /**
     * Anade un cliente a la lista de espera a su seccion deseada.
     * @param cliente cliente reservando la silla
     * @param seccion seccion deseada por el cliente para reservar
     */
    private void listaEspera(Cliente cliente, String seccion){
        if(seccion == "1"){
            listaEsperaField.add(cliente);
        }
        else if(seccion == "2"){
            listaEsperaMain.add(cliente);
        }
        else if(seccion == "3"){
            listaEsperaGrandstand.add(cliente);
        }
        System.out.println("Agregado a la lista de espera.");
    }

    /**
     * Cancela la reservacion de un cliente y mueve un cliente de la lista de espera si aplica.
     * @param cliente cliente que quiere cancelar su reservacion
     */
    public void cancelarReservacion(Cliente cliente) {
        Asiento asiento = reservaciones.remove(cliente);

        if (asiento != null) {
            asiento.cancelReser();
            historial.add("Cancelación: " + cliente.getNombre() + " canceló su reservación.");
            moverDeListaEspera();
            System.out.println("Reservación cancelada para " + cliente.getNombre());
        }
    }

    /**
     * Mueve el primer cliente en la lista de espera a un asiento si es posible.
     */
    private void moverDeListaEspera() {
        if (!listaEsperaField.isEmpty()) {
            Cliente cliente = listaEsperaField.poll();
            reservar(cliente, "1");
        } else if (!listaEsperaMain.isEmpty()) {
            Cliente cliente = listaEsperaMain.poll();
            reservar(cliente, "2");
        } else if (!listaEsperaGrandstand.isEmpty()) {
            Cliente cliente = listaEsperaGrandstand.poll();
            reservar(cliente, "3");
        }
    }
}
