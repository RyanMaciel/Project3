
import java.util.*;
class Graphl {
  public ArrayList<Vertex> verts = new ArrayList<Vertex>();

  public Graphl(){

  }
  public void addVert(Vertex newVert){
    verts.add(newVert);
  }

  public void addEdge(int source, int destination, double weight){
    Vertex sourceVert = verts.get(source);
    Vertex destVert = verts.get(destination);
    Edge newEdge = new Edge(sourceVert, destVert, weight);
    sourceVert.addAdjacentEdge(newEdge);
    destVert.addAdjacentEdge(newEdge);
  }

}
