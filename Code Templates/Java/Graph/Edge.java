
/**
 *
 * @author Chamal Lakshika Perera
 * @license published under public domain
 */
/**
 * This is the Edge class which used for implement the Graph.
 */
public class Edge {

    public Vertex destination;//destination vertex
    public double cost;//weight of edge

    /**
     * This construct an edge
     *
     * @param dest destination vertex
     * @param c cost of edge
     */
    public Edge(Vertex dest, double c) {
        destination = dest;
        cost = c;
    }

    /**
     * This construct an edge
     *
     * @param dest destination vertex
     */
    public Edge(Vertex dest) {
        destination = dest;
        cost = 1;
    }
}
