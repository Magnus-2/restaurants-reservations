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

        List<Task> tasks = new ArrayList<>();
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tasks");
            while (rs.next()) {
                tasks.add(
                        new Task(
                                rs.getString("userName"),
                                rs.getString("userSureName"),
                                rs.getString("eMail"),
                                rs.getString("userPassword"),
                                rs.getInt("userID")
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


        return tasks;
    }

    @Override
    public void addTask(Task task, User user) {

        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "INSERT into tasks (userName, userSureName, eMail, userPassword, userID) VALUES (" +
                    "'" + task.getUserName() + "', " +
                    "'" + task.getUserSureName() + "', " +
                    "'" + task.geteMail()+ "', " +
                    "'" + task.getUserPassword() + "', " +
                    "'" + task.getUserID() + "')";

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



    public void createTableTask() {

        // Be carefull: It deletes data if table already exists.
        //

        Statement stmt = null;
        Connection connection = null;
        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();

            String dropTable = "DROP TABLE IF EXISTS tasks";
            stmt.executeUpdate(dropTable);

            String createTable = "CREATE TABLE tasks (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name varchar(100) NOT NULL, " +
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
}
