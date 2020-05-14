

 


public abstract class Function {

	/**
	 * Prints a result for the specific function based on three inputs
	 * @param optVal
	 * @param x
	 * @param y
	 * @param z
	 * @return a string which is the answer to the function
	 */
	public abstract String answerString(double optVal, double x, double y, double z);
	
	/**
	 * Calculates the value f(x) of the function at x
	 * @param x The x-value at which the function will be evaluated
	 * @return a double, the value of the function at x
	 */
	public abstract double fnValue(double x);
	
	/**
	 * Translates f(x) to the display coordinate system.  Note that swing
	 * and awt place (0,0) in the upper left, and (xmax, ymax) in the lower right of the
	 * display.  A buffer of 5 pixels is kept at top and bottom.
	 * @param x the value at which f(x) will be evaluated
	 * @param gridHeight the height in pixels of the display
	 * @param minY the minimum f(x) to be displayed, over the extent of the x's
	 * @param maxY the maximum f(x) to be displayed, over the extent of the x's
	 * @return the value of f(x) translated to the display coordinate system
	 */
	public double fnValueToPlot(double x, double gridHeight, double minY, double maxY) {
		 double y = fnValue(x);
		 double yDraw = (gridHeight+5)-((y-minY)*(gridHeight/(maxY-minY)));
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
	
	public abstract double getXVal (double x);
	
	public abstract double getYVal (double x);
	
	public abstract double getZVal (double x);
	

}
