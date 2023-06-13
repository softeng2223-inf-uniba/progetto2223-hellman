package app;
import it.uniba.app.Difficulty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestDifficulty {
        @Test
        public void testEnumValues() {
            Assertions.assertEquals(Difficulty.EASY, Difficulty.valueOf("EASY"));
            Assertions.assertEquals(Difficulty.MEDIUM, Difficulty.valueOf("MEDIUM"));
            Assertions.assertEquals(Difficulty.HARD, Difficulty.valueOf("HARD"));
        }

        @Test
        public void testToString() {
            Assertions.assertEquals("EASY", Difficulty.EASY.toString());
            Assertions.assertEquals("MEDIUM", Difficulty.MEDIUM.toString());
            Assertions.assertEquals("HARD", Difficulty.HARD.toString());
        }
}


