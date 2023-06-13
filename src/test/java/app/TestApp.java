package app;

import it.uniba.app.App;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class TestApp {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void testHandleDifficolta() {
        provideInput("facile\n");
        App.main(new String[]{});
        assertEquals("Difficoltà impostata a facile.\n", getOutput());

        provideInput("medio\n");
        App.main(new String[]{});
        assertEquals("Difficoltà impostata a medio.\n", getOutput());

        provideInput("difficile\n");
        App.main(new String[]{});
        assertEquals("Difficoltà impostata a difficile.\n", getOutput());

        provideInput("facile 30\n");
        App.main(new String[]{});
        assertEquals("OK\n", getOutput());
    }

    @Test
    public void testHandleTentativi() {
        provideInput("tentativi 20\n");
        App.main(new String[]{});
        assertEquals("OK\n", getOutput());

        provideInput("tentativi abc\n");
        App.main(new String[]{});
        assertEquals("Comando non valido: utilizza /tentativi <numero>.\n", getOutput());

        provideInput("tentativi\n");
        App.main(new String[]{});
        assertEquals("Comando non valido: utilizza /tentativi <numero>.\n", getOutput());
    }

    @Test
    public void testHandleGioca() {
        provideInput("/gioca\n");
        App.main(new String[]{});
        assertEquals("Navi posizionate e partita iniziata.\n", getOutput());

        provideInput("/gioca\n");
        App.main(new String[]{});
        assertEquals("Partita già in corso.\n", getOutput());
    }

    @Test
    public void testHandleSvelaGriglia() {
        provideInput("/svelagriglia\n");
        App.main(new String[]{});
        assertEquals("Griglia delle navi:\n", getOutput());
    }

    @Test
    public void testHandleTempo() {
        provideInput("/tempo 10\n");
        App.main(new String[]{});
        assertEquals("OK\n", getOutput());

        provideInput("/tempo abc\n");
        App.main(new String[]{});
        assertEquals("Comando non valido: utilizza /tempo <numero>.\n", getOutput());

        provideInput("/tempo\n");
        App.main(new String[]{});
        assertEquals("Comando non valido: utilizza /tempo <numero>.\n", getOutput());

        provideInput("/tempo -5\n");
        App.main(new String[]{});
        assertEquals("Comando non valido: il valore del tempo deve essere >= 0.\n", getOutput());
    }

    @Test
    public void testHandleMossa() {
        provideInput("A-1\n");
        App.main(new String[]{});
        // Inserisci qui l'output atteso

        provideInput("A1\n");
        App.main(new String[]{});
        // Inserisci qui l'output atteso

        provideInput("A\n");
        App.main(new String[]{});
        // Inserisci qui l'output atteso
    }

    @Test
    public void testHandleAbbandona() {
        provideInput("S\n");
        App.main(new String[]{});
        // Inserisci qui l'output atteso

        provideInput("N\n");
        App.main(new String[]{});
        // Inserisci qui l'output atteso
    }
}
