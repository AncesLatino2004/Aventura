package JocNau;

public class Tripulant extends NPC {
	private int currentRoom;
	private Map m = new Map();
	private boolean despert = false;
	
	public Tripulant() {
        super("Tripulant", "Bon dia, capità. A què es deu la interrupció del meu son?");
    }
	
	public void askForCard(){
		System.out.println("Bon dia, capità. A què es deu la interrupció del meu son?");
		System.out.println("Vol la meva targeta d'accés? Aquí la té, capità.");
	}
	
	public void moveTripulant(int direction, Map map) {
	    Room room = map.getRoom(currentRoom);
	    int nextRoomIndex = room.getExit(direction); // Obtenir la sortida segons la direcció

	    if (nextRoomIndex != -1) {
	        currentRoom = nextRoomIndex; // Actualitzar l'habitació actual del tripulant
	        System.out.println("El tripulant s'ha mogut a: " + map.getRoom(currentRoom).getName());
	    } else {
	        System.out.println("El tripulant no es pot moure en aquesta direcció.");
	    }
	}
	
	public void setDespert() {
		despert = true;
	}
	 
	public boolean getDespert() {
		return despert;
	}
}
