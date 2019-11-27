
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class handles all of the GUI aspects of the viewMessages use case
 * @author Brennan Couturier, 3638808
 *
 */
public class viewMessagesUI
{

	//Non-javafx variables
	//-------------------------------------------------------------------------------

	private viewMessagesControl vmc;

	private int userId;


	//-------------------------------------------------------------------------------

	//Javafx variables
	//-------------------------------------------------------------------------------
	/**
	 * This button will display all of the messages the current user has sent
	 */
	private Button btnSentItems;
	/**
	 * This button will display all of the messages the current user has recieved
	 */
	private Button btnRecievedItems;

	/**
	 * This button will open the postMessageUI and allow the user to post a message
	 */
	private Button btnNewMessage;

	private Button btnExit;

	/**
	 * Used to neatly format the screen
	 */
	private GridPane viewMessagesPane;

	/**
	 * This scene is used to display a list of messages as well as the information of a selected one
	 */
	private Scene scViewMessages;

	/**
	 * This is the table listing all the messages a user has received or sent, depending on which button is pressed
	 * Received messages are displayed by default
	 */
	private TableView<Message> messagesTable;

	/**
	 * This displays a brief description of each message in the table
	 */
	private TableColumn messagesCol;

	/**
	 * This label will display the textual aspect of a selected message
	 */
	private Label lblTxtMessage;

	/**
	 * This label will display "From" if the user is looking at received messages, and "To" if they are looking at sent messages
	 */
	private Label lblFromTo;

	/**
	 * This label will display the name of the user who sent the message, or the name of the user who received the message, depending on what button is pressed
	 */
	private Label lblName;

	/**
	 * This label will display a Timestamp of when the message was received/sent.
	 */
	private Label lblDate;

	//-------------------------------------------------------------------------------

	public viewMessagesUI()
	{
		vmc = new viewMessagesControl();
		userId = MainMenu.getUserId();
	}

	private void initViewMessagesComponents()
	{
		initMessagesTable();

		lblTxtMessage = new Label("");
		lblTxtMessage.setPrefSize(480, 400);
		lblTxtMessage.setStyle("-fx-background-color: WHITE");
		lblTxtMessage.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));

		lblFromTo = new Label("From:");
		lblFromTo.setPrefWidth(50);
		lblFromTo.setAlignment(Pos.CENTER_RIGHT);

		lblName = new Label("");
		lblName.setPrefWidth(200);


		lblDate = new Label("");
		lblDate.setPrefWidth(150);

		btnExit = new Button("Back");
		btnExit.setOnAction(this::processExitButtonPress);
		btnExit.setPrefWidth(50);

		btnSentItems = new Button("Sent Items");
		btnSentItems.setOnAction(this::processSentItemsButtonPress);
		btnSentItems.setPrefWidth(150);

		btnRecievedItems = new Button("Inbox");
		btnRecievedItems.setOnAction(this::processRecievedItemsButtonPress);
		btnRecievedItems.setPrefWidth(150);

		btnNewMessage = new Button("New Message");
		btnNewMessage.setOnAction(this::processNewMessageButtonPress);
		btnNewMessage.setPrefWidth(150);

		viewMessagesPane = new GridPane();
		viewMessagesPane.setHgap(10);
		viewMessagesPane.setVgap(10);
		viewMessagesPane.setAlignment(Pos.CENTER);
		viewMessagesPane.setStyle("-fx-background-color: whitesmoke");
		//viewMessagesPane.setGridLinesVisible(true);
	}

	public Scene initViewMessagesScene()
	{
		initViewMessagesComponents();
		//displayMessagesReceived(userId);

		viewMessagesPane.add(btnExit, 5, 1);
		viewMessagesPane.add(lblFromTo, 2, 5);
		viewMessagesPane.add(lblName, 3, 5);
		viewMessagesPane.add(lblDate, 4, 5);
		viewMessagesPane.add(lblTxtMessage, 2, 2, 4, 10);
		viewMessagesPane.add(btnSentItems, 0, 6);
		viewMessagesPane.add(btnRecievedItems, 0, 5);
		viewMessagesPane.add(btnNewMessage, 0, 1);
		viewMessagesPane.add(messagesTable, 1, 1, 1, 10);

		scViewMessages = new Scene(viewMessagesPane, 900, 600);
		return scViewMessages;

	}

	public void displayViewMessages(Stage stg)
	{
		stg.setScene(initViewMessagesScene());
		stg.show();
	}

	public void displayMessagesRecieved(int userId)
	{
		ArrayList<Message> messages = new ArrayList<Message>();


		try
		{
			messages = vmc.getMessagesReceived(userId);
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
		ObservableList<Message> listMessages = FXCollections.observableArrayList(messages);

		messagesCol.setCellValueFactory(new PropertyValueFactory<Message, String>("receivedDescription"));
		messagesTable.setItems(listMessages);
		messagesTable.setOnMousePressed(this::processReceivedMessageChosen);
		//fill table with messages
	}

	public void displayMessagesSent(int userId)
	{
		ArrayList<Message> messages = new ArrayList<Message>();

		try
		{
			messages = vmc.getMessagesSent(userId);
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
		ObservableList<Message> listMessages = FXCollections.observableArrayList(messages);

		messagesCol.setCellValueFactory(new PropertyValueFactory<Message, String>("sentDescription"));
		messagesTable.setItems(listMessages);
		//fill table with messages
	}


	private void processSentItemsButtonPress(ActionEvent event)
	{
		//displayMessagesSent(userId);
	}

	private void processRecievedItemsButtonPress(ActionEvent event)
	{
		//displayMessagesReceived(userId);
	}

	private void processNewMessageButtonPress(ActionEvent event)
	{
		postMessageUI pmu = new postMessageUI();

		pmu.displayCreateMessageForm(MainMenu.getStage());
	}

	private void processExitButtonPress(ActionEvent event)
	{
		//if student is logged in
		StudentMainMenu smm = new StudentMainMenu();
		smm.resetToMainMenu();
	}

	private void processReceivedMessageChosen(MouseEvent event)
	{
		Message m = messagesTable.getSelectionModel().getSelectedItem();
		lblTxtMessage.setText(m.getMessageText());
		lblName.setText(m.getFromAccount().getFullName());
		
		//lblDate.setText(string);
		//	lblDate.setText(string);
	}

	public void resetToViewMessagesUI()
	{
		displayViewMessages(MainMenu.getStage());
	}

	private void initMessagesTable()
	{
		messagesTable = new TableView<Message>();
		messagesTable.setEditable(false);
		messagesTable.setPrefSize(200, 500);
		messagesCol = new TableColumn("Messages");
		messagesCol.setMinWidth(200);
		messagesTable.getColumns().addAll(messagesCol);

		messagesTable.setOnMousePressed(this::processReceivedMessageChosen);

	}
}
