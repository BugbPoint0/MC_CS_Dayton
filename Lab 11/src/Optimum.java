


public class Optimum {
	double optimalObjValue;
	double x;
	double y;
	double z;
	
	public Optimum(double optY, double xOpt, double yOpt, double zOpt) {
		optimalObjValue = optY;
		x=xOpt;
		y=yOpt;
		z=zOpt;
	}
	
	public double getOptimum () {
		return optimalObjValue;
	}
	
	public double getOptX () {
		return x;
	}
	
	public double getOptY () {
		return y;
	}
	
	public double getOptZ () {
		return z;
	}

}
