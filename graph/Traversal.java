package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Queue;

/** Implements a generalized traversal of a graph.  At any given time,
 *  there is a particular collection of untraversed vertices---the "fringe."
 *  Traversal consists of repeatedly removing an untraversed vertex
 *  from the fringe, visting it, and then adding its untraversed
 *  successors to the fringe.
 *
 *  Generally, the client will extend Traversal.  By overriding the visit
 *  method, the client can determine what happens when a node is visited.
 *  By supplying an appropriate type of Queue object to the constructor,
 *  the client can control the behavior of the fringe. By overriding the
 *  shouldPostVisit and postVisit methods, the client can arrange for
 *  post-visits of a node (as in depth-first search).  By overriding
 *  the reverseSuccessors and processSuccessor methods, the client can control
 *  the addition of neighbor vertices to the fringe when a vertex is visited.
 *
 *  Traversals may be interrupted or restarted, remembering the previously
 *  marked vertices.
 *  @author tapan.jasthi
 */

public abstract class Traversal {

    /** A Traversal of G, using FRINGE as the fringe. */
    protected Traversal(Graph G, Queue<Integer> fringe) {
        _G = G;
        _fringe = fringe;

        markedVerts = new ArrayList<>();
        postVerts = new ArrayList<>();
    }

    /** Unmark all vertices in the graph. */
    public void clear() {
        ArrayList<Integer> removeMe = markedVerts;
        markedVerts.removeAll(removeMe);
    }

    /** Initialize the fringe to V0 and perform a traversal. */
    public void traverse(Collection<Integer> V0) {
        _fringe.addAll(V0);

        while (!_fringe.isEmpty()) {
            int nextVert = _fringe.remove();

            if (!marked(nextVert)) {
                visitVert(nextVert);
            } else if (!postVerts.contains(nextVert)) {
                postVerts.add(nextVert);
                postVisit(nextVert);
            }
        }
    }

    /** Visits the specified vertex, nextVert, by adding
     *  it and its children to fringe. Helper to traverse.
     *  @param nextVert is the vertex to be traversed. */
    private void visitVert(int nextVert) {
        _fringe.add(nextVert);
        visit(nextVert);
        mark(nextVert);

        Iteration<Integer> children = _G.successors(nextVert);
        for (int child : children) {
            if (!marked(child)) {
                _fringe.add(child);
            }
        }
    }

    /** Initialize the fringe to { V0 } and perform a traversal. */
    public void traverse(int v0) {
        traverse(Arrays.<Integer>asList(v0));
    }

    /** Returns true iff V has been marked. */
    protected boolean marked(int v) {
        return markedVerts.contains(v);
    }

    /** Mark vertex V. */
    protected void mark(int v) {
        markedVerts.add(v);
    }

    /** Perform a visit on vertex V.  Returns false iff the traversal is to
     *  terminate immediately. */
    protected boolean visit(int v) {
        return true;
    }

    /** Return true if we should postVisit V after traversing its
     *  successors.  (Post-visiting generally is useful only for depth-first
     *  traversals, although we define it for all traversals.) */
    protected boolean shouldPostVisit(int v) {
        return false;
    }

    /** Revisit vertex V after traversing its successors.  Returns false iff
     *  the traversal is to terminate immediately. */
    protected boolean postVisit(int v) {
        return true;
    }

    /** Return true if we should schedule successors of V in reverse order. */
    protected boolean reverseSuccessors(int v) {
        return false;
    }

    /** Process successor V to U.  Returns true iff V is then to
     *  be added to the fringe.  By default, returns true iff V is unmarked. */
    protected boolean processSuccessor(int u, int v) {
        return !marked(v);
    }



    /** The graph being traversed. */
    private final Graph _G;
    /** The fringe. */
    protected final Queue<Integer> _fringe;

    /** ArrayList to store visited vertices. */
    private ArrayList<Integer> markedVerts;
    /** ArrayList to store postvisited vertices. */
    private ArrayList<Integer> postVerts;
}
