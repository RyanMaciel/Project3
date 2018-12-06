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
}
