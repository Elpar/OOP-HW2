
package homework2;

import javafx.util.Pair;
import java.util.ArrayList;

public class Node<obj extends Object> {
    obj label;
    private ArrayList parents;
    private ArrayList children;

    //Representation Invariant:
    //parents != null, children != null, label != null

    //Abstraction Function:
    //Each member from each of the lists (parents/children) represents a directed edge from/to a specific parent/child.
    //Label is the name of the node.

    /**
     * @requires nodeLabel != null.
     * @modifies this.
     * @effects initializes a new node with label nodeLabel.
     */
    Node(obj nodeLabel) {
        label = nodeLabel;
        parents = new ArrayList<Pair<obj,obj>>();
        children = new ArrayList<Pair<obj,obj>>();
    }

    /**
     * @requires parentName != null & edgeLabel != null.
     * @modifies this.
     * @effects adds edge from parentName node to this node to the parents list of this.
     */
    public void addEdgeFromParent(obj parentName, obj edgeLabel) {

    }

    /**
     * @requires childName != null & edgeLabel != null.
     * @modifies this.
     * @effects adds edge from this node to childName node to the children list of this.
     */
    public void addEdgeToChild(obj childName, obj edgeLabel) {

    }

    /**
     * @requires edgeLabel != null.
     * @modifies none.
     * @effects returns false if an outgoing edge with the same label exists, true if not.
     */
    boolean isOutgoingEdgeExists(obj edgeLabel) { //TODO: implement.
        return false;
    }

    /**
     * @requires edgeLabel != null.
     * @modifies none.
     * @effects returns false if an incoming edge with the same label exists, true if not.
     */
    boolean isIncomingEdgeExists(obj edgeLabel) { //TODO: implement.
        return false;
    }

    /**
     * @requires addEdgeToChild(childName, edgeLabel) for some childName //TODO: make sure no need for !null checks
     * @modifies none.
     * @effects returns a string of the child connected to this node by the edgeLabel edge.
     */
    public String getChildByEdgeLabel(obj edgeLabel) { //TODO: use toString()
        String string = "";
        return string;
    }

    /**
     * @requires addEdgeFromParent(parentName, edgeLabel) for some parentName //TODO: make sure no need for !null checks
     * @modifies none.
     * @effects returns a string of the parent connected to this node by the edgeLabel edge.
     */
    public String getParentByEdgeLabel(obj edgeLabel) { //TODO: use toString()
        String string = "";
        return string;
    }

}
