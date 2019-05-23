package Excepions;

import java.util.Date;

import Users.User;

public class MyNotEnoughSpaceException extends Exception{

	private Double size;
	private User user;
	private Date date;
	
	MyNotEnoughSpaceException(Double size, User newUser) {
		super("not enough space");
		this.date = new Date();
		this.size = size;
		this.user = newUser;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
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
