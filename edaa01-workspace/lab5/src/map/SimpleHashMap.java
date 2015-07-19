package map;

import java.util.Arrays;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private Entry<K, V>[] table;
	private int capacity;

	int size;

	/**
	 * Constructs an empty hashmap with the default initial capacity (16) and
	 * the default load factor (0.75),
	 */
	public SimpleHashMap() {
		capacity = 16;
		size = 0;
		table = (Entry<K, V>[]) new Entry[16];
	}

	/**
	 * Constructs an empty hashmap with the specified initial capacity and the
	 * default load factor (0.75),
	 */
	public SimpleHashMap(int capacity) {
		this.capacity = capacity;
		size = 0;
		table = (Entry<K, V>[]) new Entry[capacity];
	}

	@Override
	public V get(Object arg0) {
		K key = (K) arg0;
		int index = index(key);
		if (find(index, key) != null) {
			Entry<K, V> entry = find(index, key);
			V value = entry.getValue();
			return value;
		}
		System.out.println(show());
		return null;
	}

	@Override
	public String toString() {
		StringBuilder completestring = new StringBuilder();
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				String string = i + "                   "
						+ table[i].getKey().toString() + " = "
						+ table[i].getValue().toString();
				completestring.append(string);
				completestring.append("\n");
				addStringList(table[i].next, completestring, i);
			}
		}
		return completestring.toString();
	}

	private StringBuilder addStringList(Entry<K, V> entry,
			StringBuilder completestring, int i) {
		if (entry == null) {
		} else {
			String string = i + "                   "
					+ entry.getKey().toString() + " = "
					+ entry.getValue().toString();
			completestring.append(string);
			completestring.append("\n");
			if (entry.next != null) {
				completestring = addStringList(entry.next, completestring, i);
			}
		}
		return completestring;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public V put(K arg0, V arg1) {
		V old;
		int index = index(arg0);
		// checks for duplicates of key
		if (find(index, arg0) != null) {
			old = find(index, arg0).getValue();
			find(index, arg0).setValue(arg1);
			return old;
		}
		size++;
		// går att göra en while-loop med temp = table[index] ist
		// checks if first slot in list with index given by key is empty
		if (table[index] == null) {

			if (size / capacity > 0.75) {
				rehash();
			}
			index = index(arg0);
			table[index] = new Entry<K, V>(arg0, arg1);

			return null;
		}
		// adds key further down the list if the first slot wasn't empty
		addListEntry(arg0, arg1, table[index]);

		return null;
	}

	private V addListEntry(K arg0, V arg1, Entry<K, V> tableindex) {
		V previous = null;
		if (tableindex.next == null) {
			previous = tableindex.getValue();
			tableindex.next = new Entry<K, V>(arg0, arg1);
		} else {
			addListEntry(arg0, arg1, tableindex.next);
		}
		return previous;
	}

	private void rehash() {
		capacity = 2 * capacity;
		Entry<K, V>[] newtable = (Entry<K, V>[]) new Entry[capacity];
		for (int i = 0; i < table.length; i++) {
			while (table[i] != null) {
				int newindex = table[i].getKey().hashCode() % newtable.length;
				if (newindex < 0) {
					newindex = newindex + newtable.length;
				}
				if (newtable[newindex] == null) {
					newtable[newindex] = new Entry<K, V>(table[i].getKey(),
							table[i].getValue());
				} else {
					addListEntry(table[i].getKey(), table[i].getValue(),
							newtable[newindex]);
				}
				table[i] = table[i].next; // can ruin table since it won't be
				// used it anymore after this
			}
		}
		table = newtable;
	}

	@Override
	public V remove(Object arg0) {
		K key = (K) arg0;
		int index = index(key);
		// case 1: list is empty
		if (table[index] == null) {
			return null;
		}
		// case 2: key is not in the list
		if (find(index, key) == null) {
			return null;
		}
		// case 3: key is in the first element in the list
		if (find(index, key) != null && table[index].getKey().equals(key)) {
			V removedvalue = table[index].getValue();
			table[index] = table[index].next;
			size--;
			return removedvalue;
		}
		// case 4: key is later in list
		if (table[index].next != null) {
			Entry<K, V> newfirstentry = new Entry(table[index].getKey(),
					table[index].getValue());
			V old = helpRemove(index, key, table[index].next, newfirstentry);
			table[index] = newfirstentry;
			size--;
			return old;
		}
		return null;
	}

	private V helpRemove(int index, K key, Entry<K, V> entry,
			Entry<K, V> newfirstentry) {
		if (entry.getKey().equals(key)) {
			V removedvalue = entry.getValue();
			addTail(index, key, entry, newfirstentry);
			return removedvalue;
		} else {
			newfirstentry.next = new Entry(entry.getKey(), entry.getValue());
			if (entry.next != null) {
				return helpRemove(index, key, entry.next, newfirstentry.next);
			}
		}
		return null;
	}

	private void addTail(int index, K key, Entry<K, V> entry,
			Entry<K, V> newfirstentry) {
		if (entry.next != null) {
			newfirstentry.next = new Entry(entry.next.getKey(),
					entry.next.getValue());
			if (entry.next.next != null) {
				addTail(index, key, entry.next, newfirstentry.next);
			}
		}
	}

	@Override
	public int size() {
		return size;
	}

	public String show() {
		return toString();
	}

	/** help method 1. Returnerar det index en nyckel ska ligga på. */
	private int index(K key) {
		int index = key.hashCode() % table.length;
		if (index < 0) {
			index = index + table.length;
		}
		return index;
	}

	/**
	 * Private help method 2 * Returnerar det Entry-par som har nyckeln key i
	 * listan som finns på position index i tabellen. Om det inte finns något
	 * sådant ska metoden returnera null
	 */
	private Entry<K, V> find(int index, K key) {
		if (table[index] != null) {
			if (table[index].getKey().equals(key)) {
				return table[index];
			}
			return helperFinder(index, key, table[index].next);
		}
		return null;
	}

	private Entry<K, V> helperFinder(int index, K key, Entry<K, V> entry) {
		Entry<K, V> returnentry = null;
		if (entry != null) {
			if (entry.getKey().equals(key)) {
				returnentry = entry;
			} else {
				returnentry = helperFinder(index, key, entry.next);
			}
		}
		return returnentry;
	}

	/** Nestled class Entry */
	public static class Entry<K, V> implements Map.Entry<K, V> {
		private K key;
		private V value;
		private Entry<K, V> next;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			this.value = value;
			return value;
		}
	}

}
