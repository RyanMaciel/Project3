/**
* Name: Jonah Davis and Ryan Maciel, 
* NetID: jdavis65 rmaciel2
* Assignment: Project 3
*/ 

package project.pkg3;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JFrame;
/**
 * Class to draw the map.
 */
public class DrawMap extends Canvas
{
    JFrame win;
    ArrayList<Vertex> verts;
    float maxLon, maxLat, minLon, minLat;
    ArrayList<Vertex> vertPath;
    
    /**
     * Sets the path to be highlighted
     * @param verts the vertices along the highlighted path
     */
    public void drawPath(ArrayList<Vertex> verts){
        vertPath = verts;
        win.repaint();
    }
    
    /**
     * Draws the map.
     * @param g 
     */
    public void paint(Graphics g){
        int h = getHeight();
        int w = getWidth();
        float coordWidth = maxLon - minLon;
        float coordHeight = maxLat - minLat;
        
        g.setColor(Color.BLACK);
        
        //Draws each edge
        for(Vertex v : verts){
            for(Edge e : v.adjacents){
                g.drawLine((int)((coordWidth - maxLon + e.vert1.lon) / coordWidth * w * .9 + (.05 * w)), (int)((maxLat - e.vert1.lat) / coordHeight * h * .9 + (.05 * h)), 
                           (int)((coordWidth - maxLon + e.vert2.lon) / coordWidth * w * .9 + (.05 * w)), (int)((maxLat - e.vert2.lat) / coordHeight * h * .9 + (.05 * h)));
                
            }
        }
        //Draws the shortest path, if one has been found
        if(vertPath != null) {

            g.setColor(Color.RED);
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(2));
            for(int i = 0; i < vertPath.size()-1; i++){
                Vertex vert1 = vertPath.get(i);
                Vertex vert2 = vertPath.get(i+1);
                g2.draw(new Line2D.Float((int)((coordWidth - maxLon + vert1.lon) / coordWidth * w * .9 + (.05 * w)), (int)((maxLat - vert1.lat) / coordHeight * h * .9 + (.05 * h)), 
                           (int)((coordWidth - maxLon + vert2.lon) / coordWidth * w * .9 + (.05 * w)), (int)((maxLat - vert2.lat) / coordHeight * h * .9 + (.05 * h))));
                
            }
        }
    }
    
    /**
     * Class constructor.
     * @param arr the array of vertices that make up the map
     * @param maxLong the max longitude
     * @param maxLati the max latitude
     * @param minLong the min longitude
     * @param minLati the min latitude
     */
    public DrawMap(ArrayList<Vertex> arr, float maxLong, float maxLati, float minLong, float minLati){
        verts = arr;
        maxLon = maxLong;
        maxLat = maxLati;
        minLon = minLong;
        minLat = minLati;
        win = new JFrame("Map");
        win.setSize(800, 600);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.add(this);
        win.setVisible(true);
    }
}
