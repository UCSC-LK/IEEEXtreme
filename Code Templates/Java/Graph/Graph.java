
import java.util.*;

/**
 *
 * @author Chamal Lakshika Perera
 * @license published under public domain
 */
/**
 * This is the implementation of graph data structure
 */
public class Graph {

    public static final double INFINITY = Double.MAX_VALUE;//infinity value
    public HashMap<String, Vertex> vertices = new HashMap<String, Vertex>();//to store all the vertices
    public static final String WHITE = "White";//use to traversal, vertex has not seen yet
    public static final String BLACK = "Black";//use to traversal, vertex has finishedfinished the traversal with vertex
    public static final String GREY = "Grey";//use to traversal, vertex is currently traversing
    public static int time = 0;//time in dfs
    public PriorityQueue<Path> edges = new PriorityQueue<Path>();//to store all the edges

    /**
     * This method is to add directed weighted edge to graph
     *
     * @param firstVertex first vertex of the edge
     * @param secondVertex second vertex of the edge
     * @param cost weight of the edge
     */
    public void addEdge(String source, String dest, double cost) {
        Vertex v = getVertex(source);
        Vertex w = getVertex(dest);
        v.adj.add(new Edge(w, cost));
        edges.add(new Path(v, w, cost));
    }

    /**
     * this is to check, whether there is a path and control the printing if a
     * path exists
     *
     * @param dest name of vertex which a path must exist to
     */
    public void printPath(String dest) {

        Vertex w = vertices.get(dest);
        if (w == null) {
            throw new NoSuchElementException(dest);
        } else if (w.parent == null) {
            System.out.println(dest + " is unreachable");
        } else {
            System.out.print(" (cost is: " + w.distance + ")");
            //System.out.println("Path found");
            printPath(w);
            System.out.println();
        }

    }

    /**
     * this is to print a path in graph
     *
     * @param dest destination vertex which a path must exist to
     */
    private void printPath(Vertex dest) {
        if (dest.parent != null) {
            printPath(dest.parent);
            System.out.print(" to ");
        }
        System.out.print(dest.name);
    }

    /**
     * get a vertex by name. if not exist add a new vertex
     *
     * @param name name of the vertex that should be returned
     * @return vertex specified by parameter name
     */
    private Vertex getVertex(String name) {
        Vertex v = vertices.get(name);
        if (v == null) {
            v = new Vertex(name);
            vertices.put(name, v);
        }
        return v;
    }

    /**
     * this is to initialize depth first search
     *
     * @param sourceName name of starting vertex
     */
    public void dfs(String sourceName) {
        for (Vertex v : vertices.values()) {
            v.color = WHITE;
            v.parent = null;
        }
        time = 0;
        Vertex source = vertices.get(sourceName);
        if (source == null) {
            throw new NoSuchElementException(sourceName);
        }
        dfsVisit(source);
    }

    /**
     * this is to implement depth first search
     *
     * @param u starting vertex
     */
    public void dfsVisit(Vertex u) {
        u.color = GREY;
        time++;
        u.discoveryTime = time;
        for (Edge e : u.adj) {
            Vertex v = e.destination;
            if (v != null) {
                if (v.color.equals(WHITE)) {
                    v.parent = u;
                    dfsVisit(v);
                }
            } else {
                break;
            }
        }
        u.color = BLACK;
        time++;
        u.finishTime = time;
    }

    /**
     * this is to initialize breadth first search
     *
     * @param sourceName name of starting vertex
     */
    public void bfsInit(String sourceName) {
        Vertex source = vertices.get(sourceName);
        if (source == null) {
            throw new NoSuchElementException(sourceName);
        }
        bfs(source);

    }

    /**
     * this is to implement breadth first search
     *
     * @param u starting vertex
     */
    public void bfs(Vertex u) {
        for (Vertex v : vertices.values()) {
            v.color = WHITE;
            v.parent = null;
            v.distance = INFINITY;
        }
        u.color = GREY;
        u.distance = 0;
        u.parent = null;
        Queue<Vertex> q = new LinkedList<Vertex>();
        q.add(u);
        while (!q.isEmpty()) {
            u = q.remove();
            for (Edge e : u.adj) {
                Vertex v = e.destination;
                if (v.color.equals(WHITE)) {
                    v.color = GREY;
                    v.distance = u.distance + 1;
                    v.parent = u;
                    q.add(v);
                }
            }
            u.color = BLACK;
        }
    }

