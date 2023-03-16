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
        polinomAdunare.checkZeros();
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
        polinomScadere.checkZeros();
        return polinomScadere;

    }
    public static Polinom multiplication(Polinom polinom1, Polinom polinom2) {
        Polinom polinomMultiplication = new Polinom();
        for (Map.Entry<Integer, Monom> entry1 : polinom1.getPolinom().entrySet()) {
            for (Map.Entry<Integer, Monom> entry2 : polinom2.getPolinom().entrySet()) {
                int coeficient = entry1.getValue().getCoeficient() * entry2.getValue().getCoeficient();
                int putere = entry1.getValue().getPower() + entry2.getValue().getPower();
                polinomMultiplication.addMonomToPolinom(new Monom(coeficient, putere, "x"));
            }
        }
        polinomMultiplication.checkZeros();
        return polinomMultiplication;
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
        polinom.checkZeros();
        return polinomDerivare;
    }

}
