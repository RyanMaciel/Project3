import java.util.*;

public class Vertex {
    int d;
    Vertex par;
    String name;
    ArrayList<Edge> adjacents = new ArrayList<Edge>();
    
    /**
     * Default class constructor.
     */
    public Vertex(String nameP){
        d = Integer.MAX_VALUE;
        par = null;
        name = nameP;
    }
    
    /**
     * Class constructor. Sets distance to n
     * @param n the distance from this vertex to the source
     */
    public Vertex(int n, Vertex parent, String nameP){
        d = n;
        par = parent;
        name = nameP;
    }

    public void addAdjacentEdge(Edge adjEdge){
        adjacents.add(adjEdge);
    }
}
