
public class loginControl {
	
	private Account CurrentUser;
	
	private DataManager dm;
	
	public LoginControl(DataManager datamanager) {
		dm = datamanagerm;
	}  
	
	public loginControl()
	{
		dm = MainMenu.getDataManager();
	}
	
	public boolean checkCredentials(String userName,String passWord) {
		Account account = dm.Account(userName, passWord);
		if (account == null) {
			return false;
		}
		
		saveCurrentUser(account);
		return true;
	}
	
	public void saveCurrentUser(Account current) {
		CurrentUser = current;
	}
	
	public Account returnCurrentUser() {
		return CurrentUser;
	}
}