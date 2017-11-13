package graph;

import java.util.ArrayList;

/** A partial implementation of Graph containing elements common to
 *  directed and undirected graphs.
 *
 *  @author tapan.jasthi
 */

abstract class GraphObj extends Graph {

    /** Vertex class that stores the value and its
     * children and parents lists. */
    class Vertex {
        /** Value of the vertex. */
        private int value;
        /** ArrayList of the vertex parents. */
        private ArrayList<Integer> parents;
        /** ArrayList of the vertex children. */
        private ArrayList<Integer> children;

        /** Vertex constructor.
         * @param n is the value of the vertex */
        private Vertex(int n) {
            value = n;
            parents = new ArrayList<>();
            children = new ArrayList<>();
        }

        /** Returns the value of the vertex. */
        int getValue() {
            return value;
        }

        /** Returns the children ArrayList of the vertex. */
        ArrayList<Integer> getChildren() {
            return children;
        }

        /** Returns the parent ArrayList of the vertex. */
        ArrayList<Integer> getParents() {
            return parents;
        }
    }

    /** Edge class that stores its number identification
     * and its start and end vertices. */
    class Edge {
        /** Identification number of the edge. */
        private int number;
        /** Start of the edge. */
        private int start;
        /** End of the edge. */
        private int end;

        /** Edge constructor.
         * @param u is the start vertex. *
         * @param v is the end vertex. */
        private Edge(int u, int v) {
            number = edgeId(u, v);
            start = u;
            end = v;
        }

        /** Returns the end int vertex of the edge. */
        int getEnd() {
            return end;
        }

        /** Returns the start int vertex of the edge. */
        int getStart() {
            return start;
        }
    }

    /** A new, empty Graph. */
    GraphObj() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    @Override
    public int vertexSize() {
        return vertices.size();
    }

    @Override
    public int maxVertex() {
        if (vertices.size() == 0) {
            return 0;
        }

        int maximum = Integer.MIN_VALUE;
        for (int i = 0; i < vertices.size(); i += 1) {
            maximum = Math.max(vertices.get(i).getValue(), maximum);
        }
        return maximum;
    }

    @Override
    public int edgeSize() {
        return edges.size();
    }

    @Override
    public abstract boolean isDirected();

    @Override
    public int outDegree(int v) {
        Vertex vert = getVertex(v);
        if (vert == null) {
            return 0;
        }
        return vert.getChildren().size();
    }

    @Override
    public abstract int inDegree(int v);

    @Override
    public boolean contains(int u) {
        if (getVertex(u) != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(int u, int v) {
        if (contains(u) && contains(v)) {
            if (getEdge(u, v) != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int add() {
        for (int i = 0; i < vertices.size(); i += 1) {
            Vertex v = vertices.get(i);
            if (v.value != i + 1) {
                Vertex vert = new Vertex(i + 1);
                vertices.add(i, vert);
                return i + 1;
            }
        }

        Vertex vert = new Vertex(vertices.size() + 1);
        vertices.add(vert);
        return vert.value;
    }


    @Override
    public int add(int u, int v) {
        if (contains(u, v)) {
            return edgeId(u, v);
        }

        Edge e = new Edge(u, v);

        Vertex uVert = getVertex(u);
        Vertex vVert = getVertex(v);
        if (isDirected()) {
            uVert.getChildren().add(v);
            vVert.getParents().add(u);
        } else {
            if (u != v) {
                uVert.getChildren().add(v);
            }
            vVert.getChildren().add(u);
        }

        edges.add(e);
        return e.number;
    }

    @Override
    public void remove(int v) {

        if (!contains(v)) {
            return;
        }

        for (int i = 0; i < edges.size(); i += 1) {
            Edge e = edges.get(i);
            if ((e.getStart() == v) || (e.getEnd() == v)) {
                remove(e.getStart(), e.getEnd());
                i -= 1;
            }
        }

        vertices.remove(getVertex(v));
    }

    @Override
    public void remove(int u, int v) {
        if (!(contains(u) || !contains(v))) {
            return;
        }

        if (!contains(u, v)) {
            return;
        }

        Vertex uVert = getVertex(u);
        Vertex vVert = getVertex(v);
        if (isDirected()) {
            uVert.getChildren().remove((Integer) v);
            vVert.getParents().remove((Integer) u);
        } else {
            uVert.getChildren().remove((Integer) v);
            vVert.getChildren().remove((Integer) u);
        }

        edges.remove(getEdge(u, v));
    }

    @Override
    public Iteration<Integer> vertices() {
        ArrayList<Integer> valueList = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i += 1) {
            Vertex v = vertices.get(i);
            valueList.add(v.getValue());
        }
        return Iteration.iteration(valueList);
    }

    @Override
    public int successor(int v, int k) {
        if (contains(v)) {
            if (k >= 0 && k < getVertex(v).getChildren().size()) {
                return getVertex(v).getChildren().get(k);
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    @Override
    public abstract int predecessor(int v, int k);

    @Override
    @SuppressWarnings("unchecked")
    public Iteration<Integer> successors(int v) {
        if (contains(v)) {
            return Iteration.iteration(getVertex(v).getChildren());
        } else {
            ArrayList blank = new ArrayList<Integer>();
            return Iteration.iteration((Iterable<Integer>) blank);
        }
    }

    @Override
    public abstract Iteration<Integer> predecessors(int v);

    @Override
    public Iteration<int[]> edges() {
        ArrayList<int[]> edgeData = new ArrayList<>();
        for (int i = 0; i < edges.size(); i += 1) {
            Edge e = edges.get(i);
            edgeData.add(new int[] {e.getStart(), e.getEnd()});
        }
        return Iteration.iteration(edgeData);
    }

    @Override
    protected void checkMyVertex(int v) {
        if (!contains(v)) {
            throw new NullPointerException("Not Found");
        }
    }

    @Override
    protected int edgeId(int u, int v) {
        int pairingPartOne = ((u + v) * (u + v + 1)) / 2;
        if (!isDirected()) {
            return pairingPartOne + Math.min(u, v);
        } else {
            return pairingPartOne + v;
        }
    }

    /** Given the vertex number, return the Vertex object.
     * @param v is the value of the vertex. */
    Vertex getVertex(int v) {
        for (int i = 0; i < vertices.size(); i += 1) {
            Vertex x = vertices.get(i);
            if (x.getValue() == v) {
                return x;
            }
        }
        return null;
    }

    /** Using vertices u and v, return the edge between them.
     * @param u is the value of the start vertex.
     * @param v is the value of the end vertex. */
    Edge getEdge(int u, int v) {
        for (int i = 0; i < edges.size(); i += 1) {
            Edge e = edges.get(i);
            if (e.number == edgeId(u, v)) {
                return e;
            }
        }
        return null;
    }

    /** ArrayList of vertices. */
    private ArrayList<Vertex> vertices;
    /** ArrayList of edges. */
    private ArrayList<Edge> edges;
}
