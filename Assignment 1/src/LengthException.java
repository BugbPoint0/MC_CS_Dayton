
public class LengthException extends RuntimeException {

	public LengthException() {
		super("The password must be at least 6 characters long");
	}
	
	/**
	 * @param message : A message that displays describing the exception
	 */
	public LengthException(String message) {
		super(message);
	}
	
}
