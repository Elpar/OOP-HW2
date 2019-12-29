
package HW2;
import java.util.Collection;
import java.util.HashMap;

/**
 * this class represents the topological vertex of a graph. Vertex consists of label, functional node and Maps of
 * children and parents whose keys are the edges labels
 * @param <labelObj>
 */
public class Vertex<labelObj extends Object> {
    private labelObj label;
    private Node node = null;
    private HashMap<labelObj, labelObj> parents;
    private HashMap<labelObj, labelObj> children;

    //Representation Invariant:
    //parents != null, children != null, label != null

    //Abstraction Function:
    //Each member from each of the lists (parents/children) represents a directed edge from/to a specific parent/child.
    //Label is the name of the node. node represents the functionality of the vertex.

    /**
     * @requires nodeLabel != null.
     * @modifies this.
     * @effects initializes a new node with label nodeLabel.
     */
    Vertex(labelObj nodeLabel) {
        label = nodeLabel;
        parents = new HashMap<labelObj,labelObj>();
        children = new  HashMap<labelObj,labelObj>();
        checkRep();
    }

    Vertex(labelObj nodeLabel, Node functionalNode) {
        label = nodeLabel;
        node = functionalNode;
        parents = new HashMap<labelObj,labelObj>();
        children = new  HashMap<labelObj,labelObj>();
        checkRep();
    }

    /**
     * @requires node2 != null.
     * @modifies none.
     * @effects checks if the label of this equals the label of node2.
     */
    public boolean equals(Vertex vertex2) { // TODO: check in lectures if this equals implementation corresponds with the hash!
        if (vertex2 == null || getClass() != vertex2.getClass()) return false;
        if (label.equals(vertex2.label)) return true;
        return false;
    }

    /**
     * @requires parentName != null & edgeLabel != null.
     * @modifies this.
     * @effects adds edge from parentName node to this node to the parents list of this.
     */
    public void addEdgeFromParent(labelObj parentName, labelObj edgeLabel) {
        checkRep();
        if(parentName == null || edgeLabel == null){
            throw new IllegalArgumentException("null parentName or edgelabel");
        }
        parents.put(edgeLabel, parentName);
        checkRep();
    }

    /**
     * @requires childName != null & edgeLabel != null.
     * @modifies this.
     * @effects adds edge from this node to childName node to the children list of this.
     */
    public void addEdgeToChild(labelObj childName, labelObj edgeLabel) {
        checkRep();
        if(childName == null || edgeLabel == null){
            throw new IllegalArgumentException("null childName or edgelabel");
        }
        children.put(edgeLabel, childName);
        checkRep();
    }

    /**
     * @requires edgeLabel != null.
     * @modifies none.
     * @effects returns false if an outgoing edge with the same label exists, true if not.
     */
    boolean isOutgoingEdgeExists(labelObj edgeLabel) {
        checkRep();
        return children.containsKey(edgeLabel);
    }

    /**
     * @requires edgeLabel != null.
     * @modifies none.
     * @effects returns false if an incoming edge with the same label exists, true if not.
     */
    boolean isIncomingEdgeExists(labelObj edgeLabel) {
        checkRep();
        return parents.containsKey(edgeLabel);
    }

    /**
     * @requires addEdgeToChild(this.label, edgeLabel)
     * @modifies none.
     * @effects returns a string of the child connected to this node by the edgeLabel edge.
     */
    public labelObj getChildByEdgeLabel(labelObj edgeLabel) {
        checkRep();
        labelObj childName;
        if(edgeLabel == null || !children.containsKey(edgeLabel)){
            throw new IllegalArgumentException("edgeLabel is null or is not an outgoing edge");
        }
        childName = children.get(edgeLabel);
        return childName;
    }

    /**
     * @requires addEdgeFromParent(this.label, edgeLabel)
     * @modifies none.
     * @effects returns a string of the parent connected to this node by the edgeLabel edge.
     */
    public labelObj getParentByEdgeLabel(labelObj edgeLabel) {
        checkRep();
        labelObj parentName;
        if (edgeLabel == null || !parents.containsKey(edgeLabel)) {
            throw new IllegalArgumentException("edgeLabel is null or is not an outgoing edge");
        }
        parentName = parents.get(edgeLabel);
        return parentName;
    }

    /**
     * @requires children != null
     * @modifies none.
     * @effects returns a space-separated string of the children of this, alphabetically ordered.
     */
    public Collection<labelObj> getChildrenList() {
        return children.values();
    }

    /**
     * @requires parents != null
     * @modifies none.
     * @effects returns a space-separated string of the parents of this, alphabetically ordered.
     */
    public Collection<labelObj> getParentsList() {
        return parents.values();
    }

    public Node getNode(){
        return node;
    }


    private void checkRep() { //TODO: FIX THIS! Incorrect checks for same parents/children!!!
        assert label != null : "Label is null";
        for (labelObj iter1 : parents.values()) {
            for (labelObj iter2 : parents.values()) {
                if (iter1 == iter2) continue;
                assert !iter1.equals(iter2) : "Found two parents that are the same in the node";
            }
            for (labelObj iter2 : children.values()) {
                if (iter1 == iter2) continue;
                assert !iter1.equals(iter2) : "Found two children that are the same in the node";
            }

        }
    }
}
