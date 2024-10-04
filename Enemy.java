package JocNau;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy {
    private String name;
    private int currentRoom;

    public Enemy(String name, int startRoom) {
        this.name = name;
        this.currentRoom = startRoom;
    }

    public String getName() {
        return name;
    }

    public int getCurrentRoom() {
        return currentRoom;
    }

    // Método para mover al enemigo basado en la dirección
    public void moveEnemy(String direction, Map map) {
        Room room = map.getRoom(currentRoom);
        int nextRoomIndex = room.getExit(direction); // Obtener la salida según la dirección

        if (nextRoomIndex != -1) {
            currentRoom = nextRoomIndex; // Actualizar la habitación actual del enemigo
            System.out.println(name + " se ha movido a: " + map.getRoom(currentRoom).getName());
        } else {
            System.out.println(name + " no puede moverse en esa dirección.");
        }
    }

    // Método de movimiento aleatorio (si es necesario mantenerlo)
    public void moveRandomly(Map map) {
        Room room = map.getRoom(currentRoom);
        List<Integer> validExits = new ArrayList<>();
        int[] exits = room.getExits();

        for (int exit : exits) {
            if (exit != -1) {
                validExits.add(exit);
            }
        }

        if (!validExits.isEmpty()) {
            Random random = new Random();
            currentRoom = validExits.get(random.nextInt(validExits.size()));
            System.out.println(name + " se ha movido a: " + map.getRoom(currentRoom).getName());
        } else {
            System.out.println(name + " no puede moverse.");
        }
    }
}
