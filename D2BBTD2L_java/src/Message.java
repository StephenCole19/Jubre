/**
 * 
 */

import java.sql.Timestamp;
/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author bcouturi
 */
public class Message 
{
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	*/
	private static int messageId;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	*/
	private String messageText;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	*/
	private int from_accountId;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	*/
	private int to_accountId;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private Account account;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private Account account2;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	*/
	private Timestamp sentTimestamp;
	
	/**
	 * This is the constructor for a message. 
	 */
	public Message()
	{
		
	}
	
	/**
	 * This sets the message id.
	 * Used to store the id from the database
	 * @param id The id of the message
	 */
	public void setMessageId(int id)
	{
		messageId = id;
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
	 * @param timeSent The time the message was stored in the database
	 */
	public void setSentTimestamp(Timestamp timeSent)
	{
		sentTimestamp = timeSent;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void SQLInsertMessage() 
	{
		// begin-user-code
		// TODO Auto-generated method stub
		
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void SQLGetMessages() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}
}