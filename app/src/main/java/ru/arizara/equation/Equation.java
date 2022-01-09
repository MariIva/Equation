package ru.arizara.equation;

import java.util.Arrays;

public class Equation {
    private int a;
    private int b;
    private int c;
    private double x[];

    public Equation(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void calc(){
        if (a != 0) {
            double d = (b * b) - (4 * a * c);
            if (d < 0)
                x = null;
            else if (d == 0) {
                double x1 = (-b*1.) / (2 * a);
                x = new double[]{x1};
            }
            else {
                double x1 = (-b - Math.sqrt(d)) / (2 * a);
                double x2 = (-b + Math.sqrt(d)) / (2 * a);
                x = new double[]{x1, x2};
            }
        } else {
            int x1 = (-c) / b;
            x = new double[]{x1};
        }
    }

    public String getX() {
        if(x == null)
            return  "Нет решений";
        else if(x.length == 1)
            return  String.format("x = %.2f", x[0]);
        else{
            return  String.format("x1 = %.2f x2 = %.2f", x[0], x[1]);
        }
    }

    @Override
    public String toString() {
        return String.format("%d*x*x%+d*x%+d = 0\n", a, b, c) + getX();
    }
}
