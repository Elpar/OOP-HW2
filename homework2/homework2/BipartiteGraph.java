
package homework2;
import java.util.*;

public class BipartiteGraph<obj extends Object> {
    private HashMap<obj, Vertex> whiteNodes;
    private HashMap<obj, Vertex> blackNodes;

    //Representation Invariant:
    //whiteNodes != null & blackNodes != null and do not contain 2 (or more) of the same node in both sets combined.

    //Abstraction Function:
    //whiteNodes represents all the white nodes in the bipartite graph, while blackNodes represents all the black nodes
    //in the graph. Edges are represented in the nodes in lists of pair<edge,node>, one for parents and one for
    //children.

    /**
     * @requires none.
     * @modifies none.
     * @effects create a new bipartite graph with the label graphLabel and initialized empty nodes lists.
     */
    BipartiteGraph() {
        whiteNodes = new HashMap<obj, homework2.Vertex>();
        blackNodes = new HashMap<obj, homework2.Vertex>();
    }

    /**
     * @requires none.
     * @modifies none.
     * @effects returns the node labeled label's label (obj type), if doesn't exist returns null.
     */
    public Node getNodeByLabel(obj label) {
        checkRep();
        if (label == null) return null;

        for (Vertex iter : blackNodes.values()) {
            if (iter.equals(label)) return iter.getNode(); //TODO: Think of changing so each node contains its object, in order for the equal to be on the name only which will be the identifier
        }
        for (HashMap.Entry<obj,Vertex> iter : whiteNodes.entrySet()) {
            if (iter.getKey().equals(label)) return iter.getValue().getNode();
        }
        checkRep();
        return null; //label doesn't exist
    }

    /**
     * @requires label != null , label doesn't already exists in blackVertexs or whiteNodes lists.
     * @modifies blackNodes.
     * @effects adds a node with clone of label to the blackNodes hashset.
     */
    public void addBlackNode(obj label) {
        checkRep();
        if(label == null){
            throw new IllegalArgumentException("can't initiate a black node with null or existing label");
        }
        Vertex n = new homework2.Vertex(label);
        this.blackNodes.put(label, n);
        checkRep();
    }

    /**
     * @requires label != null , label doesn't already exists in blackVertexs or whiteNodes lists.
     * @modifies blackNodes.
     * @effects adds a node with clone of label to the blackNodes hashset.
     */
    public void addBlackNode(obj label, Node node) {
        checkRep();
        if(label == null){
            throw new IllegalArgumentException("can't initiate a black node with null or existing label");
        }
        Vertex n = new homework2.Vertex(label, node);
        this.blackNodes.put(label, n);
        checkRep();
    }

    /**
     * @requires label != null , label doesn't already exists in blackNodes or whiteNodes lists.
     * @modifies whiteNodes.
     * @effects adds a node with clone of label to the whiteNodes hashset.
     */
    public void addWhiteNode(obj label) {
        this.checkRep();
        if(label == null || this.blackNodes.containsKey(label)){
            throw new IllegalArgumentException("can't initiate a white node with null or existing label");
        }
        Vertex n = new homework2.Vertex(label);
        this.blackNodes.put(label, n);
        this.checkRep();
    }

    /**
     * @requires label != null , label doesn't already exists in blackNodes or whiteNodes lists.
     * @modifies whiteNodes.
     * @effects adds a node with clone of label to the whiteNodes hashset.
     */
    public void addWhiteNode(obj label, Node node) {
        this.checkRep();
        if(label == null || this.blackNodes.containsKey(label)){
            throw new IllegalArgumentException("can't initiate a white node with null or existing label");
        }
        Vertex n = new homework2.Vertex(label, node);
        this.blackNodes.put(label, n);
        this.checkRep();
    }

    /**
     * @requires childName node exists in one of the hashsets whiteNodes/blackNodes, and parentName exists in the other.
     *           childName != null, parentName != null, edgeLabel != null
     * @modifies parentName and childName nodes.
     * @effects adds an edge from parentName node to childName node and updates the nodes accordingly.
     */
    public void addEdge(obj parentName, obj childName, obj edgeLabel) {
        this.checkRep();
        if(parentName == null || childName == null || edgeLabel == null){
            throw new IllegalArgumentException("null argument detected when tried to add an edge");
        }
        if(this.whiteNodes.containsKey(parentName) && this.blackNodes.containsKey(childName)){
            whiteNodes.get(parentName).addEdgeToChild(childName, edgeLabel);
            blackNodes.get(childName).addEdgeFromParent(parentName, edgeLabel);
        }
        else if(this.blackNodes.containsKey(parentName) && this.whiteNodes.containsKey(childName)){
            blackNodes.get(parentName).addEdgeToChild(childName, edgeLabel);
            whiteNodes.get(childName).addEdgeFromParent(parentName, edgeLabel);
        }
        else {
            throw new IllegalArgumentException("parent or child (or both) do not exist, or exist with the same color");
        }
    }

