package graph;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;


/** Unit tests for the Graph class.
 *
 * Additional tess are added
 *
 */
public class GraphTesting {

    @Test
    public void emptyGraph() {
        DirectedGraph g = new DirectedGraph();
        assertEquals("Initial graph has vertices", 0, g.vertexSize());
        assertEquals("Initial graph has edges", 0, g.edgeSize());
    }

    @Test
    public void testUedgesize() {
        UndirectedGraph ug = new UndirectedGraph();
        ug.add();
        ug.add();
        ug.add();
        ug.add();
        ug.add(1, 3);
        ug.add(3, 1);
        ug.add(2, 3);
        assertEquals(2, ug.edgeSize());
    }

    @Test
    public void testDedgesize() {
        DirectedGraph ug = new DirectedGraph();
        ug.add();
        ug.add();
        ug.add();
        ug.add();
        ug.add(1, 3);
        ug.add(3, 1);
        ug.add(2, 3);
        assertEquals(3, ug.edgeSize());
    }

    @Test
    public void testadd() {
        DirectedGraph dg = new DirectedGraph();
        dg.add();
        dg.add();
        dg.add();
        dg.add();
        dg.add();
        dg.add();
        dg.add();
        assertEquals(true, dg.contains(1));
        assertEquals(7, dg.vertexSize());
        assertEquals(false, dg.contains(8));
    }

    @Test
    public void testOutgoing() {
        UndirectedGraph ug = new UndirectedGraph();
        ug.add();
        ug.add();
        ug.add();
        ug.add();
        ug.add(1, 1);
        ug.add(1, 2);
        ug.add(1, 3);
        ug.add(1, 4);
        assertEquals(4, ug.outDegree(1));
    }

    @Test
    public void uselfedges() {
        UndirectedGraph ug = new UndirectedGraph();
        ug.add();
        ug.add();
        ug.add();
        ug.add();
        ug.add(1, 1);
        ug.add(1, 2);
        ug.add(1, 3);
        ug.add(1, 4);
        ug.remove(1, 1);
        assertEquals(3, ug.outDegree(1));
        ug.add(1, 1);
        ug.remove(1);
        assertEquals(0, ug.outDegree(1));
        assertEquals(0, ug.outDegree(2));
    }

    @Test
    public void checkEdge() {
        UndirectedGraph ug = new UndirectedGraph();
        ug.add();
        ug.add(1, 1);
        assertEquals(1, ug.edgeSize());
    }

    @Test
    public void dcheckEdge() {
        DirectedGraph ug = new DirectedGraph();
        ug.add();
        ug.add(1, 1);
        assertEquals(1, ug.edgeSize());
    }

    @Test
    public void newedgesize() {
        UndirectedGraph ug = new UndirectedGraph();
        ug.add();
        ug.add();
        ug.add();
        ug.add();
        ug.add();
        ug.add(1, 1);
        ug.add(2, 2);
        ug.add(3, 3);
        ug.add(4, 4);
        ug.add(1, 2);
        ug.add(2, 4);
        ug.add(3, 4);
        ug.add(3, 2);
        ug.add(1, 3);
        assertEquals(9, ug.edgeSize());
    }

    @Test
    public void newedgesizeDir() {
        DirectedGraph ug = new DirectedGraph();
        ug.add();
        ug.add();
        ug.add();
        ug.add();
        ug.add(1, 1);
        ug.add(2, 2);
        ug.add(3, 3);
        ug.add(1, 2);
        ug.add(1, 3);
        ug.add(2, 3);
        ug.add(3, 2);
        ug.add(2, 4);
        ug.add(4, 2);
        ug.add(4, 3);
        ug.add(3, 4);
        assertEquals(11, ug.edgeSize());
    }

