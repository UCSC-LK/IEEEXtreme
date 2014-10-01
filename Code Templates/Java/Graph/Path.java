/**
 *
 * @author Chamal Lakshika Perera
 * @license published under public domain
 */
/**
 * This is the Path class which which is for implement the Bellman-Ford and Kruskal
 * algorithms.
 */
public class Path implements Comparable<Path> {

    public Vertex source;//source of path
    public Vertex destination;//destination of path
    public double weight;//weight of path

    /**
     * This construct a Path which is to store in Queue for Bellman-Ford
     *
     * @param u source vertex
     * @param v destination vertex
     * @param w weight of path
     */
    public Path(Vertex u, Vertex v, double w) {
        source = u;
        destination = v;
        weight = w;
    }
    @Override
    public int compareTo(Path rhs) {
        double otherCost = rhs.weight;
        return weight < otherCost ? -1 : weight > otherCost ? 1 : 0;
    }
}
