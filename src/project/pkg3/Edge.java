/**
* Name: Jonah Davis and Ryan Maciel, 
* NetID: jdavis65 rmaciel2
* Assignment: Project 3
*/
package project.pkg3;

/**
 * Class to store information about the edges of the graph.
 */
class Edge {
  public Vertex vert1;
  public Vertex vert2;
  public double w;

  /**
   * Class constructor. 
   * @param v1 the first endpoint 
   * @param v2 the second endpoint
   * @param wp the weight of the edge
   */
  public Edge(Vertex v1, Vertex v2, double wp){
  	vert1 = v1;
  	vert2 = v2;
  	w = wp;
  }
}
