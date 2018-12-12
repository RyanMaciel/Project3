/**
* Name: Jonah Davis and Ryan Maciel, 
* NetID: jdavis65 rmaciel2
* Assignment: Project 3
*/
package project.pkg3;


/** Class to read in road data and create a graph */
import java.util.*;


/**
* The main class for the project. Should be run from the command line and takes
* in command line arguements in accordence with the project description. Parses
* variables from the input file, finds the shortest path and uses the DrawMap
* class to render it.
*/
class GraphLoader {

	/**
	* A class to represent an interection (this is different from the Vertex class.)
	*/
	class Node{
		public double lat;
		public double lon;
		public String name;
		public int index;
		public Node(String nameP, double latP, double lonP, int indexP){
			name = nameP;
			lat = latP;
			lon = lonP;
			index = indexP;
		}

	}

	/**
	* A class to represent roads (different from edge classes).
	*/
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


	/**
	* Haversine distance
	* https://rosettacode.org/wiki/Haversine_formula#Java
	*/
	public static final double R = 6372.8; // In kilometers
	public static double distance(Node source, Node dest) {
		double lat1 = source.lat;
		double lat2 = dest.lat;
		double lon1 = source.lon;
		double lon2 = dest.lon;
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);
 
		double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
		double c = 2 * Math.asin(Math.sqrt(a));
		return R * c;
	}
		

	/**
	* Implementation fo dijkstra's algorithm as discussed in class.
	*/
	public static void dijkstras(Graphl map, Vertex startVert, Vertex endVert){
		startVert.d=0;
		Vertex[] vertArr = new Vertex[map.verts.size()];
		for(int i = 0; i < vertArr.length; i++){
			vertArr[i] = map.verts.get(i);
		}
		
		VertMinHeap unvisited = new VertMinHeap(vertArr);
		unvisited.buildHeap();
		Vertex v = unvisited.removeMin();
		
		while(unvisited.size() > 0){
			for(Edge e : v.adjacents){
				Vertex u = e.vert1;
				if(u.equals(v)) u = e.vert2;

                if(u.d > v.d + e.w){
                    u.d = v.d + e.w;
                    u.par = v;
                    
                    unvisited.buildHeap();

                }
            }
            v = unvisited.removeMin();
        }

				}
			}
			unvisited.buildHeap();
			v = unvisited.removeMin();
			if(v.name.equals(endVert.name)) 
				break;
		}

	}
	public static void main(String[] args){

		String startIntersection = "", endIntersection = "";

		// If I don't initialize here the compiler complains
		Vertex startVert = new Vertex("", 0, 0);
		Vertex endVert = new Vertex("", 0, 0);

		// Determine if we should show map.
		boolean showMap = false;
		boolean calcDirections = false;
		for(String s : args){
			if(s.equals("--show")){
				showMap = true;
				break;
			}
		}
		//Case if only --directions is present
		if(args[1].equals("--directions")){
			calcDirections = true;
			startIntersection = args[2];
			endIntersection = args[3];
		} else if(args.length > 2 && args[2].equals("--directions")){
			calcDirections = true;
			startIntersection = args[3];
			endIntersection = args[4];
		}


		// We only need to initialize this to access the embedded node and rode classes.
		GraphLoader loader = new GraphLoader();
		Graphl map = new Graphl();
		String fileName = args[0];
		In in = new In(fileName);

		int numIntersections = 0;
			
		HashMap<String, Node> intersections = new HashMap<String, Node>();

		/**
		* Handle all of the intersections, adding them to the above hashmap
		* Which allows them to be accessed easily by name to build roads.
		*/
		String nextString = in.readString();
		int currentIndex = 0;

		// keep track of the farthest vertices of the graph for rendering.
		float maxLat = -1 * Float.MAX_VALUE;
		float maxLon = -1 * Float.MAX_VALUE;
		float minLon = Float.MAX_VALUE;
		float minLat = Float.MAX_VALUE;

		// Populate from the intersections.
		while(nextString.equals("r") == false){
			if(nextString.equals("i")){
				String intersectionName = in.readString();
				float lat = in.readFloat();
				float lon = in.readFloat();
				intersections.put(intersectionName, loader.new Node(intersectionName, lat, lon, currentIndex));

				// Add vertex
				Vertex newVert = new Vertex(intersectionName, lat, lon);
				if(intersectionName.equals(startIntersection)){
					startVert = newVert;
				}
				else if(intersectionName.equals(endIntersection)){
					endVert = newVert;
				}
				map.addVert(newVert);
				if(maxLat < lat) maxLat = lat;
				if(maxLon < lon) maxLon = lon;
				if(minLat > lat) minLat = lat;
				if(minLon > lon) minLon = lon;
				currentIndex++;
			}
			nextString = in.readString();
		}


		// Populate from roads.
		try{
			while(nextString.equals("r")){
				String roadName = in.readString();
				String sourceName = in.readString();
				String destName = in.readString();
				Node source = intersections.get(sourceName);
				Node dest = intersections.get(destName);
				loader.new Road(source, dest, roadName);

				map.addEdge(source.index, dest.index, distance(source, dest));

				nextString = in.readString();
			}
		} catch(NoSuchElementException e){
			
		}

		dijkstras(map, startVert, endVert);
        String route = "";
        Vertex traceVert = endVert;
        ArrayList<Vertex> pathVerts = new ArrayList<Vertex>();
        pathVerts.add(traceVert);
        while(traceVert.par != null){
            route += traceVert.name + ", ";
            traceVert = traceVert.par;
            pathVerts.add(traceVert);

			// Reverse the order of the path to print out.
			for(int i = pathVerts.size()-1; i >= 0; i--){
				route += ", " + pathVerts.get(i).name;
			}

			System.out.println("Route:" + route);
		}
		if(showMap){
			DrawMap mapRenderer = new DrawMap(map.verts, maxLon, maxLat, minLon, minLat);
			if(calcDirections){
				mapRenderer.drawPath(pathVerts);
			}
		}
	}
}
