
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
        whiteNodes = new HashSet<Node>();
        blackNodes = new HashSet<Node>();
    }

    /**
     * @requires label != null , label doesn't already exists in blackNodes or whiteNodes lists.
     * @modifies blackNodes.
     * @effects adds a node with clone of label to the blackNodes hashset.
     */
    public void addBlackNode(obj label) {

    }

    /**
     * @requires label != null , label doesn't already exists in blackNodes or whiteNodes lists.
     * @modifies whiteNodes.
     * @effects adds a node with clone of label to the whiteNodes hashset.
     */
    public void addWhiteNode(obj label) {

    }

    /**
     * @requires childName node exists in one of the hashsets whiteNodes/blackNodes, and parentName exists in the other.
     *           childName != null, parentName != null, edgeLabel != null
     * @modifies parentName and childName nodes.
     * @effects adds an edge from parentName node to childName node and updates the nodes accordingly.
     */
    public void addEdge(obj parentName, obj childName, obj edgeLabel) {
       // look in both of the lists: whiteNodes or blackNodes, then get(childName).addEdgeFromParent();
       // look in both of the lists: whiteNodes or blackNodes, then get(parentName).addEdgeToChild();
    }

    /**
     * @requires none.
     * @modifies none.
     * @effects returns a space-separated string listing all the nodes in the blackNodes hashset in alphabetical order.
     */
    public String listBlackNodes() {
        checkRep();
        String blackString = "";
        List<String> blackList = new ArrayList<String>();
        Iterator<Node> iter = blackNodes.iterator();
        while (iter.hasNext()) {
            blackList.add(iter.next().toString());
        }
        Collections.sort(blackList);
        Iterator sortedIter = blackList.iterator();
        while (sortedIter.hasNext()) {
            blackString.concat(iter.next().toString());
            blackString.concat(" ");
        }
        checkRep();
        return blackString;
    }

    /**
     * @requires none.
     * @modifies none.
     * @effects returns a space-separated string listing all the nodes in the whiteNodes hashset in alphabetical order.
     */
    public String listWhiteNodes() {
        checkRep();
        String whiteString = "";
        List<String> whiteList = new ArrayList<String>();
        Iterator<Node> iter = whiteNodes.iterator();
        while (iter.hasNext()) {
            whiteList.add(iter.next().toString());
        }
        Collections.sort(whiteList);
        Iterator sortedIter = whiteList.iterator();
        while (sortedIter.hasNext()) {
            whiteString.concat(iter.next().toString());
            whiteString.concat(" ");
        }
        checkRep();
        return whiteString;
    }

    /**
     * @requires parentName != null & exists in one of the hashsets whiteNodes/blackNodes.
     * @modifies none.
     * @effects returns a space-separated string listing all the children of parentName node, alphabetically ordered.
     */
    public String listChildren(obj parentName) {
        checkRep();
        String childrenString = "";
        if (whiteNodes.contains(parentName)) {
            for (Node node : whiteNodes) {
                if (node.equals(parentName)) {
                    childrenString = node.getChildrenList();
                }
            }
        } else if (blackNodes.contains((parentName))) {
            for (Node node : blackNodes) {
                if (node.equals(parentName)) {
                    childrenString = node.getChildrenList();
                }
            }
        } else {
            return null;
        }
        checkRep();
        return childrenString;
    }

    /**
     * @requires childName != null & exists in one of the hashsets whiteNodes/blackNodes.
     * @modifies none.
     * @effects returns a space-separated string listing all the parents of childName node, alphabetically ordered.
     */
    public String listParents(obj childName) {
        checkRep();
        String parentString = "";
        if (whiteNodes.contains(childName)) {
            for (Node node : whiteNodes) {
                if (node.equals(childName)) {
                    parentString = node.getParentsList();
                }
            }
        } else if (blackNodes.contains((childName))) {
            for (Node node : blackNodes) {
                if (node.equals(childName)) {
                    parentString = node.getParentsList();
                }
            }
        } else {
            return null;
        }
        checkRep();
        return parentString;
    }

    /**
     * @requires addEdge(parentName, childName, edgeLabel) for some childName //TODO: make sure no need for !null checks
     * @modifies none.
     * @effects returns a string of the child connected to the parrentName node by the edgeLabel edge.
     */
    public String getChildByEdgeLabel(obj parentName, obj edgeLabel) {
        checkRep();
        String childrenString = "";
        if (whiteNodes.contains(parentName)) {
            for (Node node : whiteNodes) {
                if (node.equals(parentName)) {
                    childrenString = node.getChildByEdgeLabel(edgeLabel);
                }
            }
        } else if (blackNodes.contains((parentName))) {
            for (Node node : blackNodes) {
                if (node.equals(parentName)) {
                    childrenString = node.getChildByEdgeLabel(edgeLabel);
                }
            }
        } else {
            return null;
        }
        checkRep();
        return childrenString;
    }

    /**
     * @requires addEdge(childName, parentName, edgeLabel) for some parentName //TODO: make sure no need for !null checks
     * @modifies none.
     * @effects returns a string of the parent connected to the childName node by the edgeLabel edge.
     */
    public String getParentByEdgeLabel(obj childName, obj edgeLabel) {
        checkRep();
        String parentString = "";
        if (whiteNodes.contains(childName)) {
            for (Node node : whiteNodes) {
                if (node.equals(childName)) {
                    parentString = node.getParentByEdgeLabel(edgeLabel);
                }
            }
        } else if (blackNodes.contains((childName))) {
            for (Node node : blackNodes) {
                if (node.equals(childName)) {
                    parentString = node.getParentByEdgeLabel(edgeLabel);
                }
            }
        } else {
            checkRep();
            return null;
        }
        checkRep();
        return parentString;
    }

    private void checkRep() {
        //Check there are no duplicate nodes
        for (Node node : whiteNodes) {
            for (Node nodeFollow : whiteNodes) {
                if (node == nodeFollow) continue;
                assert (node.equals(nodeFollow)) : "Found two nodes with the same label amongst the white nodes";
            }
        }
        for (Node node : blackNodes) {
            for (Node nodeFollow : blackNodes) {
                if (node == nodeFollow) continue;
                assert (node.equals(nodeFollow)) : "Found two nodes with the same label amongst the black nodes";
            }
        }
        for (Node node : blackNodes) {
            for (Node nodeFollow : whiteNodes) {
                assert (node.equals(nodeFollow)) : "Found two nodes with the same label, one black and one white";
            }
        }
        //Check there are no same edges?
    }
}
