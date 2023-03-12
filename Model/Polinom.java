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

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (Map.Entry<Integer, Monom> entry : polinom.entrySet()) {
            buffer.append(entry.getValue().toString()).append("\n");
        }
        return buffer.toString();
    }
}
