
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JFrame;
import org.xmlpull.v1.XmlPullParserException;

public class OsmTest {

      public static void main(String[] args) throws IOException, FileNotFoundException, XmlPullParserException {
            ParseOSM p = new ParseOSM();

            LinkedList edges = p.getEdges();
            LinkedList nodes = p.getNodes();

            edges = filterEdges(edges);
            LinkedList test = new LinkedList();

            p.edges = edges;

            for (Object edge : edges) {
                  DirectedEdge e = DirectedEdge.class.cast(edge);
                  for (Object edge2 : edges) {
                        DirectedEdge e2 = DirectedEdge.class.cast(edge2);
                        if (e.from() == e2.from()) {
                              if (e.getName() == null && e2.getName() != null) {
                                    e.addInterection(e.from());
                                    e2.addInterection(e2.from());
                              } else if (e.getName() != null && e2.getName() == null) {
                                    e.addInterection(e.from());
                                    e2.addInterection(e2.from());
                              } else if (e.getName() == null && e2.getName() == null) {

                              } else if (!e.getName().equals(e2.getName())) {
                                    e.addInterection(e.from());
                                    e2.addInterection(e2.from());
                              }
                        } else if (e.from() == e2.to()) {
                              if (e.getName() == null && e2.getName() != null) {
                                    e.addInterection(e.from());
                                    e2.addInterection(e2.to());
                              } else if (e.getName() != null && e2.getName() == null) {
                                    e.addInterection(e.from());
                                    e2.addInterection(e2.to());

                              } else if (e.getName() == null && e2.getName() == null) {

                              } else if (!e.getName().equals(e2.getName())) {
                                    e.addInterection(e.from());
                                    e2.addInterection(e2.to());
                              }

                        } else if (e.to() == e2.from()) {
                              if (e.getName() == null && e2.getName() != null) {
                                    e.addInterection(e.to());
                                    e2.addInterection(e2.from());
                              } else if (e.getName() != null && e2.getName() == null) {
                                    e.addInterection(e.to());
                                    e2.addInterection(e2.from());
                              } else if (e.getName() == null && e2.getName() == null) {

                              } else if (!e.getName().equals(e2.getName())) {
                                    e.addInterection(e.to());
                                    e2.addInterection(e2.from());
                              }

                        } else if (e.to() == e2.to()) {
                              if (e.getName() == null && e2.getName() != null) {
                                    e.addInterection(e.to());
                                    e2.addInterection(e2.to());
                              } else if (e.getName() != null && e2.getName() == null) {
                                    e.addInterection(e.to());
                                    e2.addInterection(e2.to());
                              } else if (e.getName() == null && e2.getName() == null) {

                              } else if (!e.getName().equals(e2.getName())) {
                                    e.addInterection(e.to());
                                    e2.addInterection(e2.to());
                              }

                        }

                  }
            }

            System.out.println(edges.size());

            JFrame jf = new JFrame("test");
            jf.setSize(1000, 1000);
            jf.setVisible(true);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Graphics t = new Graphics(p);
            jf.add(t);

      }

      public static LinkedList filterEdges(LinkedList edges) {
            LinkedList ret = new LinkedList();
            for (Object edge : edges) {
                  DirectedEdge e = DirectedEdge.class.cast(edge);
                  if (e.getType() != null) {
                        ret.add(e);
                  }
            }

            return ret;
      }

}
