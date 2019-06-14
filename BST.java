import java.util.NoSuchElementException;

public class BST<T extends Comparable<T>> {
    private class Node {
        private T data;
        private Node left;
        private Node right;
        
        public Node(T data) {
            this.data = data;
            left = null;
            right = null;
        }
    }
    
    private Node root;
    private boolean isPrimaryKey;
    
    /***CONSTRUCTORS***/
    
    /**
     * Default constructor for BST
     * sets root to null
     * sets isPrimaryKey to true;
     */
    public BST() {
        root = null;
        isPrimaryKey = true;
    }
    
    /**
     * One-argument constructor for BST
     * @param isPrimaryKey the key to use for BST
     * sets root to null
     */
    public BST(boolean isPrimaryKey) {
    	root = null;
    	this.isPrimaryKey = isPrimaryKey;
    }
    
    /***ACCESSORS***/
    
    /**
     * Returns the data stored in the root
     * @precondition !isEmpty()
     * @return the data stored in the root
     * @throws NoSuchElementException when
     * preconditon is violated
     */
    public T getRoot() throws NoSuchElementException{
        if (isEmpty()) {
        	throw new NoSuchElementException("getRoot(): the tree is empty! No data to acccess!");
        } else {
        	return root.data;
        }
    }
    
    /**
     * returns whether the BST uses primary key
     * @return whether the BST uses primary key
     */
    public boolean getIsPrimaryKey() {
    	return isPrimaryKey;
    }
    
    /**
     * Determines whether the tree is empty
     * @return whether the tree is empty
     */
    public boolean isEmpty() {
        return (root == null);
    }
    
    /**
     * Returns the current size of the 
     * tree (number of nodes)
     * @return the size of the tree
     */
    public int getSize() {
        return getSize(root);
    }
    
    /**
     * Helper method for the getSize method
     * @param node the current node to count
     * @return the size of the tree
     */
    private int getSize(Node node) {
        if (node == null) {
        	return 0;
        } else {
        	int size = getSize(node.left);
        	size += getSize(node.right);
        	return size + 1;
        }
    }
    
    /**
     * Returns the height of tree by
     * counting edges.
     * @return the height of the tree
     */
    public int getHeight() {
        return getHeight(root);
    }
    
    /**
     * Helper method for getHeight method
     * @param node the current
     * node whose height to count
     * @return the height of the tree
     */
    private int getHeight(Node node) {
        if (node == null) {
        	return -1;
        } else {
        	int heightLeft = getHeight(node.left);
        	int heightRight = getHeight(node.right);
        	if (heightLeft >= heightRight) {
        		return heightLeft + 1;
        	} else {
        		return heightRight + 1;
        	}
        }
    }
    
    /**
     * Returns the smallest value in the tree
     * @precondition !isEmpty()
     * @return the smallest value in the tree
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    public T findMin() throws NoSuchElementException{
        if (isEmpty()) {
        	throw new NoSuchElementException("findMin(): the tree is empty! No data to acccess!");
        } else {
        	return findMin(root);
        }
    }
    
    /**
     * Helper method to findMin method
     * @param node the current node to check
     * if it is the smallest
     * @return the smallest value in the tree
     */
    private T findMin(Node node) {
        if (node.left != null) {
        	return findMin(node.left);
        } else {
        	return node.data;
        }
    }
    
    /**
     * Returns the largest value in the tree
     * @precondition !isEmpty()
     * @return the largest value in the tree
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    public T findMax() throws NoSuchElementException{
        if (isEmpty()) {
        	throw new NoSuchElementException("findMax(): the tree is empty! No data to acccess!");
        } else {
        	return findMax(root);
        }
    }
    
    /**
     * Helper method to findMax method
     * @param node the current node to check
     * if it is the largest
     * @return the largest value in the tree
     */
    private T findMax(Node node) {
        if (node.right != null) {
        	return findMax(node.right);
        } else {
        	return node.data;
        }
    }
    
