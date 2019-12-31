package HW2;

import java.util.ArrayList;
import java.util.Collection;

public class Participant extends Filter<String, Transaction> {

    //Representation Invariant:
    //neededProductAmount > 0 & neededProductName != null //TODO: MAKE SURE IS CORRECT

    //Abstraction Function:
    //TODO: FILL THIS!!!!

    int neededProductAmount;
    String neededProductName;
    Transaction transactionToProcess;

    /**
     * @requires //TODO: fill this
     * @modifies this.
     * @effects constructs a new participant.
     */
    public Participant(String label, int neededProductAmount, String neededProductName) {
        super(label);
        //TODO: add here all the checks for the inputs
        transactionToProcess = null;
        this.neededProductAmount = neededProductAmount;
        this.neededProductName = neededProductName;
        checkRep();
    }

    /**
     * @requires none.
     * @modifies none.
     * @effects returns the storageBuffer's total amount.
     */
    public Integer getStorageBufferAmount() {
        checkRep();
        int totalAmount = 0;
        for(Transaction t : super.storageBuffer) {
            totalAmount += t.getAmount();
        }
        checkRep();
        return totalAmount;
    }

    /**
     * @requires none.
     * @modifies none.
     * @effects returns the objectsToPass's amount.
     */
    public Integer getObjectsToPassAmount() {
        checkRep();
        int totalAmount = 0;
        for(Transaction t : super.objectsToPass) {
            totalAmount += t.getAmount();
        }
        checkRep();
        return totalAmount;
    }

    /**
     * @requires graph != null.
     * @modifies none.
     * @effects simulates one step of participant.
     */
    @Override
    public void simulate(BipartiteGraph<String> graph) {
        checkRep();
        if (graph == null) throw new IllegalArgumentException("Given graph is null in simulate");
        if (transactionToProcess == null && transactionToProcess.getProduct().equals(neededProductName)) {
            if (neededProductAmount >= transactionToProcess.getAmount()) {
                neededProductAmount -= transactionToProcess.getAmount();
            } else { // transaction amount is greater then needed amount
                transactionToProcess.substractAmount(neededProductAmount);
                neededProductAmount = 0;
                super.addToStorageBuffer(transactionToProcess);
            }
        } else {
            super.addToStorageBuffer(transactionToProcess);
        }
        transactionToProcess = null;// TODO: check this null assignment if it does not change the original reference

        // pass one transaction to one child
        Node<Channel> currentNode;
        for(String currentLabel : graph.listChildren(super.getLabel())) {
            currentNode = graph.getNodeByLabel(currentLabel);
            if (currentNode.getNode().leftCapacity() <= 0) { // TODO: check how to fix this casting (its disgusting)
                continue;
            }
            currentNode.getNode().addTransaction(super.storageBuffer.remove(super.storageBuffer.size()-1));
            break;
        }
        checkRep();
    }

    /**
     * @requires t != null.
     * @modifies this.
     * @effects adds a transaction to process.
     */
    public void addTransaction (Transaction t) {
        if (t == null) throw new IllegalArgumentException("Given t is null in addTransaction");
        checkRep();
        transactionToProcess = t;
        checkRep();
    }

    private void checkRep() { //TODO: implement this

    }
}
