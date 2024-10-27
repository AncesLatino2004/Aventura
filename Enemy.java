package JocNau;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Enemy {
    private String name;
    private int currentRoom;
    private boolean calmado; // Variable per determinar si l'enemic està calmat
    private Map m = new Map();
    private Player player = new Player("Hero", m.getRoom(8));
    private Scanner scanner = new Scanner(System.in);
    private AdventureGame game;

    public Enemy(String name, int startRoom) {
        this.name = name;
        this.currentRoom = startRoom;
        this.calmado = false; // Per defecte, l'enemic no està calmat
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

    // Mètode per moure l'enemic basat en la direcció
    public void moveEnemy(int direction, Map map) {
        if (!calmado) { // Si l'enemic no està calmat, es pot moure
            Room room = map.getRoom(currentRoom);
            int nextRoomIndex = room.getExit(direction); // Obtenir la sortida segons la direcció

            if (nextRoomIndex != -1) {
                currentRoom = nextRoomIndex; // Actualitzar l'habitació actual de l'enemic
                System.out.println(name + " s'ha mogut a: " + map.getRoom(currentRoom).getName());
            } else {
                System.out.println(name + " no pot moure's en aquesta direcció.");
            }
        } else {
            System.out.println(name + " està calmat i no es mourà més.");
        }
    }

    // Mètode de moviment aleatori (si és necessari mantenir-lo)
    public void moveRandomly(Map map) {
        if (!calmado) { // Si l'enemic no està calmat, es pot moure aleatòriament
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
                System.out.println(name + " s'ha mogut a: " + map.getRoom(currentRoom).getName());
            } else {
                System.out.println(name + " no pot moure's.");
            }
        } else {
            System.out.println(name + " està calmat i no es mourà més.");
        }
    }

    public void atacar() {
        if (player.hasDonut()) { // Verificar si el jugador té un Donut
            System.out.println("Gonzalin s'ha distret amb els Donuts que portes i no es mourà més.");
            setCalmado(true); // Calmar a Gonzalin si rep el Donut
        } else {
            System.out.println("Gonzalin s'abalança sobre tu i et mata!");
            GameOver(); // Cridar el mètode de Game Over
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
        game.restartGame();
    }
}
