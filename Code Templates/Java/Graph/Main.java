
/**
 *
 * @author Chamal Lakshika Perera
 * @license published under public domain
 */
public class Main {

    public static void main(String[] args) {
        Graph g = new Graph();
//        g.addEdge("A", "B", 7);
//        g.addEdge("A", "D", 5);
//        g.addEdge("B", "C", 8);
//        g.addEdge("B", "E", 7);
//        g.addEdge("B", "D", 9);
//        g.addEdge("D", "E", 15);
//        g.addEdge("D", "F", 6);
//        g.addEdge("C", "E", 5);
//        g.addEdge("E", "F", 8);
//        g.addEdge("E", "G", 9);
//        g.addEdge("F", "G", 11);
        
        g.addEdge("A", "B", 4);
        g.addEdge("A", "F", 8);
        g.addEdge("B", "C", 4);
        g.addEdge("B", "E", 2);
        g.addEdge("C", "D", 2);
        g.addEdge("D", "E", 6);
        g.addEdge("D", "F", 1);
        MST mst = g.kruskal();
        mst.printEdgeList();
    }
}
