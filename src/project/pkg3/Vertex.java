/**
* Name: Jonah Davis and Ryan Maciel, 
* NetID: rmaciel2
* Assignment: Project 3
*/

package project.pkg3;

import java.util.*;

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

    public void addAdjacentEdge(Edge adjEdge){
        adjacents.add(adjEdge);
    }
}
