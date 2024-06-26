package it.uniba.app;

import it.uniba.app.exceptions.GameAlreadyRunningException;
import it.uniba.app.exceptions.IllegalPositionException;
import it.uniba.app.ships.Cacciatorpediniere;
import it.uniba.app.ships.Corazzata;
import it.uniba.app.ships.Incrociatore;
import it.uniba.app.ships.Orientation;
import it.uniba.app.ships.Portaerei;
import it.uniba.app.ships.Ship;
import it.uniba.app.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.time.Instant;
import java.time.Duration;

/**
 * Classe che rappresenta il gioco.
 */
public final class BattleshipGame {
    public static final int GRID_SIZE = 10;
    /**
     * La difficoltà del gioco.
     */
    private Difficulty currentDifficulty;
    /**
     * Lista delle navi.
     */
    private List<Ship> ships = null;
    /**
     * Numero di tentativi errati massimi.
     */
    private int maxFailedAttempts;
    /**
     * Numero di tentativi errati effettuati.
     */
    private int failedAttempts;
    /**
     * Numero di colpi andati a segno.
     */
    private int hits;
    /**
     * La griglia delle navi. Contiene true nelle caselle occupate dalle navi.
     */
    private boolean[][] grid = new boolean[GRID_SIZE][GRID_SIZE];
    /**
     * La griglia dei colpi. Contiene 0 nelle caselle non colpite, 1 nelle caselle
     * colpite e 2 nelle caselle mancate.
     */
    private int[][] hitsGrid = new int[GRID_SIZE][GRID_SIZE];
    /**
     * Il numero di tentativi per la difficoltà EASY.
     */
    private static final int EASY_ATTEMPTS = 50;
    /**
     * Il numero di tentativi per la difficoltà MEDIUM.
     */
    private static final int MEDIUM_ATTEMPTS = 30;
    /**
     * Il numero di tentativi per la difficoltà HARD.
     */
    private static final int HARD_ATTEMPTS = 10;
    /**
     * Il numero di ms da aspettare per ogni iterazione del timer.
     */
    private static final int TIMER_SLEEP_TIME = 1000;
    /**
     * Durata di un minuto in secondi.
     */
    private static final int MINUTE_DURATION = 60;
    /**
     * Indica se una partita è in esecuzione.
     */
    private boolean gameRunning = false;
    /**
     * L'istante di inizio della partita.
     * @see java.time.Instant
     */
    private Instant startTime = null;
    /**
     * Il numero in minuti di durata della partita.
     */
    private int gameDuration = -1;
    /**
     * Runnable che gestisce il timer della partita.
     */
    private final Runnable timer = () -> {
        while (true) {
            try {
                Thread.sleep(TIMER_SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (gameRunning) {
                if (startTime != null && gameDuration > 0) {
                    if (Instant.now().isAfter(startTime.plusSeconds(gameDuration * MINUTE_DURATION))) {
                        gameRunning = false;
                        System.out.print("\nTempo scaduto!\nInserisci un comando: ");
                    }
                }
            }
        }
    };

    BattleshipGame() {
        currentDifficulty = Difficulty.EASY;
        maxFailedAttempts = EASY_ATTEMPTS;
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                hitsGrid[i][j] = 0;
            }
        }
        hits = 0;
        failedAttempts = 0;
        new Thread(timer).start();
    }

    void setDifficulty(final String command, final Integer customAttempts) {
        switch (command.toLowerCase()) {
            case "facile" -> {
                currentDifficulty = Difficulty.EASY;
                maxFailedAttempts = (customAttempts != null) ? customAttempts : EASY_ATTEMPTS;
            }
            case "medio" -> {
                currentDifficulty = Difficulty.MEDIUM;
                maxFailedAttempts = (customAttempts != null) ? customAttempts : MEDIUM_ATTEMPTS;
            }
            case "difficile" -> {
                currentDifficulty = Difficulty.HARD;
                maxFailedAttempts = (customAttempts != null) ? customAttempts : HARD_ATTEMPTS;
            }
            case "tentativi" -> {
                if (customAttempts != null && customAttempts > 0) {
                    maxFailedAttempts = customAttempts;
                } else {
                    throw new IllegalArgumentException("Il numero di tentativi deve essere maggiore di 0");
                }
            }
            default -> {
            }
        }
    }

