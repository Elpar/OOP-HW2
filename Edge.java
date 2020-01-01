
package homework2;

/**
 * This class implements an edge.
 */
public class Edge<labelObj> {

    //Representation Invariant:
    //parent != null, child != null, label != null.

    //Abstraction Function:
    // Parent, child and label represent the labels of this edge, the child and the parent name.

    private final labelObj parent;
    private final labelObj child;
    private final labelObj label;

    public Edge(labelObj pa, labelObj ch, labelObj la) {
        if (pa == null || ch == null || la == null) throw new IllegalArgumentException("Null parent/child/label");
        parent = pa;
        child = ch;
        label = la;
        checkRep();
    }

    /**
     * @requires none.
     * @modifies none.
     * @effects returns the parent of this.
     */
    public labelObj getChild() {
        checkRep();
        return child;
    }

     /**
     * @requires none.
     * @modifies none.
     * @effects returns the parent of this.
     */
    public labelObj getLabel() {
        checkRep();
        return label;
    }

     /**
     * @requires none.
     * @modifies none.
     * @effects returns the parent of this.
     */
    public labelObj getParent() {
        checkRep();
        return parent;
    }

    private void checkRep() {
        assert parent != null : "Parent is null";
        assert child != null : "Child is null";
        assert label != null : "Label is null";
    }

}
