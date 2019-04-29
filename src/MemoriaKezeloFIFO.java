import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MemoriaKezeloFIFO {
    private int maxKeret;
    private int csere = 0;
    private HashMap<Character, Integer> memoria = new HashMap<>();
    private int mutato = 0;
    private ArrayList<Integer> hattertar = new ArrayList<>();
    public MemoriaKezeloFIFO(int _maxKeret) {
        maxKeret=_maxKeret;
    }
    public int getCsere() {return csere;}
    public char lap(Integer n) {
        if (memoria.containsValue(n)) {
            return '-';
        }
        if (memoria.size()<maxKeret) {
            memoria.put((char)('A'+memoria.size()), n);
            csere++;
            return (char)('A'+memoria.size()-1);
        } else {
            hattertar.remove(n);
            char fifoelso = (char)('A'+mutato);
            hattertar.add(memoria.get(fifoelso));
            memoria.replace(fifoelso, n);
            mutato++;
            if (mutato == maxKeret) mutato = 0;
            csere++;
            return fifoelso;
        }
    }
}
