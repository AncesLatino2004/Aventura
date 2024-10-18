package JocNau;
import java.util.ArrayList;
import java.util.Arrays;

public class Map {
    private Room[] rooms;

    public Map() {
        rooms = new Room[10]; // Mapa de 10 habitaciones

        // Crear las habitaciones con su descripción
        rooms[0] = new Room("Vestuari", "Una habitación con taquillas y uniformes.");
        rooms[1] = new Room("Oficines", "La oficina donde se realizan todas las gestiones de la nave.");
        rooms[2] = new Room("Tallers", "Un taller lleno de herramientas.");
        rooms[3] = new Room("Cocina", "La cocina donde se prepara la comida.");
        rooms[4] = new Room("Commandament", "La sala de mando.");
        rooms[5] = new Room("Baños", "Los baños de la nave.");
        rooms[6] = new Room("Menjador", "El comedor para los empleados.");
        rooms[7] = new Room("Sortida Exterior", "La salida al exterior de la nave.");
        rooms[8] = new Room("Dormitori", "Un dormitorio donde descansar.");
        rooms[9] = new Room("Propulsors", "La sala de propulsores.");

        // Definir las conexiones: [arriba, abajo, izquierda, derecha]
        rooms[0].setExits(-1, 3, -1, 1);
        rooms[1].setExits(-1, 4, 0, 2);
        rooms[2].setExits(-1, 5, 1, -1);
        rooms[3].setExits(0, 6, -1, 4);
        rooms[4].setExits(1, 7, 3, 5);
        rooms[5].setExits(2, 8, 4, -1);
        rooms[6].setExits(3, -1, -1, 7);
        rooms[7].setExits(4, 9, 6, 8);
        rooms[8].setExits(5, -1, 7, -1);
        rooms[9].setExits(7, -1, -1, -1);
        
        rooms[0].addItem(new Item("Traje", "Sirve para salir a arreglar los propulsores", null));
        rooms[1].addItem(new Item("Tarjeta", "Sirve para abrir la taquilla", null));
        rooms[2].addItem(new Item("Herramienta", "Arregla los propulsores", null));
        rooms[3].addItem(new Item("Linterna", "Ilumina el taller", null));
        rooms[6].addItem(new Item("Donut", "Para al Gonzalin", null));
    }

    // Método para obtener una habitación específica por índice
    public Room getRoom(int index) {
        if (index >= 0 && index < rooms.length) {
            return rooms[index];
        } else {
            return null;
        }
    }

    // Método para devolver todas las habitaciones como una lista
    public ArrayList<Room> getAllRooms() {
        return new ArrayList<>(Arrays.asList(rooms)); // Convertir el array a ArrayList y devolverlo
    }
    
}
