package Repository;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import Command.Command;

public class Userinfo extends Command {

	private String username = null;
	private String optionPOO = null;

	public Userinfo(String[] newList) {
		if (newList.length == 1) {
			if(newList[0].equals("-POO")){
				optionPOO = newList[0];
			}else{
				username = newList[0];
			}
				
		} else if (newList.length == 2) {
			username = newList[0];
			if (newList[1].equals("-POO")) {
				optionPOO = newList[1];
			}
		}

	}

	public void execute() {

		if (optionPOO == null)
			showInTermianl();
		else
			showInJList();
	}

	public void showInTermianl() {

		if (username == null) {
			if (currentUser.getUsername().equals("guest")) {
				Terminal.textArea.append("\nUsername : guest");
				return;
			}
			Terminal.textArea.append("\nUsername : " + currentUser.getUsername() + "\nFirstname : "
					+ currentUser.getFirstname() + "\nSurname : " + currentUser.getSecondname() + "\nCreation date : "
					+ currentUser.getCreationDateString() + "\nLast acces date : " + new Date()
					+ "\nPassword : HOW ABOUT NO?");

			return;
		}

		BufferedReader br = null;

		try {
			Terminal.textArea.append("\n");
			String sCurrentLine = new String("");

			br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\UsersDataBase.txt"));
			int check = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				if (!sCurrentLine.equals("------") && username.equals(sCurrentLine.split(" ")[2])) {

					while (!(sCurrentLine = br.readLine()).equals("------")) {
						if (check == 0) {
							Terminal.textArea.append("Username : " + username + "\n");
							Terminal.textArea.append(sCurrentLine + "\n");
							check = 1;
						} else {
							if (!sCurrentLine.split(" ")[0].equals("Password"))
								Terminal.textArea.append(sCurrentLine + "\n");
						}
						// sCurrentLine = br.readLine();
					}
				}
				if (check == 1) {
					break;
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void showInJList() {

		JFrame frame = new JFrame("User INFORMATION");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setSize(400, 200);
		frame.setBackground(Color.gray);

		JPanel panel = new JPanel();
		//panel.setLayout(new BorderLayout());
		JButton button = new JButton("OK");
		panel.add(button);
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// frame.setVisible(false);
				frame.dispose();
			}

		});
		frame.add(panel);

		String[] list = new String[7];
		int counter = 0;
		if (username == null) {
			if (currentUser.getUsername().equals("guest")) {
				list[counter] = ("Username : guest\n");
			} else {

				list[counter++] = ("Username : " + currentUser.getUsername());
				list[counter++] = ("Firstname : " + currentUser.getFirstname());
				list[counter++] = "Surname : " + currentUser.getSecondname();
				list[counter++] = ("Creation date : " + currentUser.getCreationDate());
				list[counter++] = ("Last acces date : " + new Date());
				list[counter++] = "Password : HOW ABOUT NO?";

			}
		} else {

			BufferedReader br = null;

			try {

				String sCurrentLine = new String("");

				br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\UsersDataBase.txt"));
				int check = 0;
				while ((sCurrentLine = br.readLine()) != null) {
					if (!sCurrentLine.equals("------") && username.equals(sCurrentLine.split(" ")[2])) {

						while (!(sCurrentLine = br.readLine()).equals("------")) {
							if (check == 0) {
								list[counter++] = ("Username : " + username + "\n");
								list[counter++] = (sCurrentLine + "\n");
								check = 1;
							} else {
								if (!sCurrentLine.split(" ")[0].equals("Password"))
									list[counter++] = (sCurrentLine + "\n");
							}
							// sCurrentLine = br.readLine();
						}
					}
					if (check == 1) {
						break;
					}

				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null)
						br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

		JList<String> listbox = new JList<String>(list);
		panel.add(listbox, BorderLayout.CENTER);

		frame.setVisible(true);

	}

}
