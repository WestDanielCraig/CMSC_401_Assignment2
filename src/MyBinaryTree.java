/* Java program to demonstrate insert operation in binary search tree 
 * 
 * Code from BTNode search, addRec, deleteRec, and tree traversals based off
 * BST page on geeksforgeeks.org
 * 
 */
class MyBinaryTree { 
  
    /* Class containing left and right child of current node and key value*/
    class BTNode { 
        int key;
        BTNode parent;
        BTNode left, right; 
  
        public BTNode(int item) { 
            key = item; 
            left = right = null; 
        }
        //Method to get node data
        public int getData() {
        	return key;
        }        
    }
    
    // Root of BST 
    BTNode root; 
  
    // Constructor 
    MyBinaryTree() {  
        root = null;  
    } 
  
    // A boolean method to call BTNode search to find a specific key
    public boolean contains(int num) {
        BTNode temp = search(root, num);

    	if (temp == null) {
            return false;
        } else
    		return true;
    }
    
 // A utility function to search a given key in BST 
    public BTNode search(BTNode root, int key) { 
        // Base Cases: root is null or key is present at root 
        if (root==null || root.key==key) 
            return root; 
      
        // val is greater than root's key 
        if (root.key > key) 
            return search(root.left, key); 
      
        // val is less than root's key 
        return search(root.right, key); 
    } 
    
    // This method mainly calls insertRec() 
    void add(int newNum) {
       root = addRec(root, newNum);
    } 
      
    /* A recursive function to insert a new key in BST */
    BTNode addRec(BTNode root, int key) { 
  
        /* If the tree is empty, return a new node */
        if (root == null) { 
            root = new BTNode(key); 
            return root; 
        } 
  
        /* Otherwise, recur down the tree */
        if (key < root.key) 
            root.left = addRec(root.left, key); 
        else if (key > root.key) 
            root.right = addRec(root.right, key); 
  
        /* return the (unchanged) node pointer */
        return root; 
    } 
  
    // This method mainly calls deleteRec() 
    boolean delete(int delNum)
    {
        BTNode temp = search(root, delNum);

        if (temp == null) {
            System.out.println("There is no node numbered " + delNum);
            return false;
        } else {
            root = deleteRec(root, delNum);
        }
        return true;
    } 
  
    /* A recursive function to insert a new key in BST */
    BTNode deleteRec(BTNode root, int key) { 
        /* Base Case: If the tree is empty */
        if (root == null)  return root; 
  
        /* Otherwise, recur down the tree */
        if (key < root.key) 
            root.left = deleteRec(root.left, key); 
        else if (key > root.key) 
            root.right = deleteRec(root.right, key); 
  
        // if key is same as root's key, then This is the node 
        // to be deleted 
        else
        { 
            // node with only one child or no child 
            if (root.left == null) 
                return root.right; 
            else if (root.right == null) 
                return root.left; 
  
            // node with two children: Get the inorder successor (smallest 
            // in the right subtree) 
            root.key = minValue(root.right); 
  
            // Delete the inorder successor 
            root.right = deleteRec(root.right, root.key); 
        }   
        return root; 
    }
    
    int minValue(BTNode root) { 
        int minv = root.key; 
        while (root.left != null) 
        { 
            minv = root.left.key; 
            root = root.left; 
        } 
        return minv; 
    }

    StringBuilder in_order = new StringBuilder();
    
    // This method mainly calls inOrder() 
    String inOrder()  {
       in_order = new StringBuilder();

       return inOrder(root);
    } 
  
    // A utility function to do inorder traversal of BST 
    String inOrder(BTNode root) {
        if (root != null) { 
            inOrder(root.left); 
            //System.out.print(root.key + " ");
            in_order.append(root.key + " ");
            inOrder(root.right); 
        }
        return in_order.toString();
    }

    StringBuilder post_order = new StringBuilder();

    // This method mainly calls postOrder(BTNode node)
    String postOrder() {
        post_order = new StringBuilder();

        return postOrder(root);
    }
    
    /* Given a binary tree, print its nodes according to the 
    "bottom-up" postorder traversal. */
    String postOrder(BTNode node) {
        if (node == null) 
            return null;
  
        // first recur on left subtree 
        postOrder(node.left); 
  
        // then recur on right subtree 
        postOrder(node.right); 
  
        // now deal with the node 
        //System.out.print(node.key + " ");
        post_order.append(node.key + " ");

        return post_order.toString();
    }

    StringBuilder pre_order = new StringBuilder();

    // This method mainly calls preOrder
    String preOrder() {
        pre_order = new StringBuilder();

        return preOrder(root);
    }
    
    /* Given a binary tree, print its nodes in preorder*/
    String preOrder(BTNode node) {
        if (node == null) 
            return null;
  
        /* first print data of node */
        //System.out.print(node.key + " ");
        pre_order.append(node.key + " ");
  
        /* then recur on left sutree */
        preOrder(node.left); 
  
        /* now recur on right subtree */
        preOrder(node.right);

        return pre_order.toString();
    } 
  
    // Driver Program to test above functions 
    public static void main(String[] args) {   
    	int[] numbers = new int[args.length]; //Makes an int array with the correct length from args array
    	
    	//Loop to parse strings from args array to int array
    	for (int i = 0; i < args.length; i++) {
    		numbers[i] = Integer.parseInt(args[i]);
    	}    	
    	MyBinaryTree tree = new MyBinaryTree(); //Creates new tree object to store array

    	//Loop to go add array of ints to BST
    	for (int i = 0; i < numbers.length; i++) {
    		tree.add(numbers[i]);
    	}
  
        // print in order traversal of the BST
        System.out.println("in order traversal = " + tree.inOrder() + "\n");

    	// print post order traversal of the BST
    	//System.out.println("post order traversal = " + tree.postOrder() + "\n");

    	// print pre order traversal of the BST
        //System.out.println("pre order traversal = " + tree.preOrder() + "\n");

/*        System.out.println("Does the tree contain 5: " + tree.contains(5));
        System.out.println("Does the tree contain 2: " + tree.contains(2));
        System.out.println("Does the tree contain 7: " + tree.contains(7));
        System.out.println("Does the tree contain 42: " + tree.contains(42));
        System.out.println("Does the tree contain 4: " + tree.contains(4));
        System.out.println("Does the tree contain 9998: " + tree.contains(9998));
        System.out.println("Does the tree contain -65: " + tree.contains(-65));
        System.out.println("Does the tree contain -90: " + tree.contains(-90) + "\n");

        System.out.println("Was the number 10 deleted? " + tree.delete(10));
        System.out.println("Was the number 2 deleted? " + tree.delete(2));
        System.out.println("Was the number 9998 deleted? " + tree.delete(9998) + "\n");

        System.out.println("In order traversal of the tree = " + tree.inOrder());
*/








    } 
} 