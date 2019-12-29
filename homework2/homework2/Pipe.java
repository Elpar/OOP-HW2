
package homework2;

import java.util.ArrayList;

public abstract class Pipe<obj extends Object, workObj extends Object> implements Simulatable<obj>{
    //Representation Invariant:
    //capacity >= -1

    //Abstraction Function:
    //label represents the object which the pipe is for.
    //capacity represents the capacity for the pipe for objects of label type the pipe can hold.
    //The value for capacity is 0 for "no capacity", -1 for "unlimited" and any integer > 0 for the rest.

    private obj label;
    private int capacity;
    private ArrayList workingObjects;

    /**
     * @requires label != null.
     * @modifies this.
     * @effects constructs a new pipe with unlimited capacity.
     */
    public Pipe(obj pipeLabel) {
        label = pipeLabel;
        workingObjects = new ArrayList<workObj>();
        capacity = -1;
    }

    /**
     * @requires none.
     * @modifies none.
     * @effects runs a step of the simulation of the pipe's obj type.
     */
    public abstract void simulate();

    /**
     * @requires none.
     * @modifies none.
     * @effects  returns the label of pipe.
     */
    public obj getLabel() {
        checkRep();
        return label;
    }

    /**
     * @requires pipeCapacity >= -1.
     * @modifies this.
     * @effects set the pipe's capacity.
     */
    public void setPipeCapacity (int pipeCapacity) {
        checkRep();
        capacity = pipeCapacity;
        checkRep();
    }

    /**
     * @requires none.
     * @modifies none.
     * @effects returns the pipe's capacity
     */
    public int getPipeCapacity() {
        checkRep();
        return capacity;
    }

    private void checkRep() {
        assert capacity >= -1 : "Illegal capacity value";
    }
}
