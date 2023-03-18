package Model;

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
    public int getNumberOfMonoms() {
        int contour = 0;
        for(Map.Entry<Integer,Monom> entry : polinom.entrySet()) {
            contour ++;
        }
        return  contour;
    }
    public void addMonomToPolinom(Monom monom) {
        if(!polinom.containsKey(monom.getPower())) {
            polinom.put(monom.getPower(), monom);
        }
        else {
            Monom monom1 = polinom.get(monom.getPower());
            polinom.put(monom.getPower(), new Monom(monom.getCoeficient() + monom1.getCoeficient(),monom.getPower(),"x"));
        }
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

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (Map.Entry<Integer, Monom> entry : polinom.entrySet()) {
            buffer.append(entry.getValue().toString()).append("\n");
        }
        return buffer.toString();
    }
}
