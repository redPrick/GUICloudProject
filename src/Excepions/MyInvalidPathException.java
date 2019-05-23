package Excepions;

import java.util.Date;


import FileAndFolder.DataObject;
import FileAndFolder.FolderObject;
import Tree.Repository;
import Users.User;

public class MyInvalidPathException extends Exception{
	
	String pathFolder;// = new FolderObject(); // aloci in constructor
	Repository currentFolder; // = new FolderObjet(); in consturctor
	User user;// = new User(); in constructor
	Date date;
	
	
	public MyInvalidPathException( Repository current, String path , User newUser){
		super("Path doesn't exist");
		this.pathFolder = path;
		this.currentFolder = current;
		this.user = newUser;
		this.date = new Date();
		
		
	}
	public MyInvalidPathException(String message) {
		super(message);
	}
	public String getPathFolder() {
		return pathFolder;
	}
	public void setPathFolder(String pathFolder) {
		this.pathFolder = pathFolder;
	}
	public Repository getCurrentFolder() {
		return currentFolder;
	}
	public void setCurrentFolder(Repository currentFolder) {
		this.currentFolder = currentFolder;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
