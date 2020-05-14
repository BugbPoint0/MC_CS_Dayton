



public interface GraphManagerInterface {
	
	/**
	 * getFnValue calculates the value of the function requested by the user, at a specific x value.
	 * @param fnNum the choice of which function to evaluate
	 * @param x the value at which to evaluate the function
	 * @return the value of f(x)
	 */
	public double getFnValue (int fnNum, double x);
	
	/**
	 * Gets the actual function instance
	 * @param choice the index of the function desired
	 * @return an instance of a sub-class of the Function class
	 */
	public Function getFunction(int choice);
	
	/**
	 * Sets the left and right extents to be considered, and computes and sets the minimum and maximum
	 * values of f(x) for those left-right extents. Note that these values are NOT transformed to fit in the 
	 * display grid, they are just the values of x and f(x)
	 * @param xLeft user's desired left extent
	 * @param xRight user's desired right extent
	 * @param gridWidth width of the panel in pixels
	 */
    public void setExtents(double xLeft, double xRight, double gridWidth);
    
    /**
     * Gets the left extent of the function to be considered
     * @return the x value of the left extent as a double
     */
    public double getLeftExtent ();
    
    /**
     * Gets the right extent of the function to be considered
     * @return the x value of the right extent as a double
     */
    public double getRightExtent ();
    
    /**
     * Gets the top extent of the function to be considered
     * @return the maximum f(x) value that occurs from left to right
     */
    public double getTopExtent ();
    
    /**
     * Gets the bottom extent of the function to be considered
     * @return the minimum f(x) value that occurs from left to right
     */
    public double getBottomExtent ();
}
