package Repository;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Command.Command;

public class Newuser extends Command{

	static JFrame frame = null;
	public void execute() {
//		if (!this.check())
//			return ;

		
		frame = new JFrame("Login");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		FlowLayout layout = new FlowLayout();
		//layout.setAlignment(FlowLayout.CENTER);
		frame.setLayout(layout);
		frame.setMinimumSize(new Dimension(320, 375));
		//frame.setSize(400, 400);
		//frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	
		placeComponents();

		frame.setVisible(true);

	}
	private static void placeComponents() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(320, 375));
		
		JLabel userLabel = new JLabel("Username");
		userLabel.setBounds(15, 10, 80, 25);
		panel.add(userLabel);

		JTextField userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		panel.add(userText);
		
		JLabel firstnameLabel = new JLabel("Firstname");
		firstnameLabel.setBounds(15, 60, 80, 25);
		panel.add(firstnameLabel);

		JTextField firstnameText = new JTextField(20);
		firstnameText.setBounds(100, 60, 160, 25);
		panel.add(firstnameText);
		
		JLabel secondnameLabel = new JLabel("Secondname");
		secondnameLabel.setBounds(15, 110, 80, 25);
		panel.add(secondnameLabel);

		JTextField secondnameText = new JTextField(20);
		secondnameText.setBounds(100, 110, 160, 25);
		panel.add(secondnameText);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(15, 160, 80, 25);
		panel.add(passwordLabel);

		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 160, 160, 25);
		panel.add(passwordText);
		
		
		JLabel ReTypepasswordLabel = new JLabel("Retype Password");
		ReTypepasswordLabel.setBounds(15, 210, 80, 25);
		panel.add(ReTypepasswordLabel);

		JPasswordField ReTypepasswordText = new JPasswordField(20);
		ReTypepasswordText.setBounds(100, 210, 160, 25);
		panel.add(ReTypepasswordText);

		JButton loginButton = new JButton("login");
		loginButton.setBounds(15, 260, 80, 25);
		loginButton.addActionListener(new ActionListener() {
        	 
            public void actionPerformed(ActionEvent e) {
        		String username = userText.getText();
        		String firstname = firstnameText.getText();
        		String secondname = secondnameText.getText();
            	String password = String.valueOf(passwordText.getPassword());
            	String ReTypePassword = String.valueOf(ReTypepasswordText.getPassword());
            	
            	
            	if(!password.equals(ReTypePassword)){
            		JOptionPane.showMessageDialog(frame,
                            "Password do not match\n",
                            "Login",
                            JOptionPane.ERROR_MESSAGE);
            	}else{
            	
            	signUp(username, firstname, secondname, password);
                    JOptionPane.showMessageDialog(frame, 
                            "You have successfully siggned up.",
                            "Login",
                            JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                }
            }
        });
		
		panel.add(loginButton);
		
		JButton cancelButton = new JButton("cancel");
		cancelButton.setBounds(185, 260, 80, 25);
		cancelButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		       frame.dispose();
		    }
		});
		panel.add(cancelButton);
		frame.add(panel);
	}
	public static void signUp(String username, String firstname, String secondname, String password){
		
		try {
			String filename = System.getProperty("user.dir") + "\\UsersDataBase.txt";
			FileWriter fw = new FileWriter(filename, true); 

			fw.write("Username : " + username);
			fw.write("\nFirstname : " + firstname);
			fw.write("\nSecondname : " + secondname);
			fw.write("\nPassword : " + encrypt(password));
			fw.write("\nCreation Date : " + new Date());
			//fw.write("\nLastAcces Date : ");
			fw.write("\n------\n");
			fw.close();
			
		} catch (IOException ioe) {
			System.err.println("IOException: " + ioe.getMessage());
		}
	}
	
}
