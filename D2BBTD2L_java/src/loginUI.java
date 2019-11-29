

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class displays the login and handles IO
 * @author Brennan Couturier, 3638808
 */
public class loginUI 
{
	private Boolean loginSuccessful;

	private String username;
	private String password;
	
	private Image mainMenuLogo;
	private ImageView mainMenuLogoView;
	private Image mainMenuIcon;
	private ImageView mainMenuIconView;
	private Scene scLogin;
	private GridPane loginPane;
	private Label lblUsername;
	private Label lblPassword;
	private Label lblError;
	private TextField txtUsername;
	private PasswordField txtPassword;
	private Button btnLogin;
	private Button btnQuit;
	
	public void displayLoginForm(Stage stg) 
	{
		stg.setScene(initLogin());
		stg.show();
	}
	
	private void initLoginComponents()
	{
		
		try 
		{
			mainMenuLogo = new Image(new FileInputStream("images/D2BBTD2L_Logo.png"));
			mainMenuIcon = new Image(new FileInputStream("images/logo.png"));
		} 		
		catch (FileNotFoundException e) 
		{
			System.err.println(e.getMessage());
		}
		
		mainMenuLogoView = new ImageView(mainMenuLogo);
		mainMenuLogoView.setFitHeight(160);
		mainMenuLogoView.setFitWidth(489);
		
		mainMenuIconView = new ImageView(mainMenuIcon);
		mainMenuIconView.setFitHeight(100);
		mainMenuIconView.setFitWidth(100);		
		
		txtUsername = new TextField("");
		txtUsername.setPrefWidth(150);
		
		txtPassword = new PasswordField();
		txtPassword.setPrefWidth(150);
		//txtPassword.setSkin(new PasswordSkin(txtPassword));
		
		lblError = new Label("");
		lblError.setPrefSize(300, 50);
		lblError.setAlignment(Pos.CENTER);
		
		lblUsername = new Label("Username:");
		lblUsername.setPrefWidth(80);
		lblUsername.setAlignment(Pos.CENTER_RIGHT);
		
		lblPassword = new Label("Password:");
		lblPassword.setPrefWidth(80);
		lblPassword.setAlignment(Pos.CENTER_RIGHT);
		
		btnLogin = new Button("Login");
		btnLogin.setOnAction(this::processLoginButtonPress);
		btnLogin.setPrefWidth(150);
		
		btnQuit = new Button("Quit");
		btnQuit.setOnAction(this::processExitButtonPress);
		btnQuit.setPrefWidth(80);
		
		loginPane = new GridPane();
		loginPane.setHgap(20);
		loginPane.setVgap(20);
		loginPane.setAlignment(Pos.CENTER);
		
	}
	
	private Scene initLogin()
	{
		initLoginComponents();
		loginPane.add(txtUsername, 3, 1);
		loginPane.add(txtPassword, 3, 2);
		loginPane.add(lblUsername, 2, 1);
		loginPane.add(lblPassword, 2, 2);
		loginPane.add(lblError, 2, 5, 3, 1);
		loginPane.add(mainMenuIconView, 1, 0);
		loginPane.add(btnQuit, 5, 0);
		loginPane.add(btnLogin, 3, 4);
		//loginPane.setGridLinesVisible(true);
		
		scLogin = new Scene(loginPane, 900, 600);
		//scLogin.getStylesheets().add("darkMode.css");
		scLogin.getStylesheets().add("lightMode.css");
		
		return scLogin;
	}

	private void enterCredentials() 
	{
		username = txtUsername.getText().trim();
		password = txtPassword.getText().trim();
		
		if (username.isEmpty() && password.isEmpty())
		{
			displayErrorMessage("Username and Password are required fields");
		}
		else if (username.isEmpty())
		{
			displayErrorMessage("Username is a required field");
		}
		else if (password.isEmpty())
		{
			displayErrorMessage("Password is a required field");
		}
		else
		{
			handleLogin(username, password);
		}
		
	}
	
	private void handleLogin(String username, String password)
	{
		loginControl lc = new loginControl();
		try 
		{
			loginSuccessful = lc.handleLogin(username, password);
		} 
		catch (SQLException e) 
		{
			displayErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		
		if (loginSuccessful)
		{
			displayLoginConfirmation("Login successful");
		}
		else
		{
			displayErrorMessage("Login unsuccessful");
		}
	}

	private void displayLoginConfirmation(String message) 
	{
		lblError.setText(message);
		
		
		lblError.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
		lblError.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, null)));
		
		
		
		Task<Void> sleeper = new Task<Void>()
		{
			protected Void call() throws Exception
			{
				try
				{
					Thread.sleep(1000);
				}
				catch(InterruptedException e)
				{
					
				}
				return null;
			}
		};
		
		sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>()
		{
			public void handle(WorkerStateEvent event)
			{
				
				MainMenu.setUser(loginControl.getAccount());
				MainMenu.displayMainMenu();
				
			}
		});
		new Thread(sleeper).start();
		
		
	}
	
	private void processLoginButtonPress(ActionEvent event)
	{
		enterCredentials();
	}
	
	private void processExitButtonPress(ActionEvent event)
	{
		MainMenu.getStage().close();
	}
	
	private void displayErrorMessage(String errorMessage)
	{
		lblError.setText(errorMessage);
		
		
		lblError.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
		lblError.setBackground(new Background(new BackgroundFill(Color.MISTYROSE, CornerRadii.EMPTY, null)));
		
		
	}
	
}