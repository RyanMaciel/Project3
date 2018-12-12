/**
* Name: Jonah Davis and Ryan Maciel, 
* NetID: jdavis65 rmaciel2
* Assignment: Project 3
*/
package project.pkg3;

class Edge {
  public Vertex vert1;
  public Vertex vert2;
  public double w;


  public Edge(Vertex v1, Vertex v2, double wp){
  	vert1 = v1;
  	vert2 = v2;
  	w = wp;
  }

  public Vertex otherVert(Vertex v){
  	if(v == vert1){
  		return vert2;
  	}
  	return vert1;
  }
}
