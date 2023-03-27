package Model;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.TreeMap;

public class Polinom {

    private TreeMap<Integer, Monom> polinom;

    public Polinom() {
        polinom = new TreeMap<>();
    }

    public Polinom(Polinom polinom1) {
        polinom = new TreeMap<>();
        for (Map.Entry<Integer, Monom> entry : polinom1.getPolinom().entrySet()) {
            Monom monom = new Monom(entry.getValue().getCoeficient(), entry.getValue().getPower(), entry.getValue().getVariable());
            polinom.put(entry.getValue().getPower(), monom);

        }
    }
    public int getHighestPower(){
        return polinom.lastEntry().getValue().getPower();
    }
    public void addMonomToPolinom(Monom monom) {
        Monom monom2 = polinom.get(monom.getPower());
        if(!polinom.containsKey(monom.getPower())) {
            polinom.put(monom.getPower(), monom);
        }
        else if(monom2.getCoeficient() == -1 * monom.getCoeficient()) {
            polinom.put(0, new Monom(0, 0, "x"));
            polinom.remove(monom.getPower());
        }
        else {
            Monom monom1 = polinom.get(monom.getPower());
            polinom.put(monom.getPower(), new Monom(monom.getCoeficient() + monom1.getCoeficient(),monom.getPower(),"x"));
        }
        System.out.println(polinom);
    }

    public TreeMap<Integer, Monom> getPolinom() {
        return polinom;
    }

    public void checkZeros() {
        for(Map.Entry<Integer, Monom> entry : polinom.entrySet()) {
            if(entry.getValue().getCoeficient() == 0){
                polinom.remove(entry.getKey());
            }

        }
    }
    public void formatDecimals() {
        for (Map.Entry<Integer, Monom> entry : polinom.entrySet()) {
            entry.getValue().setDecimals();
        }
    }
    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (Map.Entry<Integer, Monom> entry : polinom.entrySet()) {
            buffer.append(entry.getValue().toString()).append("\n");
        }
        return buffer.toString();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Polinom other = (Polinom) obj;
        if (this.polinom.size() != other.polinom.size()) {
            return false;
        }
        for (Map.Entry<Integer, Monom> entry : this.polinom.entrySet()) {
            Integer key = entry.getKey();
            System.out.println(entry);
            if(!other.polinom.containsKey(key)) {
                return false;
            }
        }
        return true;
    }
}
