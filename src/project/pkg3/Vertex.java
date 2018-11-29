package project.pkg3;

public class Vertex {
    int d;
    Vertex par;
    
    /**
     * Default class constructor.
     */
    public Vertex(){
        d = Integer.MAX_VALUE;
        par = null;
    }
    
    /**
     * Class constructor. Sets distance to n
     * @param n the distance from this vertex to the source
     */
    public Vertex(int n, Vertex parent){
        d = n;
        par = parent;
    }
}
