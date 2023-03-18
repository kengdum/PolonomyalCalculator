package Model;

import java.text.DecimalFormat;

public class Monom {
    private double coeficient;
    private int power;
    private String variable;

    public Monom(double coeficient, int power, String variable) {
        this.coeficient = coeficient;
        this.power = power;
        this.variable = variable;
    }
    public void setDecimals(){
        int myInt = (int) this.coeficient;
        if(myInt == this.coeficient){
            this.coeficient = myInt;
            System.out.println(this.coeficient);
        }
        else{
            this.coeficient = Math.round(this.coeficient * 100.0) / 100.0;
            System.out.println(this.coeficient);
        }

    }
    public double getCoeficient() {
        return coeficient;
    }

    public int getPower() {
        return power;
    }

    public String getVariable() {
        return variable;
    }

    public void setCoeficient(double coeficient) {
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
