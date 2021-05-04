package com.example.assignment2sd.bll;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.example.assignment2sd.dao.CovidTestDAO;

import com.example.assignment2sd.objects.CovidTest;
import com.example.assignment2sd.objects.Fighter;
import org.springframework.stereotype.Service;

@Service
public class CovidTestBLL
{
    public CovidTestDAO covidTestDAO;

    public CovidTestBLL()
    {
        this.covidTestDAO = new CovidTestDAO();
    }

    public Date addDays(Date initialDate, int days)
    {
        Date newDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(initialDate);
        cal.add(Calendar.DATE, days);
        return newDate = cal.getTime();
    }

    public int getIntRange(int start, int finish)
    {
        Random rnd = new Random();
        int randomNumber = start + rnd.nextInt(finish + 1 - start);
        return randomNumber;

    }

    public Date getDateRange(Date startDate, Date finishDate)
    {
        long startMillis = startDate.getTime();
        long endMillis = finishDate.getTime();
        long randomMilis = ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis);
        Date randomDate = new Date(randomMilis);
        return randomDate;

    }

    public void generateRandomTestResult(Fighter fighter)
    {
        String testResult = null, fighterInIsolation = null, dataString = null;

        CovidTestBLL covidTestBLL = new CovidTestBLL();
        List<CovidTest> testsList = covidTestBLL.getDAO().report();
        String newDataString = null;
        FighterBLL fighterBLL = new FighterBLL();
        for(CovidTest ct : testsList)
        {
            if(ct.getFighterFirstName().equals(fighter.getFirstName()) &&
                    ct.getFighterLastName().equals(fighter.getLastName()) &&
                    fighter.getInIsolation().equals("Yes"))
            {
                if(ct.getSecondTestDate() == null)
                    dataString = ct.getArrivalTestDate();
                else
                    dataString = ct.getSecondTestDate();

                System.out.println(dataString);
                Date newData = null;
                try {
                    newData = new SimpleDateFormat("yyyy-MM-dd").parse(dataString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                newData = addDays(newData, 21); //3 weeks between tests
                newDataString =  new SimpleDateFormat("yyyy-MM-dd").format(newData);

                int random = getIntRange(0,9);
                if(random < 8)
                    testResult = "Negative";
                else
                    testResult = "Positive";

                if(testResult.equals("Negative"))
                    fighterInIsolation = "No";
                else
                    fighterInIsolation = "Yes";
                if(ct.getSecondTest() == null || ct.getSecondTest().equals("Positive"))
                {
                    covidTestBLL.getDAO().updateSecondTest(testResult, fighter.getFirstName(), fighter.getLastName());
                    covidTestBLL.getDAO().updateSecondData(newDataString, fighter.getFirstName(), fighter.getLastName());
                    fighterBLL.getDAO().updateFighterInIsolation(fighter, fighterInIsolation);
                }



            }


        }
    }

    public void insertCovidTest(CovidTest covidTest)
    {
        this.covidTestDAO.insert(covidTest);

    }

    public CovidTestDAO getDAO()
    {
        return this.covidTestDAO;
    }

    public static void main(String[] args)
    {
        CovidTestBLL covidTestBLL = new CovidTestBLL();
        FighterBLL fighterBLL = new FighterBLL();
        List<Fighter> fightersList = fighterBLL.getDAO().report();

        for(Fighter f : fightersList)
            covidTestBLL.generateRandomTestResult(f);
    }
}