    void showDifficulty() {
        System.out.println("Il livello di difficoltà impostato è : " + currentDifficulty);
        System.out.println("Il numero massimo di tentativi falliti corrispondente è : " + maxFailedAttempts);
    }

    void setTime(final int minuteNumbers) {
        startTime = Instant.now();
        gameDuration = minuteNumbers;
    }

    void showShips() {
        System.out.println("Navi da affondare:");
        System.out.println("- Cacciatorpediniere ⊠⊠           esemplari: 4");
        System.out.println("- Incrociatore       ⊠⊠⊠          esemplari: 3");
        System.out.println("- Corazzata          ⊠⊠⊠⊠         esemplari: 2");
        System.out.println("- Portaerei          ⊠⊠⊠⊠⊠        esemplari: 1");
    }

    void revealHitsGrid() {
        String gridOutput = "  ";
        for (int i = 1; i <= GRID_SIZE; i++) {
            gridOutput += " " + i + " ";
        }
        gridOutput += "\n";
        gridOutput += " |";
        for (int i = 0; i < GRID_SIZE; i++) {
            gridOutput += "-";
            for (int j = 0; j < String.valueOf(i).length(); j++) {
                gridOutput += "-";
            }
            gridOutput += "-";
        }
        gridOutput += "|\n";
        for (int i = 0; i < GRID_SIZE; i++) {
            gridOutput += (char) ('A' + i) + "|";
            for (int j = 0; j < GRID_SIZE; j++) {
                if (hitsGrid[j][i] == 0) {
                    gridOutput += "   ";
                } else {
                    if (hitsGrid[j][i] == 1) {
                        gridOutput += " X ";
                    } else {
                        gridOutput += " - ";
                    }
                    if (String.valueOf(j).length() == 2) {
                        gridOutput += " ";
                    }
                }
            }
            gridOutput += "\n";
        }

        System.out.println(gridOutput);
    }

    /**
     * Mostra la griglia delle navi.
     * La griglia sarà di questo tipo:
     * <pre>
     *     1  2  3  4  5  6  7  8  9  10
     *   |------------------------------|
     * A | 0  0  0  0  0  0  0  0  0  0 |
     * B | 0  0  0  0  0  0  0  0  0  0 |
     * C | 0  0  0  0  0  0  0  0  0  0 |
     * D | 0  0  0  0  0  0  0  0  0  0 |
     * E | 0  0  0  0  0  0  0  0  0  0 |
     * F | 0  0  0  0  0  0  0  0  0  0 |
     * G | 0  0  0  0  0  0  0  0  0  0 |
     * H | 0  0  0  0  0  0  0  0  0  0 |
     * I | 0  0  0  0  0  0  0  0  0  0 |
     * J | 0  0  0  0  0  0  0  0  0  0 |
     * </pre>
     */
    void revealShipGrid() {
        String gridOutput = "  ";
        for (int i = 1; i <= GRID_SIZE; i++) {
            gridOutput += " " + i + " ";
        }
        gridOutput += "\n";
        gridOutput += " |";
        for (int i = 0; i < GRID_SIZE; i++) {
            gridOutput += "-";
            for (int j = 0; j < String.valueOf(i).length(); j++) {
                gridOutput += "-";
            }
            gridOutput += "-";
        }
        gridOutput += "|\n";
        for (int i = 0; i < GRID_SIZE; i++) {
            gridOutput += (char) ('A' + i) + "|";
            for (int j = 0; j < GRID_SIZE; j++) {
                if (grid[j][i]) {
                    gridOutput += " ⊠ ";
                } else {
                    gridOutput += "   ";
                }
                if (String.valueOf(j).length() == 2) {
                    gridOutput += " ";
                }
            }
            gridOutput += "\n";
        }

        System.out.println(gridOutput);
    }

