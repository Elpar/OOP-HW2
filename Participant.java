package homework2;

import java.util.ArrayList;
import java.util.Collection;

public class Participant extends Filter<String, Transaction> {

    //Representation Invariant:
    //neededProductAmount >= 0 & neededProductName != null

    //Abstraction Function:
    //neededProductName and neededProductAmount represent the participant's needed product and its needed amount.
    //transactionsToProcess represents the transaction that is needed to be processed.

    int neededProductAmount;
    String neededProductName;

    /**
     * @requires label != null, neededProductamount >= 0, neededProductName != null.
     * @modifies this.
     * @effects constructs a new participant.
     */
    public Participant(String label, int neededProductAmount, String neededProductName) {
        super(label);
        if (neededProductAmount < 0)
            throw new ArithmeticException("Given neededProductAmount is less than 0 in Participant constructor");
        if (neededProductName == null)
            throw new IllegalArgumentException("Given neededProductName is null in Participant constructor");
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
        if (super.passedWorkingObjects.size() > 0) {
            Transaction transactionToProcess = super.passedWorkingObjects.get(super.passedWorkingObjects.size()-1);
            if (transactionToProcess.getProduct().equals(neededProductName)) {
                if (neededProductAmount >= transactionToProcess.getAmount()) {
                    neededProductAmount -= transactionToProcess.getAmount();
                    super.addToStorageBuffer(transactionToProcess);
                } else if (neededProductAmount > 0) { // transaction amount is greater then needed amount
                    Transaction t = new Transaction(transactionToProcess.getProduct(), neededProductAmount);
                    super.addToStorageBuffer(t);
                    transactionToProcess.substractAmount(neededProductAmount);
                    neededProductAmount = 0;
                    super.objectsToPass.add(transactionToProcess);
                }
            } else {
                super.objectsToPass.add(transactionToProcess);
            }
            super.passedWorkingObjects.remove(transactionToProcess);
        }

        // pass one transaction to one child
        if (super.objectsToPass.size() > 0) {
            Node<Channel> currentNode;
            for(String currentChildLabel : graph.listChildren(super.getLabel())) {
                currentNode = graph.getNodeByLabel(currentChildLabel);
                if (currentNode.getNode().leftCapacity() <= 0) {
                    continue;
                }
                currentNode.getNode().addTransaction(super.objectsToPass.get( super.objectsToPass.size()-1));
                super.objectsToPass.remove( super.objectsToPass.size()-1);
                break;
            }
        }
        checkRep();
    }

//    /**
//     * @requires t != null.
//     * @modifies this.
//     * @effects adds a transaction to process.
//     */
//    public void addTransaction (Transaction t) {
//        if (t == null) throw new IllegalArgumentException("Given t is null in addTransaction");
//        checkRep();
//       super.addWorkingObject(t);
//        checkRep();
//    }

    private void checkRep() {
        assert neededProductAmount >= 0 : "The needed product amount is less than 0";
        assert neededProductName != null : "The needed product name is null";
    }
}
