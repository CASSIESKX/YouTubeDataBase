/**
 * Defines the a doubly-linked list class
 * @author Kexin Shu
 * @author Yunting Lin
 */

import java.util.NoSuchElementException;

public class List<T extends Comparable<T>>  {
    private class Node {
        private T data;
        private Node next;
        private Node prev;
        
        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    
    private int length;
    private Node first;
    private Node last;
    private Node iterator;
    
    /****CONSTRUCTOR****/
    
    /**
     * Instantiates a new List with default values
     * @postcondition a new and empty list is created
     */
    public List() {
    	first = null;
    	last = null;
    	length = 0;
    	iterator = null;
    }
    
    /**
     * Instantiates a new List by copying another List
     * @param original the List to make a copy of
     * @postcondition a new List object, which is an identical
     * but separate copy of the List original
     */
    public List(List<T> original) {
        if (original == null) {
            return;
        }
        if (original.length == 0) {
            length = 0;
            first = null;
            last = null;
            iterator = null;
        } else {
            Node temp = original.first;
            while (temp != null) {
                addLast(temp.data);
                temp = temp.next;
            }
            iterator = null;
        }
    }

    /****ACCESSORS****/
    
    /**
     * Returns the value stored in the first node
     * @precondition The list is not empty
     * @return the integer value stored at node first
     * @throws NoSuchElementException when precondition is violated
     */
    public T getFirst() throws NoSuchElementException{
        if(first == null) {
        	throw new NoSuchElementException("getFirst(): Cannot get elements from an empty List!");
        }else {
        	return first.data;
        }
    }
    
    /**
     * Returns the value stored in the last node
     * @precondition The list is not empty
     * @return the integer value stored in the node last
     * @throws NoSuchElementException when precondition is violated
     */
    public T getLast() throws NoSuchElementException{
    	if(first == null) {
        	throw new NoSuchElementException("getLast(): Cannot get elements from an empty List!");
        }else {
        	return last.data;
        }
    }
    
    /**
     * Returns the current length of the list
     * @return the length of the list from 0 to n
     */
    public int getLength() {
        return length;
    }
    
    /**
     * Returns whether the list is currently empty
     * @return whether the list is empty
     */
    public boolean isEmpty() {
        if(length == 0) return true;
        else return false;
    }
    
    
    /**
     * Uses the iterative linear search
     * algorithm to locate a specific
     * element in the list
     * @param element the value to search for
     * @return the location of value in the
     * List or -1 to indicate not found
     * Note that if the List is empty we will
     * consider the element to be not found
     * @postcondition: position of the iterator remains
     * unchanged!
     */
    public int linearSearch(T element) {
        if(isEmpty()) return -1;
        Node temp = first;
        int loc = 1;
        while(temp != null) {
        	if(temp.data.equals(element)) {
        		return loc;
        	}
        	temp =temp.next;
        	loc++;
        }
        return -1; //Index from 0 or 1???????????
    }


/**Mutators*/

/**
     * Places the iterator at first
     * and then iteratively advances 
     * it to the specified index
     * no recursion
     * @param index the index where
     * the iterator should be placed
     * @precondition 1 <= index <= length
     * @throws IndexOutOfBoundsException
     * when precondition is violated
     */
    public void advanceToIndex(int index) throws IndexOutOfBoundsException{
     if(index < 1 || index > this.length){
    	 throw new IndexOutOfBoundsException("advanceToIndex: Index out of bound.");
     }
     placeIterator();
     for(int i = 1; i < index; i++) {
    	 this.advanceIterator();
     }
    }
    
    
    /**
c     * Creates a new last element
     * @param data the data to insert at the 
     * end of the list
     * @postcondition A new last element will be created
     */
    public void addLast(T data) {
    	if (first == null) {
            first = last = new Node(data);
        } else {
            Node N = new Node(data);
            last.next = N;
            N.next = null;
            N.prev = last;
            last = N;
        }
        length++;
    }
    
