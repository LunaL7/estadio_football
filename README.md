# estadio_baseball

## Coasa que Faltan Hacer (codigo funciona corriendo el main)

-Commentar codigo

-Verificar History con las instrucciones creo que esta mal(tambien les faltaria documentar proccesos creo que hay cosas que guardan)

 Markup : -Probar codigo
            - Asegurar que esta haciendo lo que tiene que hacer
            - Si un cliente ya existe lo tiene que saber la maquina
            - que pasa si cancelo cuando no tienen reservacion
            - que paso si reservo cuando no hay espacio
            - no se asegurar que sea robusto

-Leer rubica y instruciones a ver si cubrimos todo

-Refinar mas los mensajes de la consola 

-Limpiar/Refactor codigo innecesario (cambiar los nombres de methodos,valores,etc para que tenga todo sentido)

## Bono si Hay Tiempo

-Puede ser que añadir support para que coja un csv de clientes y reservaciones(para que se acuerde)

////

Este programa esta disenado para poder reservar una silla en un estadio. El programa deja a un cliente escoger su silla preferida de tres secciones,Field Level, Main Level, Grandstand Level,verifica la disponibilidad del asiento y reserva la silla. Adiconal a esto el sistema cancela reservaciones y asigna esas sillas a un liebte en lista de espera.

El programa utiliza para organizar sus asientos un hashSet que los divide por nivel, las reservaciones son manejadas por un Hashmap y esto asocia el cliente con la silla. Las listas de espera maneja los clientes esperando por un asiento y le asigna uno cuando se convierte disponible. El hisporial es manejado por un Linked list


Integrantes del grupo:
Luis Luna
Alvin Gutierrez
Noel Alonso