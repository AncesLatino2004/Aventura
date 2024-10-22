package JocNau;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy {
    private String name;
    private int currentRoom;
    private boolean calmado; // Variable para determinar si el enemigo está calmado
    private Map m = new Map();
    private Player player = new Player("Hero", m.getRoom(8));

    public Enemy(String name, int startRoom) {
        this.name = name;
        this.currentRoom = startRoom;
        this.calmado = false; // Por defecto, el enemigo no está calmado
    }

    public String getName() {
        return name;
    }

    public int getCurrentRoom() {
        return currentRoom;
    }

    public boolean isCalmado() {
        return calmado;
    }

    public void setCalmado(boolean calmado) {
        this.calmado = calmado;
    }

    // Método para mover al enemigo basado en la dirección
    public void moveEnemy(int direction, Map map) {
        if (!calmado) { // Si el enemigo no está calmado, puede moverse
            Room room = map.getRoom(currentRoom);
            int nextRoomIndex = room.getExit(direction); // Obtener la salida según la dirección

            if (nextRoomIndex != -1) {
                currentRoom = nextRoomIndex; // Actualizar la habitación actual del enemigo
                System.out.println(name + " se ha movido a: " + map.getRoom(currentRoom).getName());
            } else {
                System.out.println(name + " no puede moverse en esa dirección.");
            }
        } else {
            System.out.println(name + " está calmado y no se moverá más.");
        }
    }

    // Método de movimiento aleatorio (si es necesario mantenerlo)
    public void moveRandomly(Map map) {
        if (!calmado) { // Si el enemigo no está calmado, puede moverse aleatoriamente
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
        } else {
            System.out.println(name + " está calmado y no se moverá más.");
        }
    }

    public void atacar() {
        if (player.hasDonut()) { // Verificar si el jugador tiene un Donut
            System.out.println("Gonzalin se ha distraído con los Donuts que llevas y no se va a mover más.");
            setCalmado(true); // Calmar a Gonzalin si recibe el Donut
        } else {
            System.out.println("¡Gonzalin se te echa encima y te mata!");
            GameOver(); // Llamar al método de Game Over
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