    /**
    * removes the element at the front of the list
    * @precondition The list is not empty
    * @postcondition The first element in the list will be removed
    * @throws NoSuchElementException when precondition is violated
    */
    public void removeFirst() throws NoSuchElementException{
        if(first == null) {
        	throw new NoSuchElementException("removeFirst(): Cannot remove from an empty List!");
        }else if (length == 1) {
            first = last = iterator = null;
        } else {
        	if(iterator == first) {
        		iterator = null;
        	}
            first = first.next;
            first.prev = null; 
        }
        length--;
    }
    
    /**
     * removes the element at the end of the list
     * @precondition The list is not empty
     * @postcondition The last element in the list will be removed
     * @throws NoSuchElementException when precondition is violated
     */
    public void removeLast() throws NoSuchElementException{
    	if(first == null) {
    		throw new NoSuchElementException("removeLast(): Cannot remove from an empty List!");
    	}
        else if (length == 1) {
            removeFirst();
            return;
        } else {
        	//UPDATED
        	if(iterator == last)
                iterator = null;
            last = last.prev;
            last.next = null;
        }
        length--;
    }
      
      /** 
       * Returns the element currently pointed     
       * @precondition The iterator cannot be null     
       * @return the value pointed by the iterator     
       * @throws NullPointerException when the precondition     
       * is violated
       */
      public T getIterator() throws NullPointerException{ 
    	  if(iterator == null) {  
    		  throw new NullPointerException("getIterator: Iterator is off the end of the list.");
    		  }else{ return iterator.data; }
      }
      
      /** 
       * Moves the iterator to the start of the list
       * @throws NullPointerException when the precondition
       * is violated
       */
	public void placeIterator () throws NullPointerException{
		iterator = first;
	}
	
	/** 
	 * Removes the element currently pointed to by the iterator
	 * @precondition The iterator is not null
	 * @postcondition Iterator then points to NULL
	 * @throws NullPointerException when the precondition     
     * is violated
	 */
	public void removeIterator() throws NullPointerException{
		if(iterator == null) { // if the iterator is null
			throw new NullPointerException ("removeIterator: Cannot remove the element pointed by the iterator when the iterator is null.");
		}else if(iterator.next == null) { // if the iterator is at the last node
			removeLast();
			iterator = null;
		}else if(iterator == first) { //if the iterator is at the first node
			removeFirst();
			iterator = null;
		}
		else {
			iterator.prev.next = iterator.next;
			iterator.next.prev = iterator.prev;
			iterator = null;
			length--;
		}
	}
	
	/**
	 * Add an element after the node currently pointed to by the iterator
	 * @precondition The iterator is not null
	 * @postcondition A new element will be inserted after the iterator
	 * @throws nullPointerException if the precondition is violated
	 */
	public void addIterator(T data) throws NullPointerException{
		if(iterator == null) {
			throw new NullPointerException ("addIterator: Cannot add the element after the iterator when the iterator is null.");
		}else if(iterator==last) {
			addLast(data);
		}else {
		Node temp = new Node (data);
		temp.next = iterator.next;
		iterator.next = temp;
		temp.prev = iterator;
		temp.next.prev = temp;
		length++;
		}
	}
	
	/**
	 * Moves the iterator up by one node
	 * @precondition The iterator is not null
	 * @postcondition The iterator moves up by one
	 * @throws nullPointerException if the precondition is violated
	 */
	public void advanceIterator() throws NullPointerException{
		if(iterator == null) {
			throw new NullPointerException ("advanceIterator: Cannot move the iterator when the iterator is null.");
		}else if(iterator == last) {
			iterator = null;
		}else
		iterator = iterator.next;
	}
	
	/**
	 * Determines whether the iterator is off the end of the list
	 * @return Whether the iterator is off the end of the list, i.e. is NULL
	 */
	public boolean offEnd() {
		return iterator == null;
	}
	
}
	   
