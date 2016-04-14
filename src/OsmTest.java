
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
            
            
            
            LinkedList nodeTest = intersectionsToList(edges);

            p.nodes = nodeTest;
            
            
            for(Object sd : edges){
                  DirectedEdge e = DirectedEdge.class.cast(sd);
                  System.out.println(e.getOtherTags());
            }
                    
                    
            
            MoselReader mr = new MoselReader("D:\\eitTest\\input.txt", "D:\\eitTest\\output.txt");
            mr.writeGraphToFile(edges, nodes);
            mr.closeStreams();
            System.out.println(nodeTest.size());

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

      public static LinkedList FindIntersections(LinkedList edges) {
            for (Object edge : edges) {
                  DirectedEdge e = DirectedEdge.class.cast(edge);
                  for (Object edge2 : edges) {
                        DirectedEdge e2 = DirectedEdge.class.cast(edge2);
                        if (e.from() == e2.from()) {
                              if (e.getName() == null && e2.getName() != null) {
                                    e.addInterection(e.from());
                                    e2.addInterection(e2.from());
                                    e.from().setIntersection(true);
                              } else if (e.getName() != null && e2.getName() == null) {
                                    e.addInterection(e.from());
                                    e2.addInterection(e2.from());
                                    e.from().setIntersection(true);
                              } else if (e.getName() == null && e2.getName() == null) {

                              } else if (!e.getName().equals(e2.getName())) {
                                    e.addInterection(e.from());
                                    e2.addInterection(e2.from());
                                    e.from().setIntersection(true);
                              }
                        } else if (e.from() == e2.to()) {
                              if (e.getName() == null && e2.getName() != null) {
                                    e.addInterection(e.from());
                                    e2.addInterection(e2.to());
                                    e.from().setIntersection(true);
                              } else if (e.getName() != null && e2.getName() == null) {
                                    e.addInterection(e.from());
                                    e2.addInterection(e2.to());
                                    e.from().setIntersection(true);

                              } else if (e.getName() == null && e2.getName() == null) {

                              } else if (!e.getName().equals(e2.getName())) {
                                    e.addInterection(e.from());
                                    e2.addInterection(e2.to());
                                    e.from().setIntersection(true);
                              }

                        } else if (e.to() == e2.from()) {
                              if (e.getName() == null && e2.getName() != null) {
                                    e.addInterection(e.to());
                                    e2.addInterection(e2.from());
                                    e.to().setIntersection(true);
                              } else if (e.getName() != null && e2.getName() == null) {
                                    e.addInterection(e.to());
                                    e2.addInterection(e2.from());
                                    e.to().setIntersection(true);
                              } else if (e.getName() == null && e2.getName() == null) {

                              } else if (!e.getName().equals(e2.getName())) {
                                    e.addInterection(e.to());
                                    e2.addInterection(e2.from());
                                    e.to().setIntersection(true);
                              }

                        } else if (e.to() == e2.to()) {
                              if (e.getName() == null && e2.getName() != null) {
                                    e.addInterection(e.to());
                                    e2.addInterection(e2.to());
                                    e.to().setIntersection(true);
                              } else if (e.getName() != null && e2.getName() == null) {
                                    e.addInterection(e.to());
                                    e2.addInterection(e2.to());
                                    e.to().setIntersection(true);
                              } else if (e.getName() == null && e2.getName() == null) {

                              } else if (!e.getName().equals(e2.getName())) {
                                    e.addInterection(e.to());
                                    e2.addInterection(e2.to());
                                    e.to().setIntersection(true);
                              }

                        }

                  }
            }
            return edges;
      }

      public static LinkedList intersectionsToList(LinkedList edges) {
            LinkedList intersections = new LinkedList();
            for (Object edge : edges) {
                  DirectedEdge e = DirectedEdge.class.cast(edge);
                  for (Object edge2 : edges) {
                        DirectedEdge e2 = DirectedEdge.class.cast(edge2);
                        if (e.from() == e2.from()) {
                              if (e.getName() == null && e2.getName() != null) {
                                    if (!intersections.contains(e.from())) {
                                          intersections.add(e.from());
                                          e.from().setIntersection(true);
                                    }
                              } else if (e.getName() != null && e2.getName() == null) {
                                    if (!intersections.contains(e.from())) {
                                          intersections.add(e.from());
                                          e.from().setIntersection(true);
                                    }
                              } else if (e.getName() == null && e2.getName() == null) {

                              } else if (!e.getName().equals(e2.getName())) {
                                    if (!intersections.contains(e.from())) {
                                          intersections.add(e.from());
                                          e.from().setIntersection(true);
                                    }
                              }
                        } else if (e.from() == e2.to()) {
                              if (e.getName() == null && e2.getName() != null) {
                                    if (!intersections.contains(e.from())) {
                                          intersections.add(e.from());
                                          e.from().setIntersection(true);
                                    }
                              } else if (e.getName() != null && e2.getName() == null) {
                                    if (!intersections.contains(e.from())) {
                                          intersections.add(e.from());
                                          e.from().setIntersection(true);
                                    }
                              } else if (e.getName() == null && e2.getName() == null) {

                              } else if (!e.getName().equals(e2.getName())) {
                                    if (!intersections.contains(e.from())) {
                                          intersections.add(e.from());
                                          e.from().setIntersection(true);
                                    }
                              }

                        } else if (e.to() == e2.from()) {
                              if (e.getName() == null && e2.getName() != null) {
                                    if (!intersections.contains(e.to())) {
                                          intersections.add(e.to());
                                          e.to().setIntersection(true);
                                    }
                              } else if (e.getName() != null && e2.getName() == null) {
                                    if (!intersections.contains(e.to())) {
                                          intersections.add(e.to());
                                          e.to().setIntersection(true);
                                    }
                              } else if (e.getName() == null && e2.getName() == null) {

                              } else if (!e.getName().equals(e2.getName())) {
                                    if (!intersections.contains(e.to())) {
                                          intersections.add(e.to());
                                          e.to().setIntersection(true);
                                    }
                              }

                        } else if (e.to() == e2.to()) {
                              if (e.getName() == null && e2.getName() != null) {
                                    if (!intersections.contains(e.to())) {
                                          intersections.add(e.to());
                                          e.to().setIntersection(true);
                                    }
                              } else if (e.getName() != null && e2.getName() == null) {
                                    if (!intersections.contains(e.to())) {
                                          intersections.add(e.to());
                                          e.to().setIntersection(true);
                                    }
                              } else if (e.getName() == null && e2.getName() == null) {

                              } else if (!e.getName().equals(e2.getName())) {
                                    if (!intersections.contains(e.to())) {
                                          intersections.add(e.to());
                                          e.to().setIntersection(true);
                                    }
                              }

                        }

                  }
            }
            return intersections;
      }
}
