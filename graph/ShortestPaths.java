package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/** The shortest paths through an edge-weighted graph.
 *  By overrriding methods getWeight, setWeight, getPredecessor, and
 *  setPredecessor, the client can determine how to represent the weighting
 *  and the search results.  By overriding estimatedDistance, clients
 *  can search for paths to specific destinations using A* search.
 *  @author tapan.jasthi
 */
public abstract class ShortestPaths {

    /** The shortest paths in G from SOURCE. */
    public ShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public ShortestPaths(Graph G, int source, int dest) {
        _G = G;
        _source = source;
        _dest = dest;

        nextVertices = new PriorityQueue<>(_G.vertexSize(), new PathComparer());
    }

    /** Initialize the shortest paths.  Must be called before using
     *  getWeight, getPredecessor, and pathTo. */
    public void setPaths() {
        setWeight(getDest(), Integer.MAX_VALUE);

        setWeight(getSource(), 0);
        setPredecessor(getSource(), 0);

        for (int vertex : _G.vertices()) {
            nextVertices.add(vertex);
        }
        while (!nextVertices.isEmpty()) {
            int vert = nextVertices.remove();
            if (vert == _dest) {
                return;
            }
            for (int child : _G.successors(vert)) {
                compareWeights(vert, child);
            }
        }
    }

    /** Examines the weight of the path of the given path
     * from predecessor to successor. If the weight based on
     * the heuristic and path length is less than the initial
     * weight, the path is updated and the next vertex is
     * added to the priority queue with its new weight.
     * Helper for the setPaths method.
     * @param predecessor is the start vertex.
     * @param successor is the second vertex.
     */
    private void compareWeights(int predecessor, int successor) {
        double samplePath = getWeight(predecessor)
                + getWeight(predecessor, successor);
        if (samplePath < getWeight(successor)) {
            setWeight(successor, samplePath);
            setPredecessor(successor, predecessor);

            nextVertices.remove(successor);
            nextVertices.add(successor);
        }
    }

    /** Returns the starting vertex. */
    public int getSource() {
        return _source;
    }

    /** Returns the target vertex, or 0 if there is none. */
    public int getDest() {
        return _dest;
    }

    /** Returns the current weight of vertex V in the graph.  If V is
     *  not in the graph, returns positive infinity. */
    public abstract double getWeight(int v);

    /** Set getWeight(V) to W. Assumes V is in the graph. */
    protected abstract void setWeight(int v, double w);

    /** Returns the current predecessor vertex of vertex V in the graph, or 0 if
     *  V is not in the graph or has no predecessor. */
    public abstract int getPredecessor(int v);

    /** Set getPredecessor(V) to U. */
    protected abstract void setPredecessor(int v, int u);

    /** Returns an estimated heuristic weight of the shortest path from vertex
     *  V to the destination vertex (if any).  This is assumed to be less
     *  than the actual weight, and is 0 by default. */
    protected double estimatedDistance(int v) {
        return 0.0;
    }

    /** Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     *  not in the graph, returns positive infinity. */
    protected abstract double getWeight(int u, int v);

    /** Returns a list of vertices starting at _source and ending
     *  at V that represents a shortest path to V.  Invalid if there is a
     *  destination vertex other than V. */
    public List<Integer> pathTo(int v) {
        List<Integer> pathway = new ArrayList<>();
        int destination = v;

        pathway.add(v);
        while (destination != getSource()) {
            int spot = getPredecessor(destination);
            pathway.add(spot);
            destination = spot;
        }
        Collections.reverse(pathway);
        return pathway;
    }

    /**
     * Returns a list of vertices starting at the source and ending at the
     * destination vertex. Invalid if the destination is not specified.
     */
    public List<Integer> pathTo() {
        return pathTo(getDest());
    }

    /** Comparing class for the Priorityqueue. */
    class PathComparer implements Comparator<Integer> {
        /** Comparing paths based on weight and heuristic. */
        @Override
        public int compare(Integer spot1, Integer spot2) {
            double firstValue = getWeight(spot1) + estimatedDistance(spot1);
            double secondValue = getWeight(spot2) + estimatedDistance(spot2);
            if (firstValue == secondValue) {
                return 0;
            }
            if (firstValue < secondValue) {
                return -1;
            }
            return 1;
        }
    }

    /** The graph being searched. */
    protected final Graph _G;
    /** The starting vertex. */
    private final int _source;
    /** The target vertex. */
    private final int _dest;

    /** Priorityqueue to get the next shortest path. */
    private PriorityQueue<Integer> nextVertices;
}
