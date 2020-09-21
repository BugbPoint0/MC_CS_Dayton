
public class NoSpecialCharacterException extends RuntimeException {

	public NoSpecialCharacterException() {
		super("The password must contain at least one special character");
	}

	/**
	 * @param message : A message that displays describing the exception
	 */
	public NoSpecialCharacterException(String message) {
		super(message);
	}

}