    /**
     * @requires none.
     * @modifies none.
     * @effects returns a space-separated string listing all the nodes in the blackNodes hashset in alphabetical order.
     */
    public Collection<obj> listBlackNodes() {
        checkRep();
        return  blackNodes.keySet();
    }

    /**
     * @requires none.
     * @modifies none.
     * @effects returns a space-separated string listing all the nodes in the whiteNodes hashset in alphabetical order.
     */
    public Collection<obj> listWhiteNodes() {
        checkRep();
        return whiteNodes.keySet();
    }

    /**
     * @requires parentName != null & exists in one of the hashsets whiteNodes/blackNodes.
     * @modifies none.
     * @effects returns a space-separated string listing all the children of parentName node, alphabetically ordered.
     */
    public Collection<obj> listChildren(obj parentName) {
        checkRep();
        if (whiteNodes.containsKey(parentName)) {
            for (Vertex node : whiteNodes.values()) {
                if (node.equals(parentName)) {
                    return node.getChildrenList();
                }
            }
        } else if (blackNodes.keySet().contains(parentName)) {
            for (Vertex node : blackNodes.values()) {
                if (node.equals(parentName)) {
                    return node.getChildrenList();
                }
            }
        }
        return null;
    }

    /**
     * @requires childName != null & exists in one of the hashsets whiteNodes/blackNodes.
     * @modifies none.
     * @effects returns a space-separated string listing all the parents of childName node, alphabetically ordered.
     */
    public Collection<obj> listParents(obj childName) {
        checkRep();
        String parentString = "";
        if (whiteNodes.keySet().contains(childName)) {
            for (Vertex node : whiteNodes.values()) {
                if (node.equals(childName)) {
                    return node.getParentsList();
                }
            }
        } else if (blackNodes.keySet().contains((childName))) {
            for (Vertex node : blackNodes.values()) {
                if (node.equals(childName)) {
                    return node.getParentsList();
                }
            }
        }
        return null;
    }

    /**
     * @requires addEdge(parentName, childName, edgeLabel) for some childName //TODO: make sure no need for !null checks
     * @modifies none.
     * @effects returns a string of the child connected to the parrentName node by the edgeLabel edge.
     */
    public String getChildByEdgeLabel(obj parentName, obj edgeLabel) {
        String childName = "";
        if(parentName == null || edgeLabel == null){
            throw new IllegalArgumentException("parentName or edgeLabel are null in getChildByEdge");
        }
        if(whiteNodes.containsKey(parentName)){
            childName += whiteNodes.get(parentName).getChildByEdgeLabel(edgeLabel);
        }
        else if(blackNodes.containsKey(parentName)){
            childName += blackNodes.get(parentName).getChildByEdgeLabel(edgeLabel);
        }
        else {
            throw new IllegalArgumentException("tried to get to non existing node in getChildByEdgeLabel");
        }
        return childName;
    }

    /**
     * @requires addEdge(childName, parentName, edgeLabel) for some parentName //TODO: make sure no need for !null checks
     * @modifies none.
     * @effects returns a string of the parent connected to the childName node by the edgeLabel edge.
     */
    public String getParentByEdgeLabel(obj childName, obj edgeLabel) {
        String parentName = "";
        if(childName == null || edgeLabel == null){
            throw new IllegalArgumentException("childName or edgeLabel are null in getChildByEdge");
        }
        if(whiteNodes.containsKey(childName)){
            parentName += whiteNodes.get(childName).getChildByEdgeLabel(edgeLabel);
        }
        if(blackNodes.containsKey(childName)){
            parentName += blackNodes.get(childName).getChildByEdgeLabel(edgeLabel);
        }
        else {
            throw new IllegalArgumentException("tried to get to non existing node in getParentByEdgeLabel");
        }
        return parentName;
    }

    private void checkRep() {
        //Check there are no duplicate nodes
        for (Vertex node : whiteNodes.values()) {
            for (Vertex nodeFollow : whiteNodes.values()) {
                if (node == nodeFollow) continue;
                assert (node.equals(nodeFollow)) : "Found two nodes with the same label amongst the white nodes";
            }
        }
        for (Vertex node : blackNodes.values()) {
            for (Vertex nodeFollow : blackNodes.values()) {
                if (node == nodeFollow) continue;
                assert (node.equals(nodeFollow)) : "Found two nodes with the same label amongst the black nodes";
            }
        }
        for (Vertex node : blackNodes.values()) {
            for (Vertex nodeFollow : whiteNodes.values()) {
                assert (node.equals(nodeFollow)) : "Found two nodes with the same label, one black and one white";
            }
        }
        //Check there are no same edges?
    }
}
