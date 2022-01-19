package model;

import java.util.ArrayList;

public class Property {
    String name, location;
    ArrayList<Person> person;

    private Property(String name, String location){
        this.name = name;
        this.location = location;
        this.person = new ArrayList<Person>();
    }

    public static Property getInstanceOfProperty(String name, String location){
        return new Property(name, location);
    }

    public String getName(){
        return this.name;
    }

    public String getLocation(){
        return this.location;
    }

    public String addPerson(Person p){
        if(!isPersonPresent(p)) {
            this.person.add(p);
            return "Person added.";
        }
        return "Person present already.";
    }

    public String removePerson(Person p){
        if(isPersonPresent(p)) {
            this.person.remove(p);
            return "Person removed.";
        }
        return "Person not present.";
    }

    public boolean isPersonPresent(Person p){
        for(int i=0; i<this.person.size(); i++){
            if(p == this.person.get(i))
                return true;
        }
        return false;
    }

    public String toString(){
        String s = "Property ID: " + this.getName() + "\n" + "Location: " + this.getLocation();
        return s;
    }
}