    boolean isGameRunning() {
        return gameRunning;
    }

    void printTimeElapsed() {
        Instant now = Instant.now();
        Duration timeElapsed = Duration.between(startTime, now);
        System.out.println("Tempo trascorso: " + timeElapsed.toMinutesPart() + ":" + timeElapsed.toSecondsPart());
        if (gameDuration == -1) {
            System.out.println("Non è stato impostato un limite di tempo per la partita!");
        } else {
            Duration timeLeft = Duration.between(now, startTime.plus(Duration.ofMinutes(gameDuration)));
            System.out.println("Tempo rimanente: " + timeLeft.toMinutesPart() + ":" + timeLeft.toSecondsPart());
        }
    }

    void printAttempts() {
        System.out.println("Tentativi effettuati:" + (failedAttempts + hits));
        System.out.println("Tentativi falliti:" + failedAttempts);
        System.out.println("Numero massimo di tentativi falliti:" + maxFailedAttempts);
    }

    void makeMove(final Pair pos) {
        int[] coords = pos.toArray();
        if (hitsGrid[coords[1]][coords[0]] != 0) {
            System.out.println("Hai già effettuato una tentativo in questa posizione!");
        } else {
            if (grid[coords[1]][coords[0]]) {
                hitsGrid[coords[1]][coords[0]] = 1;
                System.out.print("Colpito");
                Ship s = getShip(pos);
                hits++;
                s.hit();
                if (s.isSunk()) {
                    System.out.println(" e Affondato!");
                } else {
                    System.out.println("!");
                }
                boolean allSunk = true;
                for (Ship sTemp : ships) {
                    if (!sTemp.isSunk()) {
                        allSunk = false;
                        break;
                    }
                }
                if (allSunk) {
                    System.out.println("Hai vinto! Hai affondato tutte le navi!");
                    gameRunning = false;
                }
            } else {
                hitsGrid[coords[1]][coords[0]] = 2;
                failedAttempts++;
                System.out.println("Acqua!");
                if (maxFailedAttempts == failedAttempts) {
                    System.out.println("Game over! Hai raggiunto il numero di tentativi massimi!");
                    gameRunning = false;
                }
            }
            revealHitsGrid();
            System.out.println("Numero di tentativi effettuati: " + (hits + failedAttempts));
            printTimeElapsed();
        }
    }

    private Ship getShip(final Pair pos) {
        for (Ship s : ships) {
            Pair[] coords = new Pair[s.getLength()];
            int[] startingPosCoords = s.getStartingPosition().toArray();
            for (int i = 0; i < s.getLength(); i++) {
                coords[i] = s.getOrientation() == Orientation.VERTICAL
                ? new Pair(((char) ('A' + startingPosCoords[0] + i)), startingPosCoords[1] + 1)
                : new Pair(((char) ('A' + startingPosCoords[0])), startingPosCoords[1] + i + 1);
            }
            for (Pair p : coords) {
                if (p.equals(pos)) {
                    return s;
                }
            }
        }
        return null;
    }

