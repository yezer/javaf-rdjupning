package set;

import java.util.NoSuchElementException;
import java.util.Iterator;


public class MaxSet<E extends Comparable<E>> extends ArraySet<E> {
	private E maxElement;
	
	
	/**
	 * Constructs a new empty set.
	 */
	public MaxSet() {
		super();
		
		
		maxElement =null;
		
		
		}
		
	
	/** Returns the currently largest element in this set. 
	pre: the set is not empty 
	post: the set is unchanged 
	@return the currently largest element in this set 
	@throws NoSuchElementException if this set is empty 
	*/ 
	public E getMax() {
		if (maxElement==null){
			throw new NoSuchElementException();
		}
			return maxElement;
		}
	
	
	/** 
	 * Adds the specified element to this set, if it is not already present.
	 * post: x is added to the set if it is not already present
	 * @param  x the element to be added
	 * @return true if the specified element was added
	 */
	public boolean add(E x) {
		if (maxElement==null){
			maxElement =x;
		}
		if(super.add(x)){
			
			if (maxElement.compareTo(x) <0)  {
				maxElement = x;
			}
		}
		return true;
	}
	
	/** 
	 * Removes the specified element from this set if it is present. 
	 * post: 	x is removed if it was present
	 * @param 	x the element to remove - if present
	 * @return true if the set contained the specified element
	 */
	public boolean remove(Object x) {
		if(maxElement == null){
			return false;
		}
		if(size()==1){
			super.remove(x);
			maxElement=null;
			return true;
		}
		if (maxElement.equals(x)) {
			super.remove(x);
			Iterator<E> itr = iterator();
			maxElement=itr.next();
				while(itr.hasNext()) {
					E e = itr.next();
					int s = maxElement.compareTo(e);
					if(s<0)
						maxElement = e;
				}
				return true;
		}
		super.remove(x);
		return true;
	}
	
	/** Adds all of the elements in the specified set, for which it is 
	possible, to this set. 
	post: all elements, for which it is possible, in the 
	specified set are added to this set. 
	@return true if this set changed as a result of the call 
	*/
	public boolean addAll(SimpleSet<? extends E> c) {
		super.addAll(c);
		return false;
	}
	
}