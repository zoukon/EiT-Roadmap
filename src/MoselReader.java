
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class MoselReader {

      FileReader fr;
      BufferedReader br;
      FileWriter fw;
      BufferedWriter bw;

      public MoselReader(String inFile, String outFile) throws FileNotFoundException, IOException {
            this.fr = new FileReader(inFile);
            this.br = new BufferedReader(fr);
            this.fw = new FileWriter(outFile);
            this.bw = new BufferedWriter(fw);
      }

      public void writeGraphToFile(LinkedList edges, LinkedList nodes) throws IOException {
            bw.write("Arcs :");
            bw.newLine();
            bw.write("[");

            for (Object s : edges) {
                  DirectedEdge e = DirectedEdge.class.cast(s);
                  bw.write(nodes.indexOf(e.from()) + ",");
                  bw.write(nodes.indexOf(e.to()) + ",");
                  bw.write(1 + ",");
                  bw.write(0 + ",");
                  bw.write(0 + ",");
                  bw.write(calculateTime(e, 1) + ",");
                  bw.write(calculateTime(e, 1.5) + ",");
                  bw.write(calculateTime(e, 1) + ",");
                  bw.write(calculateTime(e, 2.0) + ",");
                  bw.write(calculateTime(e, 1) + ",");
                  bw.write(calculateTime(e, 2.5) + ",");
                  bw.newLine();
            }
            bw.write("]");
            bw.newLine();
            bw.newLine();
            bw.write("ArcsVei : [");

            int i = 0;
            for (Object s : edges) {
                  DirectedEdge e = DirectedEdge.class.cast(s);
                  if (e.getType().equals("highway") || e.getType().equals("residential") || e.getType().equals("service") || 
                          e.getType().equals("trunk") || e.getType().equals("primary") || e.getType().equals("secondary") || 
                          e.getType().equals("tetriary") || e.getType().equals("unclassified")) {
                        bw.write(i + ",");
                  }
                  i++;
            }

            bw.write("]");
            bw.newLine();
            bw.write("ArcsSykkel : [");

            for (Object s : edges) {
                  DirectedEdge e = DirectedEdge.class.cast(s);
                  if (e.getType().equals("cycleway")) {
                        bw.write(i + ",");
                  }
                  i++;
            }

            bw.write("]");
            bw.newLine();
            bw.write("ArcsGang : [");
            //TODO: add logic
            bw.write("]");
            bw.newLine();


            bw.write("ArcsVeiOgSykkel: [");
            //TODO: add logic
            bw.write("]");
            bw.newLine();

            bw.write("ArcsSykkelOgGang: [");
            //TODO: add logic
            bw.write("]");
            bw.newLine();

            bw.write("ArcsVeiOgGang: [");
            //TODO: add logic
            bw.write("]");
            bw.newLine();
            bw.newLine();

            bw.write("nBuenummer : " + edges.size());
            bw.newLine();
            
            bw.write("nNoder : " + nodes.size());
            bw.newLine();
            
            bw.write("nKjøretøyVei : " + 2);
            bw.newLine();
            
            bw.write("nKjøretøySykkel : " + 0);
            bw.newLine();
            
            bw.write("nKjøretøyGang : " + 1);
            bw.newLine();
            
            bw.write("nMaxBuer : " + 100);
            bw.newLine();
            
            bw.write("W : " + 5);
            bw.newLine();
            
            bw.write("BIGM : " + 200);
            bw.newLine();
      }

      //Calculates the time used to plow an edge based on the speedLimit
      private int calculateTime(DirectedEdge e, double bonus) {
            double mps = 0.277778 * (double) e.speedMax();
            double ret = (((double) e.getLength()) / mps) * bonus;

            return (int) ret;
      }

      public RoadGraph getGraphFromFile() {
            RoadGraph rg = new RoadGraph();

            return rg;
      }

      public void closeStreams() throws IOException {
            br.close();
            fr.close();
            bw.close();
            fw.close();

      }
}
