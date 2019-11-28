


import java.sql.SQLException;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class handles all of the GUI aspects of the post message UI
 * @author Brennan Couturier, 3638808
 *
 */
public class postMessageUI
{

	//Non javafx variables
	//-------------------------------------------------------------------------------


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
	 * This button allows the user to go back to the view messages screen
	 */
	private Button btnBack;

	/**
	 * This label will direct the user to select a user to send a message to from a drop down menu
	 */
	private Label lblSelectRecipient;

	/**
	 * This label will let a user know if they made a mistake writing a message. Non-editable
	 */
	private Label lblErrorMessage;
	/**
	 * This will be the text field where the user will write their message
	 */
	private TextField txtMessageInput;

	/**
	 * This combo box will be filled with all the users that the user can send a message to.
	 */
	private ComboBox<Account> cbAvailableRecipients;


	//-------------------------------------------------------------------------------

	/**
	 * This is used to create a new instance of postMessageUI
	 */
	public postMessageUI()
	{

	}

	/**
	 * This initializes all the GUI variables
	 */
	private void initPostMessageComponents()
	{
		btnExit = new Button("Main Menu");
		btnExit.setOnAction(this::processExitButtonPress);
		btnExit.setPrefWidth(100);

		btnBack = new Button("Back");
		btnBack.setOnAction(this::processBackButtonPress);
		btnBack.setPrefWidth(100);

		btnPostMessage = new Button("Submit");
		btnPostMessage.setOnAction(this::processPostMessageButtonPress);
		btnPostMessage.setPrefWidth(100);

		lblSelectRecipient = new Label("Choose a recipient");

		lblErrorMessage = new Label("");
		lblErrorMessage.setPrefWidth(400);
		lblErrorMessage.setPrefHeight(50);

		txtMessageInput = new TextField();
		txtMessageInput.setPrefSize(400, 200);
		txtMessageInput.setAlignment(Pos.TOP_LEFT);

		postMessageControl pmc = new postMessageControl();
		try
		{
			cbAvailableRecipients = pmc.fillAccountsComboBox();
		}
		catch (SQLException e)
		{
			displayErrorMessage(" SQLException: Problem loading available accounts.");
		}
		cbAvailableRecipients.setPrefWidth(300);

		postMessagePane = new GridPane();
		postMessagePane.setHgap(20);
		postMessagePane.setVgap(20);
		postMessagePane.setAlignment(Pos.CENTER);
		//postMessagePane.setGridLinesVisible(true);

	}

	/**
	 * This creates a scene object with all the postMessageUI GUI components
	 * @return A scene object with all the postMessageUI GUI components
	 */
	private Scene initPostMessageScene()
	{
		initPostMessageComponents();


		//postMessagePane.add(btnExit, 9, 1);
		postMessagePane.add(btnBack, 10, 1);
		postMessagePane.add(btnPostMessage, 10, 10);
		postMessagePane.add(lblSelectRecipient, 0, 1);
		postMessagePane.add(txtMessageInput, 0, 3, 11, 7);
		postMessagePane.add(cbAvailableRecipients, 1, 1, 3, 1);
		postMessagePane.add(lblErrorMessage, 0, 10, 9, 1);

		scPostMessage = new Scene(postMessagePane, 900, 600, Color.WHITESMOKE);
		return scPostMessage;
	}


	/**
	 * Used to set the scene in the main window/stage
	 * @param stg The main window that the program is using
	 */
	public void displayCreateMessageForm(Stage stg)
	{
	    stg.setScene(initPostMessageScene());
		stg.show();
	}

	/**
	 * This gets values from the textfield and drop down menu and stores a message in the database
	 */
	public void writeMessage()
	{
		messageText = txtMessageInput.getText().trim();
		from_id = MainMenu.getUserAccount().getAccountId();
		String errorMessage = "";
		postMessageControl pmc = new postMessageControl();


		//Take message String and account ids from gui
		if (messageText.isEmpty())
		{
			errorMessage = errorMessage + " Message is a required field.";
		}
		else if (messageText.length() > 65000)
		{
			errorMessage = errorMessage + " Message is way too long.";
		}
		
		if (cbAvailableRecipients.getValue() == null)
		{
			errorMessage = errorMessage + " Please select a recipient.";
		}
		else
		{

			to_id = cbAvailableRecipients.getValue().getAccountId();
		}

		if (!errorMessage.isEmpty() && messageText.length() < 65000)
		{
			displayErrorMessage(errorMessage);
		}
		else
		{
			try
			{
				postSuccessful = pmc.postMessage(messageText, from_id, to_id);
			}
			catch(SQLException e)
			{
				displayErrorMessage(" SQLException: " + e.getMessage());
			}

			if (!postSuccessful)
			{
				displayErrorMessage(" A problem occurred. Message was not successfully sent.");
			}
			else
			{
				displayPostMessageConfirmation();
			}
		}
	}


	public void displayPostMessageConfirmation()
	{
		lblErrorMessage.setText(" Message successfully sent");
		lblErrorMessage.setBorder(new Border(new BorderStroke(Color.STEELBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
		lblErrorMessage.setBackground(new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, CornerRadii.EMPTY, null)));
		
		Task<Void> sleeper = new Task<Void>()
		{
			protected Void call() throws Exception
			{
				try
				{
					Thread.sleep(2500);
				}
				catch(InterruptedException e)
				{
					
				}
				return null;
			}
		};
		
		sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>()
		{
			public void handle(WorkerStateEvent event)
			{
				viewMessagesUI vmu = new viewMessagesUI();
				vmu.displayViewMessages(MainMenu.getStage());
			}
		});
		new Thread(sleeper).start();
		
		
	}

	private void processExitButtonPress(ActionEvent event)
	{
		//if student is logged in
		StudentMainMenu smm = new StudentMainMenu();
		smm.resetToMainMenu();
	}

	private void processPostMessageButtonPress(ActionEvent event)
	{
		writeMessage();
	}

	private void processBackButtonPress(ActionEvent event)
	{
		viewMessagesUI vmu = new viewMessagesUI();
		vmu.resetToViewMessagesUI();
	}

	private void displayErrorMessage(String errorMessage)
	{
		lblErrorMessage.setText(errorMessage);
		lblErrorMessage.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
		lblErrorMessage.setBackground(new Background(new BackgroundFill(Color.MISTYROSE, CornerRadii.EMPTY, null)));
	}


}
