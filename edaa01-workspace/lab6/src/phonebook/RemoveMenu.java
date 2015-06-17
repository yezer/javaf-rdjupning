package phonebook;

import javax.swing.*;
import java.awt.event.*;

public class RemoveMenu extends JMenuItem implements ActionListener {
	private PhoneBook phoneBook;
	private PhoneBookGUI gui;

	public RemoveMenu(PhoneBook phoneBook, PhoneBookGUI gui) {
		super("Remove");
		this.phoneBook = phoneBook;
		this.gui = gui;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		String s = JOptionPane.showInputDialog("Enter name to remove");
		if (s == null ) {
			return;
		}
		if ( s.isEmpty() == true) {
			gui.setTheText("No name was entered."+ "\n");
			return;
		}
		boolean wasThereSuchANameToRemove = phoneBook.remove(s);
		if (wasThereSuchANameToRemove == false) {
			gui.setTheText("The phonebook does not contain the name " + s + ". No name was removed" + "\n");
		} else {
			gui.setTheText("The name " + s + " was successfully removed" + "\n");
		}
	}
}