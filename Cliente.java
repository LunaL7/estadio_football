/**
 * Representa el cliente y su informacion necesaria para reservar un asiento.
 */
public class Cliente {
    String nombre;   //Nomnre del cliente
    String correo;   //Correo electronico del cliente
    String telefono; //Telefono del cliente

    /**
     * Constructor de Cliente. Tiene parametros para nombre, correo, y telefono.
     * @param nombre nombre del cliente
     * @param correo correo electronico del cliente
     * @param telefono telefono del cliente
     */
    public Cliente(String nombre, String correo, String telefono){
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }

    /**
     * 
     * @return devuelve el nombre del cliente
     */
    public String getNombre(){
        return nombre;
    }
    /**
     * 
     * @return devuelve el correo electronico del cliente
     */
    public String getCorreo(){
        return correo;
    }
    /**
     * 
     * @return devuelve el telefono del cliente
     */
    public String getTelefono(){
        return telefono;
    }

    /**
     * Establece el nombre del cliente.
     * @param nombre nombre del cliente
     */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    /**
     * Establece el correo electronico del cliente.
     * @param correo correo electronico del cliente
     */
    public void setCorreo(String correo){
        this.correo = correo;
    }
    /**
     * Establece el telefono del cliente.
     * @param telefono telefono del cliente
     */
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }


    
}
