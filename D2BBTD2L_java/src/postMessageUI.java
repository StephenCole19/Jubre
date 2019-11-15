

import java.sql.SQLException;
import java.util.*;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Brennan Couturier 3638808
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class postMessageUI {
	
	//Non javafx variables
	private postMessageControl pmc;
	private boolean postSuccessful;
	private int from_id;
	private int to_id;
	private Scanner scan = new Scanner(System.in);
	private String messageText;
	
	//javafx variables
	
	
	/**
	 * This is used to create a new instance of postMessageUI
	 */
	public postMessageUI()
	{
		
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void displayCreateMessageForm() {
		// begin-user-code
		// TODO Auto-generated method stub
		System.out.println("Enter sender id");
		from_id = scan.nextInt();
		System.out.println("Enter recipient id");
		to_id = scan.nextInt();
		
		postMessageUI pmu = new postMessageUI();
		pmu.writeMessage();
		
		scan.close();
		
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void writeMessage() {
		// begin-user-code
		// TODO Auto-generated method stub
		System.out.println("Enter message:");
		messageText = scan.nextLine();
		pmc = new postMessageControl();
		
		System.out.println(messageText);
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
			
		// end-user-code
	}

	
	public void displayPostMessageConfirmation() {
		// begin-user-code
		// TODO Auto-generated method stub
		System.out.println("Message saved to database");
		// end-user-code
	}
}