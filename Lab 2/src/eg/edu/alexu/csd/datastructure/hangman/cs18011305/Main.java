package eg.edu.alexu.csd.datastructure.hangman.cs18011305;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("Dictionary.txt")).useDelimiter(",\\s*");
        List<String> dictionary = new ArrayList<String>();
        String token1 = "";
        while (scanner.hasNext()) {
            // find next line
            token1 = scanner.next();
            dictionary.add(token1);
        }
        scanner.close();
        String[] tempsArray = dictionary.toArray(new String[0]);

        for (String s : tempsArray) {
            System.out.println(s);
        }
    }
}
