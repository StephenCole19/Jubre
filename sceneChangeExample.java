import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainMenu extends Application
{
	private Stage stgMain;

	//This is the "Main" method of a Javafx application
	//primaryStage is the window that your application is using.
	//I store it in a class variable so that it can be used outside of start
	//This is how our project will look because Login will be the first thing to be shown
	public void start(Stage primaryStage)
	{
		stgMain = primaryStage;
		scLogin = initLogin();
		stgMain.setScene(scLogin);
		stgMain.setTitle("Login");
		stgMain.show();

		//if login successful, display main menu
		//scMainMenu = initMainMenu();
		//stgMain.setScene(scMainMenu);
		//stgMain.show();
	}

	//This just creates a blank screen
	private Scene initMainMenu()
	{
		GridPane examplePane = new GridPane();
		Scene exampleScene = new Scene(examplePane, 900, 600);
		return exampleScene;
	}

	//This loads the login screen and returns the scene
	private void initLogin()
	{
		Login l = new Login();
		Scene loginScene = l.loadLoginScene();
	}

	//This will be used by any class to return to the main menu
	public void resetToMainMenu()
	{
		Scene m = initMainMenu();
		stgMain.setScene(m);
		stgMain.show();
	}
}

//---------------------------------------------------------------------------

public class Login
{
	//These are just some random variables
	private Scene loginScene;
	private GridPane loginPane;
	private Label lblSuccess;
	private Button btnExit;

	//This initializes the variables
	private void loadLoginSceneComponents()
	{
		lblSucces = new Label("Success");
		btnExit = new Button("Exit");
		//This makes it so when the button is pressed, this method is called
		btnExit.setOnAction(this::processExitButtonPress);

		loginScene = new GridPane();
	}

	//Creates the login screen and returns it
	public Scene initLogin()
	{
		loadLoginSceneComponents();

		loginPane.add(lblSucces, 5, 5);
		loginPane.add(btnExit, 5, 6);

		loginScene = new Scene(loginPane, 900, 600);
		return loginScene;
	}

	//Executes the resetToMainMenu method to go back to the main menu
	private void processExitButtonPress(ActionEvent event)
	{
		MainMenu m = new MainMenu();
		m.resetToMainMenu();
	}
}
