
package homework2;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * homework2.SimulatorTest contains JUnit block-box unit tests for Simulator.
 */

public class SimulatorTest {
//    @Test
//    public void testBasicFunctionality() {
//        homework2.SimulatorTestDriver driver = new SimulatorTestDriver();
//
//        //create a Simulator
//        driver.createSimulator("sim1");
//
//        //add participants and channel
//        driver.addParticipant("sim1", "p1", "wool", 10);
//        driver.addParticipant("sim1", "p2", "metal", 15);
//        driver.addChannel("sim1", "c1", 15);
//
//        //add edges between participants
//        driver.addEdge("sim1", "p1", "c1", "e1");
//        driver.addEdge("sim1", "c1", "p2", "e2");
//
//        //create transactions
//        Transaction t1 = new Transaction("wool", 7);
//        Transaction t2 = new Transaction("metal", 5);
//
//        //checks
//        driver.printAllEdges("sim1");
//        driver.sendTransaction("sim1", "c1", t1);
//        driver.sendTransaction("sim1", "c1", t2);
//        assertEquals("wrong contents", "7 5", driver.listContents("sim1", "c1"));
//        assertEquals("wrong storage amount", 0, driver.getParticipantStorageAmount("sim1", "p1"));
//        assertEquals("wrong recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p1"));
//
//        //run one step of simulation
//        driver.simulate("sim1");
//        assertEquals("wrong contents", "7", driver.listContents("sim1", "c1"));
//        assertEquals("wrong storage amount", 5, driver.getParticipantStorageAmount("sim1", "p2"));
//    }

    @Test
    public void testMultipleParticipantsAndChannels() {
        homework2.SimulatorTestDriver driver = new SimulatorTestDriver();

        //create a Simulator
        driver.createSimulator("sim1");

        //add participants and channel
        driver.addParticipant("sim1", "p1", "deathcrystals", 3);
        driver.addParticipant("sim1", "p2", "shleemies", 5);
        driver.addParticipant("sim1", "p3", "megaseeds", 7);
        driver.addParticipant("sim1", "p4", "meeseeksboxes", 10);
        driver.addChannel("sim1", "c1", 10);
        driver.addChannel("sim1", "c2", 10);
        driver.addChannel("sim1", "c3", 10);
        driver.addChannel("sim1", "c4", 10);

        //add edges between participants
        driver.addEdge("sim1", "p1", "c1", "e1");
        driver.addEdge("sim1", "c1", "p2", "e2");
        driver.addEdge("sim1", "p2", "c2", "e3");
        driver.addEdge("sim1", "c2", "p3", "e4");
        driver.addEdge("sim1", "p2", "c3", "e5");
        driver.addEdge("sim1", "c3", "p4", "e6");
        driver.addEdge("sim1", "p4", "c4", "e5");
        driver.addEdge("sim1", "c4", "p1", "e6");
        driver.addEdge("sim1", "p3", "c4", "e7");

        //create transactions
        Transaction t1 = new Transaction("deathcrystals", 10);
        Transaction t2 = new Transaction("shleemies", 3);
        Transaction t3 = new Transaction("megaseeds", 7);
        Transaction t4 = new Transaction("jerries", 1);

        //send transactions
        driver.sendTransaction("sim1", "c3", t1);
        driver.sendTransaction("sim1", "c1", t2);
        driver.sendTransaction("sim1", "c4", t3);
        driver.sendTransaction("sim1", "c1", t4);

        //assert contents
        assertEquals("wrong contents", "3 1", driver.listContents("sim1", "c1"));
        assertEquals("wrong contents", "", driver.listContents("sim1", "c2"));
        assertEquals("wrong contents", "10", driver.listContents("sim1", "c3"));
        assertEquals("wrong contents", "7", driver.listContents("sim1", "c4"));

        //run simulations and check
        driver.simulate("sim1");
        assertEquals("wrong contents", "3 7", driver.listContents("sim1", "c1"));
        assertEquals("wrong contents", "", driver.listContents("sim1", "c2"));
        assertEquals("wrong contents", "1", driver.listContents("sim1", "c3"));
        assertEquals("wrong contents", "10", driver.listContents("sim1", "c4"));
        assertEquals("wrong storage amount", 0, driver.getParticipantStorageAmount("sim1", "p1"));
        assertEquals("wrong recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p1"));
        assertEquals("wrong storage amount", 0, driver.getParticipantStorageAmount("sim1", "p2"));
        assertEquals("wrong recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p2"));
        assertEquals("wrong storage amount", 0, driver.getParticipantStorageAmount("sim1", "p3"));
        assertEquals("wrong recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p3"));
        assertEquals("wrong storage amount", 0, driver.getParticipantStorageAmount("sim1", "p4"));
        assertEquals("wrong recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p4"));

        driver.simulate("sim1");
        assertEquals("wrong contents", "3 7", driver.listContents("sim1", "c1"));
        assertEquals("wrong contents", "", driver.listContents("sim1", "c2"));
        assertEquals("wrong contents", "7", driver.listContents("sim1", "c3"));
        assertEquals("wrong contents", "1", driver.listContents("sim1", "c4"));
        assertEquals("wrong storage amount", 3, driver.getParticipantStorageAmount("sim1", "p1"));
        assertEquals("wrong recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p1"));
        assertEquals("wrong storage amount", 0, driver.getParticipantStorageAmount("sim1", "p2"));
        assertEquals("wrong recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p2"));
        assertEquals("wrong storage amount", 0, driver.getParticipantStorageAmount("sim1", "p3"));
        assertEquals("wrong recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p3"));
        assertEquals("wrong storage amount", 0, driver.getParticipantStorageAmount("sim1", "p4"));
        assertEquals("wrong recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p4"));

        driver.simulate("sim1");
        assertEquals("wrong contents", "3 1", driver.listContents("sim1", "c1"));
        assertEquals("wrong contents", "", driver.listContents("sim1", "c2"));
        assertEquals("wrong contents", "7", driver.listContents("sim1", "c3"));
        assertEquals("wrong contents", "7", driver.listContents("sim1", "c4"));
        assertEquals("wrong storage amount", 3, driver.getParticipantStorageAmount("sim1", "p1"));
        assertEquals("wrong recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p1"));
        assertEquals("wrong storage amount", 0, driver.getParticipantStorageAmount("sim1", "p2"));
        assertEquals("wrong recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p2"));
        assertEquals("wrong storage amount", 0, driver.getParticipantStorageAmount("sim1", "p3"));
        assertEquals("wrong recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p3"));
        assertEquals("wrong storage amount", 0, driver.getParticipantStorageAmount("sim1", "p4"));
        assertEquals("wrong recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p4"));
    }
}
