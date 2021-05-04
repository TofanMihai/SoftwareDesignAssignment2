package com.example.assignment2sd.bll;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.assignment2sd.objects.Event;
import com.example.assignment2sd.objects.Fighter;

public class EventBuilder
{
    public List<Fighter> fighterList;
    public String location;
    public String date;
    public String hour;
    public Fighter fighterOne;
    public int week;


    public EventBuilder addLocation(ArrayList<String> locations)
    {

        Random rnd = new Random();
        int index = rnd.nextInt(locations.size());

        this.location = locations.get(index);
        return this;
    }

    public EventBuilder addDate(String date)
    {
        this.date = date;
        return this;
    }

    public EventBuilder addHour(ArrayList<String> hours)
    {
        Random rnd = new Random();
        int index = rnd.nextInt(hours.size());

        this.hour = hours.get(index);
        return this;
    }

    public EventBuilder addFighterOne(List<Fighter> fighters)
    {
        this.fighterList = fighters;
        return this;
    }

    public EventBuilder addFighterTwo(List<Fighter> fighters)
    {
        this.fighterList = fighters;
        return this;
    }

    public EventBuilder addWeek(int week)
    {
        this.week = week;
        return this;
    }

    public Event build()
    {
        Event newEvent = new Event();

        buildLocation(newEvent);
        buildDate(newEvent);
        buildHour(newEvent);
        buildFighterOne(newEvent);
        buildFighterTwo(newEvent);
        buildWeek(newEvent);

        return newEvent;
    }


    private void buildLocation(Event newEvent)
    {

        newEvent.location = this.location;
    }

    private void buildDate(Event newEvent)
    {
        newEvent.date = this.date;
    }

    private void buildHour(Event newEvent)
    {

        EventBLL eventBLL = new EventBLL();
        List<Event> eventsList = eventBLL.getDAO().report();
        LocalTime eHour = null, eventHour = null;
        if(eventsList.isEmpty())
        {
            newEvent.hour = this.hour;
            return;
        }
        else
        {
            for(Event e : eventsList)
            {
                eHour = LocalTime.parse(e.getHour());
                if(e.getLocation().equals(this.location) && e.getDate().equals(this.date) && e.getHour().equals(this.hour))
                {
                    eventHour = eHour.plusMinutes(30);
                    newEvent.hour = eventHour.toString();
                }
                else
                    newEvent.hour = this.hour;
            }
        }
    }

    private void buildFighterOne(Event newEvent)
    {
        FighterBLL fighterBLL = new FighterBLL();


        for(Fighter f : this.fighterList)
        {
            if(f.getInIsolation().equals("No") &&
                    f.getScheduled().equals("No"))
            {

                newEvent.fighterOne = f.getFirstName().concat(" ").concat(f.getLastName());
                fighterBLL.getDAO().updateFighterScheduled(f, "Yes");
                f.setScheduled("Yes");
                this.fighterOne = f;
                break;
            }
        }

    }

    private void buildFighterTwo(Event newEvent)
    {
        FighterBLL fighterBLL = new FighterBLL();

        if(this.fighterOne == null)
            return;


        for(Fighter f : this.fighterList)
        {

            Double weightMargin = Math.abs(this.fighterOne.getWeight() - f.getWeight());


            if(f.getInIsolation().equals("No") &&
                    f.getScheduled().equals("No") &&
                    weightMargin <= 5 &&
                    !this.fighterOne.equals(f))
            {
                newEvent.fighterTwo = f.getFirstName().concat(" ").concat(f.getLastName());
                fighterBLL.getDAO().updateFighterScheduled(f, "Yes");
                f.setScheduled("Yes");

                fighterBLL.getDAO().updateFighterScheduled(this.fighterOne, "Yes");
                this.fighterOne.setScheduled("Yes");
                break;

            }
            else
            {
                fighterBLL.getDAO().updateFighterScheduled(this.fighterOne, "No");
                this.fighterOne.setScheduled("No");
            }
        }
    }

    private void buildWeek(Event newEvent)
    {
        newEvent.week = this.week;
    }

}
