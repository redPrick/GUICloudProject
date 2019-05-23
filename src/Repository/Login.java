package Repository;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import Command.Command;
import Tree.Repository;
import Users.User;

public class Login extends Command {

	static private JFrame myFrame = null;

	public void execute() {

		myFrame = new JFrame("Login");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		myFrame.setLocation(dim.width / 2 - myFrame.getSize().width / 2, dim.height / 2 - myFrame.getSize().height / 2);
		myFrame.setMinimumSize(new Dimension(300, 150));
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		placeComponents();
		myFrame.pack();
		myFrame.setVisible(true);

	}

	private static void placeComponents() {
		JPanel panel = new JPanel();
		// panel.setFocusable(true);
		// panel.requestFocusInWindow();

		panel.setLayout(null);

		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);

		JTextField userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		panel.add(userText);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);

		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 40, 160, 25);

		panel.add(passwordText);

		JButton loginButton = new JButton("login");
		loginButton.setBounds(10, 80, 80, 25);

//		panel.addKeyListener(new KeyAdapter() { // not working
//			public void keyPressed(KeyEvent e) {
//				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//					String checkUsername = userText.getText();
//					String checkPassword = String.valueOf(passwordText.getPassword());
//					if (Login.authenticate(checkUsername, checkPassword)) {
//						JOptionPane.showMessageDialog(myFrame,
//								"Hi " + checkUsername + "! You have successfully logged in.", "Login",
//								JOptionPane.INFORMATION_MESSAGE);
//						// Terminal.thread.resume();
//						myFrame.dispose();
//
//					} else {
//						JOptionPane.showMessageDialog(myFrame, "Invalid username or password", "Login",
//								JOptionPane.ERROR_MESSAGE);
//						// reset username and password
//
//					}
//				}
//			}
//		});
		loginButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String checkUsername = userText.getText();
				String checkPassword = String.valueOf(passwordText.getPassword());
				if (Login.authenticate(checkUsername, checkPassword)) {
					JOptionPane.showMessageDialog(myFrame, "Hi " + checkUsername + "! You have successfully logged in.",
							"Login", JOptionPane.INFORMATION_MESSAGE);
					// Terminal.thread.resume();
					myFrame.dispose();

				} else {
					JOptionPane.showMessageDialog(myFrame, "Invalid username or password", "Login",
							JOptionPane.ERROR_MESSAGE);
					// reset username and password

				}
			}
		});
		loginButton.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), " login ");
		loginButton.getActionMap().put(" login ", new AbstractAction() {

			public void actionPerformed(ActionEvent e) {

				String checkUsername = userText.getText();
				String checkPassword = String.valueOf(passwordText.getPassword());
				if (Login.authenticate(checkUsername, checkPassword)) {
					JOptionPane.showMessageDialog(myFrame, "Hi " + checkUsername + "! You have successfully logged in.",
							"Login", JOptionPane.INFORMATION_MESSAGE);
					// Terminal.thread.resume();
					myFrame.dispose();

				} else {
					JOptionPane.showMessageDialog(myFrame, "Invalid username or password", "Login",
							JOptionPane.ERROR_MESSAGE);
					// reset username and password

				}
			}
		});
		panel.add(loginButton);

		JButton cancelButton = new JButton("cancel");
		cancelButton.setBounds(185, 80, 80, 25);

		cancelButton.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), " cancel ");
		cancelButton.getActionMap().put(" cancel ", new AbstractAction() {

			public void actionPerformed(ActionEvent e) {
				myFrame.dispose();
			}
		});
		// panel.addKeyListener(new KeyAdapter() { //not working
		// public void keyPressed(KeyEvent e) {
		// if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
		// myFrame.dispose();
		// }
		// }
		// });
		panel.add(cancelButton);
		myFrame.add(panel);

	}

	public static boolean authenticate(String username, String password) {
		boolean bCheck = false;
		BufferedReader br = null;
		int check = 0;
		try {
			String firstname = new String("");
			String secondname = new String("");
			String date = "";
			// String sCurrentLine = new String("");
			String decryptedPass = new String("");
			br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\UsersDataBase.txt"));
			// while ((sCurrentLine = br.readLine()) != null) {
			for (String sCurrentLine = br.readLine(); sCurrentLine != null; sCurrentLine = br.readLine()) { // what
				if (username.equals(sCurrentLine.split(" ")[2])) {
					check = 1;
					while (!(sCurrentLine = br.readLine()).equals("-----")) {

						if (sCurrentLine.split(" ")[0].equals("Firstname")) {
							firstname = sCurrentLine.split(" ")[2];
						} else if (sCurrentLine.split(" ")[0].equals("Secondname")) {
							secondname = sCurrentLine.split(" ")[2];
						} else if (sCurrentLine.split(" ")[0].equals("Password")) {
							decryptedPass = decrypt(sCurrentLine.split(" ")[2]);
							// } else if (sCurrentLine.split("
							// ")[0].equals("Creation Date ")) {
						} else {
							date = sCurrentLine.substring(16);
							break;
						}
					}
					if (password.equals(decryptedPass)) {
						bCheck = true;
						User newUser = new User(username, firstname, secondname, password);
						newUser.setCreationDateString(date);
						currentUser = newUser;
						br.close();
						break;
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
					return bCheck;
				}

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return false;
	}
	// public void run() {
	//
	//
	// try {
	// Terminal.thread.sleep(10000);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

}
