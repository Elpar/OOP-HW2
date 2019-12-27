
package homework2;

import java.util.List;

public class Simulator<obj extends Object> {

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
        String blackNodes = simulatorGraph.listBlackNodes();
        String whiteNodes = simulatorGraph.listWhiteNodes();
        String[] blackNodesList = blackNodes.split(" ");
        String[] whiteNodesList = whiteNodes.split(" ");

        for (String str : blackNodesList) {
            Pipe<obj> workingPipe = (Pipe<obj>) simulatorGraph.getNodeByLabel(str);
            workingPipe.simulate(simulatorGraph);
        }
        for (String str : whiteNodesList) {
            Filter<obj> workingFilter = (Filter<obj>) simulatorGraph.getNodeByLabel(str);
            workingFilter.simulate(simulatorGraph);
        }
        rounds++;
        checkRep();
    }

    private void checkRep() {
        assert simulatorGraph != null : "Simulator graph must not be null";
        assert rounds >= 0 : "Illegal rounds number";
    }
}
