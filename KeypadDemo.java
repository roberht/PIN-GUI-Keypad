//written by Robert Herrera

import java.awt.Insets;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;

public class KeypadDemo extends Application
{
	// Buttons for the keypad
	public static Button textDisplay;
	public Button button1;
	public Button button2;
	public Button button3;
	public Button button4;
	public Button button5;
	public Button button6;
	public Button button7;
	public Button button8;
	public Button button9;
	public Button buttonStar;
	public Button button0;
	public Button buttonHash;
	static String message="";
	String keypadInput="";
	String input1 ="";
	String input2 ="";
	Boolean aptEntered = false;
	
	public static void main(String[] args) throws FileNotFoundException 
	{		
		// method launch is declared in the 
		// Application class.  It calls 
		// method start, below
		Keypad.setUpTenantDatabase();
		launch(args);
	}  // end main
	
	// Start is called by launch.  Start creates
	// the container for the page (here, it's a
	// vertical box (VBox) named 'root'), puts
	// content into the container, puts the
	// container into a scene, puts the scene
	// into a stage, then makes the page visible.
	public void start(Stage stage)
	{
		// Set up the screen layout. 
		// The next three rows will have a grid for
		// the keypad. The buttons will be arranged 
		// in this order
		// [Display]
		// 1  2  3
		// 4  5  6
		// 7  8  9	
		// *  0  #
		// overall container for the page, with padding
		// of 8 pixels around the 2 elements.
		VBox root = new VBox(8);  
	    root.setPrefWidth(500);  
	    // 500 pixels wide for now
 
		// Set up the 12 buttons for the keypad.  Each
		// button is distinguished by its ID.  The ID 
		// can be examined by the code in the handle
		// method of the SimpleEventHandler object.
		
		// buttons 
	    textDisplay = new Button(" ");
	    textDisplay.setPrefSize(450, 80);
	    textDisplay.setId("display");
	    textDisplay.setStyle(" -fx-base: chartreuse; -fx-font: 72 arial;");
	    
	    TilePane tilePane = new TilePane();
		tilePane.setStyle(" -fx-padding: 3 30 3 30;");
		tilePane.setHgap(10);  // horizontal space between entries
		tilePane.getChildren().add(textDisplay);
	
		// Add the tilePane object to the root container
		root.getChildren().add(tilePane);

		button1 = new Button("1");
		button1.setPrefSize(150,150);
		button1.setId("1");
		button1.setOnAction(new SimpleEventHandler());
	
		button2 = new Button("2");
		button2.setPrefSize(150,150);
		button2.setId("2");
		button2.setOnAction(new SimpleEventHandler());
		
		button3 = new Button("3");
		button3.setPrefSize(150,150);
		button3.setId("3");
		button3.setOnAction(new SimpleEventHandler());
		
		// buttons for the second row
		button4 = new Button("4");
		button4.setPrefSize(150,150);
		button4.setId("4");
		button4.setOnAction(new SimpleEventHandler());
	
		button5 = new Button("5");
		button5.setPrefSize(150,150);
		button5.setId("5");
		button5.setOnAction(new SimpleEventHandler());
		
		button6 = new Button("6");
		button6.setPrefSize(150,150);
		button6.setId("6");
		button6.setOnAction(new SimpleEventHandler());
		
		// buttons for the third row 
		button7 = new Button("7");
		button7.setPrefSize(150,150);
		button7.setId("7");
		button7.setOnAction(new SimpleEventHandler());
	
		button8 = new Button("8");
		button8.setPrefSize(150,150);
		button8.setId("8");
		button8.setOnAction(new SimpleEventHandler());
		
		button9 = new Button("9");
		button9.setPrefSize(150,150);
		button9.setId("9");
		button9.setOnAction(new SimpleEventHandler());
		
		buttonStar = new Button("*");
		buttonStar.setPrefSize(150,150);
		buttonStar.setId("*");
		buttonStar.setOnAction(new SimpleEventHandler());
		
		button0 = new Button("0");
		button0.setPrefSize(150,150);
		button0.setId("0");
		button0.setOnAction(new SimpleEventHandler());
		
		buttonHash = new Button("#");
		buttonHash.setPrefSize(150,150);
		buttonHash.setId("#");
		buttonHash.setOnAction(new SimpleEventHandler());
		
		// set up the displayable image
		
		GridPane gridPane = new GridPane();
		gridPane.setPrefSize(500, 500); // default size
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		
		String style = "";  // style for the overall grid
        style += " -fx-background-insets: 0,1,2; -fx-background-radius: 3,2,1; ";
        style += " -fx-padding: 3 30 3 30; -fx-text-fill: black; -fx-font: 62 arial;";
		
		gridPane.setStyle(style);
		
		// Add the buttons to the grid.
		// Layout is column first, then row
		gridPane.add(button1, 0, 0);
		gridPane.add(button2, 1, 0);
		gridPane.add(button3, 2, 0);
		gridPane.add(button4, 0, 1);
		gridPane.add(button5, 1, 1);
		gridPane.add(button6, 2, 1);
		gridPane.add(button7, 0, 2);
		gridPane.add(button8, 1, 2);
		gridPane.add(button9, 2, 2);
		gridPane.add(buttonStar, 2, 3);
		gridPane.add(button0, 1, 3);
		gridPane.add(buttonHash, 0, 3);
		
		
		// Add the grid to the root container
		root.getChildren().add(gridPane);
		
		// Create a scene, add it to the stage,
		// title it, then make it visible
		
		stage.setScene(new Scene(root));
		stage.setTitle("Apartment Complex Keypad 9000");
		stage.show();	
	}  // end method start
	
	// Handler class
	class SimpleEventHandler implements EventHandler<ActionEvent>
	{	
		public void handle(ActionEvent event)
		{
			// Identify the button that was clicked by examining its ID.
			// First, get the object that caused the event. 
			Object source = event.getSource();		
			// Only buttons have event handlers, so cast the Object 
			// to a Button.
		    Button clickedBtn = (Button) source; 
		    // Retrieve the ID (as a String) of the Button who triggered
		    // the event
		    String sid = clickedBtn.getId();
		    
			//clear screen and keypad "buffer" if "*" is typed
		    if (sid == "*")
		    {
		    	message ="";
		    	textDisplay.setText("");
		    	keypadInput ="";
		    }
		    //append button ID only for numbers. Display "*" for each button pressed		      
			if (sid != "*" && sid != "#")
			{
			    keypadInput += sid; 
			    message +="*";
			    textDisplay.setText(message);
			}
			  //clear display and store 1st input
		    if (sid == "#" && !aptEntered)
			  {
				  textDisplay.setText("");	
				  message="";
				  textDisplay.setText(message);
				  input1 = keypadInput;		
				  keypadInput ="";			
				  aptEntered = true;//this block will be skipped next time "#" is entered
				  return;	//exit simpleEventHandler
			  }	
		    if (sid == "#" && aptEntered)
		    {
		    	input2 = keypadInput;
				textDisplay.setText("");	//clear screen
			    message="";
				keypadInput ="";			//clear keypad "buffer"
			    textDisplay.setText(message);
				aptEntered = false;
		    	Keypad.tenantSearch(input1, input2);
		    }  
		}  // end method handle		
	}  // end class SimpleEventHandler	
}  // end class GraphicDemo
