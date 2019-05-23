package Repository;

import Autocomplete.Autocomplete;
import Command.Command;
import FileAndFolder.FileObject;
import Permissions.Permissions;
import Tree.Repository;

public class Touch extends Command{
	
	private String[] list = null;
	static Double result = 0.0;
	
	public Touch(String[] newList){
		list = newList;
	}

	public void execute() {
		if (!this.check())
			return;

		
		if(list.length == 1){
			FileObject fileObj = new FileObject(list[0], new Permissions(true, true, Command.getCurrentUser()));
			Repository node = new Repository(fileObj, this.getCurrentFolder(), "FILE");
			Terminal.autocomplete.list.add(list[0]);
		}else if(list.length == 2){
			if(checkDouble(list[1])){
				FileObject fileObj = new FileObject(list[0], new Permissions(true, true, Command.getCurrentUser()));
				fileObj.setDimension(result);
				Repository node = new Repository(fileObj, this.getCurrentFolder() , "FILE");
				Autocomplete.list.add(list[0]);
			}else{
				if((list[1]).charAt(0) == '-'){
					Permissions permission = interpretPermission(list[1]);
					FileObject fileObj = new FileObject(list[0], permission);
					Repository node = new Repository(fileObj, this.getCurrentFolder(), "FILE");
					Autocomplete.list.add(list[0]);
					
				}else{ // al doilea e numele
					Repository parent = returnNode_fromPath((list[0]).split("\\\\"));
					FileObject fileObj = new FileObject(list[1], new Permissions(true, true, Command.getCurrentUser()));
					Repository node = new Repository(fileObj, parent, "FILE");
					
					
				}
			}
	
		}else if(list.length == 3){
			if(checkDouble(list[1])){
				//Repository parent = returnNode_fromPath((list[0]).split("\\\\"));
				Permissions permission = interpretPermission(list[2]);
				FileObject fileObj = new FileObject(list[0], permission);
				fileObj.setDimension(result);
				Repository child = new Repository(fileObj, Command.currentFolder, "FILE");
				Autocomplete.list.add(list[0]);
				return;
			}
			if(checkDouble(list[2])){
				Repository parent = returnNode_fromPath((list[0]).split("\\\\"));
				FileObject fileObj = new FileObject(list[1], new Permissions(true, true, Command.getCurrentUser()));
				fileObj.setDimension(result);
				Repository child = new Repository(fileObj, parent, "FILE");
			}else{
				Repository parent = returnNode_fromPath((list[0]).split("\\\\"));
				Permissions permission = interpretPermission(list[2]);
				FileObject fileObj = new FileObject(list[1], permission);
				Repository child = new Repository(fileObj, parent, "FILE");
			}
		}
		else{
			Repository parent = returnNode_fromPath((list[0]).split("\\\\"));
			Permissions permission = interpretPermission(list[3]);
			FileObject fileObj = new FileObject(list[1],permission);
			checkDouble(list[2]);
			fileObj.setDimension(result);
			Repository child = new Repository(fileObj, parent, "FILE");
		}
	}
	
	public static boolean checkDouble(String param){
		if(param == null)
			return false;
		
		for(int i = 0; i < param.length(); i ++){
			if(Character.isDigit(param.charAt(i))){
				result = result * 10 + (double)(param.charAt(i) - '0');
			}else{
				result = 0.0;
				return false;
			}
		}
		return true;
	}
	
}
