package map;


	import java.util.Arrays;

	public class SimpleHashMapTwo<K, V> {
	  Entry<K, V>[][] table;
	  final double loadFactor;

	  public SimpleHashMapTwo() {
	    this(31);
	  }

	  public SimpleHashMapTwo(int size, double loadFactor) {
	    table = new Entry[size][];
	    this.loadFactor = loadFactor;
	  }

	  public SimpleHashMapTwo(int size) {
	    this(size, 0.75);
	  }

	  static class Entry<K, V> {
	    K k;
	    V v;

	    public Entry(K k, V v) {
	      this.k = k;
	      this.v = v;
	    }

	    public K getK() {
	      return k;
	    }

	    public void setK(K k) {
	      this.k = k;
	    }

	    public V getV() {
	      return v;
	    }

	    public void setV(V v) {
	      this.v = v;
	    }
	  }

	  V get(K k) {
	    int bucket = k.hashCode() % table.length;
	    if (table[bucket] != null) {
	      for (Entry<K, V> entry : table[bucket]) {
	        if (entry.getK().equals(k)) {
	          return entry.getV();
	        }
	      }
	    }
	    return null;
	  }


	  V internalPut(K k, V v, Entry[][] table) {
	    int bucket = k.hashCode() % table.length;
	    V oldV = null;
	    if (table[bucket] == null) {
	      table[bucket] = new Entry[1];
	      table[bucket][0] = new Entry(k, v);
	    } else {
	      for (Entry<K, V> entry : table[bucket]) {
	        if (entry.getK().equals(k)) {
	          // we have a match, replace v
	          oldV = entry.getV();
	          entry.setV(v);
	        }
	      }
	      if (oldV == null) {
	        // didn't have a match, add to the array and add Entry
	        Entry[] newBucket = Arrays.copyOf(table[bucket], table[bucket].length + 1);
	        newBucket[table[bucket].length] = new Entry(k, v);
	        table[bucket] = newBucket;
	      }
	      return oldV;
	    }

	    return null;
	  }

	  V put(K k, V v) {
	    V value = internalPut(k, v, table);

	    // calculate load factor, because.
	    int occupied = 0;
	    for (int i = 0; i < table.length; i++) {
	      if (table[i] != null) {
	        occupied += 1;
	      }
	    }
	    if ((occupied / loadFactor) > table.length) {
	      // need to rehash, because um.
	      Entry[][] newTable = new Entry[table.length * 5 / 3][];
	      for (int i = 0; i < table.length; i++) {
	        if (table[i] != null) {
	          for (Entry<K, V> e : table[i]) {
	            internalPut(e.getK(), e.getV(), newTable);
	          }
	        }
	      }
	      table = newTable;
	    }
	    return value;
	  }

	  public static void main(String[] args) {
	    SimpleHashMapTwo<Integer, Integer> map = new SimpleHashMapTwo<>();
	    if (map.put(1, 1) != null) {
	      throw new RuntimeException("map put failed");
	    }
	    if (map.get(1) != 1) {
	      throw new RuntimeException("map get failed");
	    }
	    if (map.put(1, 2) != 1) {
	      throw new RuntimeException("map put failed");
	    }

	    if (map.get(1) != 2) {
	      throw new RuntimeException("map get failed");
	    }

	    map.put(32, 32);
	    if (map.get(32) != 32) {
	      throw new RuntimeException("map get failed");
	    }

	    for (int i = 0; i < 100; i++) {
	      map.put(i, 100 + i);
	    }

	    for (int i = 0; i < 100; i++) {
	      System.out.println(map.get(i));
	      if (map.get(i) != 100 + i) {
	        throw new RuntimeException("map failed on " + i);
	      }
	    }
	  }
	}

