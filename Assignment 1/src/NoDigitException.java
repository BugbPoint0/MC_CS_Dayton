
public class NoDigitException extends RuntimeException {

	public NoDigitException() {
		super("The password must contain at least one digit");
	}

	/**
	 * @param message : A message that displays describing the exception
	 */
	public NoDigitException(String message) {
		super(message);
	}



}
