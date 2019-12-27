
package homework2;

public abstract class Filter<obj extends Object> implements Simulatable<obj> {
    //Representation Invariant:
    //empty.

    //Abstraction Function:
    //label represents the object which the filter is for.

    private obj label;

    /**
     * @requires filterLabel != null.
     * @modifies this.
     * @effects constructs a new filter.
     */
    public Filter(obj filterLabel) {
        label = filterLabel;
    }

    /**
     * @requires none.
     * @modifies none.
     * @effects runs a step of the simulation of the filter's obj type.
     */
    public abstract void simulate(BipartiteGraph<obj> graph);

    /**
     * @requires none.
     * @modifies none.
     * @effects returns the filter's label.
     */
    public obj getLabel() {
        return label;
    }
}
