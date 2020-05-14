



/**
 * The manager deals with the specified functions, i.e., selecting the function that the user wants,
 * setting the x-axis and y-axis extents that the user wants plotted, getting the function value at a 
 * specified x, and getting the y value to be drawn on the GUI.  
 * @author ralexander
 *
 */
public class GraphManager implements GraphManagerInterface {
	private double xLeft, xRight;
	private double yTop = Integer.MIN_VALUE;
	private double yBottom = Integer.MAX_VALUE;
	private int functionChoice=-999;
	Function function1, function2, function3, function4, function5, function6;
	
	/**
	 * Constructor instantiates the functions specified.
	 */
	GraphManager() {
		function1 = new Function1();
		function2 = new Function2();
		function3 = new Function3();
		function4 = new Function4();
		function5 = new Function5();
	}
	
	/**
	 * getFnValue calculates the value of the function requested by the user, at a specific x value.
	 * @param fnNum the choice of which function to evaluate
	 * @param x the value at which to evaluate the function
	 * @return the value of f(x)
	 */
	public double getFnValue (int fnNum, double x) {
		switch(fnNum) {
			case 1: return function1.fnValue(x);
			case 2: return function2.fnValue(x);
			case 3: return function3.fnValue(x);
			case 4: return function4.fnValue(x);
			case 5: return function5.fnValue(x);
			default: return 0;
		}
	}

	/**
	 * Sets the function choice
	 * @param choice an integer indexing the function desired
	 */
	public void setFunctionChoice(int choice) {
		functionChoice = choice;
	}
	
	/**
	 * Gets the function index previously selected by the user
	 * @return an index 1-4 corresponding to a function
	 */
	public int getFunctionChoice() {
		return functionChoice;
	}
	
	/**
	 * Gets the actual function instance
	 * @param choice the index of the function desired
	 * @return an instance of a sub-class of the Function class
	 */
	public Function getFunction(int choice) {
		switch(choice) {
			case 1: return function1;
			case 2: return function2;
			case 3: return function3;
			case 4: return function4;
			case 5: return function5;
			default: return null;
		}
	}
	
	/**
	 * Sets the left and right extents to be considered, and computes and sets the minimum and maximum
	 * values of f(x) for those left-right extents. 
	 * @param xLeft user's desired left extent
	 * @param xRight user's desired right extent
	 * @param d width of the panel in pixels
	 */
    public void setExtents(double xLeft, double xRight, double d) {
    	 this.xLeft = xLeft;
    	 this.xRight = xRight;
	   	 double x0=xLeft, x1=0, y1;
	   	 Function fn = getFunction(functionChoice);
	   	 yTop = Integer.MIN_VALUE;
	   	 yBottom = Integer.MAX_VALUE;
	   	 y1 = fn.fnValue(x0);
	   	 if (y1>yTop && y1<Integer.MAX_VALUE) yTop=y1;
		 if (y1<yBottom && y1>Integer.MIN_VALUE) yBottom=y1;
	   	 for (int i=1; i<d; i++) {
		 	 x1 = x0+((xRight-xLeft)/d);
			 y1 = fn.fnValue(x1); 
			 if (y1>yTop && y1<Integer.MAX_VALUE) yTop=y1;
			 if (y1<yBottom && y1>Integer.MIN_VALUE) yBottom=y1;
			 x0=x1;
		 }
		 System.out.println("xLeft = "+xLeft+";   xRight = "+xRight+"   maxY = "+yTop+";   minY = "+yBottom+"    gridWidth = "+d);	  
    }	
    
    /**
     * Gets the left extent of the function to be considered
     * @return the x value of the left extent as a double
     */
    public double getLeftExtent () {
    	return xLeft;
    }
    
    /**
     * Gets the right extent of the function to be considered
     * @return the x value of the right extent as a double
     */
    public double getRightExtent () {
    	return xRight;
    }
    
    /**
     * Gets the top extent of the function to be considered
     * @return the maximum f(x) value that occurs from left to right
     */
    public double getTopExtent () {
    	return yTop;
    }
    
    /**
     * Gets the bottom extent of the function to be considered
     * @return the minimum f(x) value that occurs from left to right
     */
    public double getBottomExtent () {
    	return yBottom;
    }
    
    /**
     * Overrides toString, creating a string describing the functions' formulas
     */
    public String toString() {
		String rtnString = "";
		rtnString+="1. "+function1.toString()+"\n";
		rtnString+="2. "+function2.toString()+"\n";
		rtnString+="3. "+function3.toString()+"\n";
		rtnString+="4. "+function4.toString()+"\n";
		rtnString+="5. "+function5.toString()+"\n";
		return rtnString;
	}

}
