package Permissions;

import Users.User;

public class Permissions {
	private boolean read;
	private boolean write;
	private User user;

	public Permissions() {
		this.read = false;
		this.write = false;
		this.user = null;
	}
	public Permissions(boolean read, boolean write, User newUser) {
		this.read = read;
		this.write = write;
		this.user = newUser;
	}
	public Permissions(boolean read, boolean write) {
		this.read = read;
		this.write = write;
	}
	
	public boolean getReadPermission(){
		return this.read;
	}
	public boolean getWritePermission(){
		return this.write;
	}
	public User getUser(){
		return this.user;
	}
	public void setReadPermission(boolean read){
		this.read = read;
	}
	public void setWritePermission(boolean write){
		this.read = write;
	}
	public void setUser(User newUser){
		this.user = newUser;
	}
	
}
