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

    String databaseURL = "jdbc:postgresql://ec2-54-228-218-84.eu-west-1.compute.amazonaws.com:5432/dce8jva31tlpfp";
    String username = "vocdnjhpriflxg";
    String password = "0cd6fcd4f865a9299def441b6fe168261a9b1c9c8328d65347e2c208cdce64e5";
    BasicDataSource basicDataSource;
    BasicDataSource basicDataSource2;

    static PostgresTaskManagerImpl postgresTaskManager = null;

    private PostgresTaskManagerImpl() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(databaseURL);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        basicDataSource2 = new BasicDataSource();
        basicDataSource2.setUrl(databaseURL);
        basicDataSource2.setUsername(username);
        basicDataSource2.setPassword(password);
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


    public void createTableTask() {

        // Be carefull: It deletes data if table already exists.
        //

            // if chooser == ture an usertable is created
            Statement stmt = null;
            Connection connection = null;
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

    public void createTableRestaurant(){

        Statement stmtr = null;
        Connection connectionr = null;
        try {
            connectionr = basicDataSource2.getConnection();
            stmtr = connectionr.createStatement();

            String dropTable = "DROP TABLE IF EXISTS restaurant";
            stmtr.executeUpdate(dropTable);

            String createTable = "CREATE TABLE restaurant(" +
                    "id SERIAL PRIMARY KEY, " +
                    "rName varchar(100) NOT NULL, " +
                    "rSeats int NOT NULL, " +
                    "rZipcode int NOT NULL, " +
                    "rAddress varchar(250) NOT NULL, " +
                    "rCategory varchar(250) NOT NULL, " +
                    "rPhone int NOT NULL, " +
                    "rEmail varchar(250) NOT NULL, " +
                    "rPassword varchar(250) NOT NULL)";
            stmtr.executeUpdate(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmtr.close();
            connectionr.close();
        } catch (SQLException e) {
            e.printStackTrace();


        }


    }

    @Override
    public Collection<Restaurant> getAllRestaurants(Restaurant restaurant) {
        List<Restaurant> restauranttasks = new ArrayList<>();
        Statement stmtr = null;
        Connection connectionr = null;

        try {
            connectionr = basicDataSource2.getConnection();
            stmtr = connectionr.createStatement();
            ResultSet rsr = stmtr.executeQuery("SELECT * FROM restaurant");
            while (rsr.next()) {
                restauranttasks.add(
                        new Restaurant(
                                rsr.getString("rName"),
                                rsr.getInt("rSeats"),
                                rsr.getInt("rZipcode"),
                                rsr.getString("rAddress"),
                                rsr.getString("rCategory"),
                                rsr.getInt("rPhone"),
                                rsr.getString("rEmail"),
                                rsr.getString("rPassword")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            stmtr.close();
            connectionr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return restauranttasks;
    }

    @Override
    public void addRestaurant(Restaurant restaurant) {
        Statement stmtr = null;
        Connection connectionr = null;

        try {
            connectionr = basicDataSource2.getConnection();
            stmtr = connectionr.createStatement();
            String udapteSQL = "INSERT into restaurant(rName, rSeats, rZipcode, rAddress,rCategory, rPhone, rEmail, rPassword) VALUES (" +
                    "'" + restaurant.getrName() + "', " +
                    "'" + restaurant.getrSeats() + "', " +
                    "'" + restaurant.getrZipcode() + "', " +
                    "'" + restaurant.getrAddress() + "', " +
                    "'" + restaurant.getrCategory() + "', " +
                    "'" + restaurant.getrPhone() + "', " +
                    "'" + restaurant.getrEmail() + "', " +
                    "'" + restaurant.getrPassword() + "')";

            stmtr.executeUpdate(udapteSQL);

            stmtr.close();
            connectionr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmtr.close();
            connectionr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void createTableUser() {

        Statement stmtu = null;
        Connection connectionu = null;
        try {
            connectionu = basicDataSource2.getConnection();
            stmtu = connectionu.createStatement();

            String dropTable = "DROP TABLE IF EXISTS user";
            stmtu.executeUpdate(dropTable);

            String createTable = "CREATE TABLE user(" +
                    "id SERIAL PRIMARY KEY, " +
                    "firstName varchar(100) NOT NULL, " +
                    "lastName varchar(100) NOT NULL, " +
                    "eMail varchar(250) NOT NULL, " +
                    "phoneNumber varchar(50) NOT NULL, " +
                    "password varchar(250) NOT NULL)";
            stmtu.executeUpdate(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmtu.close();
            connectionu.close();
        } catch (SQLException e) {
            e.printStackTrace();


        }

    }

    @Override
    public Collection<User> getAllUsers(User user) {
        List<User> usertasks = new ArrayList<>();
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource2.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");
            while (rs.next()) {
                usertasks.add(
                        new User(
                                rs.getString("firstName"),
                                rs.getString("lastName"),
                                rs.getString("eMail"),
                                rs.getString("phoneNumber"),
                                rs.getString("password")
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
    public void addUser(User user) {
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource2.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "INSERT into user(firstName, lastName, eMail, phoneNumber, password) VALUES (" +
                    "'" + user.getFirstName() + "', " +
                    "'" + user.getLastName() + "', " +
                    "'" + user.getEMail() + "', " +
                    "'" + user.getPassword() + "')";

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
