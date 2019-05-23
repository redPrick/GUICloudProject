package Interfaces;


import FileAndFolder.FileObject;
import FileAndFolder.FolderObject;
import Tree.Repository;
import Users.User;

public interface Commands {

	public void setCurrentFolder(Repository node);
	public void setCurrentUser(User user);
	
	public void mkdir(String... list);//
	
	public void touch(Object... list);
	
	public void rm(String... list); //
	
	public void ls(Repository child, String option, int level);//
	public void ls(String string);
	public void ls(String string, String option);
	public void ls();//
	public void cd(String path); //
	public void cat(FileObject node);//
	public void pwd();//
	public void upload();
	
	public void echo();
	public User newuser();//
	public void userinfo(String user);//
	public void logout();
	public void login(String username);
	
}
