
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
            
            for (Object edge : edges) {
                  DirectedEdge e = DirectedEdge.class.cast(edge);
                  System.out.println(e.getType());
            }
            
            

            JFrame jf = new JFrame("test");
            jf.setSize(1000, 1000);
            jf.setVisible(true);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Graphics t = new Graphics(p);
            jf.add(t);
                    
                    
                              

      }
      
}
