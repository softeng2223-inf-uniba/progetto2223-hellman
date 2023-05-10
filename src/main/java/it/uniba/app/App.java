package it.uniba.app;

/**
 * Main class of the application.
 */
public final class App {

    public static void main(String[] args) {
        if(args.length == 1 && (args[0].equalsIgnoreCase("-h") || args[0].equalsIgnoreCase("--help"))) {
            showHelp();
        }
    }

    private static void showHelp(){

    }
}
