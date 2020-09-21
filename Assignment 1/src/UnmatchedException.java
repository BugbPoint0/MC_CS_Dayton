
public class UnmatchedException extends RuntimeException {

	public UnmatchedException() {
		super("The passwords do not match");
	}

	/**
	 * @param message : A message that displays describing the exception
	 */
	public UnmatchedException(String message) {
		super(message);
	}


}
