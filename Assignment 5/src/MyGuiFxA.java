import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;

import javafx.scene.control.Alert;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MyGuiFxA extends Application {;
	private double[][] sales;
	public static final int MAX_STORES = 6;
	public static final int MAX_ITEMS = 6;
	private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
	Button readFileBtn, exitBtn, copyFileBtn;
	GridPane dataPane;
	

	/**
	 * Lets the user choose a file to read the sales information and displays
	 * the information on the screen
	 * @throws FileNotFoundException 
	 */
	public void readFile() throws FileNotFoundException {
		File selectedFile;

		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose a file to read retail items' sales information");
		if ((selectedFile = chooser.showOpenDialog(null)) != null) {
			// Read the file
			sales = TwoDimRaggedArrayUtility.readFile(selectedFile);
		}
		//clear table
		clearTable();
		//display on the screen
		int row,col;
		double total, lowest, highest;
		for(row=0;row<sales.length; row++)
			for(col=0;col<sales[row].length;col++)
				dataPane.add(new TextField(currencyFormat.format(sales[row][col])),col+1,row+1);

		//display row totals
		for(row=0;row<sales.length;row++)
		{
			total = TwoDimRaggedArrayUtility.getRowTotal(sales, row);
			dataPane.add(new TextField(currencyFormat.format(total)), 7, row+1);
		}

		//find the row with largest number of columns
		int columns = 0;
		for(row=0;row<sales.length;row++)
			if(sales[row].length > columns) columns = sales[row].length;

		//display column totals
		for(col=0;col<columns;col++)
		{
			total = TwoDimRaggedArrayUtility.getColumnTotal(sales, col);
			dataPane.add(new TextField(currencyFormat.format(total)), col+1, 7);
		}
		
		
		//display total of all sales
		total = TwoDimRaggedArrayUtility.getTotal(sales);
		dataPane.add(new TextField(currencyFormat.format(total)), 7, 7);
		
		//display holiday bonuses
		double[] result = HolidayBonus.calculateHolidayBonus(sales, 5000, 1000, 2000);
		for(row=0;row<sales.length;row++){
			dataPane.add(new TextField(currencyFormat.format(result[row])), 8, row+1);
		}
		
		//display total of holiday bonuses
		double bonus = HolidayBonus.calculateTotalHolidayBonus(sales, 5000, 1000, 2000);
		dataPane.add(new TextField(currencyFormat.format(bonus)), 8, 7);

		//find highest in each column
		for(col=0;col<columns;col++)
		{
			highest = TwoDimRaggedArrayUtility.getHighestInColumn(sales, col);
			TextField temp = new TextField(currencyFormat.format(highest));
			temp.setStyle("-fx-background-color: green;");
			for(row=0;row<sales.length;row++) {
				if(col < sales[row].length){
					if(sales[row][col]==highest)
						dataPane.add(temp, col+1, row+1);
				}
			}
		}

		//find lowest in each column

			for(col=0;col<columns;col++)
			{
				lowest = TwoDimRaggedArrayUtility.getLowestInColumn(sales, col);
				highest = TwoDimRaggedArrayUtility.getHighestInColumn(sales, col);
				if (lowest == highest) continue;
				TextField temp = new TextField(currencyFormat.format(lowest));
				temp.setStyle("-fx-background-color: red;");
				for(row=0;row<sales.length;row++) {
					if(col < sales[row].length){
						if(sales[row][col]==lowest)
							dataPane.add(temp, col+1, row+1);
					}
				}
			}
		
	}
	
	/**
	 * Lets the user choose name and location to copy the file used for
	 * the information on the screen
	 * @throws FileNotFoundException 
	 */
	public void copyFile() throws FileNotFoundException
	{
		File selectedFile;

		FileChooser chooser = new FileChooser();
		chooser.setTitle("Name and Location of copied file");
		if ((selectedFile = chooser.showSaveDialog(null)) != null) {
			// Read the file
			TwoDimRaggedArrayUtility.writeToFile(sales,selectedFile);
		}
	}

	// Handler class.
	private class ButtonEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			//handler for Load Sales Data
			if (e.getSource() == readFileBtn) {

				try {
					readFile();
					copyFileBtn.setVisible(true);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}

			//handler for Load Sales Data
			if (e.getSource() == copyFileBtn) {

				try {
					copyFile();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

				//handler for Exit button
			}  else if (e.getSource() == exitBtn)

				System.exit(0);
		}
	}

	@Override
	public void start(Stage stage) {
		
		Tooltip buttonToolTipArr[] = new Tooltip[5];
		buttonToolTipArr[0] = new Tooltip(
				"Load sales data from a file and Display");
		buttonToolTipArr[1] = new Tooltip("Exit Application");

		// Main Pane
		BorderPane MainPane = new BorderPane();

		// Create Title Pane, add title label and add it to the top of the Main
		// Pane
		HBox titlePanel = new HBox();
		titlePanel.setAlignment(Pos.BASELINE_CENTER);
		Label titleLbl = new Label("Disney World Retail District 5 Sales Report\n");
		titleLbl.setFont(new Font(30));
		titleLbl.setTextFill(Color.BLUE);

		titlePanel.getChildren().add(titleLbl);
		MainPane.setTop(titlePanel);

		// CenterPane
		VBox centerPane = new VBox();
		centerPane.setAlignment(Pos.CENTER);

		// columnHeader Pane
		HBox columnHeaderPane = new HBox(10);
		columnHeaderPane.setAlignment(Pos.CENTER);

		
		int i,j;
		dataPane = new GridPane();
		dataPane.setAlignment(Pos.BASELINE_CENTER);
		dataPane.add(new Label("     "), 0, 0);
		dataPane.add(new Label("Books"), 1, 0);
		dataPane.add(new Label("Tsum Tsum"), 2, 0);
		dataPane.add(new Label("Trading Pins"), 3, 0);
		dataPane.add(new Label("Star Wars"), 4, 0);
		dataPane.add(new Label("Lego"), 5, 0);
		dataPane.add(new Label("Marvel"), 6, 0);
		dataPane.add(new Label("Total"), 7, 0);
		dataPane.add(new Label("Holiday Bonus"), 8, 0);
		
		for(i=1;i<9;i++)
		{
			dataPane.add(new Label("     "), 0,i);
			for(j = 1; j<8;j++)
				dataPane.add(new TextField(), i,j);
		}
		
	
		dataPane.add(new Label("Emporium"), 0, 1);
		dataPane.add(new Label("World Traveler"), 0, 2);
		dataPane.add(new Label("Discovery Trading Center"), 0, 3);
		dataPane.add(new Label("Merchant of Venus"), 0, 4);
		dataPane.add(new Label("Once Upon a Toy"), 0, 5);
		dataPane.add(new Label("Tatooine Traders"), 0, 6);
		dataPane.add(new Label("Total"), 0, 7);
		
		//put in buttons and labels for high and low
		Button high = new Button("  ");
		high.setStyle("-fx-background-color: green;");
		dataPane.setHalignment(high, HPos.RIGHT);

		Button low = new Button("  ");
		low.setStyle("-fx-background-color: red;");
		dataPane.setHalignment(low, HPos.RIGHT);
		
		dataPane.add(high, 0,9);
		dataPane.add(low, 0,10);
		dataPane.add(new Label("Highest Sales in Category"), 1, 9);
		dataPane.add(new Label("Lowest Sales in Category"), 1,10);

		// Create bottom Pane
		HBox bottomPane = new HBox(10);
		bottomPane.setAlignment(Pos.BASELINE_CENTER);

		// Create buttons
		readFileBtn = new Button("Load Sales Data");
		readFileBtn.setTooltip(buttonToolTipArr[0]);
		copyFileBtn = new Button("Copy File");
		copyFileBtn.setTooltip(buttonToolTipArr[1]);
		copyFileBtn.setVisible(false);
		exitBtn = new Button("Exit");
		exitBtn.setTooltip(buttonToolTipArr[2]);

		// add event handler to buttons
		readFileBtn.setOnAction(new ButtonEventHandler());
		copyFileBtn.setOnAction(new ButtonEventHandler());
		exitBtn.setOnAction(new ButtonEventHandler());

		// add buttons to bottomPane
		bottomPane.getChildren().addAll(readFileBtn, copyFileBtn, exitBtn);
		MainPane.setBottom(bottomPane);

		// add panes to center pane
		centerPane.getChildren().addAll(dataPane);

		MainPane.setCenter(centerPane);

		Scene scene = new Scene(MainPane, 1200, 400);
		stage.setScene(scene);

		// Set stage title and show the stage.
		stage.setTitle("District Sales Report");
		stage.show();
	}

	/**
	 * Clears the numeric fields on the screen
	 */
	public void clearTable()
	{
		TextField field= new TextField();
		int i,j;
		
		for(i=1;i<7;i++)
		{
			for(j = 1; j<8;j++)
				dataPane.add(new TextField(), i,j);
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}