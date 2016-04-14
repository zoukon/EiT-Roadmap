
import java.awt.Canvas;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graphics extends Canvas {

      LinkedList edges;
      LinkedList nodes;

      public Graphics(ParseOSM map) {
            this.nodes = map.getNodes();
            this.edges = map.getEdges();
      }

      public Graphics(RoadGraph rg) {
            this.nodes = rg.nodes;
            this.edges = rg.edges;
      }
       public Graphics(LinkedList nodes, LinkedList edges) {
            this.nodes = nodes;
            this.edges = edges;
      }

      @Override
      public void paint(java.awt.Graphics g) {
            for (Object es : edges) {
                  DirectedEdge e = DirectedEdge.class.cast(es);
                  if (e.getType() != null && !e.getType().equals("pedestrian")) {
                        if (e.getType().equals("highway") || e.getType().equals("cycleway") || e.getType().equals("residential") || e.getType().equals("service")) {
                              g.setColor(Color.blue);
                        } else if (e.getType().equals("secondary")) {
                              g.setColor(Color.green);
                        } else {
                              g.setColor(Color.red);
                        }

                        // Visualization of OSM files
                        double fromLat = (e.from().getLat() - 63.3) * 8000 - 400;
                        double fromLon = (e.from().getLon() - 10.3) * 4000 - 100;

                        double toLat = (e.to().getLat() - 63.3) * 8000 - 400;
                        double toLon = (e.to().getLon() - 10.3) * 4000 - 100;

                        /*
                        
                        
                        double fromLat = (double)(e.from().getLat() - 571000);
                        double fromLon = (double)(e.from().getLon() - 7035000);

                        double toLat = (double)(e.to().getLat() - 571000);
                        double toLon = (double)(e.to().getLon() - 7035000);
                                
                         */
                        int x1 = (int) fromLat;
                        int y1 = (int) fromLon;

                        int x2 = (int) toLat;
                        int y2 = (int) toLon;

                        g.drawLine(x1, y1, x2, y2);
                        /*
                        List<GraphNode> intersections = e.getIntersections();
                        for (int i = 0; i < intersections.size(); i++) {
                              GraphNode gn = intersections.get(i);
                              g.drawOval((int)((gn.getLat()-63.3)*8000-400),(int)((gn.getLon()-10.3)*4000-100), 3, 3);
                        }
                                */
                  }

            }
            /**
            g.setColor(Color.red);
            for(Object gns : nodes){
                  GraphNode gn = GraphNode.class.cast(gns);
                  
                  if(gn.isIntersection()){
                        g.drawOval((int)((gn.getLat()-63.3)*8000-400),(int)((gn.getLon()-10.3)*4000-100), 3, 3);
                  }
            }
            * **/
      }

}
