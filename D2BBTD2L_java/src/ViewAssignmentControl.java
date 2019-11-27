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


	
	public void downloadFile(int assignmentId) {
		
		Assignment assignment = retrieveAssignmentFile(assignmentId);
		
		try {
			InputStream is = assignment.assignmentFile.getBinaryStream();
			ReadableByteChannel rbc = Channels.newChannel(is);
			FileOutputStream fos = new FileOutputStream(System.getProperty("user.home") + "/Downloads/" + assignment.assignmentName);
			
			
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
			rbc.close();		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public Assignment retrieveAssignmentFile(int assignmentId) {
		 return dataManager.requestAssignment(assignmentId);
	}
}
