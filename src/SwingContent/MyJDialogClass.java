package SwingContent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyJDialogClass extends JDialog{

	public MyJDialogClass(JFrame frame, String title, String message) {
	    super(new JFrame(), title, true);
	    if (frame != null) {
	      Dimension franeSize = frame.getSize(); 
	      Point p = frame.getLocation(); 
	      setLocation(p.x + franeSize.width / 4, p.y + franeSize.height / 4);
	    }
	    
	    
	    JPanel messagePane = new JPanel();
	    messagePane.add(new JLabel(message));
	    add(messagePane);
	    
	    
	    JPanel buttonPane = new JPanel();
	    JButton button = new JButton("OK"); 
	    button.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				 setVisible(false); 
				 dispose(); 
			}
	    	
	    });
	    buttonPane.add(button); 
	    
	    add(buttonPane, BorderLayout.SOUTH);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    pack(); 
	    setVisible(true);
	  }
}
