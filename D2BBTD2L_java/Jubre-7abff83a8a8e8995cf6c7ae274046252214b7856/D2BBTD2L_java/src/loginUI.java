import javafx.stage.Stage;

public class loginUI 
{
	private Boolean loginSuccessful;
	
	private Account user;

	public void displayLoginForm(Stage stg) 
	{
		
	}


	public void enterCredentials() 
	{
		
	}

	public void displayLoginConfirmation() 
	{
		//Set the account here once they've logged in along with other things by calling setUserMainMenu and passing it the id of the user who just logged in
		//Thanks Logan
	}
	
	public void setUserMainMenu(Account user)
	{
		MainMenu.setUser(user);
	}
	
	
}