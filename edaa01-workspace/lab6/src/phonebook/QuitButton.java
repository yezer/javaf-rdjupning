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
		if (saveOrNot == JOptionPane.YES_OPTION) {
			String filename = JOptionPane.showInputDialog("Enter filename to save to");
			phoneBook.saveToFile(filename);
		}
		System.exit(0);
	}
}
