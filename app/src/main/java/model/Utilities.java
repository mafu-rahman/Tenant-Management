package model;

public class Utilities {
    String name;
    double amount;

    private Utilities(String name, double amount){
        this.name = name;
        this.amount = amount;
    }

    public static Utilities getInstanceOfBill(String name, double amount){
        return new Utilities(name, amount);
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public double getAmount(){
        return this.amount;
    }

}
