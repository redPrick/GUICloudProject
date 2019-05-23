package Repository;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Command.Command;
import Excepions.MyInvalidPathException;
import Tree.Repository;

public class Cat extends Command {

	private String path = null;
	private String optionPOO = null;

	public Cat(String[] newpath) {
		path = newpath[0];
		if (newpath.length > 1 && newpath[1].equals("-POO"))
			optionPOO = newpath[1];
	}

	public void execute() {
		if (!this.check())
			return;

		Repository node = returnNode_fromPath(path.split("\\\\"));

		try {
			if (node == null) {
				throw new MyInvalidPathException(this.currentFolder, node.getName(), node.getPermission().getUser());
			} else {
				if (optionPOO == null)
					Terminal.textArea.append("\n" + node.getData().getValue().toString());
				else
					showInJTextArea(node);
			}
		} catch (MyInvalidPathException e) {
			e.printStackTrace();
		}
	}


	public void showInJTextArea(Repository node) {
		JFrame frame = new JFrame();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		JTextArea textArea = new JTextArea(node.getData().getValue());
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		JPanel panel = new JPanel(new FlowLayout());
		
		panel.add(scrollPane);
		
		Font font = new Font("Verdana", Font.BOLD, 12);
		textArea.setFont(font);
		JButton button = new JButton("OK");
		panel.add(button);
		button.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				 //frame.setVisible(false); 
				 frame.dispose(); 
			}
			
		});
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}
