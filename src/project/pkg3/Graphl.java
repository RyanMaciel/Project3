/**
* Name: Jonah Davis and Ryan Maciel, 
* NetID: jdavis65 rmaciel2
* Assignment: Project 3
*/
package project.pkg3;

import java.util.*;

/**
 * Class representing a graph. Vertices are stored in a list with edges stored 
 * in lists within Vertices
 */
class Graphl {
  public ArrayList<Vertex> verts = new ArrayList<Vertex>();

  /**
   * Class constructor.
   */
  public Graphl(){

  }
  
  /**
   * Adds a Vertex to the graph.
   * @param newVert the Vertex to be added 
   */
  public void addVert(Vertex newVert){
    verts.add(newVert);
  }
  
  /**
   * Adds an edge between two vertices.
   * @param source the first endpoint
   * @param destination the second endpoint
   * @param weight the weight of the edge
   */
  public void addEdge(int source, int destination, double weight){
    Vertex sourceVert = verts.get(source);
    Vertex destVert = verts.get(destination);
    if(sourceVert == null || destVert == null)System.out.println("NOPE");
    Edge newEdge = new Edge(sourceVert, destVert, weight);
    sourceVert.addAdjacentEdge(newEdge);
    destVert.addAdjacentEdge(newEdge);
  }
}
