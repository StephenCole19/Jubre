import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PostAssignmentUI{
	
	private Scene postScene;
	private FlowPane postPane;
	private Label succ;
	private Button submitBtn;
	private Button btnExit;
	private TextField filePath;
	private TextField assignName;
	private FileChooser pick;
	private DatePicker date;
	private Stage primaryStage;
	private File selected;
	private PostAssignmentControl control;
	private ComboBox courseNameList;
	
	public PostAssignmentUI() {
		control = new PostAssignmentControl(MainMenu.getDataManager());
	}
	
	
	public void displayPostAssignmentForm(Stage stg) { 
		stg.setScene(initPostAssignment());
		stg.show();
		
	}
	
	public void loadPostAssignmentScene() {
		succ = new Label("Pending");
				
		btnExit = new Button("Exit");
		assignName = new TextField();
		assignName.setPromptText("Assignment Name");

		submitBtn = new Button("Submit!");
		filePath = new TextField();
		filePath.setPromptText("Click for file picker");
		date = new DatePicker();
		pick = new FileChooser();

		ArrayList<String> courseNames = control.getCourseNames();
		courseNameList = new ComboBox();
		courseNameList.getItems().addAll(courseNames.toArray());

		postPane= new FlowPane(Orientation.VERTICAL);


		btnExit.setOnAction(this::processExitButtonPress);
		submitBtn.setOnAction(this::processSubmitButtonPress);
	}
	
	
	public void processExitButtonPress(ActionEvent Event) {
		ProfMainMenu pmm = new ProfMainMenu();
		pmm.resetToMainMenu();
	}
	
	public void processSubmitButtonPress(ActionEvent Event) {
		
		String assName = assignName.getText();
		java.util.Date dueDateIn = java.sql.Date.valueOf(date.getValue());
		String courseName = (String) courseNameList.getValue();
		
		boolean wow = control.postAssignment(courseName, assName, selected, dueDateIn);
		
		if(wow == true) {
			succ.setText("Success!");
		} else {
			succ.setText("Failure!");
		}
		
	}
	
	public Scene initPostAssignment() {
		loadPostAssignmentScene();
		
		postPane.getChildren().add(btnExit);
		postPane.getChildren().add(assignName);
		postPane.getChildren().add(filePath);
		postPane.getChildren().add(date);
		postPane.getChildren().add(courseNameList);
		
		postPane.getChildren().add(succ);
		postPane.getChildren().add(submitBtn);
		
		postPane.setVgap(10);
		postPane.setAlignment(Pos.CENTER);
		postPane.setColumnHalignment(HPos.CENTER); 
	    	postPane.setRowValignment(VPos.CENTER); 
	    	postPane.setStyle("-fx-background-color: yellow;");
	    
	    	filePath.setOnMouseClicked(e -> {
	        	selected = pick.showOpenDialog(primaryStage);
	        	filePath.setText(selected.getPath());
	    	});
	    
	    
		return postScene = new Scene(postPane, 900, 600);
		
	}
        
        
		return postScene = new Scene(postPane, 900, 600);
		
	}



}
