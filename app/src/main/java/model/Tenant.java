package model;

import java.util.*;

public class Tenant extends Person {
    String name, flatNo;
    double baseRent, advancePayment, securityDeposit, totalDues;

    ArrayList<Time> paidMonths;
    ArrayList<Utilities> utilities;
    Hashtable<Time, Double> monthlyPaidAmount;


    private Tenant(String name, String flatNo, double baseRent, double advancePayment, double securityDeposit) {
        this.name = name;
        this.flatNo = flatNo;
        this.baseRent = baseRent;
        this.advancePayment = advancePayment;
        this.securityDeposit = securityDeposit;
        this.utilities = new ArrayList<Utilities>();
        this.monthlyPaidAmount = new Hashtable<Time, Double>();
        this.paidMonths = new ArrayList<Time>();
    }

    public static Tenant getInstanceOfTenant(String name, String flatNo, double baseRent, double advancePayment, double securityDeposit) {
        return new Tenant(name, flatNo, baseRent, advancePayment, securityDeposit);
    }

    public double getBaseRent(){
        return this.baseRent;
    }

    public void setBaseRent(double amount){
        this.baseRent = amount;
    }

    public void addUtility(Utilities u){
        this.utilities.add(u);
    }

    public double getTotalUtilities(){
        double total = 0;
        for(int i=0; i<utilities.size(); i++){
            total += utilities.get(i).getAmount();
        }
        return total;
    }

    public double getTotalRent(){
        return (getBaseRent() + getTotalUtilities());
    }

    public double getAmountPaidMonth(Time t){
        if(findIfMonthPaid(t) > -1) {
            t = paidMonths.get(findIfMonthPaid(t));
        }
        if (monthlyPaidAmount.get(t) != null){
            return monthlyPaidAmount.get(t);
        }
        return -1;
    }

    public void payRent(double amount, Time t) {
        if(findIfMonthPaid(t) > -1) {
            t = paidMonths.get(findIfMonthPaid(t));
        }
        if(monthlyPaidAmount.get(t)!=null) {
            monthlyPaidAmount.put(t, (amount + (monthlyPaidAmount.get(t)) ) );
        }
        else {
            monthlyPaidAmount.put(t, amount);
            paidMonths.add(t);
        }
    }

    public double getRemainingDues(Time t){
        if(findIfMonthPaid(t) > -1) {
            t = paidMonths.get(findIfMonthPaid(t));
        }
        if(monthlyPaidAmount.get(t) != null){
            return (getTotalRent() - getAmountPaidMonth(t));
        }
        else {
            return (getTotalRent());
        }
    }

    public int findIfMonthPaid(Time t) {
        for(int i=0; i<paidMonths.size(); i++) {
            if(paidMonths.get(i).equals(t)) {
                return i;
            }
        }
        return -1;
    }
}