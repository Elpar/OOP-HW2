
package homework2;

import java.util.ArrayList;

public abstract class Filter<obj extends Object, workObj extends Object> implements Simulatable<obj> {
    //Representation Invariant:
    //empty.

    //Abstraction Function:
    //label represents the object which the filter is for.

    private obj label;
    private ArrayList workingObjects;

    /**
     * @requires filterLabel != null.
     * @modifies this.
     * @effects constructs a new filter.
     */
    public Filter(obj filterLabel) {
        label = filterLabel;
        workingObjects = new ArrayList<workObj>();
    }

    /**
     * @requires none.
     * @modifies none.
     * @effects runs a step of the simulation of the filter's obj type.
     */
    public abstract void simulate();

    /**
     * @requires none.
     * @modifies none.
     * @effects returns the filter's label.
     */
    public obj getLabel() {
        return label;
    }
}
