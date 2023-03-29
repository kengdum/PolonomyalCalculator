package src.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import Model.Monom;
import Model.Operatie;
import Model.Polinom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
public class OperatiiTest {

    private static Polinom polinom1 = new Polinom();
    private static Polinom polinom2  = new Polinom();
    @BeforeAll
    static void setUpPolinom() {

        Monom monom1 = new Monom(1,3,"x");
        Monom monom2 = new Monom(-2,2,"x");
        Monom monom3 = new Monom(6,1,"x");
        Monom monom4 = new Monom(-5,0,"x");
        Monom monom5 = new Monom(1,2,"x");
        Monom monom6 = new Monom(-1,0,"x");

        polinom1.addMonomToPolinom(monom1);
        polinom1.addMonomToPolinom(monom2);
        polinom1.addMonomToPolinom(monom3);
        polinom1.addMonomToPolinom(monom4);
        polinom2.addMonomToPolinom(monom5);
        polinom2.addMonomToPolinom(monom6);
    }
    @Test
    void testAdunare() {
        Polinom polinomAdunare = new Polinom();
        Monom monom1 = new Monom(1,3,"x");
        Monom monom2 = new Monom(-1,2,"x");
        Monom monom3 = new Monom(6,1,"x");
        Monom monom4 = new Monom(-6, 0, "x");
        polinomAdunare.addMonomToPolinom(monom1);
        polinomAdunare.addMonomToPolinom(monom2);
        polinomAdunare.addMonomToPolinom(monom3);
        polinomAdunare.addMonomToPolinom(monom4);
        assertEquals(polinomAdunare, Operatie.addition(polinom1, polinom2));
    }
    @Test
    void testScadere() {
        Polinom polinomScadere = new Polinom();
        Monom monom1 = new Monom(1,3,"x");
        Monom monom2 = new Monom(-3,2,"x");
        Monom monom3 = new Monom(6,1,"x");
        Monom monom4 = new Monom(-4,0,"x");
        polinomScadere.addMonomToPolinom(monom1);
        polinomScadere.addMonomToPolinom(monom2);
        polinomScadere.addMonomToPolinom(monom3);
        polinomScadere.addMonomToPolinom(monom4);
        assertEquals(polinomScadere,Operatie.substraction(polinom1,polinom2));
    }
    @Test
    void testInmultire() {
        Polinom polinomInmultire = new Polinom();
        Monom monom1 = new Monom(1,5,"x");
        Monom monom2 = new Monom(-2,4,"x");
        Monom monom3 = new Monom(5,3,"x");
        Monom monom4 = new Monom(3,2,"x");
        Monom monom5 = new Monom(-6, 1, "x");
        Monom monom6 = new Monom(5,0,"x");
        polinomInmultire.addMonomToPolinom(monom1);
        polinomInmultire.addMonomToPolinom(monom2);
        polinomInmultire.addMonomToPolinom(monom3);
        polinomInmultire.addMonomToPolinom(monom4);
        polinomInmultire.addMonomToPolinom(monom5);
        polinomInmultire.addMonomToPolinom(monom6);
        assertEquals(polinomInmultire, Operatie.multiplication(polinom1,polinom2));
    }
    @Test
    void testDerivare() {
        Polinom polinomDerivare = new Polinom();
        Monom monom1 = new Monom(3,2,"x");
        Monom monom2 = new Monom(-4,1,"x");
        Monom monom3 = new Monom(6,0,"x");
        polinomDerivare.addMonomToPolinom(monom1);
        polinomDerivare.addMonomToPolinom(monom2);
        polinomDerivare.addMonomToPolinom(monom3);
        assertEquals(polinomDerivare, Operatie.derivation(polinom1));
    }
    @Test
    void testIntegrare() {
        Polinom polinomIntegrare = new Polinom();
        Monom monom1 = new Monom(3.0/4, 4, "x");
        Monom monom2 = new Monom(- 2.0/3, 3, "x");
        Monom monom3 = new Monom(3, 2, "x");
        Monom monom4 = new Monom(-5, 1, "x");
        polinomIntegrare.addMonomToPolinom(monom1);
        polinomIntegrare.addMonomToPolinom(monom2);
        polinomIntegrare.addMonomToPolinom(monom3);
        polinomIntegrare.addMonomToPolinom(monom4);
        assertEquals(polinomIntegrare, Operatie.integration(polinom1));
    }
    @Test
    void testImpartire() {
        Polinom remainder = new Polinom();
        Polinom quotient = new Polinom();
        Polinom[] result = {quotient, remainder};

        Monom monom1 = new Monom(1,1,"x");
        Monom monom2 = new Monom(-2,0,"x");
        Monom monom3 = new Monom(7,0,"x");
        Monom monom4 = new Monom(7,1,"x");

        quotient.addMonomToPolinom(monom1);
        quotient.addMonomToPolinom(monom2);

        remainder.addMonomToPolinom(monom3);
        remainder.addMonomToPolinom(monom4);

        assertEquals(quotient , Operatie.division(polinom1,polinom2)[0]);
        assertEquals(remainder , Operatie.division(polinom1,polinom2)[1]);

    }
}
