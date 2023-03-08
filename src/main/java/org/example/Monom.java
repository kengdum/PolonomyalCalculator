package org.example;

public class Monom {
    private int coeficient;
    private int power;
    private String variable;

    public Monom(int coeficient, int power, String variable) {
        this.coeficient = coeficient;
        this.power = power;
        this.variable = variable;
    }
    public int getCoeficient() {
        return coeficient;
    }

    public int getPower() {
        return power;
    }

    public String getVariable() {
        return variable;
    }

    public void setCoeficient(int coeficient) {
        this.coeficient = coeficient;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    @Override
    public String toString() {
        return "Monom{" +
                "coeficient=" + coeficient +
                ", power=" + power +
                ", variable='" + variable + '\'' +
                '}';
    }
}
