import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        MemoriaKezeloFIFO mkFIFO = new MemoriaKezeloFIFO(4);
        MemoriaKezeloLRU mkLRU = new MemoriaKezeloLRU(4);
        MemoriaKezeloLRU mkLRU2 = new MemoriaKezeloLRU(4);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        PrintWriter writer = new PrintWriter(System.out);
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            String[] lapok = line.split(",");
            //FIFO
            for (String lap : lapok) {
                writer.print(mkFIFO.lap(Integer.parseInt(lap)));
            }
            writer.println();
            writer.println(mkFIFO.getCsere());
            //LRU
            for (String lap : lapok) {
                writer.print(mkLRU.lap(Integer.parseInt(lap)));
            }
            writer.println();
            writer.println(mkLRU.getCsere());
            //LRU2
            for (String lap : lapok) {
                writer.print(mkLRU2.lap(Integer.parseInt(lap)));
            }
            writer.println();
            writer.println(mkLRU2.getCsere());

        }
        reader.close();
        writer.close();
    }
}
