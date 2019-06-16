import java.util.ArrayList;
import java.util.NoSuchElementException;

public class HashTable<T extends Comparable<T>> {

	private int numElements;
	private ArrayList<List<T>> Table;

	/**
	 * Constructor for the HashTable.java class. Initializes the Table to be sized
	 * according to value passed in as a parameter Inserts size empty Lists into the
	 * table. Sets numElements to 0
	 * 
	 * @param size the table size
	 */
	public HashTable(int size) {
		// The table is an arraylist of list
		Table = new ArrayList<List<T>>(size);
		while (size > 0) {
			Table.add(new List<T>());
			size--;
		}
		numElements = 0;
	}

	/** Accessors */

	/**
	 * returns the hash value in the Table for a given Object
	 * 
	 * @param t the Object
	 * @return the index in the Table
	 */
	private int hash(T t) {
		int code = t.hashCode();
		return code % Table.size();
	}

	/**
	 * counts the number of elements at this index
	 * 
	 * @param index the index in the Table
	 * @precondition 0 <= index < Table.length
	 * @return the count of elements at this index
	 * @throws IndexOutOfBoundsException
	 */
	public int countBucket(int index) throws IndexOutOfBoundsException {
		// Handle Precondition
		if (index < 0 || index > Table.size()) {
			throw new IndexOutOfBoundsException("countBucket: Index out of bound.\n");
		}
		return Table.get(index).getLength();
	}

	public int tableSize() {
		return Table.size();
	}

	/**
	 * returns total number of elements in the Table
	 * 
	 * @return total number of elements
	 */
	public int getNumElements() {
		return numElements;
	}

	/**
	 * searches for a specified element in the Table
	 * 
	 * @param t the element to search for
	 * @return the index in the Table (0 to Table.length - 1) or -1 if t is not in
	 *         the Table
	 */
	public int search(T t) {
		int code = hash(t);
		return Table.get(code).linearSearch(t);
	}

	/** Manipulation Procedures */

	/**
	 * inserts a new element in the Table calls the hash method to determine
	 * placement
	 * 
	 * @param t the element to insert
	 */
	public void insert(T t) {
		numElements++;
		// calls the hash method to determine placement
		int index = hash(t);
		Table.get(index).addLast(t);
	}

	/**
	 * removes the element t from the Table calls the hash method on the key to
	 * determine correct placement has no effect if t is not in the Table
	 * 
	 * @param t the key to remove
	 * @precondition t must be in the table
	 * @throws NoSuchElementException when the element is not in the table
	 */
	public void remove(T t) throws NoSuchElementException {
		if (search(t) == -1) {
			throw new NoSuchElementException("remove: Cannot remove when the element is not in the table");
		}
		// calls the hash method on the key to get the placement
		int index = hash(t);
		// remove the element
		int indexInList = Table.get(index).linearSearch(t);
		// Place the iterator
		Table.get(index).placeIterator();
		// Advance the iterator to the indexInList
		Table.get(index).advanceToIndex(indexInList);
		// Delete the element
		Table.get(index).removeIterator();
		// Decrement the size
		numElements--;
	}

	/** Additional Methods */

	/**
     * Prints all the keys at a specified
     * bucket in the Table. Each element displayed
     * on its own line, with a blank line 
     * separating each element
     * Above the elements, prints the message
     * "Printing bucket #<bucket>:"
     * Note that there is no <> in the output
     * @param bucket the index in the Table
     * @throws IndexOutOfBoundsException
     */
    public void printBucket(int bucket) throws IndexOutOfBoundsException{
    	if(bucket < 0 || bucket > Table.size()) {
    		throw new IndexOutOfBoundsException ("printBucket: Index Out Of Bounds. \n");
    	}
    		System.out.println("Printing bucket #" + bucket +": ");
			System.out.println(Table.get(bucket).toString());
    }

	/**
     * Prints the first element at each bucket
     * along with a count of the total elements
     * with the message "+ <count> -1 more 
     * at this bucket." Each bucket separated
     * with to blank lines. When the bucket is 
     * empty, prints the message "This bucket
     * is empty." followed by two blank lines
     */
    public void printTable(){
        for(int i = 0 ; i < Table.size(); i++) {
        	if(Table.get(i).getLength() == 0) {
        		continue;
        	}else{
        		T temp = Table.get(i).getFirst();
        		System.out.print(temp.toString());
        	}
        }
     }
    
    public List<T> getElement(int bucket){
    	return Table.get(bucket);
    } 
	
}
