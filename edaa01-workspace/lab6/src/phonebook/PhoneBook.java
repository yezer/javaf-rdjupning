package phonebook;

import java.text.Collator;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class PhoneBook implements Serializable {
	private Map<String, LinkedList<String>> phoneBook;

	public PhoneBook() {
		phoneBook = new HashMap();

	}

	/**
	 * Associates the specified number with the specified name in this phone
	 * book. post: If the specified name is not present in this phone book, the
	 * specified name is added and associated with the specified number.
	 * Otherwise the specified number is added to the set of number associated
	 * with name.
	 * 
	 * @param name
	 *            The name for which a phone number is to be added
	 * @param number
	 *            The number associated with the specified name
	 * @return true if the specified name and number was inserted
	 */
	public boolean put(String name, String number) {
		boolean theNameAlreadyExists = true;
		// the name is not present in the phone book
		if (findNumber(name) == null) {
			theNameAlreadyExists = false;
			LinkedList<String> list = new LinkedList<String>();
			list.add(number);
			phoneBook.put(name, list);
			// the name is present so the number is added
		} else {
			LinkedList<String> list = new LinkedList<String>(findNumber(name));
			list.add(number);
			phoneBook.put(name, list);
		}
		return theNameAlreadyExists;
	}

	/**
	 * Removes the the specified name from this phone book. post: If the
	 * specified name is present in this phone book, it is removed. Otherwise
	 * this phone book is unchanged.
	 * 
	 * @param name
	 *            The name to be removed
	 * @return true if the specified name was present
	 */
	public boolean remove(String name) {
		return phoneBook.remove(name) != null;
	}

	/**
	 * Retrieves a list of phone numbers for the specified name. If the
	 * specified name is not present in this phone book an empty list is
	 * returned.
	 * 
	 * @param name
	 *            The name whose associated phone numbers are to be returned
	 * @return The phone numbers associated with the specified name
	 */
	public List<String> findNumber(String name) {
		return phoneBook.get(name);
	}

	/**
	 * Retrieves a list of names associated with the specified phone number. If
	 * the specified number is not present in this phone book an empty list is
	 * returned.
	 * 
	 * @param number
	 *            The number for which the set of associated names is to be
	 *            returned.
	 * @return The list of names associated with the specified number
	 */
	public List<String> findNames(String number) {
		List<String> listOfNames = new LinkedList<String>();
		Iterator it = phoneBook.entrySet().iterator();
		// Iterate through the names in the phone book
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			String name = (String) pairs.getKey(); // The name of this
													// person in the phone
													// book
			LinkedList<String> numbers = (LinkedList<String>) pairs.getValue(); // The
																				// list
																				// of
																				// this
																				// person's
																				// phone
																				// numbers

			for (String phoneNum : numbers) { // Go through all the numbers
												// the person
				if (phoneNum.equals(number)) { // This person owns this number
												// why doesn't == work here?
					listOfNames.add(name); // Add to the list
				}
			}
			// it.remove(); // should avoid a ConcurrentModificationException
		}
		return listOfNames;
	}

	/**
	 * Retrieves the set of all names present in this phone book. The set's
	 * iterator will return the names in ascending order
	 * 
	 * @return The set of all names present in this phone book
	 */
	public Set<String> names() { 
		Set<String> setOfNames = new HashSet<String>(phoneBook.keySet());  //have to do a new HashSet or else the values from phoneBook are changed when setOfNames are changed
		// vad händer om någon har skrivit in en siffra som sitt namn, eller
		// öåa?
		Set<String> setSortedNames = new LinkedHashSet<String>();
		Collator myCollator = Collator.getInstance(new Locale("sv", "SE"));
		while (setOfNames.isEmpty() == false) {
			Iterator<String> it = setOfNames.iterator();
			String theFirstLetter = it.next();
			while (it.hasNext()) {
				String b = it.next();
				if (myCollator.compare(theFirstLetter, b) > 0) {
					theFirstLetter = b;
				}
			}
			setSortedNames.add(theFirstLetter);
			setOfNames.remove(theFirstLetter);
		}
		return setSortedNames;
	}

	/**
	 * Returns true if this phone book is empty
	 * 
	 * @return true if this phone book is empty
	 */
	public boolean isEmpty() {
		return phoneBook.isEmpty();
	}

	/**
	 * Returns the number of names in this phone book
	 * 
	 * @return The number of names in this phone book
	 */
	public int size() {
		return phoneBook.size();
	}

	public void saveToFile(String fileName) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(fileName));
			out.writeObject(phoneBook);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void readFromFile(String fileName) {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					fileName));
			phoneBook = (Map<String, LinkedList<String>>) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
