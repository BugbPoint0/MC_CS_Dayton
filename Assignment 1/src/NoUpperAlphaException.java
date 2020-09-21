
public class NoUpperAlphaException extends RuntimeException{

	public NoUpperAlphaException() {
		super("The password must contain at least one uppercase alphabetic character");
	}
	
	/**
	 * @param message : A message that displays describing the exception
	 */
	public NoUpperAlphaException(String message) {
		super(message);
	}
	
}
