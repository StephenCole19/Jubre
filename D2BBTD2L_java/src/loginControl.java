

public class loginControl {
	
	private Account CurrentUser;
	
	private DataManager dm;
	
	public LoginControl(DataManager datamanager) {
		dm = datamanagerm;
	}  
	
	public loginControl()
	{
		dm = MainMenu.getDataManager();
	}
	
	public boolean checkCredentials(String userName,String passWord) {
		Account account = dm.Account(userName, passWord);
		if (account == null) {
			return false;
		}
		
		saveCurrentUser(account);
		return true;
	}
	
	public void saveCurrentUser(Account current) {
		CurrentUser = current;
	}
	
	public Account returnCurrentUser() {
		return CurrentUser;

/**
 * 
 */

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author bcouturi
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class loginControl {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private DataManager dataManager;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void getAssignmentSpecificationsassignmentId() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void getStudentId() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void checkCredentials() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code

	}
}