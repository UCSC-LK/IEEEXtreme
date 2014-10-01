
/**
 *
 * @author Chamal Lakshika Perera
 * @license published under public domain
 */
/**
 * This is the Node class which used for implement the Disjoint Set.
 */
public class Node {

    public Node parent = null;//parent node
    public int rank = -1;//variable used for union
    String name;//name of node

    /**
     * This constructs a node
     *
     * @param nodeName name of the node
     */
    public Node(String nodeName) {
        name = nodeName;
    }
}
