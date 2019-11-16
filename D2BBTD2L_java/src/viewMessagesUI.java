import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Button;

/**
 * 
 */

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author bcouturi
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class viewMessagesUI {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private viewMessagesControl vmc;
	
	//Javafx variables
	
	/**
	 * This button will allow the user to display all the messages they've sent
	 */
	private Button btnSentItems;
	
	/**
	 * This button will allow the user to display all the messages they've recieved
	 */
	private Button btnRecievedItems;
	
	private void initViewMessagesComponents()
	{
		btnSentItems = new Button("Sent Items");
		//btnSentItems.setOnAction(this::processSentItemsButtonPress);
		
		btnRecievedItems = new Button("Inbox");
	}
	
	public viewMessagesUI()
	{
		vmc = new viewMessagesControl();
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void displayMessages(int userId) {
		// begin-user-code
		// TODO Auto-generated method stub
		ArrayList<Message> messages = new ArrayList<Message>();
		
		try
		{
			messages = vmc.getMessages(userId);
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
		
		// end-user-code
	}
}