    /**
     * Searches for a specified value
     * in the tree
     * @param data the value to search for
     * @return whether the value is stored
     * in the tree
     */
    public boolean search(T data) {
        if (root == null) {
        	return false;
        } else {
        	if (isPrimaryKey) {
        		return searchByPrimaryKey(data, root);
        	} else {
        		return searchBySecondaryKey(data, root);
        	}
        	
        }
    }
    
    /**
     * Helper method for the search method which searches by primary key
     * @param data the data to search for
     * @param node the current node to check
     * @return whether the data is stored
     * in the tree
     */
    private boolean searchByPrimaryKey(T data, Node node) {
        if (data.compareToByPrimaryKey(node.data) == 0) {
        	return true;
        } else if (data.compareToByPrimaryKey(node.data) < 0) {
        	if (node.left == null) {
        		return false;
        	} else {
        		return searchByPrimaryKey(data, node.left);
        	}
        } else {
        	if (node.right == null) {
        		return false;
        	} else {
        		return searchByPrimaryKey(data, node.right);
        	}
        }
    }
    
    /**
     * Helper method for the search method
     * @param data the data to search for
     * @param node the current node to check
     * @return whether the data is stored
     * in the tree
     */
    private boolean searchBySecondaryKey(T data, Node node) {
        if (data.compareToBySecondaryKey(node.data) == 0) {
        	return true;
        } else if (data.compareToBySecondaryKey(node.data) < 0) {
        	if (node.left == null) {
        		return false;
        	} else {
        		return searchBySecondaryKey(data, node.left);
        	}
        } else {
        	if (node.right == null) {
        		return false;
        	} else {
        		return searchBySecondaryKey(data, node.right);
        	}
        }
    }
    
    /**
     * Determines whether two trees store
     * identical data in the same structural
     * position in the tree
     * @param o another Object
     * @return whether the two trees are equal
     */
    @SuppressWarnings("unchecked")
	@Override public boolean equals(Object o) {
    	if (o == this) {	
    		return true;
    	} else if (!(o instanceof BST)) {
    		return false;
    	} else {
    		BST<T> t = (BST<T>) o;
    		return equals(root, t.root);
    	}
    }
    
    /**
     * Helper method for the equals method
     * @param node1 the node of the first bst
     * @param node2 the node of the second bst
     * @return whether the two trees contain
     * identical data stored in the same structural
     * position inside the trees
     */    
    private boolean equals(Node node1, Node node2) {
        if (node1 != null && node2 != null) {
        	if (node1.data == node2.data) {
        		return equals(node1.left, node2.left) && equals(node1.right, node2.right);
        	} else {
        		return false;
        	}
        } else if (node1 == null && node2 == null) {
        	return true;
        } else {
        	return false;
        }
    }
    
    /***MUTATORS***/
    
    /**
     * Inserts a new node in the tree
     * @param data the data to insert
     * @return whether inserting is successful
     */
    public boolean insert(T data) {
       if (root == null) {
    	   root = new Node(data);
       } else {
    	   if (isPrimaryKey) {
    		   if (!insertByPrimaryKey(data, root)) {
    			   return false;
    		   }
    	   } else {
    		   insertBySecondaryKey(data, root);
    	   }
       }
       return true;
    }
    
    /**
     * Helper method to insert which inserts by primary key
     * Inserts a new value in the tree
     * @param data the data to insert
     * @param node the current node in the
     * @return whether inserting is successful
     * search for the correct location
     * in which to insert
     */
    private boolean insertByPrimaryKey(T data, Node node) {
    	if (data.compareToByPrimaryKey(node.data) == 0) {
    		return false;
    	} else if (data.compareToByPrimaryKey(node.data) < 0) {
        	if (node.left == null) {
        		node.left = new Node(data);
        		return true;
        	} else {
        		return insertByPrimaryKey(data, node.left);
        	}
        } else {
        	if (node.right == null) {
        		node.right = new Node(data);
        		return true;
        	} else {
        		return insertByPrimaryKey(data, node.right);
        	}
        }
    }
    
