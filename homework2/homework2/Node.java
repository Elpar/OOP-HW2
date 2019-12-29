package homework2;

/**
 * this class is a wrapper of the functional class nodeObj.
 * @param <nodeObj>
 */
public class Node<nodeObj extends Object> {
    private nodeObj FunctionalNode;

    Node(nodeObj node_){
        FunctionalNode = node_;
    }

    public nodeObj getNode(){
        return this.FunctionalNode;
    }

}
