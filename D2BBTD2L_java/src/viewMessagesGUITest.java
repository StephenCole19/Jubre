


import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class viewMessagesGUITest extends Application
{	
	private static Stage stgMain;
	private static DataManager dm;
	
	private Scene scMain;
	private GridPane mainPane;
	private Button btnPostMessage;
	
	public void start(Stage primaryStage)
	{
		stgMain = primaryStage;
		dm = new DataManager();
		displayMainMenu(stgMain);
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	private void initMainMenuComponents()
	{
		btnPostMessage = new Button("New Message");
		btnPostMessage.setOnAction(this::processPostMessageButtonPress);
		btnPostMessage.setPrefSize(100, 100);
		
		mainPane = new GridPane();
		mainPane.setHgap(20);
		mainPane.setVgap(20);
		mainPane.setAlignment(Pos.CENTER);
	}
	
	private Scene initMainMenu()
	{
		initMainMenuComponents();
		
		mainPane.add(btnPostMessage, 1, 1);
		
		scMain = new Scene(mainPane, 900, 600);
		return scMain;
	}
	
	private void displayMainMenu(Stage stg)
	{
		stg.setScene(initMainMenu());
		stg.show();
	}
	
	public Stage getStage()
	{
		return stgMain;
	}
	
	
	public DataManager getDataManager()
	{
		return dm;
	}
	
	
	public void resetToMainMenu()
	{
		displayMainMenu(getStage());
	}
	
	public void processPostMessageButtonPress(ActionEvent event)
	{
		viewMessagesUI vmu = new viewMessagesUI();
		vmu.displayViewMessages(stgMain);
	}
}
