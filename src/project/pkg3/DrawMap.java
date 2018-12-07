package project.pkg3;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JFrame;

public class DrawMap extends Canvas
{
    ArrayList<Vertex> verts;
    float maxLon, maxLat, minLon, minLat;
    ArrayList<Vertex> vertPath;
    
    public void drawPath(ArrayList<Vertex> verts){
        vertPath = verts;
    }

    public void paint(Graphics g){
        int h = getHeight();
        int w = getWidth();
        float coordWidth = maxLon - minLon;
        float coordHeight = maxLat - minLat;
        
        g.setColor(Color.BLACK);
        
        for(Vertex v : verts){
            for(Edge e : v.adjacents){
                g.drawLine((int)((coordWidth - maxLon + e.vert1.lon) / coordWidth * w * .9 + (.05 * w)), (int)((maxLat - e.vert1.lat) / coordHeight * h * .9 + (.05 * h)), 
                           (int)((coordWidth - maxLon + e.vert2.lon) / coordWidth * w * .9 + (.05 * w)), (int)((maxLat - e.vert2.lat) / coordHeight * h * .9 + (.05 * h)));
                
            }
        }
        if(vertPath != null) {

            g.setColor(Color.RED);
            for(int i = 0; i < vertPath.size()-1; i++){
                Vertex vert1 = vertPath.get(i);
                Vertex vert2 = vertPath.get(i+1);
                g.drawLine((int)((coordWidth - maxLon + vert1.lon) / coordWidth * w * .9 + (.05 * w)), (int)((maxLat - vert1.lat) / coordHeight * h * .9 + (.05 * h)), 
                           (int)((coordWidth - maxLon + vert2.lon) / coordWidth * w * .9 + (.05 * w)), (int)((maxLat - vert2.lat) / coordHeight * h * .9 + (.05 * h)));
                
            }
        }
    }
    
    public DrawMap(ArrayList<Vertex> arr, float maxLong, float maxLati, float minLong, float minLati){
        verts = arr;
        maxLon = maxLong;
        maxLat = maxLati;
        minLon = minLong;
        minLat = minLati;
        JFrame win = new JFrame("Map");
        win.setSize(800, 600);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.add(this);
        win.setVisible(true);
    }
}