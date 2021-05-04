package com.example.assignment2sd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.assignment2sd.connection.ConnectionFactory;
import com.example.assignment2sd.objects.Fighter;

public class FighterDAO extends AbstractDAO<Fighter>
{
    private String updateFighterScheduledQuery = "UPDATE assignment1sd.fighter SET scheduled = ? WHERE firstName = ? AND lastName = ?";
    private String updateFighterInIsolationQuery = "UPDATE assignment1sd.fighter SET inIsolation = ? WHERE firstName = ? AND lastName = ?";
    private String getFightersByDates = "SELECT * FROM assignment1sd.fighter";


    public ResultSet getFightersByDates()
    {
        PreparedStatement st = null;
        ResultSet result = null;
        Connection connect = ConnectionFactory.getConnection();

        try
        {
            st = connect.prepareStatement(getFightersByDates);
            result = st.executeQuery();
            return result;

        }
        catch (SQLException e)
        {

            e.printStackTrace();
            return result;

        }
    }

    public void updateFighterScheduled(Fighter fighter, String scheduled)
    {
        PreparedStatement st = null;

        Connection connect = ConnectionFactory.getConnection();
        try
        {
            st = connect.prepareStatement(updateFighterScheduledQuery);
            st.setString(1, scheduled);
            st.setString(2, fighter.getFirstName());
            st.setString(3, fighter.getLastName());

            st.executeUpdate();

        }
        catch (SQLException e)
        {

            e.printStackTrace();

        }
    }

    public void updateFighterInIsolation(Fighter fighter, String inIsolation)
    {
        PreparedStatement st = null;

        Connection connect = ConnectionFactory.getConnection();
        try
        {
            st = connect.prepareStatement(updateFighterInIsolationQuery);
            st.setString(1, inIsolation);
            st.setString(2, fighter.getFirstName());
            st.setString(3, fighter.getLastName());

            st.executeUpdate();

        }
        catch (SQLException e)
        {

            e.printStackTrace();

        }
    }




}
