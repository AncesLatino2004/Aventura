package JocNau;

import java.util.ArrayList;

public class Player {
    private String name;
    private Room currentRoom;
    private ArrayList<Item> inventory;
    private boolean hasSuit;
    private boolean hasFlashlightOn;

    public Player(String name, Room startRoom) {
        this.name = name;
        this.currentRoom = startRoom;
        this.inventory = new ArrayList<>();
        this.hasSuit = false;
        this.hasFlashlightOn = false;
    }

    public String getName() {
        return name;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    // Método para mover al jugador basado en la dirección
    public void movePlayer(String direction, Map map) {
        int nextRoomIndex = currentRoom.getExit(direction); // Usar el método getExit() con la dirección
        
        if (nextRoomIndex == -1) {
            System.out.println("No puedes moverte en esa dirección.");
        } else {
            Room nextRoom = map.getRoom(nextRoomIndex); // Obtener la próxima habitación
            if (nextRoom.getName().equals("Tallers") && !hasFlashlightOn) {
                System.out.println("El taller está oscuro. Necesitas encender la linterna para entrar.");
            } else if (nextRoom.getName().equals("Exterior") && !hasSuit) {
                System.out.println("No puedes salir al exterior sin ponerte el traje espacial.");
            } else {
                currentRoom = nextRoom;
                System.out.println("Te has movido a: " + currentRoom.getName());
                System.out.println(currentRoom.getDescription());
            }
        }
    }

    public void pickUpItem(String itemName) {
        Item foundItem = null;
        for (Item item : currentRoom.getItems()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                foundItem = item;
                break;
            }
        }
        if (foundItem != null) {
            inventory.add(foundItem);
            currentRoom.removeItem(foundItem);
            System.out.println("Has recogido: " + foundItem.getName());
        } else {
            System.out.println("No hay tal objeto aquí.");
        }
    }

    public void useItem(String itemName) {
        Item foundItem = null;
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                foundItem = item;
                break;
            }
        }
        if (foundItem != null) {
            foundItem.use(this);
            if (foundItem.getEffect().equals("wear_suit")) {
                hasSuit = true;
            } else if (foundItem.getEffect().equals("turn_on_flashlight")) {
                hasFlashlightOn = true;
            }
            // Puedes agregar más efectos específicos aquí.
        } else {
            System.out.println("No tienes tal objeto en tu inventario.");
        }
    }

    public void showInventory() {
        if (inventory.size() > 0) {
            System.out.println("Tu inventario contiene:");
            for (Item item : inventory) {
                System.out.println("- " + item.getName());
            }
        } else {
            System.out.println("Tu inventario está vacío.");
        }
    }

    // Este es el nuevo método que agregué
    public ArrayList<Item> getInventory() {
        return inventory;
    }
}
