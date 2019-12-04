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
public class DataManager{
	//yikes
	Connection connection = null;
	
	
	/**
	 * This is the DataManager constructor that forms a connection to the cs204301ateam2 database
	 * A potential place for update would be the fact that a connection is established every time 
	 * a data manager is created, which may not be necessary as we could have 1 general data manager 
	 * that we pass around references to. Hasn't been decided as of November 13, 2019
	 *  -  Brennan Couturier
	 */
	@SuppressWarnings("deprecation")
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
	 * 
	 * @param usernameIn
	 * @param passwordIn
	 * @return
	 * @throws SQLException
	 */
	public Account getAccountFromLoginInfo(String usernameIn, String passwordIn) throws SQLException
	{
		PreparedStatement getAccountFromUsernamePs;
		String getAccountFromUsernameQuery = "select * from Account where username = ? and password = sha1(?);";
		
		Account a = null;
		
		try
		{
			getAccountFromUsernamePs = connection.prepareStatement(getAccountFromUsernameQuery);
			getAccountFromUsernamePs.setString(1, usernameIn);
			getAccountFromUsernamePs.setString(2, passwordIn);
			ResultSet rs = getAccountFromUsernamePs.executeQuery();
			
			if (rs.next())
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
		
		if (getAccountFromUsernamePs != null)
		{
			try
			{
				getAccountFromUsernamePs.close();
			}
			catch (SQLException e)
			{
				throw e;
			}
		}
		return a;
	}
	
	/**
	 * 
	 * @param idIn
	 * @return
	 * @throws SQLException
	 */
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
			
			if (rs.next())
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
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
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
	
	/**
	 * 
	 * @param accountId
	 * @return
	 * @throws SQLException
	 */
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
			
			if(rs.next())
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
	 * 
	 * @param messageText
	 * @param from_accountId
	 * @param to_accountId
	 * @return
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
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
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

	/**
	 * 
	 * @param ac
	 * @param type
	 * @return
	 */
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
	 * 
	 * @return
	 */
	public AssignmentSubmission[] getAssSubs()
	{
		PreparedStatement ps;
		ArrayList<AssignmentSubmission> arr = new ArrayList<AssignmentSubmission>();
		try
		{
			ps = connection.prepareStatement("SELECT * FROM AssignmentSubmission t1 JOIN Assignment t2 ON t1.assignmentId = t2.assignmentId");
			ResultSet rs = ps.executeQuery();

			while(rs.next())
			{
				AssignmentSubmission assSub = new AssignmentSubmission(
					new Assignment(
						rs.getString(9),
						rs.getInt(4),
						rs.getDate(11)
						),
					rs.getInt(1),
					rs.getDouble(5)
					);

				arr.add(assSub);
			}

			ps.close();
		}
		catch(SQLException e) {}

		return arr.toArray(new AssignmentSubmission[0]);
	}

	
	/**
	 * 
	 * @param st
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
	 * 
	 * @param ta
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
	 * 
	 * @param profAc
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
	 * 
	 * @param adminAc
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
	* @author StephenCole19
	*/
	public Assignment requestAssignment(String assignmentName) {
		Assignment assignment = new Assignment();
    	
			try {
				Statement st = connection.createStatement();

				ResultSet rs = st.executeQuery("select * from Assignment where AssignmentName = '" + assignmentName + "';" );
				
				rs.next();
				
				assignment.assignmentId = rs.getInt(1);
				assignment.courseOfferingId = rs.getInt(2);
				assignment.assignmentName = rs.getString(3);
				assignment.assignmentFile = rs.getBlob(4);
				assignment.dueDate = rs.getDate(5);
	
	
	   		} catch (SQLException e) {
				System.err.println("SQL error: Assignment not found");
	   	 }
	
	   	
	   	return assignment;
	}


	public ArrayList<String> requestAssignmentNames(){

		ArrayList<String> assignmentList = new ArrayList<String>();

		try {
			Statement st = connection.createStatement();

			ResultSet rs = st.executeQuery("select AssignmentName from Assignment;" );

			
			while(rs.next()){
				assignmentList.add(rs.getString(1));
			}
			
		} catch (SQLException e) {
			System.err.println("SQL error: Assignment not found");
  
  public ArrayList<String> requestCourseNames(){
		ArrayList<String> courseList = new ArrayList<String>();

		try {
			Statement st = connection.createStatement();
      
      ResultSet rs = st.executeQuery("select courseNumber from Course;" );
			
			while(rs.next()){
				courseList.add(rs.getString(1));
			}
     } catch (SQLException e) {
			System.err.println("SQL error: Assignment not found");
		}
		return courseList;
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

	public int retrieveCourseOfferingID(String courseNumber){
		int courseOfferingID = -1;
		try {
			Statement st = connection.createStatement();

			ResultSet rs = st.executeQuery("select courseId from Course where courseNumber = '"+ courseNumber +"';" );
			
			rs.next();
			int courseID = rs.getInt(1);

			rs = st.executeQuery("select courseOfferingId from CourseOfferingInfo where courseId ="+ courseID +";" );
			
			rs.next();
			courseOfferingID = rs.getInt(1);

			
		} catch (SQLException e) {
			System.err.println("SQL error: Assignment not found");
		}
		return courseOfferingID;
	}

	/** 
	* @throws SQLException 
	* @author StephenCole19
	*/

	public void uploadAssignment(String courseNumber, String assName, Blob blobFile, java.sql.Date dueDate) throws SQLException {
		int courseOfferingID = retrieveCourseOfferingID(courseNumber);
		PreparedStatement ps = connection.prepareStatement(
		        "INSERT INTO Assignment (courseOfferingId, assignmentName, assignmentFile, dueDate) VALUES (?,?,?,?)");
		ps.setInt(1, courseOfferingID);
		ps.setString(2, assName);
		ps.setBlob(3, blobFile);
		ps.setDate(4, dueDate);
		ps.executeUpdate();
	}


	/**
	 * 
	 * @param offering
	 * @throws SQLException
	 */
	public void addCourseOfferingInfo(CourseOfferingInfoObject offering)
           throws SQLException {
          System.out.println("in addCourseOfferingInfo");
          PreparedStatement ps = connection.prepareStatement(
            "insert into CourseOfferingInfo (professorId, taId, roomNum," 
            + "courseId, term, year, classLength, classTime, monday, tuesday,"
            + "wednesday,thursday,friday) "
            + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
          ps.setInt(1, offering.getProfessorId());
          ps.setInt(2, offering.getTaId());
          ps.setString(3, offering.getRoomNumber());
          ps.setInt(4, offering.getCourseId());
          ps.setInt(5, offering.getTerm());
          ps.setInt(6, offering.getYear());
          ps.setInt(7, (int)(offering.getCourseLength()));
          ps.setString(8, offering.getTime());
          ps.setBoolean(9, offering.getDOW()[0]);
          ps.setBoolean(10, offering.getDOW()[1]);
          ps.setBoolean(11, offering.getDOW()[2]);
          ps.setBoolean(12, offering.getDOW()[3]);
          ps.setBoolean(13, offering.getDOW()[4]);
          
          ps.executeUpdate();
	}


		/**
		 * 
		 * @return
		 * @throws SQLException
		 */
        public String[] getAvailableTAs() throws SQLException {
          System.out.println("in getAvailableTAs");
          ArrayList<String> results = new ArrayList<String>();
          String query = "select firstName,lastName from Account where accountType=3";
          PreparedStatement statement = connection.prepareStatement
          (query);
          ResultSet rs = statement.executeQuery();
          while(rs.next()) {
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            System.out.println("TA from DB: " + firstName + " " + lastName);
            results.add(firstName + "~" + lastName);
          }
          return results.toArray(new String[results.size()]);
        }
        
        /**
         * 
         * @return
         * @throws SQLException
         */
        public String[] getAvailableProfessors() throws SQLException {
          System.out.println("in getAvailableProfessors");
          ArrayList<String> results = new ArrayList<String>();
          String query = "select firstName,lastName from Account where accountType=4";
          PreparedStatement statement = connection.prepareStatement(query);
          ResultSet rs = statement.executeQuery();
          while(rs.next()) {
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            System.out.println("Profs from DB: " + firstName + " " + lastName);
            results.add(firstName + "~" + lastName);
          }
          return results.toArray(new String[results.size()]);
        }
        
        /**
         * 
         * @return
         * @throws SQLException
         */
        public String[] getAvailableCourses() throws SQLException {
          System.out.println("in getAvailableCourses");
          ArrayList<String> results = new ArrayList<String>();
          String query = "select courseNumber from Course";
          PreparedStatement statement = connection.prepareStatement(query);
          ResultSet rs = statement.executeQuery();
          while(rs.next()) {
        	String courseNum = rs.getString("courseNumber");
        	System.out.println("Course Num from DB: " + courseNum);
            results.add(courseNum);
          }
          return results.toArray(new String[results.size()]);
        }
        
        /**
         * 
         * @param firstName
         * @param lastName
         * @return
         * @throws SQLException
         */
        public int getAccountId(String firstName, String lastName) throws SQLException {
            System.out.println("in getAccountId");
            PreparedStatement statement = connection.prepareStatement("select accountId from Account where firstName=? and lastName=?");
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            ResultSet rs = statement.executeQuery();
            rs.next();
            int accountId = rs.getInt("accountId");
            System.out.println("accountId upon exit: " + accountId);
            return accountId;
        }
        
        /**
         * 
         * @param courseNum
         * @return
         * @throws SQLException
         */
        public int getCourseId(String courseNum) throws SQLException {
            System.out.println("in getCourseId. courseNum: " + courseNum);
            PreparedStatement statement = connection.prepareStatement("select courseId from Course where courseNumber=?");
            statement.setString(1, courseNum);
            ResultSet rs = statement.executeQuery();
            rs.next();
            int courseId = rs.getInt("courseId");
            System.out.println("courseId upon exit: " + courseId);
            return courseId;
        }

}

