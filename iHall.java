package JocNau;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class iHall extends NPC {
    private Map<String, String> itemLocations; // Mapa que contiene los objetos y sus ubicaciones

    public iHall(String name, String dialogue) {
        super(name, dialogue);
        // Inicializar el mapa con las ubicaciones correctas de los objetos
        itemLocations = new HashMap<>();
        itemLocations.put("Tarjeta", "Oficines");
        itemLocations.put("Traje", "Vestuari");
        itemLocations.put("Linterna", "Cocina");
        itemLocations.put("Herramienta", "Tallers");
        itemLocations.put("Donut", "Menjador");
    }

    // Método para interactuar con el jugador y preguntar qué objeto necesita
    public void askForObject(String itemName) {
        System.out.println(getName() + " pregunta: ¿Qué objeto necesitas?");
        giveLocation(itemName); // Proporcionar la ubicación del objeto
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
