package Repository;

import javax.swing.JFrame;

import Command.Command;
import SwingContent.MyJDialogClass;

public class Echo extends Command{

	private String message = null;
	private String optionPOO = null;
	
	public Echo(String[] newmessage){
		message = newmessage[0];
		if(newmessage.length > 1 && newmessage[1].equals("-POO"))
			optionPOO = newmessage[1];
	}
	
	public void execute() {
		if(optionPOO == null)
			showInTermianl();
		else
			showInJDialog();
	}
	public void showInTermianl(){
		Terminal.textArea.append("\n" + message);
	}
	public void showInJDialog(){
		new MyJDialogClass(new JFrame(), "echo command", message);
	}
}
