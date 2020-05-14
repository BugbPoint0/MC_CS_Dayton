




import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import _solutionA6Tickets.MovieTicketManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.geometry.Insets;

/**
 * GUI for the MovieTicket application
 * @author Professor Kartchner
 *
 */
public class FXMainPane extends BorderPane {
  private static final long serialVersionUID = 1L;
  private HBox typeButtonGroupPanel,headerPanel,ticketInfoPanel,addTicketButtonPanel;
  private HBox ratingButtonGroupPanel, featureButtonGroupPanel; 
  private VBox addTicketPanel, ticketLabelPanel, ticketTextPanel;
  private FlowPane ticketMgmtButtonPanel;
  private TextField nameField, idField, movieNameField, dayField, timeField;
  private Label nameLabel, dayLabel, movieNameLabel, timeLabel, idLabel, headerLabel, priceLabel;
  private Label featureLabel, ratingLabel, priceAmtLabel, monthlyTicketSalesLabel, monthlySalesAmt;
  private RadioButton adultButton, childButton, employeeButton, moviePassButton;
  private RadioButton gButton, pgButton, pg13Button, rButton, nrButton;
  private RadioButton imaxButton, three_DButton, noneButton;
  private Button addTicketButton, readFileButton, Exit, clearButton;
  private Button printReportButton, print3DButton, printMoviePassButton, printPatronsButton;
  private MovieTicketManager tickets;
  private ImageView icon;
  private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
  private Alert alert = new Alert(AlertType.INFORMATION);
  
