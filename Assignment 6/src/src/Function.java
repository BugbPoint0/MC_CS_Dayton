

 
public abstract class Function {
	/**
	 * Calculates the value f(x) of the function at x
	 * @param x The x-value at which the function will be evaluated
	 * @return a double, the value of the function at x
	 */
	public abstract double fnValue(double x);
	
	/**
	 * Translates f(x) to the display coordinate system.  Note that Java graphics 
	 * places (0,0) in the upper left, and (xmax, ymax) in the lower right of the
	 * display.  A buffer of 5 pixels is kept at top and bottom.
	 * @param x the value at which f(x) will be evaluated
	 * @param d the height in pixels of the display
	 * @param minY the minimum f(x) to be displayed, over the extent of the x's
	 * @param maxY the maximum f(x) to be displayed, over the extent of the x's
	 * @return the value of f(x) translated to the display coordinate system
	 */
	public double fnValueToPlot(double x, double d, double minY, double maxY) {
		 double y = fnValue(x);
		 double yDraw = (d+5)-((y-minY)*(d/(maxY-minY)));
		 return yDraw;
	}
	
	/**
	 * Determines the display value where y=0
	 * @param height Height of the Canvas
	 * @param minY th=0e minimum height of the function within the chosen extents
	 * @param maxY the maximum height of the function within the chosen extents
	 * @return the value of y to display corresponding to y
	 */
	public double originToPlot(double height, double minY, double maxY) {
		 double yDraw = (height+5)-((0-minY)*(height/(maxY-minY)));
		 return yDraw;
	}
}
