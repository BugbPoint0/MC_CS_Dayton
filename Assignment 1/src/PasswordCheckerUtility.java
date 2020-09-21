import java.util.ArrayList;
import java.util.regex.*;

public class PasswordCheckerUtility {

	
	// Default constructor
	public PasswordCheckerUtility() {
		
	}
	
	
	/**
	 * Checks to see if password is valid
	 * @param passwordString
	 * @return true if password is valid else false
	 * @throws LengthException
	 * @throws NoDigitException
	 * @throws NoUpperAlphaException
	 * @throws NoLowerAlphaException
	 * @throws NoSpecialCharacterException
	 * @throws InvalidSequenceException
	 */
	public static boolean isValidPassword(String passwordString) throws LengthException, NoDigitException, 
	NoUpperAlphaException, NoLowerAlphaException, NoSpecialCharacterException, InvalidSequenceException {
		
		boolean isValidPassword = false;
		
		if (isLongEnough(passwordString) == true) {
			isValidPassword = true;
		} else {
			isValidPassword = false;
		}
		
		if (hasUpperAlpha(passwordString) == true) {
			isValidPassword = true;
		} else {
			isValidPassword = false;
		}
		
		if (hasLowerAlpha(passwordString) == true) {
			isValidPassword = true;
		} else {
			isValidPassword = false;
		}
		
		if (hasNumber(passwordString) == true) {
			isValidPassword = true;
		} else {
			isValidPassword = false;
		}
		
		if (hasSpecialChar(passwordString) == true) {
			isValidPassword = true;
		} else {
			isValidPassword = false;
		}
		
		if(hasInvalidSequence(passwordString) == false) {
			isValidPassword = true;
		} else {
			isValidPassword = false;
		}
		
		return isValidPassword; 
	}
	
	/**
	 * Checks to see if password is weak (between 6 and 9 chars)
	 * @param passwordString
	 * @return true if password is weak else false
	 */
	public static boolean isWeakPassword(String passwordString) {
		
	boolean isWeakPassword = false;
			
		try {
			if (passwordString.length() >= 6 && passwordString.length() <= 9) {
				throw new WeakPasswordException();
			} else {
				isWeakPassword = true;
			}
		} catch (WeakPasswordException e) {
			System.out.println(e.getMessage());
			isWeakPassword = false;
		}
		
		return isWeakPassword; 	
	}
	
	
	/**
	 * Gets every invalid password and puts them into an ArrayList
	 * @param passwords
	 * @return the ArrayList of each password
	 * 
	 */
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
		
		ArrayList<String> invalidPasswords = new ArrayList<String>();
		
		for (String i : passwords) {
			if (isValidPassword(i) == false) {
				invalidPasswords.add(i);
			}
		}
		
		return invalidPasswords; 
	}
	
	/**
	 * Checks to see if the password is longer than 6 characters
	 * @param passwordString
	 * @return true if the password is longer than 6 char otherwise false
	 */
	private static boolean isLongEnough(String passwordString) {
		boolean is6CharLong = false;
		
		try {
			if (passwordString.length() < 6) {
				throw new LengthException();
			} else {
				is6CharLong = true;
			}
		} catch (LengthException e) {
			System.out.println(e.getMessage());
			is6CharLong = false;
		}
	 
		return is6CharLong;
	}
	
	/**
	 * Checks to see if the password contains an uppercase letter
	 * @param passwordString
	 * @return true if password contains an uppercase letter else return false
	 */
	private static boolean hasUpperAlpha(String passwordString) {
		boolean hasUppercaseChar = false;
		
		try {
			if (passwordString.equals(passwordString.toLowerCase())) {
				throw new NoUpperAlphaException();
			} else {
				hasUppercaseChar = true;
			}
		} catch (NoUpperAlphaException e) {
			e.getMessage();
			hasUppercaseChar = false;
		}
		
		return hasUppercaseChar;
	}
	
	/**
	 * Checks to see if the password contains a lowercase letter
	 * @param passwordString
	 * @return true if password contains an lowercase letter else return false
	 */
	private static boolean hasLowerAlpha(String passwordString) {
		boolean hasLowercaseChar = false;
		
		try {
			if (passwordString.equals(passwordString.toUpperCase())) {
				throw new NoLowerAlphaException();
			} else {
				hasLowercaseChar = true;
			}
		} catch (NoLowerAlphaException e) {
			e.getMessage();
			hasLowercaseChar = false;
		}
		
		return hasLowercaseChar;
	}
	
	
	/**
	 * Checks to see if password has a number
	 * @param passwordString
	 * @return true if password contains a Number else return false
	 */
	private static boolean hasNumber(String passwordString) {
		boolean hasNumber = false;
		
		try { 
			if (passwordString.matches(".*\\d.*")) {
				hasNumber = true;
			} else {
				throw new NoDigitException();
			}
		} catch(NoDigitException e) {
			e.getMessage();
			hasNumber = false;
		}
		
		return hasNumber;
	}
	
	/**
	 * Checks to see if password has a special character
	 * @param passwordString
	 * @return true if password has a special character else return false
	 */
	private static boolean hasSpecialChar(String passwordString) {
		boolean hasSpecialChar = false;
		
		try { 
			if (!passwordString.matches("[a-zA-Z0-9]*")) {
				hasSpecialChar = true;
			} else {
				throw new NoSpecialCharacterException();
			}
		} catch(NoSpecialCharacterException e) {
			e.getMessage();
			hasSpecialChar = false;
		}
		
		return hasSpecialChar;
	}
	
	/**
	 * Checks to see if password has an invalid sequence (more than 2 of the same character)
	 * @param passwordString
	 * @return true 
	 */
	private static boolean hasInvalidSequence(String passwordString) {
		boolean hasInvalidSequence = false;
		Pattern pt = Pattern.compile("(.)\\1{2}");
		Matcher mt = pt.matcher(passwordString);
		
		try { 
			if (mt.find()) {
				throw new InvalidSequenceException();
			} else {
				hasInvalidSequence = false;
			}
		} catch(InvalidSequenceException e) {
			e.getMessage();
			hasInvalidSequence = true;
		}
		
		return hasInvalidSequence;
	}
	
}
