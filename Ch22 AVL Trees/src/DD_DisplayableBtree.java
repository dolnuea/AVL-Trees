import javafx.scene.layout.Pane;
import javax.swing.JPanel;

/**
 * @author Dolunay Dagci
 * Assignment: Ch22 Program Challenge 1
 * Due Date: 6/12/2019
 * This class displays a binary tree
 */
public class DD_DisplayableBtree extends DD_BinaryTree
{
    private DD_BinaryTree tree;
    public DD_DisplayableBtree(DD_BinaryTree t)
    {
        tree = t;
    }

    @Override
    public boolean isEmpty()
    {
        return tree.isEmpty();
    }

    // Return a view of the binary tree suitable for
    // display in Swing.
    public JPanel getSwingView()
    {
        return  DD_SwingNodeUtilities.getView(tree.root);
    }

//    // Return a view of the binary tree suitable for
//    // display in JavaFX
//    public Pane getJavaFXView()
//    {
//        return  DD_JavaFXNodeUtilities.getView(tree.root);
//    }
}
