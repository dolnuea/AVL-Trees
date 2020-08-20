/**
 * @author Dolunay Dagci
 * Assignment: Ch22 Program Challenge 1
 * Due Date: 6/12/2019
 This class implements AVLTrees.
 */
class DD_AVLTree extends DD_BinaryTree
{
    /**
     This class represents the result of removing
     a node from a binary tree.
     */
    private class DD_RemovalResult
    {
        DD_Node node;    // Removed node
        DD_Node tree;    // Remaining tree

        DD_RemovalResult(DD_Node node, DD_Node tree)
        {
            this.node = node;
            this.tree = tree;
        }
    }
    // Convenience method casts the inherited root
    // from DD_Node to DD_AVLNode.
    private DD_AVLNode getRoot()
    {
        return (DD_AVLNode) root;
    }

    /**
     The getHeight method computes the height of an AVL tree.
     @param tree An AVL tree.
     @return The height of the AVL tree.
     */
    static int getHeight(DD_AVLNode tree)
    {
        if (tree == null)
            return -1;
        else
            return tree.height;
    }

    /**
     The balance method rebalances an AVL tree.
     @param bTree The AVL tree needing to be balanced.
     @return The balanced AVL tree.
     */
    private DD_AVLNode balance(DD_AVLNode bTree)
    {
        int rHeight = getHeight(bTree.getRight());
        int lHeight = getHeight(bTree.getLeft());

        if (rHeight > lHeight)
        {
            DD_AVLNode rightChild = bTree.getRight();
            int rrHeight = getHeight(rightChild.getRight());
            int rlHeight = getHeight(rightChild.getLeft());
            if (rrHeight > rlHeight)
            {
                return rrBalance(bTree);
            } else
            {
                return rlBalance(bTree);
            }
        } else
        {
            DD_AVLNode leftChild = bTree.getLeft();
            int llHeight = getHeight(leftChild.getLeft());
            int lrHeight = getHeight(leftChild.getRight());
            if (llHeight > lrHeight)
            {
                return llBalance(bTree);
            } else
            {
                return lrBalance(bTree);
            }
        }
    }

    /**
     The rrBlance method corrects an RR imbalance.
     @param bTree The AVL tree wih an RR imbalance.
     @return The balanced AVL tree.
     */
    private DD_AVLNode rrBalance(DD_AVLNode bTree)
    {
        DD_AVLNode rightChild = bTree.getRight();
        DD_AVLNode rightLeftChild = rightChild.getLeft();
        rightChild.left = bTree;
        bTree.right = rightLeftChild;
        bTree.resetHeight();
        rightChild.resetHeight();
        return rightChild;
    }

    /**
     The rlBalance method corrects an RL imbalance.
     @parame bTree The AVL tree with an RL imbalance.
     @return The balanced AVL tree.
     */
    private DD_AVLNode rlBalance(DD_AVLNode bTree)
    {
        DD_AVLNode root = bTree;
        DD_AVLNode rNode = root.getRight();
        DD_AVLNode rlNode = rNode.getLeft();
        DD_AVLNode rlrTree = rlNode.getRight();
        DD_AVLNode rllTree = rlNode.getLeft();

        // Build the restructured tree.
        rNode.left = rlrTree;
        root.right = rllTree;
        rlNode.left = root;
        rlNode.right = rNode;

        // Adjust heights.
        rNode.resetHeight();
        root.resetHeight();
        rlNode.resetHeight();

        return rlNode;
    }

    /**
     The llBalance method corrects an LL imbalance.
     @param bTree The AVL tree with an LL imbalance.
     @return The balanced AVL tree.
     */
    private DD_AVLNode llBalance(DD_AVLNode bTree)
    {
        DD_AVLNode leftChild = bTree.getLeft();
        DD_AVLNode lrTree = leftChild.getRight();
        leftChild.right = bTree;
        bTree.left = lrTree;
        bTree.resetHeight();
        leftChild.resetHeight();
        return leftChild;
    }

