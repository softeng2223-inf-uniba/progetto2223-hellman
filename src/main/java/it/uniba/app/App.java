package it.uniba.app;

import java.util.Scanner;

/**
 * Main class of the application.
 */
public final class App {

    public static void main(String[] args) {
        if (args.length == 1 && (args[0].equalsIgnoreCase("-h") || args[0].equalsIgnoreCase("--help")))
            showHelp();
        boolean exit = false;
        do {
            Scanner s = new Scanner(System.in);
            System.out.println("==== Battleship Game ====");
            System.out.print("Inserisci un comando >> ");
            String command = s.nextLine();
            if (!command.startsWith("/"))
                System.out.println("Comando non valido!");
            else switch (command.toLowerCase().replace("/", "")) {
                case "esci":
                    break;
                case "facile":
                    break;
                case "medio":
                    break;
                case "difficile": 
                    break;
                case "mostralivello":
                    break;
                case "mostranavi":
                    break;
                case "gioca":
                    break;
                case "svelagriglia":
                    break;
            }
        } while (!exit);
    }

    private static void showHelp() {

    }
}
