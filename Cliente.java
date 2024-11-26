public class Cliente {
    String nombre;
    String correo;
    String telefono;

    public Cliente(String nombre, String correo, String telefono){
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }

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
