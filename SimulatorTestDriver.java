
package homework2;

import java.util.*;

/**
 * This class implements a testing driver for Simulator. The driver manages
 * Simulators for recycling channels
 */
public class SimulatorTestDriver {

	private Map<String, Simulator<String, Transaction>> simulators;

	/**
	 * @modifies this
	 * @effects Constructs a new test driver.
	 */
	public SimulatorTestDriver() {
		simulators = new HashMap<String, Simulator<String,Transaction>>();
	}

	/**
	 * @requires simName != null
	 * @modifies this
	 * @effects Creates a new simulator named simName. The simulator's graph is
	 *          initially empty.
	 */
	public void createSimulator(String simName) {
		if (simName == null) throw new IllegalArgumentException("Given simName is null in createSimulator");
		Simulator simulator = new Simulator();
		simulators.put(simName, simulator);
	}

	/**
	 * @requires createSimulator(simName) 
     *           && channelName != null && channelName has not been used in a previous addChannel()  or
	 *           addParticipant() call on this object
	 *           limit > 0
	 * @modifies simulator named simName
	 * @effects Creates a new Channel named by the String channelName, with a limit, and add it to
	 *          the simulator named simName.
	 */
	public void addChannel(String simName, String channelName, int limit) {
		if (simName == null) throw new IllegalArgumentException("Given simName is null in addChannel");
		if (channelName == null) throw new IllegalArgumentException("Given channelName is null in addChannel");
		if (limit <= 0) throw new ArithmeticException("Given limit is not a positive integer in addChannel");
		Channel newChannel = new Channel(channelName, limit);
		simulators.get(simName).addPipe(newChannel, channelName);
	}

	/**
	 * @requires createSimulator(simName) && participantName != null 
	 *           && participantName has not been used in a previous addParticipant(), addChannel()
	 *           call on this object
	 *			 amount > 0
	 *			 product must be a single word, without special characters/number and also in lowercase
	 * @modifies simulator named simName
	 * @effects Creates a new Participant named by the String participantName and add
	 *          it to the simulator named simName.
	 */
	public void addParticipant(String simName, String participantName, String product, int amount) {
		if (simName == null) throw new IllegalArgumentException("Given simName is null in addParticipant");
		if (participantName == null) throw new IllegalArgumentException("Given channelName is null in addParticipant");
		if (product == null) throw new IllegalArgumentException("Given product is null in addParticipant");
		if (amount <= 0) throw new ArithmeticException("Given amount is not a positive integer in addParticipant");
		Participant newParticipant = new Participant(participantName, amount, product);
		simulators.get(simName).addFilter(newParticipant, participantName);
	}

	/**
	 * @requires createSimulator(simName) && ((addPipe(parentName) &&
	 *           addFilter(childName)) || (addFilter(parentName) &&
	 *           addPipe(childName))) && edgeLabel != null && node named
	 *           parentName has no other outgoing edge labeled edgeLabel 
	 *           && node named childName has no other incoming edge labeled edgeLabel
	 * @modifies simulator named simName
	 * @effects Adds an edge from the node named parentName to the node named
	 *          childName in the simulator named simName. The new edge's label
	 *          is the String edgeLabel.
	 */
	public void addEdge(String simName, String parentName, String childName, String edgeLabel) {
		if (simName == null) throw new IllegalArgumentException("Given simName is null in addEdge");
		if (parentName == null) throw new IllegalArgumentException("Given parentName is null in addEdge");
		if (childName == null) throw new IllegalArgumentException("Given childName is null in addEdge");
		if (edgeLabel == null) throw new IllegalArgumentException("Given edgeLabel is null in addEdge");
		simulators.get(simName).addEdge(parentName,childName,edgeLabel);
	}

	/**
	 * @requires createSimulator(simName) && addChannel(channelName)
	 *           A transaction homework2.Transaction != null
	 * @modifies channel named channelName
	 * @effects pushes the homework2.Transaction into the channel named channelName in the
	 *          simulator named simName.
	 */
	public void sendTransaction(String simName, String channelName, Transaction tx) {
		if (simName == null) throw new IllegalArgumentException("Given simName is null in sendTransaction");
		if (channelName == null) throw new IllegalArgumentException("Given channelName is null in sendTransaction");
		if (tx == null) throw new IllegalArgumentException("Given tx is null in sendTransaction");
		simulators.get(simName).injectInput(channelName,tx);
    }

	/**
	 * @requires addChannel(channelName)
	 * @return a space-separated list of the homework2.Transaction values currently in the
	 *         channel named channelName in the simulator named simName.
	 */
	public String listContents(String simName, String channelName) {
		if (simName == null) throw new IllegalArgumentException("Given simName is null in listContents");
		if (channelName == null) throw new IllegalArgumentException("Given channelName is null in listContents");
		ArrayList<Transaction> txList = simulators.get(simName).getPipeByLabel(channelName).getNode().getContents();
		List<Integer> contents = new ArrayList<Integer>();
		String contentsString = "";
		Iterator<Transaction> iter = txList.iterator();
		while(iter.hasNext()) {
			contents.add(iter.next().getAmount());
		}
		Iterator<Integer> iterInt = contents.iterator();
		while(iterInt.hasNext()) {
			contentsString += iterInt.next().toString();
			if(iterInt.hasNext()) contentsString += " ";
		}
		return contentsString;
	}

	/**
	 * @requires addParticipant(participantName)
	 * @return The sum of all homework2.Transaction amount of stored products that one has in his storage buffer.
	 */
	public int getParticipantStorageAmount(String simName, String participantName) {
		if (simName == null)
			throw new IllegalArgumentException("Given simName is null in getParticipantStorageAmount");
		if (participantName == null)
			throw new IllegalArgumentException("Given participantName is null in getParticipantStorageAmount");
		int storageBufferAmount =
				simulators.get(simName).getFilterByLabel(participantName).getNode().getStorageBufferAmount();
		return storageBufferAmount;
	}

	/**
	 * @requires addParticipant(participantName)
	 * @return The sum of all homework2.Transaction amount of waiting to be recycled products that one has.
	 */
	public int getParticipantToRecycleAmount(String simName, String participantName) {
		if (simName == null)
			throw new IllegalArgumentException("Given simName is null in getParticipantToRecycleAmount");
		if (participantName == null)
			throw new IllegalArgumentException("Given participantName is null in getParticipantToRecycleAmount");
		int objectsToPassAmount =
				simulators.get(simName).getFilterByLabel(participantName).getNode().getObjectsToPassAmount();
		return objectsToPassAmount;
	}
	
	/**
	 * @requires createSimulator(simName)
	 * @modifies simulator named simName
	 * @effects runs simulator named simName for a single time slice.
	 */
	public void simulate(String simName) {
		if (simName == null) throw new IllegalArgumentException("Given simName is null in simulate");
		simulators.get(simName).simulate();
	}

	/**
	 * Prints all the edges.
	 *
	 * @requires simName the sim name.
	 * @modifies none.
	 * @effects Prints all the edges.
	 */
	public void printAllEdges(String simName) {
		if (simName == null) throw new IllegalArgumentException("Given simName is null in printAllEdges");
		Collection<Edge<String>> edges = simulators.get(simName).getEdges();
		for (Edge currEdge : edges) {
			System.out.println("Edge: " + currEdge.getLabel() + ": Parent: " + currEdge.getParent() + ", Child: " + currEdge.getChild());
		}
	}
}
