package phonebook;

import javax.swing.*;

import java.awt.event.*;

@SuppressWarnings("serial")
public class AddMenu extends JMenuItem implements ActionListener {
	private PhoneBook phoneBook;
	private PhoneBookGUI gui;

	public AddMenu(PhoneBook phoneBook, PhoneBookGUI gui) {
		super("Add");
		this.phoneBook = phoneBook;
		this.gui = gui;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		String s = JOptionPane.showInputDialog("Enter name");
		if (s == null) {
			return;
		}
		if (s.isEmpty() == true) {
			gui.setTheText("No name was entered. Process canceled." + "\n");
			return;
		}

		String t = JOptionPane.showInputDialog("Enter phone number");
		if (t == null) {
			return;
		}
		if (t.isEmpty() == true) {
			gui.setTheText("No number  was entered. Process canceled." + "\n");
			return;
		}

		boolean didTheNameAlreadyExist = phoneBook.put(s, t);
		if (didTheNameAlreadyExist == true) {
			gui.setTheText("The phone number "
					+ t
					+ " was successfully added to the list of numbers for the already existing name "
					+ s + "\n");
		} else {
			gui.setTheText("The name " + s + " and phone number " + t
					+ " were successfully added" + "\n");
		}
	}
}
