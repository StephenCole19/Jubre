import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * This class displays a main menu for the admin user
 * @author Brennan Couturier, 3638808
 *
 */
public class ProfMainMenu 
{
	private Scene scMain;
	private GridPane mainPane;
	private Label lblWelcome;
	private Image mainMenuLogo;
	private ImageView mainMenuLogoView;
	private Image mainMenuIcon;
	private ImageView mainMenuIconView;
	
	private Button btnLogout;
	private Button btnQuit;
	private Button btnViewMessages;
	private Button btnAssignments;
	private Button btnNotifications;
	private Button btnPostNotification;
	private Button btnPostAssignment;
	
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
		
		lblWelcome = new Label("Welcome Professor " + MainMenu.getUserAccount().getLastName());
		lblWelcome.setPrefSize(489, 50);
		lblWelcome.setStyle("-fx-background-color: WHITE");
		lblWelcome.setFont(Font.font ("Verdana", 20));
		lblWelcome.setAlignment(Pos.CENTER);
		
		btnQuit = new Button("Quit");
		btnQuit.setOnAction(this::processQuitButtonPress);
		btnQuit.setPrefWidth(70);
		
		btnPostAssignment = new Button("New Assignment");
		btnPostAssignment.setOnAction(this::processNewAssignmentButtonPress);
		btnPostAssignment.setPrefWidth(150);
		
		btnLogout = new Button("Logout");
		btnLogout.setOnAction(this::processLogoutButtonPress);
		btnLogout.setPrefWidth(70);
		
		btnViewMessages = new Button("Messages");
		btnViewMessages.setOnAction(this::processViewMessageButtonPress);
		btnViewMessages.setPrefWidth(100);
		
		btnAssignments = new Button("Assignments");
		btnAssignments.setOnAction(this::processAssignmentsButtonPress);
		btnAssignments.setPrefWidth(110);
		
		btnPostNotification = new Button("New Notification");
		btnPostNotification.setOnAction(this::processPostNotificationButtonPress);
		btnPostNotification.setPrefWidth(130);
		
		btnNotifications = new Button("Notifications");
		btnNotifications.setOnAction(this::processNotificationsButtonPress);
		btnNotifications.setPrefWidth(100);

		mainPane = new GridPane();
		mainPane.setHgap(20);
		mainPane.setVgap(20);
		mainPane.setAlignment(Pos.CENTER);
		mainPane.setStyle("-fx-background-color: WHITE");
		//mainPane.setGridLinesVisible(true);
	}
	
	private Scene initMainMenu()
	{
		initMainMenuComponents();

		//Col, row
		mainPane.add(btnPostAssignment, 5, 3);
		mainPane.add(btnLogout, 7, 0);
		mainPane.add(btnQuit, 7, 3);
		mainPane.add(btnViewMessages, 1, 3);
		mainPane.add(btnPostNotification, 3, 3);
		mainPane.add(btnNotifications, 2, 3);
		mainPane.add(btnAssignments, 4, 3);
		mainPane.add(lblWelcome, 2, 1, 5, 1);
		mainPane.add(mainMenuIconView, 1, 0);
		mainPane.add(mainMenuLogoView, 2, 7, 5, 5);

		scMain = new Scene(mainPane, 900, 600);
		return scMain;
	}
	
	public void displayProfMainMenu(Stage stg)
	{
		stg.setScene(initMainMenu());
		stg.show();
	}
	
	public void resetToMainMenu()
	{
		displayProfMainMenu(MainMenu.getStage());
	}
	
	private void processQuitButtonPress(ActionEvent event)
	{
		MainMenu.getStage().close();
	}
	
	private void processViewMessageButtonPress(ActionEvent event)
	{
		viewMessagesUI vmu = new viewMessagesUI();
		vmu.displayViewMessages(MainMenu.getStage());
	}
	
	private void processNotificationsButtonPress(ActionEvent event)
	{
		//display viewNotifications
	}
	
	private void processPostNotificationButtonPress(ActionEvent event)
	{
		
	}
	
	private void processAssignmentsButtonPress(ActionEvent event)
	{
		//display assignments
	}
	
	private void processNewAssignmentButtonPress(ActionEvent event)
	{
		PostAssignmentUI pau = new PostAssignmentUI();
		pau.displayPostAssignmentForm(MainMenu.getStage());
	}
	
	private void processLogoutButtonPress(ActionEvent event)
	{
		MainMenu.processLogout();
	}
}
