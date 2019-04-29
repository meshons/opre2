import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MemoriaKezeloLRU {
    private int maxKeret;
    private int csere = 0;
    private HashMap<Character, Integer> memoria = new HashMap<>();
    private HashMap<Integer, Integer> lapKor = new HashMap<>();
    private ArrayList<Integer> hattertar = new ArrayList<>();
    public MemoriaKezeloLRU(int _maxKeret) {
        maxKeret=_maxKeret;
    }
    public int getCsere() {return csere;}
    public char lap(Integer n) {
        novel();
        if (memoria.containsValue(n)) {
            lapKor.replace(n, 0);
            return '-';
        }
        if (memoria.size()<maxKeret) {
            memoria.put((char)('A'+memoria.size()), n);
            lapKor.put(n, 0);
            csere++;
            return (char)('A'+memoria.size()-1);
        } else {
            hattertar.remove(n);
            char legregebbi = 'A';
            int kor = lapKor.get(memoria.get('A'));
            for (int i=1; i<maxKeret; ++i) {
                if (lapKor.get(memoria.get((char)('A'+i))) > kor) {
                    legregebbi = (char)(i+'A');
                    kor = lapKor.get(memoria.get((char)('A'+i)));
                }
            }
            hattertar.add(memoria.get(legregebbi));
            memoria.replace(legregebbi, n);
            lapKor.remove(n);
            lapKor.put(n, 0);
            csere++;
            return legregebbi;
        }
    }
    private void novel() {
        for (Map.Entry kor: lapKor.entrySet()) {
            kor.setValue((Integer)kor.getValue()+1);
        }
    }
}
