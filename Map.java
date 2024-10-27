package JocNau;
import java.util.ArrayList;
import java.util.Arrays;

public class Map {
    private Room[] rooms;

    public Map() {
        rooms = new Room[10];

        rooms[0] = new Room("Vestidor", "Una habitació amb taquilles i uniformes.");
        rooms[1] = new Room("Oficines", "L'oficina on es fan totes les gestions de la nau.");
        rooms[2] = new Room("Tallers", "Un taller ple d'eines.");
        rooms[3] = new Room("Cuina", "La cuina on es prepara el menjar.");
        rooms[4] = new Room("Comandament", "La sala de comandament.");
        rooms[5] = new Room("Banys", "Els banys de la nau.");
        rooms[6] = new Room("Menjador", "El menjador per als empleats.");
        rooms[7] = new Room("Sortida Exterior", "La sortida a l'exterior de la nau.");
        rooms[8] = new Room("Dormitori", "Un dormitori per descansar.");
        rooms[9] = new Room("Propulsors", "La sala de propulsors.");

        rooms[0].setExits(-1, 3, -1, 1);
        rooms[1].setExits(-1, 4, 0, 2);
        rooms[2].setExits(-1, 5, 1, -1);
        rooms[3].setExits(0, 6, -1, 4);
        rooms[4].setExits(1, 7, 3, 5);
        rooms[5].setExits(2, 8, 4, -1);
        rooms[6].setExits(3, -1, -1, 7);
        rooms[7].setExits(4, 9, 6, 8);
        rooms[8].setExits(5, -1, 7, -1);
        rooms[9].setExits(7, -1, -1, -1);

        rooms[0].addItem(new Item("Vestit", "Serveix per sortir a arreglar els propulsors", null));
        rooms[1].addItem(new Item("Targeta", "Serveix per obrir la taquilla", null));
        rooms[8].addItem(new Item("Targeta", "Serveix per obrir la taquilla", null));
        rooms[2].addItem(new Item("Eina", "Arregla els propulsors", null));
        rooms[3].addItem(new Item("Llanterna", "Il·lumina el taller", null));
        rooms[6].addItem(new Item("Donut", "Per calmar en Gonzalin", null));
    }

    public Room getRoom(int index) {
        if (index >= 0 && index < rooms.length) {
            return rooms[index];
        } else {
            return null;
        }
    }

    public ArrayList<Room> getAllRooms() {
        return new ArrayList<>(Arrays.asList(rooms));
    }
}
