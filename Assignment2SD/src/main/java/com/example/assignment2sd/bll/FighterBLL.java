package com.example.assignment2sd.bll;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.example.assignment2sd.dao.FighterDAO;
import com.example.assignment2sd.objects.Fighter;
import org.springframework.stereotype.Service;

@Service
public class FighterBLL
{
    public FighterDAO fighterDAO;
    public Date tournamentStartDate;

    public FighterBLL()
    {
        this.fighterDAO = new FighterDAO();
    }




    public void insertFighter(Fighter fighter) throws Exception {
        List<Fighter> fightersList = this.fighterDAO.report();
        boolean alreadyRegistered = false;


        for(Fighter f : fightersList)
        {
            if(f.getFirstName().equals(fighter.getFirstName()) &&
                    f.getLastName().equals(fighter.getLastName()))
            {
                alreadyRegistered = true;

//                JOptionPane.showMessageDialog(null, "Fighter wasn't inserted. Fighter "
//                        + fighter.getFirstName() + " " + fighter.getLastName()
//                        + " is already registered");

                throw new Exception("Fighter wasn't inserted. Fighter "
                                    + fighter.getFirstName() + " " + fighter.getLastName()
                                    + " is already registered");
            }
        }
        if(alreadyRegistered == false)
        {
            this.fighterDAO.insert(fighter);
            //JOptionPane.showMessageDialog(null, "You have succesfully registered");


        }

    }

    public FighterDAO getDAO()
    {
        return this.fighterDAO;
    }
}
