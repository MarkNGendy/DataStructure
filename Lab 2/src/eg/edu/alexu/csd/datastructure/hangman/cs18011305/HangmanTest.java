package eg.edu.alexu.csd.datastructure.hangman.cs18011305;


import org.junit.Test;
import java.io.FileNotFoundException;
import static org.junit.Assert.*;

/**
 * @author Mark Nader Fathy
 */

public class HangmanTest {

    @Test
    public void testSetDictionary() throws FileNotFoundException {
        Hangman hangman = new Hangman();
        String[] testing = {"apple", "banana", "carrot", "bird"};
        hangman.setDictionary(testing);
        String randomWord = hangman.selectRandomSecretWord();
        assertNotNull(randomWord);
        assertTrue(randomWord.length() > 3);
    }

    @Test
    public void testEmptyDictionary() {
        Hangman hangman = new Hangman();
        String[] testing = new String[0];
        hangman.setDictionary(testing);
        String randomWord = hangman.selectRandomSecretWord();
        assertNull(randomWord);
    }

    @Test
    public void testNullDictionary() {
        Hangman hangman = new Hangman();
        hangman.setDictionary(null);
        String randomWord = hangman.selectRandomSecretWord();
        assertNull(randomWord);
    }

    @Test
    public void testRandomWord() {
        Hangman hangman = new Hangman();
        String[] testing = {"Apple", "Orange", "Banana", "GrapeFruit"};
        hangman.setDictionary(testing);
        String randomWord = hangman.selectRandomSecretWord();
        boolean isFound = false;
        for (String word : testing) {
            if (word.equals(randomWord)) {
                isFound = true;
            }
        }
        assertTrue(isFound);
    }

    @Test
    public void testOneWord() throws Exception {
        Hangman hangman = new Hangman();
        String[] testing = {"Orange"};
        hangman.setDictionary(testing);
        String randomWord = hangman.selectRandomSecretWord();
        assertEquals(randomWord, "Orange");
        hangman.setMaxWrongGuesses(15);
        String guessed = hangman.guess('o');
        assertEquals("O-----", guessed);
    }

    @Test
    public void testNullSecretWord() throws Exception {
        Hangman hangman = new Hangman();
        String guessed = hangman.guess('o');
        assertNull(guessed);
    }

    @Test
    public void testLosing() throws Exception {
        Hangman hangman = new Hangman();
        String[] testing = {"Orange"};
        hangman.setDictionary(testing);
        hangman.selectRandomSecretWord();
        hangman.setMaxWrongGuesses(2);
        String guessed = hangman.guess('o');
        assertEquals("O-----", guessed);
        guessed = hangman.guess('v');
        assertEquals("O-----", guessed);
        guessed = hangman.guess('o');
        assertEquals("O-----", guessed);
        guessed = hangman.guess('r');
        assertEquals("Or----", guessed);
        guessed = hangman.guess('o');
        assertEquals("Or----", guessed);
        guessed = hangman.guess('b');
        assertNull(guessed);

    }

}
