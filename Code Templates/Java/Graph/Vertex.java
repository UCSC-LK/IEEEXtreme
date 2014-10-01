
import java.util.LinkedList;

/**
 *
 * @author Chamal Lakshika Perera
 * @license published under public domain
 */
/**
 * This is the Vertex class which used for implement the Graph.
 */
public class Vertex implements Comparable<Vertex> {

    public String name;//name of vertex
    public LinkedList<Edge> adj;//adjacent vertex list
    public double distance;//store the distance from source
    public Vertex parent;//parent vertex.
    public String color;//variable used in algorithm
    public int discoveryTime;//discovery time in dfs
    public int finishTime;//finish time in dfs

    /**
     * this constructs a vertex
     *
     * @param n name of the vertex, to be constructed
     */
    public Vertex(String n) {
        name = n;
        adj = new LinkedList<Edge>();
        distance = Graph.INFINITY;
        parent = null;
        color = Graph.WHITE;
        discoveryTime = 0;
        finishTime = 0;
    }

    /**
     * this method compares current vertex with another vertex
     *
     * @param rhs other vertex to be compared with
     * @return the corresponding integer value according to the comparison
     */
    @Override
    public int compareTo(Vertex rhs) {
        double otherCost = rhs.distance;
        return distance < otherCost ? -1 : distance > otherCost ? 1 : 0;
    }
}
