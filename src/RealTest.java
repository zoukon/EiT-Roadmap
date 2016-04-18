
import org.xmlpull.v1.XmlPullParserException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JFrame;

/**
 * Created by Magnu on 13.04.2016.
 */
public class RealTest {

      public static void main(String[] args) throws IOException, FileNotFoundException, XmlPullParserException {
            RealEdge test = new RealEdge();

            System.out.println(test.returnEdges().size() + " " + convertArrayList(test.realNodes).size());

            LinkedList firstEdges = convertArrayList(test.returnEdges());
            LinkedList nodes = convertArrayList(test.realNodes);
            LinkedList edges = new LinkedList();

            int i = 0;

            for (Object s : firstEdges) {
                  DirectedEdge e = DirectedEdge.class.cast(s);
                  if (nodes.indexOf(e.to()) != -1) {
                        if (e.getType().equals("highway") || e.getType().equals("residential") || e.getType().equals("service")
                                || e.getType().equals("trunk") || e.getType().equals("primary") || e.getType().equals("secondary")
                                || e.getType().equals("tetriary") || e.getType().equals("unclassified")) {
                              edges.add(e);
                        }
                  }
            }

            System.out.println(edges.size());

            JFrame jf = new JFrame("test");
            jf.setSize(1000, 1000);
            jf.setVisible(true);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Graphics t = new Graphics(test.nodes, convertArrayList(test.returnEdges()));
            jf.add(t);

            MoselReader mr = new MoselReader("D:\\eitTest\\input.txt", "D:\\eitTest\\output.txt");
            mr.writeGraphToFile(edges, nodes);
            mr.closeStreams();

      }

      public static LinkedList convertArrayList(ArrayList in) {
            LinkedList out = new LinkedList();
            for (int i = 0; i < in.size(); i++) {
                  out.add(in.get(i));
            }
            return out;
      }
}
