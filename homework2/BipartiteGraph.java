
package homework2;

import java.util.*;

public class BipartiteGraph<obj extends Object> {
    private HashSet<Node> whiteNodes;
    private HashSet<Node> blackNodes;

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
        whiteNodes = new HashMap<obj, homework2.Node>();
        blackNodes = new HashMap<obj, homework2.Node>();
    }

    /**
     * @requires label != null , label doesn't already exists in blackNodes or whiteNodes lists.
     * @modifies blackNodes.
     * @effects adds a node with clone of label to the blackNodes hashset.
     */
    public void addBlackNode(obj label) {
        checkRep();
        if(label == null){
            throw new IllegalArgumentException("can't initiate a black node with null or existing label");
        }
        Node n = new homework2.Node(label);
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
        Node n = new homework2.Node(label);
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
    public String listBlackNodes() {
        String blackString = "";
        List<String> blackList = new ArrayList<String>();
        Iterator<obj> iter = blackNodes.keySet().iterator();
        while (iter.hasNext()) {
            blackList.add(iter.next().toString());
        }
        Collections.sort(blackList);
        Iterator sortedIter = blackList.iterator();
        while (sortedIter.hasNext()) {
            blackString.concat(iter.next().toString());
            blackString.concat(" ");
        }
        return blackString;
    }

    /**
     * @requires none.
     * @modifies none.
     * @effects returns a space-separated string listing all the nodes in the whiteNodes hashset in alphabetical order.
     */
    public String listWhiteNodes() {
        String whiteString = "";
        List<String> whiteList = new ArrayList<String>();
        Iterator<obj> iter = whiteNodes.keySet().iterator();
        while (iter.hasNext()) {
            whiteList.add(iter.next().toString());
        }
        Collections.sort(whiteList);
        Iterator sortedIter = whiteList.iterator();
        while (sortedIter.hasNext()) {
            whiteString.concat(iter.next().toString());
            whiteString.concat(" ");
        }
        return whiteString;
    }

    /**
     * @requires parentName != null & exists in one of the hashsets whiteNodes/blackNodes.
     * @modifies none.
     * @effects returns a space-separated string listing all the children of parentName node, alphabetically ordered.
     */
    public String listChildren(obj parentName) {
        String childrenString = "";
        if (whiteNodes.containsKey(parentName)) {
            for (Node node : whiteNodes.values()) {
                if (node.equals(parentName)) {
                    childrenString = node.getChildrenList();
                }
            }
        } else if (blackNodes.keySet().contains(parentName)) {
            for (Node node : blackNodes.values()) {
                if (node.equals(parentName)) {
                    childrenString = node.getChildrenList();
                }
            }
        } else {
            return null;
        }
        return childrenString;
    }

    /**
     * @requires childName != null & exists in one of the hashsets whiteNodes/blackNodes.
     * @modifies none.
     * @effects returns a space-separated string listing all the parents of childName node, alphabetically ordered.
     */
    public String listParents(obj childName) {
        String parentString = "";
        if (whiteNodes.keySet().contains(childName)) {
            for (Node node : whiteNodes.values()) {
                if (node.equals(childName)) {
                    parentString = node.getParentsList();
                }
            }
        } else if (blackNodes.keySet().contains((childName))) {
            for (Node node : blackNodes.values()) {
                if (node.equals(childName)) {
                    parentString = node.getParentsList();
                }
            }
        } else {
            return null;
        }
        return parentString;
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

    private void checkRep() { //TODO: add all checks

    }
}
