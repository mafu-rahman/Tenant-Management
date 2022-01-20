package model;

public class Time {

    String month;
    int year;

    private Time(String month, int year){
        this.month = month;
        this.year = year;

    }

    public static Time getInstanceOfTime(String month, int year){
        return new Time(month, year);
    }

    public String getMonth(){
        return this.month;
    }

    public void setMonth(String month){
        this.month = month;
    }

    public void setYear(int year){
        this.year = year;
    }

    public int getYear(){
        return this.year;
    }

    public boolean equals(Time t) {
        if(this.month.equals(t.month) && this.year == t.year)
            return true;
        return false;
    }
}
