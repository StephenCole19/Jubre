import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * UI test for ViewAssignment use case
 * @author StephenCole19
 *
 */
public class UITestView extends Application {
	
	private Scene postScene;
	private FlowPane viewPane;
	private Button downBtn;
	private Button btnExit;
	private TextField assID;
	private Stage primaryStage;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		initPostAssignment();
	
		primaryStage.setResizable(false);
		primaryStage.setScene(postScene);
		primaryStage.show();
		
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
	
	
	public void initPostAssignment() {
		loadPostAssignmentScene();
		
		viewPane.getChildren().add(btnExit);
		viewPane.getChildren().add(assID);
		
		viewPane.getChildren().add(downBtn);
		
		viewPane.setVgap(10);
		viewPane.setAlignment(Pos.CENTER);
		viewPane.setColumnHalignment(HPos.CENTER); 
        viewPane.setRowValignment(VPos.CENTER); 
        viewPane.setStyle("-fx-background-color: yellow;");
        
        
        
		postScene = new Scene(viewPane, 900, 600);
		
	}
	
}
