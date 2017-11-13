package graph;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

/** Tests for Traversals.
 *  @author tapan.jasthi
 */

public class TraversalTesting {

    @Test
    public void testTraversalOne() {
        DirectedGraph dGraph = new DirectedGraph();
        dGraph.add();
        dGraph.add();
        dGraph.add();
        dGraph.add();
        dGraph.add();
        dGraph.add(5, 4);
        dGraph.add(1, 5);
        dGraph.add(5, 3);
        dGraph.add(3, 2);
        dGraph.add(4, 1);

        DFS current = new DFS(dGraph);
        ArrayList<Integer> pathway = new ArrayList<>();
        pathway.add(5);
        pathway.add(3);
        pathway.add(2);
        pathway.add(4);
        pathway.add(1);
        current.traverse(5);
        assertEquals(current.getVisited(), pathway);
    }

    @Test
    public void tesTraversalTwo() {
        DirectedGraph dGraph = new DirectedGraph();
        dGraph.add();
        dGraph.add();
        dGraph.add();
        dGraph.add();
        dGraph.add();
        dGraph.add(5, 4);
        dGraph.add(5, 3);
        dGraph.add(1, 5);
        dGraph.add(3, 2);
        dGraph.add(4, 1);

        DFS current = new DFS(dGraph);
        ArrayList<Integer> pathway = new ArrayList<>();
        pathway.add(2);
        pathway.add(3);
        pathway.add(1);
        pathway.add(4);
        pathway.add(5);
        current.traverse(5);
        assertEquals(current.getpostVisited(), pathway);
    }

    class DFS extends DepthFirstTraversal {
        DFS(Graph G) {
            super(G);
            visit = new ArrayList<>();
            post = new ArrayList<>();
        }
        @Override
        protected boolean visit(int v) {
            visit.add(v);
            return true;
        }
        @Override
        protected boolean postVisit(int v) {
            post.add(v);
            return true;
        }
        protected ArrayList<Integer> getVisited() {
            return visit;
        }
        protected ArrayList<Integer> getpostVisited() {
            return post;
        }
        private ArrayList<Integer> visit;
        private ArrayList<Integer> post;
    }
}
