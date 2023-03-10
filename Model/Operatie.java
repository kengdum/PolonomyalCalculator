package Model;

import java.util.Map;

public class Operatie {
    public static PolinomModel addition(PolinomModel polinom1, PolinomModel polinom2) {
        PolinomModel polinomAdunare = new PolinomModel(polinom1);

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

    public static PolinomModel substraction(PolinomModel polinom1, PolinomModel polinom2) {
        PolinomModel polinomScadere = new PolinomModel(polinom1);

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

}