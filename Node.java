
package homework2;

/**
 * this class is a wrapper of the functional class nodeObj.
 */
public class Node<nodeObj extends Object> {
    private final nodeObj functionalNode;

    /**
     * @requires node != null.
     * @modifes this.
     * @effects constructing a new node.
     */
    Node(nodeObj node) {
        functionalNode = node;
    }

    /**
     * @requires none.
     * @modifes none.
     * @effects returns the functional node.
     */
    public nodeObj getNode() {
        return functionalNode;
    }

}