    /**
     * this is to initialize dijkstra's algorithm
     *
     * @param sourceName name of starting vertex
     */
    public void dijkstraInit(String sourceName) {
        Vertex source = vertices.get(sourceName);
        if (source == null) {
            throw new NoSuchElementException(sourceName);
        }
        dijkstra(source);

    }

    /**
     * this is to implement dijkstra's algorithm
     *
     * @param u starting vertex
     */
    public void dijkstra(Vertex u) {
        /**
         * Initializing single source
         */
        for (Vertex v : vertices.values()) {
            v.parent = null;
            v.distance = INFINITY;
        }
        u.distance = 0;
        /**
         * Adding all vertices in graph to min-priority queue
         */
        PriorityQueue<Vertex> mpq = new PriorityQueue<Vertex>();
        for (Vertex v : vertices.values()) {
            mpq.add(v);
        }
        /**
         * Relaxation
         */
        while (!mpq.isEmpty()) {
            u = mpq.remove();
            for (Edge e : u.adj) {
                Vertex v = e.destination;
                if (e.cost < 0) {
                    throw new RuntimeException("Graph has negative edges");
                }
                if (v.distance > u.distance + e.cost) {
                    v.distance = u.distance + e.cost;
                    v.parent = u;
                }
            }
        }
    }

    /**
     * this is to initialize Bellman-Ford algorithm
     *
     * @param sourceName name of starting vertex
     */
    public void bellmanFordInit(String sourceName) {
        Vertex source = vertices.get(sourceName);
        if (source == null) {
            throw new NoSuchElementException(sourceName);
        }
        bellmanFord(source);

    }

    /**
     * this is to implement Bellman-Ford algorithm
     *
     * @param u starting vertex
     */
    public void bellmanFord(Vertex u) {
        /**
         * Initializing single source
         */
        for (Vertex v : vertices.values()) {
            v.parent = null;
            v.distance = INFINITY;
        }
        u.distance = 0;
        /**
         * Adding all edges in graph to a linked list
         */
        LinkedList<Path> edgeList = new LinkedList<Path>();
        edgeList.addAll(edges);
        /**
         * Relaxation
         */
        for (int i = 0; i < vertices.size() - 1; i++) {
            for (int j = 0; j < edgeList.size(); j++) {
                Path p = edgeList.get(j);
                if (p.destination.distance > p.source.distance + p.weight) {
                    p.destination.distance = p.source.distance + p.weight;
                    p.destination.parent = p.source;
                }
            }
        }
        /**
         * check for negative-weighted cycles
         */
        for (int j = 0; j < edgeList.size(); j++) {
            Path p = edgeList.get(j);
            if (p.destination.distance > p.source.distance + p.weight) {
                throw new RuntimeException("Graph contains a negative-weighted cycle");
            }
        }
    }

    /**
     * this is the implementation of Kruskal algorithm which needs to generate a
     * Minimum Spanning Tree
     *
     * @return generated MST
     */
    public MST kruskal() {
        MST mst = new MST();
        PriorityQueue<Path> edgeList = new PriorityQueue<Path>();
        edgeList.addAll(edges);
        DisjointSets disjointSet = new DisjointSets();
        HashMap<Vertex, Node> nodes = new HashMap<Vertex, Node>();
        for (Vertex v : vertices.values()) {
            Node x = new Node(v.name);
            nodes.put(v, x);
            disjointSet.makeSet(x);
        }
        int edgeCount = edges.size();
        for (int i = 0; i < edgeCount; i++) {
            Path curPath = edgeList.remove();
            if (disjointSet.find(nodes.get(curPath.source)).equals(disjointSet.find(nodes.get(curPath.destination)))) {
            } else {
                mst.addEdge(curPath.source.name, curPath.destination.name, curPath.weight);
                disjointSet.union(nodes.get(curPath.source), nodes.get(curPath.destination));
            }

        }
        return mst;
    }

    /**
     * this method prints the edge list of a graph
     *
     */
    public void printEdgeList() {
        if (edges.isEmpty() == true) {
            System.out.println("No Edges");
        } else {
            PriorityQueue<Path> edgeList = new PriorityQueue<Path>();
            edgeList.addAll(edges);
            System.out.println("*-----------------*");
            System.out.println("*     Edge List   *");
            System.out.println("*-----------------*");

            for (int i = 0; i < edges.size(); i++) {
                Path curPath = edgeList.remove();
                System.out.println("*      " + curPath.source.name + " to " + curPath.destination.name + "     *");
                System.out.println("*-----------------*");
            }
        }
    }
}
