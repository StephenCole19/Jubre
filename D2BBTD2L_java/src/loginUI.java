import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class loginUI {
	private Scene scLogin;
	private Button loginButton;
	private Label usernameLabel;
	private Label passWordLabel;
	private java.awt.TextField userNameTxt;
	private java.awt.TextField passTxt;
	
	private LoginControl LoginControl;
	private LoginUI(LoginControl control) {
		this.LoginControl = control;
	}
	private Boolean loginSuccessful;
	private Account user;
	
	public loginUI(loginControl control) {
		this.loginControl = control;
	}
	
	private Scene setUpLoginScene(){

		btnLogin = new Button("Login");
		btnLogin.setOnAction(this::processLoginButtonPress);
		btnLogin.setPrefWidth(100);
		
		loginLabel = new Label("Please enter your credentials to login");
		usernameLabel = new Label("UserName");
		passWordLabel = new Label("Password");

		userNameTxt = new TextField();
		userNameTxt.setPrefSize(400, 200);
		userNameTxt.setAlignment(Pos.TOP_LEFT);
		
		passTxt = new TextField();
		passTxt.setPrefSize(400, 200);
		passTxt.setAlignment(Pos.TOP_LEFT);
		
		out = new Scene(btnLogin, usernameLabel, passWordLabel, userNameTxt, passTxt)
	}
	

	public void displayLoginForm(Stage stg) {
		stg.setScene(setUpLoginScene());
		stg.show();
		
	}


	public boolean checkCredentials() {
		String userName = userNameTxt.getText();
		String password = passTxt.getText();
		boolean confirm = loginContol.checkCredentials(userName, password);
		if (confirm == true) {
			displayLogingConfirmation();
		}
		else {
			// return failed
		}
		
		
	}

	public void displayLoginConfirmation() {
		
		
		LoginControl.getCurrentUser
		
		//Set the account here once they've logged in along with other things by calling setUserMainMenu and passing it the id of the user who just logged in
		//Thanks Logan
	}
	
	private void processLoginButtonPress(ActionEvent event)
	{
		checkCredentials();
	}
	
	public void setUserMainMenu(Account user)
	{
		MainMenu.setUser(user);
	}
	
	
}