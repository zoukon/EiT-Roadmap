
import java.awt.Canvas;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;

public class Graphics extends Canvas {

      LinkedList edges;
      LinkedList nodes;

      public Graphics(ParseOSM map) {
            this.nodes = map.getNodes();
            this.edges = map.getEdges();
      }

      @Override
      public void paint(java.awt.Graphics g) {
            for (Object es : edges) {
                  DirectedEdge e = DirectedEdge.class.cast(es);
                  if (e.getType() != null && !e.getType().equals("pedestrian")) {
                        if (e.getType().equals("footway")) {
                              g.setColor(Color.blue);
                        } else if (e.getType().equals("path")) {
                              g.setColor(Color.green);
                        } else if (e.getType().equals("cycleway")) {
                              g.setColor(Color.yellow);
                        } else if (e.getType().equals("unclassified")) {
                              g.setColor(Color.black);
                        } else {
                              g.setColor(Color.red);
                        }

                        double fromLat = (e.from().getLat() - 63.3) * 8000 - 400;
                        double fromLon = (e.from().getLon() - 10.3) * 4000 - 100;

                        double toLat = (e.to().getLat() - 63.3) * 8000 - 400;
                        double toLon = (e.to().getLon() - 10.3) * 4000 - 100;

                        int x1 = (int) fromLat;
                        int y1 = (int) fromLon;

                        int x2 = (int) toLat;
                        int y2 = (int) toLon;

                        g.drawLine(x1, y1, x2, y2);

                  }

            }
      }

}
