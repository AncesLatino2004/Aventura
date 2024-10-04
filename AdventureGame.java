package JocNau;

import java.util.Scanner;

public class AdventureGame {
    private Map m;
    private Player player;
    private Enemy enemy;
    private Scanner scanner;
    private iHall hall;

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
        Enemy enemy = new Enemy("Gonzalin", 6);
        hall = new iHall();
        
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
            System.out.println("Que quieres hacer: IR, AGARRAR, DEJAR, USAR, ENCENDER, IHALL, SALIR");

            String input = scanner.nextLine().toUpperCase();
            switch (input) {
                case "IR":
                    System.out.println("¿Hacia dónde quieres ir? (up, down, left, right): ");
                    String direction = scanner.nextLine().toLowerCase();
                    player.movePlayer(direction, m); // Mueve al jugador en la dirección dada
                    break;

                case "AGARRAR":
                    System.out.println("Has intentado AGARRAR algo. Implementar lógica aquí.");
                    break;

                case "DEJAR":
                    System.out.println("Has intentado DEJAR algo. Implementar lógica aquí.");
                    break;

                case "USAR":
                    System.out.println("Has intentado USAR algo. Implementar lógica aquí.");
                    break;

                case "ENCENDER":
                    System.out.println("Has intentado ENCENDER algo. Implementar lógica aquí.");
                    break;

                case "IHALL":
                    hall.askForObject(player, m); // Pasar el jugador al NPC para que interactúe
                    break;

                case "SALIR":
                    restartGame(); // Llamamos al método para reiniciar o terminar el juego
                    break;

                default:
                    System.out.println("No se ha entendido la palabra introducida, introduce una válida.");
                    break;
            }
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
