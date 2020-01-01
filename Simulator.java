
package homework2;

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

    protected BipartiteGraph<labelObj> simulatorGraph;
    private int rounds;

    /**
     * @requires none.
     * @modifies this.
     * @effects creates a new Simulator with an empty graph and zero rounds.
     */
    public Simulator() {
        simulatorGraph = new BipartiteGraph<labelObj>();
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
    public void addPipe(Pipe<labelObj,workObj> newPipe, labelObj newLabel) {
        checkRep();
        if (newLabel == null) throw new IllegalArgumentException("The given label is null");
        if (newPipe == null) throw new IllegalArgumentException("The given pipe is null");
        Node<? extends Pipe<labelObj,workObj>> newNode = new Node<>(newPipe);
        simulatorGraph.addBlackNode(newLabel, newNode);
        checkRep();
    }

    /**
     * @requires newFilter != null, newLabel != null.
     * @modifies this.
     * @effects adds newFilter to simulatorGraph.
     */
    public void addFilter(Filter<labelObj,workObj> newFilter, labelObj newLabel) {
        checkRep();
        if (newLabel == null) throw new IllegalArgumentException("The given label is null");
        if (newFilter == null) throw new IllegalArgumentException("The given filter is null");
        Node<? extends Filter<labelObj,workObj>> newNode = new Node<>(newFilter);
        simulatorGraph.addWhiteNode(newLabel, newNode);
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

    /**
     * @requires pipeLabel != null, item != null.
     * @modifies item.
     * @effects adds item to pipeLabel if it has enough capacity left for it.
     */
    public void injectInput(labelObj pipeLabel, workObj item) {
        checkRep();
        if (pipeLabel == null) throw new IllegalArgumentException("Illegal pipe label for injection");
        if (item == null) throw new IllegalArgumentException("Illegal item for injection to a pipe");
        Node<Pipe> pipeFromGraph = simulatorGraph.getNodeByLabel(pipeLabel);
        if (pipeFromGraph.getNode().isEnoughAmountLeft(item)) {
            pipeFromGraph.getNode().addWorkingObject(item);
        } else throw new ArithmeticException("Not enough room in the pipe to inject the given input");
        checkRep();
    }

    /**
     * @requires pipeLabel != null.
     * @modifies none.
     * @effects returns a node of the pipe labeled pipeLabel.
     */
    public Node<? extends Pipe<labelObj,workObj>> getPipeByLabel(labelObj pipeLabel) {
        checkRep();
        if (pipeLabel == null) throw new IllegalArgumentException("Given pipe label is null");
        return simulatorGraph.getNodeByLabel(pipeLabel);
    }

    /**
     * @requires filterLabel != null.
     * @modifies none.
     * @effects returns a node of the filter labeled filterLabel.
     */
    public Node<? extends Filter<labelObj,workObj>> getFilterByLabel(labelObj filterLabel) {
        checkRep();
        if (filterLabel == null) throw new IllegalArgumentException("Given pipe label is null");
        return simulatorGraph.getNodeByLabel(filterLabel);
    }

    /**
     *
     */
    public Collection<Edge<labelObj>> getEdges() {
        checkRep();
        return simulatorGraph.getEdges();
    }

    private void checkRep() {
        assert simulatorGraph != null : "Simulator graph must not be null";
        assert rounds >= 0 : "Illegal rounds number";
    }
}
