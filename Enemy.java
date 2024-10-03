package JocNau;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy {
    private String name;
    private int currentRoom; // La habitación actual en la que está el enemigo

    public Enemy(String name, int startRoom) {
        this.name = "Gonzalin";
        this.currentRoom = startRoom; // El enemigo comienza en una habitación específica
    }

    public String getName() {
        return name;
    }

    public int getCurrentRoom() {
        return currentRoom;
    }

    // Método para mover al enemigo a una habitación adyacente aleatoriamente
    public void moveRandomly(Map map) {
        Room room = map.getRoom(currentRoom); // Obtener la habitación actual del enemigo

        // Obtener todas las habitaciones adyacentes válidas (que no sean -1)
        List<Integer> validExits = new ArrayList<>();
        int[] exits = room.getExits();

        // Verificar cada dirección: [arriba, abajo, izquierda, derecha]
        for (int i = 0; i < exits.length; i++) {
            if (exits[i] != -1) {
                validExits.add(exits[i]);
            }
        }

        // Si hay habitaciones adyacentes válidas
        if (!validExits.isEmpty()) {
            Random random = new Random();
            int nextRoom = validExits.get(random.nextInt(validExits.size())); // Elegir una sala adyacente aleatoria
            currentRoom = nextRoom; // Actualizar la posición del enemigo
            System.out.println(name + " se ha movido a: " + map.getRoom(currentRoom).getName());
        } else {
            System.out.println(name + " no puede moverse porque no hay habitaciones adyacentes válidas.");
        }
    }
}
