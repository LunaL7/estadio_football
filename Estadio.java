import java.util.*;

public class Estadio{
    private Reserva reservaManager;
    
    public Estadio(){
        //inicializar Asientos(fieldLevel, mainLevel, grandstandLevel); nose la real

        reservaManager = new Reserva();
    }

    public void mostrarDisponibilidad(){
        reservaManager.disponibilidad();
    }

    public void hacerReserva(Cliente cliente, String seccion){
        reservaManager.reservar(cliente, seccion);
    }

    public void cancelarReserva(Cliente cliente){
        reservaManager.cancelarReservacion(cliente);
    }
}