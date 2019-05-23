package FileAndFolder;

import java.util.Date;

import Permissions.Permissions;

public abstract class DataObject {
	
	public abstract void setName(String name);
	
	public abstract char getFileType();
	
	public abstract String getName();
	
	public abstract Double getDimension();
	
	public abstract void setDimension(Double dimension) ;

	public abstract Permissions getPermission();
	
	public abstract void setPermission(Permissions permission);
	
	public abstract String getValue();
	
	public abstract Date getCreationDate();

	public abstract Date getLastAccesedDate();
	
	public abstract void setLastAccesedDate(Date lastAccesedDate);
	
	public abstract String returntype();
}
