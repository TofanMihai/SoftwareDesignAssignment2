package com.example.assignment2sd.bll;

import com.example.assignment2sd.dao.EventDAO;
import com.example.assignment2sd.objects.Event;
import org.springframework.stereotype.Service;

@Service
public class EventBLL
{
    public EventDAO eventDAO;

    public EventBLL()
    {
        this.eventDAO = new EventDAO();
    }


    public void insertEvent(Event event)
    {
        this.eventDAO.insert(event);


    }

    public EventDAO getDAO()
    {
        return this.eventDAO;
    }

}
