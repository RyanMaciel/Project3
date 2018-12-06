package project.pkg3;


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
                args = new String[5];
                args[0] = "nys.txt";
                args[1] = "--show";
                args[2] = "--directions";
                args[3] = "i10";
                args[4] = "i42";
                
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
                float maxLat = -1 * Float.MAX_VALUE;
                float maxLon = -1 * Float.MAX_VALUE;
                float minLon = Float.MAX_VALUE;
                float minLat = Float.MAX_VALUE;
		while(nextString.equals("r") == false){
			if(nextString.equals("i")){
				String intersectionName = in.readString();
				float lat = in.readFloat();
				float lon = in.readFloat();
				intersections.put(intersectionName, loader.new Node(intersectionName, lat, lon, currentIndex));

				// Add vertex
				map.addVert(new Vertex(intersectionName, lat, lon));
                                if(maxLat < lat) maxLat = lat;
                                if(maxLon < lon) maxLon = lon;
                                if(minLat > lat) minLat = lat;
                                if(minLon > lon) minLon = lon;
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
                
                String startIntersection = "", endIntersection = "";
                
                if(args[1].equals("--show")){
                    new DrawMap(map.verts, maxLon, maxLat, minLon, minLat);
                    
                    //Case if --show and --directions are present
                    if(args.length > 2){
                        startIntersection = args[3];
                        endIntersection = args[4];
                    }
                }
                //Case if only --directions is present
                else{
                    startIntersection = args[2];
                    endIntersection = args[3];
                }
                
                for(Vertex v : map.verts){
                    if(v.name.equals(startIntersection)){
                        v.d = 0;
                        break;
                    }
                }
                
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
                        }
                    }
                    
                    unvisited.buildHeap();
                    v = unvisited.removeMin();
                    if(v.name.equals(endIntersection)) 
                        break;
                }
                
                String route = "";
                while(v.par != null){
                    route += v.name + ", ";
                    v = v.par;
                }
                
                route += v.name;
                
                p(route);
	}
}