  public FXMainPane(){
     this.setMaxSize(500,650);  //setPreferredSize(new Dimension(400,550));
     tickets = new MovieTicketManager();
     
     priceLabel = new Label("Ticket Price:");
     featureLabel = new Label("Select One: ");
     ratingLabel = new Label("Select One: ");
     idLabel = new Label("");
     idLabel.setVisible(false);
     movieNameLabel = new Label("Movie Name:");
     dayLabel = new Label("Day (1-31)");
     timeLabel = new Label("Time (8-23)");
     icon = new ImageView("file:src/xyzSmall.png");
	 headerLabel = new Label("XYZ Movie Theater");
	 headerLabel.setStyle("-fx-font-size: 20");
	 headerLabel.setPadding(new Insets(0,0,20,0));
	 priceAmtLabel = new Label("");
	 priceAmtLabel.setTextFill(Color.RED);
	 priceAmtLabel.setVisible(false);
	 monthlyTicketSalesLabel = new Label("Monthly Ticket Sales: ");
	 monthlySalesAmt = new Label("$0.00");
     movieNameField = new TextField();
     idField = new TextField();
     idField.setVisible(false);
     dayField = new TextField();
     timeField = new TextField();

     //type radio buttons
     adultButton = new RadioButton("ADULT");
     adultButton.setOnAction(new RadioButtonHandler()); 
     childButton = new RadioButton("CHILD");
     childButton.setOnAction(new RadioButtonHandler());
     employeeButton = new RadioButton("EMPLOYEE");
     employeeButton.setOnAction(new RadioButtonHandler());
     moviePassButton = new RadioButton("MOVIEPASS");
     moviePassButton.setOnAction(new RadioButtonHandler());
     ToggleGroup typeButtonGroup = new ToggleGroup();
     adultButton.setToggleGroup(typeButtonGroup);
     childButton.setToggleGroup(typeButtonGroup);
     employeeButton.setToggleGroup(typeButtonGroup);
     moviePassButton.setToggleGroup(typeButtonGroup);
     
   //rating radio buttons
     gButton = new RadioButton("G");
     gButton.setOnAction(new RadioButtonHandler()); 
     pgButton = new RadioButton("PG");
     pgButton.setOnAction(new RadioButtonHandler());
     pg13Button = new RadioButton("PG13");
     pg13Button.setOnAction(new RadioButtonHandler());
     rButton = new RadioButton("R");
     rButton.setOnAction(new RadioButtonHandler());
     nrButton = new RadioButton("NR");
     nrButton.setOnAction(new RadioButtonHandler());
     ToggleGroup ratingButtonGroup = new ToggleGroup();
     gButton.setToggleGroup(ratingButtonGroup);
     pgButton.setToggleGroup(ratingButtonGroup);
     pg13Button.setToggleGroup(ratingButtonGroup);
     rButton.setToggleGroup(ratingButtonGroup);
     nrButton.setToggleGroup(ratingButtonGroup);
     
   //feature radio buttons
     imaxButton = new RadioButton("IMAX");
     imaxButton.setOnAction(new RadioButtonHandler()); 
     three_DButton = new RadioButton("3D");
     three_DButton.setOnAction(new RadioButtonHandler());
     noneButton = new RadioButton("None");
     noneButton.setOnAction(new RadioButtonHandler());
     ToggleGroup featureButtonGroup = new ToggleGroup();
     imaxButton.setToggleGroup(featureButtonGroup);
     three_DButton.setToggleGroup(featureButtonGroup);
     noneButton.setToggleGroup(featureButtonGroup);
     
     //add-ticket buttons
     addTicketButton = new Button("Purchase Ticket");
     clearButton = new Button("Clear");
     
     addTicketButton.setOnAction(new ButtonHandler());
     clearButton.setOnAction(new ButtonHandler());
     
     //report buttons
     printReportButton = new Button("Print Monthly Sales Report");
     readFileButton = new Button("Read File");
     printPatronsButton = new Button("Print All Tickets");
     print3DButton = new Button("Print 3D Tickets");
     printMoviePassButton = new Button("Print MoviePass Tickets");
     Exit = new Button("Exit");
   
     printReportButton.setOnAction(new ButtonHandler());
     readFileButton.setOnAction(new ButtonHandler());
     Exit.setOnAction(new ButtonHandler());
     printPatronsButton.setOnAction(new ButtonHandler());
     print3DButton.setOnAction(new ButtonHandler());
     printMoviePassButton.setOnAction(new ButtonHandler());
     
     //company title panel
     headerPanel = new HBox();
     headerPanel.getChildren().addAll(icon, headerLabel);
     headerPanel.setAlignment(Pos.CENTER);
     Insets insets=new Insets(40,10,20,10);
     HBox.setMargin(headerLabel, insets);
     headerPanel.setPrefHeight(10);
     
     setTop(headerPanel);
     BorderPane.setAlignment(headerPanel, Pos.CENTER);
     //radio button panel
     typeButtonGroupPanel = new HBox();
     typeButtonGroupPanel.getChildren().addAll(adultButton,childButton,employeeButton,moviePassButton);
     typeButtonGroupPanel.setAlignment(Pos.CENTER);
     insets=new Insets(10,10,20,10);
     HBox.setMargin(adultButton, insets);
     HBox.setMargin(childButton, insets);
     HBox.setMargin(employeeButton, insets);
     HBox.setMargin(moviePassButton, insets);
     
     //employee info labels
     ticketLabelPanel = new VBox();
     ticketLabelPanel.getChildren().addAll(movieNameLabel,dayLabel,timeLabel, idLabel);
     ticketLabelPanel.setAlignment(Pos.CENTER);
     insets=new Insets(8,10,10,10);
     VBox.setMargin(idLabel, insets);
     VBox.setMargin(movieNameLabel, insets);
     VBox.setMargin(dayLabel, insets);
     VBox.setMargin(timeLabel, insets);
     
     //employee info text panels
     ticketTextPanel = new VBox();
     ticketTextPanel.getChildren().addAll(movieNameField,dayField,timeField, idField);
     ticketTextPanel.setAlignment(Pos.CENTER);
     insets=new Insets(5,10,5,10);
     VBox.setMargin(idField, insets);
     VBox.setMargin(movieNameField, insets);
     VBox.setMargin(dayField, insets);
     VBox.setMargin(timeField, insets);
     
     //employee info panel
     ticketInfoPanel = new HBox();
     ticketInfoPanel.getChildren().addAll(ticketLabelPanel,ticketTextPanel);
     ticketInfoPanel.setAlignment(Pos.CENTER);
     
     //rating panel
     ratingButtonGroupPanel = new HBox();
     ratingButtonGroupPanel.setMaxWidth(450);
     ratingButtonGroupPanel.getChildren().addAll(ratingLabel,gButton,pgButton,pg13Button,rButton, nrButton);
     ratingButtonGroupPanel.setAlignment(Pos.CENTER_LEFT);
     insets=new Insets(10,10,20,10);
     HBox.setMargin(ratingLabel, insets);
     HBox.setMargin(gButton, insets);
     HBox.setMargin(pgButton, insets);
     HBox.setMargin(pg13Button, insets);
     HBox.setMargin(rButton, insets);
     HBox.setMargin(nrButton, insets);
     
     
     //feature panel
     featureButtonGroupPanel = new HBox();
     featureButtonGroupPanel.setMaxWidth(450);
     featureButtonGroupPanel.getChildren().addAll(featureLabel,imaxButton,three_DButton,noneButton);
     featureButtonGroupPanel.setAlignment(Pos.CENTER_LEFT);
     insets=new Insets(10,10,20,10);
     HBox.setMargin(featureLabel, insets);
     HBox.setMargin(imaxButton, insets);
     HBox.setMargin(three_DButton, insets);
     HBox.setMargin(noneButton, insets);
     
     VBox specialFeatures = new VBox();
     specialFeatures.getChildren().addAll(ratingButtonGroupPanel, featureButtonGroupPanel);
     specialFeatures.setAlignment(Pos.CENTER_LEFT);
     //specialFeatures.setStyle("-fx-border-color: black");
     
     HBox ticketPrice = new HBox();
     ticketPrice.getChildren().addAll(priceLabel, priceAmtLabel);
     ticketPrice.setAlignment(Pos.CENTER_LEFT);
     insets=new Insets(10,10,20,10);
     HBox.setMargin(priceLabel, insets);
     HBox.setMargin(priceAmtLabel, insets);
     
     HBox totalSales = new HBox();
     totalSales.getChildren().addAll(monthlyTicketSalesLabel, monthlySalesAmt);
     totalSales.setAlignment(Pos.CENTER_RIGHT);
     insets=new Insets(10,10,20,10);
     HBox.setMargin(monthlyTicketSalesLabel, insets);
     HBox.setMargin(monthlySalesAmt, insets);
     
     HBox stats = new HBox();
     stats.getChildren().addAll(ticketPrice, totalSales);
     stats.setAlignment(Pos.CENTER);
     insets=new Insets(10,10,20,10);
     HBox.setMargin(ticketPrice, insets);
     HBox.setMargin(totalSales, insets);
     
     
     //add employee buttons
     addTicketButtonPanel = new HBox();
     addTicketButtonPanel.getChildren().addAll(addTicketButton, clearButton);
     addTicketButtonPanel.setAlignment(Pos.CENTER);
     insets=new Insets(10,10,10,10);
     HBox.setMargin(addTicketButton, insets);
     HBox.setMargin(clearButton, insets);
     
     //employ info
     addTicketPanel = new VBox();
     addTicketPanel.getChildren().addAll(typeButtonGroupPanel,ticketInfoPanel,specialFeatures, ratingButtonGroupPanel, featureButtonGroupPanel, addTicketButtonPanel, stats);
     addTicketPanel.setAlignment(Pos.TOP_CENTER);
     addTicketPanel.setStyle("-fx-border-color: black");
 
     setCenter(addTicketPanel);
     
     ticketMgmtButtonPanel = new FlowPane();
     ticketMgmtButtonPanel.getChildren().addAll(readFileButton, printReportButton, printPatronsButton, print3DButton, printMoviePassButton, Exit);
     ticketMgmtButtonPanel.setAlignment(Pos.CENTER);
     insets=new Insets(5,5,5,5);
     FlowPane.setMargin(readFileButton, insets);
     FlowPane.setMargin(printReportButton, insets);
     FlowPane.setMargin(printPatronsButton, insets);
     FlowPane.setMargin(print3DButton, insets);
     FlowPane.setMargin(printMoviePassButton, insets);
     FlowPane.setMargin(Exit, insets);
     
     setBottom(ticketMgmtButtonPanel);
     BorderPane.setMargin(ticketMgmtButtonPanel, insets);
   }
   
