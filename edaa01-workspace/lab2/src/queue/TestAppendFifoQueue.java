package queue;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestAppendFifoQueue {
	private FifoQueue q1;
	private FifoQueue q2;

	@Before
	public void setUp() throws Exception {
		q1 = new FifoQueue<Integer>();
		q2 = new FifoQueue<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		q1 = null;
		q2 = null;
	}

	@Test
	public void testAppend() {
		q1 = new FifoQueue();
		q2 = new FifoQueue();

		// Testar två tomma köer
		assertTrue(q1.size() == 0);
		assertTrue(q2.size() == 0);
		assertEquals(q1.poll(), null);
		assertEquals(q2.poll(), null);
	}

	// Testar tom kö som konkateneras till icke-tom kö
	@Test
	public void testAppend2() {

		q1.offer(7);
		q1.append(q2);
		assertTrue(q1.size() == 1);
		assertTrue(q2.size() == 0);
		assertEquals(q1.poll(), 7);
		assertEquals(q2.poll(), null);
		assertTrue(q1.size() == 0);
	}

	// Testar icke-tom kö som konkateneras till tom kö
	@Test
	public void testAppend3() {
		q2.offer(3);
		q1.append(q2);
		assertTrue(q1.size() == 1);
		assertTrue(q2.size() == 0);
		assertEquals(q1.poll(), 3);
		assertEquals(q2.poll(), null);
		System.out.println(q2.poll());
	}

	// Testar två icke-tomma köer

	@Test
	public void testAppend4() {

		q2.offer(43);
		q1.append(q2);
		assertTrue(q1.size() == 1);
		assertTrue(q2.size() == 0);
		assertEquals(q1.poll(), 43);
		assertEquals(q2.poll(), null);
		q2.offer(3);
		q1.append(q2);
		assertTrue(q1.size() == 1);
		System.out.println(q1.size());
		System.out.println(q2.size());
		q1.offer(3);
		q2.offer(10);
		q1.append(q2);
		assertTrue(q1.size() == 3);
		System.out.println(q1.size());
		assertEquals(q1.poll(),3);
		assertEquals(q2.poll(), null);

	}
}
