import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AdminMainMenu 
{
	private Scene scMain;
	private GridPane mainPane;
	private Label lblWelcome;
	private Label onlyForFormatting;
	private Image mainMenuLogo;
	private ImageView mainMenuLogoView;
	private Image mainMenuIcon;
	private ImageView mainMenuIconView;
	private Button btnQuit;
	private Button btnCreateLoginAccount;
	private Button btnAddCourseOfferingInfo;
	private Button btnAddStudentCourseRegistrationInfo;
	private Button btnLogout;
	
	private void initMainMenuComponents()
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
		
		lblWelcome = new Label("Welcome " + MainMenu.getUserAccount().getFullNameInformal());
		lblWelcome.setPrefSize(489, 50);
		lblWelcome.setStyle("-fx-background-color: WHITE");
		lblWelcome.setFont(Font.font ("Verdana", 20));
		lblWelcome.setAlignment(Pos.CENTER);
		
		onlyForFormatting = new Label("");
		onlyForFormatting.setPrefWidth(80);
		
		btnAddStudentCourseRegistrationInfo = new Button("Register Student");
		btnAddStudentCourseRegistrationInfo.setOnAction(this::processAddStudentButtonPress);
		btnAddStudentCourseRegistrationInfo.setPrefWidth(150);
		
		btnAddCourseOfferingInfo = new Button("New Course");
		btnAddCourseOfferingInfo.setOnAction(this::processAddCourseButtonPress);
		btnAddCourseOfferingInfo.setPrefWidth(100);
		
		btnLogout = new Button("Logout");
		btnLogout.setOnAction(this::processLogoutButtonPress);
		btnLogout.setPrefWidth(70);
		
		btnCreateLoginAccount = new Button("New Account");
		btnCreateLoginAccount.setOnAction(this::processCreateAccountButtonPress);
		btnCreateLoginAccount.setPrefWidth(120);
		
		btnQuit = new Button("Quit");
		btnQuit.setOnAction(this::processExitButtonPress);
		btnQuit.setPrefWidth(70);
		
		mainPane = new GridPane();
		mainPane.setHgap(20);
		mainPane.setVgap(20);
		mainPane.setAlignment(Pos.CENTER);
		mainPane.setStyle("-fx-background-color: WHITE");
		mainPane.setGridLinesVisible(true);
	}
	
	private Scene initMainMenu()
	{
		initMainMenuComponents();

		mainPane.add(btnLogout, 7, 0);
		mainPane.add(btnAddCourseOfferingInfo, 2, 3);
		mainPane.add(btnAddStudentCourseRegistrationInfo, 3, 3);
		mainPane.add(btnCreateLoginAccount, 4, 3);
		mainPane.add(btnQuit, 7, 3);
		mainPane.add(lblWelcome, 2, 1, 5, 1);
		mainPane.add(mainMenuIconView, 1, 0);
		mainPane.add(mainMenuLogoView, 2, 7, 5, 5);
		
		scMain = new Scene(mainPane, 900, 600);
		return scMain;
	}
	
	public void displayAdminMainMenu(Stage stg)
	{
		stg.setScene(initMainMenu());
		stg.show();
	}
	
	public void processCreateAccountButtonPress(ActionEvent event)
	{
		createLoginAccountUI clau = new createLoginAccountUI();
		clau.displayCreateLoginAccount(MainMenu.getStage());
	}
	
	public void resetToMainMenu()
	{
		displayAdminMainMenu(MainMenu.getStage());
	}
	
	public void processAddCourseButtonPress(ActionEvent event)
	{
		//display addCourseOfferingInfo
	}
	
	public void processAddStudentButtonPress(ActionEvent event)
	{
		//display addStudentCourseRegistrationInfo
	}
	
	public void processLogoutButtonPress(ActionEvent event)
	{
		//display login
		//MainMenu.setUser(null);
	}
	
	public void processExitButtonPress(ActionEvent event)
	{
		MainMenu.getStage().close();
	}
}