    /**
     The lrBalance method corrects an LR imbalance.
     @param bTree The AVL tree with an LR imbalance.
     @return The balanced AVL tree.
     */
    private DD_AVLNode lrBalance(DD_AVLNode bTree)
    {
        DD_AVLNode root = bTree;
        DD_AVLNode lNode = root.getLeft();
        DD_AVLNode lrNode = lNode.getRight();
        DD_AVLNode lrlTree = lrNode.getLeft();
        DD_AVLNode lrrTree = lrNode.getRight();

        // Build the restructured tree.
        lNode.right = lrlTree;
        root.left = lrrTree;
        lrNode.left = lNode;
        lrNode.right = root;

        // Adjust heights.
        lNode.resetHeight();
        root.resetHeight();
        lrNode.resetHeight();

        return lrNode;
    }
    /**
     The add method adds a value to this AVL tree.
     @param x The value to add.
     @return true.
     */
    boolean add(int x)
    {
        root = add((DD_AVLNode) root, x);
        return true;
    }

    /**
     The add method adds a value to an AVL tree.
     @return The root of the augmented AVL tree.
     */
    private DD_AVLNode add(DD_AVLNode bTree, int x)
    {
        if (bTree == null)
        {
            return new DD_AVLNode(x);
        }
        if (x < bTree.value)
        {
            bTree.left = add(bTree.getLeft(), x);
        } else
        {
            bTree.right = add(bTree.getRight(), x);
        }

        // Compute heights of the left and right subtrees
        // and rebalance the tree if needed.
        int leftHeight = getHeight(bTree.getLeft());
        int rightHeight = getHeight(bTree.getRight());
        if (Math.abs(leftHeight - rightHeight) == 2)
        {
            return balance(bTree);
        } else
        {
            bTree.resetHeight();
            return bTree;
        }
    }

    /**
     * The remove method removes a value from a AVL tree, and returns the removed node, and the
     * remaining tree is wrapped in a RemovalResult object.
     * @param bTree the binary search tree
     * @param x element the value to be removed
     * @return The root of the diminished AVL tree or null if x is not found in btree
     */
    private DD_RemovalResult remove(DD_Node bTree, int x) {
        if (bTree == null) return null; //if root is null there is nothing to remove
        if (x < bTree.value) {
            //remove x from the left subtree
            DD_RemovalResult removalResult = remove(bTree.left, x);
            if (removalResult == null) return null; //if nothing has been removed, return null
            bTree.left = removalResult.tree;
            removalResult.tree = bTree;
            return removalResult;
        }
        if (x > bTree.value){
            //remove x from the right subtree
            DD_RemovalResult removalResult = remove(bTree.right, x);
            if (removalResult == null) return null; //if nothing has been removed, return null
            bTree.right = removalResult.tree;
            removalResult.tree = bTree;
            return removalResult;
        }
        //Is it a leaf?
        if (bTree.right == null && bTree.left == null) return new DD_RemovalResult(bTree, null);
        //Is it a branch that has 2 leaves?
        if (bTree.right!= null && bTree.left != null) {
            DD_RemovalResult removalResult = removeLargest(bTree.left); //if it is, then remove the largest from left
            DD_Node newRoot = removalResult.node;
            newRoot.left = removalResult.tree;
            newRoot.right = bTree.right;
            //prepare the result to be returned
            bTree.left = null;
            bTree.right = null;
            return new DD_RemovalResult(bTree, newRoot);
        }
        DD_Node tree;
        if (bTree.left != null) tree = bTree.left; //if left subtree is not null, set left subtree the tree
        else tree = bTree.right; //else right subtree
        bTree.left = null; //facilitate garbage collection
        bTree.right = null;
        return new DD_RemovalResult(bTree, tree);
    }

    /**
     * The removalLargest method removes the largest leaf/branch from AVL tree.
     * @param btree the binary search tree
     * @return the result of removing the largest node
     */
    private DD_RemovalResult removeLargest(DD_Node btree){
        if (btree == null) return null; //if root is null, nothing to remove
        if (btree.right == null){ //if there is no right subtree,
            DD_Node tree = btree.left; //store left subtree in tree
            btree.left = null; //facilitate garbage collection
            return new DD_RemovalResult(btree,tree); //remove left subtree
        } else {
            DD_RemovalResult removalResult = removeLargest(btree.right); //else remove the largest from right subtree
            btree.right = removalResult.tree;
            removalResult.tree = btree;
            return removalResult;
        }
    }

    /**
     * The remove method removes a value from AVL tree
     * @param x the value to remove
     * @return true if x was removed, false if x is not found
     */
    boolean remove(int x){
        DD_RemovalResult removalResult = remove(root, x);
        if (removalResult == null) return false;
        else {
            root = removalResult.tree;
            return true;
        }
    }
 }
