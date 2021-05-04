package com.example.assignment2sd.objects;

public class CovidTest
{
    private int idCovidTests;
    private String fighterFirstName;
    private String fighterLastName;
    private String arrivalTest;
    private String arrivalTestDate;


    public CovidTest()
    {

    }
    public CovidTest(String fighterFirstName, String fighterLastName, String arrivalTest, String arrivalTestDate, String secondTest, String secondTestDate)
    {

        this.fighterFirstName = fighterFirstName;
        this.fighterLastName = fighterLastName;
        this.arrivalTest = arrivalTest;
        this.arrivalTestDate = arrivalTestDate;
        this.secondTest = secondTest;
        this.secondTestDate = secondTestDate;
    }

    public int getIdCovidTests() {
        return idCovidTests;
    }


    public void setIdCovidTests(int idCovidTests) {
        this.idCovidTests = idCovidTests;
    }


    public String getFighterFirstName() {
        return fighterFirstName;
    }


    public void setFighterFirstName(String fighterFirstName) {
        this.fighterFirstName = fighterFirstName;
    }


    public String getFighterLastName() {
        return fighterLastName;
    }


    public void setFighterLastName(String fighterLastName) {
        this.fighterLastName = fighterLastName;
    }


    public String getArrivalTest() {
        return arrivalTest;
    }


    public void setArrivalTest(String arrivalTest) {
        this.arrivalTest = arrivalTest;
    }


    public String getArrivalTestDate() {
        return arrivalTestDate;
    }


    public void setArrivalTestDate(String arrivalTestDate) {
        this.arrivalTestDate = arrivalTestDate;
    }


    public String getSecondTest() {
        return secondTest;
    }


    public void setSecondTest(String secondTest) {
        this.secondTest = secondTest;
    }


    public String getSecondTestDate() {
        return secondTestDate;
    }


    public void setSecondTestDate(String secondTestDate) {
        this.secondTestDate = secondTestDate;
    }


    private String secondTest;
    private String secondTestDate;


    @Override
    public String toString() {
        return "CovidTest [idCovidTests=" + idCovidTests + ", fighterFirstName=" + fighterFirstName
                + ", fighterLastName=" + fighterLastName + ", arrivalTest=" + arrivalTest + ", arrivalTestDate="
                + arrivalTestDate + ", secondTest=" + secondTest + ", secondTestDate=" + secondTestDate + "]";
    }



}
