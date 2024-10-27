package JocNau;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class AdventureGame {
    private Map m;
    private Player player;
    private Enemy enemy;
    private Scanner scanner;
    private iHall hall;
    private Tripulant tripulant;
    private Boolean jocAcabat = false;

    public static void main(String[] args) {
        AdventureGame game = new AdventureGame();
        game.iniciarJuego();
    }

    // Constructor per inicialitzar el joc
    public AdventureGame() {
        // Crear el mapa
        m = new Map(); // Assegura't que el constructor de Map estigui ben definit
        // Crear el jugador i posar-lo a la sala inicial
        player = new Player("Hero", m.getRoom(8));
        enemy = new Enemy("Gonzalin", 6);
        hall = new iHall();
        tripulant = new Tripulant();
        // Inicialitzar l'escàner per entrada de l'usuari
        scanner = new Scanner(System.in);
    }

    // Mètode per iniciar el cicle del joc
    public void iniciarJuego() {
        buclePrincipal();
    }

    // Cicle principal del joc
    public void buclePrincipal() {
        while (jocAcabat == false) {
            System.out.println("\nEstàs a: " + player.getCurrentRoom().getName());
            System.out.println(player.getCurrentRoom().getDescription());
            System.out.println("Què vols fer: ANAR, AGAFAR, DEIXAR, USAR, INVENTARI, IHALL, DESPERTAR, SORTIR");

            String input = scanner.nextLine().toUpperCase();
            switch (input) {
            case "ANAR":
                System.out.println("Cap a on vols anar? (1: amunt, 2: avall, 3: esquerra, 4: dreta): ");
                int direction = scanner.nextInt();
                scanner.nextLine(); // Consumir el salt de línia després d'ingressar el número
                player.movePlayer(direction, m); // Mou el jugador usant un enter com a direcció
                Random random = new Random();
                int directionE = random.nextInt(4) + 1;
                enemy.moveEnemy(directionE, m);
                if (tripulant.getDespert() == false) {
                    tripulant.moveTripulant(directionE, m);
                }

                // Comprovar si el jugador ha arribat a la sala de propulsors
                if (player.getCurrentRoom().getName().equalsIgnoreCase("Propulsors")) {
                    System.out.println("T'has mogut a: Propulsors");
                    System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("Has entrat a la sala de propulsors, un lloc clau per al funcionament de la nau.");
                    System.out.println("Els propulsors estan malmesos i hi ha alertes per tot arreu. Però, tu tens una eina que podria resoldre aquest problema.");
                    System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                }

                // Comprovar si el jugador i l'enemic estan a la mateixa habitació
                if (player.getCurrentRoom().getRoomNumber() == enemy.getCurrentRoom()) {
                    System.out.println("T'has trobat amb en Gonzalin!");

                    // Verificar si el jugador té un donut
                    if (player.getInventory().stream().anyMatch(item -> item.getName().equalsIgnoreCase("Donut"))) {
                        // Eliminar el donut de l'inventari i calmar l'enemic
                        Item donut = player.getInventory().stream().filter(item -> item.getName().equalsIgnoreCase("Donut")).findFirst().get();
                        player.removeItem(donut);

                        // Gonzalin queda calmat i no atacarà durant la resta de la partida
                        System.out.println("Li has donat el donut a en Gonzalin. Ara està calmat i no et tornarà a atacar.");
                        enemy.setCalmado(true); // Defineix una variable calmado a Enemy perquè no es mogui ni ataqui
                    } else {
                        // Si el jugador no té un donut
                        System.out.println("No tens un donut! Gonzalin t'ha atacat. Has perdut el joc.");
                        restartGame();
                    }
                }
                break;

            case "AGAFAR":
                Room currentRoom = player.getCurrentRoom();
                if (currentRoom.getItems().isEmpty() || currentRoom == m.getRoom(8) || currentRoom == m.getRoom(0) && player.hasTarjeta() == false) {
                    System.out.println("No hi ha objectes en aquesta habitació per agafar.");
                } else {
                    System.out.println("Objectes disponibles en aquesta habitació:");
                    for (int i = 0; i < currentRoom.getItems().size(); i++) {
                        System.out.println((i + 1) + ". " + currentRoom.getItems().get(i).getName());
                    }

                    System.out.println("Quin objecte vols agafar? (Introdueix el número corresponent): ");
                    int itemIndex = scanner.nextInt();
                    scanner.nextLine();

                    if (itemIndex > 0 && itemIndex <= currentRoom.getItems().size()) {
                        Item itemToGrab = currentRoom.getItems().get(itemIndex - 1);
                        player.addItem(itemToGrab);
                        currentRoom.removeItem(itemToGrab);

                        System.out.println("Has agafat: " + itemToGrab.getName());
                    } else {
                        System.out.println("Número invàlid, intenta-ho de nou.");
                    }
                }
                break;

            case "DEIXAR":
                if (player.getInventory().isEmpty()) {
                    System.out.println("El teu inventari està buit. No pots deixar cap objecte.");
                } else {
                    System.out.println("El teu inventari conté els següents objectes:");
                    ArrayList<Item> inventory = player.getInventory();
                    for (int i = 0; i < inventory.size(); i++) {
                        System.out.println((i + 1) + ". " + inventory.get(i).getName());
                    }

                    System.out.println("Quin objecte vols deixar en aquesta habitació? (Introdueix el número corresponent): ");
                    int itemIndex = scanner.nextInt();
                    scanner.nextLine();

                    if (itemIndex > 0 && itemIndex <= inventory.size()) {
                        Item itemToLeave = inventory.get(itemIndex - 1);
                        player.removeItem(itemToLeave);
                        player.getCurrentRoom().addItem(itemToLeave);

                        System.out.println("Has deixat: " + itemToLeave.getName() + " a l'habitació.");
                    } else {
                        System.out.println("Número invàlid, intenta-ho de nou.");
                    }
                }
                break;

            case "USAR":
                // Mostrar l'inventari del jugador
                if (player.getInventory().isEmpty()) {
                    System.out.println("No tens objectes en el teu inventari per utilitzar.");
                } else {
                    System.out.println("El teu inventari conté:");
                    player.showInventory();

                    // Sol·licitar al jugador que triï un objecte per utilitzar
                    System.out.println("Quin objecte vols utilitzar?");
                    String objetoAUsar = scanner.nextLine().toLowerCase();

                    // Verificar si el jugador té l'objecte i utilitzar-lo
                    switch (objetoAUsar) {
                        case "llanterna":
                            if (player.getInventory().stream().anyMatch(item -> item.getName().equalsIgnoreCase("Llanterna"))) {
                                if (!player.hasFlashlightOn()) {
                                    player.setFlashlightOn(true); // Activar llanterna
                                    System.out.println("Has encès la llanterna. Ara pots veure a llocs foscos.");
                                } else {
                                    System.out.println("Ja tens la llanterna encesa.");
                                }
                            } else {
                                System.out.println("No tens la llanterna en el teu inventari.");
                            }
                            break;

                        case "traje":
                            if (player.getInventory().stream().anyMatch(item -> item.getName().equalsIgnoreCase("Traje"))) {
                                if (!player.hasSuit()) {
                                    player.setSuit(true); // Posar-se el traje
                                    System.out.println("T'has posat el traje espacial. Ara pots sortir a l'exterior.");
                                } else {
                                    System.out.println("Ja tens posat el traje espacial.");
                                }
                            } else {
                                System.out.println("No tens un traje en el teu inventari.");
                            }
                            break;

                        case "tarjeta":
                            // Lògica per a la targeta
                            if (player.getInventory().stream().anyMatch(item -> item.getName().equalsIgnoreCase("Tarjeta"))) {
                                System.out.println("Has utilitzat la targeta per obrir una taquilla.");
                                // Aquí podries afegir més lògica relacionada amb la targeta
                            } else {
                                System.out.println("No tens una targeta en el teu inventari.");
                            }
                            break;

                        case "eina":
                            // Lògica per a l'eina
                            if (player.getInventory().stream().anyMatch(item -> item.getName().equalsIgnoreCase("Eina"))) {
                                player.setHerramienta(true); // Activar eina

                                // Verificar que el jugador estigui a la sala de propulsors i que l'eina estigui activa
                                if (player.getCurrentRoom().getName().equalsIgnoreCase("Propulsors")) {
                                    System.out.println("Has utilitzat l'eina per arreglar els propulsors.");
                                    System.out.println("Has arreglat els propulsors! Has guanyat el joc!");
                                    System.exit(0); // Acabar el joc
                                } 
                            } else {
                                System.out.println("No tens una eina en el teu inventari.");
                            }
                            break;

                        case "donut":
                            // Lògica per al donut
                            if (player.getInventory().stream().anyMatch(item -> item.getName().equalsIgnoreCase("Donut"))) {
                                System.out.println("Li has donat el donut a en Gonzalin, ara està calmat.");
                                // Aquí podries afegir lògica perquè en Gonzalin desaparegui o alguna cosa similar.
                            } else {
                                System.out.println("No tens un donut en el teu inventari.");
                            }
                            break;

                        default:
                            System.out.println("No tens aquest objecte o no és usable.");
                            break;
                    }
                }
                break;

            case "IHALL":
                hall.askForObject(player, m); // Passar el jugador al NPC per interactuar
                break;
            
            case "DESPERTAR":
                if(player.getCurrentRoom() != m.getRoom(8)) {
                    System.out.println("No es pot despertar ningú en aquesta habitació.");
                } else {
                    tripulant.setDespert();
                    System.out.println("Has despertat el tripulant");
                    tripulant.askForCard();
                    System.out.println("Vols agafar la targeta del tripulant? (1. Sí 2. No)");
                    int resposta = scanner.nextInt();
                    switch(resposta) {
                        case 1:
                            Room currentRoom2 = player.getCurrentRoom();
                            Item itemToGrab = currentRoom2.getItems().get(resposta - 1);
                            player.addItem(itemToGrab);
                            break;
                        case 2:
                            System.out.println("Entesos capità, si no et fa falta ara mateix, no passa res.");
                            break;
                    }
                }
                break;
            case "SORTIR":
                restartGame(); // Truca al mètode per reiniciar o acabar el joc
                break;
                
            case "INVENTARI":
                for (int i = 0; i < player.getInventory().size(); i++) {
                    System.out.println((i + 1) + ". " + player.getInventory().get(i).getName());
                }
                break;

            default:
                System.out.println("No s'ha entès la paraula introduïda, introdueix una paraula vàlida.");
                break;
            }
            
            System.out.println("---------------------------------------------------------------------------------------------");
        }
    }

    // Mètode per reiniciar el joc
    public void restartGame() {
        System.out.println("Vols reiniciar el joc? (sí/no)");
        String resposta = scanner.nextLine();

        if (resposta.equalsIgnoreCase("no")) {
            System.out.println("Gràcies per jugar!");
            scanner.close(); // Tancar l'escàner quan acabi el joc
            jocAcabat = true;
            System.exit(0);
        } else if (resposta.equalsIgnoreCase("sí")) {
            // Reiniciar el joc
            iniciarJuego(); // Torna a cridar iniciar el joc
        } else {
            // Si no dona una resposta vàlida, preguntar de nou
            System.out.println("Resposta no vàlida. Si us plau, escriu 'sí' o 'no'.");
            restartGame();
        }
    }
}
