import java.sql.SQLException;
import java.util.ArrayList;


public class viewMessagesControl
{

	private DataManager dm;

	public viewMessagesControl()
	{
		dm = MainMenu.getDataManager();
	}


	public ArrayList<Message> getMessagesReceived(int userId) throws SQLException
	{
		ArrayList<Message> messages = new ArrayList<Message>();
		try
		{
			messages = dm.requestMessagesReceived(userId);
		}
		catch (SQLException e)
		{
			throw e;
		}

		return messages;
	}

	public ArrayList<Message> getMessagesSent(int userId) throws SQLException
	{
		ArrayList<Message> messages = new ArrayList<Message>();
		try
		{
			messages = dm.requestMessagesSent(userId);
		}
		catch (SQLException e)
		{
			throw e;
		}

		return messages;
	}
}
