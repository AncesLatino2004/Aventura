package JocNau;


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class iHall extends NPC {
    public iHall() {
        super("iHall", "Hola, puedo decirte dónde están los objetos si lo necesitas.");
    }

    // Método para interactuar con el jugador y preguntar qué objeto está buscando
    public void askForObject(Player player, Map map) {
        Scanner scanner = new Scanner(System.in);
        
        // Pedir al jugador el nombre del objeto que está buscando
        System.out.println(getName() + " dice: ¿Qué objeto estás buscando?");
        String itemName = scanner.nextLine();

        // Buscar el objeto en todas las habitaciones del mapa
        Room foundRoom = findItemInRooms(itemName, map);

        // Si se encuentra el objeto, se da la localización (50% de equivocarse)
        if (foundRoom != null) {
            giveLocation(itemName, foundRoom);
        } else {
            // Si no se encuentra el objeto, notificar que no se sabe dónde está
            System.out.println(getName() + " dice: No sé dónde está ese objeto.");
        }
    }

    // Método para buscar un objeto en todas las habitaciones del mapa
    private Room findItemInRooms(String itemName, Map map) {
        ArrayList<Room> rooms = map.getAllRooms(); // Obtener todas las habitaciones del mapa

        // Buscar en todas las habitaciones
        for (Room room : rooms) {
            for (Item item : room.getItems()) {
                if (item.getName().equalsIgnoreCase(itemName)) {
                    return room; // Retorna la habitación donde se encuentra el objeto
                }
            }
        }

        return null; // No se encontró el objeto
    }

    // Método para proporcionar la ubicación del objeto (50% de equivocarse)
    public void giveLocation(String itemName, Room correctRoom) {
        Random random = new Random();
        boolean correct = random.nextBoolean(); // Genera true o false (50% de probabilidad)

        if (correct) {
            // Dar la ubicación correcta
            System.out.println(getName() + " dice: El " + itemName + " está en la sala " + correctRoom.getName() + ".");
        } else {
            // Dar una ubicación incorrecta (elegir otra ubicación aleatoria)
            System.out.println(getName() + " dice: El " + itemName + " está en una sala diferente... creo.");
        }
    }
}
