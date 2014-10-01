
/**
 *
 * @author Chamal Lakshika Perera
 * @license published under public domain
 */
public class Main {

    public static void main(String[] args) {
        DisjointSets dj = new DisjointSets();
        Node x = new Node("X");
        Node y = new Node("Y");
        Node z = new Node("Z");
        dj.makeSet(x);
        dj.makeSet(y);
        System.out.println(dj.find(y).parent.name);
        dj.makeSet(z);
        dj.union(x, y);
        System.out.println(dj.find(y).parent.name);
    }
}
