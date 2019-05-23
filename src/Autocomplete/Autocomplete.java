package Autocomplete;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Command.Command;
import Repository.Terminal;
import Tree.Repository;

public class Autocomplete {
	public static List<String> list = new ArrayList<String>();
	public static JFrame frame = null;

	public Autocomplete() {
		list.add("cat");
		list.add("cd");
		list.add("echo");
		list.add("login");
		list.add("logout");
		list.add("ls");
		list.add("mkdir");
		list.add("newuser");
		list.add("pwd");
		list.add("rm");
		list.add("sync");
		list.add("touch");
		list.add("upload");
		list.add("userinfo");// 14

	}

	public static void renewList() {
		deleteElements();
		for (Repository node : Command.currentFolder.getChildren()) {
			list.add(node.getName());
		}
	}

	public static void deleteElements() {
		if (list.size() <= 14) {
			return;
		}
		list = list.subList(0, 14);

		//		for (int i = 14; i < list.size(); i++) {
//			list.remove(i);
//		}
	}

	public static void showJList(String prefix) {

		String[] solutionList = new String[50];
		int counter = 0;
		for (int i = 0; i < list.size(); i++) {
			if (!list.get(i).equals(prefix)) {
				if (list.get(i).startsWith(prefix)) {
					solutionList[counter++] = list.get(i);
				}
			}
		}
		if (solutionList[0] != null) {
			frame = new JFrame("Autocomplete");
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
			frame.setSize(100, 200);
			frame.setBackground(Color.gray);

			JPanel panel = new JPanel();
			int index = 0;
			String[] newSolutionListNoNullElements = new String[getElementCount(solutionList)];
			while (solutionList[index] != null) {
				newSolutionListNoNullElements[index] = solutionList[index++];
			}
			JList<String> listbox = new JList<String>(newSolutionListNoNullElements);
			listbox.setLayoutOrientation(JList.HORIZONTAL_WRAP);
			listbox.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						String parameter = listbox.getSelectedValue();
						Terminal.textArea.append(parameter.substring(prefix.length()));
						if (list.subList(0, 13).contains(parameter)) {
							Terminal.textArea.append(" ");
						} else {
							Repository tempFolder = returnNodeFromPathInCurrentFolder(parameter);
							addItemsToList(tempFolder, parameter);
						}

						// frame.setVisible(false);
						frame.dispose();
					}
				}
			});
			JScrollPane scrollPane = new JScrollPane(listbox);
			// scrollPane.setVerticalScrollBar(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			panel.add(scrollPane, BorderLayout.CENTER);
			frame.add(panel);

			frame.setVisible(true);
		}
	}

	public static void addItemsToList(Repository tempCurrentFolder, String path) {
		//renewList();

		for (Iterator<Repository> iter = tempCurrentFolder.getChildren().iterator(); iter.hasNext();) {
			list.add(path.concat("\\\\" + iter.next().getName()));
		}
	}

	public static Repository returnNodeFromPathInCurrentFolder(String path) {

		Repository tempCurrentFolder = Command.currentFolder;
		String[] folders = path.split("\\\\");
		for (int counter = 0; counter < folders.length; counter++) {
			if (!folders[counter].equals("")) {
				for (Iterator<Repository> iter = tempCurrentFolder.getChildren().iterator(); iter.hasNext();) {
					Repository node = iter.next();
					if (node.getName().equals(folders[counter])) {
						tempCurrentFolder = node;
						break;
					} else if (iter.next() == null) {
						return null;
					}
				}
			}
		}

		return tempCurrentFolder;

	}

	public static int getElementCount(String[] myStringArray) {
		int counter = 0;
		while (myStringArray[counter++] != null);
		
		return counter - 1;
	}

}
