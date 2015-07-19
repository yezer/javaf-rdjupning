package phonebook;

import javax.swing.*;

import java.awt.*;
import java.util.*;

public class PhoneBookGUI extends JFrame {
	private PhoneBook phoneBook;
	private JTextArea messageArea;

	public PhoneBookGUI(PhoneBook pb) {
		super("PhoneBook");
		phoneBook = pb;
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		Locale.setDefault(new Locale("en"));
		/* To avoid hardcoded Swedish text on OptionPane dialogs */
		UIManager.put("OptionPane.cancelButtonText", "Cancel");
		setLayout(new BorderLayout());
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		JMenu editMenu = new JMenu("Edit");
		menubar.add(editMenu);
		editMenu.add(new AddMenu(phoneBook, this));
		editMenu.add(new RemoveMenu(phoneBook, this));
		JMenu addFind = new JMenu("Find");
		menubar.add(addFind);
		addFind.add(new FindNumbers(phoneBook, this));
		addFind.add(new FindNames(phoneBook, this));
		JMenu addView = new JMenu("View");
		menubar.add(addView);
		addView.add(new ShowAll(phoneBook, this));

		JPanel southPanel = new JPanel();
		messageArea = new JTextArea(25, 75);
		messageArea.setEditable(false);
		southPanel.add(new JScrollPane(messageArea));
		southPanel.add(new QuitButton(phoneBook, this));
		add(southPanel, BorderLayout.CENTER);

		pack();
		setVisible(true);
		int readOrNot = JOptionPane.showConfirmDialog(null,
				"Do you want to read a phonebook?",
				"Do you want to read a phonebook?", JOptionPane.YES_NO_OPTION);
		if (readOrNot == JOptionPane.YES_OPTION) {
			String filename = JOptionPane
					.showInputDialog("Enter filename to read from");
			if (filename != null && filename.isEmpty() != true) {
			pb.readFromFile(filename);
			}
		}
	}

	public void setTheText(String string) {
		messageArea.append(string);
	}
}
