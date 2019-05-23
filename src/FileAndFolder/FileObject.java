package FileAndFolder;

import java.util.BitSet;
import java.util.Date;
import java.util.Random;

import Permissions.Permissions;

public class FileObject extends DataObject {

	private String name = null;
	private Double dimension = null;
	private String type = null;
	private String value = null;
	private final Date date = new Date();
	private Date lastAccesedDate = null;
	private Permissions permission = null;

	public FileObject(String newName) {
		this.setValue(randomString());
		this.name = newName;
		this.dimension = 0.0;
		this.setLastAccesedDate(new Date());
		if (newName.substring(newName.length() - 4).equals(".txt")) {
			this.setType("text");
		} else {
			this.setType("binary");
		}

	}

	public FileObject(String newName, Permissions permission){
		this.setValue(randomString());
		this.name = newName;
		this.dimension = 0.0;
		this.permission = permission;
		this.setLastAccesedDate(new Date());
		if(newName.length() >= 4){
			if(newName.substring(newName.length() - 4).equals(".txt")){
				this.setType("text");
			}else{
				this.setType("binary");
			}
		}else{
			this.setType("binary");
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public Double getDimension() {
		return dimension;
	}

	public void setDimension(Double dimension) {
		this.dimension = dimension;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public static String randomString() {
		final String SourceString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rand = new Random();
		int size = 35;

		StringBuilder newStringBuilder = new StringBuilder(size);
		for (int i = 0; i < size; i++)
			newStringBuilder.append(SourceString.charAt(rand.nextInt(SourceString.length())));
		return newStringBuilder.toString();
	}

	public void setValue(String newValues) {
		value = newValues;

	}

	public Date getCreationDate() {
		return date;
	}

	public Permissions getPermission() {
		return permission;
	}

	public void setPermission(Permissions permission) {
		this.permission = permission;
	}

	public String toString() {
		return this.name;
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
		return this.type;
	}

}
