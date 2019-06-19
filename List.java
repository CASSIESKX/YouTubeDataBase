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
     * Determines whether a List is sorted
     * by calling its recursive helper method
     * isSorted
     * Note: An empty List can be
     * considered to be (trivially) sorted
     * @return whether this List is sorted
     */
    public boolean isSorted() {
    	if(isEmpty()) return true;
    	if(length == 1) return true;
    	else
    		return isSorted(first.next);
    }
    
    /**
     * Determines whether a List is 
     * sorted in ascending order recursively
     * @return whether this List is sorted
     */
    private boolean isSorted(Node node) {
        if(node.next == null && node.data.compareTo(node.prev.data) > 0) return true;
        else if(node.data.compareTo(node.prev.data) < 0) return false;
        return isSorted(node.next);
        	
    }
    
    /**
     * Returns the index of the iterator
     * from 1 to n. Note that there is 
     * no index 0. Does not use recursion.
     * @precondition the iterator should not be null
     * @return the index of the iterator
     * @throws NullPointerException when
     * the precondition is violated
     */
    public int getIndex() throws NullPointerException{
    	if(iterator == null) 
    		throw new NullPointerException("getIndex(): Cannot get index from an null iterator.");
    	int val = 1;
    	Node temp = first;
    	while(temp != iterator && temp != null) {
    		val++;
    		temp=temp.next;
    	}
    	return val;
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
    
    /**
     * Returns the index from 1 to length
     * where value is located in the List
     * by calling the private helper method
     * binarySearch
     * @param value the value to search for
     * @return the index where value is 
     * stored from 1 to length, or -1 to
     * indicate not found
     * @precondition isSorted()
     * @postcondition the position of the
     * iterator must remain unchanged! 
     * @throws IllegalStateException when the
     * precondition is violated.
     */
    public int binarySearch(T value) throws IllegalStateException {
        if(!isSorted()) {
        	throw new IllegalStateException("binarySearch: Error, the list is not sorted.");
        }else 
        	return binarySearch(1, length, value);
        
    }
    
    /**
     * Searches for the specified value in
     * the List by implementing the recursive
     * binarySearch algorithm
     * @param low the lowest bounds of the search
     * @param high the highest bounds of the search
     * @param value the value to search for
     * @return the index at which value is located
     * or -1 to indicate not found
     * @postcondition the location of the iterator
     * must remain unchanged
     */
    private int binarySearch(int low, int high, T value) {
    	Node temp = first;
    	int mid = (low+high)/2;
    	int count = mid-1;
    	while(count > 0) {
    		temp = temp.next;
    		count--;
    	}
    	if(high < low) return -1;
    	if(value.equals(temp.data)) return mid;
    	else if(value.compareTo(temp.data) > 0) { 
    		return binarySearch(mid+1, high, value);
    	}else {
    		return binarySearch(low, mid-1, value);
    	}
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
     * Creates a new first element
     * @param data the data to insert at the 
     * front of the list
     * @postcondition A new first element will be created 
     */
    public void addFirst(T data) {
    	if (first == null) {
            first = last = new Node(data);
        } else {
            Node N = new Node(data);
            N.next = first;
            first.prev = N;
            first = N;
        }
        length++;
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
	 * Moves the iterator down by one node
	 * @precondition The iterator is not null
	 * @postcondition The iterator moves a unit down (backward)
	 * @throws nullPointerException if the precondition is violated
	 */
	public void reverseIterator () throws NullPointerException{
		if(iterator == null) {
			throw new NullPointerException("reverseIterator: Cannot move the iterator when the iterator is null.");
		}else if(iterator == first) {
			iterator = null;
		}else
		iterator = iterator.prev;
	}
	
	/**
	 * Determines whether the iterator is off the end of the list
	 * @return Whether the iterator is off the end of the list, i.e. is NULL
	 */
	public boolean offEnd() {
		return iterator == null;
	}
	/**
	 * overrides the equals method for object to compares this list
	 * to another list to see if they contain the same data in the 
	 * same order.
	 * @return Whether this list equals the other
	 */
	 @SuppressWarnings("unchecked")
	 @Override public boolean equals(Object o) {
		 if(o == this) {
			 return true;
		 }else if(!(o instanceof List)){
			 return false;
		 }else {
			 List<T> L = (List<T>) o; //safe to cast
			 if(this.length != L.length) {
				 return false;
			 }else {
				 Node temp1 = this.first;
				 Node temp2 = L.first;
				 while(temp1 != null) {
					 if(temp1.data != temp2.data) {
						 return false;
					 }
					 temp1 = temp1.next;
					 temp2 = temp2.next;
				 }
				 return true;
			 }
		 }
	 }//Note: this is an accessor method because it tells you something about the list
	
/****ADDITIONAL OPERATIONS****/
    
    /**
     * List with each value on its own line
     * At the end of the List a new line
     * @return the List as a String for display
     */
      @Override public String toString() {
          String result = "";
          Node temp = first;
          while(temp != null) {
              result += temp.data + " ";
              temp = temp.next;
          }
          return result;
      }
	
	 
	 /**
	  * Prints an numbers list
	  * @precondition The list cannot be empty
	  * @throw NullPointerException when the precondition is violated
	  * */
	 public void printNumberedList() throws NullPointerException{
		 if(length == 0) {
			 throw new NullPointerException ("printNumberedList(): Cannot print an empty list!");
		 }
		 this.placeIterator();
		 int count = 1;
		 while(iterator != null) {
			 System.out.print(count++ + ": " + getIterator() + "\n");
			 this.advanceIterator();
		 }
	 }
	 
	 /**
	     * Prints a linked list to the console
	     * in reverse by calling the private 
	     * recursive helper method printReverse
	     */
	    public void printReverse() {
	    	printReverse(first);
	    	System.out.println();
	    }
	    
	    /**
	     * Recursively prints a linked list to the console
	     * in reverse order from last to first (no loops)
	     * Each element separated by a space
	     * Should print a new line after all
	     * elements have been displayed
	     */    

	    private void printReverse(Node node) {
	    	if(node == null) return;
	    	else {
	    		printReverse(node.next);
	    		System.out.print(node.data + " ");
	    	}
	    }     
}
	   
