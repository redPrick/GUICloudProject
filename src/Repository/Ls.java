package Repository;

import java.util.List;

import javax.swing.JTable;

import Command.Command;
import Excepions.MyInvalidPathException;
import Tree.Repository;

public class Ls extends Command {

	private String path = null;
	private String option = null;
	private String optionPOO = null;

	public Ls() {

	}

	public Ls(String newpath) {
		path = newpath;
	}

	public Ls(String newpath, String newoption) {
		path = newpath;
		option = newoption;
	}

	public Ls(String[] params) {
		if (params == null)
			return;
		if (params.length == 1) {
			if (params[0].equals("-r") || params[0].equals("-ar")) {
				option = params[0];
			}else if (params[0].equals("-POO")){
				optionPOO = params[0];
			}else{
				path = params[0];
			}
		} else if (params.length == 2) {
			if(params[1].equals("-POO")){
				optionPOO = params[1];
				if(params[0].charAt(0) == '-'){
					option = params[0];
				}else{
					path = params[0];
				}
			}else{
				path = params[0];
				option = params[1];
			}
		}else{
			path = params[0];
			option = params[1];
			optionPOO = params[2];
		}
	}

	public void execute() {

		if (optionPOO == null) {
			if (path == null && option == null) {
				this.executeNoParam();
			} else if (path != null && option == null) {
				this.executePath(path);
			} else if (path != null && option != null) {
					this.executePathAndOption(path);
			} else if (path == null && option != null) {
				if (option.equals("-ar")) {
					this.executeAndShowAllDates(Command.currentFolder);
				} else {
					this.executeOption();
				}
			}
		}else{
//			if (path == null && option == null) {
//				this.executeNoParamInJTable();
//			} else if (path != null && option == null) {
//				this.executePathInJTable(path);
//			} else if (path != null && option != null) {
//				this.executePathAndOptionInJTable(path);
//			} else if (path == null && option != null) {
//				if (option.equals("-ar")) {
//					this.executeAndShowAllDatesInJTable();
//				} else {
//					this.executeOptionInJTable();
//				}
//			}
			  //headers for the table
	        String[] columns = new String[] {
	            "Id", "Name", "Hourly Rate", "Part Time"
	        };
	         
	        //actual data for the table in a 2d array
	        Object[][] data = new Object[][] {
	            {1, "John", 40.0, false },
	            {2, "Rambo", 70.0, false },
	            {3, "Zorro", 60.0, true },
	        };
	 
	        //create table with data
	        JTable table = new JTable(data, columns);
	        Terminal.scrollPane.setViewportView(table);
		}
	}

	private void executeOptionInJTable() {
		// TODO Auto-generated method stub
		
	}

	private void executePathAndOptionInJTable(String path2) {
		// TODO Auto-generated method stub
		
	}

	private void executePathInJTable(String path2) {
		// TODO Auto-generated method stub
		
	}

	private void executeNoParamInJTable() {
		// TODO Auto-generated method stub
		
	}

	private void executeAndShowAllDatesInJTable() {
		// TODO Auto-generated method stub
		
	}

	public void execute(Repository child, int level) { // post order
		// traversal
		if (!this.check())
			return;
		
		
		for (Repository each : child.getChildren()) {
			for (int i = 0; i < level; i++)
				Terminal.textArea.append("\t");
			Terminal.textArea.append(each.getName() + "\n");
			execute(each, level + 1);
		}
	}

	public void executeOption() {
		Terminal.textArea.append("\n");
		execute(Command.currentFolder, 0);
	}

	public void executeAndShowAllDates(Repository node) {
		if (!this.check())
			return;

		for (Repository each : node.getChildren()) {
			String type = each.getType();
			if (type.equals("FOLDER")) {
				Terminal.textArea.append("\nD: " + each.getName() + " [" + each.getData().getCreationDate() + " " + each.getData().getLastAccesedDate()
						+ "] " + each.getDimension());
			} else {
				Terminal.textArea.append("\nF: " + each.getName() + " " + each.getDimension() + " " + each.getData().returntype() + " " + "["
						+ each.getData().getCreationDate() + " " + each.getData().getLastAccesedDate() + "] "
						+ returnPermissionString(each.getPermission()));
			}
		}

	}

	public void executePathAndOption(String string) {
		if (!this.check())
			return;

		
		Repository newNode = null;
		try {
			newNode = returnNode_fromPath(string.split("\\\\"));
			if (newNode == null) {
				throw new MyInvalidPathException(Command.currentFolder, Command.currentFolder.getName(),
						Command.currentFolder.getPermission().getUser());
			} else {
				if (option.equals("-ar")) {
					executeAndShowAllDates(newNode);
				}else{
					Terminal.textArea.append("\n");
					execute(newNode, 0);
				}
			}
		} catch (MyInvalidPathException e) {
			e.printStackTrace();
		}
	}

	public void executePath(String string) {
		if (!this.check())
			return;
		
		
		Repository newNode = null;
		try {
			newNode = returnNode_fromPath(string.split("\\\\"));
			if (newNode == null) {
				throw new MyInvalidPathException(Command.currentFolder, Command.currentFolder.getName(),
						Command.currentFolder.getPermission().getUser());
			} else {
				List<Repository> list = newNode.getChildren();
				Terminal.textArea.append("\n");
				for (Repository each : list) {
					Terminal.textArea.append(each.getName() + "\t");
				}

				//Terminal.textArea.append("\n");
			}
		} catch (MyInvalidPathException e) {
			e.printStackTrace();
		}

	}

	public void executeNoParam() {
		if (!this.check())
			return;

		Terminal.textArea.append("\n");
		List<Repository> list = currentFolder.getChildren();
		for (Repository each : list) {
			Terminal.textArea.append(each.getName() + " ");
		}

		//Terminal.textArea.append("\n");
	}

}
