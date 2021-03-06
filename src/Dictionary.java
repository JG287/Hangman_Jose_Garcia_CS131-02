import java.io.File;
import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.util.Scanner;
import java.util.Random;

/**
 * This Dictionary class has 2 main purposes: The first main purpose is to read
 * a file that contains 200 words, (which in our case is the 200.txt file) and
 * then store those 200 words in a variable to be used later. That variable can
 * then be used later to generate a random word that is found within that file,
 * and do it every time that method is invoked.
 * 
 * @author Jose Garcia Balboa
 * @version 1.0
 * @since 2020-02-02
 */
public class Dictionary {

	private String[] wordList = new String[200];
	private int currentCard;
	private SecureRandom randomNumbers;

	public Dictionary(String fileName) {

		readFile("src/200.txt");
	}
	/**
	 * This method is used to read a file, and then scan through each line. Once
	 * each line has been scanned, it will then store all of that information from
	 * the file into the array so it can be used later.
	 * 
	 * @param fileName is the name of the file to be read.
	 * @exception FileNotFoundException if the file cannot be found by Java.
	 * @see FileNotFoundException.
	 */
	private void readFile(String fileName) {

		File file = new File("src/200.txt");
		try (Scanner scan = new Scanner(file)) {
			int index = 0;
			while (scan.hasNext()) {
				String listWords = scan.nextLine();
				wordList[index] = listWords;
				index++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to take the Array that we made earlier, and have it
	 * generate random words every time the method is invoked. We take that, and
	 * then put it into the variable words so that we can return it.
	 * 
	 * @return this returns the words variable.
	 */
	public String chooseWord() {

		String words = wordList[new Random().nextInt(wordList.length)];
		return words;
	}
}