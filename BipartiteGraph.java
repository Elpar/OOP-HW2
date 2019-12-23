
package homework2;

import java.util.HashSet;

public class BipartiteGraph<obj extends Object> {
    private HashSet whiteNodes;
    private HashSet blackNodes;

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
        whiteNodes = new HashSet<obj>();
        blackNodes = new HashSet<obj>();
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
     * @effects returns a space-separated string listing all the nodes in the blackNodes hashset.
     */
    public String listBlackNodes() { //TODO: THIS IS NOT DONE.
        String string = "";
        return string;
    }

    /**
     * @requires none.
     * @modifies none.
     * @effects returns a space-separated string listing all the nodes in the whiteNodes hashset.
     */
    public String listWhiteNodes() { //TODO: THIS IS NOT DONE.
        String string = "";
        return string;
    }

    /**
     * @requires parentName != null & exists in one of the hashsets whiteNodes/blackNodes.
     * @modifies none.
     * @effects returns a space-separated string listing all the children of parentName node, alphabetically ordered.
     */
    public String listChildren(obj parentName) {
        String string = "";
        return string;
    }

    /**
     * @requires childName != null & exists in one of the hashsets whiteNodes/blackNodes.
     * @modifies none.
     * @effects returns a space-separated string listing all the parents of childName node, alphabetically ordered.
     */
    public String listParents(obj childName) {
        String string = "";
        return string;
    }

    /**
     * @requires addEdge(parentName, childName, edgeLabel) for some childName //TODO: make sure no need for !null checks
     * @modifies none.
     * @effects returns a string of the child connected to the parrentName node by the edgeLabel edge.
     */
    public String getChildByEdgeLabel(obj parentName, obj edgeLabel) { //TODO: use toString()
        String string = "";
        return string;
    }

    /**
     * @requires addEdge(childName, parentName, edgeLabel) for some parentName //TODO: make sure no need for !null checks
     * @modifies none.
     * @effects returns a string of the parent connected to the childName node by the edgeLabel edge.
     */
    public String getParentByEdgeLabel(obj childName, obj edgeLabel) { //TODO: use toString()
        String string = "";
        return string;
    }

    private void checkRep() { //TODO: add all checks

    }
}
