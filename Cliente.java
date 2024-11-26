public class Cliente {
    String nombre;
    String correo;
    String telefono;

    //Constructor de Cliente. Tiene parametros para nombre, correo, y telefono
    public Cliente(String nombre, String correo, String telefono){
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }

    //getters
    public String getNombre(){
        return nombre;
    }
    public String getCorreo(){
        return correo;
    }
    public String getTelefono(){
        return telefono;
    }

    
}
