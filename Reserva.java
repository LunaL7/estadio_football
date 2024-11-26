import java.util.*;
import java.util.Scanner;

public class Reserva {
    private Set<Asiento> fieldLevel;
    private Set<Asiento> mainLevel;
    private Set<Asiento> grandstandLevel;

    private Queue<Cliente> listaEsperaField;
    private Queue<Cliente> listaEsperaMain;
    private Queue<Cliente> listaEsperaGrandstand;

    private HashMap<Cliente, Asiento> reservaciones;
    private LinkedList<String> historial;

    public Reserva(Set<Asiento> fieldLevel, Set<Asiento> mainLevel, Set<Asiento> grandstandLevel){
        this.fieldLevel = fieldLevel;
        this.mainLevel = mainLevel;
        this.grandstandLevel = grandstandLevel;

        this.listaEsperaField = new LinkedList<>();
        this.listaEsperaMain = new LinkedList<>();
        this.listaEsperaGrandstand = new LinkedList<>();
        this.historial = new LinkedList<>();

        this.reservaciones = new HashMap<>();
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
            if(!asiento.isReservado()){ count++; }
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
            if (!asiento.isReservado()) { return asiento; }
        }

        return null;
    }

    private void listaEspera(Cliente cliente, String seccion){

    }

    //cancelar

    //algo pa actualizar la lista de espera? como que moverlo pa actual lista
}
