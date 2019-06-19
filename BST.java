import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BST {
    private class Node {
        private Video data;
        private Node left;
        private Node right;
        
        public Node(Video data) {
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
    public Video getRoot() throws NoSuchElementException{
        if (isEmpty()) {
        	throw new NoSuchElementException("getRoot(): the tree is empty! No data to acccess!");
        } else {
        	return root.data;
        }
    }
    
    /**
     * Determines whether the tree is empty
     * @return whether the tree is empty
     */
    public boolean isEmpty() {
        return (root == null);
    }
    
    /**
     * Returns the smallest value in the tree
     * @precondition !isEmpty()
     * @return the smallest value in the tree
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    public Video findMin() throws NoSuchElementException{
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
    private Video findMin(Node node) {
        if (node.left != null) {
        	return findMin(node.left);
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
    public boolean search(Video data) {
        if (root == null) {
        	return false;
        } else {
        	if (isPrimaryKey) {
        		return searchByUrl(data, root);
        	} else {
        		return searchByVideoName(data, root);
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
    private boolean searchByUrl(Video data, Node node) {
        if (data.compareToByUrl(node.data) == 0) {
        	return true;
        } else if (data.compareToByUrl(node.data) < 0) {
        	if (node.left == null) {
        		return false;
        	} else {
        		return searchByUrl(data, node.left);
        	}
        } else {
        	if (node.right == null) {
        		return false;
        	} else {
        		return searchByUrl(data, node.right);
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
    private boolean searchByVideoName(Video data, Node node) {
        if (data.compareToByVideoName(node.data) == 0) {
        	return true;
        } else if (data.compareToByVideoName(node.data) < 0) {
        	if (node.left == null) {
        		return false;
        	} else {
        		return searchByVideoName(data, node.left);
        	}
        } else {
        	if (node.right == null) {
        		return false;
        	} else {
        		return searchByVideoName(data, node.right);
        	}
        }
    }
    
    /**
     * Search for all the video with input video name
     * @param data the value to search for
     * @return an arraylist contains all the video element with required name
     */
    public ArrayList<Video> searchByVideoName(Video data) {
    	ArrayList<Video> ret = new ArrayList<Video>();
    	searchByVideoName(root, ret, data);
    	return ret;
    }
    
    /**
     * Helper method for Search for all the video with input video name
     * @param node the current node to check
     * @param al the arraylist contains videos with matched name
     * @param data the value to search for
     */
    private void searchByVideoName(Node node, ArrayList<Video> al, Video data) {
    	if (node == null) {
    		return;
    	}
    	searchByVideoName(node.left, al, data);
    	searchByVideoName(node.right, al, data);
    	if (data.getName().equals(node.data.getName())) {
    		al.add(node.data);
    	}
    }
    
    /***MUTATORS***/
    
    /**
     * Inserts a new node in the tree
     * @param data the data to insert
     * @return whether inserting is successful
     */
    public boolean insert(Video data) {
       if (root == null) {
    	   root = new Node(data);
       } else {
    	   if (isPrimaryKey) {
    		   if (!insertByUrl(data, root)) {
    			   return false;
    		   }
    	   } else {
    		   insertByVideoName(data, root);
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
    private boolean insertByUrl(Video data, Node node) {
    	if (data.compareToByUrl(node.data) == 0) {
    		return false;
    	} else if (data.compareToByUrl(node.data) < 0) {
        	if (node.left == null) {
        		node.left = new Node(data);
        		return true;
        	} else {
        		return insertByUrl(data, node.left);
        	}
        } else {
        	if (node.right == null) {
        		node.right = new Node(data);
        		return true;
        	} else {
        		return insertByUrl(data, node.right);
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
    private void insertByVideoName(Video data, Node node) {
        if (data.compareToByVideoName(node.data) <= 0) {
        	if (node.left == null) {
        		node.left = new Node(data);
        	} else {
        		insertByVideoName(data, node.left);
        	}
        } else {
        	if (node.right == null) {
        		node.right = new Node(data);
        	} else {
        		insertByVideoName(data, node.right);
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
    public void remove(Video data) throws NoSuchElementException{
        if (root == null) {
        	throw new NoSuchElementException("remove(): the tree is empty! No data to acccess!");
        } else {
        	if (isPrimaryKey) {
        		root = removeByUrl(data, root);
        	} else {
        		root = removeByVideoName(data, root);
        	}
        	
        }
    }
    
    /**
     * Helper method to the remove method which removes by primary key
     * @param data the data to remove
     * @param node the current node
     * @return an updated reference variable
     */
    private Node removeByUrl(Video data, Node node) {       
        if (node == null) {
        	throw new NoSuchElementException("remove(): Element not found! Cannot find " + data + " in the tree.");
        } else if (data.compareToByUrl(node.data) < 0) {
        	node.left = removeByUrl(data, node.left);
        } else if (data.compareToByUrl(node.data) > 0) {
        	node.right = removeByUrl(data, node.right);
        } else {
        	if (node.left == null && node.right == null) {
        		return null;
        	} else if (node.left != null && node.right == null) {
        		return node.left;
        	} else if (node.left == null && node.right != null) {
        		return node.right;
        	} else {
        		Video minData = findMin(node.right);
        		node.data = minData;
        		node.right = removeByUrl(minData, node.right);
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
    private Node removeByVideoName(Video data, Node node) {       
        if (node == null) {
        	throw new NoSuchElementException("remove(): Element not found! Cannot find " + data + " in the tree.");
        } else if (data.compareToByVideoName(node.data) < 0) {
        	node.left = removeByVideoName(data, node.left);
        } else if (data.compareToByVideoName(node.data) > 0) {
        	node.right = removeByVideoName(data, node.right);
        } else {
        	if (node.left == null && node.right == null) {
        		return null;
        	} else if (node.left != null && node.right == null) {
        		return node.left;
        	} else if (node.left == null && node.right != null) {
        		return node.right;
        	} else {
        		Video minData = findMin(node.right);
        		node.data = minData;
        		node.right = removeByVideoName(minData, node.right);
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