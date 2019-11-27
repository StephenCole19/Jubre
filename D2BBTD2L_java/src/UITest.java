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
 * UI test for PostAssignment use case
 * @author scole4
 *
 */
public class UITest extends Application {
	
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
		succ = new Label("Pending");
		
		btnExit = new Button("Exit");
		assignName = new TextField();
		assignName.setPromptText("Assignment Name");
		
		submitBtn = new Button("Submit!");
		filePath = new TextField();
		filePath.setPromptText("Click for file picker");
		date = new DatePicker();
		pick = new FileChooser();
		
		postPane= new FlowPane(Orientation.VERTICAL);
		
		
		btnExit.setOnAction(this::processExitButtonPress);
		submitBtn.setOnAction(this::processSubmitButtonPress);
	}
	
	
	public void processExitButtonPress(ActionEvent Event) {
		primaryStage.close();
	}
	
	public void processSubmitButtonPress(ActionEvent Event) {
		DataManager dm = new DataManager();
		PostAssignmentControl control = new PostAssignmentControl(dm);
		PostAssignmentUI ui = new PostAssignmentUI(control);
		
		String assName = assignName.getText();
		java.util.Date dueDateIn = java.sql.Date.valueOf(date.getValue());
		
		boolean wow = ui.requestPostAssignment(assName, selected, dueDateIn);
		
		if(wow == true) {
			succ.setText("Success!");
		} else {
			succ.setText("Failure!");
		}
		
	}
	
	public void initPostAssignment() {
		loadPostAssignmentScene();
		
		postPane.getChildren().add(btnExit);
		postPane.getChildren().add(assignName);
		postPane.getChildren().add(filePath);
		postPane.getChildren().add(date);
		
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
        
        
		postScene = new Scene(postPane, 900, 600);
		
	}
	
}
