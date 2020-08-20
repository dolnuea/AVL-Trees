/**
 * @author Dolunay Dagci
 * Assignment: Ch22 Program Challenge 1
 * Due Date: 6/12/2019
 DD_AVLNode extends Node from which it inherits
 the value field, and also the left and right links.
 */

class DD_AVLNode extends DD_Node
{
    int height;

    // These methods cast the inhertied NODE links
    // DD_AVLNode links.
    DD_AVLNode getLeft(){ return (DD_AVLNode) left; }
    DD_AVLNode getRight(){ return (DD_AVLNode) right; }

     DD_AVLNode(int value)
    {
        // Call the other (sister) constructor.
        this(value, null, null);
    }

    DD_AVLNode(int val, DD_AVLNode left1, DD_AVLNode right1)
    {
        // Pass the parameters to the superclass constructor.
        super(val, left1, right1);
        height = 0;
    }

    /**
     The resetHeight methods recomputes height if the
     left or right subtrees have changed.
     */
    void resetHeight()
    {
        int leftHeight = -1;
        int rightHeight = -1;
        if (left != null)
        {
            leftHeight = getLeft().height;
        }
        if (right != null)
        {
            rightHeight = getRight().height;
        }
        height = 1 + Math.max(leftHeight, rightHeight);
    }
}
