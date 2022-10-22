package com.dhbw.RestaurantsReservation.dataManagerImpl;

import com.dhbw.RestaurantsReservation.dataManager.TaskManager;
import com.dhbw.RestaurantsReservation.model.restaurant.Restaurant;
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

    String databaseURL = "jdbc:postgresql://ec2-52-48-159-67.eu-west-1.compute.amazonaws.com:5432/d8etghm7ahc52g";
    String username = "ftjfbakovepexz";
    String password = "11d2adc8b6760fa9484ab4de1a456e6fe9f3e6856f4ffb83a9fa667558b80d86";
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

        if (chooser== true){
            // if chooser == ture an usertable is created

            try {
                connection = basicDataSource.getConnection();
                stmt = connection.createStatement();

                String dropTable = "DROP TABLE IF EXISTS usertasks";
                //hier gegebenen Falls wieder auf usertasks ändern
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


    @Override
    public Collection<Restaurant> getAllRestaurants() {
        List<Restaurant> restauranttasks = new ArrayList<>();
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM restauranttasks");
            while (rs.next()) {
                restauranttasks.add(
                        new Restaurant(
                                rs.getString("rName"),
                                rs.getInt("rSeats"),
                                rs.getInt("rZipcode"),
                                rs.getString("rAddress"),
                                rs.getString("rCategory"),
                                rs.getInt("rPhone"),
                                rs.getString("rEmail"),
                                rs.getString("rPassword")
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


        return restauranttasks;
    }

    @Override
    public void addRestaurant(Restaurant restaurant) {
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "INSERT into restauranttasks (rName, rSeats, rZipcode, rAddress," +
                    "rCategory, rPhone, rEmail, rPassword) VALUES (" +
                    "'" + restaurant.getrName() + "', " +
                    "'" + restaurant.getrSeats() + "', " +
                    "'" + restaurant.getrZipcode() + "', " +
                    "'" + restaurant.getrAddress() + "', " +
                    "'" + restaurant.getrCategory() + "', " +
                    "'" + restaurant.getrPhone() + "', " +
                    "'" + restaurant.getrEmail() + "', " +
                    "'" + restaurant.getrPassword() + "')";

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

}
