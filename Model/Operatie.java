package Model;

import java.util.Map;

public class Operatie {
    public static Polinom addition(Polinom polinom1, Polinom polinom2) {
        Polinom polinomAdunare = new Polinom(polinom1);

        for (Map.Entry<Integer, Monom> entry : polinom2.getPolinom().entrySet()) {
            if (polinom1.getPolinom().containsKey(entry.getKey())) {
                int var1 = polinom1.getPolinom().get(entry.getKey()).getCoeficient();
                int var2 = polinom2.getPolinom().get(entry.getKey()).getCoeficient();
                if (var1 + var2 != 0) {
                    polinomAdunare.getPolinom().get(entry.getKey()).setCoeficient(var1 + var2);
                } else {
                    polinomAdunare.getPolinom().remove(entry.getKey());
                }

            } else {
                Monom monom = new Monom(entry.getValue().getCoeficient(), entry.getKey(), entry.getValue().getVariable());
                polinomAdunare.addMonomToPolinom(monom);
            }
        }
        return polinomAdunare;
    }

    public static Polinom substraction(Polinom polinom1, Polinom polinom2) {
        Polinom polinomScadere = new Polinom(polinom1);

        for (Map.Entry<Integer, Monom> entry : polinom2.getPolinom().entrySet()) {
            if (polinom1.getPolinom().containsKey(entry.getKey())) {
                int var1 = polinom1.getPolinom().get(entry.getKey()).getCoeficient();
                int var2 = polinom2.getPolinom().get(entry.getKey()).getCoeficient();
                if (var1 - var2 != 0) {
                    polinomScadere.getPolinom().get(entry.getKey()).setCoeficient(var1 - var2);
                } else {
                    polinomScadere.getPolinom().remove(entry.getKey());
                }
            } else {
                Monom monom = new Monom(entry.getValue().getCoeficient(), entry.getKey(), entry.getValue().getVariable());
                polinomScadere.addMonomToPolinom(monom);
            }
        }
        return polinomScadere;

    }
    public static Polinom derivation(Polinom polinom) {
        Polinom polinomDerivare = new Polinom();

        for(Map.Entry<Integer,Monom> entry : polinom.getPolinom().entrySet()) {
            if(entry.getValue().getPower() == 1) {
                polinomDerivare.addMonomToPolinom(new Monom(entry.getValue().getCoeficient(), 0 , "x"));
            }
            else if (entry.getValue().getPower() != 0) {
                polinomDerivare.addMonomToPolinom(new Monom(entry.getValue().getCoeficient() * entry.getValue().getPower(), entry.getValue().getPower() -1, "x"));
            }
        }

        return polinomDerivare;
    }

}
