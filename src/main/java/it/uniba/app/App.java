package it.uniba.app;

import it.uniba.app.exceptions.GameAlreadyRunningException;
import it.uniba.app.exceptions.UnsetDifficultyException;

import java.nio.charset.StandardCharsets;
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
        Scanner s = new Scanner(System.in, StandardCharsets.UTF_8);
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
                    bg.setDifficulty(command);
                    System.out.println("Difficoltà impostata a facile.");
                    break;
                case "/medio":
                    bg.setDifficulty(command);
                    System.out.println("Difficoltà impostata a medio.");
                    break;
                case "/difficile":
                    bg.setDifficulty(command);
                    System.out.println("Difficoltà impostata a difficile.");
                    break;
                case "/mostralivello":
                    break;
                case "/mostranavi":
                    break;
                case "/gioca":
                    try {
                        bg.newGame();
                    } catch (UnsetDifficultyException | GameAlreadyRunningException e) {
                        System.err.println(e.getMessage());
                    }
                    System.out.println("Navi posizionate e partita iniziata.");
                    break;
                case "/svelagriglia":
                    System.out.println("Griglia delle navi:");
                    bg.revealShipGrid();
                    break;
                default:
                    System.out.println("Comando non riconosciuto.");
                    break;
            }
        } while (!exit);
    }

    private static void showHelp() {

        System.out.println("Benvenuti in 'Battaglia Navale'");
        System.out.print("Il gioco consiste nel eliminare tutte le navi nemiche, che all'avvio della partita vengono ");
        System.out.print("disposte in modo casuale all'interno di una griglia 10x10, indicando la posizione nella ");
        System.out.print("quale si vuole 'lanciare' il missile per colpirle. ");
        System.out.println("Lista comandi: \n ");
        System.out.println("/help : viene visualizzata la lista dei comandi e la sua descrizione ");
        System.out.print("/gioca : se nessuna partita e' in corso, vengono impostate casulamente le navi, in orizzont");
        System.out.print("ale o in verticale e si predispone a ricevere il primo tentativo o altri comandi\n");
        System.out.print("/esci : permette di chiudere il gioco, premendo 'Y' il gioco viene chiuso, ");
        System.out.print("premendo 'N' l'applicazione si predispone a ricevere nuovi tentativi o comandi\n");
        System.out.print("/mostralivello : permette di visualizzare il livello di gioco e il numero massimo di ");
        System.out.print("tentativi falliti\n");
        System.out.println("/facile : imposta il gioco a livello facile, si hanno 50 tentativi massimi falliti");
        System.out.println("/medio : imposta il gioc a livello medio, si hanno 30 tentativi massimi falliti ");
        System.out.println("/difficile : imposta il gioc a livello difficile, si hanno 10 tentativi massimi falliti ");
        System.out.print("/mostranavi : permette di visuaizzare la sua dimensione in quadrati e il numero di navi ");
        System.out.print("da affondare.\n -Cacciatorpediniere    [][]        esemplari: 4\n ");
        System.out.print("-Incrociatore   [][][]      esemplari: 3\n -Corazzata  [][][][]        esemplari: 2\n ");
        System.out.print("- Portaerei     [][][][][]      esemplari: 1\n");
        System.out.print("/svelagriglia : permette di visualizzare la griglia 10x10, con le righe numerate ");
        System.out.print("da 1 a 10 e le colonne numerate da A a J, e tutte le navi posizionate al suo interno\n");
    }
}
