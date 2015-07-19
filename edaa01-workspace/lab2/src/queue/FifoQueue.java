package queue;

import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		last = null;
	}

	/**
	 * Returns an iterator over the elements in this queue
	 * 
	 * @return an iterator over the elements in this queue
	 */

	public Iterator<E> iterator() {
		QueueIterator itr = new QueueIterator();
		return itr;
	}

	/**
	 * Returns the number of elements in this queue
	 * 
	 * @return the number of elements in this queue
	 */
	public int size() {
		return size;
	}

	/**
	 * Inserts the specified element into this queue, if possible post: The
	 * specified element is added to the rear of this queue
	 * 
	 * @param x
	 *            the element to insert
	 * @return true if it was possible to add the element to this queue, else
	 *         false
	 */
	public boolean offer(E x) {
		QueueNode<E> node = new QueueNode<E>(x);
		if (last == null) {
			last = node;
			last.next = last;
		} else {
			QueueNode<E> first = last.next;
			last.next = node;
			node.next = first;
			last=node;

		}
		size = size + 1;
		return true;
	}

	/**
	 * Retrieves and removes the head of this queue, or null if this queue is
	 * empty. post: the head of the queue is removed if it was not empty
	 * 
	 * @return the head of this queue, or null if the queue is empty
	 */
	public E poll() {
		if (size() != 0) {
			E element = last.next.element;
			last.next = last.next.next;
			size = size - 1;
			if(size==0){
				last=null;
			}
			return element;
		}
		
		return null;
	}

	/**
	 * Retrieves, but does not remove, the head of this queue, returning null if
	 * this queue is empty
	 * 
	 * @return the head element of this queue, or null if this queue is empty
	 */
	public E peek() {
		if (size != 0) {

			return last.next.element;
		}
		return null;
	}

	/**
	 * Appends the specified queue to this queue
	 */
	public void append(FifoQueue<E> q) {
		
		if (size > 0 && q.size > 0) {
			QueueNode<E> first = last.next;
			last.next = q.last.next;
			last=q.last;
			last.next=first;
		
		}
		else
		if (size == 0 && q.size > 0) {
			last=q.last;
			
			
		}
		size = size + q.size;
		q.last=null;
		q.size = 0;
		
	}

	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;

		private QueueIterator() {
			if(last==null){
				pos=null;
			}
			else{
			pos = last.next;
			}
			}

		public boolean hasNext() {
			if(pos==null){
				return false;
			}
			else return true;
			
		}

		public E next() {
			if(hasNext()){
				E e = pos.element;
				pos=pos.next;
				if(pos == last.next){
					pos =null;
				}
				return e;
			}
			else{
				throw new NoSuchElementException();
			}
			
		}

		public void remove() { 
			throw new
			UnsupportedOperationException(); 
			}	
		}

}
