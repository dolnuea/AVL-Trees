/**
 * @author Dolunay Dagci
 * Assignment: Ch22 Program Challenge 1
 * Due Date: 6/12/2019
 DD_Node class.
 */
 class DD_Node
{
    int value;
    DD_Node left, right;

    // Constructor for leaf nodes.
    DD_Node(int val)
    {
        value = val;
        left = null;
        right = null;
    }

    // Constructor for non-leaf nodes.
    DD_Node(int val, DD_Node leftChild, DD_Node rightChild)
    {
        value = val;
        left = leftChild;
        right = rightChild;
    }
}
