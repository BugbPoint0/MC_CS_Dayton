public class ArraySum {

	public int sumOfArray (Integer[] a,int index) {
		if (index <= 0) {
			return a[index];
		} else {
			return (sumOfArray(a,(index - 1)) + a[index]);
		}
	}
	
}
