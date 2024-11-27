import java.util.*;

/**
 * La clase Estadio sirve como interfaz principal para las operaciones relacionadas con las reservas de asientos en el estadio.
 */
public class Estadio{
    private Reserva reservaManager;
    
    /**
     * Constructor de la clase de estadio.
     */
    public Estadio(){
        //inicializar Asientos(fieldLevel, mainLevel, grandstandLevel); nose la real

        reservaManager = new Reserva();
    }

    /**
     * Muestra la disponiblilidad de los asientos.
     */
    public void mostrarDisponibilidad(){
        reservaManager.disponibilidad();
    }

    /***
     * Realiza la reservacion en la seccion deseada por el cliente.
     * @param cliente cliente que va a reservar la silla
     * @param seccion la seccion preferida por el cliente
     */
    public void hacerReserva(Cliente cliente, String seccion){
        reservaManager.reservar(cliente, seccion);
    }

    /**
     * Cancela la reservacion del cliente.
     * @param cliente cliente a cancelar la silla
     */
    public void cancelarReserva(Cliente cliente){
        reservaManager.cancelarReservacion(cliente);
    }
}