package map;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Random;

public class Help {

	public static void main(String[] args) {
		/**
		 * int modulus = 17%5; int mod = 7%100;
		 * 
		 * int key = 432; int value =329;
		 * 
		 * boolean ismapempty = map.isEmpty(); System.out.println("IsMapEmpty: "
		 * + ismapempty); Object oldvalue = map.put(key, value); Object test1 =
		 * map.get(432);
		 * System.out.println("Hämtat värdet för första objektet: " + test1);
		 * 
		 * int key2 = 65; int value2 =9; Object oldvalue2 = map.put(key2,
		 * value2); System.out.println("Gamla värdet nyckel 65 : " + oldvalue2);
		 * 
		 * //Jag får ju inte ha dubletter! int key3 = 65; int value3 =98; Object
		 * oldvalue3 = map.put(key3, value3);
		 * System.out.println("Gamla värdet nyckel 65 : " + oldvalue3);
		 * 
		 * 
		 * 
		 * String mymap = map.show(); System.out.println(mymap); int
		 * howmanyentries = map.size(); System.out.println("This many entries: "
		 * + howmanyentries); boolean ismapemptynow = map.isEmpty();
		 * System.out.println("IsMapEmpty: " + ismapemptynow);
		 * 
		 * // Object object = new Object(); // Object MyValue = map.get(object);
		 * // Integer ninetyeight = new Integer(98); Object test = map.get(65);
		 * System.out.println(test); Object test3 = map.get(432);
		 * System.out.println(test3); // System.out.println(MyValue);
		 */

		SimpleHashMap<Integer, Integer> map = new SimpleHashMap<Integer, Integer>(
				9);
		System.out.println(map.get(1));

		map.put(1, 1);
		map.put(2, 2);
		map.put(21, 21);
		map.put(22, 22);
		System.out.println(map.get(21));
		System.out.println(map.get(1));
		// System.out.println(map.get(24));

		map.put(10, 10);
		System.out.println(map.get(10));
		map.put(11, 11);
		map.put(12, 12);
		map.put(13, 13);
		map.put(14, 14);
		System.out.println(map.get(14));
		map.put(15, 15);
		map.put(16, 16);
		map.put(17, 17);
		map.put(18, 18);

		map.put(19, 19);
		System.out.println(map.get(19));
		map.put(20, 20);

		map.put(23, 23);
		map.put(24, 24);
		map.put(25, 25);
		System.out.println(map.get(25));

		map.put(26, 26);
		System.out.println(map.get(26));
		map.put(27, 27);
		System.out.println(map.get(27));
		map.put(1, 1);
		System.out.println(map.get(1));
		map.put(2, 2);
		System.out.println(map.get(2));

		map.put(543, 543);
		map.put(666, 666);
		map.put(72, 72);
		map.put(75, 75);
		map.put(109, 109);
		map.put(12345, 12345);
		map.put(13, 13);
		map.put(14, 14);
		map.put(116, 116);
		map.put(1267, 1267);
		map.put(613, 613);
		map.put(174, 174);

		System.out.println(map.get(72));
		System.out.println(map.get(75));
		System.out.println(map.get(13));
		System.out.println(map.get(12345));
		System.out.println(map.get(666));
		System.out.println(map.get(543));
		System.out.println(map.get(116));
		System.out.println(map.get(174));
		System.out.println(map.get(18));
		System.out.println(map.get(613));

		String mymap = map.show();
		System.out.println(mymap);

		System.out.println(map.get(10));
		System.out.println(map.get(11));
		System.out.println(map.get(12));
		System.out.println(map.get(13));
		System.out.println(map.get(14));
		System.out.println(map.get(15));
		System.out.println(map.get(16));
		System.out.println(map.get(17));
		System.out.println(map.get(18));
		System.out.println(map.get(19));
		System.out.println(map.get(20));
		System.out.println(map.get(21));
		System.out.println(map.get(22));
		System.out.println(map.get(23));
		System.out.println(map.get(24));
		System.out.println(map.get(25));
		System.out.println(map.get(26));
		System.out.println(map.get(27));
		System.out.println(map.get(1));
		System.out.println(map.get(2));

		System.out.println("TEST MED RANDOM");

		Random random = new Random(123456);
		System.out.println(random);
		HashSet<Integer> randNbrs = new HashSet<Integer>();
		for (int i = 0; i < 1000; i++) {
			int r = random.nextInt(10000);
			map.put(r, r);
			randNbrs.add(r);
			System.out.println(map.get(r));
		}
		System.out.println(map.show());
		
		
		System.out.println("Test with map 2");
		SimpleHashMap<Integer, Integer> map2 = new SimpleHashMap<Integer, Integer>(
				9);
		map2.put(1, 1);
		System.out.println(map2.get(1));
	//	map2.remove(1);
		System.out.println(map2.get(1));
		map2.put(1, 1);
		map2.put(11, 11);
		map2.put(12, 12);
		map2.put(21, 21);
		map2.put(8, 8);
		map2.put(15, 15);
		map2.put(24, 24);
		map2.put(56, 56);
		map2.put(57, 57);
		map2.put(58, 58);
		map2.put(59, 59);
		map2.put(543, 543);
		map2.put(666, 666);
		map2.put(72, 72);
		map2.put(75, 75);
		map2.put(109, 109);
		map2.put(12345, 12345);
		map2.put(13, 13);
		map2.put(14, 14);
		map2.put(116, 116);
		map2.put(1267, 1267);
		map2.put(613, 613);
		map2.put(174, 174);
		map2.put(99, 99);
		map2.put(156, 156);
		map2.put(12388, 12388);
		map2.put(64, 64);
		map2.put(6489, 6489);
		map2.put(700, 700);
		map2.put(1263, 1263);
		map2.put(655, 655);
		map2.put(942, 942);
		System.out.println("The size of the map is: " + map2.size());
		System.out.println(map2.show());
		map2.remove(21);
		System.out.println("The size of the map is: " + map2.size());
		System.out.println(map2.show());
		map2.put(21, 21);
		System.out.println("The size of the map is: " + map2.size());
		System.out.println(map2.show());
		map2.remove(21);
		System.out.println("The size of the map is: " + map2.size());
		System.out.println(map2.show());
		System.out.println(map2.remove(1));
		System.out.println("The size of the map is: " + map2.size());
		System.out.println(map2.show());
		System.out.println(map2.remove(8));
		System.out.println(map2.remove(12345));
		System.out.println(map2.remove(109));
		System.out.println(map2.remove(11));
		System.out.println(map2.remove(1263));
		
		System.out.println("The size of the map is: " + map2.size());
		
		System.out.println(map2.show());

/**		System.out.println("TEST MED RANDOM 2");

		System.out.println(random);
		HashSet<Integer> randNbrs2 = new HashSet<Integer>();
		for (int i = 0; i < 1000; i++) {
			int r = random.nextInt(10000);
			map2.put(r, r);
			randNbrs2.add(r);
			System.out.println("Added value: " + map2.get(r));
		}
		System.out.println(map2.show());
		for (int i : randNbrs2) {
			System.out.println("Värde som tas bort: " + i  + " " + map2.remove(i));
		}  */
	

	}

}
