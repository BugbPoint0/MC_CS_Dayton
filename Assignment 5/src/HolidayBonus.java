// David Dayton 

public class HolidayBonus {

	
	public HolidayBonus() { } // Blank constructor that does nothing
	
	public static double[]	calculateHolidayBonus(double[][] data, double high, double low, double other) {
		double bonusAmounts [] = new double [data.length];
		
		// determine number of categories there are 
		int numOfCategories = 0;
		for (int row = 0; row < data.length; row++) {
			if (numOfCategories < data[row].length) {
				numOfCategories = data[row].length;
			}
		}
		
		// find the highest, lowest, and other selling store for each category
		for (int categories = 0; categories < numOfCategories; categories++) {
			int highestStore = TwoDimRaggedArrayUtility.getHighestInColumnIndex(data, categories);
			int lowestStore = TwoDimRaggedArrayUtility.getLowestInColumnIndex(data, categories);
			
			if (TwoDimRaggedArrayUtility.getHighestInColumn(data, categories) > 0) {
				bonusAmounts[highestStore] += high;
			}
			
			if ((TwoDimRaggedArrayUtility.getLowestInColumn(data, categories) > 0) && (highestStore != lowestStore)) {
				bonusAmounts[lowestStore] += low;
			}
			
			for (int store = 0; store < data.length; store++) {
				if ((categories >= data[store].length) || (store == highestStore) || (store == lowestStore) || data[store][categories] <= 0) {
					continue; // skip this store for this category
				}
				else 
				{
					bonusAmounts[store] += other;
				}
			}	
		}
		
		return bonusAmounts;
	}
	
	public static double calculateTotalHolidayBonus(double[][] data, double high, double low, double other) {
		double totalBonus = 0.d;

		double bonusAmounts [] = calculateHolidayBonus(data, high, low, other);
		
		for (double x : bonusAmounts) {
			totalBonus += x;
		}
		
		return totalBonus;
	}
	
}
