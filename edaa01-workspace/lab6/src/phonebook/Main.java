package phonebook;

import java.util.HashSet;
import java.util.Set;

public class Main {
	public static void main(String[] args) {	
		PhoneBook pb = new PhoneBook();
		new PhoneBookGUI(pb);
	}
}