   public void readFile() throws FileNotFoundException{
		// TODO Auto-generated method stub
		FileChooser chooser = new FileChooser();
		File selectedFile = chooser.showOpenDialog(null);
		if (selectedFile!=null)
		{
			tickets.readFile(selectedFile);
		}
   }
   
   private class RadioButtonHandler implements EventHandler<ActionEvent> {
   	@Override
    public void handle(ActionEvent event) {

	   //public void actionPerformed(ActionEvent event)	   {
		   if(event.getSource() == adultButton)
		   {
			   idLabel.setVisible(false);
			   idField.setVisible(false);
		   }
		   else if(event.getSource() == childButton)
		   {
			   idLabel.setVisible(false);
			   idField.setVisible(false);
		   }
		   else if(event.getSource() == employeeButton)
		   {
			   idLabel.setText("Employee Id:");
			   idLabel.setVisible(true);
			   idField.setVisible(true);
			   
		   }
		   else if(event.getSource() == moviePassButton)
		   {
			   idLabel.setText("MoviePass Id:");
			   idLabel.setVisible(true);
			   idField.setVisible(true);
		   }
	   }
   }
   
   private class ButtonHandler implements EventHandler<ActionEvent> {
	   	@Override
	    public void handle(ActionEvent event) {

       Object selectedButton = event.getSource();
       
       if (selectedButton==addTicketButton) {
    	   int id = 0, day=0,time=0;
    	   boolean add = true;
           String movieName = movieNameField.getText();
           day = Integer.parseInt(dayField.getText());
           if(day<1 || day>31) {
        	   alert.setTitle("Error");
    		   alert.setHeaderText("Day is not valid (1-31)");
    		   alert.showAndWait();
    		   add = false;
           }
           time = Integer.parseInt(timeField.getText());
           if(time<8 || time>23) {
        	   alert.setTitle("Error");
    		   alert.setHeaderText("Time is not valid (8-23)");
    		   alert.showAndWait();
    		   add = false;
           }
           String type="";
           String rating = getRating();
           String feature = getFeature();
           if (adultButton.isSelected()) 
        	   {
        	   id = 0;
        	   type = "Adult";
        	   }
           else if (childButton.isSelected()) {
        	   id = 0;
        	   type = "Child";
        	   if(rating.equals("PG13") || rating.equals("R") || rating.equals("NR")){
        		   alert.setTitle("Error");
        		   alert.setHeaderText("CHILD ticket can only have rating of G or PG");
        		   alert.showAndWait();
        		   add = false;
        	   }

           }
           else if (employeeButton.isSelected()) 
           {
        	   id = Integer.parseInt(idField.getText());
        	   type = "Employee";
        	   }
           else if (moviePassButton.isSelected()) 
        	   {
        	   id = Integer.parseInt(idField.getText());
        	   type = "MoviePass";
        	   }
           if(add){
           double result = tickets.addTicket(movieName, rating,day, time,feature,type,id);
           priceAmtLabel.setText(currencyFormat.format(result));
           priceAmtLabel.setVisible(true);
           monthlySalesAmt.setText(currencyFormat.format(tickets.totalSalesMonth()));
           }
       }
       
       else if (selectedButton==printPatronsButton)
       {
    	   ArrayList<String> list = tickets.getAllTickets();
    	   String result = "";
    	   for(int i=0;i<list.size();i++)
    		   result += list.get(i)+"\n";
       		alert.setTitle("All Tickets");
			alert.setHeaderText(result);
			alert.showAndWait();
         
       }
       
       else if (selectedButton==clearButton)
       	{
    	   idField.setText("");
    	   movieNameField.setText("");
    	   dayField.setText("");
    	   timeField.setText("");
       	}
       
       else if (selectedButton==readFileButton)
       {
    	  try {
			readFile();
			monthlySalesAmt.setText(currencyFormat.format(tickets.totalSalesMonth()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       }
       else if (selectedButton==print3DButton)
       {
    	   ArrayList<String> list = tickets.get3DTickets();
    	   String result = "";
    	   for(int i=0;i<list.size();i++)
    		   result += list.get(i)+"\n";
       		alert.setTitle("3D Tickets");
			alert.setHeaderText(result);
			alert.showAndWait();
       }
       
       else if (selectedButton==printReportButton)
       {
    	   String list = tickets.monthlySalesReport();
       		alert.setTitle("Monthly Sales Report");
			alert.setHeaderText(list);
			alert.showAndWait();
       }
       
       else if (selectedButton==printMoviePassButton)
       {
    	   ArrayList<String> list = tickets.getMoviePassTickets();
    	   String result = "";
    	   for(int i=0;i<list.size();i++)
    		   result += list.get(i)+"\n";
       		alert.setTitle("MoviePass Tickets");
			alert.setHeaderText(result);
			alert.showAndWait();
       }
       
       else if (selectedButton==Exit)
       {
         System.exit(0);
       }

     }
	   	
  }
   public String getRating(){
	   if(gButton.isSelected())
		   return "G";
	   else if (pgButton.isSelected())
		   return "PG";
	   else if(pg13Button.isSelected())
		   return "PG13";
	   else if(rButton.isSelected())
		   return "R";
	   else // is is NR
		   return "NR";
   }
   
   public String getFeature(){
	   if(imaxButton.isSelected())
		   return "IMAX";
	   else if (three_DButton.isSelected())
		   return "3D";
	   else // this is the none button
		   return "NONE";
   }
 }
