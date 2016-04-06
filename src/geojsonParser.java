
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class geojsonParser {

      public static void main(String[] args) throws FileNotFoundException, IOException {
            LinkedList nodes = new LinkedList();
            LinkedList edges = new LinkedList();
            RoadGraph g = new RoadGraph();
            

            FileReader fr = new FileReader("C:\\Users\\Richard\\Desktop\\eit\\broyteruter_student.geojson");
            BufferedReader br = new BufferedReader(fr);

            List<Integer> id = new ArrayList<>();
            List<Integer> linkid = new ArrayList<>();
            List<String> category = new ArrayList<>();
            List<String> type = new ArrayList<>();
            List<String> status = new ArrayList<>();
            List<Integer> roadNumber = new ArrayList<>();
            List<String> name = new ArrayList<>();

            List<List<Double>> roadCords = new ArrayList<>();

            for (int i = 0; i < 5; i++) {
                  br.readLine();
            }
            
            while(br.ready()){
            //for (int i = 0; i < 20; i++) {
                  StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                  while (st.hasMoreTokens()) {
                        String t = st.nextToken();
                        String t2;
                        if (t.length() > 2) {
                              t = t.substring(1, t.length() - 2);
                        }
                        //Adding necessary information to the different lists
                        switch (t) {
                              case "ID":
                                    t2 = st.nextToken();
                                    t2 = t2.substring(0, t2.length() - 1);
                                    if(!t2.equals("null")){
                                          id.add(Integer.parseInt(t2));
                                    }
                                    else{
                                          id.add(-1);
                                    }
                                    
                                    break;
                              case "VLENKEID":
                                    t2 = st.nextToken();
                                    t2 = t2.substring(0, t2.length() - 1);
                                    if (!t2.equals("null")) {
                                          linkid.add(Integer.parseInt(t2));
                                    } else {
                                          linkid.add(-1);
                                    }
                                    break;
                              case "VEGKATEGORI":
                                    t2 = st.nextToken();
                                    if (!t2.equals("null,")) {
                                          category.add(t2.substring(1, t2.length() - 2));
                                    } else {
                                          category.add("null");
                                    }
                                    break;
                              case "TYPEVEG":
                                    t2 = st.nextToken();
                                    if (!t2.equals("null,")) {
                                          type.add(t2.substring(1, t2.length() - 2));
                                    } else {
                                          type.add("null");
                                    }
                                    break;
                              case "VEGSTATUS":
                                    t2 = st.nextToken();
                                    if (!t2.equals("null,")) {
                                          status.add(t2.substring(1, t2.length() - 2));
                                    } else {
                                          status.add("null");
                                    }
                                    break;
                              case "VEGNUMMER":
                                    t2 = st.nextToken();
                                    t2 = t2.substring(0, t2.length() - 1);
                                    if (!t2.equals("null")) {
                                          roadNumber.add(Integer.parseInt(t2));
                                    } else {
                                          roadNumber.add(-1);
                                    }
                                    break;

                              case "GATENAVN":
                                    t2 = st.nextToken();
                                    if (!t2.equals("null,")) {
                                          //Adding together names that are seperated by spaces
                                          while (t2.charAt(t2.length() - 2) != '"') {
                                                String t3 = st.nextToken();
                                                t2 += " " + t3;
                                          }
                                          name.add(t2.substring(1, t2.length() - 2));

                                    } else {
                                          name.add("null");
                                    }
                                    break;
                              case "coordinates":
                                    List<Double> temp = new ArrayList<>();
                                    while (st.hasMoreTokens()) {
                                          t2 = st.nextToken();
                                          if (t2.charAt(0) == ']' || t2.charAt(0) == '[' || t2.charAt(0) == '}') {

                                          } else if (t2.charAt(t2.length() - 1) == ',') {
                                                t2 = t2.substring(0, t2.length() - 1);
                                                temp.add(Double.parseDouble(t2));

                                          } else {
                                                temp.add(Double.parseDouble(t2));
                                          }
                                    }
                                    roadCords.add(temp);
                                    break;

                        }
                  }
            }
            //Printing the information, to copare with the source file
            for (int i = 0; i < id.size(); i++) {
                  /*
                  System.out.print("ID: " + id.get(i) + ", ");
                  System.out.print("VLENKEID: " + linkid.get(i) + ", ");
                  System.out.print("VEGKATEGORI: " + category.get(i) + ", ");
                  System.out.print("TYPEVEG: " + type.get(i) + ", ");
                  System.out.print("VEGSTATUS: " + status.get(i) + ", ");
                  System.out.print("VEGNUMMER: " + roadNumber.get(i) + ", ");
                  System.out.print("GATENAVN: " + name.get(i) + ", ");
                  System.out.print("Coordinates: ");
                          */
                  for (int j = 0; j < roadCords.get(i).size(); j += 2) {
                        GraphNode gn = new GraphNode();
                        //System.out.print("[" + roadCords.get(i).get(j) + ", " + roadCords.get(i).get(j + 1) + "], ");
                        gn.setLat(roadCords.get(i).get(j));
                        gn.setLon(roadCords.get(i).get(j+1));
                        nodes.add(gn);
                        if(j > 0){
                              DirectedEdge de = new DirectedEdge((GraphNode)nodes.get(nodes.size()-2), gn, linkid.get(i), 0, name.get(i), false);
                              edges.add(de);
                        }
                  }
                 // System.out.println("");
            }
            
            
            
            GraphNode test;
            for (int i = 0; i < nodes.size(); i++) {
                  test = (GraphNode) nodes.get(i);
                  //System.out.println("[" + test.getLat() + ", " + test.getLon() + "]");
            }

            fr.close();
            br.close();
            
            System.out.println(edges.size() + "Edges");
            System.out.println(nodes.size() + "Nodes");

      }

}
