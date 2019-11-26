

import java.sql.SQLException;
import java.sql.Timestamp;

import javafx.beans.property.SimpleStringProperty;

/**
 * This class represents a Message object
 * @author Brennan Couturier, 3638808
 *
 */
public class Message 
{
	/**
	 * This is the id of the message. This is the primary key in the message table
	 */
	private int messageId;
	
	/**
	 * This is the textual aspect of the message
	 */
	private String messageText;
	
	/**
	 * This is the id number of the user who sent the message
	 */
	private int from_accountId;
	
	/**
	 * This is the id number of the user to whom the message was sent.
	 */
	private int to_accountId;
	
	/**
	 * This is a Timestamp of when the message was sent
	 */
	private Timestamp timeSent;
	
	private final SimpleStringProperty receivedDescription;
	
	private final SimpleStringProperty sentDescription;
	
	private DataManager dm;
	
	private Account fromAccount;
	
	private Account toAccount;
	
	/**
	 * This constructor is to be used when retrieving messages from the database, when all values are known
	 * @param messageId The id number of the message
	 * @param timeSent The time the message was stored in the database
	 * @param messageText The textual aspect of the message
	 * @param from_accountId The id of the user sending the message
	 * @param to_accountId The id of the user receiving the message
	 */
	public Message(int messageId, Timestamp timeSent, String messageText, int from_accountId, int to_accountId)
	{
		dm = MainMenu.getDataManager();
		try 
		{
			fromAccount = dm.getAccountFromId(from_accountId);
			toAccount = dm.getAccountFromId(to_accountId);
		} 
		catch (SQLException e) 
		{
			System.err.println(e.getMessage());
		}
		
		receivedDescription = new SimpleStringProperty(fromAccount.getFullName() + "\t" + timeSent + "\n" + messageText);
		sentDescription = new SimpleStringProperty(toAccount.getFullName() + "\t" + timeSent + "\n" + messageText);
		
		this.messageId = messageId;
		this.timeSent = timeSent;
		this.messageText = messageText;
		this.from_accountId = from_accountId;
		this.to_accountId = to_accountId;
		
	}
	
	public Account getFromAccount()
	{
		return fromAccount;
	}
	
	public Account getToAccount()
	{
		return toAccount;
	}
	
	/**
	 * This sets the message text
	 * @param text The text that the message will contain
	 */
	public void setMessageText(String text)
	{
		messageText = text;
	}
	
	/**
	 * This sets the id of the user the message is from
	 * @param fromId The id of the user the message is from
	 */
	public void setFromAccountId(int fromId)
	{
		from_accountId = fromId;
	}
	
	/**
	 * This sets the id of the user the message is being sent to
	 * @param ToId The id of the user the message is being sent to
	 */
	public void setToAccountId(int ToId)
	{
		to_accountId = ToId;
	}
	
	
	/**
	 * This sets the time that the message was sent (stored in the database)
	 * @param timeSentIn The time the message was stored in the database
	 */
	public void setTimeSent(Timestamp timeSentIn)
	{
		timeSent = timeSentIn;
	}
	
	/**
	 * This returns the id of the calling message object
	 * @return The id of the calling message object
	 */
	public int getMessageId()
	{
		return messageId;
	}
	
	/**
	 * This returns the textual portion of the message
	 * @return The textual portion of the message
	 */
	public String getMessageText()
	{
		return messageText;
	}
	
	/**
	 * This returns the id number of the user who sent the message
	 * @return The id number of the user who sent the message
	 */
	public int getFromAccountId()
	{
		return from_accountId;
	}
	
	/**
	 * This returns the id number of the user to whom the message was sent
	 * @return The id number of the user to whom the message was sent
	 */
	public int getToAccountId()
	{
		return to_accountId;
	}
	
	/**
	 * This returns the time the message was sent as a Timestamp
	 * @return The time the message was sent as a Timestamp
	 */
	public Timestamp getTimeSent()
	{
		return timeSent;
	}
	
	public String getReceivedDescription()
	{
		return receivedDescription.get();
	}
	
	public String getSentDescription()
	{
		return sentDescription.get();
	}
}