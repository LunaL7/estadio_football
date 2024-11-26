import java.util.*;

public class Estadio{
    private Reserva reservaManager;
    
    public Estadio(){
        Set<Asiento> fieldLevel = new HashSet<>();
        Set<Asiento> mainLevel = new HashSet<>();
        Set<Asiento> grandstandLevel = new HashSet<>();

        //inicializar Asientos(fieldLevel, mainLevel, grandstandLevel); nose la real

        reservaManager = new Reserva(fieldLevel, mainLevel, grandstandLevel);
    }
    // maybe inicializar los asientos

    //disponibilidad (literal simplemente llama lo que esta en Reserva y ya)

    //hacer reserva?

    //cancelar reserva??
}