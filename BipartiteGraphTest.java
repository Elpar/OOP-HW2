
package HW2;

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
    public void testFindingNonExistingElements() {
        HW2.BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph3");

        //add some nodes
        driver.addBlackNode("graph3", "nb1");
        driver.addWhiteNode("graph3", "nw1");

        //check
    }

//    @Test
//    public void testSameColorsConnected() {
//
//    }

//    @Test
//    public void test
//    //  TODO: Add black-box tests
    
  
}
