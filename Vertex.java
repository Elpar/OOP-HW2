
package HW2;
import java.util.Collection;
import java.util.HashMap;

/**
 * this class represents the topological vertex of a graph. Vertex consists of label, functional node and Maps of
 * children and parents whose keys are the edges labels
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
        if (nodeLabel == null) throw new IllegalArgumentException("The node label is illegal");
        label = nodeLabel;
        parents = new HashMap<labelObj,labelObj>();
        children = new  HashMap<labelObj,labelObj>();
        checkRep();
    }

    /**
     * @requires nodeLabel != null, functionalNode != null.
     * @modifies this.
     * @effects initializes a new node with label nodeLabel.
     */
    Vertex(labelObj nodeLabel, Node functionalNode) {
        if (nodeLabel == null) throw new IllegalArgumentException("The node label is illegal");
        if (functionalNode == null) throw new IllegalArgumentException("The functional node is illegal");
        label = nodeLabel;
        node = functionalNode;
        parents = new HashMap<labelObj,labelObj>();
        children = new  HashMap<labelObj,labelObj>();
        checkRep();
    }

    /**
     * @requires vertex2 != null & vertex2's class equals this's class.
     * @modifies none.
     * @effects checks if the label of this equals the label of vertex2.
     */
    public boolean equals(Vertex vertex2) {
        checkRep();
        if (vertex2 == null || getClass() != vertex2.getClass()) {
            checkRep();
            return false;
        }
        if (label.equals(vertex2.label)) {
            checkRep();
            return true;
        }
        checkRep();
        return false;
    }

    /**
     * @requires parentName != null & edgeLabel != null.
     * @modifies this.
     * @effects adds edge from parentName node to this node to the parents list of this.
     */
    public void addEdgeFromParent(labelObj parentName, labelObj edgeLabel) {
        checkRep();
        if (parentName == null) throw new IllegalArgumentException("The given parent name label is null");
        if (edgeLabel == null) throw new IllegalArgumentException("The given edge label is null");
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
        if (childName == null) throw new IllegalArgumentException("The given child name label is null");
        if (edgeLabel == null) throw new IllegalArgumentException("The given edge label is null");
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
        if (edgeLabel == null) throw new IllegalArgumentException("The given edge label is null");
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
        if (edgeLabel == null) throw new IllegalArgumentException("The given edge label is null");
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
        if (edgeLabel == null) throw new IllegalArgumentException("The given edge label is null");
        if (!children.containsKey(edgeLabel))
            throw new IllegalArgumentException("The given edge label is not an outgoing edge");
        childName = children.get(edgeLabel);
        checkRep();
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
        if (edgeLabel == null) throw new IllegalArgumentException("The given edge label is null");
        if (!parents.containsKey(edgeLabel))
            throw new IllegalArgumentException("The given edge label is not an incoming edge");
        parentName = parents.get(edgeLabel);
        return parentName;
    }

    /**
     * @requires children != null
     * @modifies none.
     * @effects returns a space-separated string of the children of this, alphabetically ordered.
     */
    public Collection<labelObj> getChildrenList() {
        checkRep();
        return children.values();
    }

    /**
     * @requires parents != null
     * @modifies none.
     * @effects returns a space-separated string of the parents of this, alphabetically ordered.
     */
    public Collection<labelObj> getParentsList() {
        checkRep();
        return parents.values();
    }

    /**
     * @requires none.
     * @modifies none.
     * @effects returns the node of this.
     */
    public Node getNode() {
        checkRep();
        return node;
    }

    private void checkRep() {
        assert label != null : "Label is null";
        assert children != null : "Children is null";
        assert parents != null : "Parents is null";
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
