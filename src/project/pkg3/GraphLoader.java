
/** Class to read in road data and create a graph */
import java.util.*;
class GraphLoader {
	class Node{
		public float lat;
		public float lon;
		public String name;
		public int index;
		public Node(String nameP, float latP, float lonP, int indexP){
			name = nameP;
			lat = latP;
			lon = lonP;
			index = indexP;
		}

	}
	class Road{
		public Node source;
		public Node dest;
		public String name;
		public Road(Node sourceP, Node destP, String nameP){
			source = sourceP;
			dest = destP;
			name = nameP;
		}
	}
	public static void p(Object a){
		System.out.println(a);
	}
	public static double distance(Node source, Node dest){
		return Math.sqrt(Math.pow(dest.lat-source.lat, 2) + Math.pow(dest.lon - source.lon, 2));
	}
 	public static void main(String[] args){

 		GraphLoader loader = new GraphLoader();
 		Graphl map = new Graphl();
		String fileName = args[0];
		In in = new In(fileName);

		int numIntersections = 0;
		//Graph map;
			
		HashMap<String, Node> intersections = new HashMap<String, Node>();

		// Handle all of the intersections, adding them to the above hashmap
		// Which allows them to be accessed easily by name to build roads.
		String nextString = in.readString();
		int currentIndex = 0;
		while(nextString.equals("r") == false){
			if(nextString.equals("i")){
				String intersectionName = in.readString();
				float lat = in.readFloat();
				float lon = in.readFloat();
				intersections.put(intersectionName, loader.new Node(intersectionName, lat, lon, currentIndex));

				// Add vertex
				map.addVert(new Vertex(intersectionName));

				currentIndex++;
			}
			nextString = in.readString();
		}
		try{
			while(nextString.equals("r")){
				String roadName = in.readString();
				String sourceName = in.readString();
				String destName = in.readString();
				Node source = intersections.get(sourceName);
				Node dest = intersections.get(destName);
				loader.new Road(source, dest, roadName);
				/**
					Add edge here:
					graph.addEdge(source.index, dest.index, distance(source, dest))
				*/

				map.addEdge(source.index, dest.index, distance(source, dest));

				nextString = in.readString();
			}
		} catch(NoSuchElementException e){
			
		}

		p(numIntersections);
	}
}
