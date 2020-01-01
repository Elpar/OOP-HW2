
package homework2;

/**
 * This class is a wrapper of the functional class nodeObj.
 */
public class Node<nodeObj extends Object> {

    //Representation Invariant:
    //functionalNode != null.

    //Abstraction Function:
    //This class is a wrapper for the node within a vertex, that contains the functionality of it.

    private final nodeObj functionalNode;

    /**
     * @requires node != null.
     * @modifes this.
     * @effects constructing a new node.
     */
    Node(nodeObj node) {
        if (node == null) throw new IllegalArgumentException("Given node is null");
        functionalNode = node;
        checkRep();
    }

    /**
     * @requires none.
     * @modifes none.
     * @effects returns the functional node.
     */
    public nodeObj getNode() {
        checkRep();
        return functionalNode;
    }

    private void checkRep() {
        assert functionalNode != null: "The functionalNode is null";
    }

}
