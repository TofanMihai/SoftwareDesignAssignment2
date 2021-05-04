package com.example.assignment2sd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.assignment2sd.connection.ConnectionFactory;
import com.example.assignment2sd.objects.CovidTest;

public class CovidTestDAO extends AbstractDAO<CovidTest>
{

    private String getIDQuery = "SELECT idCovidTests FROM assignment1sd.covidTest WHERE arrivalTest = ? AND secondTest = ?";
    private String updateTestQuery = "UPDATE assignment1sd.covidTest SET secondTest = ? where fighterFirstName = ? AND fighterLastName = ?";
    private String updateDataQuery = "UPDATE assignment1sd.covidTest SET secondTestDate = ? where fighterFirstName = ? AND fighterLastName = ?";

    public int getCovidTestDAOId(CovidTest covidTest)
    {

        PreparedStatement st = null;
        ResultSet result = null;
        Connection connect = ConnectionFactory.getConnection();
        try
        {
            st = connect.prepareStatement(getIDQuery);
            st.setString(1, covidTest.getArrivalTest());
            st.setString(2,covidTest.getSecondTest());

            result = st.executeQuery();
            result.next();
            int id = result.getInt(1);
            return id;

        }
        catch (SQLException e)
        {

            e.printStackTrace();
            return 0;
        }
    }

    public void updateSecondTest(String resultTest, String firstName, String lastName)
    {

        PreparedStatement st = null;
        Connection connect = ConnectionFactory.getConnection();
        try
        {
            st = connect.prepareStatement(updateTestQuery);
            st.setString(1, resultTest);
            st.setString(2, firstName);
            st.setString(3, lastName);

            st.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateSecondData(String data, String firstName, String lastName)
    {

        PreparedStatement st = null;
        Connection connect = ConnectionFactory.getConnection();
        try
        {
            st = connect.prepareStatement(updateDataQuery);
            st.setString(1, data);
            st.setString(2, firstName);
            st.setString(3, lastName);

            st.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
