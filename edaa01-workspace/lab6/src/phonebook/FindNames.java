package phonebook;
import javax.swing.*;

import java.awt.event.*;
import java.util.*;

@SuppressWarnings("serial")
public class FindNames extends JMenuItem implements ActionListener {
	private PhoneBook phoneBook;
	private PhoneBookGUI gui;
	
	public FindNames(PhoneBook phoneBook, PhoneBookGUI gui) {
		super("Find name(s)");
		this.phoneBook = phoneBook;
		this.gui = gui;
		addActionListener(this);
	}
	
	 public void actionPerformed(ActionEvent e) {
		 String s = JOptionPane.showInputDialog("Enter number");
			if (s == null ) {
				return;
			}
			if ( s.isEmpty() == true) {
				gui.setTheText("No number was entered."+ "\n");
				return;
			}
		 List<String> t = phoneBook.findNames(s);	 
			if (t.isEmpty() == true) {
				gui.setTheText("No names are associated with the phone number " + s+ "\n");
			} else {
				 StringBuilder names = new StringBuilder();;
					for (String name : t) {
						names.append(name + " ");		
						}
		 gui.setTheText("The number " +  s  +  "  has the following names: " + names + "\n");
	 }
}
}