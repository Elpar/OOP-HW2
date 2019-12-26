
package homework2;

import javafx.util.Pair;
import java.util.Collections;


import java.util.*;

public class Node<obj extends Object> {
    obj label;
    private HashMap<obj, obj> parents;
    private HashMap<obj, obj> children;

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
        parents = new HashMap<obj,obj>();
        children = new  HashMap<obj,obj>();
        this.checkRep();
    }

    /**
     * @requires node2 != null.
     * @modifies none.
     * @effects checks if the label of this equals the label of node2.
     */
    public boolean equals(Node node2) { // TODO: check in lectures if this equals implementation corresponds with the hash!
        if (node2 == null || getClass() != node2.getClass()) return false;
        if (this.label.equals(node2.label)) return true;
        return false;
    }

    /**
     * @requires parentName != null & edgeLabel != null.
     * @modifies this.
     * @effects adds edge from parentName node to this node to the parents list of this.
     */
    public void addEdgeFromParent(obj parentName, obj edgeLabel) {
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
    public void addEdgeToChild(obj childName, obj edgeLabel) {
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
    boolean isOutgoingEdgeExists(obj edgeLabel) { //TODO: implement.
        this.checkRep();
        return this.children.containsKey(edgeLabel);
    }

    /**
     * @requires edgeLabel != null.
     * @modifies none.
     * @effects returns false if an incoming edge with the same label exists, true if not.
     */
    boolean isIncomingEdgeExists(obj edgeLabel) {
        this.checkRep();
        return this.parents.containsKey(edgeLabel);
    }

    /**
     * @requires addEdgeToChild(this.label, edgeLabel)  //TODO: make sure no need for !null checks
     * @modifies none.
     * @effects returns a string of the child connected to this node by the edgeLabel edge.
     */
    public String getChildByEdgeLabel(obj edgeLabel) { //TODO: use toString
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
    public String getParentByEdgeLabel(obj edgeLabel) { //TODO: use toString()
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
    public String getChildrenList() {
        String childrenString = "";
        List<String> childrenList = new ArrayList<String>();
        Iterator<obj> iter = children.values().iterator();
        while (iter.hasNext()) {
            childrenList.add(iter.next().toString());
        }
        Collections.sort(childrenList);
        Iterator sortedIter = childrenList.iterator();
        while (sortedIter.hasNext()) {
            childrenString.concat(iter.next().toString());
            childrenString.concat(" ");
        }
        return childrenString;
    }

    /**
     * @requires parents != null
     * @modifies none.
     * @effects returns a space-separated string of the parents of this, alphabetically ordered.
     */
    public String getParentsList() {
        String parentsString = "";
        List<String> parentsList = new ArrayList<String>();
        Iterator<obj> iter = parents.values().iterator();
        while (iter.hasNext()) {
            parentsList.add(iter.next().toString());
        }
        Collections.sort(parentsList);
        Iterator sortedIter = parentsList.iterator();
        while (sortedIter.hasNext()) {
            parentsString.concat(iter.next().toString());
            parentsString.concat(" ");
        }
        return parentsString;
    }


    private void checkRep() { //TODO: FIX THIS! Incorrect checks for same parents/children!!!
        assert label != null : "Label is null";
        for (Object iter1 : parents) {
            for (Object iter2 : parents) {
                if (iter1 == iter2) continue;
                assert iter1.equals(iter2) : "Found two parents that are the same in the node";
            }
            for (Object iter2 : children) {
                if (iter1 == iter2) continue;
                assert iter1.equals(iter2) : "Found two children that are the same in the node";
            }

        }
    }
}
