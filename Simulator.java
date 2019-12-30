
package HW2;

import com.sun.javafx.geom.Edge;

import java.util.Collection;
import java.util.List;

/**
 * This class is an implementation of the Simulator class required in the assignment.
 * When creating a Simulator, it has the labelObj which is the type of the labels of the objects,
 * and the workObj is the type of the working objects of the Simulator.
 */
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
            workingPipeNode.getNode().simulate(simulatorGraph);
        }
        for (labelObj label : whiteNodes) {
            Node<Filter> workingFilterNode = simulatorGraph.getNodeByLabel(label);
            workingFilterNode.getNode().simulate(simulatorGraph);
        }
        rounds++;
        checkRep();
    }

    /**
     * @requires newPipe != null, newLabel != null.
     * @modifies this.
     * @effects adds newPipe to simulatorGraph.
     */
    public void addPipe(Node newPipe, labelObj newLabel) {
        checkRep();
        if (newLabel == null) throw new IllegalArgumentException("The given label is null");
        if (newPipe == null) throw new IllegalArgumentException("The given pipe is null");
        simulatorGraph.addBlackNode(newLabel, newPipe);
        checkRep();
    }

    /**
     * @requires newFilter != null, newLabel != null.
     * @modifies this.
     * @effects adds newFilter to simulatorGraph.
     */
    public void addFilter(Node newFilter, labelObj newLabel) {
        checkRep();
        if (newLabel == null) throw new IllegalArgumentException("The given label is null");
        if (newFilter == null) throw new IllegalArgumentException("The given filter is null");
        simulatorGraph.addWhiteNode(newLabel, newFilter);
        checkRep();
    }

    /**
     * @requires parent != null, child != null, newEdge != null.
     * @modifies this.
     * @effects adds newEdge to simulatorGraph.
     */
    public void addEdge(labelObj parent, labelObj child, labelObj newEdge) {
        checkRep();
        if (parent == null) throw new IllegalArgumentException("The given parent is null");
        if (child == null) throw new IllegalArgumentException("The given child is null");
        if (newEdge == null) throw new IllegalArgumentException("The given edge label is null");
        simulatorGraph.addEdge(parent, child, newEdge);
        checkRep();
    }

    private void checkRep() {
        assert simulatorGraph != null : "Simulator graph must not be null";
        assert rounds >= 0 : "Illegal rounds number";
    }
}
