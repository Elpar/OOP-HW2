
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

    @Test(expected = ??!???!??!!?! HOW TO CATCH THE ASSERT??)
    public void testAddingNodesWithTheSameName() {
        HW2.BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph3");

        //add some nodes
        driver.addBlackNode("graph3", "nb1");
        driver.addWhiteNode("graph3", "nb1");
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

    //TODO: Continue tests from here!!!!!
    @Test
    public void testGivenNullInputToWhiteNodeWithoutObject() {
        HW2.BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph5");

        //add some nodes
        driver.addWhiteNode("graph5", "nw1");

    }

    @Test
    public void testGivenNullInputToWhiteNodeWithObject() {
        HW2.BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph5");

        //add some nodes
        driver.addWhiteNode("graph5", "nw1");

    }

    @Test
    public void testGivenNullInputToBlackNodeWithObject() {
        HW2.BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph5");

        //add some nodes
        driver.addBlackNode("graph5", "nb1");

    }

    @Test
    public void testGivenNullInputToBlackNodeWithoutObject() {
        HW2.BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph5");

        //add some nodes
        driver.addBlackNode("graph5", "nb1");

    }

    @Test
    public void testCheckMultipleChilren() {
        HW2.BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph5");

        //add some nodes
        driver.addBlackNode("graph5", "nb1");
    }

    @Test
    public void testCheckMultipleParents() {
        HW2.BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph5");

        //add some nodes
        driver.addBlackNode("graph5", "nb1");
    }
}
