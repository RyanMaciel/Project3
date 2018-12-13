/**
* Name: Jonah Davis and Ryan Maciel, 
* NetID: jdavis65 rmaciel2
* Assignment: Project 3
*/

package project.pkg3;

import java.util.*;

/**
 * Class that stores information about a Vertex, including its position, name, distance 
 * from a source, and parent on the shortest path.
 * @author onahj
 */
public class Vertex{
    double d;
    Vertex par;
    String name;
    float lat;
    float lon;
    boolean visited = false;
    ArrayList<Edge> adjacents = new ArrayList<Edge>();
    
    /**
     * Default class constructor.
     */
    public Vertex(String nameP, float latitude, float longitude){
        d = Double.MAX_VALUE;
        par = null;
        name = nameP;
        lat = latitude;
        lon = longitude;
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
    
    /**
     * Add an edge to a neighboring Vertex
     * @param adjEdge the Edge to be added
     */
    public void addAdjacentEdge(Edge adjEdge){
        adjacents.add(adjEdge);
    }
}
