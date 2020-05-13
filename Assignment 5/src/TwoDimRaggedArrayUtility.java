import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

// David Dayton

public class TwoDimRaggedArrayUtility {
	
	public TwoDimRaggedArrayUtility() { } // Blank constructor that does nothing

	public static double[][] readFile(java.io.File file) throws java.io.FileNotFoundException {
		
		String tempArray [][] = new String[10][];
		Scanner scan = new Scanner(file);
		int tempRow = 0;
	
		// read the file into a temparay array of strings
		while(scan.hasNextLine()) {
			String row [] = scan.nextLine().split(" ");
			tempArray[tempRow] = new String[row.length];
			for (int col = 0; col < row.length; col++) {
				tempArray[tempRow][col] = row[col];
			}
			tempRow++;
		}
		
		scan.close();
		
		// Declare the array that is going to be returned
		double array [][] = new double[tempRow][];  
		  
		// Fill the array with the non-null contents of the temp array 
		for (int row = 0; row < array.length; row++)  {
			array[row] = new double[tempArray[row].length];
			for (int column = 0; column < tempArray[row].length; column++) {
				array[row][column] = Double.parseDouble(tempArray[row][column]);
			}
		}  
	
		return array; 
	}
	
	public static void writeToFile(double[][] data, java.io.File outputFile) throws IOException {
		
		FileWriter write = new FileWriter(outputFile);
		PrintWriter pw = new PrintWriter(write);
		
		for (int row = 0; row < data.length; row++)  {
			for (int column = 0; column < data[row].length; column++) {
				pw.print(data[row][column]);
				pw.append(' ');
			}
			pw.println();
		}
			
		pw.close();
	}
	
	
	public static double getTotal(double[][] data) {
		double total = 0.d;
		
		for (int row = 0; row < data.length; row++)  {
			for (int column = 0; column < data[row].length; column++) {
				total += data[row][column];
			}
		}
		
		return total;
	}
	
	public static double getAverage(double[][] data) {
		int totalNumOfElements = 0;
		double total = getTotal(data);
		
		for (int row = 0; row < data.length; row++)  {
			for (int column = 0; column < data[row].length; column++) {
				totalNumOfElements++;
			}
		}
		
		return (total/totalNumOfElements);
	}
	
	
	public static double getRowTotal(double[][] data, int row) {
		double rowTotal = 0.d;
		
		for (int column = 0; column < data[row].length; column++) {
			rowTotal += data[row][column];
		}
		
		return rowTotal;
	}
	
	public static double getColumnTotal(double[][] data, int col) {
		double colTotal = 0.d;
		
		for (int row = 0; row < data.length; row++) {
			if (col >= data[row].length) {
				continue;
			}
			colTotal += data[row][col];
		}
		
		return colTotal;
	}
	
	public static double getHighestInRow(double[][] data, int row) {
		double highestInRow = -1.d;
			
		for (int column = 0; column < data[row].length; column++) {
			if (row >= data.length) {
				continue;
			}
			if ((highestInRow < data[row][column])) {
				highestInRow = data[row][column];
			}	
		}
		
		return highestInRow;
	}
	
	public static int getHighestInRowIndex(double[][] data, int row) {
		double highestInRow = -1.d;
		int highestRowIndex = -1;
	
		for (int column = 0; column < data[row].length; column++) {
			if (row >= data.length) {
				continue;
			}
			if (highestInRow < data[row][column]) {
				highestInRow = data[row][column];
				highestRowIndex = column;
			}	
		}
		
		return highestRowIndex;
	}
	
	public static double getLowestInRow(double[][] data, int row) {
		double lowestInRow = 1000000.d;
		for (int column = 0; column < data[row].length; column++) { 
			if (row >= data.length) {
				continue;
			}
			if (lowestInRow > data[row][column]) {
				lowestInRow = data[row][column];
			}	
		}
		
		return lowestInRow;
	}
	
	public static int getLowestInRowIndex(double[][] data, int row) {
		double lowestInRow = 1000000.d;
		int lowestRowElement = -1;
		
		for (int column = 0; column < data[row].length; column++) {
			if (row >= data.length) {
				continue;
			}
			if (lowestInRow > data[row][column]) {
				lowestInRow = data[row][column];
				lowestRowElement = column;
			}	
		}
		
		return lowestRowElement;
	}
	
	public static double getHighestInColumn(double[][] data, int col) {
		double highestInCol = -1.d;
		
		for (int row = 0; row < data.length; row++) {
			if (col >= data[row].length) {
				continue;
			}
			if (highestInCol < data[row][col]) {
				highestInCol = data[row][col];
			}
		}
		
		return highestInCol;
	}
	
	public static int getHighestInColumnIndex(double[][] data, int col) {
		double highestInCol = -1.d;
		int highestInColIndex = -1;
		
		for (int row = 0; row < data.length; row++) {
			if (col >= data[row].length) {
				continue;
			}
			if (highestInCol < data[row][col]) {
				highestInCol = data[row][col];
				highestInColIndex = row;
			}
		}
		
		return highestInColIndex;
	}

	public static double getLowestInColumn(double[][] data, int col) {
		double lowestInCol = 1000000.d;
		
		for (int row = 0; row < data.length; row++) {
			if (col >= data[row].length) {
				continue;
			}
			if (lowestInCol > data[row][col]) {
				lowestInCol = data[row][col];
			}
		}
	
		return lowestInCol;
	}
	
	
	public static int getLowestInColumnIndex(double[][] data, int col) {
		double lowestInCol = 1000000.d;
		int lowestInColIndex = -1;
		
		for (int row = 0; row < data.length; row++) {
			if (col >= data[row].length) {
				continue;
			}
			if (lowestInCol > data[row][col]) {
				lowestInCol = data[row][col];
				lowestInColIndex = row;
			}
		}
	
		return lowestInColIndex;
	}
	
	public static double getHighestInArray(double[][] data) {
		double highestInArray = -1.d;
		
		for (int row = 0; row < data.length; row++)  {
			for (int column = 0; column < data[row].length; column++) {
				if (highestInArray < data[row][column]) {
					highestInArray = data[row][column];
				}
			}
		}
		
		return highestInArray;
	}
	
	
	public static double getLowestInArray(double[][] data) {
		double lowestInArray = 1000000.d;
		
		for (int row = 0; row < data.length; row++)  {
			for (int column = 0; column < data[row].length; column++) {
				if (lowestInArray > data[row][column]) {
					lowestInArray = data[row][column];
				}
			}
		}
		
		return lowestInArray;
	}
	
	
}
