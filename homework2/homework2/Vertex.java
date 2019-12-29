
package homework2;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.util.Collections;


import java.util.*;

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
        this.checkRep();
    }

    Vertex(labelObj nodeLabel, Node node_) {
        label = nodeLabel;
        node = node_;
        parents = new HashMap<labelObj,labelObj>();
        children = new  HashMap<labelObj,labelObj>();
        this.checkRep();
    }

    /**
     * @requires node2 != null.
     * @modifies none.
     * @effects checks if the label of this equals the label of node2.
     */
    public boolean equals(Vertex vertex2) { // TODO: check in lectures if this equals implementation corresponds with the hash!
        if (vertex2 == null || getClass() != vertex2.getClass()) return false;
        if (this.label.equals(vertex2.label)) return true;
        return false;
    }

    /**
     * @requires parentName != null & edgeLabel != null.
     * @modifies this.
     * @effects adds edge from parentName node to this node to the parents list of this.
     */
    public void addEdgeFromParent(labelObj parentName, labelObj edgeLabel) {
        this.checkRep();
        if(parentName == null || edgeLabel == null){
            throw new IllegalArgumentException("null parentName or edgelabel");
        }
        this.parents.put(edgeLabel, parentName);
        this.checkRep();
    }

    /**
     * @requires childName != null & edgeLabel != null.
     * @modifies this.
     * @effects adds edge from this node to childName node to the children list of this.
     */
    public void addEdgeToChild(labelObj childName, labelObj edgeLabel) {
        this.checkRep();
        if(childName == null || edgeLabel == null){
            throw new IllegalArgumentException("null childName or edgelabel");
        }
        this.parents.put(edgeLabel, childName);
        this.checkRep();
    }

    /**
     * @requires edgeLabel != null.
     * @modifies none.
     * @effects returns false if an outgoing edge with the same label exists, true if not.
     */
    boolean isOutgoingEdgeExists(labelObj edgeLabel) { //TODO: implement.
        this.checkRep();
        return this.children.containsKey(edgeLabel);
    }

    /**
     * @requires edgeLabel != null.
     * @modifies none.
     * @effects returns false if an incoming edge with the same label exists, true if not.
     */
    boolean isIncomingEdgeExists(labelObj edgeLabel) {
        this.checkRep();
        return this.parents.containsKey(edgeLabel);
    }

    /**
     * @requires addEdgeToChild(this.label, edgeLabel)  //TODO: make sure no need for !null checks
     * @modifies none.
     * @effects returns a string of the child connected to this node by the edgeLabel edge.
     */
    public String getChildByEdgeLabel(labelObj edgeLabel) { //TODO: use toString
        this.checkRep();
        String childName = "";
        if(edgeLabel == null || !children.containsKey(edgeLabel)){
            throw new IllegalArgumentException("edgeLabel is null or is not an outgoing edge");
        }
        childName += children.get(edgeLabel).toString();
        return childName;
    }

    /**
     * @requires addEdgeFromParent(this.label, edgeLabel)  //TODO: make sure no need for !null checks
     * @modifies none.
     * @effects returns a string of the parent connected to this node by the edgeLabel edge.
     */
    public String getParentByEdgeLabel(labelObj edgeLabel) { //TODO: use toString()
        this.checkRep();
        String parentName = "";
        if(edgeLabel == null || !children.containsKey(edgeLabel)){
            throw new IllegalArgumentException("edgeLabel is null or is not an outgoing edge");
        }
        parentName += children.get(edgeLabel).toString();
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
        return this.node;
    }


    private void checkRep() { //TODO: FIX THIS! Incorrect checks for same parents/children!!!
        assert label != null : "Label is null";
        for (Object iter1 : this.parents.values()) {
            for (Object iter2 : this.parents.values()) {
                if (iter1 == iter2) continue;
                assert iter1.equals(iter2) : "Found two parents that are the same in the node";
            }
            for (Object iter2 : this.children.values()) {
                if (iter1 == iter2) continue;
                assert iter1.equals(iter2) : "Found two children that are the same in the node";
            }

        }
    }
}
