import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.*;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainMenu extends Application
{
	//Non-javafx variables
	//-------------------------------------------
	private static DataManager dm;
	private static Account userAccount;
	
	//Splash screen variables
	//-------------------------------------------
	private Scene splashScene;
	private GridPane splashPane;
	private Image logo;
	
	//javafx variables
	//-------------------------------------------
	private static Stage stgMain;

	public void start(Stage primaryStage)
	{	
		stgMain = primaryStage;
		stgMain.setResizable(false);
		dm = new DataManager();
		Account tempUser = new Account(1, "TestUsername", "TestPassword", 1, "Brennan", "Couturier");
		userAccount = tempUser;
		
		Stage temp = new Stage();
		temp.initStyle(StageStyle.UNDECORATED);
		displaySplashScreen(temp);
		
		Task<Void> sleeper = new Task<Void>()
		{
			protected Void call() throws Exception
			{
				try
				{
					Thread.sleep(2500);
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
				temp.close();
				
				//Login
				//If login successful call displayMainMenu()
				//i.e. if userAccount was set to something, login was good
				if (userAccount != null)
				{
					displayMainMenu();
				}
				else
				{
					System.err.println("account is null");
				}
				
			}
		});
		new Thread(sleeper).start();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
	
	private Scene initSplashScreen()
	{
		
		try 
		{
			logo = new Image(new FileInputStream("images/logo.png"));
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println(e.getMessage());
		}
		ImageView logoView = new ImageView(logo);
		
		splashPane = new GridPane();
		splashPane = new GridPane();
		splashPane.setHgap(20);
		splashPane.setVgap(20);
		splashPane.setAlignment(Pos.CENTER);
		splashPane.setStyle("-fx-background-color: WHITE");
		
		splashPane.add(logoView, 1, 1);
		splashScene = new Scene(splashPane, 400, 300);
		return splashScene;
	}
	
	private void displaySplashScreen(Stage stg)
	{
		stg.setScene(initSplashScreen());
		stg.show();
	}
	
	private void displayStudentMainMenu(Stage stg)
	{
		StudentMainMenu smm = new StudentMainMenu();
		smm.displayStudentMainMenu(stg);
	}
	
	private void displayAdminMenu(Stage stg)
	{
		AdminMainMenu amm = new AdminMainMenu();
		amm.displayAdminMainMenu(stg);
	}
	
	private void displayProfMainMenu(Stage stg)
	{
		ProfMainMenu pmm = new ProfMainMenu();
		pmm.displayProfMainMenu(stg);
	}
	
	private void displayTAMainMenu(Stage stg)
	{
		TAMainMenu tmm = new TAMainMenu();
		tmm.displayTAMainMenu(stg);
	}
	
	public void displayMainMenu()
	{
		if(userAccount.getAccountType() == 1)
		{
			displayStudentMainMenu(stgMain);
		}
		else if(userAccount.getAccountType() == 2)
		{
			displayAdminMenu(stgMain);
		}
		else if(userAccount.getAccountType() == 3)
		{
			displayTAMainMenu(stgMain);
		}
		else if(userAccount.getAccountType() == 4)
		{
			displayProfMainMenu(stgMain);
		}
	}

	public static Stage getStage()
	{
		return stgMain;
	}
	
	public static int getUserId()
	{
		return userAccount.getAccountId();
	}


	public static DataManager getDataManager()
	{
		return dm;
	}
	
	public static Account getUserAccount()
	{
		return userAccount;
	}
	
	public static void setUser(Account user)
	{
		userAccount = user;
	}
}
