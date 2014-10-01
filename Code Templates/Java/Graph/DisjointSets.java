
/**
 *
 * @author Chamal Lakshika Perera
 * @license published under public domain
 */
/**
 * This is the implementation of Disjoint Set data structure.
 */
public class DisjointSets {

    /**
     * This method creates a new set with a given element
     *
     * @param x element which the new set should be created to include
     */
    public void makeSet(Node x) {
        x.parent = x;
        x.rank = 0;
    }

    /**
     * This method finds the set of a given element
     *
     * @param x element which included set should be found
     * @return parent node of the set
     */
    public Node find(Node x) {
        if (x.parent != x) {
            x.parent = find(x.parent);
        }
        return x.parent;
    }

    /**
     * This method joins two sets which includes two given elements if they are
     * not in the same set
     *
     * @param x first given element
     * @param y second given element
     */
    public void union(Node x, Node y) {
        Node xRoot = find(x);
        Node yRoot = find(y);
        if (xRoot.equals(yRoot)) {
            return;
        }
        if (xRoot.rank < yRoot.rank) {
            xRoot.parent = yRoot;
        } else if (xRoot.rank > yRoot.rank) {
            yRoot.parent = xRoot;
        } else {
            yRoot.parent = xRoot;
            xRoot.rank = xRoot.rank + 1;
        }
    }
}
