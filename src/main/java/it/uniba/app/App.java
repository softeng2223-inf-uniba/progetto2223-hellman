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
                    public class GrigliaNavi {
                        public static void main(String[] args) {
                            char[][] griglia = new char[10][10];

                            // Inizializza la griglia con spazi vuoti
                            for (int i = 0; i < 10; i++) {
                                for (int j = 0; j < 10; j++) {
                                    griglia[i][j] = ' ';
                                }
                            }
                            // Posiziona alcune navi nella griglia
                            griglia[0][0] = 'X'; // Esempio di nave posizionata nella riga 1, colonna A
                            griglia[5][7] = 'X'; // Esempio di nave posizionata nella riga 6, colonna H

                            // Stampa la griglia con le righe e colonne numerate
                            System.out.print("  ");
                            for (char c = 'A'; c <= 'J'; c++) {
                                System.out.print(c + " ");
                            }
                            System.out.println();

                            for (int i = 0; i < 10; i++) {
                                System.out.print((i + 1) + " ");
                                for (int j = 0; j < 10; j++) {
                                    System.out.print(griglia[i][j] + " ");
                                }
                                System.out.println();
                            }
                        }
                    }
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
