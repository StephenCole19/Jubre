

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

/**
 * This class handles all of the interaction between the UI and the DataManager for the postMessage use case
 * @author Brennan Couturier, 3638808
 *
 */
public class postMessageControl
{

	/**
	 * A DataManager object to call methods in the DataManager to execute queries
	 */
	private DataManager dm;

	/**
	 * This constructor creates an instance of the postMessageControl class and instantiates the DataManager object dm
	 */
	public postMessageControl()
	{
		dm = MainMenu.getDataManager();
	}

	/**
	 * This calls the DataManager's getAllAccounts method to get a list of all the accounts in the database
	 * @return A list of all the accounts in the database
	 * @throws SQLException Throws an SQLException if there is a problem performing the SQL query
	 */
	public ArrayList<Account> getAllAccounts() throws SQLException
	{
		return dm.getAllAccounts();
	}

	/**
	 * This fills a combo box with all of the accounts in the database for use by the UI
	 * @return A combo box filled with all of the accounts in the database for use by the UI
	 * @throws SQLException Throws an SQLException if there is a problem getting all of the accounts from the database
	 */
	public ComboBox<Account> fillAccountsComboBox() throws SQLException
	{
		ArrayList<Account> availableAccounts = new ArrayList<Account>();

		try
		{
			availableAccounts = getAllAccounts();
		}
		catch (SQLException e)
		{
			throw e;
		}

		Account accounts[] = new Account[availableAccounts.size()];
		accounts = availableAccounts.toArray(accounts);
		ComboBox<Account> cb = new ComboBox<Account>(FXCollections.observableArrayList(accounts));

		return cb;
	}

	/**
	 * This saves a message to the database
	 * @param messageText The textual aspect of the message
	 * @param from_accountId The id number of the user sending the message
	 * @param to_accountId The id number of the user who is receiving the message
	 * @return A boolean indicating whether the insertion of the message into the database was successful.
	 * @throws SQLException An SQLException is thrown if a problem occurs in the insert statement in the DataManager's handleMessageSubmit method
	 */
	public boolean postMessage(String messageText, int from_accountId, int to_accountId) throws SQLException
	{
		boolean successful = false;

		try
		{
			if (dm.accountExists(to_accountId))
			{
				successful = dm.handleMessageSubmit(messageText, from_accountId, to_accountId);
			}
		}
		catch (SQLException e)
		{
			throw e;
		}

		return successful;
	}
}

