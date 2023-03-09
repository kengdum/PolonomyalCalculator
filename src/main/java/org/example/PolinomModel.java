package org.example;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class PolinomModel{

    private TreeMap<Integer, Monom> polinom;

    public PolinomModel(){
        polinom = new TreeMap<>();
    }
    public PolinomModel(PolinomModel polinom1) {
        polinom = new TreeMap<>();
        for(Map.Entry<Integer, Monom> entry : polinom1.getPolinom().entrySet()) {
            Monom monom = new Monom(entry.getValue().getCoeficient(), entry.getValue().getPower(), entry.getValue().getVariable());
            polinom.put(entry.getValue().getPower(), monom);

        }
     }

    public void addMonomToPolinom(Monom monom) {
        polinom.put(monom.getPower(), monom);
    }
    public TreeMap<Integer, Monom> getPolinom() {
        return polinom;
    }

    public static PolinomModel addition(PolinomModel polinom1, PolinomModel polinom2) {
        PolinomModel polinomAdunare = new PolinomModel(polinom1);

        for(Map.Entry<Integer, Monom> entry : polinom2.getPolinom().entrySet()) {
            if(polinom1.getPolinom().containsKey(entry.getKey())) {
                int var1 = polinom1.getPolinom().get(entry.getKey()).getCoeficient();
                int var2 = polinom2.getPolinom().get(entry.getKey()).getCoeficient();
                polinomAdunare.getPolinom().get(entry.getKey()).setCoeficient(var1 + var2);
            }
            else {
                Monom monom = new Monom(entry.getValue().getCoeficient(), entry.getKey(), entry.getValue().getVariable());
                polinomAdunare.addMonomToPolinom(monom);
            }
        }
        return polinomAdunare;
    }
    public static PolinomModel substraction(PolinomModel polinom1, PolinomModel polinom2) {
        PolinomModel polinomScadere = new PolinomModel(polinom1);

        for(Map.Entry<Integer, Monom> entry : polinom2.getPolinom().entrySet()) {
            if(polinom1.getPolinom().containsKey(entry.getKey())) {
                int var1 = polinom1.getPolinom().get(entry.getKey()).getCoeficient();
                int var2 = polinom2.getPolinom().get(entry.getKey()).getCoeficient();
                polinomScadere.getPolinom().get(entry.getKey()).setCoeficient(var1 - var2);
            }
            else {
                Monom monom = new Monom(entry.getValue().getCoeficient(), entry.getKey(), entry.getValue().getVariable());
                polinomScadere.addMonomToPolinom(monom);
            }
        }
        return polinomScadere;
    }

    @Override
    public String toString() {
        String buffer = "";
        for(Map.Entry<Integer, Monom> entry : polinom.entrySet()) {
            buffer = buffer + entry.getValue().toString() + "\n";
        }
        return buffer;
    }
}
