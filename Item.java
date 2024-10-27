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
        System.out.println("Has usat: " + name + ". " + description);
        switch (effect.toLowerCase()) {
            case "open_lockers":
                System.out.println("Has utilitzat la targeta per obrir les taquilles.");
                break;
            case "wear_suit":
                System.out.println("T'has posat el vestit. Ara pots sortir a l'exterior.");
                break;
            case "turn_on_flashlight":
                System.out.println("Has encès la llanterna. Ara pots veure a la sala fosca.");
                break;
            case "fix_propulsors":
                System.out.println("Has utilitzat l'eina per arreglar els propulsors.");
                break;
            case "use_donut":
                System.out.println("Has utilitzat el donut per paralitzar l'enemic.");
                break;
            default:
                System.out.println("L'objecte no té un ús específic.");
        }
    }
}