    @Test
    public void nedgesizeDir() {
        DirectedGraph ug = new DirectedGraph();
        ug.add();
        ug.add();
        ug.add();
        ug.add();
        ug.add(1, 1);
        ug.add(2, 2);
        ug.add(3, 3);
        ug.add(1, 2);
        ug.add(1, 3);
        ug.add(2, 3);
        ug.add(3, 2);
        ug.add(2, 4);
        ug.add(4, 2);
        ug.add(4, 3);
        ug.add(3, 4);
        ug.remove(1);
        assertEquals(8, ug.edgeSize());
        ug.remove(3);
        assertEquals(3, ug.edgeSize());
    }

    @Test
    public void unedgesizeDir() {
        UndirectedGraph ug = new UndirectedGraph();
        ug.add();
        ug.add();
        ug.add();
        ug.add();
        ug.add(1, 1);
        ug.add(2, 2);
        ug.add(3, 3);
        ug.add(1, 2);
        ug.add(1, 3);
        ug.add(2, 3);
        ug.add(3, 2);
        ug.add(2, 4);
        ug.add(4, 2);
        ug.add(4, 3);
        ug.add(3, 4);
        assertEquals(8, ug.edgeSize());
        ug.remove(1);
        assertEquals(5, ug.edgeSize());
        ug.remove(3);
        assertEquals(2, ug.edgeSize());
        ug.remove(4);
        assertEquals(1, ug.edgeSize());
    }


    @Test
    public final void testIsDirected() {
        DirectedGraph dg = new DirectedGraph();
        assertEquals(true, dg.isDirected());
    }

    @Test
    public final void testInOutDegree() {
        Graph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();

        g.add(1, 2);
        g.add(2, 5);
        g.add(5, 7);
        g.add(7, 6);
        g.add(3, 6);
        g.add(1, 3);
        g.add(1, 4);
        g.add(2, 4);
        g.add(5, 4);
        g.add(4, 3);
        g.add(4, 6);
        g.add(4, 7);
        assertEquals(3, g.inDegree(4));
        assertEquals(0, g.inDegree(1));
        assertEquals(3, g.inDegree(6));
        assertEquals(2, g.inDegree(3));
    }

    @Test
    public final void testOutDegree() {
        Graph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();

        g.add(1, 2);
        g.add(2, 5);
        g.add(5, 7);
        g.add(7, 6);
        g.add(3, 6);
        g.add(1, 3);
        g.add(1, 4);
        g.add(2, 4);
        g.add(5, 4);
        g.add(4, 3);
        g.add(4, 6);
        g.add(4, 7);
        assertEquals(3, g.outDegree(4));
        assertEquals(3, g.outDegree(1));
        assertEquals(0, g.outDegree(6));
        assertEquals(1, g.outDegree(3));
    }

    @Test
    public final void testPredecessorsIteration() {
        DirectedGraph dg = new DirectedGraph();
        dg.add();
        dg.add();
        dg.add();
        dg.add();
        ArrayList<Integer> result = new ArrayList<Integer>();
        dg.add(1, 2);
        dg.add(2, 3);
        dg.add(1, 4);
        dg.add(4, 2);
        for (int x : dg.predecessors(2)) {
            result.add(x);
        }
        int first = result.get(0);
        int second = result.get(1);
        assertEquals(2, result.size());
        assertEquals(1, first);
        assertEquals(4, second);
    }

    @Test
    public final void testVertexSize() {
        Graph g = new DirectedGraph();
        assertEquals(0, g.vertexSize());
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        assertEquals(6, g.vertexSize());

        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        assertEquals(13, g.vertexSize());
    }

    @Test
    public final void testMaxVertex() {
        Graph g = new DirectedGraph();
        assertEquals(0, g.maxVertex());
        g.add();
        assertEquals(1, g.maxVertex());
        g.add();
        assertEquals(2, g.maxVertex());
        g.add();
        assertEquals(3, g.maxVertex());

        g.remove(1);
        assertEquals(3, g.maxVertex());
        g.add();
        assertEquals(3, g.maxVertex());
        g.add();
        g.add();
        g.add();
        assertEquals(6, g.maxVertex());
    }

