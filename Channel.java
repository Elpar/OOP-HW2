
package HW2;

import java.util.ArrayList;
import java.util.Iterator;

public class Channel extends Pipe<String,Transaction> {

    //Representation Invariants:
    //None.

    //Abstraction Function:
    //The simulate(BipartiteGraph<String>) implementation represents a channel.

    int nextParticipantToGetTxn;

    /**
     * @requires label != null, capacity > 0.
     * @modifies none.
     * @effects constructs a new channel.
     */
    public Channel(String label, Integer capacity) {
        super(label,capacity);
        if (label == null) throw new IllegalArgumentException("Given label is null in Channel constructor");
        if (capacity <= 0)
            throw new ArithmeticException("Given capacity is not a positive integer in Channel constructor");
        nextParticipantToGetTxn = 0;
        checkRep();
    }

    /**
     * @requires none.
     * @modifies none.
     * @effects returns the amount of capacity left.
     */
    public int leftCapacity() {
        checkRep();
        int leftCapacity = super.getPipeCapacity();
        for(Transaction currentTransaction : super.workingObjects){
            leftCapacity -= currentTransaction.getAmount();
        }
        checkRep();
        return leftCapacity;
    }

    /**
     * @requires none.
     * @modifies none.
     * @effects returns a list of the content of this.
     */
    public ArrayList<Transaction> getContents() {
        checkRep();
        ArrayList<Transaction> contents = new ArrayList<Transaction>();
        for(Transaction t : super.workingObjects) {
            contents.add(new Transaction(t.getProduct(), t.getAmount()));
        }
        checkRep();
        return contents;
    }

    /**
     * @requires this.leftCapacity was invoked earlier and returned with a positive value, T != null.
     * @modifies this.workingObjects.
     * @effects adds a transaction to this.
     */
    public void addTransaction(Transaction T) {
        checkRep();
        if (T == null) throw new IllegalArgumentException("Given transaction is null in addTransaction");
        if (leftCapacity() <= 0) throw new ArithmeticException("Chanel is full, cannot add a Transaction");
        if (leftCapacity() < T.getAmount()) {
            T.substractAmount(T.getAmount() - leftCapacity());
        }
        super.addWorkingObject(T);
        checkRep();
    }

    /**
     * @requires graph != null.
     * @modifies none.
     * @effects simulates one step of channel.
     */
    @Override
    public void simulate(BipartiteGraph<String> graph) {
        checkRep();
        if (graph == null) throw new IllegalArgumentException("Given graph is null in simulate");
        //Pass one transaction to one child
        if (!graph.listChildren(super.getLabel()).iterator().hasNext()) return; // in this case - no children
        String currentLabel = graph.listChildren(super.getLabel()).iterator().next();
        Node<Participant> childParticipantNode = null;
        if (graph.getNodeByLabel(currentLabel).getClass() != childParticipantNode.getClass())
            throw new IllegalStateException("illegel filter found in simulator");
        childParticipantNode = graph.getNodeByLabel(currentLabel);
        Participant childParticipant = childParticipantNode.getNode();
        childParticipant.addTransaction(super.workingObjects.remove(nextParticipantToGetTxn)); //TODO: make sure remove actually returns the transaction
        nextParticipantToGetTxn ++;
        nextParticipantToGetTxn %= super.workingObjects.size();
        checkRep();
    }

    /**
     * @requires workingObject != null.
     * @modifies none.
     * @effects returns true if there's enough amount left in the pipe, false otherwise.
     */
    @Override
    public boolean isEnoughAmountLeft(Transaction workingObject) {
        checkRep();
        if (workingObject == null)
            throw new IllegalArgumentException("Given workingObject is null in isEnoughAmountLeft");
        return (leftCapacity() >= workingObject.getAmount());
    }

    public void checkRep() {}  //nothing to be done here
}
