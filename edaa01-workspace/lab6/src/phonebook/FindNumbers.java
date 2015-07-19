package phonebook;

import javax.swing.*;

import java.util.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class FindNumbers extends JMenuItem implements ActionListener {
	private PhoneBook phoneBook;
	private PhoneBookGUI gui;

	public FindNumbers(PhoneBook phoneBook, PhoneBookGUI gui) {
		super("Find number(s)");
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
			gui.setTheText("No name was entered." + "\n");
			return;
		}
		List<String> t = phoneBook.findNumber(s);
		if (t == null) {
			gui.setTheText("No phone numbers were found for " + s + "\n");
		} else {
			StringBuilder numbers = new StringBuilder();
			;
			for (String number : t) {
				numbers.append(number + " ");
			}
			gui.setTheText(s + " has the following numbers: " + numbers + "\n");
		}
	}
}