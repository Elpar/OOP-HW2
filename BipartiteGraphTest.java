
package HW2;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * HW2.BipartiteGraphTest contains JUnit block-box unit tests for BipartiteGraph.
 */
public class BipartiteGraphTest {

	@Test
    public void testExample() {
        HW2.BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        
        //create a graph
        driver.createGraph("graph1");
        
        //add a pair of nodes
        driver.addBlackNode("graph1", "n1");
        driver.addWhiteNode("graph1", "n2");
        
        //add an edge
        driver.addEdge("graph1", "n1", "n2", "edge");
        
        //check neighbors
        assertEquals("wrong black nodes", "n1", driver.listBlackNodes("graph1"));
        assertEquals("wrong white nodes", "n2", driver.listWhiteNodes("graph1"));
        assertEquals("wrong children", "n2", driver.listChildren ("graph1", "n1"));
        assertEquals("wrong children", "", driver.listChildren ("graph1", "n2"));
        assertEquals("wrong parents", "", driver.listParents ("graph1", "n1"));
        assertEquals("wrong parents", "n1", driver.listParents ("graph1", "n2"));
    }
    
    @Test
    public void testGetByEdge() {
        HW2.BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
	    driver.createGraph("graph2");

	    //add some nodes
        driver.addBlackNode("graph2", "nb1");
        driver.addWhiteNode("graph2", "nw1");
        driver.addBlackNode("graph2", "nb2");
        driver.addWhiteNode("graph2", "nw2");

        //add edges
        driver.addEdge("graph2", "nb1", "nw1", "e1");
        driver.addEdge("graph2", "nb2", "nw2", "e2");
        driver.addEdge("graph2", "nb1", "nw2", "e3");

        //check get child by edge label
        assertEquals("wrong child by edge","nw1", driver.getChildByEdgeLabel("graph2","nb1", "e1"));
        assertEquals("wrong child by edge","nw2", driver.getChildByEdgeLabel("graph2","nb1", "e3"));
        assertEquals("wrong child by edge","nw2", driver.getChildByEdgeLabel("graph2","nb2", "e2"));

        //check get parent by edge label
        assertEquals("wrong parent by edge","nb1", driver.getParentByEdgeLabel("graph2","nw1", "e1"));
        assertEquals("wrong parent by edge","nb1", driver.getParentByEdgeLabel("graph2","nw2", "e3"));
        assertEquals("wrong parent by edge","nb2", driver.getParentByEdgeLabel("graph2","nw2", "e2"));
    }

    @Test
    public void testAddingNodesWithTheSameName() {
        HW2.BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph3");

        //add some nodes
        driver.addBlackNode("graph3", "nb1");
        driver.addWhiteNode("graph3", "nb1"); //should fail at checkRep().
	}

    @Test(expected = IllegalArgumentException.class)
    public void testSameBlackColorsConnected() {
        HW2.BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph4");

        //add some nodes
        driver.addBlackNode("graph4", "nb1");
        driver.addBlackNode("graph4", "nb2");

        //check for black nodes
        driver.addEdge("graph4", "nb1", "nb2", "e1");
        }

    @Test(expected = IllegalArgumentException.class)
    public void testSameWhiteColorsConnected() {
        HW2.BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph5");

        //add some nodes
        driver.addWhiteNode("graph5", "nw1");
        driver.addWhiteNode("graph5", "nw2");

        //check for white nodes
        driver.addEdge("graph5", "nw1", "nw2", "e1");
        }

    @Test
    public void testAddingNodesWithObjects() {
        HW2.BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph6");

        //add a pair of nodes
        Node node = new Node<String>("1");
        Node node2 = new Node<String>("2");
        driver.addBlackNodeWithObject("graph6", "n1", node);
        driver.addWhiteNodeWithObject("graph6", "n2", node2);

        //add an edge
        driver.addEdge("graph6", "n1", "n2", "edge");

        //check neighbors
        assertEquals("wrong black nodes", "n1", driver.listBlackNodes("graph6"));
        assertEquals("wrong white nodes", "n2", driver.listWhiteNodes("graph6"));
        assertEquals("wrong children", "n2", driver.listChildren ("graph6", "n1"));
        assertEquals("wrong children", "", driver.listChildren ("graph6", "n2"));
        assertEquals("wrong parents", "", driver.listParents ("graph6", "n1"));
        assertEquals("wrong parents", "n1", driver.listParents ("graph6", "n2"));
    }

