import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.sql.SQLException;
import java.util.*;


public class ViewAssignmentControl {

	private DataManager dataManager;
	
	
	public ViewAssignmentControl(DataManager dm) {
        this.dataManager = dm;
	}


	
	public void downloadFile(String assignmentName) {
		
		Assignment assignment = retrieveAssignmentFile(assignmentName);
		
		try {
			InputStream is = assignment.assignmentFile.getBinaryStream();
			ReadableByteChannel rbc = Channels.newChannel(is);
			FileOutputStream fos = new FileOutputStream(System.getProperty("user.home") + "/Downloads/" + assignment.assignmentName);
			
			
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
			rbc.close();		
		} catch (FileNotFoundException e) {
			System.out.println("NO FILE");
		} catch (IOException e) {
			System.out.println("IO Except");
		} catch (SQLException e) {
			System.out.println("SQL");
		}
	}

	public ArrayList<String> getAssignmentNames(){
		return dataManager.requestAssignmentNames();
	}

	public Assignment retrieveAssignmentFile(String assignmentName) {
		return dataManager.requestAssignment(assignmentName);
	}
}
