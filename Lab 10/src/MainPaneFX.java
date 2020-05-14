


import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;


/**
 * This panel is the basic pane, inside which other panes are placed.
 * Updated with JavaFX alerts and TextInputDialog by prof Kartchner  
 * @author ralexander
 */
public class MainPaneFX {

	private BorderPane mainPane;
	private HBox buttonPanel;
	private GraphPanelFX graphPanel;
	private Canvas graphCanvas;
	private Button graphFunctionButton, exitButton;
	
	//The manager is the way the GUI communicates with the worker code
	private GraphManager graphManager;
	
	/**
	 * The MainPanel constructor sets up the GUI with two buttons and a display area.  
	 */
	MainPaneFX(double CANVAS_WIDTH, double CANVAS_HEIGHT) {

		mainPane = new BorderPane();

		//create the dataManager instance
		graphManager = new GraphManager();
		
	    //create the exitButton
	    exitButton = new Button("Exit");
	    //exitButton.setToolTipText("Exit the program");
	    exitButton.setOnAction(event -> 
			 System.exit(0) );
			 
	    //create the button to start graphing
	    graphFunctionButton = new Button("Graph a Function");
	    //graphFunctionButton.setToolTipText("Select a function and graph it");

		/*
		 * When the button pushed was the graph function button, user is prompted to select one of the functions,
		 * and is asked for the left and right limits (extents) to plot the function.
		 */
	    graphFunctionButton.setOnAction(event -> {
	    	graphCanvas.setVisible(false);
			 int choice = 0;
			 double left=0, right=0;
			 choice = askForFunction("Select one of the following functions to graph (by number):\n"+graphManager.toString());
			 if(choice != 0) {
				 graphManager.setFunctionChoice(choice);
				 try {
					 left = askForExtent("Enter the left extent of the function domain");
					 right = askForExtent("Enter the right extent of the function domain");
					 graphManager.setExtents(left, right, graphCanvas.getWidth());
					 graphPanel.drawGraph();
			     } catch (NullPointerException e) {
			    	 //JOptionPane.showMessageDialog(null, "No entry: exiting"); 
			    	 new Alert(Alert.AlertType.ERROR, "No entry: exiting").showAndWait();
				 }
				 graphCanvas.setVisible(true);
			 }
	    });
		//create the buttonPanel
	    buttonPanel = new HBox();
	    //buttonPanel.setPreferredSize(new Dimension(600,50));
	    buttonPanel.setVisible(true);
	    buttonPanel.setAlignment(Pos.CENTER);
	    HBox.setMargin(exitButton, new Insets(10,10,10,10)); 
	    HBox.setMargin(graphFunctionButton, new Insets(10,10,10,10)); 
	    buttonPanel.getChildren().addAll(exitButton, graphFunctionButton);
	    //buttonPanel.add(graphFunctionButton);
	    //add the panel to the bottom section of the main panel
	    mainPane.setBottom(buttonPanel);
	          
	    //panel to hold the graph
	    graphPanel = new GraphPanelFX(graphManager, CANVAS_WIDTH, CANVAS_HEIGHT);
	    graphCanvas = graphPanel.getGraphCanvas(graphPanel);	    
	
	    graphCanvas.setVisible(true);
	    mainPane.setCenter(graphCanvas);

	}

	public GraphManager getGraphManager() {
		return graphManager;
	}

	private int askForFunction(String str) {
		boolean error=false;
		int returnVal=0;
		do {
			try {
				//returnVal = Integer.parseInt(JOptionPane.showInputDialog(null, str));
				TextInputDialog functionIdDialog = new TextInputDialog("");
			    functionIdDialog.setTitle("");
			    functionIdDialog.setHeaderText(null);
			    functionIdDialog.setContentText(str);

			    Optional<String> functionID = functionIdDialog.showAndWait();
			    if (functionID.isPresent()) {
			        returnVal = Integer.parseInt(functionID.get());
			    }
				if (returnVal<1 || returnVal>5) {
					error = true;
					//JOptionPane.showMessageDialog(null, "Choice of function must be an integer between 1 and 5");
					new Alert(Alert.AlertType.ERROR, "Choice of function must be an integer between 1 and 5").showAndWait();
					}
				else error = false;
			}
			catch (NumberFormatException e) {
				//JOptionPane.showMessageDialog(null, "Input Error: "+e);
				new Alert(Alert.AlertType.ERROR, "Input error!").showAndWait();
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
				 //returnVal = Double.parseDouble(JOptionPane.showInputDialog(null, str));
				 error = false;
				 TextInputDialog functionIdDialog = new TextInputDialog("");
				    functionIdDialog.setTitle("");
				    functionIdDialog.setHeaderText(null);
				    functionIdDialog.setContentText(str);

				    Optional<String> functionID = functionIdDialog.showAndWait();
				    if (functionID.isPresent()) {
				        returnVal = Integer.parseInt(functionID.get());
				    }
		 	 }
			 
		 	 catch (NumberFormatException e) {
		 		//JOptionPane.showMessageDialog(null, "Input Error - "+e); 
		 		new Alert(Alert.AlertType.ERROR, "Input error!").showAndWait();
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