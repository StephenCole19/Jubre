

import java.sql.SQLException;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Brennan Couturier 3638808
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class postMessageUI {
	
	//Non javafx variables
	//-------------------------------------------------------------------------------
	/**
	 * This is a reference to the postMessageControl to call the postMessage method
	 */
	private postMessageControl pmc;
	
	/**
	 * This is a boolean to check if the post was successful
	 */
	private boolean postSuccessful;
	
	/**
	 * This is the account id of the person sending the message
	 */
	private int from_id;
	
	/**
	 * This is the account id of the person who the message is being sent to
	 */
	private int to_id;
	
	/**
	 * This is the textual aspect of the message
	 */
	private String messageText;
	
	//javafx variables
	//-------------------------------------------------------------------------------
	/**
	 * This is the screen that will allow the user to post a message
	 */
	private Scene scPostMessage;
	
	/**
	 * This is used to format the screen in a neat manner
	 */
	private GridPane postMessagePane;
	
	/**
	 * This is the button that will return the user to the main menu
	 */
	private Button btnExit;
	
	/**
	 * This button will allow the user to post a message after they've included all the required information
	 */
	private Button btnPostMessage;
	
	/**
	 * This label will direct the user to select a user to send a message to from a drop down menu
	 */
	private Label lblSelectRecipient;
	
	/**
	 * This will be the text field where the user will write their message
	 */
	private TextField txtMessageInput;
	
	/**
	 * This combo box will be filled with all the users that the user can send a message to.
	 */
	private ComboBox<Account> cbAvailableRecipients;
	
	/**
	 * This will be a list of all the accounts a user can send a message to. Their names will be shown in the combo box
	 */
	private ArrayList<Account> availableAccounts;
	
	
	//-------------------------------------------------------------------------------
	
	/**
	 * This initializes all the GUI variables
	 */
	private void initPostMessageComponents()
	{
		btnExit = new Button("Back");
		btnExit.setOnAction(this::processExitButtonPress);
		btnExit.setPrefWidth(80);
		
		btnPostMessage = new Button("Submit");
		btnPostMessage.setOnAction(this::processPostMessageButtonPress);
		btnPostMessage.setPrefWidth(80);
		
		lblSelectRecipient = new Label("Choose a recipient");
		
		txtMessageInput = new TextField();
		txtMessageInput.setPrefSize(400, 200);
		txtMessageInput.setAlignment(Pos.TOP_LEFT);
		
		cbAvailableRecipients = fillAccountsComboBox();
		cbAvailableRecipients.setPrefWidth(300);
		
		postMessagePane = new GridPane();
		postMessagePane.setHgap(20);
		postMessagePane.setVgap(20);
		postMessagePane.setAlignment(Pos.CENTER);
		//postMessagePane.setGridLinesVisible(true);
		
	}
	
	/**
	 * This creates a scene object with all the GUI elements to post a message
	 */
	public Scene initPostMessageScene()
	{
		initPostMessageComponents();
		
		postMessagePane.add(btnExit, 10, 1);
		postMessagePane.add(btnPostMessage, 10, 10);
		postMessagePane.add(lblSelectRecipient, 0, 1);
		postMessagePane.add(txtMessageInput, 0, 3, 11, 7);
		postMessagePane.add(cbAvailableRecipients, 1, 1, 3, 1);
		
		scPostMessage = new Scene(postMessagePane, 900, 600);
		return scPostMessage;
	}
	
	
	
	/**
	 * This is used to create a new instance of postMessageUI
	 */
	public postMessageUI()
	{
		
	}


	public void displayCreateMessageForm(Stage stg) 
	{
		stg.setScene(initPostMessageScene());
		stg.show();
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	*/
	public void writeMessage() {
		
		//Take message String and account ids from gui
		
		
		try
		{
			postSuccessful = pmc.postMessage(messageText, from_id, to_id);
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
		
		if (!postSuccessful)
		{
			System.err.println("Error sending message");
		}
		else
		{
			displayPostMessageConfirmation();
		}
			
	}

	
	public void displayPostMessageConfirmation() {
		// begin-user-code
		// TODO Auto-generated method stub
		System.out.println("Message saved to database");
		// end-user-code
	}
	
	private ComboBox<Account> fillAccountsComboBox()
	{
		pmc = new postMessageControl();
		//This would populate an arraylist with all the accounts in the system
		try
		{
			availableAccounts = pmc.getAllAccounts(); 
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
				
		Account accounts[] = new Account[availableAccounts.size()];
		accounts = availableAccounts.toArray(accounts);
		ComboBox<Account> cb = new ComboBox<Account>(FXCollections.observableArrayList(accounts));
		
		return cb;
	}
	
	private void processExitButtonPress(ActionEvent event)
	{
		//Return to main menu
	}
	
	private void processPostMessageButtonPress(ActionEvent event)
	{
		//Call writeMessage()
	}
	
	
}