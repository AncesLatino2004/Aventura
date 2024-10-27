package JocNau;

import java.util.ArrayList;

public class Player {
    private String name;
    private Room currentRoom;
    private ArrayList<Item> inventory;
    private boolean hasSuit;
    private boolean hasFlashlightOn;
    private boolean hasTarjeta;
    private boolean hasHerramienta;
    private boolean hasDonut;

    public Player(String name, Room startRoom) {
        this.name = name;
        this.currentRoom = startRoom;
        this.inventory = new ArrayList<>();
        this.hasSuit = false;
        this.hasFlashlightOn = false;
        this.hasTarjeta = false;
        this.hasHerramienta = false;
        this.hasDonut = false;
    }

    public String getName() {
        return name;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    // Mètode per moure el jugador
    public void movePlayer(int direction, Map map) {
        int nextRoomIndex = currentRoom.getExit(direction);
        if (nextRoomIndex == -1) {
            System.out.println("No pots moure't en aquesta direcció.");
        } else {
            Room nextRoom = map.getRoom(nextRoomIndex);

            // Restricció per a la sala "Tallers"
            if (nextRoom.getName().equals("Tallers") && !hasFlashlightOn) {
                System.out.println("El taller està fosc. Necessites encendre la llanterna per entrar.");
                return;
            }

            // Restricció per a la sala "Propulsors"
            if (nextRoom.getName().equals("Propulsors") && !hasSuit) {
                System.out.println("No pots entrar a la sala de propulsors sense posar-te el vestit.");
                return;
            }

            // Si no hi ha restriccions, moure's a la nova habitació
            currentRoom = nextRoom;
            System.out.println("T'has mogut a: " + currentRoom.getName());
            System.out.println(currentRoom.getDescription());
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

            // Activar llanterna
            if (foundItem.getEffect().equals("turn_on_flashlight")) {
                hasFlashlightOn = true;
                System.out.println("Has encès la llanterna.");
            }

            // Posar-se el vestit
            else if (foundItem.getEffect().equals("wear_suit")) {
                hasSuit = true;
                System.out.println("T'has posat el vestit espacial.");
            }

            // Usar l'eina només a la sala "Propulsors"
            else if (foundItem.getEffect().equals("use_herramienta")) {
                if (currentRoom.getName().equals("Propulsors")) {
                    hasHerramienta = true;
                    System.out.println("Has usat l'eina i has arreglat els propulsors. Has guanyat el joc!");
                    // Aquí acaba el joc
                    System.exit(0);
                } else {
                    System.out.println("No pots usar l'eina aquí. Has d'estar a la sala de propulsors.");
                }
            }

        } else {
            System.out.println("No tens aquest objecte en el teu inventari.");
        }
    }

    // Nou mètode per afegir un objecte a l'inventari
    public void addItem(Item item) {
        inventory.add(item);
        System.out.println("L'objecte " + item.getName() + " ha estat afegit al teu inventari.");
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
            System.out.println("Has recollit: " + foundItem.getName());
        } else {
            System.out.println("No hi ha tal objecte aquí.");
        }
    }

    public void showInventory() {
        if (inventory.size() > 0) {
            System.out.println("El teu inventari conté:");
            for (Item item : inventory) {
                System.out.println("- " + item.getName());
            }
        } else {
            System.out.println("El teu inventari està buit.");
        }
    }

    // Mètodes per verificar si el jugador té certs objectes
    public boolean hasSuit() {
        return hasSuit;
    }

    public boolean hasFlashlightOn() {
        return hasFlashlightOn;
    }

    public boolean hasTarjeta() {
        return hasTarjeta;
    }

    public boolean hasHerramienta() {
        return hasHerramienta;
    }

    public boolean hasDonut() {
        return hasDonut;
    }

    // Mètodes per establir els estats dels objectes
    public void setSuit(boolean hasSuit) {
        this.hasSuit = hasSuit;
    }

    public void setFlashlightOn(boolean hasFlashlightOn) {
        this.hasFlashlightOn = hasFlashlightOn;
    }

    public void setTarjeta(boolean hasTarjeta) {
        this.hasTarjeta = hasTarjeta;
    }

    public void setHerramienta(boolean hasHerramienta) {
        this.hasHerramienta = hasHerramienta;
    }

    public void setDonut(boolean hasDonut) {
        this.hasDonut = hasDonut;
    }

    // Aquest és el nou mètode que he afegit
    public ArrayList<Item> getInventory() {
        return inventory;
    }
    
    public void removeItem(Item item) {
        inventory.remove(item);
        System.out.println("L'objecte " + item.getName() + " ha estat eliminat del teu inventari.");
    }
}
