package Repository;

import Autocomplete.Autocomplete;
import Command.Command;
import FileAndFolder.FolderObject;
import Permissions.Permissions;
import Tree.Repository;

public class Mkdir extends Command{

	private String[] list = null;
	

	public Mkdir(String[] newList){
		list = newList;
	}

	public void execute() {
		if (!this.check())
			return;
		
		
		String name = "";
		String path = "";
		
		if(list.length == 1){
			FolderObject folderObj = new FolderObject(list[0], new Permissions(true, true, Command.getCurrentUser()));
			Repository node = new Repository(folderObj, this.getCurrentFolder(), "FOLDER");
			Autocomplete.list.add(list[0]);
		}else if(list.length == 2){
			
			if(list[1].charAt(0) != '-'){
				String[] pathh = (list[0]).split("\\\\");
				Repository parent = returnNode_fromPath(pathh);
				FolderObject folderObj = new FolderObject(list[1], new Permissions(true, true, Command.getCurrentUser()));
				Repository node = new Repository(folderObj, parent, "FOLDER");
				
				
				
			}else{
				Permissions permission = interpretPermission(list[1]);
				FolderObject folderObj = new FolderObject(list[0], permission);
				
				Repository node = new Repository(folderObj, this.getCurrentFolder(), "FOLDER");
				Autocomplete.list.add(list[0]);
				
			}
	
		}else{
			Repository parent = returnNode_fromPath(((String)list[0]).split("\\\\"));
			Permissions permission = interpretPermission(list[2]);
			FolderObject folderObj = new FolderObject(list[1], permission);
			Repository node = new Repository(folderObj, parent, "FOLDER");
			Autocomplete.list.add(list[1]);
			
		}

	}
}
