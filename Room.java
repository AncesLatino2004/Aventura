package JocNau;

import java.util.ArrayList;

public class Room {
    private String name;
    private int roomNumber;
    private String description;
    private int[] exits; // Array de 4 posiciones: [arriba, abajo, izquierda, derecha]
    private ArrayList<Item> items; // Lista de objetos en la habitación

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        exits = new int[4]; // Inicializar las salidas
        for (int i = 0; i < exits.length; i++) {
            exits[i] = -1; // Sin salidas por defecto
        }
        items = new ArrayList<>(); // Inicializar la lista de objetos
    }

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    
    public int getRoomNumber() {
        return roomNumber;
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Método para establecer las salidas de la sala
    public void setExits(int up, int down, int left, int right) {
        exits[0] = up; // Salida hacia arriba
        exits[1] = down; // Salida hacia abajo
        exits[2] = left; // Salida hacia la izquierda
        exits[3] = right; // Salida hacia la derecha
    }

    // Método para obtener todas las salidas de la sala
    public int[] getExits() {
        return exits;
    }

    // Nuevo método para obtener la salida según la dirección dada
    public int getExit(int direction) {
        switch (direction) {
            case 1: // Arriba
                return exits[0];
            case 2: // Abajo
                return exits[1];
            case 3: // Izquierda
                return exits[2];
            case 4: // Derecha
                return exits[3];
            default:
                return -1;
        }
    }


    // Método para agregar un objeto a la habitación
    public void addItem(Item item) {
        items.add(item);
    }

    // Método para obtener los objetos en la habitación
    public ArrayList<Item> getItems() {
        return items;
    }

    // Método para eliminar un objeto de la habitación
    public void removeItem(Item item) {
        items.remove(item);
    }
}
