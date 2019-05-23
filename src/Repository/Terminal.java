package Repository;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Autocomplete.Autocomplete;
import Command.Command;

public class Terminal implements Runnable {

	public static Terminal INSTANCE = null;
	public static JFrame frame = new JFrame("Terminal");
	public static JPanel panel = new JPanel();
	public static JTextArea textArea = new JTextArea(300, 300);
	public static JScrollPane scrollPane = new JScrollPane(textArea);

	public static String lastline = "";
	public static String RememberLastLine = "";
	public static Thread thread;
	public Command command = Command.getInstance();
	public static Autocomplete autocomplete = new Autocomplete();

	public Terminal() {
		thread = new Thread(this);
		thread.start();
	}

	public static Terminal getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Terminal();

		return INSTANCE;
	}

	public void run() {
		textArea.setBackground(Color.black);
		textArea.setForeground(Color.green);
		Font font = new Font("Verdana", Font.BOLD, 12);

		textArea.setFont(font);
		textArea.addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
					// Autocomplete.renewList();

					setLastLine();
					if (!lastline.equals("")) {
						String[] lastLineParams = lastline.split(" ");
						Autocomplete.showJList(lastLineParams[lastLineParams.length - 1]);
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (!textArea.getText().equals("")) {
						setLastLine();
						if (lastline.equals("exit")) {
							frame.dispose();
						}
						executeCommand(command, Terminal.lastline.split(" "));

					}
				}
				if (e.getKeyCode() == KeyEvent.VK_F1) {
					if (!textArea.getText().equals("")) {
						textArea.append(RememberLastLine);
					}
				}
			}

			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
			}
		});
		// panel.setLayout(new FlowLayout());
		// panel.add(scrollPane);
		frame.add(scrollPane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

	public void setLastLine() {
		String[] lines = Terminal.textArea.getText().split("\\n");
		int counter = Terminal.textArea.getLineCount();
		if (counter != lines.length) {
			RememberLastLine = lastline;
			lastline = "";
		} else {
			lastline = lines[counter - 1];
			RememberLastLine = lastline;
		}
	}

	public static void executeCommand(Command command, String[] params) {

		String[] subArray = new String[4];
		if (params.length > 1)
			subArray = Arrays.copyOfRange(params, 1, params.length);
		// else
		// subArray[0] = null;
		// Command com = Class.newInstance();

		// Command.textArea.append("workstation@" +
		// Command.getCurrentUser().getUsername() + "~/");
		// new Pwd().execute();
		// Command.textArea.append("$ ");
		switch (params[0]) {
		case "cat":
			if (subArray[0] != null)
				command.accept(new Cat(subArray));
			else
				Terminal.textArea.append("\ntarget not found\n");
			break;
		case "cd":
			if (subArray[0] != null)
				command.accept(new Cd(subArray[0]));
			else
				Terminal.textArea.append("\ntarget not found");
			break;
		case "echo":
			if (subArray[0] != null)
				command.accept(new Echo(subArray));
			else
				Terminal.textArea.append("\n");
			break;
		case "login":
			command.accept(new Login());
			break;
		case "logout":
			command.accept(new Logout());
			break;
		case "ls":
			command.accept(new Ls(subArray));
			break;
		case "mkdir":
			if (subArray[0] != null)
				command.accept(new Mkdir(subArray));
			else
				Terminal.textArea.append("\ntarget not found");
			break;
		case "newuser":
			command.accept(new Newuser());
			break;
		case "pwd":
			command.accept(new Pwd());
			break;
		case "rm":
			if (subArray[0] != null)
				command.accept(new Rm(subArray[0]));
			else
				Terminal.textArea.append("\ntarget not found");
			break;
		case "sync":
			command.accept(new Sync());
			break;
		case "touch":
			if (subArray[0] != null)
				command.accept(new Touch(subArray));
			else
				Terminal.textArea.append("\ntarget not found");
			break;
		case "upload":
			command.accept(new Upload());
			break;
		case "userinfo":
			command.accept(new Userinfo(subArray));
			break;
		case "clear":
			Terminal.textArea.setText("");
			break;

		default:
			Terminal.textArea.append("\nCommand not found");
			break;
		}

		Autocomplete.renewList();

	}

}
