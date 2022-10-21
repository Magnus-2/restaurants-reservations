package com.dhbw.RestaurantsReservation.dataManagerImpl;

import com.dhbw.RestaurantsReservation.dataManager.TaskManager;
import com.dhbw.RestaurantsReservation.model.user.User;
import com.dhbw.RestaurantsReservation.model.task.Task;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PostgresTaskManagerImpl implements TaskManager {

    String databaseURL = "jdbc:postgresql://ec2-34-252-216-149.eu-west-1.compute.amazonaws.com:5432/d9ggrcte17fo7h";
    String username = "pbyofpxpstoaup";
    String password = "349a5066ac0eef81cd2f2771a0b722448f2b9222f4832d66a8e78be1627a006b";
    BasicDataSource basicDataSource;

    static PostgresTaskManagerImpl postgresTaskManager = null;

    private PostgresTaskManagerImpl() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(databaseURL);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
    }

    static public PostgresTaskManagerImpl getPostgresTaskManagerImpl() {
        if (postgresTaskManager == null)
            postgresTaskManager = new PostgresTaskManagerImpl();
        return postgresTaskManager;
    }






    @Override
    public Collection<Task> getAllTasks(User user) {

        List<Task> usertasks = new ArrayList<>();
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usertasks");
            while (rs.next()) {
                usertasks.add(
                        new Task(
                                rs.getString("name"),
                                rs.getString("surname"),
                                rs.getString("description"),
                                rs.getInt("priority")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return usertasks;
    }

    @Override
    public void addTask(Task task, User user) {

        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "INSERT into usertasks (name, surname, description, priority) VALUES (" +
                    "'" + task.getName() + "', " +
                    "'" + task.getSurname() + "', " +
                    "'" + task.getDescription() + "', " +
                    "'" + task.getPriority() + "')";

            stmt.executeUpdate(udapteSQL);

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public void createTableTask(Boolean chooser) {

        // Be carefull: It deletes data if table already exists.
        //
        Statement stmt = null;
        Connection connection = null;

        if (chooser){
            // if chooser == ture an usertable is created

            try {
                connection = basicDataSource.getConnection();
                stmt = connection.createStatement();

                String dropTable = "DROP TABLE IF EXISTS usertasks";
                stmt.executeUpdate(dropTable);

                String createTable = "CREATE TABLE usertasks (" +
                        "id SERIAL PRIMARY KEY, " +
                        "name varchar(100) NOT NULL, " +
                        "surname varchar(250) NOT NULL, " +
                        "description varchar(250) NOT NULL, " +
                        "priority int NOT NULL)";
                stmt.executeUpdate(createTable);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                stmt.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();


            }

        }
        else {
            //if chooser == false a restauranttable is created
            try {
                connection = basicDataSource.getConnection();
                stmt = connection.createStatement();

                String dropTable = "DROP TABLE IF EXISTS restaurantstasks";
                stmt.executeUpdate(dropTable);

                String createTable = "CREATE TABLE restaurantstasks (" +
                        "id SERIAL PRIMARY KEY, " +
                        "rName varchar(100) NOT NULL, " +
                        "rSeats int NOT NULL, " +
                        "rZipcode int NOT NULL, " +
                        "rAddress varchar(250) NOT NULL, " +
                        "rCategory varchar(250) NOT NULL, " +
                        "rPhone int NOT NULL, " +
                        "rEmail varchar(250) NOT NULL, " +
                        "rPassword varchar(250) NOT NULL)";
                stmt.executeUpdate(createTable);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                stmt.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();


            }

        }
    }
}
