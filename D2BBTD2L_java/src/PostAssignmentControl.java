import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.sql.rowset.serial.SerialBlob;



public class PostAssignmentControl {
	
	private DataManager dataManager;
	
	public PostAssignmentControl(DataManager dm) {
		this.dataManager = dm;
	}
	
	public boolean postAssignment(String courseNumber, String assName, File pdfFile, java.util.Date dueDate) {
		boolean success = true;
		
		Blob blobFile = null;
		try {
			byte[] pdfData = new byte[(int) pdfFile.length()];
			DataInputStream dis = new DataInputStream(new FileInputStream(pdfFile));
			dis.readFully(pdfData);
			dis.close();
			blobFile = new SerialBlob(pdfData);
			
			
		    	java.sql.Date sqlDate =  new java.sql.Date(dueDate.getTime());
			
			dataManager.uploadAssignment(courseNumber, assName, blobFile, sqlDate);
			
		} catch (FileNotFoundException e) {
			success = false;
			System.out.print("FNF");
		} catch (IOException e) {
			success = false;
			System.out.println("IO");
		} catch (SQLException e) {
			success = false;
			System.out.println("SQL");
		}
		
		
		
		
		return success;
	}
	public ArrayList<String> getCourseNames(){
		return dataManager.requestCourseNames();
	}
}
