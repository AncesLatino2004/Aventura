package JocNau;

import java.util.ArrayList;

public class Room {
    private String name;
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
    public int getExit(String direction) {
        switch (direction.toLowerCase()) {
            case "up":
                return exits[0]; // Salida hacia arriba
            case "down":
                return exits[1]; // Salida hacia abajo
            case "left":
                return exits[2]; // Salida hacia la izquierda
            case "right":
                return exits[3]; // Salida hacia la derecha
            default:
                return -1; // Si la dirección es inválida, devolver -1 (no hay salida)
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
