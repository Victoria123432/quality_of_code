
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        HumanIMB humanIMB = new HumanIMB(80, 1.52);
        System.out.println(humanIMB.getResult());
    }
}

class HumanIMB {
    private double weight; 
    private double height; 
    private double imb;

    public HumanIMB(double weight, double height) {
        this.weight = weight;
        this.height = height;
        updateIMB();
    }

    private void updateIMB() {
        this.imb = weight / (height * height);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
        updateIMB();
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
        updateIMB();
    }

    public double getIMB() {
        return imb;
    }

    public String getResult() {
        if (imb < 18.5)
            return "Deficit";
        if (imb < 25)
            return "Norm";
        if (imb < 30)
            return "Warning!";
        return "Fat";

    }
}