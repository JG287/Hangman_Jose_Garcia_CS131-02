import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.util.Arrays;

/**
 * The primary purpose of this Hangman class is to have a method that contains
 * the core gameplay loop of how the game will operate when done by a person.
 * This class also has 2 other methods, one of which is used to load a file that
 * contains how many wins and losses someone has so it can show up when the
 * playGame method is ran, and the other method writes to the file and records
 * the amount of wins and losses that have happened once the game has been
 * played.
 * 
 * @author Jose Garcia Balboa
 * @version 1.0
 * @since 2020-02-02
 */
public class Hangman {

    private int wins;
    private int losses;
    private String currentWord;

    /**
     * This method instantiates the Dictionary class and uses the 200.txt file to
     * read from.
     */
    public Hangman() {

        Dictionary d = new Dictionary("200.txt");
    }

    /**
     * This method takes our Seed.txt file and loads the win/loss record from it.
     * After that, the file will contain values from previous runs.
     * 
     * @exception IOException on input error.
     * @see IOException.
     */
    private void loadWL() throws IOException {
        File file = new File("Seed.txt");
        Scanner scan = new Scanner(file);
        scan.useDelimiter(",");
        wins = scan.nextInt();
        losses = scan.nextInt();
        scan.close();
    }

    /**
     * This method takes our Seed.txt file and writes to it, writing the win/loss
     * record and updating it when the program is finished running.
     * 
     * @exception IOException on input error.
     * @see IOException.
     */
    private void writeWL() throws IOException {

        FileWriter myWriter = new FileWriter("Seed.txt");
        String w = String.valueOf(wins);
        String l = String.valueOf(losses);
        String result = w + "," + l;
        myWriter.write(result);
        myWriter.close();

    }

    /**
     * This method is essentially the meat and potatoes of how the Hangman game will
     * function. once playGame is executed, the game will start up and it will
     * proceed to play out like a normal Hangman game with 6 guesses. playGame will
     * also record the win/loss record of the player to a file.
     */
    public void playGame() {

        Dictionary d = new Dictionary("200.txt");
        Scanner scn = new Scanner(System.in);
        String randomWord = d.chooseWord();
        String guess;
        int count = 6;
        char[] ch = randomWord.toCharArray();
        char[] chars = new char[ch.length];
        Arrays.fill(chars, '-');
        System.out.print("Would you like to play Y/N? ");
        String userInput = scn.nextLine();
        if (userInput.equals("N")) {
            System.out.println("You have a total of " + wins + " wins and " + losses + " losses");
        }
        if (userInput.equals("Y")) {
            System.out.print(chars);
            while (!String.valueOf(chars).equals(randomWord) && count > 0) {
                boolean b = false;
                System.out.print("\nYou have " + count + " guesses left. ");
                System.out.print("\nWhat is your guess? ");
                guess = scn.next();
                char c = guess.charAt(0);
                for (int i = 0; i < chars.length; i++) {
                    if (randomWord.charAt(i) == c) {
                        chars[i] = c;
                        b = true;
                    }
                    System.out.print(chars[i]);
                }
                if (!b) {
                    --count;
                }
            }

            if (String.valueOf(chars).equals(randomWord)) {
                System.out.println("\n\nYou win!");
                System.out.println("Would you like to play again Y/N? ");
                userInput = scn.next();

                if (userInput.equals("N")) {
                    System.out.println("You have a total of " + wins + " wins and " + losses + " losses");
                }
                if (userInput.equals("Y")) {
                    this.playGame();
                }
            }
            if (count == 0) {
                System.out.println("\n\nYou lose!");
                System.out.println("You are out of guesses! Would you like to play again Y/N? ");
                userInput = scn.next();

                if (userInput.equals("N")) {
                    System.out.println("You have a total of " + wins + " wins and " + losses + " losses");
                }
                if (userInput.equals("Y")) {
                    this.playGame();
                }
            }
            scn.close();
        }
    }
}