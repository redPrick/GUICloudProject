package Repository;

import Command.Command;
import Users.User;

public class Logout extends Command{

	public void execute() {

		this.setCurrentUser(new User("guest"));

	}
	
}
