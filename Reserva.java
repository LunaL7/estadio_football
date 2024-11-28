import java.util.*;

/**
 * La clase Reserva administra las reservas de asientos del estadio. 
 * Incluye funcionalidades para inicializar asientos, gestionar disponibilidad, 
 * realizar reservas, cancelar reservas y manejar listas de espera.
 * 
 * Aqui se utilizo un hash set para las diferentes secciones del estadio, fieldLevel, mainLevel, grandstandLevel.
 * Se utilizo un linked list para la lista de espera, los clientes se agregan a la cola si no hay sillas disponibles.
 * Se utilizo un hashmap para las reservaciones que vincula el cliente con su asiento que desea reservar.
 * Se utilizo un linked list para el historial para poder tener una lista de los clientes atendidos.
 */
public class Reserva {
    private Set<Asiento> fieldLevel;
    private Set<Asiento> mainLevel;
    private Set<Asiento> grandstandLevel;

    private Queue<Cliente> listaEsperaField;
    private Queue<Cliente> listaEsperaMain;
    private Queue<Cliente> listaEsperaGrandstand;

    private HashMap<Cliente, List<Asiento>> reservaciones;
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
                Asiento asiento = new Asiento(tipo.getNombre(), fila, numero);
                seccion.add(asiento);
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

            List<Asiento> reservasCliente = reservaciones.get(cliente);
            if (reservasCliente == null) {
                reservasCliente = new ArrayList<>();
                reservaciones.put(cliente, reservasCliente);
            }
            reservasCliente.add(asiento);

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

        if(seccion.equals("1")){ seccionSet = fieldLevel; }
        else if(seccion.equals("2")){ seccionSet = mainLevel; }
        else if(seccion.equals("3")){ seccionSet = grandstandLevel; }
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
    public void cancelarReservacion(Cliente cliente, Asiento asiento) {
        List<Asiento> reservasCliente = reservaciones.get(cliente);

        if (reservasCliente != null && reservasCliente.contains(asiento)) {
            reservasCliente.remove(asiento);
            asiento.cancelReser();

            historial.add("Cancelación: " + cliente.getNombre() + " canceló su reservación para el asiento " + asiento.getNumero());
            System.out.println("La reservación ha sido cancelada.");

            moverDeListaEspera();
        } else {
            System.out.println("No se encontró la reservación para el cliente en ese asiento.");
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

    public boolean tieneReserva(Cliente cliente){
        if(reservaciones.containsKey(cliente)){
            return true;
        }
        return false;
    }

    public List<Asiento> obtenerReservas(Cliente cliente) {
        return reservaciones.get(cliente);
    }

    public int calcularCostoTotal(Cliente cliente) {
        List<Asiento> reservasCliente = reservaciones.get(cliente);
        int total = 0;

        if (reservasCliente != null) {
            for (Asiento asiento : reservasCliente) {
                if(asiento.getSeccion().equals("Field Level")){
                    total += 300;
                }
                if(asiento.getSeccion().equals("Main Level")){
                    total += 120;
                }
                if(asiento.getSeccion().equals("Grandstand Level")) {
                    total += 45;
                }
            }
        }

        return total;
    }
}
