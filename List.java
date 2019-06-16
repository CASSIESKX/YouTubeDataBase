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
     * @precondition: the list cannot be empty 
     * @return the data stored at node first
     * @throws NoSuchElementException when precondition is violated
     */
    public T getFirst() throws NoSuchElementException{
        if (length == 0) {
        	throw new NoSuchElementException("getFirst: List is Empty. No data to access!");
        }
        return first.data;
    }
    
    public T getIterator() throws NullPointerException {
    	if (offEnd()) {
    		throw new NullPointerException("getIterator: "
    				+ "The iterator is off the end of the List. No data to access!");
    	}
    	return iterator.data;
    }
    
    /**
     * Returns whether the iterator is off the end of the List
     * @Return whether the iterator is null
     */
    public boolean offEnd() {
    	return iterator == null;
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
        return -1; 
    }
    
    /****MUTATORS****/
    
    /**
     * Creates a new last element
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
    * @precondition: the list cannot be empty
    * @postcondition: the first element in the list will be removed
    * @throws NoSuchElementException when precondition is violated
    */
    public void removeFirst() throws NoSuchElementException{
    	if (length == 0) {
    		throw new NoSuchElementException("removeFirst(): Cannot remove from an empty List!");
    	} else if (length == 1) {
    		first = last = null;
    	} else {
    		first = first.next;
    		first.prev = null;
    	}
        length--;
    }
    
    /**
     * removes the element at the end of the list
     * @precondition: the list cannot be empty
     * @postcondition: the last element in the list will be removed
     * @throws NoSuchElementException when precondition is violated
     */
    public void removeLast() throws NoSuchElementException {
        if (length == 0) {
        	throw new NoSuchElementException("removeLast(): Cannot remove from an empty List!");
        } else if (length == 1) {
    		first = last = null;
    	} else {
    		last = last.prev;
    		last.next = null;
    	}
    	length--;
    }
    
    /**
     * moves the iterator to the start of the list
     * @postcondition: the iterator is pointed at the first node of the list
     */
    public void placeIterator() {
    	iterator = first;
    }
    
    /**
     * moves the iterator up by one node
     * @precondition iterator != null
     * throws NullPointerException when precondition
     * is violated
     */
    public void advanceIterator() throws NullPointerException {
    	if (offEnd()) {
    		throw new NullPointerException("advanceIterator():"
    				+ "Iterator is off end. Cannot be advanced!");
    	} else {
    		iterator = iterator.next;
    	}
    }
    
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
    public void advanceToIndex(int index) throws IndexOutOfBoundsException {
        if (index < 1 || index > length) {
        	throw new IndexOutOfBoundsException("advanceToIndex(): Index is out of bounds!");
        } else {
        	placeIterator();
        	for (int i = 1; i < index; i++) {
        		advanceIterator();
        	}
        }
    }
    	
    /**
     * removes the element currently pointed to by the iterator
     * @precondition: the iterator is off the end of the List
     * @postcondition: iterator then points to NULL
     */
    public void removeIterator() throws NullPointerException {
    	if (offEnd()) {
    		throw new NullPointerException("removeIterator(): "
    				+ "Iterator is off end. Cannot be removed!");
    	} else if (iterator == first) {
    		removeFirst();
    	} else if (iterator == last) {
    		removeLast();
    	} else {
    		iterator.prev.next = iterator.next;
    		iterator.next.prev = iterator.prev;
    		length--;
    	}
    	iterator = null;
    }
    
    /**
     * inserts an element after the node currently pointed to by the iterator 
     * @param data the new data to insert
     * @precondition iterator != null
     * throws NullPointerException when precondition
     * is violated
     */
    public void addIterator(T data) throws NullPointerException {
    	if (offEnd()) {
    		throw new NullPointerException("addIterator(): "
    				+ "Iterator is off end. Cannot be added!");
    	} else if (iterator == last) {
    		addLast(data); 
    	} else {
    		Node n = new Node(data);
    		n.next = iterator.next;
    		n.prev = iterator;
    		n.next.prev = n;
    		iterator.next = n;
    		length++;
    	}
    }
    
}
	   
