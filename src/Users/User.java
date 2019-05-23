package Users;

import java.util.Date;

public class User {
	private String username = null;
	private String password = null;
	private String firstname = null;
	private String secondname = null;
	private String creationDateString = null;
	private Date creationDate = new Date();
	private String lastlongString = null;
	private Date lastLog = new Date();
	
	
	public User(String username, String firstname, String secondname, String password){
		this.username = username;
		this.firstname = firstname;
		this.secondname = secondname;
		this.password = password;
		
		setLastlongString(lastLog.toString());
		setCreationDateString(creationDate.toString());
	}
	
	public User(String username, String firstname, String secondname){
		this.username = username;
		this.firstname = firstname;
		this.secondname = secondname;
		
		setLastlongString(lastLog.toString());
		setCreationDateString(creationDate.toString());
	}
	public User(String username){
		this.username = username;
		
		setLastlongString(lastLog.toString());
		setCreationDateString(creationDate.toString());
	}
	
	public String getUsername() {
		if (username == null)
			return "";
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		if( password == null)
			return "";
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		if (firstname == null)
			return "";
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getSecondname() {
		if (secondname == null)
			return "";
		return secondname;
	}
	public void setSecondname(String secondname) {
		this.secondname = secondname;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date date){
		
	}
	public void setLastLog(Date log){
		this.lastLog = log;
	}

	public String getCreationDateString(){
		return creationDateString;
	}
	
	public void setCreationDateString(String date){
		this.creationDateString = date;
	}
	
	public String getLastlongString(){
		return lastlongString;
	}
	
	public void setLastlongString(String date){
		this.lastlongString = date;
	}
	
	
	
}
