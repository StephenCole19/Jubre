import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

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
	
	//-------------------------------------------------------------------------------
	
	//Javafx variables
	//-------------------------------------------------------------------------------
	/**
	 * This button will display all of the messages the current user has sent
	 */
	private Button btnSentItems;
	
	private int userId;
	
	/**
	 * This button will display all of the messages the current user has recieved
	 */
	private Button btnRecievedItems;
	
	private ListView<Message> tableValues;
	
	private GridPane viewMessagesPane;
	
	private Scene scViewMessages;
	
	//-------------------------------------------------------------------------------
	
	public viewMessagesUI()
	{
		vmc = new viewMessagesControl();
		MainMenu mm = new MainMenu();
		
		userId = mm.getUserId();
	}
	
	private void initViewMessagesComponents()
	{
		tableValues = getListViewMessagesReceived(userId);
		
		btnSentItems = new Button("Sent Items");
		btnSentItems.setOnAction(this::processSentItemsButtonPress);
		
		btnRecievedItems = new Button("Inbox");
		btnRecievedItems.setOnAction(this::processRecievedItemsButtonPress);
		
		viewMessagesPane = new GridPane();
		viewMessagesPane.setHgap(20);
		viewMessagesPane.setVgap(20);
		viewMessagesPane.setAlignment(Pos.CENTER);
	}

	public Scene initViewMessagesScene()
	{
		initViewMessagesComponents();
		
		viewMessagesPane.add(tableValues, 0, 0);
		
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
		
		if (messages.isEmpty())
		{
			System.out.println("No messages");
		}
		else
		{
			System.out.println("Messages");
			for (int i = 0; i < messages.size(); i++)
			{
				int messageId = messages.get(i).getMessageId();
				System.out.println(i + ": " + messageId);
			}
		}
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
		
		if (messages.isEmpty())
		{
			System.out.println("No messages");
		}
		else
		{
			System.out.println("Messages");
			for (int i = 0; i < messages.size(); i++)
			{
				int messageId = messages.get(i).getMessageId();
				System.out.println(i + ": " + messageId);
			}
		}
	}
	
	private ListView<Message> getListViewMessagesReceived(int userId)
	{
		ArrayList<Message> messages = new ArrayList<Message>();
		try 
		{
			messages = vmc.getMessagesReceived(userId);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		ObservableList<Message> messagesList = FXCollections.observableArrayList(messages);
		
		ListView<Message> messagesListView = new ListView<Message>(messagesList);
		messagesListView.setCellFactory(param -> new ListCell<Message>()
				{
					@Override
					protected void updateItem(Message item, boolean empty)
					{
						super.updateItem(item, empty);
						
						if (empty || item == null || item.getMessageText() == null)
						{
							setText(null);
						}
						else
						{
							setText(item.getMessageText());
						}
					}
				});
		if(messages.isEmpty())
		{
			System.err.println("no messages");
		}
		return messagesListView;
	}
	
	
	private void processSentItemsButtonPress(ActionEvent event)
	{
		//Populate table with list of messages the user sent
	}
	
	private void processRecievedItemsButtonPress(ActionEvent event)
	{
		//populate table with list of messages the user received
	}
}