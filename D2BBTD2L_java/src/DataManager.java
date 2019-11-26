
/**
 * 
 */

//import static CourseInfoObject.*;
//import static CourseOfferingInfoObject.*;
//import static notificationObject.*;
import java.sql.*;
import java.util.*;
/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author Brennan Couturier
*/
public class DataManager {
	
	Connection connection = null;
	
	
	/**
	 * This is the DataManager constructor that forms a connection to the cs204301ateam2 database
	 * A potential place for update would be the fact that a connection is established every time 
	 * a data manager is created, which may not be necessary as we could have 1 general data manager 
	 * that we pass around references to. Hasn't been decided as of November 13, 2019
	 *  -  Brennan Couturier
	 */
	public DataManager() 
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}
		catch (Exception e) 
		{
			System.err.println(e.toString());
		}
		String url = "jdbc:mysql://cs2043.cs.unb.ca:3306/cs204301ateam2";
		try 
		{
			connection = DriverManager.getConnection(url, "cs204301ateam2", "Z34SRYfW");
		}
		catch (SQLException e)
		{
			System.err.println("Database connection error.");
		}
	}
	
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private Quiz quiz;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private Assignment assignment;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private AssignmentSubmission assignmentSolution;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private Notification notification;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private LockerItem locker;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private CourseInfo courseInfo;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private TA_Account account;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private TA_Account account2;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private TA_Account account3;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private TA_Account account4;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private ProfessorAccount professorAccount;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private StudentAccount userAccount;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private AdminAccount adminAccount;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private CourseRegistration courseRegistration;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private CourseOfferingInfo courseOfferingInfo;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private Account account5;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private CourseOfferingInfo courseOfferingInfo2;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private AdminAccount adminAccount2;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private ProfessorAccount professorAccount2;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private TA_Account tA_Account;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private StudentAccount studentAccount;
	
	public Account getAccountFromId(int idIn) throws SQLException
	{
		PreparedStatement getAccountFromIdPs;
		String getAccountFromIdQuery = "select * from Account where accountId = ?;";
		
		Account a = null;
		
		try
		{
			getAccountFromIdPs = connection.prepareStatement(getAccountFromIdQuery);
			getAccountFromIdPs.setInt(1, idIn);
			ResultSet rs = getAccountFromIdPs.executeQuery();
			
			if (rs != null)
			{
				int id = rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				int accountType = rs.getInt(4);
				String firstName = rs.getString(5);
				String lastName = rs.getString(6);
				
				a = new Account(id, username, password, accountType, firstName, lastName);
			}
		}
		catch (SQLException e)
		{
			throw e;
		}
		
		if (getAccountFromIdPs != null)
		{
			try
			{
				getAccountFromIdPs.close();
			}
			catch (SQLException e)
			{
				throw e;
			}
		}
		
		return a;
	}
	
	public ArrayList<Account> getAllAccounts() throws SQLException
	{
		PreparedStatement getAllAccountsPs;
		String getAllAccountsQuery = "select * from Account;";
		
		ArrayList<Account> accounts = new ArrayList<Account>();
		
		try
		{
			getAllAccountsPs = connection.prepareStatement(getAllAccountsQuery);
			
			ResultSet rs = getAllAccountsPs.executeQuery();
			
			while (rs.next())
			{
				int id = rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				int accountType = rs.getInt(4);
				String firstName = rs.getString(5);
				String lastName = rs.getString(6);
				
				Account a = new Account(id, username, password, accountType, firstName, lastName);
				
				accounts.add(a);
			}
		}
		catch (SQLException e)
		{
			throw e;
		}
		
		if (getAllAccountsPs != null)
		{
			try
			{
				getAllAccountsPs.close();
			}
			catch (SQLException e)
			{
				throw e;
			}
			
		}
		
		return accounts;
	}
	
	public boolean accountExists(int accountId) throws SQLException
	{
		PreparedStatement checkAccountExistsPs;
		String checkAccountExistQuery = "select count(*) from Account where accountId = ?;";
		int rowNum = 0;
		int numAccounts = 0;
		
		try
		{
			checkAccountExistsPs = connection.prepareStatement(checkAccountExistQuery);
			checkAccountExistsPs.setInt(1, accountId);
			ResultSet rs = checkAccountExistsPs.executeQuery();
			
			if(rs != null)
			{
				rs.last();
				rowNum = rs.getRow();
				numAccounts = rs.getInt(rowNum);
			}
			
			if (numAccounts == 1)
			{
				return true;
			}
		}
		catch (SQLException e)
		{
			throw e;
		}
		
		if (checkAccountExistsPs != null)
		{
			try
			{
				checkAccountExistsPs.close();
			}
			catch(SQLException e)
			{
				throw e;
			}
		}
		
		return false;
	}

	
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	 * @throws SQLException 
	*/
	public boolean handleMessageSubmit(String messageText, int from_accountId, int to_accountId) throws SQLException 
	{
		
		boolean submitSuccessful = false;
		
		PreparedStatement submitMessagePs;
		
		String submitMessageQuery = "insert into Message (sentTimestamp, messageText, from_accountId, to_accountId) values (?, ?, ?, ?);";
		
		Timestamp timeSent = new Timestamp(System.currentTimeMillis());
		
		try
		{
			submitMessagePs = connection.prepareStatement(submitMessageQuery);
			submitMessagePs.setTimestamp(1, timeSent);
			submitMessagePs.setString(2, messageText);
			submitMessagePs.setInt(3, from_accountId);
			submitMessagePs.setInt(4, to_accountId);
			
			int numRowsChanged = submitMessagePs.executeUpdate();
			
			if (numRowsChanged == 1)
			{
				submitSuccessful = true;
			}
		}
		catch (SQLException e)
		{
			throw e;
		}
		
		if (submitMessagePs != null)
		{
			try
			{
				submitMessagePs.close();
			}
			catch (SQLException e)
			{
				throw e;
			}
		}
		
		return submitSuccessful;
	}

	/** 
	* This method retrieves from the database all the messages that have a passed user's id as the recipient's id
	 * @throws SQLException
	 * @param userId The id of the user whose messages will be retrieved
	*/
	
	public ArrayList<Message> requestMessagesReceived(int userId) throws SQLException 
	{
		//Declare the prepared statement
		PreparedStatement requestMessagesReceivedPs;
		
		//Initialize the message request query. Will select all the messages that have the user's id as the to_accountId value
		String requestMessagesReceivedQuery = "select * from Message where to_accountId = ?;";
		
		//Create a list of all messages
		ArrayList<Message> messages = new ArrayList<Message>();
		
		//Declare message variables
		int messageId;
		Timestamp timeSent;
		String messageText;
		int from_accountId;
		int to_accountId;
		
		try
		{
			//Prepare the message request query
			requestMessagesReceivedPs = connection.prepareStatement(requestMessagesReceivedQuery);
			
			//Put the user's id number into the query, so that only messages addressed to that user are retrieved
			requestMessagesReceivedPs.setInt(1, userId);
			
			//Execute the query to retrieve a set of SQL rows
			ResultSet rs = requestMessagesReceivedPs.executeQuery();
			
			//If there are any message rows retrieved, create message objects out of them then add them to a linked list
			//If there are no message rows retrieved, return an empty list
			while (rs.next())
			{				
				messageId = rs.getInt(1);
				timeSent = rs.getTimestamp(2);
				messageText = rs.getString(3);
				from_accountId = rs.getInt(4);
				to_accountId = rs.getInt(5);
				
				Message m = new Message(messageId, timeSent, messageText, from_accountId, to_accountId);
				
				messages.add(m);
			}
		}
		catch (SQLException e)
		{
			throw e;
		}
		
		if (requestMessagesReceivedPs != null)
		{
			try
			{
				requestMessagesReceivedPs.close();
			}
			catch (SQLException e)
			{
				throw e;
			}
		}
		
		return messages;
	}
	
	public ArrayList<Message> requestMessagesSent(int userId) throws SQLException
	{
		PreparedStatement requestMessagesSentPs;
		
		String requestMessagesSentQuery = "select * from Message where from_accountId = ?;";
		
		ArrayList<Message> messages = new ArrayList<Message>();
		
		//Declare message variables
		int messageId;
		Timestamp timeSent;
		String messageText;
		int from_accountId;
		int to_accountId;
		
		try
		{
			//Prepare the message request query
			requestMessagesSentPs = connection.prepareStatement(requestMessagesSentQuery);
					
			//Put the user's id number into the query, so that only messages addressed to that user are retrieved
			requestMessagesSentPs.setInt(1, userId);
					
			//Execute the query to retrieve a set of SQL rows
			ResultSet rs = requestMessagesSentPs.executeQuery();
					
			//If there are any message rows retrieved, create message objects out of them then add them to a linked list
			//If there are no message rows retrieved, return an empty list
			while (rs.next())
			{				
				messageId = rs.getInt(1);
				timeSent = rs.getTimestamp(2);
				messageText = rs.getString(3);
				from_accountId = rs.getInt(4);
				to_accountId = rs.getInt(5);
						
				Message m = new Message(messageId, timeSent, messageText, from_accountId, to_accountId);
						
				messages.add(m);
			}
		}
		catch (SQLException e)
		{
			throw e;
		}
		
		if (requestMessagesSentPs != null)
		{
			try
			{
				requestMessagesSentPs.close();
			}
			catch (SQLException e)
			{
				throw e;
			}
		}
				
		return messages;
	}

	public int handleCreateAccount(Account ac, int type) {
		try {
			String query = "insert into Account (username,password,accountType,firstName,lastName)" + 
							"values (?,sha1(?),?,?,?)";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, ac.getUsername());
			pst.setString(2, ac.getPassword());
			pst.setInt(3, ac.getAccountType());
			pst.setString(4, ac.getFirstName());
			pst.setString(5,ac.getLastName());
			
			pst.executeUpdate();
			
			String query2 = "select accountId from Account where username = ?;";
			PreparedStatement getID  = connection.prepareStatement(query2);
			getID.setString(1, ac.getUsername());
			ResultSet rs = getID.executeQuery();
			
			int id = 0;
			while(rs.next()) {
				 id = Integer.parseInt(rs.getString(1));
			}
			return id;
		} catch (SQLException e) {
			//if insert was unsuccessful then -1 is returned. This is so the UI has something to identify.
			System.err.println(e.getMessage());
			return -1;
		}	
	}
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void handleCreateStudent(StudentAccount st) {
		try {
			Statement stmt = connection.createStatement();
			String query = "insert into StudentAccount (accountId, hasReadNotifications) values ('" + st.accountId + 
							"', " + st.hasUnreadNotifications + ")";
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void handleCreateTA(TA_Account ta) {
		try {
			Statement stmt = connection.createStatement();
			String query = "insert into TA_Account (accountId,email) values ('" +
							ta.accountId + "', '" + ta.email + "')";
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void handleCreateProf(ProfessorAccount profAc) {
		try {
			Statement stmt = connection.createStatement();
			String query = "insert into ProfessorAccount (accountId,faculty) values " +
							"('" + profAc.accountId + "', '" + profAc.faculty + "')";
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void handleCreateAdmin(AdminAccount adminAc) {
		try {
			Statement stmt = connection.createStatement();
			String query = "insert into AdminAccount (accountId,position) values " +
							"('" + adminAc.accountId + "', '" + adminAc.position + "')";
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void getNotifications() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void markNotificationsRead() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void getMarks() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void markFeedbackRead() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void addCourseInfo() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void addsAssingmentInfo() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void addMark() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void getLocker() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void getLockerItem() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void requestAssignmentFileassignmentId() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

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
	public void uploadAssignmentSubmissionstudentIdfileassignmentId() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param offeringInfo
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void addCourseOfferingInfo(CourseOfferingInfoObject offeringInfo) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param notification
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void postNotification(notificationObject notification) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param courseId
	* @return
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Set<CourseInfoObject> retrieveCourseInfo(int courseId) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param paths
	* @return
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Boolean uploadFilesToLocker(String... paths) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void createNewQuiz() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void postQuizResults() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void getQuiz() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void submitAnswers() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void getAvailableQuizzes() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void addCourseRegistrationInfoArrayListCourseRegistrationObject() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public double getTotalLockerSize() {
		// begin-user-code
		// TODO Auto-generated method stub
		return 0;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void checkLoginCredentials() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}
}