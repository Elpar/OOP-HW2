
package homework2;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Pipe<obj extends Object, workObj extends Object> implements Simulatable<obj> {
    //Representation Invariant:
    //capacity > 0.
    //workingObjects != null.

    //Abstraction Function:
    //label represents the object which the pipe is for.
    //capacity represents the capacity for the pipe for objects of label type the pipe can hold.
    //The value for capacity is 0 for "no capacity" and any integer > 0 for the rest.
    //workingObjects is a list of all the working objects.
    //workingObjects is protected to that those who inherit from Filter can use it.

    private obj label;
    private Integer capacity;
    protected ArrayList<workObj> workingObjects;

    /**
     * @requires label != null, givenCapacity > 0 & is an integer.
     * @modifies this.
     * @effects constructs a new pipe with given capacity.
     */
    public Pipe(obj pipeLabel, int givenCapacity) {
        if (givenCapacity <= 0) throw new IllegalArgumentException("Capacity must be greater than 0");
        label = pipeLabel;
        workingObjects = new ArrayList<workObj>();
        capacity = givenCapacity;
        checkRep();
    }

    /**
     * @requires none.
     * @modifies none.
     * @effects runs a step of the simulation of the pipe's obj type.
     */
    public abstract void simulate(BipartiteGraph<obj> graph);

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
     * @requires pipeCapacity > 0.
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

    /**
     * @requires newWorkingObject != null, amount of all the objects cannot be more than (this.capacity).
     * @modifies this.
     * @effects adds newWorkingObject to workingObjects.
     */
    public void addWorkingObject(workObj newWorkingObject) {
        checkRep();
        if (newWorkingObject == null)
            throw new IllegalArgumentException("The given working object is null in addWorkingObject");
        if (!isEnoughAmountLeft(newWorkingObject))
            throw new ArithmeticException("Not enough capacity left in addWorkingObject");
        workingObjects.add(newWorkingObject);
        checkRep();
    }

    /**
     * @requires workingObject != null.
     * @modifies none.
     * @effects returns true if there's enough amount left in the pipe, false otherwise.
     */
    abstract public boolean isEnoughAmountLeft(workObj workingObject);

    /**
     * @requires none.
     * @modifies none.
     * @effects returns the contents of workingObjects.
     */
    abstract public ArrayList<workObj> getContents();

    private void checkRep() {
        assert workingObjects != null : "workingObjects is null!";
        for (workObj iter : workingObjects) {
            assert iter != null : "null object is found inside workingObjects";
        }
        assert capacity > 0: "capacity is not a positive integer";
    }
}
