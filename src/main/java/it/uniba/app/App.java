package it.uniba.app;

import it.uniba.app.exceptions.GameAlreadyRunningException;
import it.uniba.app.exceptions.GameNotReadyException;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.StringTokenizer;

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
        Scanner s = new Scanner(System.in, StandardCharsets.UTF_8);
        System.out.println("==== Battleship Game ====");
        boolean exit = false;
        do {
            System.out.print("Inserisci un comando: ");
            String input = s.nextLine();
            if (input.startsWith("/")) {
                String[] arguments = null;
                boolean hasArgs = false;
                StringTokenizer st = new StringTokenizer(input.substring(1), " ");

                if (st.countTokens() > 1) {
                    hasArgs = true;
                    arguments = new String[st.countTokens()];
                }

                String command = st.nextToken();
                if (hasArgs) {
                    int numTokens = st.countTokens();
                    for (int i = 0; i < numTokens; i++) {
                        arguments[i] = st.nextToken();
                    }
                }
                switch (command.toLowerCase()) {
                    case "help" -> showHelp();
                    case "esci" -> {
                        System.out.print("Sei sicuro di voler chiudere il gioco? S/N: ");
                        String conferma = s.nextLine();
                        if (conferma.equalsIgnoreCase("S")) {
                            System.out.println("Chiusura del gioco in corso...");
                            exit = true;
                        } else {
                            System.out.println("Puoi continuare a giocare.");
                        }
                    }
                    case "facile" -> {
                        if (!hasArgs) {
                            bg.setDifficulty(command, null);
                            System.out.println("Difficoltà impostata a facile.");
                        } else {
                            try {
                                int valore = Integer.parseInt(arguments[0]);
                                bg.setDifficulty(command, valore);
                                System.out.println("OK");
                            } catch (NumberFormatException e) {
                                System.err.println("Comando non valido: utilizza /facile <numero>.");
                            }
                        }
                    }
                    case "medio" -> {
                        if (!hasArgs) {
                            bg.setDifficulty(command, null);
                            System.out.println("Difficoltà impostata a medio.");
                        } else {
                            try {
                                int valore = Integer.parseInt(arguments[0]);
                                bg.setDifficulty(command, valore);
                                System.out.println("OK");
                            } catch (NumberFormatException e) {
                                System.err.println("Comando non valido: utilizza /medio <numero>.");
                            }
                        }
                    }
                    case "difficile" -> {
                        if (!hasArgs) {
                            bg.setDifficulty(command, null);
                            System.out.println("Difficoltà impostata a difficile.");
                        } else {
                            try {
                                int valore = Integer.parseInt(arguments[0]);
                                bg.setDifficulty(command, valore);
                                System.out.println("OK");
                            } catch (NumberFormatException e) {
                                System.err.println("Comando non valido: utilizza /difficile <numero>.");
                            }
                        }
                    }
                    case "mostralivello" -> bg.showDifficulty();
                    case "mostranavi" -> {
                        try {
                            bg.showShips();
                        } catch (GameNotReadyException e) {
                            System.err.println(e.getMessage());
                        }
                    }
                    case "gioca" -> {
                        try {
                            bg.newGame();
                            System.out.println("Navi posizionate e partita iniziata.");
                        } catch (GameAlreadyRunningException e) {
                            System.err.println(e.getMessage());
                        }
                    }
                    case "svelagriglia" -> {
                        System.out.println("Griglia delle navi:");
                        bg.revealShipGrid();
                    }
                    default -> System.out.println("Comando non riconosciuto.");
                }
            } else {
                System.out.println("Comando non riconosciuto.");
            }
        } while (!exit);
    }

    private static void showHelp() {
        System.out.println("Benvenuti in 'Battaglia Navale'");
        System.out.print("Il gioco consiste nell'eliminare tutte le navi nemiche, che all'avvio della partita vengono");
        System.out.print(" disposte in modo casuale all'interno di una griglia 10x10, indicando la posizione nella ");
        System.out.println("quale si vuole 'lanciare' il missile per colpirle.");
        System.out.println("Lista comandi: \n ");
        System.out.println("/help : viene visualizzata la lista dei comandi e la sua descrizione ");
        System.out.print("/gioca : se nessuna partita è in corso, vengono impostate casualmente le navi, in orizzont");
        System.out.print("ale o in verticale e si predispone a ricevere il primo tentativo o altri comandi\n");
        System.out.print("/esci : permette di chiudere il gioco, premendo 'Y' il gioco viene chiuso, ");
        System.out.print("premendo 'N' l'applicazione si predispone a ricevere nuovi tentativi o comandi\n");
        System.out.print("/mostralivello : permette di visualizzare il livello di gioco e il numero massimo di ");
        System.out.print("tentativi falliti\n");
        System.out.println("/facile : imposta il gioco a livello facile, si hanno 50 tentativi massimi falliti. ");
        System.out.println("Può essere specificato un numero di tentativi massimi falliti, esempio: /facile 30. ");
        System.out.println("/medio : imposta il gioco a livello medio, si hanno 30 tentativi massimi falliti ");
        System.out.println("Può essere specificato un numero di tentativi massimi falliti, esempio: /medio 20. ");
        System.out.println("/difficile : imposta il gioco a livello difficile, si hanno 10 tentativi massimi falliti ");
        System.out.println("Può essere specificato un numero di tentativi massimi falliti, esempio: /difficile 5. ");
        System.out.print("/mostranavi : permette di visualizzare la sua dimensione in quadrati e il numero di navi ");
        System.out.print("da affondare.\n -Cacciatorpediniere    ⊠⊠        esemplari: 4\n");
        System.out.println(" -Incrociatore          ⊠⊠⊠      esemplari: 3");
        System.out.println(" -Corazzata             ⊠⊠⊠⊠     esemplari: 2");
        System.out.println(" -Portaerei             ⊠⊠⊠⊠⊠    esemplari: 1");
        System.out.print("/svelagriglia : permette di visualizzare la griglia 10x10, con le righe numerate ");
        System.out.print("da 1 a 10 e le colonne numerate da A a J, e tutte le navi posizionate al suo interno\n");
    }
}
