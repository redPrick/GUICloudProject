package Repository;

import Command.Command;
import Excepions.MyPathTooLongException;
import Tree.Repository;

public class Pwd extends Command{

	public void execute() {
		if (!this.check())
			return;

		String arrayString[] = new String[20];

		int counter = 0, totalSize = 0;
		Repository node = currentFolder;
		try {
			while (node != null) {

				arrayString[counter] = node.getData().getName();
				totalSize += arrayString[counter++].length();

				node = node.getParent();
			}
			if (totalSize > 255) {
				throw new MyPathTooLongException();
			} else {
				Terminal.textArea.append("\n");
				for (int j = counter - 1; j >= 0; j--) {
					Terminal.textArea.append(arrayString[j] + "\\");
				}
				//Terminal.textArea.append("\n");

			}
		} catch (MyPathTooLongException e) {
			e.printStackTrace();
		}
	}
	
}
