import java.io.IOException;
/**
 * The primary goal of this Application class is to simply instantiate the
 * Hangman class, and then run the playGame method.
 * 
 * @author Jose Garcia Balboa
 * @version 1.0
 * @since 2020-02-02
 */
public class Application {

	public static void main(String[] args) throws IOException {

		Hangman h = new Hangman();
		h.playGame();
	}
	
}