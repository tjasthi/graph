package graph;
import java.util.ArrayList;

/**
 * Represents a general unlabeled directed graph whose vertices are denoted by
 * positive integers. Graphs may have self edges.
 *
 * @author tapan.jasthi
 */
public class DirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public int inDegree(int v) {
        Vertex x = getVertex(v);
        if (x == null) {
            return 0;
        }
        return x.getParents().size();
    }

    @Override
    public int predecessor(int v, int k) {
        if (!contains(v)) {
            return 0;
        } else {
            if (k >= 0 && k < getVertex(v).getChildren().size()) {
                return getVertex(v).getParents().get(k);
            } else {
                return 0;
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iteration<Integer> predecessors(int v) {
        if (contains(v)) {
            return Iteration.iteration(getVertex(v).getParents());
        } else {
            ArrayList blank = new ArrayList<Integer>();
            return Iteration.iteration((Iterable<Integer>) blank);
        }
    }
}
