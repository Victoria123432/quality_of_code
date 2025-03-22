package edu.vikadem;

/*
@author admin
@lab2
@class Trapezium
@since 22.03.2025 - 01.09

*/public class Trapezium {

    private double base1;
    private double base2;
    private double height;
    private double side1;
    private double side2;

    public Trapezium(double base1, double base2, double height, double side1, double side2) {
        this.base1 = base1;
        this.base2 = base2;
        this.height = height;
        this.side1 = side1;
        this.side2 = side2;
    }

    public double getBase1() {
        return base1;
    }

    public void setBase1(double base1) {
        this.base1 = base1;
    }

    public double getBase2() {
        return base2;
    }

    public void setBase2(double base2) {
        this.base2 = base2;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getSide1() {
        return side1;
    }

    public void setSide1(double side1) {
        this.side1 = side1;
    }

    public double getSide2() {
        return side2;
    }

    public void setSide2(double side2) {
        this.side2 = side2;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Trapezium trapezium)) return false;

        return Double.compare(getBase1(), trapezium.getBase1()) == 0 && Double.compare(getBase2(), trapezium.getBase2()) == 0 && Double.compare(getHeight(), trapezium.getHeight()) == 0 && Double.compare(getSide1(), trapezium.getSide1()) == 0 && Double.compare(getSide2(), trapezium.getSide2()) == 0;
    }

    @Override
    public int hashCode() {
        int result = Double.hashCode(getBase1());
        result = 31 * result + Double.hashCode(getBase2());
        result = 31 * result + Double.hashCode(getHeight());
        result = 31 * result + Double.hashCode(getSide1());
        result = 31 * result + Double.hashCode(getSide2());
        return result;
    }

    @Override
    public String toString() {
        return "Trapezium{" +
                "base1=" + base1 +
                ", base2=" + base2 +
                ", height=" + height +
                ", side1=" + side1 +
                ", side2=" + side2 +
                '}';
    }

    public double getArea() {
        return ((base1 + base2) / 2) * height;
    }

    public double getPerimeter() {
        return base1 + base2 + side1 + side2;
    }

    public double getMedian() {
        return (base1 + base2) / 2;
    }

    public boolean isSideHeight() {
        return (side1 == height || side2 == height);
    }


    public boolean isIsosceles() {
        return side1 == side2;
    }

}
