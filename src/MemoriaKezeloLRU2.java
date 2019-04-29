import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MemoriaKezeloLRU2 {
    private int maxKeret;
    private int csere = 0;
    private HashMap<Character, Integer> memoria = new HashMap<>();
    private HashMap<Integer, Integer> lapKor = new HashMap<>();
    private HashMap<Integer, Integer> pageLockCounter = new HashMap<>();
    private HashMap<Integer, Boolean> pageLock = new HashMap<>();
    private ArrayList<Integer> hattertar = new ArrayList<>();
    public MemoriaKezeloLRU2(int _maxKeret) {
        maxKeret=_maxKeret;
    }
    public int getCsere() {return csere;}
    public char lap(Integer n) {
        novel();
        pageLockCsokkent();
        if (memoria.containsValue(n)) {
            lapKor.replace(n, 0);
            pageLock.replace(n, false);
            pageLockCounter.replace(n, 0);
            return '-';
        }
        if (memoria.size()<maxKeret) {
            memoria.put((char)('A'+memoria.size()), n);
            lapKor.put(n, 0);
            pageLockCounter.put(n, 6);
            pageLock.put(n, true);
            csere++;
            return (char)('A'+memoria.size()-1);
        } else {
            hattertar.remove(n);
            csere++;
            HashMap<Character, Integer> memcopy = new HashMap<>(memoria);
            while (memcopy.size() > 0) {
                char legregebbi = (char)memcopy.keySet().toArray()[0];
                int kor = lapKor.get(memcopy.get(legregebbi));
                for (Map.Entry<Character, Integer> lap: memcopy.entrySet()){
                    if (lapKor.get(lap.getValue()) > kor) {
                        legregebbi = lap.getKey();
                        kor = lapKor.get(lap.getValue());
                    }
                }
                if (!pageLock.get(memcopy.get(legregebbi))) {
                    hattertar.add(memcopy.get(legregebbi));
                    memoria.replace(legregebbi, n);
                    lapKor.remove(n);
                    lapKor.put(n, 0);
                    if (!pageLock.containsKey(n)) {
                        pageLock.put(n, true);
                        pageLockCounter.put(n, 6);
                    } else {
                        pageLockCounter.replace(n, 6);
                        pageLock.replace(n, true);
                    }
                    return legregebbi;
               } else {
                    memcopy.remove(legregebbi);
                }
            }
            return '*';
        }
    }

    private void pageLockCsokkent() {
        for (Map.Entry<Integer, Integer> kor: pageLockCounter.entrySet()) {
            if((Integer)kor.getValue()>0) {
                kor.setValue((Integer) kor.getValue() - 1);
                if ((Integer)kor.getValue()==0)
                    pageLock.replace(kor.getKey(), false);
            }
        }
    }

    private void novel() {
        for (Map.Entry<Integer, Integer> kor: lapKor.entrySet()) {
            kor.setValue((Integer)kor.getValue()+1);
        }
    }
}
