public class Asiento{
 private String seccion;
 private int fila;
 private int numero;
 private boolean reservado;
 private TipoAsiento tipoAsiento;

 public enum TipoAsiento {
    FIELD_LEVEL("Field Level", 300, 10, 50), // 10 filas con 50 sillas por fila
    MAIN_LEVEL("Main Level", 120, 20, 50),  // 20 filas con 50 sillas por fila
    GRANDSTAND_LEVEL("Grandstand Level", 45, 40, 50); // 40 filas con 50 sillas por fila

    private String nombre;
    private int costo;
    private int filas;
    private int sillasPorFila;

    //Constructor
    TipoAsiento(String nombre, int costo, int filas, int sillasPorFila) {
        this.nombre = nombre;
        this.costo = costo;
        this.filas = filas;
        this.sillasPorFila = sillasPorFila;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCosto() {
        return costo;
    }

    public int getFilas() {
        return filas;
    }

    public int getSillasPorFila() {
        return sillasPorFila;
    }

    @Override
    public String toString() {
        return nombre + " - Costo: $" + costo + ", Filas: " + filas + ", Sillas por fila: " + sillasPorFila;
    }
}

public Asiento(String seccion, int fila, int numero, TipoAsiento tipoasiento){
    this.seccion = seccion;
    this.fila = fila;
    this.numero = numero;
    this.reservado = false;
    this.tipoAsiento = tipoAsiento; 
}

public TipoAsiento getTipoAsiento() {
    return tipoAsiento;
}

public String getSeccion() {
    return seccion;
}

public void setSeccion(String seccion) {
    this.seccion = seccion;
}

public int getFila(){
    return fila;
}

public void setFila(int fila){
    this.fila = fila;
}

public int getNumero(){
    return numero;
}
public void setNumero(int numero){
    this.numero = numero;
}

public boolean isreservado(){
    return reservado;
}

public void reservar(){
    this.reservado = true;
}

public void cancelReser(){
    this.reservado = false;
}

public String toString(){
    return "Seccion" + getSeccion() + "fila" + getFila() + "asiento" + getNumero();
}


}