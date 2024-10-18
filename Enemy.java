package JocNau;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy {
    private String name;
    private int currentRoom;
     private Map m = new Map();
    private Player player = new Player("Hero", m.getRoom(8));
    
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
    public void moveEnemy(int direction, Map map) {
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

    public void atacar() {
    	System.out.println();
    	if(player.hasDonut() == true) {
    		System.out.println("Gonzalin se ha distraido con los Donuts que llevas encima y no se va a mover mas.");
    	}
    	else {
    		System.out.println("Gonzalin se te hecha encima y te mata");
    		GameOver();
    	}
    	
    }
    public void GameOver() {
    	System.out.println("███▀▀▀██┼███▀▀▀███┼███▀█▄█▀███┼██▀▀▀\n" +
                "██┼┼┼┼██┼██┼┼┼┼┼██┼██┼┼┼█┼┼┼██┼██┼┼┼\n" +
                "██┼┼┼▄▄▄┼██▄▄▄▄▄██┼██┼┼┼▀┼┼┼██┼██▀▀▀\n" +
                "██┼┼┼┼██┼██┼┼┼┼┼██┼██┼┼┼┼┼┼┼██┼██┼┼┼\n" +
                "███▄▄▄██┼██┼┼┼┼┼██┼██┼┼┼┼┼┼┼██┼██▄▄▄\n" +
                "┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                "███▀▀▀███┼▀███┼┼██▀┼██▀▀▀┼██▀▀▀▀██▄┼\n" +
                "██┼┼┼┼┼██┼┼┼██┼┼██┼┼██┼┼┼┼██┼┼┼┼┼██┼\n" +
                "██┼┼┼┼┼██┼┼┼██┼┼██┼┼██▀▀▀┼██▄▄▄▄▄▀▀┼\n" +
                "██┼┼┼┼┼██┼┼┼██┼┼█▀┼┼██┼┼┼┼██┼┼┼┼┼██┼\n" +
                "███▄▄▄███┼┼┼─▀█▀┼┼─┼██▄▄▄┼██┼┼┼┼┼██▄");
   
    }
}