    /**
     * Helper method to insert which inserts by secondary key
     * Inserts a new value in the tree
     * @param data the data to insert
     * @param node the current node in the
     * search for the correct location
     * in which to insert
     */
    private void insertBySecondaryKey(T data, Node node) {
        if (data.compareToBySecondaryKey(node.data) <= 0) {
        	if (node.left == null) {
        		node.left = new Node(data);
        	} else {
        		insertBySecondaryKey(data, node.left);
        	}
        } else {
        	if (node.right == null) {
        		node.right = new Node(data);
        	} else {
        		insertBySecondaryKey(data, node.right);
        	}
        }
    }
    
    /**
     * Removes a value from the BST
     * @param data the value to remove
     * @precondition !isEmpty()
     * @precondition the data is located in the tree
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    public void remove(T data) throws NoSuchElementException{
        if (root == null) {
        	throw new NoSuchElementException("remove(): the tree is empty! No data to acccess!");
        } else {
        	if (isPrimaryKey) {
        		root = removeByPrimaryKey(data, root);
        	} else {
        		root = removeBySecondaryKey(data, root);
        	}
        	
        }
    }
    
    /**
     * Helper method to the remove method which removes by primary key
     * @param data the data to remove
     * @param node the current node
     * @return an updated reference variable
     */
    private Node removeByPrimaryKey(T data, Node node) {       
        if (node == null) {
        	throw new NoSuchElementException("remove(): Element not found! Cannot find " + data + " in the tree.");
        } else if (data.compareToByPrimaryKey(node.data) < 0) {
        	node.left = removeByPrimaryKey(data, node.left);
        } else if (data.compareToByPrimaryKey(node.data) > 0) {
        	node.right = removeByPrimaryKey(data, node.right);
        } else {
        	if (node.left == null && node.right == null) {
        		return null;
        	} else if (node.left != null && node.right == null) {
        		return node.left;
        	} else if (node.left == null && node.right != null) {
        		return node.right;
        	} else {
        		T minData = findMin(node.right);
        		node.data = minData;
        		node.right = removeByPrimaryKey(minData, node.right);
        	}
        }
        
        return node;
    }
    
    /**
     * Helper method to the remove method which removes by secondary key
     * @param data the data to remove
     * @param node the current node
     * @return an updated reference variable
     */
    private Node removeBySecondaryKey(T data, Node node) {       
        if (node == null) {
        	throw new NoSuchElementException("remove(): Element not found! Cannot find " + data + " in the tree.");
        } else if (data.compareToBySecondary(node.data) < 0) {
        	node.left = removeBySecondaryKey(data, node.left);
        } else if (data.compareToBySecondary(node.data) > 0) {
        	node.right = removeBySecondaryKey(data, node.right);
        } else {
        	if (node.left == null && node.right == null) {
        		return null;
        	} else if (node.left != null && node.right == null) {
        		return node.left;
        	} else if (node.left == null && node.right != null) {
        		return node.right;
        	} else {
        		T minData = findMin(node.right);
        		node.data = minData;
        		node.right = removeBySecondaryKey(minData, node.right);
        	}
        }
        
        return node;
    }
    
        
    /***ADDITIONAL OPERATIONS***/
    
    /**
     * Prints the data in sorted order 
     * to the console
     */
    public void inOrderPrint() {
        inOrderPrint(root);
        System.out.println();
    }
    
    /**
     * Helper method to inOrderPrint method
     * Prints the data in sorted order
     * to the console
     */
    private void inOrderPrint(Node node) {
        if (node == null) {
        	return;
        } else {
        	inOrderPrint(node.left);
        	System.out.print(node.data);
        	inOrderPrint(node.right);
        }
    }
 
}