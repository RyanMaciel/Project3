/** Class to read in road data and create a graph */
import java.util.*;

class GraphLoader {
	class Node{
		public float lat;
		public float lon;
		public String name;
		public Node(String nameP, float latP, float lonP){
			name = nameP;
			lat = latP;
			lon = lonP;
		}

	}
	public static void p(Object a){
		System.out.println(a);
	}
	public static float distance(float lat1, float lon1, float lat2, float lon2){
		return Math.sqrt(Math.pow(lat2-lat1, 2) + Math.pow(lon2 - lon1, 2));
	}
 	public static void main(String[] args){

 		GraphLoader loader = new GraphLoader();

		String fileName = args[0];
		In in = new In(fileName);

		int numIntersections = 0;
		Graph map;
			
		HashMap<String, Node> intersections = new HashMap<String, Node>();

		String nextString = in.readString();
		while(nextString.equals("r") == false){
			if(nextString.equals("i")){
				String intersectionName = in.readString();
				float lat = in.readFloat();
				float lon = in.readFloat();
				intersections.put(intersectionName, loader.new Node(intersectionName, lat, lon));
			}
			nextString = in.readString();
		}
		try{
			
		} catch(NoSuchElementException e){
			
		}

		p(numIntersections);
	}
}
