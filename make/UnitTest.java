package make;

/* You MAY add public @Test methods to this class.  You may also add
 * additional public classes containing "UnitTest" in their name. These
 * may not be part of your make package per se (that is, it must be
 * possible to remove them and still have your package work). */

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import ucb.junit.textui;

/** Unit tests for the make package. */
public class UnitTest {

    /** Run all JUnit tests in the make package. */
    public static void main(String[] ignored) {
        System.exit(textui.runClasses(make.UnitTest.class));
    }

    @Test
    public void makeTest() {
        Maker maker = new Maker();

        assertEquals(maker.getGraph().vertexSize(), 0);
        assertEquals(maker.getGraph().edgeSize(), 0);

        maker.getGraph().add();
        assertEquals(maker.getGraph().vertexSize(), 1);
        assertEquals(maker.getGraph().edgeSize(), 0);
    }

}
