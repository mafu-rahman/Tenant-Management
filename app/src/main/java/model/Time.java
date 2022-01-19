package model;

public class Time {

    int month;
    int year;

    private Time(int month, int year){
        this.month = month;
        this.year = year;

    }

    public static Time getInstanceOfTime(int month, int year){
        return new Time(month, year);
    }

    public int getMonth(){
        return this.month;
    }

    public void setMonth(int month){
        this.month = month;
    }

    public void setYear(int year){
        this.year = year;
    }

    public int getYear(){
        return this.year;
    }

    public boolean equals(Time t) {
        if(this.month == t.month && this.year == t.year)
            return true;
        return false;
    }
}
