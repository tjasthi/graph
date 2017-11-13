package graph;

import java.util.ArrayList;

/** A partial implementation of ShortestPaths that contains the weights of
 *  the vertices and the predecessor edges.   The client needs to
 *  supply only the two-argument getWeight method.
 *  @author tapan.jasthi
 */
public abstract class SimpleShortestPaths extends ShortestPaths {

    /** The shortest paths in G from SOURCE. */
    public SimpleShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public SimpleShortestPaths(Graph G, int source, int dest) {
        super(G, source, dest);
        weights = new ArrayList<>();
        predecessorPairs = new ArrayList<>();
    }

    /** Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     *  not in the graph, returns positive infinity. */
    @Override
    public double getWeight(int v) {
        for (Weight w: weights) {
            if (w.getVertex() == v) {
                return w.getWeight();
            }
        }
        return Double.MAX_VALUE;
    }

    @Override
    protected void setWeight(int v, double w) {
        for (Weight weight: weights) {
            if (weight.getVertex() == v) {
                weight.setWeight(w);
                break;
            }
        }
        weights.add(new Weight(v, w));
    }

    @Override
    public int getPredecessor(int v) {
        for (int[] x: predecessorPairs) {
            if (x[0] == v) {
                return x[1];
            }
        }
        return 0;
    }

    @Override
    protected void setPredecessor(int v, int u) {
        for (int[] x: predecessorPairs) {
            if (x[0] == v) {
                x[1] = u;
                break;
            }
        }
        predecessorPairs.add(new int[] {v, u});
    }

    /** New class of weight. Specifies the related vertex and its weight. */
    class Weight {
        /** Vertex of the Weight. */
        private int vertex;
        /** Weight of the Weight. */
        private double weight;

        /** Contructor for a new Weight.
         * @param v is the vertex.
         * @param w is the weight.*/
        Weight(int v, double w) {
            this.vertex = v;
            this.weight = w;
        }

        /** Returns the vertex of the weight. */
        private int getVertex() {
            return vertex;
        }

        /** Returns the weight of the weight. */
        private double getWeight() {
            return weight;
        }

        /** Sets the weight of the weight.
         * @param w is the weight to be set. */
        public void setWeight(double w) {
            this.weight = w;
        }
    }

    /** Each element is a number representing a weight.
     * The element's index matches a vertex in the weightLabels ArrayList. */
    private ArrayList<Weight> weights;
    /** ArrayList object of array length 2.
     * Each array has predecessor in index 1 and successor in index 2. */
    private ArrayList<int[]> predecessorPairs;
}
