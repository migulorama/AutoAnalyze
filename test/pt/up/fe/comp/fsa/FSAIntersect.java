package pt.up.fe.comp.fsa;

import org.junit.Ignore;
import org.junit.Test;

import java.util.LinkedHashSet;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.*;

public class FSAIntersect {

    @Test
    public void TestGenericIntersection() {
        try {
            FSA aut1 = new FSA("aut1", "q0", new LinkedHashSet<String>()); //(abc)*
            aut1.addEdge("q0", 'a', "q0");
            aut1.addEdge("q0", 'b', "q0");
            aut1.addEdge("q0", 'c', "q0");
            aut1.addFinalState("q0");

            FSA aut2 = new FSA("aut1", "q0", new LinkedHashSet<String>()); //(ab)*
            aut2.addEdge("q0", 'a', "q0");
            aut2.addEdge("q0", 'b', "q0");
            aut2.addEdge("q0", 'c', "q3");
            aut2.addFinalState("q0");

            FSA aut3 = new FSA("aut1", "q0", new LinkedHashSet<String>()); //a*
            aut3.addEdge("q0", 'a', "q0");
            aut3.addEdge("q0", 'b', "q3");
            aut3.addEdge("q0", 'c', "q3");
            aut3.addFinalState("q0");

            FSA automaton = aut1.intersect(aut2).intersect(aut3);

            automaton.minimize();
            System.out.println(automaton);

            assertTrue(automaton.accepts("a"));
            assertTrue(automaton.accepts("aa"));
            assertTrue(automaton.accepts(""));

            assertFalse(automaton.accepts("b"));
            assertFalse(automaton.accepts("bb"));
            assertFalse(automaton.accepts("c"));
            assertFalse(automaton.accepts("cc"));

        } catch (FSAException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void TestIntersectionWithComplement() {
        try {
            FSA aut1 = new FSA("aut1", "q0", new LinkedHashSet<String>());
            aut1.addEdge("q0", 'e', "q1");
            aut1.addEdge("q1", 'f', "q2");
            aut1.addEdge("q2", 'a', "q3");
            aut1.addEdge("q3", 'c', "q4");
            aut1.addEdge("q4", 'l', "q5");
            aut1.addFinalState("q2");

            FSA aut1complement = new FSA(aut1);
            aut1complement.complement();

            FSA automaton = aut1.intersect(aut1complement);

            assertTrue(automaton.doesNotAcceptAnything());
        } catch (FSAException e) {
            e.printStackTrace();
            fail();
        }
    }
}
