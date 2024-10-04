package JocNau;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class iHall extends NPC {
    private HashMap<String, String> itemLocations; // Mapa que contiene los objetos y sus ubicaciones

    public iHall() {
        super("iHall", "Hola, puedo decirte dónde están los objetos si lo necesitas.");
        // Inicializar el mapa con las ubicaciones correctas de los objetos
        itemLocations = new HashMap<>();
        itemLocations.put("Tarjeta", "Oficines");
        itemLocations.put("Traje", "Vestuari");
        itemLocations.put("Linterna", "Cocina");
        itemLocations.put("Herramienta", "Tallers");
        itemLocations.put("Donut", "Menjador");
    }

    // Método para interactuar con el jugador y preguntar qué objeto del inventario necesita
    public void askForObject(Player player) {
        // Mostrar el inventario del jugador
        if (player.getInventory().isEmpty()) {
            System.out.println(getName() + " dice: No tienes ningún objeto en tu inventario.");
            return;
        }

        System.out.println(getName() + " dice: ¿Qué objeto de tu inventario necesitas?");
        player.showInventory(); // Mostrar el inventario del jugador

        // Capturar la entrada del jugador para saber qué objeto necesita
        Scanner scanner = new Scanner(System.in);
        String itemName = scanner.nextLine();

        // Verificar si el jugador tiene el objeto en su inventario
        boolean found = false;
        for (Item item : player.getInventory()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                found = true;
                giveLocation(itemName); // Proporcionar la ubicación del objeto
                break;
            }
        }

        if (!found) {
            System.out.println(getName() + " dice: No tienes ese objeto en tu inventario.");
        }
    }

    // Método para proporcionar la ubicación del objeto (50% de equivocarse)
    public void giveLocation(String itemName) {
        Random random = new Random();
        boolean correct = random.nextBoolean(); // Genera true o false (50% de probabilidad)

        if (itemLocations.containsKey(itemName)) {
            if (correct) {
                // Dar la ubicación correcta
                System.out.println(getName() + " dice: El " + itemName + " está en la sala " + itemLocations.get(itemName) + ".");
            } else {
                // Dar una ubicación incorrecta (elegir otra ubicación aleatoria)
                Object[] rooms = itemLocations.values().toArray(); // Array con las ubicaciones
                String wrongRoom = (String) rooms[random.nextInt(rooms.length)];
                System.out.println(getName() + " dice: El " + itemName + " está en la sala " + wrongRoom + ". (¿Seguro?)");
            }
        } else {
            System.out.println(getName() + " dice: No sé dónde está ese objeto.");
        }
    }
}
