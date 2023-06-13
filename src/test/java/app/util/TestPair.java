package app.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import it.uniba.app.util.Pair;
public class TestPair {

        @Test
        public void testValidPairCreation() {
            Pair pair = new Pair('A', 1);
            Assertions.assertEquals('A', pair.getFirst());
            Assertions.assertEquals(1, pair.getSecond());
        }

        @Test
        public void testInvalidFirstValue() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                Pair pair = new Pair('Z', 1);
            });
        }

        @Test
        public void testInvalidSecondValue() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                Pair pair = new Pair('A', 10);
            });
        }

        @Test
        public void testToArray() {
            Pair pair = new Pair('C', 4);
            int[] array = pair.toArray();
            Assertions.assertArrayEquals(new int[]{2, 3}, array);
        }

        @Test
        public void testEquals() {
            Pair pair1 = new Pair('D', 5);
            Pair pair2 = new Pair('D', 5);
            Pair pair3 = new Pair('E', 6);

            Assertions.assertEquals(pair1, pair2);
            Assertions.assertNotEquals(pair1, pair3);
        }

        @Test
        public void testHashCode() {
            Pair pair1 = new Pair('F', 7);
            Pair pair2 = new Pair('F', 7);
            Pair pair3 = new Pair('G', 8);

            Assertions.assertEquals(pair1.hashCode(), pair2.hashCode());
            Assertions.assertNotEquals(pair1.hashCode(), pair3.hashCode());
        }

        @Test
        public void testToString() {
            Pair pair = new Pair('H', 9);
            Assertions.assertEquals("H-9", pair.toString());
        }
    }
