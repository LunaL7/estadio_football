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
        boolean isInt = false;
        int tele = 0;

        // Crear cliente
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el correo del cliente: ");
        String correo = scanner.nextLine();
        while(!isInt){ //while loop asegura que entres un numero
            System.out.print("Ingrese el teléfono del cliente: ");
            String next = scanner.nextLine();
            try{
                tele = Integer.parseInt(next);
                isInt = true;
            } catch(NumberFormatException e){
                System.out.println("Telefono invalido. Ingrese un numero de telefono valido");
            }
        }
        
        Cliente cliente = new Cliente(nombre, correo, String.valueOf(tele));

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
            String opcion = scanner.next();
            scanner.nextLine();  // Limpiar buffer

            switch (opcion) {
                case "1":
                    estadio.mostrarDisponibilidad();
                    break;
                case "2":
                    System.out.print("Ingrese la sección para reservar (1 - Field Level, 2 - Main Level, 3 - Grandstand Level): ");
                    String seccion = scanner.nextLine();
                    estadio.hacerReserva(cliente, seccion);
                    break;
                case "3":
                    estadio.cancelarReserva(cliente);
                    break;
                case "4":
                    System.out.println("¡Gracias por usar el sistema!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }
}
