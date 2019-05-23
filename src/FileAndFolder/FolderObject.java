package FileAndFolder;

import java.util.BitSet;
import java.util.Date;

//import java.util.ArrayList;
//import java.util.List;

import Permissions.Permissions;

public class FolderObject extends DataObject{
	
	//private List<DataObject> constainer = new ArrayList<DataObject>();
	private String name = null;
	private Double dimension = null;
	private Permissions permission = null;
	private final Date date = new Date();
	private Date lastAccesedDate = null;
	
	
	public FolderObject(String newName){
		this.name = newName;
		this.setLastAccesedDate(new Date());
	}
	public FolderObject(String newName, Permissions permission){
		this.name = newName;
		this.dimension = 0.0;
		this.permission = permission;
		this.setLastAccesedDate(new Date());
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getDimension() {
		return dimension;
	}

	public void setDimension(Double dimension) {
		this.dimension = dimension;
	}

	public Permissions getPermission() {
		return permission;
	}

	public void setPermission(Permissions permission) {
		this.permission = permission;
	}

	public Date getCreationDate() {
		return date;
	}

	public String getValue() {
		return null;
	}
	public Date getLastAccesedDate() {
		return lastAccesedDate;
	}
	public void setLastAccesedDate(Date lastAccesedDate) {
		this.lastAccesedDate = lastAccesedDate;
	}
	public char getFileType() {
		return 'F';
	}
	public String returntype() {
		return null;
		
	}
	
	


}
