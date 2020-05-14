




import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;


/**
 * This panel is the basic pane, inside which other panes are placed.  
 * @author ralexander
 */
public class MainPaneFX {

	private BorderPane mainPane;
	private HBox buttonPanel;
	private GraphPanelFX graphPanel;
	private Canvas graphCanvas;
	private Button optimizeButton, exitButton;
	
	
	//The manager is the way the GUI communicates with the worker code
	private OptimizerManager optimizerManager;
	
	/**
	 * The MainPanel constructor sets up the GUI with two buttons and a display area.  
	 */
	MainPaneFX(double CANVAS_WIDTH, double CANVAS_HEIGHT) {

		mainPane = new BorderPane();

		//create the dataManager instance
		optimizerManager = new OptimizerManager();
		
	    //create the exitButton
	    exitButton = new Button("Exit");
	    exitButton.setTooltip(new Tooltip("Select to close the application"));
	    exitButton.setOnAction(event -> 
			 System.exit(0) );
			 
	    //create the button to start graphing
	    optimizeButton = new Button("Optimize a problem");
	    optimizeButton.setTooltip(new Tooltip("Select a problem and optimize it"));

		/*
		 * When the button pushed Optimize button, user is prompted to select one of the problems to optimize,
		 * and is asked for the left and right limits (extents) to plot the function.
		 */
	    optimizeButton.setOnAction(event -> {
	    	SwingUtilities.invokeLater( () -> {
	    	graphCanvas.setVisible(false);
			 int choice = 0;
			 double left=0, right=0;
			 Optimum opt=null;
			 choice = askForFunction("Select one of the following problems to optimize (by number):\n"+optimizerManager.toString());
			 if(choice != 0) {
				 optimizerManager.setFunctionChoice(choice);
				 try {
					 left = askForExtent("Enter the left extent of the function domain");
					 right = askForExtent("Enter the right extent of the function domain");
					 optimizerManager.setExtents(left, right, graphCanvas.getWidth());
					 graphPanel.drawGraph();
					 opt = optimizerManager.optimize(choice);
					 
					 JOptionPane.showMessageDialog(null, optimizerManager.answerString(choice, 
							 opt.getOptimum(),opt.getOptX(),opt.getOptY(),opt.getOptZ()));
					 
			     } catch (NullPointerException e) {
			    	 JOptionPane.showMessageDialog(null, "No entry: exiting"); 
				 }
				 graphCanvas.setVisible(true);
			 }
	    	});
	    });
		//create the buttonPanel
	    buttonPanel = new HBox();
	    buttonPanel.setVisible(true);
	    buttonPanel.setAlignment(Pos.CENTER);
	    HBox.setMargin(exitButton, new Insets(10,10,10,10)); 
	    HBox.setMargin(optimizeButton, new Insets(10,10,10,10)); 
	    buttonPanel.getChildren().addAll(exitButton, optimizeButton);
	    //add the panel to the bottom section of the main panel
	    mainPane.setBottom(buttonPanel);
	          
	    //panel to hold the graph
	    graphPanel = new GraphPanelFX(optimizerManager, CANVAS_WIDTH, CANVAS_HEIGHT);
	    graphCanvas = graphPanel.getGraphCanvas(graphPanel);	    
	
	    graphCanvas.setVisible(true);
	    mainPane.setCenter(graphCanvas);

	}

	public OptimizerManager getGraphManager() {
		return optimizerManager;
	}

	private int askForFunction(String str) {
		
		
		boolean error=false;
		int returnVal=0;
		
		do {
			
			try {
				returnVal = Integer.parseInt(JOptionPane.showInputDialog
						(null, str));
				if (returnVal<1 || returnVal>5) {
					error = true;
					SwingUtilities.invokeLater( () -> {
					JOptionPane.showMessageDialog(null, "Choice of problem must be an integer between 1 and 3");
					});
					}
				else error = false;
			}
			catch (NumberFormatException e) {
				SwingUtilities.invokeLater( () -> {
				JOptionPane.showMessageDialog(null, "Input Error: "+e);
				});
				error = true;
			
			}
			
		} while(error);
		
		return returnVal;
		
		
	}
	
	private double askForExtent(String str) throws NullPointerException {
		
		boolean error=false;
		
		double returnVal=0;
		do {
			 try {
				
				 returnVal = Double.parseDouble(JOptionPane.showInputDialog(null, str));
				 
				 error = false;
				 
		 	 }
			 
		 	 catch (NumberFormatException e) {
		 		
		 		SwingUtilities.invokeLater( () -> {
		 		JOptionPane.showMessageDialog(null, "Input Error - "+e); 
		 		});
		 		
		 	 	error = true;
		 	}
		 } while(error);
		
		return returnVal;
		
	}
	
	public BorderPane getMainPane() {
		// TODO Auto-generated method stub
		return mainPane;
	}

}
