package clueGame;

public class BadConfigFormatException extends Exception {

	public BadConfigFormatException() {
		super("Bad Configuration of either Setup or Layout file");
	}

	public BadConfigFormatException(String message) {
		super(message);
	}

	

}
