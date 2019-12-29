
package HW2;
import java.util.*;

/**
 * This class implements a testing driver for BipartiteGraph. The driver
 * manages BipartiteGraphs whose nodes and edges are Strings.
 */
public class BipartiteGraphTestDriver {

    private Map<String, BipartiteGraph<String>> graphs;

    /**
     * @modifies this
     * @effects Constructs a new test driver.
     */
    public BipartiteGraphTestDriver () {
       graphs = new HashMap<String, BipartiteGraph<String>>();
    }

    /**
     * @requires graphName != null
     * @modifies this
     * @effects Creates a new graph named graphName. The graph is initially
     * 			empty.
     */
    public void createGraph(String graphName) {
        BipartiteGraph bg = new BipartiteGraph<String>();
        graphs.put(graphName, bg);
    }

    /**
     * @requires createGraph(graphName)
     *           && nodeName != null
     *           && neither addBlackNode(graphName,nodeName) 
     *                  nor addWhiteNode(graphName,nodeName)
     *                      has already been called on this
     * @modifies graph named graphName
     * @effects Adds a black node represented by the String nodeName to the
     * 			graph named graphName.
     */
    public void addBlackNode(String graphName, String nodeName) {
        graphs.get(graphName).addBlackNode(nodeName);
    }

    /**
     * @requires createGraph(graphName)
     *           && nodeName != null
     *           && neither addBlackNode(graphName,nodeName) 
     *                  nor addWhiteNode(graphName,nodeName)
     *                      has already been called on this
     * @modifies graph named graphName
     * @effects Adds a white node represented by the String nodeName to the
     * 			graph named graphName.
     */
    public void addWhiteNode(String graphName, String nodeName) {
        graphs.get(graphName).addWhiteNode(nodeName);
    }

    /**
     * @requires createGraph(graphName)
     *           && ((addBlackNode(parentName) && addWhiteNode(childName))
     *              || (addWhiteNode(parentName) && addBlackNode(childName)))
     *           && edgeLabel != null
     *           && node named parentName has no other outgoing edge labeled
     * 				edgeLabel
     *           && node named childName has no other incoming edge labeled
     * 				edgeLabel
     * @modifies graph named graphName
     * @effects Adds an edge from the node parentName to the node childName
     * 			in the graph graphName. The new edge's label is the String
     * 			edgeLabel.
     */
    public void addEdge(String graphName,
    					String parentName, String childName, 
                        String edgeLabel) {
    	graphs.get(graphName).addEdge(parentName, childName, edgeLabel);
    }

    /**
     * @requires createGraph(graphName)
     * @return a space-separated list of the names of all the black nodes
     * 		   in the graph graphName, in alphabetical order.
     */
    public String listBlackNodes(String graphName) {
        Collection<String> blackNodes = graphs.get(graphName).listBlackNodes();
        String blackString = "";
        List<String> blackList = new ArrayList<String>();
        Iterator<String> iter = blackNodes.iterator();
        while (iter.hasNext()) {
            blackList.add(iter.next());
        }
        Collections.sort(blackList);
        Iterator<String> sortedIter = blackList.iterator();
        while (sortedIter.hasNext()) {
            blackString += sortedIter.next() ;
            if (sortedIter.hasNext()) {
                blackString += " ";
            }
        }
        return blackString;
    }

    /**
     * @requires createGraph(graphName)
     * @return a space-separated list of the names of all the white nodes
     * 		   in the graph graphName, in alphabetical order.
     */
    public String listWhiteNodes(String graphName) {
        Collection<String> whiteNodes = graphs.get(graphName).listWhiteNodes();
        String whiteString = "";
        List<String> whiteList = new ArrayList<String>();
        Iterator<String> iter = whiteNodes.iterator();
        while (iter.hasNext()) {
            whiteList.add(iter.next());
        }
        Collections.sort(whiteList);
        Iterator<String> sortedIter = whiteList.iterator();
        while (sortedIter.hasNext()) {
            whiteString += sortedIter.next();
            if (sortedIter.hasNext()) {
                whiteString += " ";
            }
        }
        return whiteString;
    }

    /**
     * @requires createGraph(graphName) && createNode(parentName)
     * @return a space-separated list of the names of the children of
     * 		   parentName in the graph graphName, in alphabetical order.
     */
    public String listChildren(String graphName, String parentName) {
        Collection<String> children = graphs.get(graphName).listChildren(parentName);
        String childrenString = "";
        List<String> childrenList = new ArrayList<String>();
        Iterator<String> iter = children.iterator();
        while (iter.hasNext()) {
            childrenList.add(iter.next().toString());
        }
        Collections.sort(childrenList);
        Iterator sortedIter = childrenList.iterator();
        while (sortedIter.hasNext()) {
            childrenString += sortedIter.next();
            if (sortedIter.hasNext()) {
                childrenString +=" ";
            }
        }
    	return childrenString;
    }

    /**
     * @requires createGraph(graphName) && createNode(childName)
     * @return a space-separated list of the names of the parents of
     * 		   childName in the graph graphName, in alphabetical order.
     */
    public String listParents(String graphName, String childName) {
        Collection<String> parents = graphs.get(graphName).listParents(childName);
        String parentsString = "";
        List<String> parentsList = new ArrayList<String>();
        Iterator<String> iter = parents.iterator();
        while (iter.hasNext()) {
            parentsList.add(iter.next().toString());
        }
        Collections.sort(parentsList);
        Iterator sortedIter = parentsList.iterator();
        while (sortedIter.hasNext()) {
            parentsString += sortedIter.next();
            if (sortedIter.hasNext()) {
                parentsString += " ";
            }
        }
        return parentsString;
    }
    
    /**
     * @requires addEdge(graphName, parentName, str, edgeLabel) for some
     * 			 string str
     * @return the name of the child of parentName that is connected by the
     * 		   edge labeled edgeLabel, in the graph graphName.
     */
    public String getChildByEdgeLabel(String graphName, String parentName, String edgeLabel) {
    	return graphs.get(graphName).getChildByEdgeLabel(parentName,edgeLabel).toString();
    }

    /**
     * @requires addEdge(graphName, str, childName, edgeLabel) for some
     * 			 string str
     * @return the name of the parent of childName that is connected by the 
     * 		   edge labeled edgeLabel, in the graph graphName.
     */
    public String getParentByEdgeLabel(String graphName, String childName, String edgeLabel) {
        return graphs.get(graphName).getParentByEdgeLabel(childName,edgeLabel).toString();
    }
}
