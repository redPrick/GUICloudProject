package Repository;

import Command.Command;
import Excepions.MyInvalidPathException;
import Tree.Repository;

public class Rm extends Command{

	private String list = null;
	
	public Rm(String newList){
		list = newList;
	}
	
	public void execute(){
		if(!this.check())
			return;
		
		
		Repository node = null;
		
		node = returnNode_fromPath(list.split("\\\\"));

//			for(Iterator iter = this.currentFolder.getChildren().iterator(); iter.hasNext();){
//				if(iter.next().equals(list[0])){
//					node = (Repository) iter;
//					break;
//				}
//			}
//		}
		try{
			if(node == null){
				Terminal.textArea.append("Fail\n");
				throw new MyInvalidPathException(this.currentFolder, node.getName(), node.getPermission().getUser());
			}else{
				//currentFolder.removeChild(node);
				Double dim = node.getParent().getDimension();
				Double node_dim = node.getDimension();
				node.getParent().setDimension(dim - node_dim);
				
				node.getParent().removeChild(node);
				Terminal.textArea.append("Succes\n");

			}
		} catch(MyInvalidPathException e){
			e.printStackTrace();
		}
		
	}

	
}
