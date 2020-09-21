
public class NoLowerAlphaException extends RuntimeException{

	public NoLowerAlphaException() {
		super("The password must contain at least one lowercase alphabetic character");
	}
	
	/**
	 * @param message : A message that displays describing the exception
	 */
	public NoLowerAlphaException(String message) {
		super(message);
	}

}