    /**
     * Inizializza la griglia assegnando alle navi posizione ed orientamento
     * casuale.
     * Questo viene fatto attraverso "brute force", ovvero generando casualmente
     * posizione ed orientamento e verificando che non ci siano sovrapposizioni.
     *
     * @throws GameAlreadyRunningException se c'è già una partita in corso
     */
    void newGame() throws GameAlreadyRunningException {
        if (gameRunning) {
            throw new GameAlreadyRunningException("C'è già una partita in corso.");
        }

        boolean err;
        boolean[][] tempGrid = null;
        do {
            err = false;
            try {
                tempGrid = new boolean[GRID_SIZE][GRID_SIZE];
                ships = new ArrayList<>();
                Pair position = getRandomPosition(tempGrid);
                Random r = new Random();
                Orientation orientation = r.nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship portaerei = new Portaerei(orientation, position, tempGrid);
                updateGrid(tempGrid, position, orientation, portaerei);
                // corazzate
                position = getRandomPosition(tempGrid);
                orientation = r.nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship corazzata1 = new Corazzata(orientation, position, tempGrid);
                updateGrid(tempGrid, position, orientation, corazzata1);
                position = getRandomPosition(tempGrid);
                orientation = r.nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship corazzata2 = new Corazzata(orientation, position, tempGrid);
                updateGrid(tempGrid, position, orientation, corazzata2);
                // incrociatori
                position = getRandomPosition(tempGrid);
                orientation = r.nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship incrociatore1 = new Incrociatore(orientation, position, tempGrid);
                updateGrid(tempGrid, position, orientation, incrociatore1);
                position = getRandomPosition(tempGrid);
                orientation = r.nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship incrociatore2 = new Incrociatore(orientation, position, tempGrid);
                updateGrid(tempGrid, position, orientation, incrociatore2);
                position = getRandomPosition(tempGrid);
                orientation = r.nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship incrociatore3 = new Incrociatore(orientation, position, tempGrid);
                updateGrid(tempGrid, position, orientation, incrociatore3);
                // cacciatorpediniere
                position = getRandomPosition(tempGrid);
                orientation = r.nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship cacciatorpediniere1 = new Cacciatorpediniere(orientation, position, tempGrid);
                updateGrid(tempGrid, position, orientation, cacciatorpediniere1);
                position = getRandomPosition(tempGrid);
                orientation = r.nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship cacciatorpediniere2 = new Cacciatorpediniere(orientation, position, tempGrid);
                updateGrid(tempGrid, position, orientation, cacciatorpediniere2);
                position = getRandomPosition(tempGrid);
                orientation = r.nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship cacciatorpediniere3 = new Cacciatorpediniere(orientation, position, tempGrid);
                updateGrid(tempGrid, position, orientation, cacciatorpediniere3);
                position = getRandomPosition(tempGrid);
                orientation = r.nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship cacciatorpediniere4 = new Cacciatorpediniere(orientation, position, tempGrid);
                updateGrid(tempGrid, position, orientation, cacciatorpediniere4);
            } catch (IllegalPositionException e) {
                err = true;
            }
        } while (err);
        grid = tempGrid;
        gameRunning = true;
        startTime = Instant.now();
    }

    private void updateGrid(final boolean[][] tempGrid, final Pair position,
            final Orientation orientation, final Ship ship) {
        ships.add(ship);
        for (int i = 0; i < ship.getLength(); i++) {
            int[] coordinates = position.toArray();
            if (orientation == Orientation.HORIZONTAL) {
                tempGrid[coordinates[1] + i][coordinates[0] ] = true;
            } else {
                tempGrid[coordinates[1]][coordinates[0] + i] = true;
            }
        }
    }

    private Pair getRandomPosition(final boolean[][] checkGrid) {
        Pair p = null;
        boolean err;
        Random r = new Random();
        do {
            err = false;
            String alphabet = "";
            for (int i = 0; i < GRID_SIZE; i++) {
                alphabet += (char) ('A' + i);
            }
            int x = r.nextInt(GRID_SIZE);
            int y = r.nextInt(GRID_SIZE);
            p = new Pair(alphabet.charAt(x), y + 1);
            int[] coordinates = p.toArray();
            if (checkGrid[coordinates[1]][coordinates[0]]) {
                err = true;
            }
        } while (err);
        return p;
    }
    void setGameRunning(final boolean value) {
        gameRunning = value;
    }
}
