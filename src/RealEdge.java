
import org.xmlpull.v1.XmlPullParserException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Magnus on 12.04.2016.
 */
public class RealEdge {

      LinkedList edges;
      LinkedList nodes;
      ArrayList<GraphNode> realNodes;

      public RealEdge() throws IOException, FileNotFoundException, XmlPullParserException {
            ParseOSM p = new ParseOSM();

            this.edges = OsmTest.filterEdges(p.getEdges());

            this.nodes = p.getNodes();
            realNodes = new ArrayList<GraphNode>();

            for (Object edge : edges) {
                  DirectedEdge e = DirectedEdge.class.cast(edge);
                  for (Object edge2 : edges) {
                        DirectedEdge e2 = DirectedEdge.class.cast(edge2);
                        if (e.from() == e2.from()) {
                              if (e.getName() == null && e2.getName() != null) {
                                    e.from().setIntersection(true);
                                    if (!realNodes.contains(e.from())) {
                                          realNodes.add(e.from());
                                    }
                              } else if (e.getName() != null && e2.getName() == null) {
                                    e.from().setIntersection(true);
                                    if (!realNodes.contains(e.from())) {
                                          realNodes.add(e.from());
                                    }
                              } else if (e.getName() == null && e2.getName() == null) {

                              } else if (!e.getName().equals(e2.getName())) {
                                    e.from().setIntersection(true);
                                    if (!realNodes.contains(e.from())) {
                                          realNodes.add(e.from());
                                    }
                              }
                        } else if (e.from() == e2.to()) {
                              if (e.getName() == null && e2.getName() != null) {
                                    e.from().setIntersection(true);
                                    if (!realNodes.contains(e.from())) {
                                          realNodes.add(e.from());
                                    }

                              } else if (e.getName() != null && e2.getName() == null) {
                                    e.from().setIntersection(true);
                                    if (!realNodes.contains(e.from())) {
                                          realNodes.add(e.from());
                                    }

                              } else if (e.getName() == null && e2.getName() == null) {

                              } else if (!e.getName().equals(e2.getName())) {
                                    e.from().setIntersection(true);
                                    if (!realNodes.contains(e.from())) {
                                          realNodes.add(e.from());
                                    }
                              }

                        } else if (e.to() == e2.from()) {
                              if (e.getName() == null && e2.getName() != null) {
                                    e.to().setIntersection(true);
                                    if (!realNodes.contains(e.to())) {
                                          realNodes.add(e.to());
                                    }
                              } else if (e.getName() != null && e2.getName() == null) {
                                    e.to().setIntersection(true);
                                    if (!realNodes.contains(e.to())) {
                                          realNodes.add(e.to());
                                    }
                              } else if (e.getName() == null && e2.getName() == null) {

                              } else if (!e.getName().equals(e2.getName())) {
                                    e.to().setIntersection(true);
                                    if (!realNodes.contains(e.to())) {
                                          realNodes.add(e.to());
                                    }
                              }

                        } else if (e.to() == e2.to()) {
                              if (e.getName() == null && e2.getName() != null) {
                                    e.to().setIntersection(true);
                                    if (!realNodes.contains(e.to())) {
                                          realNodes.add(e.to());
                                    }
                              } else if (e.getName() != null && e2.getName() == null) {
                                    e.to().setIntersection(true);
                                    if (!realNodes.contains(e.to())) {
                                          realNodes.add(e.to());
                                    }
                              } else if (e.getName() == null && e2.getName() == null) {

                              } else if (!e.getName().equals(e2.getName())) {
                                    e.to().setIntersection(true);
                                    if (!realNodes.contains(e.to())) {
                                          realNodes.add(e.to());
                                    }
                              }

                        }

                  }
            }
            System.out.println(realNodes.size());

      }

      private ArrayList<DirectedEdge> copyList(LinkedList edges) {
            ArrayList<DirectedEdge> ret = new ArrayList<DirectedEdge>();
            for (Object edge : edges) {
                  DirectedEdge e = DirectedEdge.class.cast(edge);
                  ret.add(e);
            }
            return ret;
      }

      public ArrayList<DirectedEdge> returnEdges() {
            ArrayList<DirectedEdge> realEdges = new ArrayList<DirectedEdge>();
            ArrayList<DirectedEdge> fullEdgeList = copyList(edges);
            for (GraphNode node : this.realNodes) {
                  int i = 0;
                  for (int j = 0; j < fullEdgeList.size(); j++) {
                        if (fullEdgeList.get(j).from().equals(node)) {
                              if (fullEdgeList.get(j).to().isIntersection()) {
                                    realEdges.add(fullEdgeList.get(j));
                                    fullEdgeList.remove(fullEdgeList.get(j));
                                    //System.out.println("SJELDEN");
                                    continue;
                              }
                              GraphNode tempStop = fullEdgeList.get(j).to();
                              GraphNode tempStart = node;
                              fullEdgeList.remove(fullEdgeList.get(j));
                              DirectedEdge lastEdge = fullEdgeList.get(j);
                              double tempLength = fullEdgeList.get(j).getLength();
                              while (fullEdgeList.size() != 0 && i < fullEdgeList.size()) {
                                    if (fullEdgeList.get(i).from().equals(tempStop) && fullEdgeList.get(i).to().isIntersection()) {
                                          DirectedEdge realEdge = new DirectedEdge(tempStart, fullEdgeList.get(i).to(),
                                                  (tempLength + fullEdgeList.get(i).getLength()), fullEdgeList.get(i).speedMax(), fullEdgeList.get(i).isOneway(),
                                                  fullEdgeList.get(i).getType(), fullEdgeList.get(i).getName(), 1, fullEdgeList.get(i).getWayId());
                                          fullEdgeList.remove(fullEdgeList.get(i));
                                          realEdges.add(realEdge);
                                          //System.out.println("KUUUUUUULI");
                                          tempStop = null;
                                          tempLength = 0;
                                          break;
                                    } else if (fullEdgeList.get(i).from().equals(tempStop) && !fullEdgeList.get(i).to().isIntersection()) {
                                          tempStop = fullEdgeList.get(i).to();
                                          lastEdge = fullEdgeList.get(i);
                                          tempLength = tempLength + fullEdgeList.get(i).getLength();
                                          fullEdgeList.remove(fullEdgeList.get(i));
                                          //System.out.println("LEGGER PÅ");
                                          i = 0;
                                    } else {
                                          i++;
                                    }

                              }
                              if (tempStop != null) {
                                    DirectedEdge realEdge = new DirectedEdge(tempStart, lastEdge.to(),
                                            tempLength, lastEdge.speedMax(), lastEdge.isOneway(),
                                            lastEdge.getType(), lastEdge.getName(), 1, lastEdge.getWayId());
                                    fullEdgeList.remove(lastEdge);
                                    realEdges.add(realEdge);
                                    //System.out.println("HMMMM");
                                    tempStop = null;
                              }
                        }
                  }
            }
            return realEdges;
      }
}
