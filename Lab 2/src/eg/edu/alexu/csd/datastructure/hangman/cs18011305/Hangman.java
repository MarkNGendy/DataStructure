package eg.edu.alexu.csd.datastructure.hangman.cs18011305;
import java.util.*;
import java.io.*;

public class Hangman implements IHangman {

    private String[] words;
    private String chosenWord;
    private String dashedWord;
    private int max = 0;
    private int numOfCorrectLetters = 0;

    public void readFile(String file) {
        // Assumption : Each Line in the dictionary file contains a word
        try {
            int numOfWords = getNumberOfWords(file);
            String[] words = getWords(file, numOfWords);
            setDictionary(words);
        } catch (FileNotFoundException e) {
            setDictionary(new String[0]);
        }
    }

    @Override
    public void setDictionary(String[] words) {
        this.words = words;
    }

    @Override
    public String selectRandomSecretWord() {
        if (words == null || words.length == 0) {
            return null;
        }
        Random random = new Random();
        chosenWord = words[random.nextInt(words.length)];
        updateDashedWord(chosenWord.length());
        numOfCorrectLetters = 0;
        return chosenWord;
    }

    @Override
    public String guess(Character c) throws Exception {
        //Ignoring null character
        if (c == null) {
            return dashedWord;
        }

        if (chosenWord == null) {
            return null;
        }

        //Exceptions Validations
        if (!(Character.isLetter(c))) {
            throw new Exception("Invalid Character");
        }

        if (isBuggyWord(chosenWord)) {
            throw new Exception("Buggy word is chosen from dictionary");
        }

        //Returning null validations
        boolean found = false;
        StringBuilder dashedWordBuilder = new StringBuilder();
        for (int i = 0; i < chosenWord.length(); i++) {
            if (Character.toLowerCase(c) == Character.toLowerCase(chosenWord.charAt(i))) {
                dashedWordBuilder.append(chosenWord.charAt(i));
                numOfCorrectLetters++;
                found = true;
            } else if(dashedWord.charAt(i) != '-') {
                dashedWordBuilder.append(dashedWord.charAt(i));
            } else {
                dashedWordBuilder.append('-');
            }
        }

        if (!found) {
            max--;
        }
        if (max == 0) {
            return null;
        }
        if (numOfCorrectLetters == chosenWord.length()) {
            return null;
        }

        dashedWord = dashedWordBuilder.toString();
        return dashedWord;
    }

    @Override
    public void setMaxWrongGuesses(Integer max) {
        if (max == null) this.max = 1;
        else if (max >= 0) this.max = max;
    }

    private void updateDashedWord(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append('-');
        }
        dashedWord = builder.toString();
    }

    private String[] getWords(String file, int numOfWords) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(file));
        String[] words = new String[numOfWords];
        for (int i = 0; i < numOfWords; i++) {
            words[i] = scanner.next();
        }
        scanner.close();
        return words;
    }

    private int getNumberOfWords(String file) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(file));
        int numOfWords = 0;
        while (scanner.hasNextLine()){
            scanner.nextLine();
            numOfWords++;
        }
        scanner.close();
        return numOfWords;
    }

    private Boolean isBuggyWord(String str) {
        for (int i = 0; i < str.length(); i++) {
            if(!Character.isLetter(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Hangman hangman = new Hangman();
        System.out.println(System.getProperty("user.dir"));
        hangman.readFile("Dictionary.txt");
        hangman.selectRandomSecretWord();
        hangman.setMaxWrongGuesses(5);
        try {
            System.out.println(hangman.guess('a'));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

