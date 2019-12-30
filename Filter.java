
package HW2;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Filter<obj extends Object, workObj extends Object> implements Simulatable<obj> {
    //Representation Invariant:
    //workingObjects != null.

    //Abstraction Function:
    //label represents the object which the filter is for.
    //workingObjects are the objcts that the filter contains.
    //workingObjects is protected to that those who inherit from Pipe can use it.

    private obj label;
    protected ArrayList workingObjects;

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

    /**
     *
     * @param newWorkingObject
     */
    public void addWorkingObject(workObj newWorkingObject) {//TODO: this

    }

    /**
     *
     */
    private void checkRep() { //TODO: this
        Iterator<workObj> iter;
    }
}
