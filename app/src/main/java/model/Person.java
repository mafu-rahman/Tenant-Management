package model;

import java.io.Serializable;

public class Person implements Serializable {

    String name, flatNo;
    double rent, advancePayment, securityDeposit, dues, salary;
    Utilities utilities;

    public Person(){}

    public Person(String name){
        this.name = name;
    }

    /*
    Getters
     */

    /*
    Setters
     */

    /*
    Helper Methods
     */


}
