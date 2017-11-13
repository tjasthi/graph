package trip;

/* You MAY add public @Test methods to this class.  You may also add
 * additional public classes containing "Testing" in their name. These
 * may not be part of your trip package per se (that is, it must be
 * possible to remove them and still have your package work). */

import org.junit.Test;
import ucb.junit.textui;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/** Unit tests for the trip package. */
public class UnitTest {

    /** Run all JUnit tests in the graph package. */
    public static void main(String[] ignored) {
        System.exit(textui.runClasses(trip.UnitTest.class));
    }

    @Test
    public void reportSegmentTest() {
        double distance = 0;
        int seq = 0;
        String output = "";

        ArrayList<Integer> segment = new ArrayList<>();
        segment.add(1);
        segment.add(2);
        segment.add(2);
        segment.add(3);
        segment.add(4);


        int k = segment.get(0);
        int j = 0;
        for (int i = 1; i < segment.size(); i += 1) {
            j = segment.get(i);

            int segmentDistance = j - k;

            if (i > 1) {
                if (j != k) {
                    distance = (Math.round((distance * 10))) / 10.0;
                    output += seq + ". Take " + k + " " + " to "
                            + j + " for " + distance + " miles.\n";
                    distance = 0;
                    seq += 1;
                }
                k = j;
            }
            distance += segmentDistance;
        }
        assertEquals(output, "0. Take 1  to 2 for 1.0 miles.\n"
                + "1. Take 2  to 3 for 1.0 miles.\n"
                + "2. Take 3  to 4 for 1.0 miles.\n");
    }
}