    @Test
    public final void testEdgeSize() {
        Graph g = new DirectedGraph();
        assertEquals(0, g.edgeSize());
        g.add();
        g.add();
        g.add();
        g.add();

        g.add(1, 2);
        g.add(1, 3);
        g.add(3, 2);
        assertEquals(3, g.edgeSize());
        g.add(3, 4);
        g.add(4, 3);
        assertEquals(5, g.edgeSize());
    }

    @Test
    public final void testContainsNode() {
        Graph g = new DirectedGraph();
        assertEquals(false, g.contains(2));
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.remove(4);
        assertEquals(false, g.contains(4));
        assertEquals(true, g.contains(5));
    }

    @Test
    public final void testContainsEdge() {
        Graph g = new DirectedGraph();
        assertEquals(false, g.contains(1));
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(1, 3);
        g.add(3, 2);
        g.add(3, 4);
        g.add(4, 3);

        assertEquals(false, g.contains(2, 1));
        assertEquals(true, g.contains(1, 2));
        assertEquals(true, g.contains(3, 4));
        assertEquals(true, g.contains(4, 3));
        assertEquals(false, g.contains(4, 1));
    }

    @Test
    public final void testAddNode() {
        Graph g = new DirectedGraph();
        assertEquals(false, g.contains(1));
        g.add();
        assertEquals(true, g.contains(1));
        g.add();
        g.add();
        assertEquals(true, g.contains(2));
        assertEquals(true, g.contains(3));
        g.remove(2);
        assertEquals(false, g.contains(2));
        g.add();
        assertEquals(true, g.contains(2));
    }

    @Test
    public final void testAddEdge() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();

        g.add(1, 2);
        assertEquals(false, g.contains(4, 5));
        g.add(2, 3);
        g.add(3, 4);
        g.add(4, 5);
        assertEquals(true, g.contains(1, 2));
        assertEquals(true, g.contains(4, 5));

    }

    @Test
    public final void testRemoveNode() {
        Graph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();

        g.remove(4);

        assertEquals(true, g.contains(1));
        assertEquals(false, g.contains(4));
        g.add();
        assertEquals(true, g.contains(4));
        g.remove(1);
        g.remove(2);
        g.remove(3);
        assertEquals(2, g.vertexSize());

    }

    @Test
    public final void testRemoveEdge() {
        Graph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(1, 3);
        g.add(3, 2);
        g.add(3, 4);
        g.add(4, 3);

        g.remove(1, 2);
        g.remove(2, 1);
        g.remove(3, 4);

        assertEquals(false, g.contains(1, 2));
        assertEquals(false, g.contains(3, 4));
        assertEquals(true, g.contains(4, 3));
        assertEquals(1, g.inDegree(2));
        assertEquals(2, g.inDegree(3));
        assertEquals(1, g.outDegree(3));
    }

    @Test
    public final void testVertices() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int x : g.vertices()) {
            result.add(x);
        }
        int first = result.get(0);
        int second = result.get(1);
        int third = result.get(2);
        int forth = result.get(3);
        assertEquals(1, first);
        assertEquals(2, second);
        assertEquals(3, third);
        assertEquals(4, forth);
    }

    @Test
    public final void testSuccessors() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(1, 3);
        g.add(1, 4);
        g.add(3, 4);
        assertEquals(2, g.successor(1, 0));
        assertEquals(3, g.successor(1, 1));
        assertEquals(4, g.successor(1, 2));
        assertEquals(0, g.successor(1, 3));
        assertEquals(0, g.successor(5, 0));
        assertEquals(0, g.successor(2, 0));
        assertEquals(4, g.successor(3, 0));
        assertEquals(0, g.successor(4, 0));
    }

    @Test
    public final void testSuccessorsIteration() {
        UndirectedGraph g = new UndirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        ArrayList<Integer> result = new ArrayList<>();
        g.add(1, 2);
        g.add(2, 3);
        g.add(1, 4);
        g.add(4, 2);
        for (int x : g.successors(1)) {
            result.add(x);
        }
        int first = result.get(0);
        int second = result.get(1);
        assertEquals(2, result.size());
        assertEquals(2, first);
        assertEquals(4, second);
    }
}
