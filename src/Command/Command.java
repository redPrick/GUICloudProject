package Command;

import java.awt.Color;
import java.awt.Font;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import FileAndFolder.FolderObject;
import Permissions.Permissions;
import Tree.Repository;
import Users.User;

public class Command{

	public static Command INSTANCE = null;
	public static Repository currentFolder = null;
	public static User currentUser = null;
	public static Repository rootFolder = null;

	
	public void execute(){
		
	}
	
	public void accept(Command command){
		command.execute();
	}
	
	public static Command getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Command("guest");

		return INSTANCE;
	}
	
	public Command(){
		
	}
	public Command(String guest) {
//		while(true){
//			Scanner reader = new Scanner(System.in);  // Reading from System.in
//			System.out.println("Username : ");
//			String username = reader.nextLine();
//			
//			Login login = new Login(username);
//			login.execute();
//			if(currentUser.getUsername().equals(username)){
//				break;
//			}else{
//				System.out.println("login as guest?(y/n");
//				if(reader.next().charAt(0) == 'y'){
//					this.setCurrentUser(new User("guest"));
//					break;
//				}
//			}
//			
//		}
		

		
		this.setCurrentUser(new User(guest));
		FolderObject Folderobj = new FolderObject("root",new Permissions( true, true, new User("ALL")));
		Repository root = new Repository(Folderobj , "FOLDER");
		Command.rootFolder = root;
		this.setCurrentFolder(root);
	}

	public void setCurrentFolder(Repository node) {
		Command.currentFolder = node;
	}

	public void setCurrentUser(User node) {
		Command.currentUser = node;
	}

	public Repository getCurrentFolder() {
		return Command.currentFolder;
	}

	public static User getCurrentUser() {
		return currentUser;
	}

	

	

//	public static Node returnNode(Node node, String[] string, int counter, int size) { // call
//																						// it
//																						// with
//																						// string.split("\\");
//		if (counter == size) {
//			return node;
//		}
//		Node aux = null;
//		for (Iterator<Node> iter = node.getChildren().iterator(); iter.hasNext();) {
//			aux = iter.next();
//			// if(counter == size && aux.getName().equals(string[counter])){
//			// return aux;
//			// }
//			if (aux.getName().equals(string[counter])) {
//				break;
//			}
//		}
//		return returnNode(aux, string, counter + 1, size);
//	}

	public static Repository returnNode_fromPath(String[] path) {
		if (path == null)
			return null;
		Repository aux = null;
		if (path[0].equals("root")) {
			aux = rootFolder;
		} else {
			aux = currentFolder;
		}
		for (String folder : path) {
			if (folder == null)
				break;
			if (folder.equals("..")) {
				if (aux == rootFolder) {
					return null;
				} else {
					aux = aux.getParent();
				}
			} else if (!folder.equals("root")) {
				for (Iterator<Repository> iter = aux.getChildren().iterator(); iter.hasNext();) {
					Repository temp = iter.next();
					if (temp.getName().equals(folder)) {
						aux = temp;
						break;
					}
				}

			}
		}

		return aux;
	}

	
	public static Permissions interpretPermission(String string){
		boolean read = false, write = false;
		
		for(int i = 1; i < string.length() - 1; i ++){
			if(string.charAt(i) == 'r'){
				read = true;
			}else if(string.charAt(i) == 'w'){
				write = true;
			}
		}
		return new Permissions(read, write, getCurrentUser());
		
	}

	
	public boolean check() {
		String currentuserUsername = new String(getCurrentUser().getUsername());

		if (currentuserUsername.equals("guest")){
			return false;
		}else if(currentFolder.getPermission().getUser().getUsername().contains("ALL")){
			return true;
		}else if (currentuserUsername.equals(currentFolder.getData().getPermission().getUser().getUsername())) {
			return true;
		} else if (currentuserUsername.equals("root")) {
			return true;
		}

		return false;

	}

	public static StringBuffer encrypt(String password) {
		StringBuffer ecryptedPass = new StringBuffer("");
		for (int counter = 0; counter < password.length(); counter++) {
			ecryptedPass.append((char) ((int) password.charAt(counter) + (counter + 1) - 30 / (counter + 1)));
		}
		return ecryptedPass;
	}

	public static String decrypt(String password) {
		StringBuffer decryptedPass = new StringBuffer("");
		for (int counter = 0; counter < password.length(); counter++) {
			decryptedPass.append((char) (password.charAt(counter) - (counter + 1) + 30 / (counter + 1)));
		}
		return decryptedPass.toString();
	}
	
	public static String returnPermissionString(Permissions permission){
		if(permission.getReadPermission() && permission.getWritePermission()){
			return "-rw-";
		}else if(permission.getReadPermission()){
			return "-r";
		}else if(permission.getWritePermission()){
			return "-w";
		}
		return "--";
		
	}


}
