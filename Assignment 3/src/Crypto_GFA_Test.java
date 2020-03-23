

public class Crypto_GFA_Test {

	public static void main(String[] args) {

		String str1 = "\"THIS TEST SHOULD SUCCEED\"";
		String str4 = "HELLO WORLD"; 
		String str5 = "IFMMP!XPSME";


		boolean good = CryptoManager.stringInBounds(str1);	
		System.out.println(str1+" Is it in bounds? "+good);
	}
}
