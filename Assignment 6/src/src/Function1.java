




public class Function1 extends Function {

	@Override
	public double fnValue(double x) {
		if (x==0.0) 
			return Double.MAX_VALUE;
		else 
			return 1/x;
	}
	
	public String toString() {
		return "1/x";
	}

}
