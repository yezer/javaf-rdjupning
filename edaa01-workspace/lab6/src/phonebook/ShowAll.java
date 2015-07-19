package phonebook;

import javax.swing.*;

import java.awt.event.*;
import java.util.*;

@SuppressWarnings("serial")
public class ShowAll extends JMenuItem implements ActionListener {
	private PhoneBook phoneBook;
	private PhoneBookGUI gui;

	public ShowAll(PhoneBook phoneBook, PhoneBookGUI gui) {
		super("Show all");
		this.phoneBook = phoneBook;
		this.gui = gui;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Set<String> s = phoneBook.names();
		if (s.isEmpty() == true) {
			gui.setTheText("The phonebook is empty " + "\n");
		} else {
			StringBuilder phoneBookNames = new StringBuilder();
			;
			for (String string : s) {
				phoneBookNames.append("\n");
				phoneBookNames.append(string);
			}
			gui.setTheText("The phonebook has the following names "
					+ phoneBookNames + "\n");
		}
	}
}