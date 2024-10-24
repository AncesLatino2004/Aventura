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

    public static void main(String[] args) {
        AdventureGame game = new AdventureGame();
        game.iniciarJuego();
    }

    // Constructor para inicializar el juego
    public AdventureGame() {
        // Crear el mapa
        m = new Map(); // Debes asegurarte de que el constructor de Map esté bien definido
        // Crear al jugador y ponerlo en la habitación inicial
        player = new Player("Hero", m.getRoom(8));
        enemy = new Enemy("Gonzalin", 6);
        hall = new iHall();
        tripulant = new Tripulant();
        // Inicializar el escáner para entrada del usuario
        scanner = new Scanner(System.in);
    }

    // Método para iniciar el ciclo del juego
    public void iniciarJuego() {
        buclePrincipal();
    }

    // Ciclo principal del juego
    public void buclePrincipal() {
        while (true) {
            System.out.println("\nEstás en: " + player.getCurrentRoom().getName());
            System.out.println(player.getCurrentRoom().getDescription());
            System.out.println("Que quieres hacer: IR, AGARRAR, DEJAR, USAR, INVENTARI, IHALL, DESPERTAR, SALIR");

            String input = scanner.nextLine().toUpperCase();
            switch (input) {
            case "IR":
                System.out.println("¿Hacia dónde quieres ir? (1: arriba, 2: abajo, 3: izquierda, 4: derecha): ");
                int direction = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea después de ingresar el número
                player.movePlayer(direction, m); // Mueve al jugador usando un entero como dirección
                Random random = new Random();
                int directionE = random.nextInt(4) + 1;
                enemy.moveEnemy(directionE, m);

                // Comprobar si el jugador ha llegado a la sala de propulsores
                if (player.getCurrentRoom().getName().equalsIgnoreCase("Propulsores")) {
                    System.out.println("Te has movido a: Propulsores");
                    System.out.println("--------------------------------------------------");
                    System.out.println("Has entrado en la sala de propulsores, un lugar crucial para el funcionamiento de la nave.");
                    System.out.println("Los propulsores están dañados y parpadean con luces rojas. Sin embargo, tú tienes una herramienta que podría resolver este problema.");
                    System.out.println("--------------------------------------------------");
                }

                // Comprobar si el jugador y el enemigo están en la misma habitación
                if (player.getCurrentRoom().getRoomNumber() == enemy.getCurrentRoom()) {
                    System.out.println("¡Te has encontrado con Gonzalin!");
                    
                    // Verificar si el jugador tiene un donut
                    if (player.getInventory().stream().anyMatch(item -> item.getName().equalsIgnoreCase("Donut"))) {
                        // Eliminar el donut del inventario y calmar al enemigo
                        Item donut = player.getInventory().stream().filter(item -> item.getName().equalsIgnoreCase("Donut")).findFirst().get();
                        player.removeItem(donut);

                        // Gonzalin queda calmado y no atacará por el resto de la partida
                        System.out.println("Le has dado el Donut a Gonzalin. Ahora está calmado y no te atacará más.");
                        enemy.setCalmado(true); // Puedes definir una variable `calmado` en Enemy que lo haga no moverse ni atacar
                    } else {
                        // Si el jugador no tiene un Donut
                        System.out.println("¡No tienes un Donut! Gonzalin te ha atacado. Has perdido el juego.");
                        System.exit(0); // Terminar el juego
                    }
                }
                break;

            case "AGARRAR":
                Room currentRoom = player.getCurrentRoom();
                if (currentRoom.getItems().isEmpty() || currentRoom == m.getRoom(8)|| currentRoom == m.getRoom(0) && player.hasTarjeta() == false) {
                    System.out.println("No hay objetos en esta habitación para agarrar.");
                } else {
                    System.out.println("Objetos disponibles en esta habitación:");
                    for (int i = 0; i < currentRoom.getItems().size(); i++) {
                        System.out.println((i + 1) + ". " + currentRoom.getItems().get(i).getName());
                    }

                    System.out.println("¿Qué objeto quieres agarrar? (Ingresa el número correspondiente): ");
                    int itemIndex = scanner.nextInt();
                    scanner.nextLine();

                    if (itemIndex > 0 && itemIndex <= currentRoom.getItems().size()) {
                        Item itemToGrab = currentRoom.getItems().get(itemIndex - 1);
                        player.addItem(itemToGrab);
                        currentRoom.removeItem(itemToGrab);

                        System.out.println("Has agarrado: " + itemToGrab.getName());
                    } else {
                        System.out.println("Número inválido, intenta de nuevo.");
                    }
                }
                break;
                
            case "DEJAR":
                if (player.getInventory().isEmpty()) {
                    System.out.println("Tu inventario está vacío. No puedes dejar ningún objeto.");
                } else {
                    System.out.println("Tu inventario contiene los siguientes objetos:");
                    ArrayList<Item> inventory = player.getInventory();
                    for (int i = 0; i < inventory.size(); i++) {
                        System.out.println((i + 1) + ". " + inventory.get(i).getName());
                    }

                    System.out.println("¿Qué objeto quieres dejar en esta habitación? (Ingresa el número correspondiente): ");
                    int itemIndex = scanner.nextInt();
                    scanner.nextLine();

                    if (itemIndex > 0 && itemIndex <= inventory.size()) {
                        Item itemToLeave = inventory.get(itemIndex - 1);
                        player.removeItem(itemToLeave);
                        player.getCurrentRoom().addItem(itemToLeave);

                        System.out.println("Has dejado: " + itemToLeave.getName() + " en la habitación.");
                    } else {
                        System.out.println("Número inválido, intenta de nuevo.");
                    }
                }
                break;

            case "USAR":
                // Mostrar el inventario del jugador
                if (player.getInventory().isEmpty()) {
                    System.out.println("No tienes objetos en tu inventario para usar.");
                } else {
                    System.out.println("Tu inventario contiene:");
                    player.showInventory();

                    // Solicitar al jugador que elija un objeto para usar
                    System.out.println("¿Qué objeto quieres usar?");
                    String objetoAUsar = scanner.nextLine().toLowerCase();

                    // Verificar si el jugador tiene el objeto y usarlo
                    switch (objetoAUsar) {
                        case "linterna":
                            if (player.getInventory().stream().anyMatch(item -> item.getName().equalsIgnoreCase("Linterna"))) {
                                if (!player.hasFlashlightOn()) {
                                    player.setFlashlightOn(true); // Activar linterna
                                    System.out.println("Has encendido la linterna. Ahora puedes ver en lugares oscuros.");
                                } else {
                                    System.out.println("Ya tienes la linterna encendida.");
                                }
                            } else {
                                System.out.println("No tienes una linterna en tu inventario.");
                            }
                            break;

                        case "traje":
                            if (player.getInventory().stream().anyMatch(item -> item.getName().equalsIgnoreCase("Traje"))) {
                                if (!player.hasSuit()) {
                                    player.setSuit(true); // Ponerse el traje
                                    System.out.println("Te has puesto el traje espacial. Ahora puedes salir al exterior.");
                                } else {
                                    System.out.println("Ya tienes puesto el traje espacial.");
                                }
                            } else {
                                System.out.println("No tienes un traje en tu inventario.");
                            }
                            break;

                        case "tarjeta":
                            // Lógica para la tarjeta
                            if (player.getInventory().stream().anyMatch(item -> item.getName().equalsIgnoreCase("Tarjeta"))) {
                                System.out.println("Has usado la tarjeta para abrir una taquilla.");
                                // Aquí podrías agregar más lógica relacionada con la tarjeta
                            } else {
                                System.out.println("No tienes una tarjeta en tu inventario.");
                            }
                            break;

                        case "herramienta":
                        	
                            // Lógica para la herramienta
                            if (player.getInventory().stream().anyMatch(item -> item.getName().equalsIgnoreCase("Herramienta"))) {
                                player.setHerramienta(true); // Activar linterna

                                // Verificar que el jugador esté en la sala de propulsores y que la herramienta esté activa
                                if (player.getCurrentRoom().getName().equalsIgnoreCase("Propulsors")) {
                                    System.out.println("Has usado la herramienta para arreglar los propulsores.");
                                    System.out.println("¡Has arreglado los propulsores! ¡Has ganado el juego!");
                                    System.exit(0); // Terminar el juego
                                } 
                            } else {
                                System.out.println("No tienes una herramienta en tu inventario.");
                            }
                            break;

                        case "donut":
                            // Lógica para el donut
                            if (player.getInventory().stream().anyMatch(item -> item.getName().equalsIgnoreCase("Donut"))) {
                                System.out.println("Le has dado el donut a Gonzalin, ahora está calmado.");
                                // Aquí podrías agregar lógica para que Gonzalin desaparezca o algo similar.
                            } else {
                                System.out.println("No tienes un donut en tu inventario.");
                            }
                            break;

                        default:
                            System.out.println("No tienes ese objeto o no es usable.");
                            break;
                    }
                }
                break;

            case "IHALL":
                hall.askForObject(player, m); // Pasar el jugador al NPC para que interactúe
                break;
            
            case "DESPERTAR":
            	if(player.getCurrentRoom() != m.getRoom(8)) {
            		System.out.println("No se puede despertar a alguien en esta habitacion");
            	}else {
            		System.out.println("Despertes al tripulant");
            		tripulant.askForCard();
            		  System.out.println("Vols agafar la targeta del tripulant?: (1. Sí 2. No");
                      int resposta = scanner.nextInt();       
                      switch(resposta) {
                      	case 1:
                      		Room currentRoom2 = player.getCurrentRoom();
                      		Item itemToGrab = currentRoom2.getItems().get(resposta - 1);
                            player.addItem(itemToGrab);      
                            break;
                        case 2:                            	
                      		System.out.println("Entesos capità, si no li fa falta ara mateix, no passa res");
                      		break;
                      }
            	}
            	break;
            case "SALIR":
                restartGame(); // Llamamos al método para reiniciar o terminar el juego
                break;
                
            case "INVENTARI":
            	  for (int i = 0; i < player.getInventory().size(); i++) {
                      System.out.println((i + 1) + ". " + player.getInventory().get(i).getName());
                  }
            	break;

            default:
                System.out.println("No se ha entendido la palabra introducida, introduce una válida.");
                break;
            }
            
            System.out.println("--------------------------------------------------------------------------");
        }
    }

    // Método para reiniciar el juego
    public void restartGame() {
        System.out.println("¿Quieres reiniciar el juego? (si/no)");
        String resposta = scanner.nextLine();

        if (resposta.equalsIgnoreCase("no")) {
            System.out.println("¡Gracias por jugar!");
            scanner.close(); // Cerrar el escáner cuando termine el juego
            System.exit(0);
        } else if (resposta.equalsIgnoreCase("si")) {
            // Reiniciar el juego
            iniciarJuego(); // Llama nuevamente a iniciar el juego
        } else {
            // Si no da una respuesta válida, preguntar de nuevo
            System.out.println("Respuesta no válida. Por favor, escribe 'si' o 'no'.");
            restartGame();
        }
    }
}
