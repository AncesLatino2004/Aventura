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

    // Método para mover al jugador
    public void movePlayer(int direction, Map map) {
        int nextRoomIndex = currentRoom.getExit(direction);
        if (nextRoomIndex == -1) {
            System.out.println("No puedes moverte en esa dirección.");
        } else {
            Room nextRoom = map.getRoom(nextRoomIndex);
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


    // Nuevo método para agregar un objeto al inventario
    public void addItem(Item item) {
        inventory.add(item);
        System.out.println("El objeto " + item.getName() + " ha sido añadido a tu inventario.");
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

 

    // Métodos para verificar si el jugador tiene ciertos objetos
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

    // Métodos para establecer los estados de los objetos
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

    // Este es el nuevo método que agregué
    public ArrayList<Item> getInventory() {
        return inventory;
    }
    public void removeItem(Item item) {
        inventory.remove(item);
        System.out.println("El objeto " + item.getName() + " ha sido eliminado de tu inventario.");
    }
}
