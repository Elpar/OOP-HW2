
package HW2;

import com.sun.javafx.geom.Edge;

import java.util.Collection;
import java.util.List;


//labelObj is the type of the labels of the objects, workObj is the type of the working objects. //TODO: write this correctly
public class Simulator<labelObj extends Object, workObj extends  Object> {

    //Representation Invariant:
    //BipartiteGraph != null.

    //Abstraction Function:
    //The simulator contains a bipartite graph which represents filters as white nodes and pipes as black nodes.
    //rounds counts how many rounds the simulator has ran.

    BipartiteGraph<labelObj> simulatorGraph = new BipartiteGraph<labelObj>();
    private int rounds;

    /**
     * @requires none.
     * @modifies this.
     * @effects creates a new Simulator and zeroes rounds.
     */
    public Simulator() {
        rounds = 0;
        checkRep();
    }

    /**
     * @requires none.
     * @modifies none.
     * @effects runs one round of the simulator for all pipes and filters.
     */
    public void simulate() {
        checkRep();
        Collection<labelObj> blackNodes = simulatorGraph.listBlackNodes();
        Collection<labelObj> whiteNodes = simulatorGraph.listWhiteNodes();

        for (labelObj label : blackNodes) {
            Node<Pipe> workingPipeNode = simulatorGraph.getNodeByLabel(label);
            workingPipeNode.getNode().simulate();
        }
        for (labelObj label : whiteNodes) {
            Node<Filter> workingFilterNode = simulatorGraph.getNodeByLabel(label);
            workingFilterNode.getNode().simulate();
        }
        rounds++;
        checkRep();
    }

    /**
     *
     * @param label
     * @param newCapacity
     */
    public void addPipe(Node newPipe) { //TODO: this.

    }

    /**
     *
     */
    public void addFilter(Node newFilter) {//TODO: this.

    }

    /**
     *
     */
    public void addEdge(labelObj newEdge) {//TODO: this.

    }

    private void checkRep() {
        assert simulatorGraph != null : "Simulator graph must not be null";
        assert rounds >= 0 : "Illegal rounds number";
    }
}
