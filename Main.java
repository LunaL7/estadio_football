import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

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
        boolean numeroValido = false;
        String telefono = "";

        // Crear cliente
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el correo del cliente: ");
        String correo = scanner.nextLine();

        while(!numeroValido){ //while loop asegura que entres un numero
            System.out.print("Ingrese el teléfono del cliente: ");
            telefono = scanner.nextLine();

            if(Pattern.matches("\\d{10}", telefono)){
                numeroValido = true;
            } 
            else{
                System.out.println("Telefono invalido. Ingrese un numero de telefono valido");
            }
        }
        
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
            //ver reservas
            System.out.println("4. Ver reservas");
            //Salir
            System.out.println("5. Salir");
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
            
                    int totalCost = estadio.calcularCostoTotal(cliente);
                    System.out.println("El costo total de la(s) reserva(s) es: $" + totalCost);
                    break;
                case "3":
                    if(estadio.tieneReserva(cliente)){
                        System.out.println("Cual deseas cancelar?");
                        List<Asiento> reservasCliente = estadio.obtenerReservas(cliente);

                        for (int i = 0; i < reservasCliente.size(); i++) {
                            Asiento asiento = reservasCliente.get(i);
                            System.out.println((i + 1) + ". Sección: " + asiento.getSeccion() + ", Asiento: " + asiento.getNumero());
                        }

                        System.out.println("Ingresa el número de la reserva que deseas cancelar: ");
                        int seleccion = scanner.nextInt();

                        if (seleccion > 0 && seleccion <= reservasCliente.size()) {
                            Asiento asientoSeleccionado = reservasCliente.get(seleccion - 1);
                            estadio.cancelarReservacion(cliente, asientoSeleccionado);
                        }
                        else{
                            System.out.println("Seleccion Invalida");
                        }
                    }
                    break;
                case "4":
                    if (estadio.tieneReserva(cliente)) {
                        List<Asiento> reservasCliente = estadio.obtenerReservas(cliente);
                        System.out.println("Tus reservas actuales son:");
                        for (Asiento asiento : reservasCliente) {
                            System.out.println("Sección: " + asiento.getSeccion() + ", Fila: " + asiento.getFila() + ", Asiento: " + asiento.getNumero());
                        }
            
                        int totalCostReservations = estadio.calcularCostoTotal(cliente);
                        System.out.println("El costo total de todas tus reservas es: $" + totalCostReservations);
                    } 
                    else {
                        System.out.println("No tienes reservas.");
                    }
                    break;
                case "5":
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
