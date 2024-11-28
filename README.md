# estadio_baseball

Integrantes del grupo:
Luis Luna
Alvin Gutierrez
Noel Alonso
////

Este programa esta disenado para poder reservar una silla en un estadio. El programa deja a un cliente escoger su silla preferida de tres secciones,Field Level, Main Level, Grandstand Level,verifica la disponibilidad del asiento y reserva la silla. Adiconal a esto el sistema cancela reservaciones y asigna esas sillas a un cliente en lista de espera.

El programa utiliza para organizar sus asientos un hashSet que los divide por nivel, las reservaciones son manejadas por un Hashmap y esto asocia el cliente con la silla. Las listas de espera maneja los clientes esperando por un asiento y le asigna uno cuando se convierte disponible. El historial es manejado por un Linked list
