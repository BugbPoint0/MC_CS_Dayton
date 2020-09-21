
public class InvalidSequenceException extends RuntimeException {

	public InvalidSequenceException() {
		super("The password cannot contain more than two of the same character in sequence");
	}

	/**
	 * @param message : A message that displays describing the exception
	 */
	public InvalidSequenceException(String message) {
		super(message);
	}


}
