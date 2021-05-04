package com.example.assignment2sd.objects;

public class Fighter
{
    private int idfighter;
    private String firstName;
    private String lastName;
    private double weight;
    private String inIsolation;
    private String scheduled;

    public Fighter()
    {

    }


    public Fighter(String firstName, String lastName, double weight, String inIsolation, String scheduled)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.weight = weight;
        this.inIsolation = inIsolation;
        this.scheduled = scheduled;
    }



    public int getIdfighter() {
        return idfighter;
    }
    public void setIdfighter(int idfighter) {
        this.idfighter = idfighter;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getInIsolation()
    {
        return inIsolation;
    }

    public void setInIsolation(String inIsolation)
    {
        this.inIsolation = inIsolation;
    }


    public String getScheduled() {
        return scheduled;
    }



    public void setScheduled(String scheduled) {
        this.scheduled = scheduled;
    }



    @Override
    public String toString() {
        return "Fighter [idfighter=" + idfighter + ", firstName=" + firstName + ", lastName=" + lastName + ", weight="
                + weight + ", inIsolation=" + inIsolation + ", scheduled=" + scheduled + "]";
    }

}
