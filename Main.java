import java.util.Scanner;

/**
 * Clase principal que gestiona la reservacion de una silla de un estadio.
 * Permite ver la disponiblilidad, reservar y cancelar de las sillas
 */
public class Main{

    /**
     * Metodo principal que inicializa el sistema de reservaciones
     * @param args 
     */
    public static void main(String[] args) { // literalmente esto es chatgpt NO DEJAR ASI
        Estadio estadio = new Estadio();
        Scanner scanner = new Scanner(System.in);

        // Crear cliente
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el correo del cliente: ");
        String correo = scanner.nextLine();
        System.out.print("Ingrese el teléfono del cliente: ");
        String telefono = scanner.nextLine();
        
        Cliente cliente = new Cliente(nombre, correo, telefono);

        // Interacción con el sistema
        while (true) {
            //Opciones
            System.out.println("\nOpciones:");
            //Ver disponibilidad de asientos
            System.out.println("1. Ver disponibilidad de asientos");
            //Hacer una reserva
            System.out.println("2. Hacer una reserva");
            // Cancelar una reserva
            System.out.println("3. Cancelar una reserva");
            //Salir
            System.out.println("4. Salir");
            //Ingrese una opción
            System.out.println("Ingrese una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer

            switch (opcion) {
                case 1:
                    estadio.mostrarDisponibilidad();
                    break;
                case 2:
                    System.out.print("Ingrese la sección para reservar (Field Level, Main Level, Grandstand Level): ");
                    String seccion = scanner.nextLine();
                    estadio.hacerReserva(cliente, seccion);
                    break;
                case 3:
                    estadio.cancelarReserva(cliente);
                    break;
                case 4:
                    System.out.println("¡Gracias por usar el sistema!");
                    return;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }
}
