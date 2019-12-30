
package HW2;
import java.util.*;

public class BipartiteGraph<obj extends Object> {
    private HashMap<obj, Vertex<obj>> whiteNodes;
    private HashMap<obj, Vertex<obj>> blackNodes;

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
        whiteNodes = new HashMap<obj, HW2.Vertex<obj>>();
        blackNodes = new HashMap<obj, HW2.Vertex<obj>>();
        checkRep();
    }

    /**
     * @requires none.
     * @modifies none.
     * @effects returns the functional node labeled label's label (obj type), if doesn't exist returns null.
     */
    public Node getNodeByLabel(obj label) {
        checkRep();
        if (label == null) return null;
        for (Vertex iter : blackNodes.values()) {
            if (iter.equals(label)) return iter.getNode();
        }
        for (HashMap.Entry<obj,Vertex<obj>> iter : whiteNodes.entrySet()) {
            if (iter.getKey().equals(label)) return iter.getValue().getNode();
        }
        checkRep();
        return null; //label doesn't exist
    }

    /**
     * @requires label != null , label doesn't already exists in blackNodes or whiteNodes lists.
     * @modifies blackNodes.
     * @effects adds a node with clone of label to the blackNodes hashset.
     */
    public void addBlackNode(obj label) {
        checkRep();
        if (label == null)
            throw new IllegalArgumentException("can't initiate a black node with null or existing label");
        Vertex vert = new HW2.Vertex(label);
        blackNodes.put(label, vert);
        checkRep();
    }

    /**
     * @requires label != null , label doesn't already exists in blackNodes or whiteNodes lists.
     * @modifies blackNodes.
     * @effects adds a node with clone of label to the blackNodes hashset.
     */
    public void addBlackNode(obj label, Node node) {
        checkRep();
        if (label == null)
            throw new IllegalArgumentException("can't initiate a black node with null or existing label");
        Vertex vert = new HW2.Vertex(label, node);
        blackNodes.put(label, vert);
        checkRep();
    }

    /**
     * @requires label != null , label doesn't already exists in blackNodes or whiteNodes lists.
     * @modifies whiteNodes.
     * @effects adds a node with clone of label to the whiteNodes hashset.
     */
    public void addWhiteNode(obj label) {
        checkRep();
        if (label == null || blackNodes.containsKey(label))
            throw new IllegalArgumentException("can't initiate a white node with null or existing label");
        Vertex n = new HW2.Vertex(label);
        whiteNodes.put(label, n);
        checkRep();
    }

    /**
     * @requires label != null , label doesn't already exists in blackNodes or whiteNodes lists.
     * @modifies whiteNodes.
     * @effects adds a node with clone of label to the whiteNodes hashset.
     */
    public void addWhiteNode(obj label, Node node) {
        checkRep();
        if (label == null || blackNodes.containsKey(label))
            throw new IllegalArgumentException("can't initiate a white node with null or existing label");
        Vertex n = new HW2.Vertex(label, node);
        whiteNodes.put(label, n);
        checkRep();
    }

    /**
     * @requires childName node exists in one of the hashsets whiteNodes/blackNodes, and parentName exists in the other.
     *           childName != null, parentName != null, edgeLabel != null
     * @modifies parentName and childName nodes.
     * @effects adds an edge from parentName node to childName node and updates the nodes accordingly.
     */
    public void addEdge(obj parentName, obj childName, obj edgeLabel) {
        checkRep();
        if (parentName == null || childName == null || edgeLabel == null)
            throw new IllegalArgumentException("null argument detected when tried to add an edge");
        if (whiteNodes.containsKey(parentName) && blackNodes.containsKey(childName)) {
            whiteNodes.get(parentName).addEdgeToChild(childName, edgeLabel);
            blackNodes.get(childName).addEdgeFromParent(parentName, edgeLabel);
        }
        else if (blackNodes.containsKey(parentName) && whiteNodes.containsKey(childName)) {
            blackNodes.get(parentName).addEdgeToChild(childName, edgeLabel);
            whiteNodes.get(childName).addEdgeFromParent(parentName, edgeLabel);
        }
        else {
            throw new IllegalArgumentException("parent or child (or both) do not exist, or exist with the same color");
        }
        checkRep();
    }

    /**
     * @requires none.
     * @modifies none.
     * @effects returns a collection of obj listing all the nodes in the blackNodes hashset.
     */
    public Collection<obj> listBlackNodes() {
        checkRep();
        return blackNodes.keySet();
    }

    /**
     * @requires none.
     * @modifies none.
     * @effects returns a collection of obj listing all the nodes in the whitenodes hashset.
     */
    public Collection<obj> listWhiteNodes() {
        checkRep();
        return whiteNodes.keySet();
    }

    /**
     * @requires parentName != null & exists in one of the hashsets whiteNodes/blackNodes.
     * @modifies none.
     * @effects returns a collection listing all the children of parentName node.
     */
    public Collection<obj> listChildren(obj parentName) {
        checkRep();
        if (whiteNodes.containsKey(parentName)) {
            for (obj nodeLabel : whiteNodes.keySet()) {
                if (nodeLabel.equals(parentName)) {
                    checkRep();
                    return whiteNodes.get(nodeLabel).getChildrenList();
                }
            }
        } else if (blackNodes.keySet().contains(parentName)) {
            for (obj nodeLabel : blackNodes.keySet()) {
                if (nodeLabel.equals(parentName)) {
                    checkRep();
                    return blackNodes.get(nodeLabel).getChildrenList();
                }
            }
        }
        checkRep();
        return null;
    }

    /**
     * @requires childName != null & exists in one of the hashsets whiteNodes/blackNodes.
     * @modifies none.
     * @effects returns a collection listing all the parents of childName node.
     */
    public Collection<obj> listParents(obj childName) {
        checkRep();
        if (whiteNodes.keySet().contains(childName)) {
            for (obj nodeLabel : whiteNodes.keySet()) {
                if (nodeLabel.equals(childName)) {
                    checkRep();
                    return whiteNodes.get(nodeLabel).getParentsList();
                }
            }
        } else if (blackNodes.keySet().contains((childName))) {
            for (obj nodeLabel : blackNodes.keySet()) {
                if (nodeLabel.equals(childName)) {
                    checkRep();
                    return blackNodes.get(nodeLabel).getParentsList();
                }
            }
        }
        checkRep();
        return null;
    }

    /**
     * @requires addEdge(parentName, childName, edgeLabel) for some childName
     * @modifies none.
     * @effects returns a label (an obj) of the child connected to the parrentName node by the edgeLabel edge.
     */
    public obj getChildByEdgeLabel(obj parentName, obj edgeLabel) {
        checkRep();
        obj childName;
        if (parentName == null || edgeLabel == null)
            throw new IllegalArgumentException("parentName or edgeLabel are null in getChildByEdge");
        if (whiteNodes.containsKey(parentName))
            childName = whiteNodes.get(parentName).getChildByEdgeLabel(edgeLabel);
        else if (blackNodes.containsKey(parentName))
            childName = blackNodes.get(parentName).getChildByEdgeLabel(edgeLabel);
        else throw new IllegalArgumentException("tried to get to non existing node in getChildByEdgeLabel");
        checkRep();
        return childName;
    }

    /**
     * @requires addEdge(childName, parentName, edgeLabel) for some parentName
     * @modifies none.
     * @effects returns a label (an obj) of the parent connected to the childName node by the edgeLabel edge.
     */
    public obj getParentByEdgeLabel(obj childName, obj edgeLabel) {
        checkRep();
        obj parentName;
        if (childName == null || edgeLabel == null)
            throw new IllegalArgumentException("childName or edgeLabel are null in getChildByEdge");
        if (whiteNodes.containsKey(childName))
            parentName = whiteNodes.get(childName).getParentByEdgeLabel(edgeLabel);
        else if (blackNodes.containsKey(childName))
            parentName = blackNodes.get(childName).getParentByEdgeLabel(edgeLabel);
        else throw new IllegalArgumentException("tried to get to non existing node in getParentByEdgeLabel");
        checkRep();
        return parentName;
    }

    private void checkRep() {
        //Check there are no duplicate nodes
        for (obj node : whiteNodes.keySet()) {
            for (obj nodeFollow : whiteNodes.keySet()) {
                if (node == nodeFollow) continue;
                assert (!node.equals(nodeFollow)) : "Found two nodes with the same label amongst the white nodes";
            }
        }
        for (obj node : blackNodes.keySet()) {
            for (obj nodeFollow : blackNodes.keySet()) {
                if (node == nodeFollow) continue;
                assert (!node.equals(nodeFollow)) : "Found two nodes with the same label amongst the black nodes";
            }
        }
        for (obj node : blackNodes.keySet()) {
           assert (!whiteNodes.keySet().contains(node)) :
                   "Found two nodes with the same label, one black and one white";
        }
    }
}
