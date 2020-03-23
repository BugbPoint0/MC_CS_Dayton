
// Does all of the encrypting and decrypting in both Caesar Cipher and Bellaso Cipher
// David Dayton

public class CryptoManager {
	
	private static final char LOWER_BOUND = ' ';
	private static final char UPPER_BOUND = '_';
	private static final int RANGE = UPPER_BOUND - LOWER_BOUND + 1;

	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes 
	 * according to the LOWER_BOUND and UPPER_BOUND characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean stringInBounds (String plainText) {
		boolean inBounds = false;
		
		for (int index = 0; index < (plainText.length()); index++)
		{
			int val = (int) plainText.charAt(index);
			int val2 = (int) LOWER_BOUND;
			int val3 = (int) UPPER_BOUND;
			
			System.out.println(val);
			if ((val >= val2) && (val <= val3))
			{
				inBounds = true;
			}
			else 
			{
				inBounds = false;
				break;
			}
			System.out.println(inBounds);
		}
		return inBounds;
	}

	
	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it 
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String encryptCaesar(String plainText, int key) {
		String encryptedString = new String();
		int newValue = 0;
		for (int index = 0; index < plainText.length(); index++)
		{
			newValue = plainText.charAt(index);
			newValue += key;
			while (newValue > 95)
			{
				newValue -= RANGE;
			}
			encryptedString += (char)newValue;
		}
		return encryptedString;
	}
	
	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset 
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String encryptBellaso(String plainText, String bellasoStr) {
		String encryptedString = new String();
		int newValue = 0;
		while (plainText.length() > bellasoStr.length())
		{
			bellasoStr += bellasoStr;
		}
		for (int index = 0; index < plainText.length(); index++)
		{	
			newValue = plainText.charAt(index);
			newValue += bellasoStr.charAt(index);
			while (newValue > 95)
			{
				newValue -= RANGE;
			}
			encryptedString += (char)newValue;
		}
		return encryptedString;
	}
	
	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String decryptCaesar(String encryptedText, int key) {
		String decryptedString = new String();
		int newValue = 0;
		for (int index = 0; index < encryptedText.length(); index++)
		{
			newValue = encryptedText.charAt(index);
			newValue -= key;
			while (newValue < 32)
			{
				newValue += RANGE;
			}
			decryptedString += (char)newValue;
		}
		return decryptedString;
	}
	
	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String decryptBellaso(String encryptedText, String bellasoStr) {
		String decryptedString = new String();
		int newValue = 0;
		while (encryptedText.length() > bellasoStr.length())
		{
			bellasoStr += bellasoStr;
		}
		for (int index = 0; index < encryptedText.length(); index++)
		{	
			newValue = encryptedText.charAt(index);
			newValue -= bellasoStr.charAt(index);
			while (newValue < 32)
			{
				newValue += RANGE;
			}
			decryptedString += (char)newValue;
		}
		return decryptedString;
	}
}
