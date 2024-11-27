/**
 * Esta clase representa las sillas del estadio y sus atributos de seccion, fila y numero.
 * Adicionalmente maneja el estatus de la reservacion y el tipo de seccion.
 */
public class Asiento{
 private String seccion;         // seccion del estadio
 private int fila;               // numero de fila de la silla 
 private int numero;             //numero de silla
 private boolean reservado;      //estatus de la reservacion 
 private TipoAsiento tipoAsiento;// indica donde esta localizado la silla

 /**
  * Enum que representa la seccion del asiento.
  */
 public enum TipoAsiento {
    FIELD_LEVEL("Field Level", 300, 10, 50), // 10 filas con 50 sillas por fila
    MAIN_LEVEL("Main Level", 120, 20, 50),  // 20 filas con 50 sillas por fila
    GRANDSTAND_LEVEL("Grandstand Level", 45, 40, 50); // 40 filas con 50 sillas por fila

    private String nombre; //nombre de la silla
    private int costo; //precio de la silla
    private int filas; //numero de filas en la seccion
    private int sillasPorFila; //numero de sillas en la fila

    /**
     * Constructor para el TipoAsiento enum.
     * @param nombre       Nombre del tipo de silla
     * @param costoCost    Costo del tipo de silla
     * @param filas        Numero de filas en la seccion
     * @param sillasPorFila Numero de sillas en la fila
    */
    TipoAsiento(String nombre, int costo, int filas, int sillasPorFila) {
        this.nombre = nombre;
        this.costo = costo;
        this.filas = filas;
        this.sillasPorFila = sillasPorFila;
    }

    /**
     * 
     * @return devuelve el nombre del tipo de silla
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * 
     * @return devuelve el costo de la silla
     */
    public int getCosto() {
        return costo;
    }

    /**
     * 
     * @return devuelve el numero de filas para ese tipo de silla
     */
    public int getFilas() {
        return filas;
    }

    /**
     * 
     * @return devuelve el numero de sillas en la fila para ese tipo de silla
     */
    public int getSillasPorFila() {
        return sillasPorFila;
    }

    /**
     * @return devuelve un string que tiene el costo,fila y sillas por fila
     */
    @Override
    public String toString() {
        return nombre + " - Costo: $" + costo + ", Filas: " + filas + ", Sillas por fila: " + sillasPorFila;
    }
}

/**
 * Constructor de asiento.
 * @param seccion seccion donde esta la silla
 * @param fila   numero de fila para la silla
 * @param numero numero de la silla
 * @param tipoasiento tipo de silla
 */
public Asiento(String seccion, int fila, int numero, TipoAsiento tipoasiento){
    this.seccion = seccion;         //seccion donde esta la silla
    this.fila = fila;               //numero de fila para la silla
    this.numero = numero;           //numero de la silla
    this.reservado = false;         //estatus de la reservacion
    this.tipoAsiento = tipoAsiento; //tipo de silla
}

/**
 * 
 * @return devuelve el tipo de asiento
 */
public TipoAsiento getTipoAsiento() {
    return tipoAsiento;
}

/**
 * 
 * @return devuelve la seccion donde esta la silla
 */
public String getSeccion() {
    return seccion;
}

/**
 * Establece la seccion de la silla.
 * @param seccion la seccion para setear
 */
public void setSeccion(String seccion) {
    this.seccion = seccion;
}

/**
 * 
 * @return devuelve la fila donde esta la silla
 */
public int getFila(){
    return fila;
}

/**
 * Establece la fila de la silla a una seccion.
 * @param fila fila para establecer
 */
public void setFila(int fila){
    this.fila = fila;
}

/**
 * 
 * @return devuelve el numero de la silla
 */
public int getNumero(){
    return numero;
}

/**
 * Establece el numero de la silla.
 * @param numero numero para la silla
 */
public void setNumero(int numero){
    this.numero = numero;
}

/**
 * @return devuelve true si esta reservado, false de lo contrario
 */
public boolean isreservado(){
    return reservado;
}

/**
 * Reserva la silla.
 */
public void reservar(){
    this.reservado = true;
}

/**
 * Cancela la reservacion.
 */
public void cancelReser(){
    this.reservado = false;
}

/**
 * devuelve la seccion, fila y numero de la silla.
 */
public String toString(){
    return "Seccion" + getSeccion() + "fila" + getFila() + "asiento" + getNumero();
}


}