    @Test(expected = IllegalArgumentException.class) //TODO: make this TRUE or remove!!!
    public void testGivenNullInputToWhiteNodeWithoutObject() {
        HW2.BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph7");

        //add an invalid node
        driver.addWhiteNode("graph7", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGivenNullInputToWhiteNodeWithObject() {
        HW2.BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph8");

        //add some nodes
        Node node = new Node<String>("1");
        driver.addWhiteNodeWithObject("graph8", null, node);
    }

@Test(expected = IllegalArgumentException.class)
    public void testGivenInputToWhiteNodeWithNullObject() {
        HW2.BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph9");

        //add some nodes
        driver.addWhiteNodeWithObject("graph9", "nw1", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGivenNullInputToBlackNodeWithObject() {
        HW2.BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph10");

        //add some nodes
        Node node = new Node<String>("1");
        driver.addBlackNodeWithObject("graph10", null, node);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGivenInputToBlackNodeWithNullObject() {
        HW2.BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph10");

        //add some nodes
        driver.addBlackNodeWithObject("graph10", "nb1", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGivenNullInputToBlackNodeWithoutObject() {
        HW2.BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph11");

        //add some nodes
        driver.addBlackNode("graph11", null);
    }

    @Test
    public void testCheckMultipleChilren() {
        HW2.BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph12");

        //add some nodes
        Node node1 = new Node<String>("1");
        Node node2 = new Node<String>("2");
        driver.addBlackNode("graph12", "nb1");
        driver.addWhiteNode("graph12", "nw1");
        driver.addWhiteNode("graph12", "nw2");
        driver.addWhiteNodeWithObject("graph12", "nwwo1", node1);
        driver.addWhiteNodeWithObject("graph12", "nwwo2", node2);

        //add edges
        driver.addEdge("graph12", "nb1", "nw1", "e1");
        driver.addEdge("graph12", "nb1", "nw2", "e2");
        driver.addEdge("graph12", "nb1", "nwwo1", "e3");
        driver.addEdge("graph12", "nb1", "nwwo2", "e4");

        //checks
        assertEquals("wrong black nodes", "nb1", driver.listBlackNodes("graph12"));
        assertEquals("wrong white nodes", "nw1 nw2 nwwo1 nwwo2", driver.listWhiteNodes("graph12"));
        assertEquals("wrong children for black node", "nw1 nw2 nwwo1 nwwo2", driver.listChildren ("graph12", "nb1"));
        assertEquals("wrong children for white node nw1", "", driver.listChildren ("graph12", "nw1"));
        assertEquals("wrong children for white node nw2", "", driver.listChildren ("graph12", "nw2"));
        assertEquals("wrong children for white node nwwo1", "", driver.listChildren ("graph12", "nwwo1"));
        assertEquals("wrong children for white node nwwo2", "", driver.listChildren ("graph12", "nwwo2"));
        assertEquals("wrong parents for black node", "", driver.listParents ("graph12", "nb1"));
        assertEquals("wrong parents for white node nw1", "nb1", driver.listParents ("graph12", "nw1"));
        assertEquals("wrong parents for white node nw2", "nb1", driver.listParents ("graph12", "nw2"));
        assertEquals("wrong parents for white node nwwo1", "nb1", driver.listParents ("graph12", "nwwo1"));
        assertEquals("wrong parents for white node nwwo2", "nb1", driver.listParents ("graph12", "nwwo2"));
    }

    @Test
    public void testCheckMultipleParents() {
        HW2.BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph13");

        //add some nodes
        Node node1 = new Node<String>("1");
        Node node2 = new Node<String>("2");
        Node node3 = new Node<String>("3");
        driver.addBlackNode("graph13", "nb1");
        driver.addBlackNode("graph13", "nb2");
        driver.addBlackNodeWithObject("graph13", "nbwo1", node1);
        driver.addBlackNodeWithObject("graph13", "nbwo2", node2);
        driver.addWhiteNode("graph13", "nw1");
        driver.addWhiteNodeWithObject("graph13", "nwwo1", node3);

        //add edges
        driver.addEdge("graph13", "nb1", "nw1", "e1");
        driver.addEdge("graph13", "nb2", "nw1", "e2");
        driver.addEdge("graph13", "nbwo1", "nw1", "e3");
        driver.addEdge("graph13", "nbwo2", "nw1", "e4");
        driver.addEdge("graph13", "nb1", "nwwo1", "e5");
        driver.addEdge("graph13", "nb2", "nwwo1", "e6");
        driver.addEdge("graph13", "nbwo1", "nwwo1", "e7");
        driver.addEdge("graph13", "nbwo2", "nwwo1", "e8");

        //checks
        assertEquals("wrong black nodes", "nb1 nb2 nbwo1 nbwo2", driver.listBlackNodes("graph13"));
        assertEquals("wrong white nodes", "nw1 nwwo1", driver.listWhiteNodes("graph13"));
        assertEquals("wrong children for black node nb1", "nw1 nwwo1", driver.listChildren ("graph13", "nb1"));
        assertEquals("wrong children for white node nbwo1", "nw1 nwwo1", driver.listChildren ("graph13", "nbwo1"));
        assertEquals("wrong children for black node nb2", "nw1 nwwo1", driver.listChildren ("graph13", "nb2"));
        assertEquals("wrong children for white node nbwo2", "nw1 nwwo1", driver.listChildren ("graph13", "nbwo2"));
        assertEquals("wrong parents for white node nw1", "nb1 nb2 nbwo1 nbwo2", driver.listParents ("graph13", "nw1"));
        assertEquals("wrong parents for white node nwwo1", "nb1 nb2 nbwo1 nbwo2", driver.listParents ("graph13", "nwwo1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddIllegalEdge() {
        HW2.BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph14");

        //add some nodes
        driver.addWhiteNode("graph14", "nw1");
        driver.addBlackNode("graph14", "nb1");

        //add illegal edge
        driver.addEdge("graph14", "nw1", "nb1", null);
    }
}
