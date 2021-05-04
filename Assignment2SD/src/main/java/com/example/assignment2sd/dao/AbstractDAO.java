package com.example.assignment2sd.dao;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



import com.example.assignment2sd.connection.ConnectionFactory;

/**
 * The class which creates the SQL commands in a generic way, so that every class that extends this class can use those common commands
 * @author mihai
 *
 * @param <T> generic parameter
 */
public class AbstractDAO<T> {


    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> abstractVariable;

    /*
     * Constructor which initialises the generic abstract variable
     */
    public AbstractDAO()
    {
        this.abstractVariable = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Method for creating the Selection SQL query
     * @param field represents the column from the database
     * @return returns the sql command as a String object in Java
     */
    private String createSelectQuery(String field)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM assignment1sd.");
        sb.append(abstractVariable.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /*
     * Method for searching in the database by the ID column
     * @param id represents the id column from the database
     * @return returns the class sent as a generic parameter after the SQL command was executed
     * @throws NoSuchMethodException if the command was unsuccesfull
     */
    public T findById(int id) throws NoSuchMethodException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sqlQuery = createSelectQuery("id" + abstractVariable.getSimpleName());
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, abstractVariable.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Creates the SQL command for inserting an element in the database
     * @return returns the SQL insert command as a string
     */
    public String createInsertQuery()
    {
        StringBuilder fields = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");

        for(Field attribute: abstractVariable.getDeclaredFields())
        {
            if(fields.length() >= 2) {
                fields.append(", ");
                values.append(", ");
            }
            fields.append(attribute.getName());
            values.append("? ");
        }
        fields.append(") VALUES ");

        String query = "INSERT INTO assignment1sd." + abstractVariable.getSimpleName() +" " + fields + values + ")";

        return query;
    }

    /**
     * Using the SQL Insert command created using the previously implemented createInsertQuery, this method inserts an Object in the DataBase
     * @param object represents the object that will be inserted into the DataBase
     */
    public void insert(T object) {

        String sqlQuery = this.createInsertQuery();
        Connection connect = null;
        PreparedStatement st = null;

        try {
            connect = ConnectionFactory.getConnection();
            st = connect.prepareStatement(sqlQuery);

            Field[] attributes = abstractVariable.getDeclaredFields();

            for(int i = 0; i < attributes.length; i++)
            {

                Field currentAttribute = attributes[i];
                currentAttribute.setAccessible(true);
                @SuppressWarnings("unchecked")
                T currentObject = (T) currentAttribute.get(object);
                st.setObject(i+1, currentObject);
            }

            st.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * Creates the SELECT* SQL command, executes it, and returns the resultSet of the command
     * @return returns the List containing the elements from the result of the execution of the SELECT* command
     */
    public List<T> report()
    {
        List<T> reportedList = new ArrayList<T>();
        String sqlQuery = "SELECT * FROM assignment1sd." + abstractVariable.getSimpleName();
        PreparedStatement st = null;
        ResultSet results = null;

        Connection connect = ConnectionFactory.getConnection();
        try {
            st = connect.prepareStatement(sqlQuery);
            results = st.executeQuery();
            reportedList = createObjects(results);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return reportedList;
    }

    /**
     * Creates the SQL command for deleting and element from the DataBase
     * @return returns the delete an element command as a string
     */
    public String createDeleteQuery()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM assignment1sd.");
        sb.append(abstractVariable.getSimpleName());
        sb.append(" WHERE ");


        Field[] attributes = abstractVariable.getDeclaredFields();
        String idAttribute = attributes[0].getName();

        sb.append(idAttribute);
        sb.append("= ?");

        String deleteQuery = sb.toString();
        return deleteQuery ;
    }

    /**
     *  Using the SQL Delete command created using the previously implemented createDeleteQuery, this method deletes an Object from the DataBase
     * @param object represents the Object that will be deleted
     * @param id represents the Object's ID, the deletion will be execution using  the ID
     */
    public void delete(T object,int id)
    {

        String sqlQuery = this.createDeleteQuery();
        Connection connect = null;
        PreparedStatement st = null;

        connect = ConnectionFactory.getConnection();
        try
        {
            st = connect.prepareStatement(sqlQuery);
            st.setObject(1, id);
            st.executeUpdate();

        } catch (SQLException e)

        {
            e.printStackTrace();
        }

    }

    /**
     * Takes the data from the DataBase and transforms it into com.example.start.objects
     * @param resultSet represents the table containing the result obtained after the execution of the SQL command
     * @return returns the list of Objects created from the resultSet
     * @throws NoSuchMethodException thrown in case the method is not generated correctly
     */
    protected List<T> createObjects(ResultSet resultSet) throws NoSuchMethodException {
        List<T> list = new ArrayList<T>();

        try {
            while (resultSet.next()) {
                T instance = abstractVariable.newInstance();
                for (Field field : abstractVariable.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), abstractVariable);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }
}
