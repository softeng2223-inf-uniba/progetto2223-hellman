package it.uniba.app;

import it.uniba.app.exceptions.GameAlreadyRunningException;
import it.uniba.app.util.Pair;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Main class of the application.
 */
public final class App {
    private static Scanner s;
    private static BattleshipGame bg;
    private static boolean exit;

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
        bg = new BattleshipGame();
        s = new Scanner(System.in, StandardCharsets.UTF_8);
        System.out.println("==== Battleship Game ====");
        exit = false;
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
                    case "esci" -> handleEsci();
                    case "facile", "medio", "difficile" -> handleDifficolta(command, arguments, hasArgs);
                    case "tentativi" -> handleTentativi(command, arguments, hasArgs);
                    case "mostralivello" -> bg.showDifficulty();
                    case "mostranavi" -> bg.showShips();
                    case "gioca" -> handleGioca();
                    case "svelagriglia" -> handleSvelaGriglia();
                    case "tempo" -> handleTempo(arguments, hasArgs);
                    case "mostratentativi" -> bg.printAttempts();
                    case "standard", "large", "extralarge" -> handleTagliaGriglia(command, hasArgs);
                    default -> System.out.println("Comando non riconosciuto.");
                }
            } else {
                handleMossa(input);
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
        System.out.println("/tempo : permette di impostare il tempo massimo di gioco, in minuti");
        System.out.println("/facile : imposta il gioco a livello facile, si hanno 50 tentativi massimi falliti. ");
        System.out.println("Può essere specificato un numero di tentativi massimi falliti, esempio: /facile 30. ");
        System.out.println("/medio : imposta il gioco a livello medio, si hanno 30 tentativi massimi falliti ");
        System.out.println("Può essere specificato un numero di tentativi massimi falliti, esempio: /medio 20. ");
        System.out.println("/difficile : imposta il gioco a livello difficile, si hanno 10 tentativi massimi falliti ");
        System.out.println("Può essere specificato un numero di tentativi massimi falliti, esempio: /difficile 5. ");
        System.out.print("/tentativi <numero>: imposta il numero massimo di tentativi falliti a <numero>. \n");
        System.out.print("/mostranavi : permette di visualizzare la sua dimensione in quadrati e il numero di navi ");
        System.out.print("da affondare.\n -Cacciatorpediniere    ⊠⊠        esemplari: 4\n");
        System.out.println(" -Incrociatore          ⊠⊠⊠      esemplari: 3");
        System.out.println(" -Corazzata             ⊠⊠⊠⊠     esemplari: 2");
        System.out.println(" -Portaerei             ⊠⊠⊠⊠⊠    esemplari: 1");
        System.out.print("/svelagriglia : permette di visualizzare la griglia 10x10, con le righe numerate ");
        System.out.print("da 1 a 10 e le colonne numerate da A a J, e tutte le navi posizionate al suo interno\n");
        System.out.print("/standard : imposta la taglia deglia griglia a 10x10\n");
        System.out.print("/large : imposta la taglia deglia griglia a 18x18\n");
        System.out.print("/extralarge : imposta la taglia deglia griglia a 26x26\n");

    }

    private static void handleEsci() {
        System.out.print("Sei sicuro di voler chiudere il gioco? S/N: ");
        String conferma = s.nextLine();
        if (conferma.equalsIgnoreCase("S")) {
            System.out.println("Chiusura del gioco in corso...");
            exit = true;
        } else {
            System.out.println("Puoi continuare a giocare.");
        }
    }

    private static void handleDifficolta(final String command, final String[] arguments, final boolean hasArgs) {
        if (!hasArgs) {
            bg.setDifficulty(command, null);
            System.out.println("Difficoltà impostata a " + command + ".");
        } else {
            try {
                int valore = Integer.parseInt(arguments[0]);
                bg.setDifficulty(command, valore);
                System.out.println("OK");
            } catch (NumberFormatException e) {
                System.err.println("Comando non valido: utilizza /" + command + " <numero>.");
            }
        }
    }

    private static void handleTentativi(final String command, final String[] arguments, final boolean hasArgs) {
        if (hasArgs) {
            try {
                int valore = Integer.parseInt(arguments[0]);
                bg.setDifficulty(command, valore);
                System.out.println("OK");
            } catch (NumberFormatException e) {
                System.err.println("Comando non valido: utilizza /tentativi <numero>.");
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.err.println("Comando non valido: utilizza /tentativi <numero>.");
        }
    }
    private static void handleTagliaGriglia(final String command, final boolean hasArgs) {
        if (!hasArgs) {
            bg.setGridSize(command);
            System.out.println("Taglia griglia impostata a " + command + ".");
        } else {
            try {
                bg.setGridSize(command);
                System.out.println("OK");
            } catch (NumberFormatException e) {
                System.err.println("Comando non valido: utilizza /" + command + " <numero>.");
            }
        }
    }

    private static void handleGioca() {
        try {
            bg.newGame();
            System.out.println("Navi posizionate e partita iniziata.");
            bg.revealHitsGrid();
        } catch (GameAlreadyRunningException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void handleSvelaGriglia() {
        System.out.println("Griglia delle navi:");
        bg.revealShipGrid();
    }

    private static void handleTempo(final String[] arguments, final boolean hasArgs) {
        if (!hasArgs) {
            System.out.println("Comando non valido: utilizza /tempo <numero>.");
        } else {
            try {
                int val = Integer.parseInt(arguments[0]);
                if (val <= 0) {
                    System.out.println("Comando non valido: il valore del tempo deve essere >= 0.");
                } else {
                    bg.setTime(val);
                    System.out.println("OK");
                }
            } catch (NumberFormatException e) {
                System.err.println("Comando non valido: utilizza /tempo <numero>.");
            }
        }
    }

    private static void handleMossa(final String input) {
        if (bg.isGameRunning()) {
            StringTokenizer tkn = new StringTokenizer(input, "-");
            if (tkn.countTokens() == 2) {
                try {
                    char lettera;
                    String token = tkn.nextToken();
                    if (Character.isLetter(token.charAt(0))) {
                        lettera = token.toUpperCase().charAt(0);
                    } else {
                        System.out.println("Comando non riconosciuto.");
                        return;
                    }
                    token = tkn.nextToken();
                    int numero = Integer.parseInt(token);
                    Pair p = new Pair(lettera, numero);
                    bg.makeMove(p);
                } catch (NumberFormatException e) {
                    System.out.println("Comando non riconosciuto.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Comando non riconosciuto.");
            }
        } else {
            System.out.println("Comando non riconosciuto.");
        }
    }
}
