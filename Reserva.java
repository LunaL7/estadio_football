import java.util.*;

public class Reserva {
    private Set<Asiento> fieldLevel;
    private Set<Asiento> mainLevel;
    private Set<Asiento> grandstandLevel;

    private Queue<Cliente> listaEsperaField;
    private Queue<Cliente> listaEsperaMain;
    private Queue<Cliente> listaEsperaGrandstand;

    private HashMap<Cliente, Asiento> reservaciones;
    private LinkedList<String> historial;

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

    private void inicializarAsientos(Set<Asiento> seccion, Asiento.TipoAsiento tipo) {
        for (int fila = 1; fila <= tipo.getFilas(); fila++) {
            for (int numero = 1; numero <= tipo.getSillasPorFila(); numero++) {
                seccion.add(new Asiento(tipo.getNombre(), fila, numero, tipo));
            }
        }
    }

    public void disponibilidad(){
        System.out.println("Disponibilidad de Asientos por Secciones: ");
        System.out.println("Field -> " + disponibilidadHelper(fieldLevel));
        System.out.println("Main -> " + disponibilidadHelper(mainLevel));
        System.out.println("Grandstand -> " + disponibilidadHelper(grandstandLevel));
    }
    private int disponibilidadHelper(Set<Asiento> asientos){
        int count = 0;

        for(Asiento asiento : asientos){
            if(!asiento.isreservado()){ count++; }
        }

        return count;
    }

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
    private Asiento reservarHelper(String seccion){
        Set<Asiento> seccionSet;

        if(seccion == "Fiel Level"){ seccionSet = fieldLevel; }
        else if(seccion == "Main Level"){ seccionSet = mainLevel; }
        else if(seccion == "Grandstand Level"){ seccionSet = grandstandLevel; }
        else { return null;}

        for (Asiento asiento : seccionSet) {
            if (!asiento.isreservado()) { return asiento; }
        }

        return null;
    }

    private void listaEspera(Cliente cliente, String seccion){
        if(seccion == "Fiel Level"){
            listaEsperaField.add(cliente);
        }
        else if(seccion == "Main Level"){
            listaEsperaMain.add(cliente);
        }
        else if(seccion == "Grandstand Level"){
            listaEsperaGrandstand.add(cliente);
        }
        System.out.println("Agregado a la lista de espera.");
    }

    public void cancelarReservacion(Cliente cliente) {
        Asiento asiento = reservaciones.remove(cliente);

        if (asiento != null) {
            asiento.cancelReser();
            historial.add("Cancelación: " + cliente.getNombre() + " canceló su reservación.");
            moverDeListaEspera();
            System.out.println("Reservación cancelada para " + cliente.getNombre());
        }
    }

    private void moverDeListaEspera() {
        if (!listaEsperaField.isEmpty()) {
            Cliente cliente = listaEsperaField.poll();
            reservar(cliente, "Field Level");
        } else if (!listaEsperaMain.isEmpty()) {
            Cliente cliente = listaEsperaMain.poll();
            reservar(cliente, "Main Level");
        } else if (!listaEsperaGrandstand.isEmpty()) {
            Cliente cliente = listaEsperaGrandstand.poll();
            reservar(cliente, "Grandstand Level");
        }
    }
}
