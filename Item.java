package JocNau;

public class Item {
    private String name;
    private String description;
    private String effect;

    public Item(String name, String description, String effect) {
        this.name = name;
        this.description = description;
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getEffect() {
        return effect;
    }

    public void use(Player player) {
        System.out.println("Has usado: " + name + ". " + description);
        // Aquí puedes implementar los efectos específicos según el objeto
        switch (effect.toLowerCase()) {
            case "open_lockers":
                System.out.println("Usaste la tarjeta para abrir las taquillas.");
                break;
            case "wear_suit":
                System.out.println("Te has puesto el traje. Ahora puedes salir al exterior.");
                break;
            case "turn_on_flashlight":
                System.out.println("Has encendido la linterna. Ahora puedes ver en la sala oscura.");
                break;
            case "fix_propulsors":
                System.out.println("Has usado la herramienta para arreglar los propulsores.");
                break;
            case "use_donut":
                System.out.println("Usaste el donut para paralizar al enemigo.");
                break;
            default:
                System.out.println("El objeto no tiene un uso específico.");
        }
    }
}
