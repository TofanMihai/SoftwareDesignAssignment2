package com.example.assignment2sd.objects;

public class Event
{
    private int idEvent;

    public String location;
    public String date;
    public String hour;
    public String fighterOne;
    public String fighterTwo;
    public int week;


    public Event()
    {

    }

    public Event(String location, String date, String hour, String fighterOne, String fighterTwo , int week)
    {
        this.location = location;
        this.date = date;
        this.hour = hour;
        this.fighterOne = fighterOne;
        this.fighterTwo = fighterTwo;
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }


    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getFighterOne() {
        return fighterOne;
    }

    public void setFighterOne(String fighterOne) {
        this.fighterOne = fighterOne;
    }

    public String getFighterTwo() {
        return fighterTwo;
    }

    public void setFighterTwo(String fighterTwo) {
        this.fighterTwo = fighterTwo;
    }

    @Override
    public String toString() {
        return "Event [idEvent=" + idEvent + ", location=" + location + ", date=" + date + ", hour=" + hour
                + ", fighterOne=" + fighterOne + ", fighterTwo=" + fighterTwo + ", week=" + week + "]";
    }
}
