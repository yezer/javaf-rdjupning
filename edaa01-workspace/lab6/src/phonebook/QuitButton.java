package phonebook;

import javax.swing.*;

import java.awt.event.*;

public class QuitButton extends JButton implements ActionListener {
	private PhoneBook phoneBook;
	private PhoneBookGUI gui;

	public QuitButton(PhoneBook phoneBook, PhoneBookGUI gui) {
		super("Quit");
		this.phoneBook = phoneBook;
		this.gui = gui;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		
		int saveOrNot = JOptionPane.showConfirmDialog(null,
				"Do you want to save this phonebook?",
				"Do you want to save this phonebook?",
				JOptionPane.YES_NO_OPTION);
		//gui.setTheText("The file was not saved");
		if (saveOrNot == JOptionPane.YES_OPTION) {
			String filename = JOptionPane
					.showInputDialog("Enter filename to save to");
			if (filename != null && filename.isEmpty() != true) {	
			phoneBook.saveToFile(filename);
			System.exit(0);
			} else {
				gui.setTheText("The file was not saved " + "\n");
			}
		}
 if (saveOrNot == JOptionPane.NO_OPTION) {
	 System.exit(0);
 }
	
	//	try {
		
			//gui.setTheText("The file was not saved");
		//	Thread.sleep(2000);
			//gui.setTheText("The file was not saved");
	//	} catch (InterruptedException e1) {
			//gui.setTheText("The file was not saved");
			// TODO Auto-generated catch block
	//		e1.printStackTrace();
	//	}
	//	gui.setTheText("The file was not saved");
	//	System.exit(0);
	}
}

