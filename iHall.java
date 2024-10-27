package JocNau;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class iHall extends NPC {
    public iHall() {
        super("iHall", "Hola, puc dir-te on són els objectes si ho necessites.");
    }

    public void askForObject(Player player, Map map) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println(getName() + " diu: Quin objecte estàs buscant?");
        String itemName = scanner.nextLine();

        Room foundRoom = findItemInRooms(itemName, map);

        if (foundRoom != null) {
            giveLocation(itemName, foundRoom);
        } else {
            System.out.println(getName() + " diu: No sé on és aquest objecte.");
        }
    }

    private Room findItemInRooms(String itemName, Map map) {
        ArrayList<Room> rooms = map.getAllRooms();

        for (Room room : rooms) {
            for (Item item : room.getItems()) {
                if (item.getName().equalsIgnoreCase(itemName)) {
                    return room;
                }
            }
        }

        return null;
    }

    public void giveLocation(String itemName, Room correctRoom) {
        Random random = new Random();
        boolean correct = random.nextBoolean();

        if (correct) {
            System.out.println(getName() + " diu: El " + itemName + " és a la sala " + correctRoom.getName() + ".");
        } else {
            System.out.println(getName() + " diu: El " + itemName + " és en una sala diferent... crec.");
        }
    }
}
