import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.sql.SQLException;
import java.util.Scanner;

import com.oracle.tools.packager.IOUtils;

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class ViewAssignmentUI {

	private Scene postScene;
	private FlowPane viewPane;
	private Button downBtn;
	private Button btnExit;
	private TextField assID;
	private Stage primaryStage;
	private ViewAssignmentControl control;
	
	public ViewAssignmentUI(ViewAssignmentControl control) {
		this.control = control;
	}
	

	/**
		Just for CLI
	**/
	public void displayAssignmentSelectionForm() {
		 System.out.println("Enter the AssignementID");
		 
		 Scanner scanner = new Scanner(System.in);
         	int assignmentID = Integer.parseInt(scanner.next());
         	scanner.close();
         
         	requestDownloadFile(7);
         
         	System.out.println("wow");
	}
	
	
	public void requestDownloadFile(int assignmentID) {
		control.downloadFile(assignmentID);
		
	}
	
	public void displayPostAssignmentForm() { 
		initPostAssignment();
		
	}
	
	public void loadPostAssignmentScene() {
		btnExit = new Button("Exit");
		downBtn = new Button("Download");
		assID = new TextField();
		assID.setPromptText("Enter AssignmentID");

		viewPane= new FlowPane(Orientation.VERTICAL);

		
		
		btnExit.setOnAction(this::processExitButtonPress);
		downBtn.setOnAction(this::processDownButtonPress);
	}
	
	
	public void processExitButtonPress(ActionEvent Event) {
		primaryStage.close();
	}
	
	public void processDownButtonPress(ActionEvent Event) {
		int assignID = Integer.parseInt(assID.getText());
		
		DataManager dm = new DataManager();
		ViewAssignmentControl control = new ViewAssignmentControl(dm);
		ViewAssignmentUI ui = new ViewAssignmentUI(control);
		
		ui.requestDownloadFile(assignID);
		
		
	}
	
	
	public Scene initPostAssignment() {
		loadPostAssignmentScene();
		
		viewPane.getChildren().add(btnExit);
		viewPane.getChildren().add(assID);
		
		viewPane.getChildren().add(downBtn);
		
		viewPane.setVgap(10);
		viewPane.setAlignment(Pos.CENTER);
		viewPane.setColumnHalignment(HPos.CENTER); 
        viewPane.setRowValignment(VPos.CENTER); 
        viewPane.setStyle("-fx-background-color: yellow;");
        
        
        
		return postScene = new Scene(viewPane, 900, 600);
		
	}



}
