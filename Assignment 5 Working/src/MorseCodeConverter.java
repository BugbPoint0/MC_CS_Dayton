import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author David Dayton
 */
public class MorseCodeConverter {

	static MorseCodeTree codeTree = new MorseCodeTree();
	
	/**
	 * default constructor
	 */
	MorseCodeConverter() {
		//codeTree = new MorseCodeTree();
	}
	
	/**
	 * Prints the tree's content into a string.
	 * returns a string with all the data in the tree in LNR order with an space in between them. 
	 * Uses the toArrayList method in MorseCodeTree It should return the data in this order:
	   "h s v i f u e l r a p w j b d x n c k y t z g q m o"
	 * Note the extra space between j and b - that is because there is an empty string that is the root, and in the LNR traversal, 
	   the root would come between the right most child of the left tree (j) and the left most child of the right tree (b). 
	 * This is used for testing purposes to make sure the MorseCodeTree has been built properly.
	 * @return The contents of the tree in a string
	 */
	public static String printTree() {
		ArrayList<String> list = codeTree.toArrayList();
		String tree;
		tree = list.toString();
		tree = tree.substring(1, tree.length()-1);
		tree = tree.replace(",", "");
		return tree;
	}
	
	/**
	 * Converts Morse code into English. Each letter is delimited by a space (‘ ‘). Each word is delimited by a ‘/’.
	 * Example: code = ".... . .-.. .-.. --- / .-- --- .-. .-.. -..", string returned = "Hello World"
	 * @param code The string of Morse code the user wants to convert to standard English. 
	 * @return The standard English that the Morse code translates to.
	 */
	public static String convertToEnglish(String code) {
		
		String[] codeSplitBySpace = code.split(" ");
		String translatedString = "";
		
		for (int x = 0; x < codeSplitBySpace.length; x++) {
			if (!codeSplitBySpace[x].equals("/")) {
				translatedString += codeTree.fetch(codeSplitBySpace[x]);
			} else {
				translatedString += " ";
			}	
		}
		
		return translatedString;
		
	}
	
	/**
	 * Converts a file of Morse code into English Each letter is delimited by a space (‘ ‘). Each word is delimited by a ‘/’. 
	 * Example: a file that contains ".... . .-.. .-.. --- / .-- --- .-. .-.. -..", string returned = "Hello World"
	 * @param codeFile The file that contains the string of Morse code that the user wants to convert to standard English. 
	 * @return code The string of Morse code the user wants to convert to standard. 
	 * @throws FileNotFoundException
	 */
	public static String convertToEnglish(File codeFile) throws FileNotFoundException {
		Scanner readFile = new Scanner(codeFile);
		String translatedString = "";
		try {
			if (codeFile != null) {
				String code = readFile.nextLine();
				String[] codeSplitBySpace = code.split(" ");
				for (int x = 0; x < codeSplitBySpace.length; x++) {
					if (!codeSplitBySpace[x].equals("/")) {
						translatedString += codeTree.fetch(codeSplitBySpace[x]);
					} else {
						translatedString += " ";
					}	
				}
			} else {
				readFile.close();
				throw new FileNotFoundException();
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		readFile.close();
		return translatedString;
		
	}
	 
}
