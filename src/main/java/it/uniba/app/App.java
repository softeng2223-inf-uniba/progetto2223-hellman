package it.uniba.app;

import java.util.Scanner;

/**
 * Main class of the application.
 */
public final class App {
    private App() {

    }

    /**
     * Classe main del programma.
     *
     * @param args argomenti passati da linea di comando
     */
    public static void main(final String[] args) {
        if (args.length == 1 && (args[0].equalsIgnoreCase("-h") || args[0].equalsIgnoreCase("--help"))) {
            showHelp();
        }
        BattleshipGame bg = new BattleshipGame();
        Scanner s = new Scanner(System.in);
        System.out.println("==== Battleship Game ====");
        boolean exit = false;
        do {
            exit = false;
            System.out.print("Inserisci un comando: ");
            String command = s.nextLine();
            switch (command.toLowerCase()) {
                case "/help":
                    break;
                case "/esci":
                    break;
                case "/facile":
                    break;
                case "/medio":
                    break;
                case "/difficile":
                    break;
                case "/mostralivello":
                    break;
                case "/mostranavi":
                    break;
                case "/gioca":
                    break;
                case "/svelagriglia":
                    break;
                default:
                    System.out.println("Comando non riconosciuto.");
                    break;
            }
        } while (!exit);
    }

    private static void showHelp() {

    }
}
