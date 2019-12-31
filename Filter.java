
package HW2;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Filter<obj extends Object, workObj extends Object> implements Simulatable<obj> {
    //Representation Invariant:
    //storageBuffer != null.
    //objectsToPass != null.

    //Abstraction Function:
    //label represents the object which the filter is for.
    //workingObjects are the objcts that the filter contains.
    //workingObjects is protected to that those who inherit from Pipe can use it.

    private obj label;
    protected ArrayList<workObj> storageBuffer;
    protected ArrayList<workObj> objectsToPass;

    /**
     * @requires filterLabel != null.
     * @modifies this.
     * @effects constructs a new filter.
     */
    public Filter(obj filterLabel) {
        if (filterLabel == null) throw new IllegalArgumentException("The given filter label is null");
        label = filterLabel;
        storageBuffer = new ArrayList<workObj>();
        objectsToPass = new ArrayList<workObj>();
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
     * @requires objectToStorageBuffer != null.
     * @modifies this.
     * @effects adds objectToStorageBuffer to the storageBuffer list.
     */
    public void addToStorageBuffer(workObj objectToStorageBuffer) {
        checkRep();
        if (storageBuffer == null) throw new IllegalArgumentException("The given working object is null");
        storageBuffer.add(objectToStorageBuffer);
        checkRep();
    }

    /**
     * @requires none.
     * @modifies none.
     * @effects returns the contents of storageBuffer.
     */
    abstract public Integer getStorageBufferAmount();

    /**
     * @requires none.
     * @modifies none.
     * @effects returns the contents of objectsToPass.
     */
    abstract public Integer getObjectsToPassAmount();

    private void checkRep() {
        assert storageBuffer != null : "storageBuffer is null!";
        for (workObj iter : storageBuffer) {
            assert iter != null : "null object is found inside storageBuffer";
        }
        assert objectsToPass != null : "objectstoPass is null!";
        for (workObj iter2 : objectsToPass) {
            assert iter2 != null : "null object is found inside objectsToPass";
        }
    }
}
