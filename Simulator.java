
package HW2;

import java.util.Collection;
import java.util.List;

public class Simulator<obj extends Object, workObj extends  Object> {

    //Representation Invariant:
    //BipartiteGraph != null.

    //Abstraction Function:
    //The simulator contains a bipartite graph which represents filters as white nodes and pipes as black nodes.
    //rounds counts how many rounds the simulator has ran.

    BipartiteGraph<obj> simulatorGraph = new BipartiteGraph<obj>();
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
        Collection<obj> blackNodes = simulatorGraph.listBlackNodes();
        Collection<obj> whiteNodes = simulatorGraph.listWhiteNodes();

        for (obj label : blackNodes) {
            Node<Pipe> workingPipeNode = simulatorGraph.getNodeByLabel(label);
            workingPipeNode.getNode().simulate();
        }
        for (obj label : whiteNodes) {
            Node<Filter> workingFilterNode = simulatorGraph.getNodeByLabel(label);
            workingFilterNode.getNode().simulate();
        }
        rounds++;
        checkRep();
    }

    private void checkRep() {
        assert simulatorGraph != null : "Simulator graph must not be null";
        assert rounds >= 0 : "Illegal rounds number";
    }
}
