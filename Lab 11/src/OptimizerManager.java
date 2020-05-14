


/**
 * The manager deals with the specified problems for optimization, i.e., selecting the problem that the user wants,
 * setting the x-axis and y-axis extents that the user wants plotted, getting the function value at a 
 * specified x, and getting the y value to be drawn on the GUI.  
 * @author ralexander
 *
 */
public class OptimizerManager implements OptimizerManagerInterface {
	private double xLeft, xRight;
	private double yTop = Integer.MIN_VALUE;
	private double yBottom = Integer.MAX_VALUE;
	private int functionChoice=-999;
	private double gridWidth;
	Function function1, function2, function3, function4, function5, function6;
	
	/**
	 * Constructor instantiates the functions specified.
	 */
	OptimizerManager() {
		function1 = new Function1();
		function2 = new Function2();
		function3 = new Function3();
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
	 * @return an index 1-3 corresponding to a function
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
			default: return null;
		}
	}
	
	/**
	 * Sets the left and right extents to be considered, and computes and sets the minimum and maximum
	 * values of f(x) for those left-right extents. 
	 * @param xLeft user's desired left extent
	 * @param xRight user's desired right extent
	 * @param gridWidth2 width of the panel in pixels
	 */
    public void setExtents(double xLeft, double xRight, double gridWidth2) {
    	 this.xLeft = xLeft;
    	 this.xRight = xRight;
    	 this.gridWidth = gridWidth2;
	   	 double x0=xLeft, x1=0, y1;
	   	 Function fn = getFunction(functionChoice);
	   	 yTop = Integer.MIN_VALUE;
	   	 yBottom = Integer.MAX_VALUE;
	   	 y1 = fn.fnValue(x0);
	   	 if (y1>yTop && y1<Integer.MAX_VALUE) yTop=y1;
		 if (y1<yBottom && y1>Integer.MIN_VALUE) yBottom=y1;
	   	 for (int i=1; i<gridWidth2; i++) {
		 	 x1 = x0+((xRight-xLeft)/gridWidth2);
			 y1 = fn.fnValue(x1); //  getFnValue(functionChoice, x1);
			 if (y1>yTop && y1<Integer.MAX_VALUE) yTop=y1;
			 if (y1<yBottom && y1>Integer.MIN_VALUE) yBottom=y1;
			 x0=x1;
		 }
		 System.out.println("xLeft = "+xLeft+";   xRight = "+xRight+"   maxY = "+yTop+";   minY = "+yBottom+"    gridWidth = "+gridWidth2);	  
    }	
    
    /**
     * Gets the left extent of the function to be considered
     * @return the x value of the left extent as a double
     */
    public double getLeftExtent () {
    	return xLeft;
    }
    
	public double getGridwidth() {
		return gridWidth;
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
		return rtnString;
	}
    
    public String answerString(int choice, double optVal, double x, double y, double z) {
    	switch(choice) {
    	//public String answerString(double cost, double height, double radius)
		case 1: return "1. "+function1.answerString(optVal, x, y, z)+"\n";
		case 2: return "2. "+function2.answerString(optVal, x, y, z)+"\n";
		case 3: return "3. "+function3.answerString(optVal, x, y, z)+"\n";
		default: return "wrong choice";
		}
    }
    
    //TO DO: figure out how to detect multiple optima
    public Optimum optimize (int choice) {
    	Optimum opt=null;
    	double x0=xLeft, x1=0, maxY = Integer.MIN_VALUE, minY = Integer.MAX_VALUE, y1; 
    	double xMin=0, xMax=0;
    	double gridWidth = getGridwidth();
    	Function fn = getFunction(choice);
    	for (int i=1; i<gridWidth; i++) {
		 	 x1 = x0+((xRight-xLeft)/gridWidth);
			 y1 = fn.fnValue(x1);
			 if (y1>maxY){ maxY=y1; xMax=x1; }
			 if (y1<minY){ minY=y1; xMin=x1; }
			 x0=x1;
    	}
		switch(choice) {
			case 1: 
				opt = new Optimum(minY, fn.getXVal(xMin), fn.getYVal(xMin), fn.getZVal(-1.0));
				return opt;
			case 2: 
				opt = new Optimum(minY, fn.getXVal(xMin), fn.getYVal(-1.0), fn.getZVal(-1.0));			
				return opt;
			case 3: 
				opt = new Optimum(minY, xMin, fn.getYVal(-1.0), fn.getZVal(-1.0));			
				return opt;
			default: return null;
		}
    }

}
