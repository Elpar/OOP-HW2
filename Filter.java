
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
    protected ArrayList<workObj> workingObjects;

    /**
     * @requires filterLabel != null.
     * @modifies this.
     * @effects constructs a new filter.
     */
    public Filter(obj filterLabel) {
        if (filterLabel == null) throw new IllegalArgumentException("The given filter label is null");
        label = filterLabel;
        workingObjects = new ArrayList<workObj>();
        checkRep();
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
        checkRep();
        return label;
    }

    /**
     * @requires storageBuffer != null.
     * @modifies this.
     * @effects adds storageBuffer to the workingObjects list.
     */
    public void addToStorageBuffer(workObj storageBuffer) {
        checkRep();
        if (storageBuffer == null) throw new IllegalArgumentException("The given working object is null");
        workingObjects.add(storageBuffer);
        checkRep();
    }

    private void checkRep() {
        assert workingObjects != null : "workingObjects is null!";
        for (workObj iter : workingObjects) {
            assert iter != null : "null object is found inside workingObjects";
        }
    }
}
