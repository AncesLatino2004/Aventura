package JocNau;

public class Tripulant extends NPC {
	public Tripulant() {
        super("Tripulant", "Buenos dias, capitan a que se deve la interrupcion de mi sueño?.");
    }
	
	public void askForCard(){
		System.out.println("Buenos dias, capitan a que se deve la interrupcion de mi sueño?.");
		System.out.println("Quiere mi tarjeta de acceso?, aqui tiene capitan");
	}
}