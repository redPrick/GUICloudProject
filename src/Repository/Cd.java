package Repository;

import Command.Command;
import Excepions.MyInvalidPathException;
import Tree.Repository;

public class Cd extends Command {

	private String path = null;

	public Cd(String newpath) {
		path = newpath;
	}

	public void execute() {
		if (!this.check())
			return;

		int count = 0;
		String[] newSplitString = new String[20];
		// Node auxNode = this.getCurrentFolder();
		// System.out.println(string);
		try {
			if (path.equals("root")) {
				this.setCurrentFolder(Command.rootFolder);
				return;
			}
			for (String splitString : path.split("\\\\")) { // repara aici
				newSplitString[count++] = splitString;
			}
			// Node newNode = returnNode(this.rootFolder, newSplitString, 1,
			// count);
			Repository newNode = returnNode_fromPath(newSplitString);
			if (newNode == null) {
				throw new MyInvalidPathException(Command.currentFolder, newNode.getName(),
						newNode.getPermission().getUser());
			} else {
				this.setCurrentFolder(newNode);
				Terminal.autocomplete.renewList();
			}
		} catch (MyInvalidPathException e) {
			e.printStackTrace();
		}
	}

}
