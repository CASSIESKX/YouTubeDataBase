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
    
}
	   
